package io.deepreader.java.commons.util;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * Created by Daniel on 10/10/15.
 */
public class Boxer {
    public static Integer[] boxInts(int[] a) {
        return IntStream.of(a).boxed().toArray(Integer[]::new);
    }

    public static Double[] boxDoubles(double[] a) {
        return DoubleStream.of(a).boxed().toArray(Double[]::new);
    }

    public static Byte[] boxBytes(byte[] a) {
        Byte[] boxed = new Byte[a.length];
        Arrays.setAll(boxed, i -> a[i]);
        return boxed;
    }
}
