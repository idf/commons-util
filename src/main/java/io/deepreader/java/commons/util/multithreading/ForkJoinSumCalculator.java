package io.deepreader.java.commons.util.multithreading;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * User: Danyang
 * Date: 1/20/2015
 * Time: 16:01
 *
 * The boilerplate for fork-join framework
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {
    private final long[] nums;
    private final int s;
    private final int e;

    public static final long THRESHOLD = 1_000;

    private ForkJoinSumCalculator(long[] nums, int s, int e) {
        this.nums = nums;
        this.s = s;
        this.e = e;
    }

    public ForkJoinSumCalculator(long[] nums) {
        this(nums, 0, nums.length);
    }

    @Override
    protected Long compute() {
        int l = e - s;
        if (l <= THRESHOLD)
            return computeSequentially();

        ForkJoinSumCalculator left = new ForkJoinSumCalculator(nums, s, s + l / 2);
        ForkJoinSumCalculator right = new ForkJoinSumCalculator(nums, s + l / 2, e);
        left.fork();
        Long ret_right = right.compute();
        Long ret_left = left.join();
        return ret_left + ret_right;

    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = s; i < e; i++) {
            sum += nums[i];
        }
        return sum;
    }

    public static long example(long n) {
        long [] nums = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(nums);
        return new ForkJoinPool().invoke(task);
    }
}