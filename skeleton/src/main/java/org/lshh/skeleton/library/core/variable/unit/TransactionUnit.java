package org.lshh.skeleton.library.core.variable.unit;

import org.lshh.skeleton.library.core.transaction.Transaction;
import org.lshh.skeleton.library.core.transaction.TransactionProcessor;
import org.lshh.skeleton.library.core.variable.Variable;

public class TransactionUnit extends UnitVariable {
    private final Transaction transaction;
    private final TransactionProcessor processor;

    public TransactionUnit(Transaction transaction, TransactionProcessor transactionProcessor) {
        this.transaction = transaction;
        this.processor = transactionProcessor;
    }

    public static TransactionUnit of(Transaction transaction, TransactionProcessor transactionProcessor) {
        return new TransactionUnit(transaction, transactionProcessor);
    }

    public Transaction getTransaction() {
        return this.transaction;
    }
    @Override
    public int act() {
        return processor.runProcess(transaction);
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
