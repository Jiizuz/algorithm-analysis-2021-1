package com.github.jiizuz.algorithmanalysis.benchmark;

import com.google.common.base.Strings;
import com.google.common.math.DoubleMath;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.PrintStream;
import java.math.RoundingMode;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * {@link Benchmark} that will use the {@link System#out} stream
 * to report the current status of the {@link Benchmark} while
 * executing the warm-up and the tests.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.benchmark.Benchmark
 * @since 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
public class ConsoleBenchmark implements Benchmark {

    /**
     * {@link PrintStream} to report on the status of the <b>tests</b>.
     */
    private final PrintStream out = System.out;

    /**
     * Default times that the algorithms will be executed.
     *
     * <p>This value is used when a NoArgsConstructor is used
     */
    private static final int EXECUTIONS = 1000000000;

    /**
     * Amount of lines to display the progress bar.
     *
     * <p>This amount of lines will be displayed when the progress reaches 100%
     */
    private static final int PROGRESS_BAR_LENGTH = 10;

    /**
     * Value to use on the display of the progress bar.
     */
    private static final char PROGRESS_BAR_CHAR = '=';

    /**
     * Suffix to set on the progress bar after the last line.
     */
    private static final char PROGRESS_BAR_SUFFIX = '>';

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
     * {@link #executions} amount of times and reports on the {@link #out}
     * stream with a progress bar the current status.
     *
     * @param <I>       input of the function
     * @param <O>       output of the function
     * @param function  to test and stress with the processor
     * @param iSupplier to retrieve the input of the function
     */
    private <I, O> void warmUp(final @NonNull Function<I, O> function, final @NonNull Supplier<I> iSupplier) {
        out.printf( "Warming up function: %s%n", function.getClass().getSimpleName() );

        int progressBarLength = 0;
        double percentage;

        final int updateRate = Math.max( 1, DoubleMath.roundToInt( executions * 0.001456D, RoundingMode.HALF_UP ) );

        for ( int i = 0; i < executions; ++i )
        {
            progressBarLength = ( i + 1 ) / (executions / PROGRESS_BAR_LENGTH);
            if ( i % updateRate == 0 )
            {
                percentage = (double) i / executions * 100;
                out.printf( "\r[%-" + PROGRESS_BAR_LENGTH + "s] %2.2f%%",
                        Strings.repeat( String.valueOf( PROGRESS_BAR_CHAR ), progressBarLength ) + PROGRESS_BAR_SUFFIX,
                        percentage );
            }

            function.apply( iSupplier.get() );
        }

        out.printf( "\r[%-" + PROGRESS_BAR_LENGTH + "s] %2.2f%% (complete)%n",
                Strings.repeat( String.valueOf( PROGRESS_BAR_CHAR ), progressBarLength ), 100D );
    }

    /**
     * Calls the specified {@link Function} using the specified {@link Supplier}
     * to give as input for the function the desired {@link #executions} amount
     * of times and calculates the execution time of that call, the resultant
     * time is stored in a {@link TimeResults} and returned.
     *
     * <p>This method reports on the {@link #out} stream with a progress bar
     * the current status.
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
        out.printf( "Starting test for function: %s%n", function.getClass().getSimpleName() );

        try ( final TimeResults results = new ArrayTimeResults( executions ) ) {
            int progressBarLength = 0;
            double percentage;

            final int updateRate = Math.max( 1, DoubleMath.roundToInt( executions * 0.001456D, RoundingMode.HALF_UP ) );

            for ( int i = 0; i < executions; ++i )
            {
                progressBarLength = ( i + 1 ) / ( executions / PROGRESS_BAR_LENGTH );
                if ( i % updateRate == 0 )
                {
                    percentage = (double) i / executions * 100;
                    out.printf( "\r[%-" + PROGRESS_BAR_LENGTH + "s] %2.2f%%",
                            Strings.repeat( String.valueOf( PROGRESS_BAR_CHAR ), progressBarLength ) + PROGRESS_BAR_SUFFIX,
                            percentage );
                }

                final I input = iSupplier.get();
                final long start = System.nanoTime();
                function.apply( input ); // ignore output
                final long end = System.nanoTime();
                results.register( Math.toIntExact( end - start ) );
            }

            out.printf( "\r[%-" + PROGRESS_BAR_LENGTH + "s] %2.2f%% (complete)%n",
                    Strings.repeat( String.valueOf( PROGRESS_BAR_CHAR ), progressBarLength ), 100D );

            out.printf( "End test of function: %s%n", function.getClass().getSimpleName() );

            return results;
        }
    }
}
