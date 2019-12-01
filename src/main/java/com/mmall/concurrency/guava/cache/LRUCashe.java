package com.mmall.concurrency.guava.cache;

/**
 * @Author: lsl
 * @Description:
 * @Date: Created on 17:55 2019/8/18
 */
public interface LRUCashe<K, V> {

    void put(K key, V v);

    V get(K key);

    void remove(K key);

    int size();

    void clear();

    int limit();
}
