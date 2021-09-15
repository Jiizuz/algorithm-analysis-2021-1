package com.github.jiizuz.algorithmanalysis.algorithm.sort;

import lombok.NonNull;

/**
 * Algorithm to sort an array of elements.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @since 1.0
 */
public interface Sorter {

    /**
     * Sorts the specified {@param numbers} array of elements
     * and stores in the same reference the sorted values.
     *
     * @param numbers to sort
     * @return the sort results of this algorithm
     * @throws NullPointerException if the specified array is <tt>null</tt>
     */
    @NonNull
    SortResults sort(@NonNull long[] numbers);
}
