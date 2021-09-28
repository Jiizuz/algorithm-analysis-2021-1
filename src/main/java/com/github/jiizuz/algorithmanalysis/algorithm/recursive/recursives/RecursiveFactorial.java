package com.github.jiizuz.algorithmanalysis.algorithm.recursive.recursives;

import com.github.jiizuz.algorithmanalysis.algorithm.factorial.Factorial;

/**
 * {@link Factorial} function that uses a recursive function.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.algorithm.factorial.Factorial
 * @since 1.0
 */
public class RecursiveFactorial implements Factorial {

    /**
     * {@inheritDoc}
     */
    @Override
    public long get(final int n) {
        if ( n == 0 )
        {
            return 1L;
        }

        return n * get( n - 1 );
    }
}
