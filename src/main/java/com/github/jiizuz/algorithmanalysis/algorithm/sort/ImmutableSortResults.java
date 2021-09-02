package com.github.jiizuz.algorithmanalysis.algorithm.sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.Duration;

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
public class ImmutableSortResults implements SortResults {

    /**
     * iterations by the process.
     */
    private final int iterations;

    /**
     * Duration of the algorithm.
     */
    @NonNull
    private final Duration processTime;
}
