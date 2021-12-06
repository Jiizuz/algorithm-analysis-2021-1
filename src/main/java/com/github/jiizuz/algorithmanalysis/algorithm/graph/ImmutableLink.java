package com.github.jiizuz.algorithmanalysis.algorithm.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.errorprone.annotations.DoNotCall;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * {@link Link} that promises to be <i>immutable</i>.
 *
 * <p>This implementation will also cast to immutable the {@link Node}.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.algorithm.graph.Link
 * @since 1.6
 */
@Beta
@RequiredArgsConstructor
public final class ImmutableLink implements Link {

    /**
     * Target {@link Node} of the {@link Link}.
     */
    @NonNull
    private final Node target;

    /**
     * Label of the {@link Link}.
     */
    private final double label;

    /**
     * Creates an {@link ImmutableLink} based on a copy from the specified
     * {@link Link} and returns the generated {@link ImmutableLink}.
     *
     * <p>If the link is already a {@link ImmutableLink}, the same instance
     * will be returned.
     *
     * @param link to copy from the data of the new link
     * @return the new generated {@link ImmutableLink}
     * @throws IllegalArgumentException if the target node of the link is
     *                                  not of type {@link ImmutableNode}
     */
    @NonNull
    public static ImmutableLink copyOf(final @NonNull Link link) {
        if (link instanceof ImmutableLink) {
            return (ImmutableLink) link;
        }
        checkArgument(link.getTarget() instanceof ImmutableNode, "the link must have an immutable node");

        return new ImmutableLink(link.getTarget(), link.getLabel());
    }

    /**
     * Creates a new {@link ImmutableLink} with the specified target and label.
     *
     * @param target node target of the link
     * @param label  of the link
     * @return the new generated {@link ImmutableLink}
     * @throws NullPointerException if the node is {@code null}
     */
    @NonNull
    public static ImmutableLink of(final @NonNull ImmutableNode target, final double label) {
        return new ImmutableLink(target, label);
    }

    /**
     * Creates a new {@link ImmutableLink} with the specified target and no label.
     *
     * @param target node target of the link
     * @return the new generated {@link ImmutableLink}
     * @throws NullPointerException if the node is {@code null}
     */
    @NonNull
    public static ImmutableLink of(final @NonNull ImmutableNode target) {
        return of(target, 0.0D);
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
     * Guaranteed to throw an exception and leave the link unmodified.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @Deprecated
    @Override
    @DoNotCall("Always throws UnsupportedOperationException")
    public void setTarget(final @NonNull Node node) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getLabel() {
        return label;
    }

    /**
     * Guaranteed to throw an exception and leave the link unmodified.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @Deprecated
    @Override
    @DoNotCall("Always throws UnsupportedOperationException")
    public void setLabel(final double label) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public ImmutableLink clone() {
        return this; // this class is immutable
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
        return Double.compare(that.getLabel(), label) == 0 && Objects.equal(target, that.getTarget());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("target", target.getId())
                .add("label", label)
                .toString();
    }
}
