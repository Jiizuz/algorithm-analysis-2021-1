package com.github.jiizuz.algorithmanalysis.algorithm.chess.knighttour;

import com.github.jiizuz.algorithmanalysis.algorithm.chess.util.AlgebraicNotation;
import com.github.jiizuz.algorithmanalysis.algorithm.chess.util.ChessUtils;
import com.github.jiizuz.algorithmanalysis.util.ImmutablePoint;
import com.github.jiizuz.algorithmanalysis.util.Point;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Main class to run a {@link KnightTour} test.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @since 1.5
 */
@UtilityClass
public class KnightTourMain {

    /**
     * Size of the chessboard.
     */
    private final int SIZE = 8;

    /**
     * Representative char of the Knight.
     */
    private final char KNIGHT = 'K';

    /**
     * Representative char of an empty space.
     */
    private final char EMPTY = '□';

    /**
     * Representative char to mark a space as 'traveled' by the knight.
     */
    private final char TRAVELED = 'X';

    /**
     * Runs the main instance of the project.
     *
     * @param args passed in the command line
     */
    public void main(final String[] args) {
        final int width = SIZE;
        final int height = SIZE;

        final KnightTour knightTour = new BruteForceKnightTour(true, width, height);

        final List<Point> tour = knightTour.findTour(ImmutablePoint.origin());

        // map the points into an algebraic notation list
        final List<AlgebraicNotation> moves = tour.stream()
                .map(point -> ChessUtils.toAlgebraicNotation(point, height))
                .collect(ImmutableList.toImmutableList());

        final StringBuilder bob = new StringBuilder();

        appendMovesInList(bob, moves);

        bob.append('\n');

        appendMovesOnBoard(bob, moves, height, width, true);

        System.out.print(bob);
    }

    // result formatting

    /**
     * Formats the specified list of {@link AlgebraicNotation} into a more visible string.
     *
     * @param bob       to append over the result
     * @param notations to format
     */
    public void appendMovesInList(final @NonNull StringBuilder bob, final @NonNull List<AlgebraicNotation> notations) {
        for (int i = 0, n = notations.size(); i < n; ++i) {
            bob.append(String.format("%2d", i + 1)).append(".  ")
                    .append(i == 0 ? "XX" : format(notations.get(i - 1)))
                    .append("   ")
                    .append(format(notations.get(i)))
                    .append('\n');
        }
    }

    /**
     * Formats the specified list of {@link AlgebraicNotation} into a more visible string.
     *
     * @param moves to format
     * @return the formatted algebraic moves
     * @see #appendMovesInList(StringBuilder, List)
     */
    @NonNull
    public String formatMovesInList(final @NonNull List<AlgebraicNotation> moves) {
        final StringBuilder bob = new StringBuilder();

        appendMovesInList(bob, moves);

        return bob.toString();
    }

    /**
     * Formats the specified moves into a chessboard representation.
     *
     * @param bob                     to append over the result
     * @param moves                   to record in a chessboards
     * @param height                  of the board to create
     * @param width                   of the board to create
     * @param appendAlgebraicNotation whether append the algebraic notation of the move
     */
    public void appendMovesOnBoard(final @NonNull StringBuilder bob, final @NonNull List<AlgebraicNotation> moves, final int height, final int width, final boolean appendAlgebraicNotation) {
        final List<Point> points = moves.stream()
                .map(notation -> ChessUtils.toPoint(notation, height))
                .collect(ImmutableList.toImmutableList());

        final boolean[][] cache = new boolean[height][width];

        final String footer = getBoardFooter(width);

        for (int i = 0, n = moves.size(); i < n; i++) {
            final AlgebraicNotation move = moves.get(i);

            final Point point = points.get(i);
            cache[point.getY()][point.getX()] = true;

            for (int y = 0; y < height; y++) {
                bob.append(height - y).append(" │");

                for (int x = 0; x < width; x++) {
                    bob.append(' ').append(cache[y][x] ? (y == point.getY() && x == point.getX() ? KNIGHT : TRAVELED) : EMPTY);
                }

                if (appendAlgebraicNotation && y == 1) { // append algebraic notation
                    bob.append("  ").append(i + 1).append(".  ")
                            .append(i == 0 ? "XX" : format(moves.get(i - 1)))
                            .append("   ")
                            .append(format(move));
                }

                bob.append('\n');
            }

            bob.append(footer);
        }
    }

    /**
     * Formats the specified moves into a chessboard representation.
     *
     * @param moves                   to record in a chessboards
     * @param height                  of the board to create
     * @param width                   of the board to create
     * @param appendAlgebraicNotation whether append the algebraic notation of the move
     * @return the resultant format of the moves
     */
    @NonNull
    public String formatMovesOnBoard(final @NonNull List<AlgebraicNotation> moves, final int height, final int width, final boolean appendAlgebraicNotation) {
        final StringBuilder bob = new StringBuilder();

        appendMovesOnBoard(bob, moves, height, width, appendAlgebraicNotation);

        return bob.toString();
    }

    /**
     * Generates a board footer with the specified width.
     *
     * <p>The result is something like: (width = 8)<pre>
     *       └────────────────
     *         a b c d e f g h
     * </pre>
     *
     * @param width of the chessboard
     * @return the generated board footer
     */
    @NonNull
    private String getBoardFooter(final int width) {
        checkArgument(width > 0, "width <= 0", width);

        final StringBuilder bob = new StringBuilder();

        bob.append("  └").append(Strings.repeat("─", width << 1)).append('\n');

        bob.append("   ");
        for (int b = 0; b < width; b++) {
            bob.append(' ').appendCodePoint(b + 'a');
        }
        bob.append('\n').append('\n');

        return bob.toString();
    }

    // util

    /**
     * Formats the specified algebraic notation into a direct string.
     *
     * @param move to format into a string
     * @return the formatted algebraic notation
     */
    @NonNull
    public String format(final @NonNull AlgebraicNotation move) {
        return String.valueOf(move.getFile()) + move.getRank();
    }
}
