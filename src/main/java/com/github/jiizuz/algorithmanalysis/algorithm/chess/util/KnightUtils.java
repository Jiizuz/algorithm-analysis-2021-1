package com.github.jiizuz.algorithmanalysis.algorithm.chess.util;

import com.github.jiizuz.algorithmanalysis.util.ImmutablePoint;
import com.github.jiizuz.algorithmanalysis.util.Point;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

/**
 * {@link UtilityClass} with methods related with the <b>Knight</b> piece.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @since 1.5
 */
@UtilityClass
public class KnightUtils {

    /**
     * Possible jump patterns of a knight in a chess table.
     */
    public final Point @NonNull [] POSSIBLE_MOVES = {
            // up
            ImmutablePoint.of(+1, -2),
            ImmutablePoint.of(-1, -2),

            // right
            ImmutablePoint.of(+2, -1),
            ImmutablePoint.of(+2, +1),

            // down
            ImmutablePoint.of(+1, +2),
            ImmutablePoint.of(-1, +2),

            // left
            ImmutablePoint.of(-2, -1),
            ImmutablePoint.of(-2, +1),
    };
}
