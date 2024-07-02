package org.lshh.skeleton.core.transaction.implement;

import org.lshh.skeleton.core.transaction.Transaction;
import org.lshh.skeleton.core.transaction.TransactionCompiled;

public class SimpleTransaction extends TransactionImplement {
    TransactionCompiled compiled;
    TransactionContext context;

    public SimpleTransaction(TransactionContext context, TransactionCompiled compiled) {
        this.context = context;
        this.compiled = compiled;
    }

    public Transaction copy() {
        TransactionCompiled compiled = this.compiled.copy();
        return new SimpleTransaction(context, compiled);
    }

    public void process(){
        for(String key : compiled.process()){
            actCommand(key);
        }
    }
    public void actCommand(String key){

    }
    public void input(String key, Object value){
        compiled.inputs().put(key, value);
    }
    public Object input(String key){
        return compiled.inputs().get(key);
    }
    public void output(String key, Object value){
        compiled.outputs().put(key, value);
    }
    public Object output(String key){
        return compiled.outputs().get(key);
    }
}
