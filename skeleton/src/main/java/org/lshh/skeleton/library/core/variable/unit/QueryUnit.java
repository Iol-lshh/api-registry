package org.lshh.skeleton.library.core.variable.unit;

import org.lshh.skeleton.library.core.variable.Variable;
import org.lshh.skeleton.library.core.variable.data.Constant;
import org.lshh.skeleton.library.core.variable.data.DataSet;
import org.lshh.skeleton.library.core.variable.data.DataVariable;
import org.lshh.skeleton.library.resource.query.Query;

public class QueryUnit extends UnitVariable {
    public final Query query;

    public QueryUnit(Query query) {
        this.query = query;
    }

    public static QueryUnit of(Query query) {
        return new QueryUnit(query);
    }

    @Override
    public int act() {
        return 0;
    }

    @Override
    public boolean isReady() {
        return query.isReady();
    }

    public QueryUnit setInput(String key, DataVariable value) {
        if(value instanceof DataSet dataSet) {
            query.setInput(key, dataSet);
        }else if(value instanceof Constant constant) {
            query.setInput(key, constant);
        }
        return this;
    }

    public DataVariable getOutput() {
        return this.query.getOutput();
    }
}
