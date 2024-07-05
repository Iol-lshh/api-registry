package org.lshh.skeleton.library.core.transaction;

import org.lshh.skeleton.library.core.variable.Variable;
import org.lshh.skeleton.library.core.variable.data.DataVariable;

import java.util.List;
import java.util.Map;

public interface Transaction {
    boolean isReady();
    Variable getInput(String key);
    Transaction setInput(String key, Variable value);
    Variable getOutput(String key);
    Transaction setOutput(String key, Variable value);

    List<String[]> processQueue();

    Variable getTmpValue();

    Transaction setTmpValue(Variable value);

    Map<String, Variable> variableMap();
    Map<String, Variable> inputMap();
    Map<String, Variable> outputMap();
}
