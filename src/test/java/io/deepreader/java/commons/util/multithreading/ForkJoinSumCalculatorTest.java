package io.deepreader.java.commons.util.multithreading;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ForkJoinSumCalculatorTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testExample() throws Exception {
        assert ForkJoinSumCalculator.example(1_000_000)==500_000_500_000L;
    }
}