package io.deepreader.java.commons.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Daniel on 10/09/15.
 */
public class TimestamperTest {
    Timestamper stamper = new Timestamper();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetCpuTime() throws Exception {
        for(int j = 0; j < 10; j++) {
            long ctime = this.stamper.getCpuTime();
            for(int i = 0; i < 1e9; i++)
                ;
            System.out.println(this.stamper.getCpuTime()-ctime);
        }
    }
}