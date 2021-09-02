package com.github.jiizuz.algorithmanalysis.algorithm.sort;

import com.github.jiizuz.algorithmanalysis.algorithm.sort.bubble.BubbleSorter;
import com.github.jiizuz.algorithmanalysis.algorithm.sort.bubble.OptimizedBubbleSorter;
import lombok.experimental.UtilityClass;

/**
 * Main class of the project to initialize and run.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @since 1.0
 */
@UtilityClass
public class Main {

    /**
     * Runs the main instance of the project.
     *
     * @param args passed in the command line
     */
    public static void main(String[] args) {
        final long[] longs = { 3, 5, 1, 2, 4 };

        final Sorter bubbleSorter = new BubbleSorter();
        final SortResults bubbleSortResults = bubbleSorter.sort(longs.clone());
        final Sorter optimizedBubbleSorter = new OptimizedBubbleSorter();
        final SortResults optimizedBubbleSortResults = optimizedBubbleSorter.sort(longs.clone());

        System.out.printf("%5d | %,d ns%n",
                bubbleSortResults.getIterations(), bubbleSortResults.getProcessTime().getNano());
        System.out.println();
        System.out.printf("%5d | %,d ns%n",
                optimizedBubbleSortResults.getIterations(), optimizedBubbleSortResults.getProcessTime().getNano());
    }
}
