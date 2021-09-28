package com.github.jiizuz.algorithmanalysis.algorithm.sort;

import com.github.jiizuz.algorithmanalysis.algorithm.array.ArrayGenerator;
import com.github.jiizuz.algorithmanalysis.algorithm.comparator.chart.ChartSorterComparator;
import com.github.jiizuz.algorithmanalysis.algorithm.sort.sorters.*;
import com.github.jiizuz.algorithmanalysis.benchmark.Benchmark;
import com.github.jiizuz.algorithmanalysis.benchmark.QuietBenchmark;
import com.google.common.collect.ImmutableList;
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
     * Tests to made in the {@link SorterComparator}.
     */
    private final int COMPARATOR_TESTS = 300;

    /**
     * Runs the main instance of the project.
     *
     * @param args passed in the command line
     */
    public void main( String[] args ) {
        // sorters to compare
        final ImmutableList<Sorter> sorters = new ImmutableList.Builder<Sorter>()
                .add( new BubbleSorter() )
                .add( new OptimizedBubbleSorter() )
                .add( new InsertionSorter() )
                .add( new QuickSorter() )
                .add( new MergeSorter() )
                .build();

        // Benchmark to use on the tests
        final Benchmark benchmark = new QuietBenchmark( BENCHMARK_EXECUTIONS );

        // comparator to generate the comparisons
        final SorterComparator comparator = ChartSorterComparator.builder()
                .arrGenerator(i -> ArrayGenerator.generateAscending( i, 0L ))
                .benchmark( benchmark )
                .tests( COMPARATOR_TESTS )
                .build();

        comparator.accumulate( sorters );
        comparator.displayData();
    }
}
