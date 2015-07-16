package io.deepreader.java.commons.util;

/**
 * Created by Daniel on 14/07/15.
 */
public class CompareUtils {
    public static int normalizeCompareTo(double val) {
        double delta = 1e-6;
        if (Math.abs(val) < delta)
            return 0;
        else if (val < 0)
            return -1;
        else
            return 1;
    }
}
