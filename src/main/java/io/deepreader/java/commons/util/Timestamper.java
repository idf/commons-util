package io.deepreader.java.commons.util;

import java.sql.Timestamp;
import java.util.Date;

/**
 * User: Danyang
 * Date: 11/21/14
 * Time: 2:13 PM
 */
public class Timestamper {
    private long start;
    private long end;

    public void loudStart() {
        this.start();
        System.out.println("Timestamper started: "+new Timestamp(this.start));
    }

    public void loudEnd() {
        String elapse = this.end();
        System.out.println("Timestamper completed: "+new Timestamp(this.end));
        System.out.println("Elapsed: "+elapse+"ms");
    }

    public void start() {
        this.start = new Date().getTime();
    }

    public String end() {
        this.end = new Date().getTime();
        return ""+(this.end-this.start);
    }
}
