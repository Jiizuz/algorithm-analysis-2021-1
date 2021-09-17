package com.github.jiizuz.algorithmanalysis.algorithm.sort.sorters;

import com.github.jiizuz.algorithmanalysis.algorithm.sort.ComputableSortResults;
import com.github.jiizuz.algorithmanalysis.algorithm.sort.SortResults;
import com.github.jiizuz.algorithmanalysis.algorithm.sort.Sorter;
import lombok.NonNull;

import java.time.Duration;

/**
 * Like Merge Sort, QuickSort is a Divide and Conquer algorithm.
 * It picks an element as pivot and partitions the given array
 * around the picked pivot. There are many versions of quickSort
 * that pick pivot in different ways.
 *
 * <ul>
 *     <li>Always pick first element as pivot.</li>
 *     <li>Always pick last element as pivot (this implementation)</li>
 *     <li>Pick a random element as pivot.</li>
 *     <li>Pick median as pivot.</li>
 * </ul>
 *
 * <p>The key process in quickSort is partition(). Target of partitions
 * is, given an array and an element x of array as pivot, put x at its
 * correct position in sorted array and put all smaller elements
 * (smaller than x) before x, and put all greater elements (greater than
 * x) after x. All this should be done in linear time.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.algorithm.sort.Sorter
 * @since 1.0
 */
public class QuickSorter implements Sorter {

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public SortResults sort(final long @NonNull [] numbers) {
        final int size = numbers.length;

        if ( size == 0 )
        {
            // no elements to sort
            return ComputableSortResults.builder()
                    .identifier( QuickSorter.class.getSimpleName() )
                    .processTime( Duration.ZERO )
                    .build();
        }

        final long startTime = System.nanoTime();

        apply( numbers );

        final long endTime = System.nanoTime();

        return ComputableSortResults.builder()
                .identifier( QuickSorter.class.getSimpleName() )
                .processTime( Duration.ofNanos( endTime - startTime ) )
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long[] apply(final long[] longs) {

        quickSort( longs, 0, longs.length - 1 );

        return longs;
    }

    /**
     * Main function of the QuickSort algorithm.
     *
     * @param numbers array to be sorted
     * @param low     starting index
     * @param high    ending index
     */
    private void quickSort(final long[] numbers, final int low, final int high) {
        if ( low < high )
        {
            // pi is partitioning index, arr[p]
            // is now at right place
            final int pi = partition( numbers, low, high );

            // Separately sort elements before
            // partition and after partition
            quickSort( numbers, low, pi - 1 );
            quickSort( numbers, pi + 1, high );
        }
    }

    /**
     * This function takes last element as pivot, places
     * the pivot element at its correct position in sorted
     * array, and places all smaller (smaller than pivot)
     * to left of pivot and all greater elements to right
     * of pivot
     *
     * @param numbers array to be sorted
     * @param low     starting index
     * @param high    ending index
     */
    private int partition(final long[] numbers, final int low, final int high) {
        // pivot
        final long pivot = numbers[high];

        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (low - 1);

        for ( int j = low; j <= high - 1; ++j )
        {
            // If current element is smaller
            // than the pivot
            if ( numbers[j] < pivot )
            {
                // Increment index of
                // smaller element
                i++;
                swap( numbers, i, j );
            }
        }

        swap( numbers, i + 1, high );

        return (i + 1);
    }

    /**
     * Swaps the element at the position i and j in the array.
     *
     * @param arr to swap on the elements
     * @param i   to swap from
     * @param j   to swap to
     */
    private void swap(final long[] arr, final int i, final int j) {
        final long aux = arr[i];
        arr[i] = arr[j];
        arr[j] = aux;
    }
}
