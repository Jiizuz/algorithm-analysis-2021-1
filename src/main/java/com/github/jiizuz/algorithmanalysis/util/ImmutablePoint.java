package com.github.jiizuz.algorithmanalysis.util;

import lombok.NonNull;

import java.util.function.IntUnaryOperator;

/**
 * Represents an immutable point in a bi-dimensional cartesian plane.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @apiNote This class is immutable and Thread-Safe.
 * @see com.github.jiizuz.algorithmanalysis.util.Point
 * @since 1.5
 */
public final class ImmutablePoint extends Point implements Cloneable {

    /**
     * In mathematics, the origin of a Euclidean space is a special point,
     * usually denoted by the letter O, used as a fixed point of reference
     * for the geometry of the surrounding space.
     *
     * <p>Its components are as 0.
     */
    private static final ImmutablePoint ORIGIN = new ImmutablePoint();

    /**
     * Construct the point with provided integer components.
     *
     * @param x X component
     * @param y Y component
     */
    private ImmutablePoint(final int x, final int y) {
        super(x, y);
    }

    /**
     * Construct the point with all components as 0.
     */
    private ImmutablePoint() {
        super();
    }

    /**
     * Construct the point with provided integer components.
     *
     * @param x X component
     * @param y Y component
     * @return the constructed immutable point
     */
    @NonNull
    public static ImmutablePoint of(final int x, final int y) {
        return new ImmutablePoint(x, y);
    }

    /**
     * Construct the point with all components as 0.
     */
    @NonNull
    public static ImmutablePoint origin() {
        return ORIGIN;
    }

    /**
     * Guaranteed to throw an exception and leave the point unmodified.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @NonNull
    @Override
    @Deprecated
    public final ImmutablePoint add(final @NonNull Point poi) {
        throw new UnsupportedOperationException("immutable point");
    }

    /**
     * Guaranteed to throw an exception and leave the point unmodified.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @NonNull
    @Override
    @Deprecated
    public final ImmutablePoint subtract(final @NonNull Point poi) {
        throw new UnsupportedOperationException("immutable point");
    }

    /**
     * Guaranteed to throw an exception and leave the point unmodified.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @NonNull
    @Override
    @Deprecated
    public final ImmutablePoint multiply(final @NonNull Point poi) {
        throw new UnsupportedOperationException("immutable point");
    }

    /**
     * Guaranteed to throw an exception and leave the point unmodified.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @NonNull
    @Override
    @Deprecated
    public final ImmutablePoint divide(final @NonNull Point poi) {
        throw new UnsupportedOperationException("immutable point");
    }

    /**
     * Guaranteed to throw an exception and leave the point unmodified.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @NonNull
    @Override
    @Deprecated
    public final ImmutablePoint copy(final @NonNull Point poi) {
        throw new UnsupportedOperationException("immutable point");
    }

    /**
     * Guaranteed to throw an exception and leave the point unmodified.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @NonNull
    @Override
    @Deprecated
    public final ImmutablePoint midpoint(final @NonNull Point other) {
        throw new UnsupportedOperationException("immutable point");
    }

    /**
     * Guaranteed to throw an exception and leave the point unmodified.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @NonNull
    @Override
    @Deprecated
    public final ImmutablePoint multiply(final int m) {
        throw new UnsupportedOperationException("immutable point");
    }

    /**
     * Guaranteed to throw an exception and leave the point unmodified.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @NonNull
    @Override
    @Deprecated
    public final ImmutablePoint multiply(final double m) {
        throw new UnsupportedOperationException("immutable point");
    }

    /**
     * Guaranteed to throw an exception and leave the point unmodified.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @NonNull
    @Override
    @Deprecated
    public final ImmutablePoint multiply(final float m) {
        throw new UnsupportedOperationException("immutable point");
    }

    /**
     * Guaranteed to throw an exception and leave the point unmodified.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @NonNull
    @Override
    @Deprecated
    public final ImmutablePoint normalize() {
        throw new UnsupportedOperationException("immutable point");
    }

    /**
     * Guaranteed to throw an exception and leave the point unmodified.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @NonNull
    @Override
    @Deprecated
    public final ImmutablePoint zero() {
        throw new UnsupportedOperationException("immutable point");
    }

    /**
     * Guaranteed to throw an exception and leave the point unmodified.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @NonNull
    @Override
    @Deprecated
    public final ImmutablePoint computeX(final @NonNull IntUnaryOperator operator) {
        throw new UnsupportedOperationException("immutable point");
    }

    /**
     * Guaranteed to throw an exception and leave the point unmodified.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @NonNull
    @Override
    @Deprecated
    public final ImmutablePoint computeY(final @NonNull IntUnaryOperator operator) {
        throw new UnsupportedOperationException("immutable point");
    }

    /**
     * Guaranteed to throw an exception and leave the point unmodified.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @NonNull
    @Override
    @Deprecated
    public final ImmutablePoint setX(final int x) {
        throw new UnsupportedOperationException("immutable point");
    }

    /**
     * Guaranteed to throw an exception and leave the point unmodified.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @NonNull
    @Override
    @Deprecated
    public final ImmutablePoint setY(final int y) {
        throw new UnsupportedOperationException("immutable point");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutablePoint clone() {
        return (ImmutablePoint) super.clone();
    }
}
