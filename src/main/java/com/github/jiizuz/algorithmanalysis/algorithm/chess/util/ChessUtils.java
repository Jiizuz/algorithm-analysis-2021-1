package com.github.jiizuz.algorithmanalysis.algorithm.chess.util;

import com.github.jiizuz.algorithmanalysis.util.Point;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Utility class with methods related with a chess game.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @since 1.5
 */
@UtilityClass
public class ChessUtils {

    // conversion util

    /**
     * Converts the specified {@link AlgebraicNotation} into a {@link Point}.
     *
     * @param notation to convert
     * @param height   of the board to use as reference
     * @return the resultant {@link Point}
     * @throws NullPointerException     if the notation is <tt>null</tt>
     * @throws IllegalArgumentException if the height is <= 0
     */
    @NonNull
    public Point toPoint(final @NonNull AlgebraicNotation notation, final int height) {
        checkArgument(height > 0, "height <= 0", height);

        final int x = notation.getFile() - 'a';
        final int y = height - notation.getRank();

        return new Point(x, y);
    }

    /**
     * Converts the specified {@link Point} into an {@link AlgebraicNotation}.
     *
     * @param point  to convert
     * @param height of the board to use as reference
     * @return the resultant {@link AlgebraicNotation}
     * @throws NullPointerException     if the point is <tt>null</tt>
     * @throws IllegalArgumentException if the height is <= 0
     */
    @NonNull
    public AlgebraicNotation toAlgebraicNotation(final @NonNull Point point, final int height) {
        checkArgument(height > 0, "height <= 0", height);

        final int x = point.getX();
        final int y = point.getY();

        return new AlgebraicNotation((char) ('a' + x), (byte) (height - y));
    }
}
