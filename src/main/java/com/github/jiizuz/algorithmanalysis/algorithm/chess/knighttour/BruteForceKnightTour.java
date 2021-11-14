package com.github.jiizuz.algorithmanalysis.algorithm.chess.knighttour;

import com.github.jiizuz.algorithmanalysis.algorithm.chess.util.KnightUtils;
import com.github.jiizuz.algorithmanalysis.util.Point;
import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;
import java.util.Random;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * A brute-force search for a knight's tour is impractical on all
 * but the smallest boards. For example, there are approximately
 * 4×1051 possible move sequences on an 8 × 8 board, and it is
 * well beyond the capacity of modern computers (or networks of
 * computers) to perform operations on such a large set. However,
 * the size of this number is not indicative of the difficulty of
 * the problem, which can be solved "by using human insight and
 * ingenuity... without much difficulty."
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.algorithm.chess.knighttour.KnightTour
 * @since 1.5
 */
@Builder
public class BruteForceKnightTour implements KnightTour {

    /**
     * Default size of a chessboard.
     */
    public static final int DEFAULT_SIZE = 8;

    /**
     * {@link Random} utility to generate pseudo-random numbers.
     */
    @Builder.Default
    private Random random = new Random();

    /**
     * Whether the algorithm should use the specified start point, or
     * allow the algorithm to generate pseudo-random start points.
     */
    @Builder.Default
    private boolean forceStart = false;

    /**
     * Width of the table. (squares)
     */
    @Builder.Default
    private int width = DEFAULT_SIZE;

    /**
     * Height of the table. (squares)
     */
    @Builder.Default
    private int height = DEFAULT_SIZE;

    /**
     * Creates a new {@link BruteForceKnightTour} with the specified configuration.
     *
     * @param random     utility to generate pseudo-random numbers
     * @param forceStart whether force start point on the tours
     * @param width      width of the table
     * @param height     height of the table
     * @throws NullPointerException     if the random is <tt>null</tt>
     * @throws IllegalArgumentException if either the width or height are negative or zero
     */
    public BruteForceKnightTour(final @NonNull Random random, final boolean forceStart, final int width, final int height) {
        checkArgument(width > 0, "width <= 0", width);
        checkArgument(height > 0, "width <= 0", height);

        this.random = random;
        this.forceStart = forceStart;
        this.width = width;
        this.height = height;
    }

    /**
     * Creates a new {@link BruteForceKnightTour} with the specified configuration.
     *
     * @param random utility to generate pseudo-random numbers
     * @param width  width of the table
     * @param height height of the table
     * @throws NullPointerException     if the random is <tt>null</tt>
     * @throws IllegalArgumentException if either the width or height are negative or zero
     * @apiNote The start points will not be forced.
     */
    public BruteForceKnightTour(final @NonNull Random random, final int width, final int height) {
        this(random, false, width, height);
    }

    /**
     * Creates a new {@link BruteForceKnightTour} with the specified configuration.
     *
     * @param forceStart whether force start point on the tours
     * @param width      width of the table
     * @param height     height of the table
     * @throws IllegalArgumentException if either the width or height are negative or zero
     * @apiNote A default {@link Random} instance will be used.
     */
    public BruteForceKnightTour(final boolean forceStart, final int width, final int height) {
        this(new Random(), forceStart, width, height);
    }

    /**
     * Creates a new {@link BruteForceKnightTour} with the specified configuration.
     *
     * <p>This constructor will use a default {@link Random} instance and will
     * allow pseudo-random start points when one with the specified points are
     * not found.
     *
     * @param width  width of the table
     * @param height height of the table
     * @throws IllegalArgumentException if either the width or height are negative or zero
     */
    public BruteForceKnightTour(final int width, final int height) {
        this(new Random(), false, width, height);
    }

    /**
     * Creates a new {@link BruteForceKnightTour} with the specified configuration.
     *
     * @param random     utility to generate pseudo-random numbers
     * @param forceStart whether force start point on the tours
     * @param size       size of the table a.k.a. <i>n</i>
     * @throws NullPointerException     if the random is <tt>null</tt>
     * @throws IllegalArgumentException if the size is are negative or zero
     */
    public BruteForceKnightTour(final @NonNull Random random, final boolean forceStart, final int size) {
        this(random, forceStart, size, size);
    }

    /**
     * Creates a new {@link BruteForceKnightTour} with the specified configuration.
     *
     * @param random utility to generate pseudo-random numbers
     * @param size   size of the table a.k.a. <i>n</i>
     * @throws NullPointerException     if the random is <tt>null</tt>
     * @throws IllegalArgumentException if the size is are negative or zero
     * @apiNote The start points will not be forced.
     */
    public BruteForceKnightTour(final @NonNull Random random, final int size) {
        this(random, false, size);
    }

    /**
     * Creates a new {@link BruteForceKnightTour} with the specified configuration.
     *
     * @param forceStart whether force start point on the tours
     * @param size       size of the table a.k.a. <i>n</i>
     * @throws IllegalArgumentException if either the width or height are negative or zero
     * @throws IllegalArgumentException if the size is are negative or zero
     * @apiNote A default {@link Random} instance will be used.
     */
    public BruteForceKnightTour(final boolean forceStart, final int size) {
        this(new Random(), forceStart, size);
    }

    /**
     * Creates a new {@link BruteForceKnightTour} with the specified configuration.
     *
     * <p>This constructor will use a default {@link Random} instance and will
     * allow pseudo-random start points when one with the specified points are
     * not found.
     *
     * @param size size of the table a.k.a. <i>n</i>
     * @throws IllegalArgumentException if the size is are negative or zero
     */
    public BruteForceKnightTour(final int size) {
        this(new Random(), false, size);
    }

    /**
     * Creates a new {@link BruteForceKnightTour} with the specified configuration.
     *
     * <p>The size of the board will be {@link #DEFAULT_SIZE}.
     *
     * @param random utility to generate pseudo-random numbers
     * @throws NullPointerException if the random is <tt>null</tt>
     * @apiNote The start points will not be forced.
     * @see #DEFAULT_SIZE
     */
    public BruteForceKnightTour(final @NonNull Random random) {
        this(random, false, DEFAULT_SIZE);
    }

    /**
     * Creates a new {@link BruteForceKnightTour} with the specified configuration.
     *
     * <p>The size of the board will be {@link #DEFAULT_SIZE}.
     *
     * @param forceStart whether force start point on the tours
     * @throws IllegalArgumentException if either the width or height are negative or zero
     * @throws IllegalArgumentException if the size is are negative or zero
     * @apiNote A default {@link Random} instance will be used.
     * @see #DEFAULT_SIZE
     */
    public BruteForceKnightTour(final boolean forceStart) {
        this(new Random(), forceStart, DEFAULT_SIZE);
    }

    /**
     * Creates a new {@link BruteForceKnightTour} with the default configuration.
     *
     * <p>This constructor will use a default {@link Random} instance and will
     * allow pseudo-random start points when one with the specified points are
     * not found.
     *
     * <p>The size of the board will be {@link #DEFAULT_SIZE}.
     *
     * @see #DEFAULT_SIZE
     */
    public BruteForceKnightTour() {
        this(new Random(), false, DEFAULT_SIZE);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public List<Point> findTour(final @NonNull Point start) {
        final Set<Point> tour = new ObjectLinkedOpenHashSet<>(width * height);

        final Point at = new Point();
        at.copy(start);

        // first point
        tour.add(at.clone());

        // auxiliary data
        final int[] possible = new int[KnightUtils.POSSIBLE_MOVES.length];
        final Point aux = new Point();
        int iPossible; // index of #possible
        int i;

        while (true) { // infinite loop, recursion will throw StackOverflow

            iPossible = 0;
            for (i = 0; i < KnightUtils.POSSIBLE_MOVES.length; ++i) { // find potential moves
                aux.copy(at).add(KnightUtils.POSSIBLE_MOVES[i]);

                if (isOnBoard(aux) && !tour.contains(aux)) {
                    possible[iPossible++] = i;
                }
            }

            if (iPossible == 0) {
                if (tour.size() == width * height) { // successful tour
                    return ImmutableList.copyOf(tour);
                } // failed tour, reset

                tour.clear(); // dump data

                at.copy(forceStart ? start : generateRandomPoint(random)); // reset start point
            } else {
                // add random potential move
                at.add(KnightUtils.POSSIBLE_MOVES[possible[random.nextInt(iPossible)]]);
            }

            tour.add(at.clone());
        }
    }

    // util

    /**
     * Returns whether the specified point is in bounds of the chess board.
     *
     * @param point to check if it is on board
     * @return <tt>true</tt> if the point is in bounds of the board
     */
    private boolean isOnBoard(final @NonNull Point point) {
        return isOnBoard(point.getX(), point.getY());
    }

    /**
     * Returns whether the specified point is in bounds of the chess board.
     *
     * @param x value on the X axis of the point
     * @param y value on the Y axis of the point
     * @return <tt>true</tt> if the point is in bounds of the board
     */
    private boolean isOnBoard(final int x, final int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    /**
     * Generates a new valid pseudo-random point over the board.
     *
     * @param random to generate pseudo-random values
     * @return the new generated {@link Point}
     */
    @NonNull
    private Point generateRandomPoint(final @NonNull Random random) {
        return new Point(random.nextInt(width), random.nextInt(height));
    }

    /**
     * Generates a new valid pseudo-random point over the board.
     *
     * @return the new generated {@link Point}
     */
    @NonNull
    private Point generateRandomPoint() {
        return generateRandomPoint(new Random());
    }
}
