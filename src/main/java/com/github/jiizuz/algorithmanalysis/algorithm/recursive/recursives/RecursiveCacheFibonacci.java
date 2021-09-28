package com.github.jiizuz.algorithmanalysis.algorithm.recursive.recursives;

import com.github.jiizuz.algorithmanalysis.algorithm.fibonacci.Fibonacci;

import java.util.Objects;

/**
 * {@link Fibonacci} function that uses a Recursive method and a
 * memoization to be less complex.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.algorithm.fibonacci.Fibonacci
 * @since 1.0
 */
public class RecursiveCacheFibonacci implements Fibonacci {

    /**
     * {@inheritDoc}
     *
     * <p>Time complexity - O(n)
     * <p>Space complexity - O(n)
     */
    @Override
    public long get(final int n) {
        final Long[] cache = new Long[ n ];

        return fibonacci( n, cache );
    }

    /**
     * Calculates the Fibonacci result of <tt>n</tt>.
     *
     * @param n     target to calculate
     * @param cache memory to retrieve calculated values
     * @return the Fibonacci number of <tt>n</tt>
     */
    private long fibonacci(final int n, final Long[] cache) {
        final long result;

        if ( Objects.nonNull( cache[ n - 1 ] ) )
        {
            return cache[ n - 1 ];
        } else
        {
            if ( n <= 2 )
            {
                result = 1;
            } else
            {
                result = fibonacci( n - 1, cache ) + fibonacci( n - 2, cache );
            }

            cache[ n - 1 ] = result;
        }

        return result;
    }
}
