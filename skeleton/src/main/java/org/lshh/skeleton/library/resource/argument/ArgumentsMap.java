package org.lshh.skeleton.library.resource.argument;

import java.util.*;

public interface ArgumentsMap <K, R> extends Map<K, R> {

    List<R> getList(Object key);

    List<R> put(K key, List<R> list);

    boolean add(K key, R value);

    boolean addAll(K key, List<R> list);

    Collection<List<R>> valueLists();

    Set<Entry<K, List<R>>> entryListSet();
}
