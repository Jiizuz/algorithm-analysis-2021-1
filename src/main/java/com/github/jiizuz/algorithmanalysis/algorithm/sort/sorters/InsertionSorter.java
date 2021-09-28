package com.github.jiizuz.algorithmanalysis.algorithm.sort.sorters;

import com.github.jiizuz.algorithmanalysis.algorithm.sort.ComputableSortResults;
import com.github.jiizuz.algorithmanalysis.algorithm.sort.SortResults;
import com.github.jiizuz.algorithmanalysis.algorithm.sort.Sorter;
import lombok.NonNull;

import java.time.Duration;

/**
 * {@link Sorter} that inserts the values in order based on a Key.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.algorithm.sort.Sorter
 * @since 1.0
 */
public class InsertionSorter implements Sorter {

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
                    .identifier( InsertionSorter.class.getSimpleName() )
                    .processTime( Duration.ZERO )
                    .build();
        }

        final long startTime = System.nanoTime();

        apply( numbers );

        final long endTime = System.nanoTime();

        return ComputableSortResults.builder()
                .identifier( InsertionSorter.class.getSimpleName() )
                .processTime( Duration.ofNanos( endTime - startTime ) )
                .build();
    }

    /**
     * {@inheritDoc}
     *
     * <p>Time complexity - Best case: O(n), Worst/Average case: O(n<sup>2</sup>)
     * <p>Space complexity - O(n)
     */
    @Override
    public long[] apply(final long[] numbers) {
        final int length = numbers.length;

        for ( int i = 1; i < length; ++i )
        {
            long key = numbers[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
             * greater than key, to one position ahead
             * of their current position
             * */
            while ( j >= 0 && numbers[j] > key )
            {
                numbers[j + 1] = numbers[j];
                j = j - 1;
            }
            numbers[j + 1] = key;
        }

        return numbers;
    }
}
