package com.github.jiizuz.algorithmanalysis.algorithm.sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.Duration;
import java.util.function.Function;

/**
 * {@link SortResults} that allows computation in the fields.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.algorithm.sort.SortResults
 * @since 1.0
 */
@Data
@Builder
@AllArgsConstructor
public class ComputableSortResults implements SortResults {

    /**
     * Identifier of the results
     */
    @NonNull
    private String identifier;

    /**
     * Duration of the algorithm.
     */
    @NonNull
    private final Duration processTime;

    /**
     * {@inheritDoc}
     */
    @Override
    public void computeIdentifier(final @NonNull Function<String, String> compute) {
        identifier = compute.apply( identifier );
    }
}
