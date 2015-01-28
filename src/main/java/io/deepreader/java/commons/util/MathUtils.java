package io.deepreader.java.commons.util;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * User: Danyang
 * Date: 1/14/2015
 * Time: 16:57
 */
public class MathUtils {
    public static boolean isInteger(double x) {
        return x%1==0;
    }

    public static boolean isInteger(float x) {
        return x%1==0;
    }

    public static boolean isPow2(int x) {
        return (x&(x-1))==0;
    }

    public static Stream<int[]> pythagoreanTriples(int upper) {
        return IntStream.range(1, upper).boxed()
                .flatMap(a -> IntStream.range(1, upper)  // not map; otherwise stream of streams
                        .filter(b -> isInteger(Math.sqrt(a*a+b*b)))
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a*a+b*b)})
                );
    }

    public static boolean isPrime(int n) {
        return IntStream.rangeClosed(2, (int) Math.sqrt(n))
                .noneMatch(i -> n%i==0);
    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n)
                .boxed() // need to box; otherwise java.lang.Object
                .collect(Collectors.partitioningBy(MathUtils::isPrime));
    }

    // Java 8 in Action
    private static <A> List<A> takeWhile(List<A> lst, Predicate<A> p) {
        int i = 0;
        for(A item: lst) {
            if(!p.test(item))
                return lst.subList(0, i);
            i++;
        }
        return lst;
    }

    private static boolean isPrime(List<Integer> primes, int n) {
        return takeWhile(primes, i -> i<= (int) Math.sqrt(n))
                .stream()
                .noneMatch(i -> n%i==0);
    }

    private class PrimeNumbersCollector implements Collector <
                Integer,
                Map<Boolean, List<Integer>>,
                Map<Boolean, List<Integer>>
            > {

        @Override
        public Supplier<Map<Boolean, List<Integer>>> supplier() {
            return () -> new HashMap<Boolean, List<Integer>>() {{  // otherwise <> cannot be used anonymous inner class
                put(true, new ArrayList<Integer>());
                put(false, new ArrayList<Integer>());
            }};
        }

        @Override
        public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
            return (acc, n) -> {
                acc.get(isPrime(acc.get(true), n)).add(n);  // no return
            };
        }

        @Override
        public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
            return (map1, map2) -> {
                map1.get(true).addAll(map2.get(true));
                map1.get(false).addAll(map2.get(false));
                return map1;
            };
        }

        @Override
        public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
            return Function.identity();
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
        }
    }

    public Map<Boolean, List<Integer>> partitionPrimesWithCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(new PrimeNumbersCollector());
    }

    public static int sum(List<Integer> lst) {
        return lst.stream().mapToInt(e->e).sum();
    }
}
