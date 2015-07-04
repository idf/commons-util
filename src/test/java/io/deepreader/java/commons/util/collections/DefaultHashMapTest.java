package io.deepreader.java.commons.util.collections;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Daniel on 17/06/15.
 */
public class DefaultHashMapTest extends TestCase {
    @Test
    public void testGet() throws Exception {
        DefaultHashMap<Integer, Integer> d = new DefaultHashMap<>(0);
        d.put(1, d.get(1) + 1);
        assertTrue(d.get(1).equals(1));
    }
}
