package com.github.jiizuz.algorithmanalysis.algorithm.graph;

import lombok.NonNull;

import java.util.Map;

/**
 * {@link Node} that is managed by a {@link Map} identifying the
 * links by the target node-Id.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.algorithm.graph.AbstractNode
 * @since 1.6
 */
public abstract class AbstractMapNode extends AbstractNode {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasLink(final int nodeId) {
        return getLinkMap().containsKey(nodeId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addLink(final @NonNull Link link) {
        return getLinkMap().put(link.getTarget().getId(), link) != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Link getLinkByNodeId(final int id) {
        return getLinkMap().get(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Link removeLinkByNodeId(final int id) {
        return getLinkMap().remove(id);
    }

    /**
     * Retrieve the map to use in the management.
     *
     * @return the map to use in the management of the node
     */
    @NonNull
    protected abstract Map<Integer, Link> getLinkMap();
}
