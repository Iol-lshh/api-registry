package org.lshh.skeleton.library.transaction;

public class TransactionException extends RuntimeException{
    public TransactionException(String message) {
        super(message);
    }
    public TransactionException(){
        super("Transaction is not ready");
    }
}
