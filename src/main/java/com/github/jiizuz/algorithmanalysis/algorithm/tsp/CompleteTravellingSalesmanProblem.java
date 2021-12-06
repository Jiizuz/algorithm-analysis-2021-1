package com.github.jiizuz.algorithmanalysis.algorithm.tsp;

import com.github.jiizuz.algorithmanalysis.algorithm.graph.Graph;
import com.github.jiizuz.algorithmanalysis.algorithm.graph.Link;
import com.github.jiizuz.algorithmanalysis.algorithm.graph.Node;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectLists;
import lombok.NonNull;

import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * {@link TravellingSalesmanProblem} that assume that the incoming
 * {@link Graph} are <i>Complete</i>, i.e. each pair of nodes are
 * connected with a link.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.algorithm.tsp.TravellingSalesmanProblem
 * @see <a href="https://en.wikipedia.org/wiki/Complete_graph">Complete Graph</a>
 * @since 1.6
 */
public class CompleteTravellingSalesmanProblem implements TravellingSalesmanProblem {

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public List<Node> findPath(final @NonNull Graph graph, final int startNodeId) {
        checkArgument(graph.getOrder() >= 2, "graph must have at least 2 nodes");
        checkArgument(graph.hasNode(startNodeId), "graph does not have starting node");
        final Node startNode = graph.getNodeNow(startNodeId);

        final ObjectList<Node> path = new ObjectArrayList<>(graph.getOrder());
        final IntList available = new IntArrayList(graph.getOrder());
        graph.forEach(node -> { // do not add start node
            if (node != startNode) available.add(node.getId());
        });

        Node node = startNode;

        do {
            path.add(node);

            if (available.isEmpty()) { // all nodes traveled
                path.add(startNode); // last node is the start node

                return ObjectLists.unmodifiable(path);
            }

            // find nearest

            Link potential = null;
            int idx = 0; // cached for removal
            for (int i = 0; i < available.size(); ++i) {
                final Link link = node.getLinkNow(available.getInt(i));

                if (Objects.isNull(potential) || Double.compare(potential.getLabel(), link.getLabel()) == 1) {
                    potential = link;
                    idx = i;
                }
            }
            available.removeInt(idx);

            // update next

            node = potential.getTarget();
        } while (true);
    }
}
