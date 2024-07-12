package org.lshh.skeleton.library.transaction.agent;

import org.lshh.skeleton.library.resource.query.Query;
import org.lshh.skeleton.library.transaction.Transaction;
import org.lshh.skeleton.library.transaction.TransactionManager;
import org.lshh.skeleton.library.transaction.keyword.TransactionKeyword;
import org.lshh.skeleton.library.lock.LockManager;
import org.lshh.skeleton.library.resource.query.QueryManager;
import org.lshh.skeleton.library.transaction.keyword.TransactionCommand.*;
import org.lshh.skeleton.library.variable.Variable;
import org.lshh.skeleton.library.variable.data.Constant;
import org.lshh.skeleton.library.variable.data.DataSet;
import org.lshh.skeleton.library.variable.data.DataVariable;
import org.lshh.skeleton.library.variable.data.join.JoinSet;
import org.lshh.skeleton.library.variable.unit.QueryUnit;
import org.lshh.skeleton.library.variable.unit.TransactionUnit;

public class TransactionAgentImplement implements TransactionAgent {
    private final TransactionManager transactionManager;
    private final QueryManager queryManager;
    private final LockManager lockManager;

    public TransactionAgentImplement(TransactionManager transactionManager, QueryManager queryManager, LockManager lockManager) {
        this.transactionManager = transactionManager;
        this.queryManager = queryManager;
        this.lockManager = lockManager;
    }

    @Override
    public int run(Transaction transaction) {
        try{
            if (!transaction.isReady()) {
                throw new IllegalStateException("Transaction is not ready");
            }
            for(String[] step : transaction.processQueue()){
                switchStep(transaction, step);
            }
        }catch (Exception e){
            return 0;
        }
        return 1;
    }

    @Override
    public void switchStep(Transaction transaction, String[] step) {

        switch (TransactionKeyword.valueOf(step[0])){
            case THIS->{
                switchThisCommand(transaction, step);
            }
            case CHILD->{
                switchChildCommand(transaction, step);
            }
            case QUERY->{
                switchQueryCommand(transaction, step);
            }
            case DATA_SET->{
                switchDataSetCommand(transaction, step);
            }
            case JOIN_SET->{
                switchJoinSetCommand(transaction, step);
            }
            default -> {
                throw new IllegalStateException("Unexpected value: " + TransactionKeyword.valueOf(step[0]));
            }
        }
    }


    @Override
    public void switchThisCommand(Transaction transaction, String[] step) {
        switch (ThisTransactionCommand.valueOf(step[1])) {
            case GET_INPUT -> {
                transaction.setTempValue(
                    transaction.getInput(step[2])
                );
            }
            case SET_OUTPUT -> {
                transaction.setOutput(
                        step[2],
                        transaction.getTempValue()
                );
            }
            case GET_VAR -> {
                transaction.setTempValue(
                    transaction.variableMap().get(step[2])
                );
            }
            case SET_VAR -> {
                transaction.variableMap().put(
                    step[2],
                    transaction.getTempValue()
                );
            }
            case FIND_QUERY -> {
                Query query = queryManager.find(step[2]);
                transaction.setTempValue(QueryUnit.of(query));
            }
            case FIND_TRANSACTION -> {
                Transaction childTransaction = transactionManager.find(step[2]);
                transaction.setTempValue(TransactionUnit.of(childTransaction));
            }
            default -> {
                throw new IllegalStateException("Unexpected value: " + TransactionKeyword.valueOf(step[0]));
            }
        }
    }

    @Override
    public void switchChildCommand(Transaction transaction, String[] step) {
        if(!(transaction.getTempValue() instanceof TransactionUnit transactionUnit)){
            throw new IllegalStateException("Unexpected value: " + TransactionKeyword.valueOf(step[0]));
        }

        switch (ChildTransactionCommand.valueOf(step[1])) {
            case GET_OUTPUT -> {

            }
            case SET_INPUT -> {

            }
            case RUN_TRANSACTION -> {

            }
            default -> {
                throw new IllegalStateException("Unexpected value: " + TransactionKeyword.valueOf(step[0]));
            }
        }
    }

    @Override
    public void switchQueryCommand(Transaction transaction, String[] step) {
        if(!(transaction.getTempValue() instanceof QueryUnit queryUnit)){
            throw new IllegalStateException("Unexpected value: " + TransactionKeyword.valueOf(step[0]));
        }

        switch (QueryCommand.valueOf(step[1])) {
            case SET_INPUT -> {
                queryUnit.setInput(
                    step[2],
                    (DataVariable) transaction.getTempValue()
                );
            }
            case GET_OUTPUT -> {
                transaction.setTempValue(
                    queryUnit.getOutput()
                );
            }
            case QUERY -> {
                queryUnit.act();
            }
            default -> {
                throw new IllegalStateException("Unexpected value: " + TransactionKeyword.valueOf(step[0]));
            }
        }
    }


    @Override
    public void switchJoinSetCommand(Transaction transaction, String[] step) {
        if(!(transaction.getTempValue() instanceof JoinSet joinSet)) {
            throw new IllegalStateException("Unexpected value: " + TransactionKeyword.valueOf(step[0]));
        }

        switch (JoinSetCommand.valueOf(step[1])) {
            case ON -> {
                joinSet.on(
                        ((Constant) transaction.getTempValue()).getValue().toString(),
                        ((Constant) transaction.getTempValue()).getValue().toString()
                );
            }
            case ON_SAME -> {
                joinSet.on(
                        ((Constant) transaction.getTempValue()).getValue().toString()
                );
            }
            case SELECT_LEFT -> {
                joinSet.selectFromLeft(
                        ((Constant) transaction.getTempValue()).getValue().toString()
                );
            }
            case SELECT_RIGHT -> {
                joinSet.selectFromRight(
                        ((Constant) transaction.getTempValue()).getValue().toString()
                );
            }
            case TO_DATASET -> {
                transaction.setTempValue(
                        joinSet.toDataSet()
                );
            }
            default -> {
                throw new IllegalStateException("Unexpected value: " + TransactionKeyword.valueOf(step[0]));
            }
        }
    }

    @Override
    public void switchDataSetCommand(Transaction transaction, String[] step) {
        if(!(transaction.getTempValue() instanceof DataSet dataSet)){
            throw new IllegalStateException("Unexpected value: " + TransactionKeyword.valueOf(step[0]));
        }

        switch (DataSetCommand.valueOf(step[1])) {
            case SELECT -> {

            }
            case WHERE -> {
                dataSet.where(
                        ((Constant) transaction.getTempValue()).getValue().toString(),
                        ((Constant) transaction.getTempValue()).getValue().toString(),
                        ((Constant) transaction.getTempValue()).getValue().toString()
                );
            }
            case INNER_JOIN -> {
                transaction.setTempValue(
                    dataSet.innerJoin((DataSet) transaction.getTempValue())
                );
            }
            case LEFT_JOIN -> {
                transaction.setTempValue(
                    dataSet.leftJoin((DataSet) transaction.getTempValue())
                );
            }
            default -> {
                throw new IllegalStateException("Unexpected value: " + TransactionKeyword.valueOf(step[0]));
            }
        }
    }

}
