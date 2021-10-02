package com.github.jiizuz.algorithmanalysis.algorithm.fibonacci;

import com.github.jiizuz.algorithmanalysis.algorithm.comparator.FunctionComparator;
import com.github.jiizuz.algorithmanalysis.algorithm.comparator.chart.ChartFunctionComparator;
import com.github.jiizuz.algorithmanalysis.algorithm.iterative.iteratives.IterativeFibonacci;
import com.github.jiizuz.algorithmanalysis.algorithm.recursive.recursives.RecursiveCacheFibonacci;
import com.github.jiizuz.algorithmanalysis.algorithm.recursive.recursives.RecursiveFibonacci;
import com.github.jiizuz.algorithmanalysis.benchmark.Benchmark;
import com.github.jiizuz.algorithmanalysis.benchmark.QuietBenchmark;
import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.ints.IntUnaryOperator;
import lombok.experimental.UtilityClass;

import java.util.function.Function;

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
     * Tests to made in the {@link FunctionComparator}.
     */
    private final int COMPARATOR_TESTS = 300;

    /**
     * Runs the main instance of the project.
     *
     * @param args passed in the command line
     */
    public void main(String[] args) {
        // algorithms to compare
        final ImmutableList<Function<Integer, Long>> algorithms = new ImmutableList.Builder<Function<Integer, Long>>()
                .add( new IterativeFibonacci() )
                .add( new RecursiveFibonacci() )
                .add( new RecursiveCacheFibonacci() )
                .build();

        // Benchmark to use on the tests
        final Benchmark benchmark = new QuietBenchmark( BENCHMARK_EXECUTIONS );

        // comparator to generate the comparisons
        final FunctionComparator<Integer, Long> comparator = ChartFunctionComparator.<Integer, Long>builder()
                .inputSupplier( i -> i )
                .cloneFunction( IntUnaryOperator.identity() )
                .benchmark( benchmark )
                .tests( COMPARATOR_TESTS )
                .chartTitle( "CPU Time / Fibonacci number" )
                .xAxisLabel( "Fibonacci number" )
                .frameTitle( "Fibonacci Comparator" )
                .build();

        comparator.accumulate( algorithms );
        comparator.displayData();
    }
}
