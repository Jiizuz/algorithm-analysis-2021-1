package com.github.jiizuz.algorithmanalysis.util;

import com.google.common.base.MoreObjects;
import lombok.NonNull;

import java.util.function.IntUnaryOperator;

/**
 * Represents a mutable point in a bi-dimensional cartesian plane. Because
 * the components of Points are mutable, storing Points long term may be
 * dangerous if passing code modifies the Point later. If you want to keep
 * around a Point, it may be wise to call <tt>clone()</tt> in order to get
 * a copy.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @apiNote This class is mutable and not Thread-Safe.
 * @see java.lang.Comparable
 * @since 1.5
 */
public class Point implements Comparable<Point>, Cloneable {

    protected int x;
    protected int y;

    /**
     * Construct the point with provided integer components.
     *
     * @param x X component
     * @param y Y component
     */
    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Construct the point with all components as 0.
     */
    public Point() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Adds a point to this one
     *
     * @param poi the other point
     * @return the same point
     */
    @NonNull
    public Point add(final @NonNull Point poi) {
        x += poi.x;
        y += poi.y;
        return this;
    }

    /**
     * Subtracts a point from this one.
     *
     * @param poi the other point
     * @return the same point
     */
    @NonNull
    public Point subtract(final @NonNull Point poi) {
        x -= poi.x;
        y -= poi.y;
        return this;
    }

    /**
     * Multiplies the point by another.
     *
     * @param poi the other point
     * @return the same point
     */
    @NonNull
    public Point multiply(final @NonNull Point poi) {
        x *= poi.x;
        y *= poi.y;
        return this;
    }

    /**
     * Divides the point by another.
     *
     * @param poi the other point
     * @return the same point
     */
    @NonNull
    public Point divide(final @NonNull Point poi) {
        x /= poi.x;
        y /= poi.y;
        return this;
    }

    /**
     * Copies another point
     *
     * @param poi the other point
     * @return the same point
     */
    @NonNull
    public Point copy(final @NonNull Point poi) {
        x = poi.x;
        y = poi.y;
        return this;
    }

    /**
     * Gets the magnitude of the point, defined as sqrt(x^2+y^2).  The
     * value of this method is not cached and uses a costly square-root
     * function, so do not repeatedly call this method to get the point's
     * magnitude. NaN will be returned if the inner result of the sqrt()
     * function overflows, which will be caused if the length is too long.
     *
     * @return the magnitude
     */
    public double length() {
        return Math.sqrt(MathUtils.square(x) + MathUtils.square(y));
    }

    /**
     * Gets the magnitude of the point squared.
     *
     * @return the magnitude
     */
    public double lengthSquared() {
        return MathUtils.square(x) + MathUtils.square(y);
    }

    /**
     * Get the distance between this point and another.   The value of this
     * method is not cached and uses a costly square-root function, so do not
     * repeatedly call this method to get the point's magnitude. NaN will be
     * returned if the inner result of the sqrt() function overflows, which
     * will be caused if the distance is too long.
     *
     * @param o the other point
     * @return the distance
     */
    public double distance(final @NonNull Point o) {
        return Math.sqrt(MathUtils.square(x - o.x) + MathUtils.square(y - o.y));
    }

    /**
     * Get the squared distance between this point and another.
     *
     * @param o the other point
     * @return the distance
     */
    public double distanceSquared(final @NonNull Point o) {
        return MathUtils.square(x - o.x) + MathUtils.square(y - o.y);
    }

    /**
     * Gets the angle between this point and another in radians.
     *
     * @param other the other point
     * @return angle in radians
     */
    public float angle(final @NonNull Point other) {
        double dot = dot(other) / (length() * other.length());

        return (float) Math.acos(dot);
    }

    /**
     * Sets this point to the midpoint between this point and another.
     *
     * @param other the other point
     * @return this same point (now a midpoint)
     */
    @NonNull
    public Point midpoint(final @NonNull Point other) {
        x = (x + other.x) / 2;
        y = (y + other.y) / 2;
        return this;
    }

    /**
     * Gets a new midpoint point between this point and another.
     *
     * @param other the other point
     * @return a new midpoint point
     */
    @NonNull
    public Point getMidpoint(final @NonNull Point other) {
        final int x = (this.x + other.x) / 2;
        final int y = (this.y + other.y) / 2;
        return new Point(x, y);
    }

    /**
     * Performs scalar multiplication, multiplying all components with a
     * scalar.
     *
     * @param m the factor
     * @return the same point
     */
    @NonNull
    public Point multiply(final int m) {
        x *= m;
        y *= m;
        return this;
    }

    /**
     * Performs scalar multiplication, multiplying all components with a
     * scalar.
     *
     * @param m the factor
     * @return the same point
     */
    @NonNull
    public Point multiply(final double m) {
        x *= m;
        y *= m;
        return this;
    }

    /**
     * Performs scalar multiplication, multiplying all components with a
     * scalar.
     *
     * @param m the factor
     * @return the same point
     */
    @NonNull
    public Point multiply(final float m) {
        x *= m;
        y *= m;
        return this;
    }

    /**
     * Calculates the dot product of this point with another.  The dot
     * product is defined as x1*x2+y1*y2. The returned value is a scalar.
     *
     * @param other the other point
     * @return dot product
     */
    public double dot(final @NonNull Point other) {
        return x * other.x + y * other.y;
    }

    /**
     * Converts this point to a unit point (a point with length of 1).
     *
     * @return the same point
     */
    @NonNull
    public Point normalize() {
        final double length = length();

        x /= length;
        y /= length;

        return this;
    }

    /**
     * Zero this point's components.
     *
     * @return the same point
     */
    @NonNull
    public Point zero() {
        x = 0;
        y = 0;
        return this;
    }

    /**
     * Returns whether this point is in an axis-aligned bounding square.
     *
     * <p>The minimum and maximum points given must be truly the minimum and
     * maximum X and Y components.
     *
     * @param min minimum point
     * @param max maximum point
     * @return whether this point is in the AABB
     */
    public boolean isInAABB(final @NonNull Point min, final @NonNull Point max) {
        return x >= min.x && x <= max.x && y >= min.y && y <= max.y;
    }

    /**
     * Returns whether this point is within a circle.
     *
     * @param origin circle origin
     * @param radius circle radius
     * @return whether this point is in the circle
     */
    public boolean isInCircle(final @NonNull Point origin, final double radius) {
        return MathUtils.square(origin.x - x) + MathUtils.square(origin.y - y) <= MathUtils.square(radius);
    }

    /**
     * Computes over the current X to update it.
     *
     * @param operator to apply to the X axis
     * @return this object reference
     */
    @NonNull
    public Point computeX(final @NonNull IntUnaryOperator operator) {
        x = operator.applyAsInt(x);
        return this;
    }

    /**
     * Computes over the current Y to update it.
     *
     * @param operator to apply to the Y axis
     * @return this object reference
     */
    @NonNull
    public Point computeY(final @NonNull IntUnaryOperator operator) {
        y = operator.applyAsInt(y);
        return this;
    }

    /**
     * Gets the X component.
     *
     * @return the X component
     */
    public int getX() {
        return x;
    }

    /**
     * Set the X component.
     *
     * @param x the new X component
     * @return this point
     */
    @NonNull
    public Point setX(final int x) {
        this.x = x;
        return this;
    }

    /**
     * Gets the Y component.
     *
     * @return the Y component
     */
    public int getY() {
        return y;
    }

    /**
     * Set the Y component.
     *
     * @param y the new Y component
     * @return this point
     */
    @NonNull
    public Point setY(final int y) {
        this.y = y;
        return this;
    }

    // Comparable

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final @NonNull Point point) {
        final int res = Integer.compare(x, point.x);

        return res != 0 ? res : Integer.compare(y, point.y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = 373;
        result = 37 * result + x;
        result = 37 * result + y;
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        final Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point clone() {
        try {
            return (Point) super.clone();
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("x", x)
                .add("y", y)
                .toString();
    }

    /**
     * Gets the minimum components of two points.
     *
     * @param p1 the first point
     * @param p2 the second point
     * @return minimum
     */
    @NonNull
    public static Point getMinimum(final @NonNull Point p1, final @NonNull Point p2) {
        return new Point(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y));
    }

    /**
     * Gets the maximum components of two points.
     *
     * @param p1 the first point
     * @param p2 the second point
     * @return maximum
     */
    @NonNull
    public static Point getMaximum(final @NonNull Point p1, final @NonNull Point p2) {
        return new Point(Math.max(p1.x, p2.x), Math.max(p1.y, p2.y));
    }
}
