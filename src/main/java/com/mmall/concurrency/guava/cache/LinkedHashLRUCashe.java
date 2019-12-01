package com.mmall.concurrency.guava.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: lsl
 * @Description:
 * @Date: Created on 17:59 2019/8/18
 */
public class LinkedHashLRUCashe<K, V> implements LRUCashe<K, V> {

    private static class InternalLRUCashe<K, V> extends LinkedHashMap<K, V> {
        final private int limit;
        public InternalLRUCashe(int limit) {
            super(16, 0.75f, true);
            this.limit = limit;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > limit;
        }
    }

    private final int limit;
    private final InternalLRUCashe<K, V> internalLRUCashe;

    public LinkedHashLRUCashe(int limit) {
        this.limit = limit;
        this.internalLRUCashe = new InternalLRUCashe<>(limit);
    }

    @Override
    public void put(K key, V v) {
        this.internalLRUCashe.put(key, v);
    }

    @Override
    public V get(K key) {
        return this.internalLRUCashe.get(key);
    }

    @Override
    public void remove(K key) {
        this.internalLRUCashe.remove(key);
    }

    @Override
    public int size() {
        return this.internalLRUCashe.size();
    }

    @Override
    public void clear() {
        this.internalLRUCashe.clear();
    }

    @Override
    public int limit() {
        return this.limit;
    }

    @Override
    public String toString() {
        return this.internalLRUCashe.toString();
    }
}
