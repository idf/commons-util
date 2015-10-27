package io.deepreader.java.commons.util.collections;

import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by Daniel on 26/10/15.
 */
public class AdvancedCollectionSample {

    void treeSet() {
        TreeSet<Integer> s = new TreeSet<>();
        int ret  = 0;
        try { ret = s.floor(1); }
        catch (NullPointerException e) {}


        try{ ret = s.ceiling(1); }
        catch (NullPointerException e) {}
    }

    void treeMap() {
        TreeMap<Integer, Integer> m = new TreeMap<>();
        m.higherEntry(1);
        m.lowerEntry(1);
    }
}
