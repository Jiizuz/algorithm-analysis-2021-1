package com.github.jiizuz.algorithmanalysis.algorithm.sort;

import lombok.NonNull;

import java.util.function.Function;

/**
 * {@link Function} to signal that it will receive an array of
 * numbers and will sort them in ascending order and then return
 * the same array object reference.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see java.util.function.Function
 * @since 1.0
 */
public interface Sorter extends Function<long[], long[]> {

    /**
     * Sorts the specified {@param numbers} array of elements
     * and stores in the same reference the sorted values.
     *
     * @param numbers to sort
     * @return the sort results of this algorithm
     * @throws NullPointerException if the specified array is <tt>null</tt>
     */
    @NonNull
    SortResults sort(long @NonNull [] numbers);
}
