package com.github.jiizuz.algorithmanalysis.algorithm.graph;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import lombok.NonNull;

/**
 * {@link Link} that allows changes, i.e. is mutable.
 *
 * <p>This implementation does not allow in any time a null target.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.algorithm.graph.Link
 * @since 1.6
 */
public class OpenLink implements Link {

    /**
     * Target {@link Node} of the {@link Link}.
     */
    @NonNull
    private Node target;

    /**
     * Label of the {@link Link}.
     */
    private double label = 0.0D;

    /**
     * Creates a new {@link OpenLink} with the specified target node and the label.
     *
     * @param target node target of the {@link Link}
     * @param label  of the new created {@link Link}
     * @throws NullPointerException if the target node is {@code null}
     */
    public OpenLink(final @NonNull Node target, final double label) {
        this.target = target;
        this.label = label;
    }

    /**
     * Creates a new {@link OpenLink} with the specified target node and no label.
     *
     * @param target node target of the {@link Link}
     * @throws NullPointerException if the target node is {@code null}
     */
    public OpenLink(final @NonNull Node target) {
        this.target = target;
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public Node getTarget() {
        return target;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTarget(final @NonNull Node node) {
        target = node;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getLabel() {
        return label;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLabel(final double linkLabel) {
        label = linkLabel;
    }

    /**
     * Creates a shallow clone of this {@link Link}. The target
     * node is not cloned.
     *
     * @return the shallow cloned {@link Link}
     */
    @Override
    public OpenLink clone() {
        try {
            return (OpenLink) super.clone();
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(target, label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Link)) return false;
        final Link that = (Link) o;
        return Objects.equal(target, that.getTarget()) && Double.compare(that.getLabel(), label) == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("target", target)
                .add("label", label)
                .toString();
    }
}
