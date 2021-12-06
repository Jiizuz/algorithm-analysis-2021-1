package com.github.jiizuz.algorithmanalysis.algorithm.graph;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import lombok.Builder;
import lombok.NonNull;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * {@link Node} that promises to be fully <i>immutable</i>.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.algorithm.graph.Node
 * @since 1.6
 */
@Beta
public final class ImmutableNode extends AbstractMapNode {

    /**
     * Id of the node.
     */
    private final int id;

    /**
     * Links of the node by the target node Id. Main structure for link management.
     */
    @NonNull
    private final Map<Integer, Link> linkMap;

    /**
     * Constructs a new {@link ImmutableNode} with the specified Id and links.
     *
     * @param id    unique-Id of the {@link Node}
     * @param links links of the {@link Node}
     * @throws NullPointerException if the links are {@code null}
     */
    @Builder
    private ImmutableNode(final int id, final @NonNull Set<? extends ImmutableLink> links) {
        this.id = id;
        this.linkMap = Int2ObjectMaps.unmodifiable(links.stream()
                .collect(Collectors.<Link, Integer, Link, Int2ObjectMap<Link>>toMap(
                        link -> link.getTarget().getId(), link -> link,
                        (u, v) -> { // this may never happen
                            throw new IllegalStateException(String.format("Duplicate node %s", u));
                        }, Int2ObjectOpenHashMap::new)));
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
     * Guaranteed to throw an exception and leave the node unmodified.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @CanIgnoreReturnValue
    @Deprecated
    @Override
    @DoNotCall("Always throws UnsupportedOperationException")
    public boolean addLink(final @NonNull Link link) {
        throw new UnsupportedOperationException();
    }

    /**
     * Guaranteed to throw an exception and leave the node unmodified.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @CanIgnoreReturnValue
    @Deprecated
    @NonNull
    @Override
    @DoNotCall("Always throws UnsupportedOperationException")
    public Optional<Link> removeLink(final int nodeId) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    protected Map<Integer, Link> getLinkMap() {
        return linkMap;
    }
}
