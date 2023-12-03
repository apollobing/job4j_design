package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public final void put(K key, V value) {
        cache.putIfAbsent(key, new SoftReference<>(value));
    }

    public final V get(K key) {
        SoftReference<V> soft = cache.get(key);
        V data;
        if (soft == null || soft.get() == null) {
            data = load(key);
            cache.put(key, new SoftReference<>(data));
        } else {
            data = soft.get();
        }
        return data;
    }

    protected abstract V load(K key);

}
