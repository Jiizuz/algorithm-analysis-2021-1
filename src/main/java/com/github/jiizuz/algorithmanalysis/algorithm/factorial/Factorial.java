package com.github.jiizuz.algorithmanalysis.algorithm.factorial;

import it.unimi.dsi.fastutil.ints.Int2LongFunction;

/**
 * Represents a way to calculate a factorial result.
 *
 * <p>In mathematics, the <b>factorial</b> of a non-negative integer
 * <tt>n</tt>, denoted by <tt>n!</tt>, is the product of all positive
 * integers less than or equal to <tt>n</tt>:
 * <pre>
 *     n! = n &#183; (n - 1) &#183; (n - 2) &#183; (n - 3) &#183; ... &#183; 3 &#183; 2 &#183; 1
 * </pre>
 *
 * <p>For example:
 * <pre>
 *     5! = 5 &#183; 4 &#183; 3 &#183; 2 &#183; 1 = 120
 * </pre>
 *
 * <p>This is a {@link Int2LongFunction}.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @implSpec this class is thread-safe
 * @see it.unimi.dsi.fastutil.ints.Int2LongFunction
 * @since 1.0
 */
@FunctionalInterface
public interface Factorial extends Int2LongFunction {

    /**
     * The factorial function is defined by the product:
     * <pre>
     * n! = 1 &#183; 2 &#183; 3 &#183; ... &#183; (n - 2) &#183; (n - 1) &#183; n
     * </pre>
     *
     * @param n to get the factorial
     * @return the calculated factorial
     */
    @Override
    long get(int n);
}
