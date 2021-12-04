package com.github.jiizuz.algorithmanalysis.algorithm.graph;

import lombok.NonNull;

/**
 * In the mathematical discipline of graph theory, the line graph
 * of an undirected graph G is another graph L(G) that represents
 * the adjacencies between edges of G. L(G) is constructed in the
 * following way: for each edge in G, make a vertex in L(G);  for
 * every two edges in G that have a vertex in common, make an edge
 * between their corresponding vertices in L(G).
 * <p>
 * The name line graph comes from a paper by Harary & Norman (1960)
 * although both Whitney (1932) and Krausz (1943) used the construction
 * before this. Other terms used for the line graph include the
 * covering graph, the derivative, the edge-to-vertex dual, the conjugate,
 * the representative graph, and the ϑ-obrazom, as well as the edge
 * graph, the interchange graph, the adjoint graph, and the derived graph.
 * <p>
 * Hassler Whitney (1932)  proved that with one exceptional case the
 * structure of a connected graph G can be recovered completely from
 * its line graph.    Many other properties of line graphs follow by
 * translating  the properties of the underlying graph from vertices
 * into edges, and by Whitney's theorem the same translation can also
 * be done in the other direction. Line graphs are claw-free, and the
 * line graphs of bipartite graphs are perfect. Line graphs are
 * characterized by nine forbidden subgraphs and can be recognized in
 * linear time.
 * <p>
 * Various extensions of the concept of a line graph have been studied,
 * including line graphs of line graphs, line graphs of multigraphs,
 * line graphs of hypergraphs, and line graphs of weighted graphs.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @implNote This class is not Thread-Safe.
 * @see java.lang.Cloneable
 * @since 1.6
 */
public interface Link extends Cloneable {

    /**
     * Retrieve the target {@link Node} of this {@link Link}.
     *
     * @return the target {@link Node} of this {@link Link}
     */
    @NonNull
    Node getTarget();

    /**
     * Update the target {@link Node} of this {@link Link}.
     *
     * @param node target of this {@link Link}
     * @throws NullPointerException if the node is {@code null}
     */
    void setTarget(@NonNull Node node);

    /**
     * Retrieve the assigned label to this {@link Link}.
     *
     * <p>a.k.a. weight.
     *
     * @return the label of this {@link Link}.
     * @implSpec The default label is {@code 0.0}.
     */
    double getLabel();

    /**
     * Update the label of this {@link Link} to the specified one.
     *
     * @param label to assign to this {@link Link}
     */
    void setLabel(double label);
}
