package io.deepreader.java.commons.util.collections;

import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by Daniel on 26/10/15.
 */
public class AdvancedCollectionSample {

    void treeSet() {
        TreeSet<Integer> s = new TreeSet<>();
        s.floor(1);
        s.ceiling(1);
    }

    void treeMap() {
        TreeMap<Integer, Integer> m = new TreeMap<>();
        m.higherEntry(1);
        m.lowerEntry(1);
    }
}
