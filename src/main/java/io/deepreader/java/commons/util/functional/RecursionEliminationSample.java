package io.deepreader.java.commons.util.functional;

import java.util.function.Function;

/**
 * Created by Daniel on 26/10/15.
 * Eliminate recursion by tail call using CPS (Continuation-passing style)
 * Eliminate recursive tail call by looping
 */
public class RecursionEliminationSample {
    int factorRec(int n) {
        if (n == 0)
            return 1;
        else
            return n * factorRec(n-1);
    }

    int factor(int n) {
        Function<Integer, Integer> k = (x) -> x;
        while(true) {
            if (n == 0)
                return k.apply(1);
            else {
                final Function<Integer, Integer> k0 = k;
                final int n0 = n;

                k = (x) -> k0.apply(n0 * x); // closure chain
                n -= 1;
            }
        }
    }
}
