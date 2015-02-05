package io.deepreader.java.commons.util;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * User: Danyang
 * Date: 1/7/2015
 * Time: 14:57
 */
public class Transformer {
    /**
     * Template
     * Fork -> Process -> Join
     * Notice that parallelStream has much more overhead than stream.
     * Method reference increases level of encapsulation
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
                .collect(Collectors.toConcurrentMap(Map.Entry::getKey, Map.Entry::getValue));
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

    /**
     * Flat a stream
     * @param words
     * @return
     */
    public static List<String> flatString(List<String> words) {
        return words.parallelStream()
                .map(elt -> elt.split(""))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }

    /**
     * TODO: replace Object[]
     * @param lstA
     * @param lstB
     * @param <T>
     * @return
     */
    public static <T> List<Object[]> pair(List<T> lstA, List<T> lstB) {
        return lstA.parallelStream()
                .flatMap(i -> lstB.parallelStream()
                                .map(j -> new Object[] {i, j})
                                )
                .collect(Collectors.toList());
    }

    /**
     * Group the indexes of the elements by each element's value.
     * http://stackoverflow.com/questions/27980488/java-8-stream-of-integer-grouping-indexes-of-a-stream-by-the-integers
     * @param nums
     * @return
     */
    public static <T> Map<T, List<Integer>> groupListIndexByValue(List<T> nums) {
        return IntStream.range(0, nums.size())
                .boxed()
                .parallel()
                .collect(Collectors.groupingBy(nums::get));
    }

    /**
     * Group the indexes of the elements by each element's value
     * @param nums
     * @param <T>
     * @return
     */
    public static <T> Map<T, List<Integer>> groupListToMap(Stream<T> nums) {
        PrimitiveIterator.OfInt indexes = IntStream.iterate(0, x -> x + 1).iterator();
        Map<T, List<Integer>> result = new HashMap<>();

        nums.iterator().forEachRemaining(i -> result.merge(i,
                        new ArrayList<>(Arrays.asList(indexes.next())),
                        (l1, l2) -> {l1.addAll(l2); return l1;}
                )
        );

        return result;
    }

    /**
     * Group the list of T and show their counts
     * @param s
     * @param <T>
     * @return
     */
    public static <T> Map<T, Long> groupAndCount(Stream<T> s) {
        return s.collect(Collectors.groupingBy(e -> e, Collectors.counting()));
    }

    /**
     * Remove an entry from a map by key
     * @param map the targeting map
     * @param tester predicate
     * @param <K>
     * @param <V>
     */
    public static <K, V> void removeByKey(Map<K, V> map, Predicate<K> tester) {
        Iterator<K> itr = map.keySet().iterator();
        while(itr.hasNext()) {
            if(tester.test(itr.next()))
                itr.remove();
        }
    }

    /**
     * Remove an entry from a map by value
     * @param map the targeting map
     * @param tester predicate
     * @param <K>
     * @param <V>
     */
    public static <K, V> void removeByValue(Map<K, V> map, Predicate<V> tester) {
        Iterator<V> itr = map.values().iterator();
        while (itr.hasNext()) {
            if(tester.test(itr.next()))
                itr.remove();
        }
    }

    /**
     * Flatten map's value to a list
     * @param map must be sorted
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> List<V> toList(SortedMap<K, V>map) {
        return map.entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    /**
     * Flatten map's value to a list. Order is not guaranteed
     * @param map generic
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> List<V> toList(Map<K, V> map) {
        return map.entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
