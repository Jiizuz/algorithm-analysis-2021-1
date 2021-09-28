package com.github.jiizuz.algorithmanalysis.algorithm.iterative.iteratives;

import com.github.jiizuz.algorithmanalysis.algorithm.sum.Sum;

/**
 * {@link Sum} with iterative method. I do not know. Do not ask.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.algorithm.sum.Sum
 * @since 1.0
 */
public class IterativeSum implements Sum {

    /**
     * {@inheritDoc}
     *
     * <p>Time complexity - O(a+b)
     * <p>Space complexity - O(1)
     */
    @Override
    public long applyAsLong(final Integer a, final Integer b) {
        long result = 0;

        for ( int i = 0; i < a; ++i )
        {
            result++; // i know, i know...
        }

        for ( int i = 0; i < b; ++i )
        {
            result++;
        }

        return result;
    }
}
