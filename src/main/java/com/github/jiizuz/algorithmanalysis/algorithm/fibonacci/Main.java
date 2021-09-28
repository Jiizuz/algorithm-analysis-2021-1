package com.github.jiizuz.algorithmanalysis.algorithm.fibonacci;

import com.github.jiizuz.algorithmanalysis.algorithm.comparator.FibonacciComparator;
import com.github.jiizuz.algorithmanalysis.algorithm.comparator.chart.ChartFibonacciComparator;
import com.github.jiizuz.algorithmanalysis.algorithm.iterative.iteratives.IterativeFibonacci;
import com.github.jiizuz.algorithmanalysis.algorithm.recursive.recursives.RecursiveCacheFibonacci;
import com.github.jiizuz.algorithmanalysis.algorithm.recursive.recursives.RecursiveFibonacci;
import com.github.jiizuz.algorithmanalysis.benchmark.Benchmark;
import com.github.jiizuz.algorithmanalysis.benchmark.QuietBenchmark;
import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import lombok.experimental.UtilityClass;

/**
 * Main class of the project to initialize and run.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @since 1.0
 */
@UtilityClass
public class Main {

    /**
     * Executions to made in the {@link Benchmark}.
     */
    private final int BENCHMARK_EXECUTIONS = 10_000;

    /**
     * Tests to made in the {@link FibonacciComparator}.
     */
    private final int COMPARATOR_TESTS = 300;

    /**
     * Runs the main instance of the project.
     *
     * @param args passed in the command line
     */
    public void main(String[] args) {
        // algorithms to compare
        final ImmutableList<Fibonacci> algorithms = new ImmutableList.Builder<Fibonacci>()
                .add( new IterativeFibonacci() )
                .add( new RecursiveFibonacci() )
                .add( new RecursiveCacheFibonacci() )
                .build();

        // Benchmark to use on the tests
        final Benchmark benchmark = new QuietBenchmark( BENCHMARK_EXECUTIONS );

        // comparator to generate the comparisons
        final FibonacciComparator comparator = ChartFibonacciComparator.builder()
                .numberSpecifier( Int2IntFunction.identity() )
                .benchmark( benchmark )
                .tests( COMPARATOR_TESTS )
                .build();

        comparator.accumulate( algorithms );
        comparator.displayData();
    }
}
