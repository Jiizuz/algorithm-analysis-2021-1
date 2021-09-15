package com.github.jiizuz.algorithmanalysis.algorithm.sort.bubble;

import com.github.jiizuz.algorithmanalysis.algorithm.sort.ComputableSortResults;
import com.github.jiizuz.algorithmanalysis.algorithm.sort.SortResults;
import com.github.jiizuz.algorithmanalysis.algorithm.sort.Sorter;
import lombok.NonNull;

import java.time.Duration;

/**
 * {@link Sorter} that uses an optimized version of the classic <tt>Bubble Sort</tt>.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.algorithm.sort.Sorter
 * @since 1.0
 */
public class OptimizedBubbleSorter implements Sorter {

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public SortResults sort(final @NonNull long[] numbers ) {
        boolean isSwapped = true;

        int iterateCount = 0;

        final long startTime = System.nanoTime();

        while ( isSwapped ) {

            isSwapped = false;

            for ( int i = 0; i < numbers.length - 1 - iterateCount; i++ ) {
                if ( numbers[i] > numbers[i + 1] ) {
                    long temp = numbers[i];
                    numbers[i] = numbers[i + 1];
                    numbers[i + 1] = temp;
                    isSwapped = true;
                }
            }

            iterateCount++;
        }

        final long endTime = System.nanoTime();

        return ComputableSortResults.builder()
                .identifier( OptimizedBubbleSorter.class.getSimpleName() )
                .processTime( Duration.ofNanos( endTime - startTime ) )
                .build();
    }
}
