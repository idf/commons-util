package io.deepreader.java.commons.util.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * Created by Daniel on 15/10/15.
 */
public class CollectionSample {
    /**
     * Sample Usage of List
     */
    class ListSample {
        void listOperations() {
            List<Integer> lst = new ArrayList<>();
            // element-wise
            lst.add(0, 123);
            lst.get(0);
            lst.set(0, 124);
            lst.indexOf(0);
            lst.remove(0);

            // list-wise
            lst.size();
            lst.subList(0, 1);
            lst.contains(123);
            lst.isEmpty();
            lst.sort((o1, o2) -> o1-o2);  // bracket the params

            // iteration
            for(Integer i: lst) {
                System.out.println(i);
            }
        }

        Integer[] convert(List<Integer> lst) {
            return lst.toArray(new Integer[0]);  // sample type
        }

        List<Integer> convert(Integer[] array) {
            return Arrays.asList(array);
        }

        List<Integer> construct() {
            return Arrays.asList(1, 2, 3);
        }

        /**
         * All sorting are in-place
         */
        void sorted(List<Integer> lst) {
            lst.sort((o1, o2) -> o1-o2);
            Collections.sort(lst);
        }

        void reverse(List<Integer> lst) {
            Collections.reverse(lst);
        }

        void stream(List<Integer> lst) {
            lst.stream().forEach(e -> System.out.println(e));
            List<Integer> ret = lst.stream().map(e -> e+1).collect(Collectors.toList());
        }
    }

    /**
     * Sample Usage of Map
     * @param <K>
     * @param <V>
     */
    static class MapSample<K, V> {
        void mapOperations() {
            Map<String, Integer> m = new HashMap<>();
            // element-wise
            m.put("a", 1);
            m.get("a");  // null possible
            m.getOrDefault("b", 0);
            m.remove("c");

            // m-wise
            m.size();
            m.containsKey("a");
            m.containsValue(1);
            m.size();

            // iteration
            for(Map.Entry<String, Integer> e: m.entrySet()) {
                System.out.println(e.getKey());
                System.out.println(e.getValue());
            }
        }

        void inc(Map<K, Integer> m, K k) {
            m.put(k, m.getOrDefault(k, 0)+1);
        }

        void inc1(Map<K, Integer> m, K key) {
            m.compute(key, (k, v) -> v == null ? 1 : v+1);
        }

        void stream(Map<K, Integer> m) {
            m.forEach((k, v) -> System.out.println(k+": "+v));
            m.entrySet().stream().map(e -> e.setValue(e.getValue()+1));
        }
    }

    /**
     * Sample Usage of String
     */
    class StringSample {
        void stringOperations() {
            String s = "abcdefg";
            // element-wise
            s.charAt(0);
            s.indexOf("a", 0);

            // string-wise
            s.contains("a");
            s.toLowerCase();
            s.toUpperCase();
            s.substring(0, 1);
            s.endsWith("fg");
            s.startsWith("ab");
            s.length();

            s.split(" ");
            s.split("\\w");
        }

        /**
         * To process the string, toCharArray enables [] and simpler comparsion
         * @param s
         * @return
         */
        char[] convert(String s) {
            return s.toCharArray();
        }

        String convert(char[] array) {
            return String.valueOf(array);
        }

        void stream(String s) {
            s.chars().forEach(e -> System.out.println((char) e));
            s.chars().mapToObj(e -> String.valueOf((char) e)).collect(Collectors.joining());
        }
    }

    /**
     * Sample Usage of TwoDim Array/List
     */
    class TwoDimArray {
        void TwoDimListOperations() {
            List<List<Character>> mat = new ArrayList<>();
            mat.add(Arrays.asList(new Character[]{'a', 'b'}));
            mat.add(Arrays.asList('a', 'b'));
        }

        char[][] construct() {
            return new char[][] {
                    {'1', '1', '0', '1'},
                    {'1', '1', '0', '1'},
                    {'1', '1', '1', '1'},
            };
        }
    }


    /**
     * Sample Usage of PriorityQueue
     */
    class PriorityQueueSample {
        /**
         * PriorityQueue is ADT; heap is an implementation of PriorityQueue.
         */
        void operations() {
            PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2-o1); // max-heap
            pq.add(1);  // same as offer
            pq.offer(1);
            pq.peek();
            pq.poll();
        }
    }
}
