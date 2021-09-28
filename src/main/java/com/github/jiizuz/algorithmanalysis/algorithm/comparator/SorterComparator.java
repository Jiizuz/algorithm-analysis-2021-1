package com.github.jiizuz.algorithmanalysis.algorithm.comparator;

import com.github.jiizuz.algorithmanalysis.algorithm.sort.Sorter;
import com.github.jiizuz.algorithmanalysis.benchmark.Benchmark;
import lombok.NonNull;

import java.util.List;
import java.util.RandomAccess;

/**
 * Manages the accumulation of data in order to allow a future
 * visual display of the accumulated data.
 *
 * <p>The data representation is implementation dependant, but
 * is a requirement to allow the user to see an easy comparison
 * of the accumulated data.
 *
 * <p>This class is designed to work with a {@link Benchmark}
 * in order to compare a {@link List} of {@link Sorter}s.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @implSpec this class is not <tt>thread-safe</tt>
 * @implNote the {@link Benchmark} may be asked on construction
 * @apiNote this class is not a {@link java.util.Comparator}
 * @since 1.0
 */
public interface SorterComparator {

    /**
     * Sends every {@link Sorter} to the desired {@link Benchmark}
     * and accumulates the results of the tests into this comparator
     * to be used on the displaying of the results.
     *
     * @param sorters to accumulate on this comparator
     * @param <T>     {@link List} that is a {@link RandomAccess} with the sorters
     * @throws NullPointerException if the list is <tt>null</tt>
     * @implSpec the {@link List} is a read-only list
     */
    <T extends List<Sorter> & RandomAccess>
    void accumulate(@NonNull T sorters);

    /**
     * Computes the collected data until now and displays a
     * comparison for the user as soon as possible.
     */
    void displayData();
}
