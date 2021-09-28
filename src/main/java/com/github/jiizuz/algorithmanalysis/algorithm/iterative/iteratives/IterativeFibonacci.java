package com.github.jiizuz.algorithmanalysis.algorithm.iterative.iteratives;

import com.github.jiizuz.algorithmanalysis.algorithm.fibonacci.Fibonacci;

/**
 * {@link Fibonacci} function that uses an Iterative method.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.algorithm.fibonacci.Fibonacci
 * @since 1.0
 */
public class IterativeFibonacci implements Fibonacci {

    /**
     * {@inheritDoc}
     *
     * <p>Time complexity - O(n)
     * <p>Space complexity - O(1)
     */
    @Override
    public long get(final int n) {
        if ( n <= 1 )
        {
            return n;
        }

        long fib = 1;
        long prevFib = 1;

        for ( int i = 2; i < n; ++i )
        {
            final long temp = fib;
            fib += prevFib;
            prevFib = temp;
        }

        return fib;
    }
}
