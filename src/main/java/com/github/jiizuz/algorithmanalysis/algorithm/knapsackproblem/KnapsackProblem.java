package com.github.jiizuz.algorithmanalysis.algorithm.knapsackproblem;

import lombok.NonNull;

import java.util.List;
import java.util.function.BiFunction;

/**
 * The knapsack problem is a problem in combinatorial optimization: Given
 * a set of items, each with a weight and a value, determine the number
 * of each item to include in a collection so that the total weight is
 * less than or equal to a given limit and the total value is as large as
 * possible. It derives its name from the problem faced by someone who is
 * constrained by a fixed-size knapsack and must fill it with the most
 * valuable items. The problem often arises in resource allocation where
 * the decision makers have to choose from a set of non-divisible projects
 * or tasks under a fixed budget or time constraint, respectively.
 *
 * <p>The knapsack problem has been studied for more than a century, with
 * early works dating as far back as 1897. The name "knapsack problem" dates
 * back to the early works of the mathematician Tobias Dantzig (1884–1956),
 * and refers to the commonplace problem of packing the most valuable or
 * useful items without overloading the luggage.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @implSpec This class is Thread-Safe.
 * @see java.util.function.BiFunction
 * @since 1.4
 */
public interface KnapsackProblem extends BiFunction<List<Item>, Integer, KnapsackProblem.Result> {

    /**
     * Searches for the maximum possible value from the specified items with the limit
     * of the specified capacity and returns the found optimal items and the resultant
     * value that can be added to a knapsack.
     *
     * @throws NullPointerException     if either the items or capacity is {@code null}
     * @throws NullPointerException     if one of the items in the list is {@code null}
     * @throws IllegalArgumentException if the capacity is {@code <= 0}
     * @throws IllegalArgumentException if the list is {@code empty}
     * @throws IllegalArgumentException if one of the items has invalid data
     * @apiNote The items of the list will be copied, but is indeed that for the first
     * moments of the method, the {@link List} is not updated, otherwise an exception
     * will be thrown.
     */
    @NonNull
    Result apply(@NonNull List<Item> items, @NonNull Integer capacity);

    /**
     * Computed result by a {@link KnapsackProblem}.
     *
     * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
     * @implSpec This class is Thread-Safe.
     * @since 1.4
     */
    interface Result {

        /**
         * The best items found to solve the problem.
         *
         * @return the items to solve the problem
         * @implSpec The returned {@link List} is {@code immutable}.
         *
         * <p>The {@link Item} in the {@link List} are {@code immutable}.
         */
        @NonNull
        List<Item> getItems();

        /**
         * Maximum possible value to have in the knapsack.
         *
         * @return the maximum possible value in the knapsack
         * @apiNote The value can be {@code 0}, but never negative.
         *
         * <p>This value is also the value of reducing the resulting
         * items' values.
         */
        long getValue();

        /**
         * Capacity of the knapsack provided in this result.
         *
         * @return the capacity of the knapsack solved
         * @apiNote This value is never zero nor negative.
         */
        int getCapacity();
    }
}
