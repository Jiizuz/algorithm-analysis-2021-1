package com.github.jiizuz.algorithmanalysis.algorithm.tsp;

import com.github.jiizuz.algorithmanalysis.algorithm.graph.*;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Main class of the project to initialize and run.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @since 1.6
 */
@UtilityClass
public class TSPMain {

    /**
     * {@link Random} utility to generate pseudo-random integers.
     */
    private final Random RANDOM = new Random();

    /**
     * Runs the entry point of the program.
     *
     * @param args specified in the console
     */
    public void main(final String[] args) {
        final Graph graph = createCompleteGraph(4);

        final TravellingSalesmanProblem tsp = new CompleteTravellingSalesmanProblem();

        final List<Node> path = tsp.findPath(graph, 0);

        final StringBuilder bob = new StringBuilder();
        appendNodesLinks(graph, bob);
        bob.append('\n');
        appendAdjacencyMatrix(graph, bob);
        bob.append('\n');
        appendNodePath(path, bob);
        System.out.println(bob);
    }

    // result util

    /**
     * Appends to the specified builder a fancy linked list of nodes based
     * on the nodes of the specified {@link Graph} and the label sum.
     * The schema is designed to work with any range of node Ids and links
     * labels.
     * <p>
     * n5 -> n7 -> n8 -> n6 -> n5 160.00
     *
     * <p>Example: (order 4 complete graph)<pre>
     * n0 -> n1 (0.0 + 22.0 = 22.0) -> n3 (22.0 + 60.0 = 82.0) -> n2 (82.0 + 39.0 = 121.0) -> n0 (121.0 + 48.0 = 169.0) [169.0]
     * </pre>
     *
     * @param path list of nodes that creates the path
     * @param bob  to append over the result
     * @throws NullPointerException if either the path or bob is {@code null}
     */
    private void appendNodePath(final @NonNull List<? extends Node> path, final @NonNull StringBuilder bob) {
        double accumulation = 0.0D;
        for (int i = 0; i < path.size(); i++) {
            final Node curr = path.get(i);

            bob.append('n').append(curr.getId());

            if (i != 0) {
                final double label = path.get(i - 1).getLinkNow(curr.getId()).getLabel();
                bob.append(" (").append(String.format("%.1f", accumulation))
                        .append(" + ").append(String.format("%.1f", label)).append(" = ");
                accumulation += label;
                bob.append(String.format("%.1f", accumulation)).append(")");
            }

            if (i != path.size() - 1) {
                bob.append(" -> ");
            }
        }
        bob.append(String.format(" [%.1f]\n", accumulation));
    }

    /**
     * Appends to the specified builder a fancy linked list of nodes based
     * on the nodes of the specified {@link Graph} and the label sum.
     * The schema is designed to work with any range of node Ids and links
     * labels.
     *
     * <p>Example: (order 4 complete graph)<pre>
     * n5 -> n7 -> n8 -> n6 -> n5 160.00
     * </pre>
     *
     * @param path list of nodes that creates the path
     * @param bob  to append over the result
     * @throws NullPointerException if either the path or bob is {@code null}
     */
    private void appendNodePathSimplified(final @NonNull List<? extends Node> path, final @NonNull StringBuilder bob) {
        double accumulation = 0.0D;
        for (int i = 0; i < path.size(); i++) {
            final Node curr = path.get(i);

            bob.append('n').append(curr.getId());

            if (i != 0) {
                accumulation += path.get(i - 1).getLinkNow(curr.getId()).getLabel();
            }

            if (i != path.size() - 1) {
                bob.append(" -> ");
            }
        }
        bob.append(String.format(" %.1f\n", accumulation));
    }

    /**
     * Appends to the specified builder a fancy linked list of nodes based
     * on the nodes and links of the specified {@link Graph}.
     * The schema is designed to work with any range of node Ids and links
     * labels, but over ranges of Node Id from 0 to 99 or link labels over
     * 99.9 can display uneven from other nodes.
     *
     * <p>Example: (order 4 complete graph)<pre>
     * [n5 -> n6 (59.0)] [n5 -> n7 (32.0)] [n5 -> n8 (38.0)]
     * [n6 -> n5 (59.0)] [n6 -> n7 (68.0)] [n6 -> n8 (19.0)]
     * [n7 -> n5 (32.0)] [n7 -> n6 (68.0)] [n7 -> n8 (50.0)]
     * [n8 -> n5 (38.0)] [n8 -> n6 (19.0)] [n8 -> n7 (50.0)]
     * </pre>
     *
     * @param graph to generate from the nodes display
     * @param bob   to append over the result
     * @throws NullPointerException if either the graph or bob is {@code null}
     */
    private void appendNodesLinks(final @NonNull Graph graph, final @NonNull StringBuilder bob) {
        Sets.newTreeSet(graph).forEach(node -> {
            node.getLinks().stream()
                    .sorted(Comparator.comparingInt(value -> value.getTarget().getId()))
                    .forEach(link -> bob
                            .append(String.format("[n%-2d", node.getId()))
                            .append(" -> ")
                            .append(String.format("n%-2d", link.getTarget().getId()))
                            .append(" (").append(String.format("%4.1f", link.getLabel())).append(')')
                            .append("] "));
            bob.append('\n');
        });
    }

    /**
     * Appends to the specified builder a fancy adjacency matrix display of the
     * specified {@link Graph}.
     * The schema is designed to work with nodes with Id from 0 to 99 and with
     * link labels from 0.0 to 99.9. Above these ranges, the matrix could be
     * displayed wrong.
     *
     * <p>Example: (order 4 complete graph)<pre>
     *      n5   n6   n7   n8
     *    ┌───────────────────
     * n5 │ 0.0 44.0 79.0 76.0
     * n6 │44.0  0.0 32.0 15.0
     * n7 │79.0 32.0  0.0 70.0
     * n8 │76.0 15.0 70.0  0.0
     * </pre>
     *
     * @param graph to generate from the adjacency matrix
     * @param bob   to append over the result
     * @throws NullPointerException if either the graph or bob is {@code null}
     */
    private void appendAdjacencyMatrix(final @NonNull Graph graph, final @NonNull StringBuilder bob) {
        checkArgument(!graph.isEmpty(), "can't generate an adjacency matrix from an empty graph");

        final double[][] adjacencyMatrix = GraphUtils.getAdjacencyMatrix(graph);

        final List<Node> nodes = new ObjectArrayList<>(graph.getNodes());
        nodes.sort(Comparator.comparingInt(Node::getId));

        // header
        bob.append("    ");
        for (final Node node : nodes) {
            bob.append(String.format(" n%-2d ", node.getId()));
        }
        bob.append("\n   ┌").append(Strings.repeat("─", 5 * nodes.size() - 1)).append('\n');

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            bob.append(String.format("n%-2d│", nodes.get(i).getId()));
            for (final double label : adjacencyMatrix[i]) {
                bob.append(String.format("%4.1f ", label));
            }
            bob.append('\n');
        }
    }

    // util

    /**
     * Creates a <i>Complete Graph</i> with the specified <b>order</b>.
     *
     * @param order of the complete graph to generate
     * @return the created {@link Graph}
     * @apiNote The links are created with a pseudo-random integer label.
     * @see <a href="https://en.wikipedia.org/wiki/Complete_graph">Complete Graph</a>
     */
    @NonNull
    public Graph createCompleteGraph(final int order) {
        final Graph graph = new FastGraph();

        for (int i = 0; i < order; i++) {
            graph.addNode(new FastNode(i));
        }

        for (final Node n1 : graph) {
            for (final Node n2 : graph) {
                if (n1 != n2 && !n2.hasLink(n1.getId())) {
                    final double label = RANDOM.nextInt(98) + 1; // use integer label
                    n1.addLink(new OpenLink(n2, label));
                    n2.addLink(new OpenLink(n1, label));
                }
            }
        }

        return graph;
    }
}
