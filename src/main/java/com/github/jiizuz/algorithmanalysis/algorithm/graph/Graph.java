package com.github.jiizuz.algorithmanalysis.algorithm.graph;

import lombok.NonNull;

import java.util.Optional;
import java.util.Set;

/**
 * In mathematics, and more specifically in <b>graph</b> theory, a graph
 * is a structure amounting to a set of objects in which some pairs of
 * the objects are in some sense "related". The objects correspond to
 * mathematical abstractions called vertices (also called nodes or points)
 * and each of the related pairs of vertices is called an edge (also
 * called link or line). Typically, a graph is depicted in diagrammatic
 * form as a set of dots or circles for the vertices, joined by lines or
 * curves for the edges. Graphs are one of the objects of study in discrete
 * mathematics.
 * <p>
 * The edges may be directed or undirected. For example, if the vertices
 * represent people at a party, and there is an edge between two people
 * if they shake hands, then this graph is undirected because any person
 * A can shake hands with a person B only if B also shakes hands with A.
 * In contrast, if any edge from a person A to a person B corresponds to
 * A owes money to B, then this graph is directed, because owing money is
 * not necessarily reciprocated. The former type of graph is called an
 * <b>undirected graph</b> while the latter type of graph is called a
 * <b>directed graph</b>.
 * <p>
 * Graphs are the basic subject studied by graph theory. The word "graph"
 * was first used in this sense by J. J. Sylvester in 1878 in a direct
 * relation between mathematics and chemical structure (what he called
 * chemico-graphical image).
 * <p>
 * Note that a {@link Graph} is {@link Iterable}, when an <i>iteration</i>
 * is called, all the nodes of the {@link Graph} will be iterated.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @implNote This class is not Thread-Safe.
 * @see java.lang.Iterable
 * @see java.lang.Cloneable
 * @since 1.6
 */
public interface Graph extends Iterable<Node>, Cloneable {

    /**
     * The <i>order</i> of a graph is its number of vertices |V|.
     *
     * <p>In a graph of order n, the maximum degree of each vertex
     * is n − 1 (or n if loops are allowed), and the maximum number
     * of edges is n(n − 1)/2 (or n(n + 1)/2 if loops are allowed).
     *
     * @return the order of this {@link Graph}
     */
    int getOrder();

    /**
     * The <i>size</i> of a graph is its number of edges |E|.
     *
     * @return the size of this {@link Graph}
     */
    int getSize();

    /**
     * An empty graph is a graph that has an empty set of vertices (and
     * thus an empty set of edges).
     *
     * @return {@code true} if this {@link Graph} is empty
     */
    boolean isEmpty();

    /**
     * Retrieve the nodes of this {@link Graph}.
     *
     * @return the {@link Node}s of the {@link Graph}.
     * @implNote The {@link Set} <b>CAN</b> be <i>immutable</i>.
     */
    @NonNull
    Set<Node> getNodes();

    /**
     * Returns whether this {@link Graph} has a {@link Node} with the
     * specified {@code nodeId}.
     *
     * @return {@code true} if this {@link Graph} has a {@link Node} with
     * the specified {@code Id}, {@code false} otherwise.
     */
    boolean hasNode(int nodeId);

    /**
     * Retrieve the {@link Node} in this {@link Graph} with the specified
     * {@code nodeId} if available, otherwise, an empty is returned.
     *
     * @param nodeId of the {@link Node} to search and return
     * @implNote The returned {@link Node} can be <i>immutable</i>.
     */
    @NonNull
    Optional<Node> getNode(int nodeId);

    /**
     * Retrieve the {@link Node} in this {@link Graph} with the specified
     * {@code nodeId} if available, otherwise throws an exception.
     *
     * @param nodeId of the {@link Node} to search and return
     * @throws IllegalArgumentException if this {@link Graph} does not have
     *                                  a {@link Node} with the specified
     *                                  Id.
     * @implNote The returned {@link Node} can be <i>immutable</i>.
     */
    @NonNull
    Node getNodeNow(int nodeId) throws IllegalArgumentException;

    /**
     * Adds the specified {@link Node} to this {@link Graph}.
     *
     * @param node to add to the {@link Graph}
     * @throws NullPointerException if the node is {@code null}
     */
    void addNode(@NonNull Node node);

    /**
     * Removes the specified {@link Node} from this {@link Graph}.
     *
     * @param node to remove from this {@link Graph}
     * @return the {@link Node} that was removed from this {@link Graph}.
     * @throws NullPointerException if the node is {@code null}
     */
    @NonNull
    Optional<Node> removeNode(@NonNull Node node);

    /**
     * Removes the {@link Node} with the specified {@code Id}.
     *
     * @param nodeId of the node to remove from this {@link Graph}
     * @return the {@link Node} that was removed from this {@link Graph}.
     */
    @NonNull
    Optional<Node> removeNode(int nodeId);

    /**
     * Removes the {@link Node} with the specified {@code Id}.
     *
     * @param nodeId of the node to remove from this {@link Graph}
     * @return the {@link Node} that was removed from this {@link Graph}.
     * @throws IllegalArgumentException if there is no link with the
     *                                  specified {@link Node}
     */
    @NonNull
    Node removeNodeNow(int nodeId) throws IllegalArgumentException;
}
