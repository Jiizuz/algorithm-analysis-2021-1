package com.github.jiizuz.algorithmanalysis.benchmark;

import lombok.NonNull;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Manages internally the operations to test and stress the processor
 * to measure the execution time of {@link Function}s provided.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @implSpec this class is <tt>thread-safe</tt>
 * @since 1.0
 */
public interface Benchmark {

    /**
     * Tests the specified {@link Function} by calling it multiple amount of
     * times retrieving the input based on the specified {@link Supplier} by
     * calling it on each test.
     *
     * <p>Some implementations may warm up the processor before the test.
     *
     * @param <I>       input of the function
     * @param <O>       output of the function
     * @param function  to test and stress with the processor
     * @param iSupplier to retrieve the input of the function
     * @return the {@link TimeResults} generated on the tests
     * @throws NullPointerException if either the function or supplier are <tt>null</tt>
     */
    @NonNull <I, O>
    TimeResults test(@NonNull Function<I, O> function, @NonNull Supplier<I> iSupplier);
}
