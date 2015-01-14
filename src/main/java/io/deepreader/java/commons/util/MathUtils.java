package io.deepreader.java.commons.util;

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
}
