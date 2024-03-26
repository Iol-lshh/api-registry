package org.lshh.skeleton.core.resource.query;

import java.util.List;
import java.util.Map;

public interface Query {
    Query setQuery(String query);
    Query setArgs(String key, Object val);
    List<Map<String, Object>> query() throws Exception;
}
