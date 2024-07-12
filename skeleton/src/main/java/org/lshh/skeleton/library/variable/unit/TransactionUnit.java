package org.lshh.skeleton.library.variable.unit;

import org.lshh.skeleton.library.transaction.Transaction;
import org.lshh.skeleton.library.transaction.agent.TransactionAgent;
import org.lshh.skeleton.library.variable.Variable;

public class TransactionUnit extends UnitVariable {
    private final Transaction transaction;
    private final TransactionAgent agent;

    public TransactionUnit(Transaction transaction, TransactionAgent transactionAgent) {
        this.transaction = transaction;
        this.agent = transactionAgent;
    }

    public static TransactionUnit of(Transaction transaction, TransactionAgent transactionAgent) {
        return new TransactionUnit(transaction, transactionAgent);
    }

    public Transaction getTransaction() {
        return this.transaction;
    }
    @Override
    public int act() {
        return agent.run(transaction);
    }

    @Override
    public boolean isReady() {
        return transaction.isReady();
    }

    public void setInput(String key, Variable variable) {
        transaction.setInput(key, variable);
    }
    public Variable getOutput(String key) {
        return transaction.getOutput(key);
    }
}
