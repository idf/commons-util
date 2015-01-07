package io.deepreader.java.commons.util;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * User: Danyang
 * Date: 1/7/2015
 * Time: 14:57
 */
public class Transformer {
    /**
     * Template
     * @param map
     * @param kp
     * @param vp
     * @param <K>
     * @param <V>
     * @return
     */
    private static <K, V> Map<K, V> transform(Map<K, V> map, Function<K, K> kp, Function<V, V> vp) {
        return map.entrySet()
                .parallelStream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
    }
}
