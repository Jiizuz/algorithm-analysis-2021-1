package com.github.jiizuz.algorithmanalysis.algorithm.recursive.recursives;

import com.github.jiizuz.algorithmanalysis.algorithm.fibonacci.Fibonacci;

/**
 * {@link Fibonacci} function that uses a Recursive method.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.algorithm.fibonacci.Fibonacci
 * @since 1.0
 */
public class RecursiveFibonacci implements Fibonacci {

    /**
     * {@inheritDoc}
     *
     * <p>Time complexity - O(2<sup>n</sup>)
     * <p>Space complexity - O(1)
     */
    @Override
    public long get(final int n) {
        if ( n <= 1 )
        {
            return n;
        }
        return get( n - 1 ) + get( n - 2 );
    }
}
