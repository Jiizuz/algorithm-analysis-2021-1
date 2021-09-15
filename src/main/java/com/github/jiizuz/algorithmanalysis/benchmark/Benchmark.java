package com.github.jiizuz.algorithmanalysis.benchmark;

import lombok.NonNull;

import java.util.function.Consumer;
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
     * @apiNote the cpu consumption of the {@link Supplier} is not considered in the tests
     */
    @NonNull <I, O>
    TimeResults test(@NonNull Function<I, O> function, @NonNull Supplier<I> iSupplier);

    /**
     * Tests the specified {@link Consumer} by calling it multiple amount of
     * times retrieving the input based on the specified {@link Supplier} by
     * calling it on each test.
     *
     * <p>Some implementations may warm up the processor before the test.
     *
     * @param <I>       input of the consumer
     * @param consumer  to test and stress with the processor
     * @param iSupplier to retrieve the input of the consumer
     * @return the {@link TimeResults} generated on the tests
     * @throws NullPointerException if either the consumer or supplier are <tt>null</tt>
     * @apiNote the cpu consumption of the {@link Supplier} is not considered in the tests
     */
    @NonNull <I>
    TimeResults test(@NonNull Consumer<I> consumer, @NonNull Supplier<I> iSupplier);

    /**
     * Tests the specified {@link Runnable} by calling it multiple amount of
     * times.
     *
     * <p>Some implementations may warm up the processor before the test.
     *
     * @param runnable to test and stress with the processor
     * @return the {@link TimeResults} generated on the tests
     * @throws NullPointerException if the runnable is <tt>null</tt>
     */
    @NonNull
    TimeResults test(@NonNull Runnable runnable);

    /**
     * Tests the specified {@link Supplier} by calling it multiple amount of
     * times. (The output of the supplier is ignored)
     *
     * <p>Some implementations may warm up the processor before the test.
     *
     * @param <O>      output of the supplier
     * @param supplier to test and stress with the processor
     * @return the {@link TimeResults} generated on the tests
     * @throws NullPointerException if the consumer supplier is <tt>null</tt>
     */
    @NonNull <O>
    TimeResults test(@NonNull Supplier<O> supplier);
}
