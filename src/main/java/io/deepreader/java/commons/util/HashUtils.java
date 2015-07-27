package io.deepreader.java.commons.util;

import java.util.Arrays;
import java.util.Date;

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

    class HashExample {
        private String who;
        private Date when;
        private double amount;

        public boolean equals(Object y) {
            if (this == y)
                return true;

            if (y instanceof HashExample) {
                HashExample that = (HashExample) y;
                if (this.who.equals(that.who) &&
                        this.when.equals(that.when) &&
                        this.amount == that.amount)
                    return true;
            }
            return false;
        }

        /**
         * Standard recipe for user-defined types:
         * 1. Combine each significant field using 31x + y rule;
         * 2. If field id a primitive type, use wrapper type hashCode().
         * 3. If field is null, return 0
         * 4. If field is a reference type, use hashCode() to applies rules recursively
         * 5. If field is an array, apply to each entry. (Arrays.deepHashCode)
         * @return
         */
        public int hashCode() {
            int hash = 17;
            hash = 31*hash + who.hashCode();
            hash = 31*hash + when.hashCode();
            hash = 31*hash + ((Double) amount).hashCode();
            return hash;
        }
    }
}
