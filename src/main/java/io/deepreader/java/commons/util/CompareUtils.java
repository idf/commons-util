package io.deepreader.java.commons.util;

/**
 * Created by Daniel on 14/07/15.
 */
public class CompareUtils {
    /**
     * However, need special handling of Double.POSITIVE_INFINITY.
     * Normally don't use this method
     * @param val
     * @return
     */
    @Deprecated
    private static int normalizeCompareTo(double val) {
        double delta = 1e-6;
        if (Math.abs(val) < delta)
            return 0;
        else if (val < 0)
            return -1;
        else
            return 1;
    }

    public static int compareTo(double i, double j) {
        i += 0.0;
        j += 0.0; // avoid negative zero
        return Double.compare(i, j);
    }

    public static int mathCompareTo(double i, double j) {
        if (i < j) return -1;
        if (i == j) return 0;
        return 1;
    }
}
