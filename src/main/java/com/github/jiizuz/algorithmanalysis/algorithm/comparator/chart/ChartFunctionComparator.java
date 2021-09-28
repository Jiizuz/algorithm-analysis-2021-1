package com.github.jiizuz.algorithmanalysis.algorithm.comparator.chart;

import com.github.jiizuz.algorithmanalysis.algorithm.comparator.FunctionComparator;
import com.github.jiizuz.algorithmanalysis.benchmark.Benchmark;
import com.github.jiizuz.algorithmanalysis.benchmark.TimeResults;
import it.unimi.dsi.fastutil.ints.Int2ObjectFunction;
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
import java.util.function.Function;

/**
 * {@link FunctionComparator} that uses a {@link JFreeChart} to
 * display a comparison in a XY Line Chart.
 *
 * <p>This implementation tests the functions based on a specified
 * amount of times on {@link #tests}.
 *
 * <p>A {@link ChartFrame} is used to pack and display the chart.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.algorithm.comparator.FunctionComparator
 * @since 1.0
 */
@Builder
@AllArgsConstructor
public class ChartFunctionComparator<I, O> implements FunctionComparator<I, O> {

    /**
     * Default title to set on the <tt>Chart</tt>.
     */
    private static final String CHART_TITLE = "CPU Time / n input";

    /**
     * Default label for the <tt>X axis</tt> on the chart.
     */
    private static final String X_AXIS_LABEL = "n input";

    /**
     * Default label for the <tt>Y axis</tt> on the chart.
     */
    private static final String Y_AXIS_LABEL = "CPU time (nanoseconds)";

    /**
     * Default title to set on the <tt>Frame</tt>.
     */
    private static final String FRAME_TITLE = "Function Comparator";

    /**
     * Default amount of tests to made if not specified.
     */
    private static final int DEFAULT_TESTS = 100;

    /**
     * {@link Int2ObjectFunction} to retrieve the input of the next function call.
     *
     * <p>The given input is the current text number.
     */
    @NonNull
    private final Int2ObjectFunction<I> inputSupplier;

    /**
     * {@link Function} to retrieve a clone input of the specified generated.
     *
     * <p>The given input is the input to clone.
     *
     * @implSpec The returned input must accomplish the next sentence:
     * <pre>
     *     input.equals(clone) == true && input != clone;
     * </pre>
     */
    @NonNull
    private final Function<I, I> cloneFunction;

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
     * {@link XYSeriesCollection} to accumulate on the series of the Function calls.
     */
    private final XYSeriesCollection seriesCollection = new XYSeriesCollection();

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends List<Function<I, O>> & RandomAccess> void accumulate(final @NonNull T functions) {
        final XYSeries[] xySeries = new XYSeries[ functions.size() ];

        for ( int i = 0, n = functions.size(); i < n; ++i )
        {
            final XYSeries series = new XYSeries( functions.get( i ).getClass().getSimpleName() );
            seriesCollection.addSeries( series );
            xySeries[i] = series;
        }

        for ( int i = 1; i <= tests; ++i )
        {
            final I input = inputSupplier.get( i );

            for ( int j = 0, n = functions.size(); j < n; ++j )
            {
                final TimeResults results = benchmark.test( functions.get( j ), () -> cloneFunction.apply( input ) );

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
