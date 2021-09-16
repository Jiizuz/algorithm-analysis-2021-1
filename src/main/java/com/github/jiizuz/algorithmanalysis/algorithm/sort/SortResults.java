package com.github.jiizuz.algorithmanalysis.algorithm.sort;

import lombok.NonNull;

import java.time.Duration;
import java.util.function.Function;

/**
 * Results generated sorting an array of elements.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @since 1.0
 */
public interface SortResults {

    /**
     * Returns an identifier of the results.
     *
     * @return an identifier of the results
     */
    @NonNull
    String getIdentifier();

    /**
     * Computes the identifier in order to replace the value and
     * set the result of the specified compute function receiving
     * the current identifier.
     *
     * @param compute to compute in order to retrieve the identifier
     * @throws NullPointerException if the function is <tt>null</tt>
     */
    void computeIdentifier(@NonNull Function<String, String> compute);

    /**
     * Returns the duration consumed of CPU by the algorithm.
     *
     * @return the duration consumed of CPU by the algorithm
     */
    @NonNull
    Duration getProcessTime();
}
