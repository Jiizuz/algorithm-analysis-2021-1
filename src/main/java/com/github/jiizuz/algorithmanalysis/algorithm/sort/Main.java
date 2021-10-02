package com.github.jiizuz.algorithmanalysis.algorithm.sort;

import com.github.jiizuz.algorithmanalysis.algorithm.array.ArrayGenerator;
import com.github.jiizuz.algorithmanalysis.algorithm.comparator.FunctionComparator;
import com.github.jiizuz.algorithmanalysis.algorithm.comparator.chart.ChartFunctionComparator;
import com.github.jiizuz.algorithmanalysis.algorithm.sort.sorters.*;
import com.github.jiizuz.algorithmanalysis.benchmark.Benchmark;
import com.github.jiizuz.algorithmanalysis.benchmark.QuietBenchmark;
import com.google.common.collect.ImmutableList;
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
    public void main( String[] args ) {
        // sorters to compare
        final ImmutableList<Function<long[], long[]>> sorters = new ImmutableList.Builder<Function<long[], long[]>>()
                .add( new BubbleSorter() )
                .add( new OptimizedBubbleSorter() )
                .add( new InsertionSorter() )
                .add( new QuickSorter() )
                .add( new MergeSorter() )
                .build();

        // Benchmark to use on the tests
        final Benchmark benchmark = QuietBenchmark.builder()
                .executions( BENCHMARK_EXECUTIONS )
                .build();

        // comparator to generate the comparisons
        final FunctionComparator<long[], long[]> comparator = ChartFunctionComparator.<long[], long[]>builder()
                .inputSupplier( i -> ArrayGenerator.generateAscending( i, 0L ) )
                .cloneFunction( long[]::clone )
                .benchmark( benchmark )
                .tests( COMPARATOR_TESTS )
                .chartTitle( "CPU Time / array length" )
                .xAxisLabel( "array length" )
                .frameTitle( "Sorters Comparator" )
                .build();

        comparator.accumulate( sorters );
        comparator.displayData();
    }
}
