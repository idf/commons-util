package io.deepreader.java.commons.util;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Daniel on 09/10/15.
 */
public class Serializer {
    public static <X> String serialize(X[] array, Function<X, String> mapper) {
        return "["+Arrays.stream(array).map(mapper).collect(Collectors.joining(","))+"]";
    }

    public static String serialize(Integer[] array) {
        return serialize(array, x -> x.toString());
    }

    public static String serialize(Double[] array) {
        DecimalFormat df = new DecimalFormat("0.00000");
        return serialize(array, x -> df.format(x));
    }
}
