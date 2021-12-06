package com.github.jiizuz.algorithmanalysis.algorithm.graph;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import lombok.Builder;
import lombok.NonNull;

import java.util.Map;
import java.util.Set;

/**
 * {@link AbstractMapNode} that uses a fast structure to manage
 * the links.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.algorithm.graph.AbstractMapNode
 * @since 1.6
 */
public class FastNode extends AbstractMapNode {

    /**
     * Id of the node.
     */
    private final int id;

    /**
     * Links of the node by the target node Id. Main structure for link management.
     */
    @NonNull
    private Int2ObjectOpenHashMap<Link> linkMap;

    /**
     * Constructs a new {@link FastNode} with the specified Id and links.
     *
     * @param nodeId unique-Id of the {@link Node}
     * @param links  links of the {@link Node}
     * @throws NullPointerException if the links are {@code null}
     */
    @Builder
    public FastNode(final int nodeId, final @NonNull Set<? extends Link> links) {
        id = nodeId;
        linkMap = asMapByNodeId(links);
    }

    /**
     * Constructs a new {@link FastNode} with the specified Id and no links.
     *
     * @param nodeId unique-Id of the {@link Node}
     */
    public FastNode(final int nodeId) {
        id = nodeId;
        linkMap = new Int2ObjectOpenHashMap<>(16);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public Set<Link> getLinks() {
        return ObjectSets.unmodifiable(new ObjectOpenHashSet<>(getLinkMap().values()));
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    protected Map<Integer, Link> getLinkMap() {
        return linkMap;
    }

    /**
     * Creates a shallow clone of this {@link Node}. The links itself
     * are not cloned, but the containers are cloned.
     *
     * @return the shallow cloned {@link FastNode}
     */
    @Override
    public FastNode clone() {
        final FastNode n;
        try {
            n = (FastNode) super.clone();
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
        n.linkMap = linkMap.clone();
        return n;
    }

    // util

    /**
     * Converts the specified {@link Set} into a {@link Map} of {@link Link}s
     * identified by the {@code Id} of the {@link Node} of the {@link Link}.
     *
     * @param links links to convert into a {@link Map}
     * @return the generated {@link Map}
     */
    @NonNull
    private Int2ObjectOpenHashMap<Link> asMapByNodeId(final @NonNull Set<? extends Link> links) {
        final Int2ObjectOpenHashMap<Link> map = new Int2ObjectOpenHashMap<>(linkMap.size());

        links.forEach(link -> map.put(link.getTarget().getId(), link));

        return map;
    }
}
