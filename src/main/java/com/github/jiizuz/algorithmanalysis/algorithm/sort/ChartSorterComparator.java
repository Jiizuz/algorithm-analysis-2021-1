package com.github.jiizuz.algorithmanalysis.algorithm.sort;

import com.github.jiizuz.algorithmanalysis.benchmark.Benchmark;
import com.github.jiizuz.algorithmanalysis.benchmark.TimeResults;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.List;
import java.util.RandomAccess;
import java.util.function.IntFunction;

/**
 * {@link SorterComparator} that uses a {@link JFreeChart} to
 * display a comparison in a XY Line Chart.
 *
 * <p>This implementation tests the sorters based on a specified
 * amount of times on {@link #tests}.
 *
 * <p>The used array to send to the sorters is generated with the
 * specified {@link #arrGenerator} sending it the number of the
 * current tests. The same array is sent to all the sorters in
 * order to make a valid tests with the same data.
 *
 * <p>A {@link ChartFrame} is used to pack and display the chart.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.algorithm.sort.SorterComparator
 * @since 1.0
 */
@Builder
@AllArgsConstructor
public class ChartSorterComparator implements SorterComparator {

    /**
     * Default title to set on the <tt>Chart</tt>.
     */
    private static final String CHART_TITLE = "CPU Time / array length";

    /**
     * Default label for the <tt>X axis</tt> on the chart.
     */
    private static final String X_AXIS_LABEL = "array length";

    /**
     * Default label for the <tt>Y axis</tt> on the chart.
     */
    private static final String Y_AXIS_LABEL = "CPU time (nanoseconds)";

    /**
     * Default title to set on the <tt>Frame</tt>.
     */
    private static final String FRAME_TITLE = "Sorters Comparator";

    /**
     * Default amount of tests to made if not specified.
     */
    private static final int DEFAULT_TESTS = 100;

    /**
     * {@link IntFunction} to generate arrays to sort.
     *
     * <p>The given input is the number of the test.
     */
    @NonNull
    private final IntFunction<long[]> arrGenerator;

    /**
     * {@link Benchmark} to use on the accumulation.
     */
    @NonNull
    private final Benchmark benchmark;

    /**
     * Maximum amount of tests to make per accumulation.
     */
    @Builder.Default
    private final int tests = DEFAULT_TESTS;

    /**
     * Title to set on the <tt>Chart</tt>.
     */
    @Builder.Default
    private final String chartTitle = CHART_TITLE;

    /**
     * Label for the <tt>X axis</tt> on the chart.
     */
    @Builder.Default
    private final String xAxisLabel = X_AXIS_LABEL;

    /**
     * Label for the <tt>Y axis</tt> on the chart.
     */
    @Builder.Default
    private final String yAxisLabel = Y_AXIS_LABEL;

    /**
     * Title to set on the <tt>Frame</tt>.
     */
    @Builder.Default
    private final String frameTitle = FRAME_TITLE;

    /**
     * {@link XYSeriesCollection} to accumulate on the series of the sorters.
     */
    private final XYSeriesCollection seriesCollection = new XYSeriesCollection();

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends List<Sorter> & RandomAccess> void accumulate(final @NonNull T sorters) {
        final XYSeries[] xySeries = new XYSeries[ sorters.size() ];

        for ( int i = 0, n = sorters.size(); i < n; ++i )
        {
            final XYSeries series = new XYSeries( sorters.get( i ).getClass().getSimpleName() );
            seriesCollection.addSeries( series );
            xySeries[i] = series;
        }

        for ( int i = 1; i <= tests; ++i )
        {
            final long[] toSort = arrGenerator.apply( i );

            for ( int j = 0, n = sorters.size(); j < n; ++j ) {
                final TimeResults results = benchmark.test( sorters.get( j ), toSort::clone );

                xySeries[j].add( i, results.getTimes().intStream().average().orElse( 0D ) );

                results.dump();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayData() {
        final JFreeChart chart = ChartFactory.createXYLineChart( chartTitle, xAxisLabel, yAxisLabel, seriesCollection );
        final ChartFrame frame = new ChartFrame( frameTitle, chart );
        frame.pack();
        frame.setVisible( true );
    }
}
