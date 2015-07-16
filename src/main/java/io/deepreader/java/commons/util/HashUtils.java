package io.deepreader.java.commons.util;

import java.util.Arrays;

/**
 * Created by Daniel on 17/07/15.
 */
public class HashUtils {
    /**
     * The java.lang.Array::hashCode is inherited from Object, which means the hashcode depends on the reference.
     * Normally the Array's hashCode() is not overridden, because hashCode needs to be fast to be useful (as it is mostly
     * used to prevent an expensive call of equals()), and even a shallow value hashCode on an array could potentially
     * be very slow.
     * @param t
     * @return
     */
    public static int hash(Object[] t) {
        return Arrays.hashCode(t);
    }

    public static int deepHash(Object[] t) {
        return Arrays.deepHashCode(t);
    }
}
