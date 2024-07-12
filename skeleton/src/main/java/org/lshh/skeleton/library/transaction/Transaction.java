package org.lshh.skeleton.library.transaction;

import org.lshh.skeleton.library.variable.Variable;

import java.util.List;
import java.util.Map;

public interface Transaction {
    boolean isReady();
    Variable getInput(String key);
    Transaction setInput(String key, Variable value);
    Variable getOutput(String key);
    Transaction setOutput(String key, Variable value);

    List<String[]> processQueue();

    Variable getTempValue();
    Transaction setTempValue(Variable value);

    Map<String, Variable> variableMap();
    Map<String, Variable> inputMap();
    Map<String, Variable> outputMap();
}
