package com.github.jiizuz.algorithmanalysis.algorithm.tsp;

import com.github.jiizuz.algorithmanalysis.algorithm.graph.Graph;
import com.github.jiizuz.algorithmanalysis.algorithm.graph.Node;
import lombok.NonNull;

import java.util.List;

/**
 * The travelling salesman problem (also called the travelling salesperson
 * problem or TSP) asks the following question: "Given a list of cities and
 * the distances between each pair of cities, what is the shortest possible
 * route that visits each city exactly once and returns to the origin city?"
 * It is an NP-hard problem in combinatorial optimization, important in
 * theoretical computer science and operations research.
 *
 * <p>The travelling purchaser problem and the vehicle routing problem are
 * both generalizations of TSP.
 *
 * <p>In the theory of computational complexity, the decision version of the
 * TSP (where given a length L, the task is to decide whether the graph has
 * a tour of at most L) belongs to the class of NP-complete problems. Thus,
 * it is possible that the worst-case running time for any algorithm for the
 * TSP increases superpolynomially (but no more than exponentially) with the
 * number of cities.
 *
 * <p>The problem was first formulated in 1930 and is one of the most intensively
 * studied problems in optimization. It is used as a benchmark for many optimization
 * methods. Even though the problem is computationally difficult, many heuristics
 * and exact algorithms are known, so that some instances with tens of thousands of
 * cities can be solved completely and even problems with millions of cities can be
 * approximated within a small fraction of 1%.
 *
 * <p>The TSP has several applications even in its purest formulation, such as planning,
 * logistics, and the manufacture of microchips. Slightly modified, it appears as a
 * sub-problem in many areas, such as DNA sequencing. In these applications, the concept
 * city represents, for example, customers, soldering points, or DNA fragments, and the
 * concept distance represents travelling times or cost, or a similarity measure between
 * DNA fragments. The TSP also appears in astronomy, as astronomers observing many sources
 * will want to minimize the time spent moving the telescope between the sources; in such
 * problems, the TSP can be embedded inside an optimal control problem. In many applications,
 * additional constraints such as limited resources or time windows may be imposed.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @implNote This class is Thread-Safe.
 * @since 1.6
 */
public interface TravellingSalesmanProblem {

    /**
     * Finds for a {@code Path} of nodes to follow in order to achieve the solution
     * of a {@link TravellingSalesmanProblem}. The nodes in the returned list are
     * the sames as the ones provided in the specified {@link Graph}.
     *
     * @param graph     to use in the algorithm and retrieve the nodes
     * @param startNode Id of the starting node for the algorithm
     * @return a {@link List} with the path of nodes to follow in order to get the
     * shorter path of a {@code TSP} problem
     * @throws IllegalArgumentException if the graph is not at least order 2 or the
     *                                  graph does not have the specified startNode
     * @throws NullPointerException     if the graph is {@code null}
     * @implSpec The {@link Graph} is not modified in any form.
     *
     * <p>The returned {@link List} is <i>immutable</i>.
     */
    @NonNull
    List<Node> findPath(@NonNull Graph graph, int startNode) throws IllegalArgumentException;
}
