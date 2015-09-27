package io.deepreader.java.commons.util.collections;

import java.util.Map;

/**
 * Created by Daniel on 27/09/15.
 */
public class MapSample<K, V> {
    void inc(Map<K, Integer> map, K k) {
        map.put(k, map.get(k) == null? 1: map.get(k)+1);
    }

    void inc1(Map<K, Integer> map, K key) {
        map.compute(key, (k, v) -> v == null? 1: v+1);
    }
}
