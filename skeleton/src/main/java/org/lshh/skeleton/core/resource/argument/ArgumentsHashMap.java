package org.lshh.skeleton.core.resource.argument;

import java.util.*;
import java.util.stream.Collectors;

public class ArgumentsHashMap<K, R> implements ArgumentsMap<K, R>{
    Map<K, List<R>> kernelMap = new HashMap<>();

    @Override
    public int size() {
        return kernelMap.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return kernelMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return kernelMap.entrySet().stream().anyMatch(entry ->
                !entry.getValue().isEmpty()
                        && entry.getValue().contains(value)
        );
    }

    @Override
    public R get(Object key) {
        List<R> result = kernelMap.get(key);
        return result == null ? null
                : result.isEmpty() ? null : result.getFirst();
    }

    @Override
    public List<R> getList(Object key){
        return kernelMap.get(key);
    }

    @Override
    public R put(K key, R value) {
        if(key == null) return null;

        List<R> values = new ArrayList<>();
        values.add(value);
        kernelMap.put(key, values);
        return get(key);
    }

    @Override
    public List<R> put(K key, List<R> list) {
        kernelMap.put(key, list);
        return getList(key);
    }

    @Override
    public boolean add(K key, R value){
        List<R> values = kernelMap.computeIfAbsent(key, k -> new ArrayList<>());
        values.add(value);
        kernelMap.put(key, values);
        return true;
    }

    @Override
    public boolean addAll(K key, List<R> list){
        List<R> oldValues = kernelMap.get(key);
        List<R> values = new ArrayList<>();
        if (oldValues != null) {
            values.addAll(oldValues);
        }
        values.addAll(list);
        kernelMap.put(key, values);
        return true;
    }

    @Override
    public R remove(Object key) {
        if(kernelMap.containsKey(key))
            return kernelMap.remove(key).getFirst();
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends R> map) {
        map.forEach(this::put);
    }

    @Override
    public void clear() {
        kernelMap.clear();
    }

    @Override
    public Set<K> keySet() {
        return kernelMap.keySet();
    }

    @Override
    public Collection<R> values() {
        return kernelMap.values().stream().flatMap(List::stream).toList();
    }

    @Override
    public Collection<List<R>> valueLists(){
        return kernelMap.values();
    }

    @Override
    public Set<Entry<K, R>> entrySet() {
        if(kernelMap.isEmpty())
            return null;

        return kernelMap.entrySet().stream()
                .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue().getFirst()))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Entry<K, List<R>>> entryListSet(){
        return kernelMap.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        if(o == this)
            return true;
        if (!(o instanceof ArgumentsMap<?, ?> m))
            return false;
        if (m.size() != size())
            return false;

        for (Entry<K, List<R>> e : entryListSet()) {
            K key = e.getKey();
            List<R> value = e.getValue();
            if (value == null) {
                if (!(m.containsKey(key) && m.getList(key) == null))
                    return false;
            } else {
                if (!value.equals(m.getList(key)))
                    return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int h = 0;
        for (Entry<K, List<R>> entryList : entryListSet())
            h += entryList.hashCode();
        return h;
    }

}