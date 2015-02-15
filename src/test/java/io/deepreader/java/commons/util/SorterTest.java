package io.deepreader.java.commons.util;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SorterTest extends TestCase {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSortByValues() throws Exception {

    }

    @Test
    public void testTop() throws Exception {

    }

    @Test
    public void testTop1() throws Exception {

    }

    @Test
    public void testTopEntries() throws Exception {
        Map<String, Integer> map = new HashMap<>();
        map.put("c", 1);
        map.put("b", 2);
        map.put("a", 3);
        TreeMap<String, Integer> ret = Sorter.sortByValue(map, new Sorter.ValueComparator<String, Integer>(map) {
            @Override
            public int compare(String a, String b) {
                try {
                    if (base.get(a) < base.get(b))
                        return 1;
                    else if (a.equals(b))
                        return 0;
                    else
                        return -1;
                } catch (NullPointerException e) {
                    return -1;
                }
            }
        });
        assertFalse(Displayer.display(Sorter.topEntries(ret, 2, (e1, e2) -> Integer.compare(e1.getValue(), e2.getValue()))).contains("1"));
    }
}