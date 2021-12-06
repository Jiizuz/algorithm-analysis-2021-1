package com.github.jiizuz.algorithmanalysis.algorithm.graph;

import com.google.common.base.MoreObjects;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import lombok.NonNull;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * {@link Graph} that uses an internal {@link Map} and a {@link Set}
 * to cache and keep track of the {@link Node}s added.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.algorithm.graph.Graph
 * @since 1.6
 */
public class FastGraph implements Graph {

    /**
     * Current nodes in the {@link Graph} by their Id.
     */
    @NonNull
    private Int2ObjectOpenHashMap<Node> nodeMap;

    /**
     * Creates a new {@link FastGraph} with the specified initial nodes.
     *
     * @param nodes initial nodes of the {@link Graph}
     * @throws NullPointerException if the nodes are {@code null}
     */
    public FastGraph(final @NonNull Set<? extends Node> nodes) {
        nodeMap = asMapById(nodes);
    }

    /**
     * Creates a new {@link FastGraph} with no initial nodes.
     */
    public FastGraph() {
        nodeMap = new Int2ObjectOpenHashMap<>(16);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getOrder() {
        return nodeMap.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSize() {
        return nodeMap.values().stream()
                .mapToInt(Node::getDegree)
                .sum();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return getOrder() == 0;
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public Set<Node> getNodes() {
        return ObjectSets.unmodifiable(new ObjectOpenHashSet<>(nodeMap.values()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNode(final int nodeId) {
        return nodeMap.containsKey(nodeId);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public Optional<Node> getNode(final int nodeId) {
        return Optional.ofNullable(getNodeById(nodeId));
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public Node getNodeNow(final int nodeId) throws IllegalArgumentException {
        return getNode(nodeId).orElseThrow(() -> new IllegalArgumentException("No node with Id " + nodeId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addNode(final @NonNull Node node) {
        putNode(node);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public Optional<Node> removeNode(final @NonNull Node node) {
        return removeNode(node.getId());
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public Optional<Node> removeNode(final int nodeId) {
        return Optional.ofNullable(removeNodeById(nodeId));
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public Node removeNodeNow(final int nodeId) throws IllegalArgumentException {
        return removeNode(nodeId).orElseThrow(() -> new IllegalArgumentException("No node with Id " + nodeId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<Node> iterator() {
        return nodeMap.values().iterator();
    }

    /**
     * Creates a shallow clone of this {@link Graph}. The nodes itself
     * are not cloned, but the container are cloned.
     *
     * @return the shallow cloned {@link FastGraph}
     */
    @Override
    public FastGraph clone() {
        final FastGraph g;
        try {
            g = (FastGraph) super.clone();
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
        g.nodeMap = nodeMap.clone();
        return g;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("order", getOrder())
                .add("size", getSize())
                .add("empty", isEmpty())
                .add("nodes", nodeMap)
                .toString();
    }

    // Nodes CRUD

    /**
     * Puts the specified node in the graph, if a node with the same Id
     * exists, the previous one will be overridden with the specified one.
     *
     * @param node to put in the graph
     */
    private void putNode(final @NonNull Node node) {
        nodeMap.put(node.getId(), node);
    }

    /**
     * Retrieve the {@link Node} with the specified Id if present in the graph.
     *
     * @param id of the node to retrieve
     * @return the found {@link Node} in the graph if present
     */
    private Node getNodeById(final int id) {
        return nodeMap.get(id);
    }

    /**
     * Removes the node with the specified Id from the graph.
     *
     * @param id of the node to remove
     */
    private Node removeNodeById(final int id) {
        return nodeMap.remove(id);
    }

    // util

    /**
     * Converts the specified {@link Set} into a {@link Map} of {@link Node}s
     * identified by the {@code Id} of the {@link Node}.
     *
     * @param nodes nodes to convert into a {@link Map}
     * @return the generated {@link Map}
     */
    @NonNull
    private Int2ObjectOpenHashMap<Node> asMapById(final @NonNull Set<? extends Node> nodes) {
        final Int2ObjectOpenHashMap<Node> map = new Int2ObjectOpenHashMap<>(nodes.size());

        nodes.forEach(node -> map.put(node.getId(), node));

        return map;
    }
}
