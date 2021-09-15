package com.github.jiizuz.algorithmanalysis.benchmark;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.io.PrintStream;
import java.util.IntSummaryStatistics;
import java.util.concurrent.TimeUnit;

/**
 * {@link UtilityClass} with utility method related with a {@link TimeResults}.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @since 1.0
 */
@UtilityClass
public class ResultsUtils {

    /**
     * Utility method to print in a fancy format the specified
     * {@link TimeResults} into the specified {@link PrintStream}.
     *
     * @param results to print in the stream
     * @param stream  to print on the results
     */
    public void printResults(final @NonNull TimeResults results, final @NonNull PrintStream stream) {
        final IntSummaryStatistics stats = results.getTimes().intStream().summaryStatistics();

        stream.printf("Results:"
                        + "\nExecuted %,d times"
                        + "\nTotal time: %,d ns (%,d ms), average %,d ns"
                        + "\nmin: %,d ns, max: %,d ns%n",
                stats.getCount(),
                stats.getSum(),
                TimeUnit.NANOSECONDS.toMillis(stats.getSum()),
                Math.round(stats.getAverage()),
                stats.getMin(),
                stats.getMax());
    }
}
