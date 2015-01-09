package io.deepreader.java.commons.util;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
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

    /**
     * source -> filter -> map -> consume
     * Equivalent to aggregate operations that accept Lambda expressions as parameters
     * Local variables referenced from a lambda expression must be final or effectively final
     * @param source
     * @param tester .test()
     * @param mapper .apply()
     * @param block .accept()
     * @param <X>
     * @param <Y>
     */
    public static <X, Y> void transform(
            Iterable<X> source,
            Predicate<X> tester,
            Function <X, Y> mapper,
            Consumer<Y> block) {
        for (X p : source) {
            if (tester.test(p)) {
                Y data = mapper.apply(p);
                block.accept(data);
            }
        }
    }
}
