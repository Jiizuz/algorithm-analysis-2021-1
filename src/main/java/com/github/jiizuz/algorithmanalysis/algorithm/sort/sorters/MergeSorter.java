package com.github.jiizuz.algorithmanalysis.algorithm.sort.sorters;

import com.github.jiizuz.algorithmanalysis.algorithm.sort.ComputableSortResults;
import com.github.jiizuz.algorithmanalysis.algorithm.sort.SortResults;
import com.github.jiizuz.algorithmanalysis.algorithm.sort.Sorter;
import lombok.NonNull;

import java.time.Duration;

/**
 * Like {@link QuickSorter}, Merge Sort is a Divide and Conquer algorithm.
 * It divides the input array into two halves, calls itself for the two
 * halves, and then merges the two sorted halves. The #merge() function is
 * used for merging two halves. The {@link #merge(long[], int, int, int)}
 * is a key process that assumes that <pre>numbers[l, ..., m]</pre> and
 * <pre>numbers[m + 1, ..., r]</pre> are sorted and merges the two sorted
 * sub-arrays into one.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.algorithm.sort.Sorter e
 * @since 1.2
 */
public class MergeSorter implements Sorter {

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
                    .identifier( MergeSorter.class.getSimpleName() )
                    .processTime( Duration.ZERO )
                    .build();
        }

        final long startTime = System.nanoTime();

        apply( numbers );

        final long endTime = System.nanoTime();

        return ComputableSortResults.builder()
                .identifier( MergeSorter.class.getSimpleName() )
                .processTime( Duration.ofNanos( endTime - startTime ) )
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long[] apply(final long[] numbers) {

        mergeSort( numbers, 0, numbers.length - 1 );

        return numbers;
    }

    /**
     * Main function of the Merge Sort algorithm.
     *
     * @param numbers array to sort
     * @param left    initial position in the array
     * @param right   final position in the array
     * @apiNote sorts numbers[left, ..., right]
     */
    private void mergeSort(final long[] numbers, final int left, final int right) {
        if ( left < right )
        {
            // Find the middle point
            final int middle = left + ( right - left ) / 2;

            // Sort first and second halves
            mergeSort( numbers, left, middle );
            mergeSort( numbers, middle + 1, right );

            // Merge the sorted halves
            merge( numbers, left, middle, right );
        }
    }

    /**
     * Merges two sub-arrays of<pre>numbers[]</pre>
     *
     * <p>The first sub-array is<pre>numbers[left, ..., middle]</pre>
     *
     * <p>The second sub-array is<pre>numbers[middle + 1, ..., right]</pre>
     *
     * @param numbers to retrieve from the numbers
     * @param left    first index of the first array
     * @param middle  middle point of the sub-arrays (also last index of the first array)
     * @param right   last index of the second array
     */
    private void merge(final long[] numbers, final int left, final int middle, final int right) {
        // Find sizes of two sub-arrays to be merged
        final int n1 = middle - left + 1;
        final int n2 = right - middle;

        // Create temp arrays
        final long[] L = new long[n1];
        final long[] R = new long[n2];

        // Copy data to temp arrays
        System.arraycopy( numbers, left, L, 0, n1 );
        System.arraycopy( numbers, middle + 1, R, 0, n2 );

        /* Merge the temp arrays */

        // Initial indexes of first and second sub-arrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = left;
        while ( i < n1 && j < n2 )
        {
            if ( L[i] <= R[j] )
            {
                numbers[k] = L[i];
                i++;
            } else
            {
                numbers[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while ( i < n1 )
        {
            numbers[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while ( j < n2 )
        {
            numbers[k] = R[j];
            j++;
            k++;
        }
    }
}
