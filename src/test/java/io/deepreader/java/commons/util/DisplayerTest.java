package io.deepreader.java.commons.util;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;

public class DisplayerTest extends TestCase {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    public void testDisplay() throws Exception {
        class P0 {
            private Integer cnt;

            public Integer getCnt() {
                return cnt;
            }

            public void setCnt(Integer cnt) {
                this.cnt = cnt;
            }
        }
        P0 p = new P0();
        p.setCnt(7);
        assertTrue(Displayer.display(p, p.getCnt()).equals("io.deepreader.java.commons.util.DisplayerTest$1P0's cnt: 7"));
    }

    public void testUnicodeVariable() throws Exception {
        int å = 1;
        assertTrue(å == 1);
        int 中文 = 1;
        assertTrue (中文 == 1);
    }
}
