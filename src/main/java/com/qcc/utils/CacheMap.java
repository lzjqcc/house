package com.qcc.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用于零时保存，
 */

public  class CacheMap<T> {
    private final Map<String, T> cache = new ConcurrentHashMap<>(128);
    private final String space;
    public CacheMap(String space) {
        this.space = space;
    }
    public void put(String key, T value) {
        cache.put(getKey(key), value);
    }
    public T get(String key) {
        return cache.get(getKey(key));
    }
    public void removeAll() {
        cache.clear();
    }
    public void remove(String key) {
        cache.remove(getKey(key));
    }
    private  String getKey(String key) {
        return space +":" +key;
    }

}
