package io.deepreader.java.commons.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GeneratorTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testRun() throws Exception {
        Generator<Integer, Integer> simpleGenerator = new Generator<Integer, Integer>(100) {
            public void run() throws InterruptedException {
               for(int i=0; i<this.in; i++) {
                   yield(i);
               }
            }
        };

        for (Integer element: simpleGenerator) {
            System.out.println(element);
        }
    }
}