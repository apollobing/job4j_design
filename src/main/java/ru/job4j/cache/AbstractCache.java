package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public final void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    public final V get(K key) {
        V data;
        if (cache.containsKey(key)) {
            data = cache.get(key).get();
            if (data == null) {
                data = load(key);
                put(key, data);
            }
        } else {
            data = load(key);
            put(key, data);
        }
        return data;
    }

    protected abstract V load(K key);

}
