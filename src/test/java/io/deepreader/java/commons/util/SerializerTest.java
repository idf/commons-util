package io.deepreader.java.commons.util;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Daniel on 09/10/15.
 */
public class SerializerTest extends TestCase {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    // This test fails. 
    //Failed tests: testSerialize(io.deepreader.java.commons.util.SerializerTest):
    //expected:<[0[.00000,1.00000,2.]00000]> but was:<[0[,00000,1,00000,2,]00000]>
    public void testSerialize() throws Exception {
        Double[] arr = new Double[] {0.0, 1.0, 2.0};
        assertEquals("[0.00000,1.00000,2.00000]", Serializer.serialize(arr));
    }

    @Test
    public void testSerialize1() throws Exception {
        Integer[] arr = new Integer[] {0, 1, 2};
        assertEquals("[0,1,2]", Serializer.serialize(arr));
    }
}
