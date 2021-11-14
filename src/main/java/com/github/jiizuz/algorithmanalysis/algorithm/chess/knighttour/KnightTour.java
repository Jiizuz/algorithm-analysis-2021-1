package com.github.jiizuz.algorithmanalysis.algorithm.chess.knighttour;

import com.github.jiizuz.algorithmanalysis.util.Point;
import lombok.NonNull;

import java.util.List;
import java.util.RandomAccess;

/**
 * A knight's tour is a sequence of moves of a knight on a chessboard
 * such that the knight visits every square exactly once. If the knight
 * ends on a square that is one knight's move from the beginning square
 * (so that it could tour the board again immediately, following the same
 * path), the tour is closed; otherwise, it is open.
 *
 * <p>The knight's tour problem is the mathematical problem of finding a
 * knight's tour. Creating a program to find a knight's tour is a common
 * problem given to computer science students. Variations of the knight's
 * tour problem involve chessboards of different sizes than the usual 8 × 8,
 * as well as irregular (non-rectangular) boards.
 *
 * <p><b>Number of tours</b>
 *
 * <p>On an 8 × 8 board, there are exactly 26,534,728,821,064 directed
 * closed tours (i.e. two tours along the same path that travel in opposite
 * directions are counted separately, as are rotations and reflections).
 * The number of undirected closed tours is half this number, since every
 * tour can be traced in reverse. There are 9,862 undirected closed tours
 * on a 6 × 6 board.
 *
 * <p><table>
 * <tr>
 * <th><i>n</i></th>
 * <th>Number of directed tours (open and closed) on an <i>n</i> × <i>n</i> board (sequence <a href="https://oeis.org/A165134">A165134</a> in the <a href="https://en.wikipedia.org/wiki/On-Line_Encyclopedia_of_Integer_Sequences">OEIS</a>)</th>
 * </tr>
 * <tr>
 * <td>1</td>
 * <td>1</td>
 * </tr>
 * <tr>
 * <td>2</td>
 * <td>0</td>
 * </tr>
 * <tr>
 * <td>3</td>
 * <td>0</td>
 * </tr>
 * <tr>
 * <td>4</td>
 * <td>0</td>
 * </tr>
 * <tr>
 * <td>5</td>
 * <td>1,728</td>
 * </tr>
 * <tr>
 * <td>6</td>
 * <td>6,637,920</td>
 * </tr>
 * <tr>
 * <td>7</td>
 * <td>165,575,218,320</td>
 * </tr>
 * <tr>
 * <td>8</td>
 * <td>19,591,828,170,979,904</td>
 * </tr>
 * </table>
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @implNote This class is not Thread-Safe.
 * @see <a href="https://en.wikipedia.org/wiki/Knight%27s_tour">Knight's Tour</a>
 * @since 1.5
 */
public interface KnightTour {

    /**
     * Finds a knight's tour using the specified point as the start of the knight.
     *
     * @param from starting point of the knight
     * @return the found tour in form of a list with the points to follow
     * @throws NullPointerException if the starting point is <tt>null</tt>
     * @implNote The returned {@link List} is also a {@link RandomAccess} list.
     *
     * <p>The specified point must not be modified in any way.
     *
     * <p>The returned points may be or not <tt>immutable</tt> representations.
     */
    @NonNull
    List<Point> findTour(@NonNull Point from);
}
