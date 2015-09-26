package io.deepreader.java.commons.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Daniel on 25/09/15.
 */
public class StreamUtils {
    public static List<List<Integer>> TwoDimMatrix(int m, int n) {
        List<Integer> row = IntStream.range(0, n).boxed().collect(Collectors.toList());
        List<List<Integer>> mat = new ArrayList<>();
        IntStream.range(0, m).forEach(e ->
                mat.add(new ArrayList<>(row))
        );
        return mat;
    }
}
