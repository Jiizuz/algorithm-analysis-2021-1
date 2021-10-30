package com.github.jiizuz.algorithmanalysis.algorithm.knapsackproblem;

/**
 * Represents a single item that can be added to a {@link KnapsackProblem}.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @implSpec This class is not Thread-Safe.
 * @since 1.4
 */
public interface Item {

    /**
     * Id to represent this {@link Item}.
     *
     * @return the Id to represent this {@link Item}
     * @apiNote Normally this value is not unique, but it can be.
     */
    String getName();

    /**
     * Sets the Id to represent this {@link Item}.
     *
     * @param name to set to this {@link Item}
     */
    void setName(String name);

    /**
     * Retrieve the value of this {@link Item} in a {@code Knapsack}.
     *
     * @return the value of this {@link Item}
     */
    int getValue();

    /**
     * Sets the value of this {@link Item} in a {@code Knapsack}.
     *
     * @param value to set to this {@link Item}
     * @throws IllegalArgumentException if the value is {@code negative}
     */
    void setValue(int value);

    /**
     * Retrieve the weight of this {@link Item} in a {@code Knapsack}.
     *
     * @return the weight of this {@link Item}
     */
    int getWeight();

    /**
     * Sets the weight of this {@link Item} in a {@code Knapsack}.
     *
     * @param weight to set to this {@link Item}
     * @throws IllegalArgumentException if the weight is {@code negative}
     */
    void setWeight(int weight);
}
