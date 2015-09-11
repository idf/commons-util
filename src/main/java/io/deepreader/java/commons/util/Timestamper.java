package io.deepreader.java.commons.util;

import org.apache.commons.lang3.time.StopWatch;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
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

    /**
     * "Wall clock time" is the real-world elapsed time experienced by a user waiting for a task to complete.
     */
    public void start() {
        this.start = new Date().getTime();
    }

    public String end() {
        this.end = new Date().getTime();
        return ""+(this.end-this.start);
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }

    /**
     * Example of using apache timer
     * @return
     */
    private long stopWatch() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("Initialize stopwatch");
        stopWatch.split();
        return stopWatch.getSplitTime();
    }

    /**
     * Timing a single-threaded task using CPU, system, and user time
     *
     * "User time" is the time spent running your application's own code.
     * "System time" is the time spent running OS code on behalf of your application (such as for I/O).
     * "CPU time" is user time plus system time. It's the total time spent using a CPU for your application.
     */
    /** Get CPU time in nanoseconds. */
    public long getCpuTime() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported() ?
                bean.getCurrentThreadCpuTime() : 0L;
    }

    /** Get user time in nanoseconds. */
    public long getUserTime() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
        return bean.isCurrentThreadCpuTimeSupported() ?
                bean.getCurrentThreadUserTime() : 0L;
    }

    /** Get system time in nanoseconds. */
    public long getSystemTime() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
        return bean.isCurrentThreadCpuTimeSupported( ) ?
                (bean.getCurrentThreadCpuTime() - bean.getCurrentThreadUserTime( )) : 0L;
    }
}
