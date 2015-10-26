package io.deepreader.java.commons.util.collections;

import io.deepreader.java.commons.util.collections.CollectionSample.MapSample;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Daniel on 27/09/15.
 */
public class MapSampleTest extends TestCase {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testInc1() throws Exception {
        Map<String, Integer> map = new HashMap<>();
        MapSample sample = new MapSample<Integer, Integer>();
        sample.inc1(map, "1");
        assertTrue(map.get("1") == 1);
        sample.inc1(map, "1");
        assertTrue(map.get("1") == 2);
    }

    @Test
    public void testInc() throws Exception {
        Map<String, Integer> map = new HashMap<>();
        MapSample sample = new MapSample<Integer, Integer>();
        sample.inc(map, "1");
        assertTrue(map.get("1") == 1);
        sample.inc(map, "1");
        assertTrue(map.get("1") == 2);
    }
}
