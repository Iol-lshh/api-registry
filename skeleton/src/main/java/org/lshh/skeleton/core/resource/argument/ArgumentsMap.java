package org.lshh.skeleton.core.resource.argument;

import java.util.*;

public class ArgumentsMap <K, V> extends AbstractMap<K, List<V>> {
    Map<K, List<V>> argumentsMap = new HashMap<>();

    public Object getObject(K key){
        List<V> values = argumentsMap.get(key);
        return values.size() > 1 ? values : values.get(0);
    }

    public List<V> getValues(K key){
        return argumentsMap.get(key);
    }

    public V getValue(K key){
        List<V> result = argumentsMap.get(key);
        return result == null ? null : result.getFirst();
    }

    @Override
    public List<V> put(K key, List<V> values){
        argumentsMap.put(key, values);
        return values;
    }

    @Override
    public Set<Entry<K, List<V>>> entrySet() {
        return argumentsMap.entrySet();
    }
}
