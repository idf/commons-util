package io.deepreader.java.commons.util.multithreading;

/**
 * User: Danyang
 * Date: 1/21/2015
 * Time: 19:40
 */

/**
 * Template
 *  Initialization-on-demand holder idiom
 */
public class SomeSingleton {
    private SomeSingleton() {}

    private static class Holder {
        private static final SomeSingleton INSTANCE = new SomeSingleton();
    }

    public static SomeSingleton getInstance() {
        return Holder.INSTANCE;
    }
}
