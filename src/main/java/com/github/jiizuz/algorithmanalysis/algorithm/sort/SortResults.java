package com.github.jiizuz.algorithmanalysis.algorithm.sort;

import lombok.NonNull;

import java.time.Duration;

/**
 * Results generated sorting an array of elements.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @since 1.0
 */
public interface SortResults {

    /**
     * Returns the amount of iterations made by the algorithm.
     *
     * @return the amount of iterations made by the algorithm
     */
    int getIterations();

    /**
     * Returns the duration consumed of CPU by the algorithm.
     *
     * @return the duration consumed of CPU by the algorithm
     */
    @NonNull
    Duration getProcessTime();
}
