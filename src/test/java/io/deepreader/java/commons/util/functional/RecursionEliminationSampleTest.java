package io.deepreader.java.commons.util.functional;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Daniel on 26/10/15.
 */
public class RecursionEliminationSampleTest extends TestCase {
    RecursionEliminationSample s = new RecursionEliminationSample();
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testFactorRec() throws Exception {
        assertEquals(s.factorRec(5), 120);
    }

    @Test
    public void testFactor() throws Exception {
        assertEquals(s.factor(5), 120);
        assertEquals(s.factor(6), 720);
    }
}