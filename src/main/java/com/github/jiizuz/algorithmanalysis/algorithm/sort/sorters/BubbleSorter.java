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
    public SortResults sort(final long @NonNull [] numbers) {
        final int size = numbers.length;

        if ( size == 0 )
        {
            // no elements to sort
            return ComputableSortResults.builder()
                    .identifier( BubbleSorter.class.getSimpleName() )
                    .processTime( Duration.ZERO )
                    .build();
        }

        final long startTime = System.nanoTime();

        apply( numbers );

        final long endTime = System.nanoTime();

        return ComputableSortResults.builder()
                .identifier( BubbleSorter.class.getSimpleName() )
                .processTime( Duration.ofNanos( endTime - startTime ) )
                .build();
    }

    /**
     * {@inheritDoc}
     *
     * <p>Time complexity - O(n<sup>2</sup>)
     * <p>Space complexity - O(1)
     */
    @Override
    public long[] apply(final long[] numbers) {
        final int length = numbers.length;

        for ( int i = 0; i < length - 1; i++ )
        {
            for ( int j = 0; j < length - i - 1; j++ )
            {
                if ( numbers[j] > numbers[j + 1] )
                {
                    // swap numbers[ j + 1 ] and numbers[ j ]
                    final long aux = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = aux;
                }
            }
        }

        return numbers;
    }
}
