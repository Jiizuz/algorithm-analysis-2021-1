package com.github.jiizuz.algorithmanalysis.algorithm.graph;

import com.google.common.base.MoreObjects;
import lombok.NonNull;

import java.util.Iterator;
import java.util.Optional;

/**
 * This class provides a skeletal implementation of the {@link Node}
 * interface to minimize the effort required to implement this interface.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.algorithm.graph.Node
 * @since 1.6
 */
public abstract class AbstractNode implements Node {

    /**
     * Cache the hash code for the node
     */
    private int hash; // Default to 0

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDegree() {
        return getLinks().size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isIsolated() {
        return getDegree() == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLeaf() {
        return getDegree() == 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasLink(final int nodeId) {
        return getLink(nodeId).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public Optional<Link> getLink(final int nodeId) {
        return Optional.ofNullable(getLinkByNodeId(nodeId));
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public Link getLinkNow(final int nodeId) throws IllegalArgumentException {
        return getLink(nodeId).orElseThrow(() -> new IllegalArgumentException("No link with Id " + nodeId));
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public Optional<Link> removeLink(final @NonNull Node node) {
        return removeLink(node.getId());
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public Optional<Link> removeLink(final int nodeId) {
        return Optional.ofNullable(removeLinkByNodeId(nodeId));
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public Link removeLinkNow(final @NonNull Node node) throws IllegalArgumentException {
        return removeLinkNow(node.getId());
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public Link removeLinkNow(final int nodeId) throws IllegalArgumentException {
        return removeLink(nodeId).orElseThrow(() -> new IllegalArgumentException("No link with Id " + nodeId));
    }

    /**
     * Retrieve the {@link Link} with the target node with the specified Id.
     * Can be {@code null}.
     *
     * @param id of the node with the {@link Link} requested
     * @return the {@link Link} with the target node with the specified Id
     */
    protected abstract Link getLinkByNodeId(int id);

    /**
     * Remove the {@link Link} with the target node with the specified Id.
     * Can be {@code null}.
     *
     * @param id of the node with the {@link Link} requested
     * @return the {@link Link} with the target node with the specified removed
     */
    protected abstract Link removeLinkByNodeId(int id);

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<Link> iterator() {
        return getLinks().iterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final @NonNull Node node) {
        return Integer.compare(getId(), node.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int h = hash;
        if (h == 0) { // since the Id of a Node does never change, it can be cached
            long input = 2L * getId() + 1;
            long unsignedValue = input - Integer.MIN_VALUE;
            long unsignedIntMax = 4294967296L;
            // Knuth's multiplicative method
            long unsignedHashValue = unsignedValue * 2654435761L % unsignedIntMax;
            // convert back to signed integer
            h = (int) (unsignedHashValue + Integer.MIN_VALUE);

            hash = h;
        }
        return h;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        final Node that = (Node) o;
        return getId() == that.getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("degree", getDegree())
                .add("isolated", isIsolated())
                .add("leaf", isLeaf())
                .toString();
    }
}
