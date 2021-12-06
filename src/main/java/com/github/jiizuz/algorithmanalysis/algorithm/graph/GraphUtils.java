package com.github.jiizuz.algorithmanalysis.algorithm.graph;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.Comparator;
import java.util.List;

/**
 * {@link UtilityClass} with methods related with a {@link Graph}.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @since 1.6
 */
@UtilityClass
public class GraphUtils {

    /**
     * Generates an <i>adjacency matrix</i> based on the nodes and links
     * of the specified {@link Graph} and returns the result in form of
     * a bi-dimensional array of {@code doubles}.
     *
     * @param graph to generate from the adjacency matrix
     * @return a bi-dimensional matrix with the adjacency matrix
     * @throws NullPointerException if the graph is {@code null}
     */
    public double @NonNull [] @NonNull [] getAdjacencyMatrix(final @NonNull Graph graph) {
        final double[][] matrix = new double[graph.getOrder()][graph.getOrder()];

        // utility list to randomly access nodes
        final List<Node> nodes = new ObjectArrayList<>(graph.iterator());
        nodes.sort(Comparator.comparingInt(Node::getId));

        for (int i = 0; i < nodes.size(); i++) {
            final Node node = nodes.get(i); // row node

            for (int j = 0; j < nodes.size(); j++) {
                final Node other = nodes.get(j); // column node

                matrix[i][j] = node.getLink(other.getId()).map(Link::getLabel).orElse(0.0D);
            }
        }

        return matrix;
    }
}
