package com.github.jiizuz.algorithmanalysis.algorithm.graph;

import lombok.NonNull;

import java.util.Optional;
import java.util.Set;

/**
 * In mathematics, and more specifically in graph theory, a <b>vertex</b>
 * (plural <b>vertices</b>) or <b>node</b> is the fundamental unit of
 * which graphs are formed: an undirected graph consists of a set of
 * vertices and a set of edges (unordered pairs of vertices), while a
 * directed graph consists of a set of vertices and a set of arcs (ordered
 * pairs of vertices). In a diagram of a graph, a vertex is usually
 * represented by a circle with a label, and an edge is represented by a
 * line or arrow extending from one vertex to another.
 * <p>
 * From the point of view of graph theory, vertices are treated as
 * featureless and indivisible objects, although they may have additional
 * structure depending on the application from which the graph arises;
 * for instance, a semantic network is a graph in which the vertices
 * represent concepts or classes of objects.
 * <p>
 * The two vertices forming an edge are said to be the endpoints of this
 * edge, and the edge is said to be incident to the vertices. A vertex
 * <i>w</i> is said to be adjacent to another vertex <i>v</i> if the graph
 * contains an edge (<i>v,w</i>). The neighborhood of a vertex <i>v</i> is
 * an induced subgraph of the graph, formed by all vertices adjacent to
 * <i>v</i>.
 *
 * <p>A {@link Node} is {@link Comparable} based on its {@code Id}.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @implNote This class is not thread-safe.
 * @see java.lang.Comparable
 * @see java.lang.Iterable
 * @see java.lang.Cloneable
 * @since 1.6
 */
public interface Node extends Comparable<Node>, Iterable<Link>, Cloneable {

    /**
     * Retrieve the unique-Id of this {@link Node}.
     *
     * <p>A {@link Node} must be unique based on this {@code Id}.
     *
     * @return the unique-Id of this {@link Node}
     * @apiNote Can be negative.
     */
    int getId();

    /**
     * The degree of a node, denoted 𝛿(v) in a graph is the number of
     * links incident to it.
     *
     * @return the degree of this {@link Node}
     */
    int getDegree();

    /**
     * An isolated node is a node with degree zero; that is, a node
     * that is not an endpoint of any edge
     *
     * @return {@code true} if this node is an isolated node
     */
    boolean isIsolated();

    /**
     * A leaf vertex (also pendant vertex) is a vertex with degree one.
     *
     * @return {@code true} if this node is a leaf node
     */
    boolean isLeaf();

    /**
     * Retrieve the current links attached to this {@link Node}.
     *
     * @return the links attached to this {@link Node}
     * @implNote The returned {@link Set} <b>CAN</b> be <i>immutable</i>.
     */
    @NonNull
    Set<? extends Link> getLinks();

    /**
     * Returns whether this {@link Node} has a {@link Link} with the
     * specified {@code nodeId}.
     *
     * @return {@code true} if this {@link Node} has a {@link Link} with
     * the node with specified {@code Id}, {@code false} otherwise.
     */
    boolean hasLink(int nodeId);

    /**
     * Retrieve the {@link Link} in this {@link Node} with the specified
     * {@code nodeId} if available, otherwise, an empty is returned.
     *
     * @param nodeId of the {@link Node} to search and return with the link
     * @implNote The returned {@link Link} can be <i>immutable</i>.
     */
    @NonNull
    Optional<Link> getLink(int nodeId);

    /**
     * Retrieve the {@link Link} in this {@link Node} with the specified
     * {@code nodeId} if available, otherwise throws an exception.
     *
     * @param nodeId of the {@link Node} to search and return with the link
     * @throws IllegalArgumentException if this {@link Node} does not have
     *                                  a {@link Link} with the {@link Node}
     *                                  with the specified Id.
     * @implNote The returned {@link Link} can be <i>immutable</i>.
     */
    @NonNull
    Link getLinkNow(int nodeId) throws IllegalArgumentException;

    /**
     * Removes the {@link Link} with the specified {@link Node} if exists.
     *
     * @param node to remove the link with this {@link Node}
     * @return the {@link Link} removed (if present)
     * @throws NullPointerException if the node is {@code null}
     */
    @NonNull
    Optional<Link> removeLink(@NonNull Node node);

    /**
     * Removes the {@link Link} with the {@link Node} with the specified {@code Id}.
     *
     * @param nodeId of the node to cut link with
     * @return the {@link Link} removed (if present)
     */
    @NonNull
    Optional<Link> removeLink(int nodeId);

    /**
     * Removes the {@link Link} with the specified {@link Node}.
     *
     * @param node to remove the link with this {@link Node}
     * @return the {@link Link} removed
     * @throws NullPointerException     if the node is {@code null}
     * @throws IllegalArgumentException if there is no link with the
     *                                  specified {@link Node}
     */
    @NonNull
    Link removeLinkNow(@NonNull Node node) throws IllegalArgumentException;

    /**
     * Removes the {@link Link} with the {@link Node} with the specified {@code Id}.
     *
     * @param nodeId of the node to cut link with
     * @return the {@link Link} removed
     * @throws IllegalArgumentException if there is no link with the
     *                                  specified {@link Node}
     */
    @NonNull
    Link removeLinkNow(int nodeId) throws IllegalArgumentException;
}
