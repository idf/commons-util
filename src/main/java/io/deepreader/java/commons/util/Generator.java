package io.deepreader.java.commons.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Date: 3/7/2015
 * Time: 15:07
 *
 * http://stackoverflow.com/questions/11570132/generator-functions-equivalent-in-java
 */
public abstract class Generator<T, R> implements Iterable<R> {
    protected T in;
    public Generator(T in) {
        this.in = in;
    }

    private class Condition {
        private boolean isSet;
        public synchronized void set() {
            isSet = true;
            notify();
        }
        public synchronized void await() throws InterruptedException {
            try {
                if (isSet)
                    return;
                wait();
            } finally {
                isSet = false;
            }
        }
    }

    static ThreadGroup THREAD_GROUP;

    Thread producer;
    private boolean hasFinished;
    private final Condition itemAvailableOrHasFinished = new Condition();
    private final Condition itemRequested = new Condition();
    private R nextItem;
    private boolean nextItemAvailable;

    @Override
    public Iterator<R> iterator() {
        return new Iterator<R>() {
            @Override
            public boolean hasNext() {
                waitForNext();
                return !hasFinished;
            }
            @Override
            public R next() {
                waitForNext();
                if (hasFinished)
                    throw new NoSuchElementException();
                nextItemAvailable = false;
                return nextItem;
            }
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
            private void waitForNext() {
                if (nextItemAvailable || hasFinished)
                    return;
                if (producer == null)
                    startProducer();
                itemRequested.set();
                try {
                    itemAvailableOrHasFinished.await();
                } catch (InterruptedException e) {
                    hasFinished = true;
                }
            }
        };
    }

    protected abstract void run() throws InterruptedException;

    protected void yield(R element) throws InterruptedException {
        nextItem = element;
        nextItemAvailable = true;
        itemAvailableOrHasFinished.set();
        itemRequested.await();
    }

    private void startProducer() {
        assert producer == null;
        if (THREAD_GROUP == null)
            THREAD_GROUP = new ThreadGroup("generators");
        producer = new Thread(THREAD_GROUP, () -> {
            try {
                itemRequested.await();
                Generator.this.run();
            } catch (InterruptedException e) {
                // No need to do anything here; Remaining steps in run()
                // will cleanly shut down the thread.
            }
            hasFinished = true;
            itemAvailableOrHasFinished.set();
        });
        producer.setDaemon(true);
        producer.start();
    }

    @Override
    protected void finalize() throws Throwable {
        producer.interrupt();
        producer.join();
        super.finalize();
    }
}