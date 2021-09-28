package com.github.jiizuz.algorithmanalysis.algorithm.fibonacci;

import it.unimi.dsi.fastutil.ints.Int2LongFunction;

/**
 * In mathematics, the <b>Fibonacci numbers</b>, commonly denoted
 * <tt>F<sub>n</sub></tt>, form a sequence, called the <b>Fibonacci sequence</b>,
 * such that each number is the sum of the two preceding ones, starting from 0
 * and 1. That is,
 * <pre>
 *     &#402;<sub>0</sub> &#61; 0, &#402;<sub>1</sub> &#61; 1
 * and
 *     &#402;<sub>n</sub> &#61; &#402;<sub>n-1</sub> + &#402;<sub>n-2</sub>
 * for <tt>n</tt> > 1
 * </pre>
 *
 * <p>The sequence starts:
 * <pre>
 *     0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, ...
 * </pre>
 *
 * <p>For example:
 * <pre>
 *     &#402;<sub>9</sub> &#61; &#402;<sub>8</sub> &#43; &#402;<sub>7</sub> &#61; 34
 * </pre>
 *
 * <p>This is a {@link Int2LongFunction}.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @implSpec This class is thread-safe
 * @see it.unimi.dsi.fastutil.ints.Int2LongFunction
 * @since 1.0
 */
public interface Fibonacci extends Int2LongFunction {

    /**
     * The Fibonacci function is defined by the sum of:
     * <pre>
     * &#402;<sub>n</sub> &#61; &#402;<sub>n-1</sub> + &#402;<sub>n-2</sub>
     * </pre>
     *
     * @param n amount of Fibonacci numbers to sum
     * @return the calculated Fibonacci number
     */
    @Override
    long get(int n);
}
