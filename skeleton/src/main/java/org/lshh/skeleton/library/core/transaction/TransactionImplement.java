package org.lshh.skeleton.library.core.transaction;

import org.lshh.skeleton.library.core.variable.Variable;
import org.lshh.skeleton.library.core.variable.data.DataVariable;
import org.lshh.skeleton.library.core.variable.unit.UnitVariable;

import java.util.*;

public class TransactionImplement implements Transaction{
    private final Map<String, Variable> inputMap;
    private final Map<String, Variable> outputMap;
    private final Map<String, Variable> variableMap;
    private final Stack<Variable> tmpStack;
    private final List<String[]> processQueue;

    public TransactionImplement() {
        this.inputMap = new HashMap<>();
        this.outputMap = new HashMap<>();
        this.variableMap = new HashMap<>();
        this.processQueue = new ArrayList<>();
        this.tmpStack = new Stack<>();
    }

    @Override
    public boolean isReady() {
        // 변수가 준비되었는지 확인
            // 타입
            // 준비 상태
        for(Variable variable : variableMap.values()){
            if(variable instanceof UnitVariable){
                if(!((UnitVariable) variable).isReady()){
                    return false;
                }
            }
        }

        return false;
    }


    @Override
    public Variable getInput(String key) {
        return inputMap.get(key);
    }

    @Override
    public Transaction setInput(String key, Variable value) {
        inputMap.put(key, value);
        return this;
    }

    @Override
    public Variable getOutput(String key) {
        return outputMap.get(key);
    }

    @Override
    public Transaction setOutput(String key, Variable value) {
        outputMap.put(key, value);
        return this;
    }

    @Override
    public List<String[]> processQueue() {
        return this.processQueue;
    }

    @Override
    public DataVariable getTmpValue() {
        return this.tmpStack.pop();
    }
    @Override
    public Transaction setTmpValue(Variable value){
        this.tmpStack.push(value);
        return this;
    }

    @Override
    public Map<String, Variable> variableMap() {
        return this.variableMap;
    }

    @Override
    public Map<String, Variable> inputMap() {
        return this.inputMap;
    }

    @Override
    public Map<String, Variable> outputMap() {
        return this.outputMap;
    }
}
