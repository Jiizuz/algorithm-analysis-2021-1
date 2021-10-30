package com.github.jiizuz.algorithmanalysis.algorithm.knapsackproblem;

import com.google.common.collect.ImmutableList;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.List;

/**
 * Main class of the project to initialize and run.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @since 1.4
 */
@UtilityClass
public class Main {

    /**
     * Runs the main instance of the project.
     *
     * @param args passed in the command line
     */
    public static void main(String[] args) {
        final List<Item> items = ImmutableList.of(
                new ImmutableItem("Object1", 4, 12),
                new ImmutableItem("Object2", 2, 1),
                new ImmutableItem("Object3", 2, 2),
                new ImmutableItem("Object4", 1, 1),
                new ImmutableItem("Object5", 10, 4));
        final int capacity = 15;

        final KnapsackProblem knapsack = new MatrixKnapsackProblem();

        final KnapsackProblem.Result result = knapsack.apply(items, capacity);

        printResult(result);
    }

    /**
     * Display the result of a {@link KnapsackProblem}.
     *
     * @param result to display
     */
    public void printResult(final @NonNull KnapsackProblem.Result result) {
        System.out.printf("Capacity = %,d%n", result.getCapacity());
        System.out.printf("Value = %,d%n", result.getValue());

        System.out.println("Items to pick:");
        result.getItems().forEach(item -> System.out.printf("- %s%n", item));
    }
}
