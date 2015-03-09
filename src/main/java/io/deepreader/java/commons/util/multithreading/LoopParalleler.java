package io.deepreader.java.commons.util.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * User: Danyang
 * Date: 3/9/2015
 * Time: 12:46
 *
 * http://stackoverflow.com/questions/5686200/parallelizing-a-for-loop
 */
public class LoopParalleler<T, R> {
    public List<R> processInputs(List<T> inputs) throws InterruptedException, ExecutionException {
        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(threads);

        List<Future<R>> futures = new ArrayList<>();
        for (final T input: inputs) {
            Callable<R> callable = new Callable<R>() {
                public R call() throws Exception {
                    // R output = new R();
                    // process your T here and compute the R
                    return null;
                }
            };
            futures.add(service.submit(callable));
        }
        service.shutdown();

        List<R> outputs = new ArrayList<>();
        for (Future<R> future : futures) {
            outputs.add(future.get());
        }
        return outputs;
    }
}
