package com.github.jiizuz.algorithmanalysis.algorithm.sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.Duration;
import java.util.function.Function;

/**
 * {@link SortResults} that promises to be <b>Immutable</b>.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.algorithm.sort.SortResults
 * @since 1.0
 */
@Data
@Builder
@AllArgsConstructor
public final class ImmutableSortResults implements SortResults {

    /**
     * Identifier of the results
     */
    @NonNull
    private final String identifier;

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
        throw new UnsupportedOperationException( "immutable implementation" );
    }
}
