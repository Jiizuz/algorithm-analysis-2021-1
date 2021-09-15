package com.github.jiizuz.algorithmanalysis.benchmark;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * {@link Benchmark} that executes a warm-up and then the tests
 * on the functions quietly in order to be faster.
 *
 * <p>The disadvantage of this {@link Benchmark} is that the user
 * will never know the status of the tests until they are done or
 * an error occurs.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.benchmark.Benchmark
 * @since 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
public class QuietBenchmark implements Benchmark {

    /**
     * Default times that the algorithms will be executed.
     *
     * <p>This value is used when a NoArgsConstructor is used
     */
    private static final int EXECUTIONS = 1000000000;

    /**
     * Times that the algorithms will be executed.
     *
     * @see #EXECUTIONS
     */
    private int executions = EXECUTIONS;

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public <I, O> TimeResults test(final @NonNull Function<I, O> function, final @NonNull Supplier<I> iSupplier) {
        warmUp( function, iSupplier );

        return time( function, iSupplier );
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public <I> TimeResults test(final @NonNull Consumer<I> consumer, final @NonNull Supplier<I> iSupplier) {
        final Function<I, Void> function = i -> {
            consumer.accept( i );
            return null;
        };

        return test( function, iSupplier );
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public TimeResults test(final @NonNull Runnable consumer) {
        final Function<Void, Void> function = ignored -> {
            consumer.run();
            return null;
        };
        final Supplier<Void> iSupplier = () -> null;

        return test( function, iSupplier );
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public <O> TimeResults test(final @NonNull Supplier<O> supplier) {
        final Function<Void, O> function = ignored -> supplier.get();
        final Supplier<Void> iSupplier = () -> null;

        return test( function, iSupplier );
    }

    /**
     * Warms up the processor by executing the {@link Function} the desired
     * {@link #executions} amount of times.
     *
     * @param <I>       input of the function
     * @param <O>       output of the function
     * @param function  to test and stress with the processor
     * @param iSupplier to retrieve the input of the function
     */
    private <I, O> void warmUp(final @NonNull Function<I, O> function, final @NonNull Supplier<I> iSupplier) {
        for ( int i = 0; i < executions; ++i )
        {
            function.apply( iSupplier.get() );
        }
    }

    /**
     * Calls the specified {@link Function} using the specified {@link Supplier}
     * to give as input for the function the desired {@link #executions} amount
     * of times and calculates the execution time of that call, the resultant
     * time is stored in a {@link TimeResults} and returned.
     *
     * @param <I>       input of the function
     * @param <O>       output of the function
     * @param function  to test and time
     * @param iSupplier to retrieve the input of the function
     * @return the resultant {@link TimeResults} of the test
     * @see #executions
     */
    @NonNull
    private <I, O> TimeResults time(final @NonNull Function<I, O> function, final @NonNull Supplier<I> iSupplier) {
        try ( final TimeResults results = new ArrayTimeResults( executions ) ) {

            for ( int i = 0; i < executions; ++i )
            {
                final I input = iSupplier.get();
                final long start = System.nanoTime();
                function.apply( input ); // ignore output
                final long end = System.nanoTime();
                results.register( Math.toIntExact( end - start ) );
            }

            return results;
        }
    }
}
