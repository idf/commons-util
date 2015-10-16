package io.deepreader.java.commons.util.general;

/**
 * Created by Daniel on 16/10/15.
 */
public class StaticMethodSample {
    void math() {
        Math.abs(-1);
        Math.max(1, 2);
        Math.min(1, 2);
        Math.random();  // [0, 1)
        System.out.println(Math.E);
        System.out.println(Math.PI);
    }

    void sys() {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Double.MAX_VALUE);
        System.out.println(Double.MIN_VALUE);
        System.out.println(Double.NaN);
    }

    void reverse() {

    }
}
