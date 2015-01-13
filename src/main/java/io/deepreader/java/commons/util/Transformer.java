package io.deepreader.java.commons.util;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
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
     * Fork -> Process -> Join
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
     * Local variables referenced from a lambda expression must be final or effectively final (FP Closure)
     *
     * <i>External Iteration</i> as compared to <i>Internal Iteration</i> using aggregate operations
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

    /**
     * Template
     */
    private static <K, V> Map<K, V> merge(Map<K, V> a, Map<K, V> mx, BiFunction<? super V, ? super V, ? extends V> remappingFunc) {
        a.forEach((k, v) -> mx.merge(k, v, remappingFunc));
        return mx;
    }

    /**
     * Constructor example:
     * List<Integer> weights = Arrays.asList(7, 3, 4, 10);
     * List<Apple> apples = map(weights, Apple::new);
     * @param lst
     * @param f
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> List<R> transform(List<T> lst, Function<T, R> f) {
        return lst.parallelStream()
                .map(f)
                .collect(Collectors.toList());
    }
}
