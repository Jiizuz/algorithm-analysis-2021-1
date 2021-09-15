package com.github.jiizuz.algorithmanalysis.algorithm.sort.sorters;

import com.github.jiizuz.algorithmanalysis.algorithm.sort.ComputableSortResults;
import com.github.jiizuz.algorithmanalysis.algorithm.sort.SortResults;
import com.github.jiizuz.algorithmanalysis.algorithm.sort.Sorter;
import lombok.NonNull;

import java.time.Duration;

/**
 * {@link Sorter} that uses the classic <tt>Bubble Sort</tt>.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.algorithm.sort.Sorter
 * @since 1.0
 */
public class BubbleSorter implements Sorter {

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public SortResults sort(final @NonNull long[] number) {
        final int size = number.length;

        if ( size == 0 )
        {
            // no elements to sort
            return ComputableSortResults.builder()
                    .identifier( BubbleSorter.class.getSimpleName() )
                    .processTime( Duration.ZERO )
                    .build();
        }

        final long startTime = System.nanoTime();

        for ( int i = 0; i < size - 1; i++ )
        {
            for ( int j = 0; j < size - i - 1; j++ )
            {
                if ( number[j] > number[j + 1] )
                {
                    // swap number[ j + 1 ] and number[ j ]
                    long temp = number[j];
                    number[j] = number[j + 1];
                    number[j + 1] = temp;
                }
            }
        }

        final long endTime = System.nanoTime();

        return ComputableSortResults.builder()
                .identifier( BubbleSorter.class.getSimpleName() )
                .processTime( Duration.ofNanos( endTime - startTime ) )
                .build();
    }
}
