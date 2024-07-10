package org.lshh.skeleton.library.resource.query.implement;

import org.lshh.skeleton.library.variable.data.Constant;
import org.lshh.skeleton.library.variable.data.DataSet;
import org.lshh.skeleton.library.variable.data.DataVariable;
import org.lshh.skeleton.library.resource.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleQuery implements Query {

    private final Long id;
    private final String queryBody;
    private final JdbcTemplateWrapper jdbcTemplate;
    private final Map<String, DataVariable> inputs;
    private DataVariable output;

    public SimpleQuery(QueryContext query, JdbcTemplateWrapper jdbcTemplate){
        this.id = query.getId();
        this.queryBody = query.getBody();
        this.jdbcTemplate = jdbcTemplate;
        this.inputs = new HashMap<>();
    }

    @Override
    public Query query() {
        Map<String, Object> args = convertInputsToArgsForQuery();
        List<Map<String, Object>> list = jdbcTemplate.queryForList(this.queryBody, args);
        List<String> columns = list.isEmpty() ? List.of() : List.copyOf(list.getFirst().keySet());
        List<List<Object>> rows = list.stream().map(map -> List.copyOf(map.values())).toList();
        this.output = DataSet.of(columns, rows);
        return this;
    }

    private Map<String, Object> convertInputsToArgsForQuery(){
        Map<String, Object> args = new HashMap<>();
        for (Map.Entry<String, DataVariable> entry : this.inputs.entrySet()) {
            if (entry.getValue() instanceof DataSet dataSet) {
                for (int i = 0; i < dataSet.getColumnSize(); i++) {
                    args.put(entry.getKey() + "_" + dataSet.getColumn(i), dataSet.getRow(0).get(i));
                }
            } else if (entry.getValue() instanceof Constant constant) {
                args.put(entry.getKey(), constant.getValue());
            }
        }
        return args;
    }

    @Override
    public boolean isReady() {
        return jdbcTemplate.isReady();
    }

    @Override
    public Query setInput(String key, DataSet value) {
        this.inputs.put(key, value);
        return this;
    }
    @Override
    public Query setInput(String key, Constant value) {
        this.inputs.put(key, value);
        return this;
    }

    @Override
    public DataVariable getOutput() {
        return this.output;
    }
}
