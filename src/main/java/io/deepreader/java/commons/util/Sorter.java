package io.deepreader.java.commons.util;

import java.util.*;

/**
 * by LinkedList: http://www.leveluplunch.com/java/examples/sort-order-map-by-values/
 * * User: Danyang
 * Date: 12/31/2014
 * Time: 14:08
 */
public class Sorter {
    public static abstract class ValueComparator<K, V> implements Comparator<K> {
        protected Map<K, V> base;

        public ValueComparator(Map<K, V> base) {
            this.base = base;
        }

        /**
         * return 0 would merge keys.
         * return 0 when keys are equal; otherwise never returns 0, indicating equality, causing the Map.get() method
         * to not find matches.
         *
         * Catch the null pointer exception when calling .containsKey(K)
         * @param a
         * @param b
         * @return
         */
        @Override
        public int compare(K a, K b) {
            try {
                if ((Integer) base.get(a)<(Integer) base.get(b))
                    return 1;
                else if(a.equals(b))
                    return 0 ;
                else
                    return -1;
            }
            catch (NullPointerException e) {
                return -1;
            }
        }
    }

    /**
     * 
     * @param map
     * @param vc the ValueComparator implemented, you need to implement the compare() method
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> TreeMap<K, V> sortByValue(Map<K, V> map, ValueComparator<K, V> vc) {
        TreeMap<K, V> sortedMap = new TreeMap<>(vc);
        sortedMap.putAll(map);
        return sortedMap;
    }

    /**
     * LinkedHashMap implementation
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V extends Comparable<V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> lst = new LinkedList<>(map.entrySet());
        Collections.sort(lst, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));
        Map<K, V> ret = new LinkedHashMap<>();
        for(Map.Entry<K, V> e: lst) ret.put(e.getKey(), e.getValue());
        return ret;
    }

    public static <K extends Comparable<K>, V> Map<K, V> sortByKey(Map<K, V> map) {
        List<Map.Entry<K, V>> lst = new LinkedList<>(map.entrySet());
        Collections.sort(lst, (o1, o2) -> o1.getKey().compareTo(o2.getKey()));
        Map<K, V> ret = new LinkedHashMap<>();
        for(Map.Entry<K, V> e: lst) ret.put(e.getKey(), e.getValue());
        return ret;
    }

    public static <K, V> TreeMap<K, V> top(TreeMap<K, V> map, int k) {
        return top(map, k, map.comparator());
    }

    public static <K, V> TreeMap<K, V> top(TreeMap<K, V> map, int k, Comparator<? super K> cmp) {
        TreeMap<K, V> ret = new TreeMap<>(map.comparator());
        int i = 0;
        Map.Entry<K, V> kth = map.entrySet().iterator().next();
        for(Map.Entry<K, V> e: map.entrySet()) {
            i ++;
            if(i==k)
                kth = e;
            else if(i>k) {
                if(cmp.compare(kth.getKey(), e.getKey())!=0)
                    break;
            }
            ret.put(e.getKey(), e.getValue());
        }
        return ret;
    }

    public static <T extends TreeMap<K, V>, K, V> T topEntries(T map, int k, Comparator<Map.Entry<K, V>> cmp) {
        T ret = (T) map.clone();
        ret.clear();

        int i = 0;
        Map.Entry<K, V> kthEntry = null;
        for(Map.Entry<K, V> e: map.entrySet()) {
            i++;
            if(i==k)
                kthEntry = e;
            else if(i>k) {
                if(cmp.compare(kthEntry, e)!=0)
                    break;
            }
            ret.put(e.getKey(), e.getValue());
        }
        return ret;
    }

    public static <T extends TreeMap<K, V>, K, V> T topEntries(T map, int k) {
        T ret = (T) map.clone();
        ret.clear();

        int i = 0;
        for(Map.Entry<K, V> e: map.entrySet()) {
            i++;
            if(i>k) {
                break;
            }
            ret.put(e.getKey(), e.getValue());
        }
        return ret;
    }

    /**
     * Template
     */
    private static <T extends Comparable> List<T> sort(List<T> lst) {
        lst.sort(Comparable::compareTo);
        return lst;
    }

    private static <T extends Comparable> List<T> sort2(List<T> lst) {
        lst.sort(Comparator.comparing(Object::toString));
        return lst;
    }

}
