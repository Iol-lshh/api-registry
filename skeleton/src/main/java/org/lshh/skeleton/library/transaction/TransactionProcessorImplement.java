package org.lshh.skeleton.library.transaction;

import org.lshh.skeleton.library.variable.Variable;
import org.lshh.skeleton.library.variable.data.Constant;
import org.lshh.skeleton.library.variable.data.DataSet;
import org.lshh.skeleton.library.variable.data.DataVariable;
import org.lshh.skeleton.library.variable.unit.QueryUnit;
import org.lshh.skeleton.library.variable.unit.TransactionUnit;
import org.lshh.skeleton.library.variable.unit.UnitVariable;
import org.lshh.skeleton.library.lock.LockManager;
import org.lshh.skeleton.library.resource.query.Query;
import org.lshh.skeleton.library.resource.query.QueryManager;
import org.lshh.skeleton.library.resource.resourcer.ResourcerManager;
import org.lshh.skeleton.library.router.RouterManager;

public class TransactionProcessorImplement implements TransactionProcessor {
    private final RouterManager routerManager;
    private final TransactionManager transactionManager;
    private final ResourcerManager resourcerManager;
    private final QueryManager queryManager;
    private final LockManager lockManager;

    public TransactionProcessorImplement(RouterManager routerManager, TransactionManager transactionManager, ResourcerManager resourcerManager, QueryManager queryManager, LockManager lockManager) {
        this.routerManager = routerManager;
        this.transactionManager = transactionManager;
        this.resourcerManager = resourcerManager;
        this.queryManager = queryManager;
        this.lockManager = lockManager;
    }

    @Override
    public int runProcess(Transaction transaction) {
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

    // 가져오기 => 할당
        // 가져오기 => tmp
        // tmp => variable/input/output
    @Override
    public void switchStep(Transaction transaction, String[] step){
        switch (step[0]){
            // set constant => tmp
            case "this_set_tmp":
                // step[1] : key
                transaction_set_tmp(transaction, step[1]);
                break;
            // set input => tmp
            case "this_get_input":
                // step[1] : key
                transaction_get_input(transaction, step[1]);
                break;
            // set tmp => input
            case "this_set_input":
                // step[1] : key
                transaction_set_input(transaction, step[1]);
                break;
            // set output => tmp
            case "this_get_output":
                // step[1] : key
                transaction_get_output(transaction, step[1]);
                break;
            // set tmp => output
            case "this_set_output":
                // step[1] : key, step[2] : value
                transaction_set_output(transaction, step[1]);
                break;
            // set variable => tmp
            case "this_get_variable":
                // step[1] : key
                transaction_get_variable(transaction, step[1]);
                break;
            // set tmp => variable
            case "this_set_variable":
                // step[1] : key
                transaction_set_variable(transaction, step[1]);
                break;
            // transaction => tmp
            case "this_find_transaction":
                // step[1] : key
                transaction_find_transaction(transaction, step[1]);
                break;
            // query => tmp
            case "this_find_query":
                // step[1] : key
                transaction_find_query(transaction, step[1]);
                break;
            // get lock
            case "lock":
                // step[1] : key
                transaction_lock(step[1]);
                break;
            // release lock
            case "unlock":
                // step[1] : key
                transaction_unlock(step[1]);
                break;
            // query dataVariable => tmp
            case "query_execute":
                // step[1] : query key
                transaction_query(transaction, step[1]);
                break;
            // set tmp => query.input
            case "query_set_input":
                // step[1] : query key, step[2] : query input key
                transaction_query_set_input(transaction, step[1], step[2]);
                break;
            // set query.output => tmp
            case "query_get_output":
                // step[1] : query key, step[2] : key
                transaction_query_get_output(transaction, step[1]);
                break;
            // set tmp => tramsaction.input
            case "transaction_set_input":
                // step[1] : transaction key, step[2] : key
                TransactionUnit transactionUnit = (TransactionUnit) transaction.variableMap().get(step[1]);
                transaction_set_input(transactionUnit.getTransaction(), step[2]);
                break;
            case "transaction_execute":
                // step[1] : transaction key
                TransactionUnit transactionUnit3 = (TransactionUnit) transaction.variableMap().get(step[1]);
                transaction_execute(transactionUnit3.getTransaction());
                break;
            case "transaction_get_output":
                // step[1] : transaction key, step[2] : key
                TransactionUnit transactionUnit2 = (TransactionUnit) transaction.variableMap().get(step[1]);
                transaction_get_output(transaction, step[2]);
                break;
            case "this_get_dataset":
                // step[1] : key
                transaction_get_dataset_variable(transaction, step[1]);
                break;
            case "this_dataset_where":
                // poll stack
                Constant constant = (Constant) transaction.getTmpValue();
                DataSet dataSet = (DataSet) transaction.getTmpValue();
                //dataSet.where(constant);

            // if
            // 데이터셋 where
            // 데이터셋 select
            // 데이터셋 join
            // 조인 on
            // 조인 select
            // 조인 toDataSet
                // 컴파일 결과 테스트 => 유닛의 act는 대신 isReady만 확인. act가 서비스에 영향을 줄 수 있기 때문에
                // 실패시, context 영속성 남기지 않음
                // 성공시, context 영속성 남김
                // 컴파일시, 타입 체크
                // 컴파일시, 주석 무시
            default:
                throw new IllegalArgumentException("Unknown step type");
        }
    }

    private void transaction_get_dataset_variable(Transaction transaction, String key) {
        DataSet dataSet = (DataSet) transaction.variableMap().get(key);
        transaction.setTmpValue(dataSet);
    }

    private void transaction_execute(Transaction transaction) {
        runProcess(transaction);
    }

    private void transaction_query_get_output(Transaction transaction, String queryKey) {
        QueryUnit queryUnit = (QueryUnit) transaction.variableMap().get(queryKey);
        transaction.setTmpValue(queryUnit.getOutput());
    }

    private void transaction_query_set_input(Transaction transaction, String queryKey, String inputKey) {
        QueryUnit queryUnit = (QueryUnit) transaction.variableMap().get(queryKey);
        queryUnit.setInput(inputKey, (DataVariable) transaction.getTmpValue());
    }

    private void transaction_query(Transaction transaction, String key) {
        QueryUnit queryUnit = (QueryUnit) transaction.variableMap().get(key);
        queryUnit.act();
    }

    private void transaction_unlock(String key) {
        lockManager.unlock(key);
    }

    private void transaction_lock(String key) {
        lockManager.lock(key);
    }

    private void transaction_find_query(Transaction transaction, String key) {
        Query query = queryManager.find(key);
        Variable tmp = UnitVariable.of(query);
        transaction.setTmpValue(tmp);
    }

    private void transaction_find_transaction(Transaction transaction, String key) {
        Transaction _transaction = transactionManager.find(key);
        Variable tmp = UnitVariable.of(_transaction);
        transaction.setTmpValue(tmp);
    }

    private void transaction_set_variable(Transaction transaction, String key) {
        transaction.variableMap().put(key, transaction.getTmpValue());
    }

    private void transaction_get_variable(Transaction transaction, String key) {
        var tmp = transaction.variableMap().get(key);
        transaction.setTmpValue(tmp);
    }

    private void transaction_set_tmp(Transaction transaction, String key){
        Variable tmp = Constant.of(key);
        transaction.setTmpValue(tmp);
    }
    private void transaction_get_input(Transaction transaction, String key){
        Variable tmp = transaction.getInput(key);
        transaction.setTmpValue(tmp);
    }
    private void transaction_set_input(Transaction transaction, String key){
        transaction.setInput(key, transaction.getTmpValue());
    }
    private void transaction_get_output(Transaction transaction, String key){
        Variable tmp = transaction.getOutput(key);
        transaction.setTmpValue(tmp);
    }
    private void transaction_set_output(Transaction transaction, String key){
        transaction.setOutput(key, transaction.getTmpValue());
    }
}
