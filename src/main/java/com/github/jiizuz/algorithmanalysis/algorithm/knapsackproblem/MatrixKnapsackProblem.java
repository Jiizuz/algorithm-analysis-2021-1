package com.github.jiizuz.algorithmanalysis.algorithm.knapsackproblem;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * {@link KnapsackProblem} implementation that uses a matrix to
 * track and filter the most optimal items based on their weight.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.algorithm.knapsackproblem.KnapsackProblem
 * @since 1.4
 */
public class MatrixKnapsackProblem implements KnapsackProblem {

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public Result apply(final @NonNull List<Item> mutableItems, final @NonNull Integer capacity) {
        checkArgument(capacity > 0, "capacity is negative or zero", capacity);
        checkArgument(!mutableItems.isEmpty(), "the items list is empty");
        final ImmutableList<ImmutableItem> items = mutableItems.stream()
                .peek(this::checkItem)
                .map(ImmutableItem::wrap)
                .collect(ImmutableList.toImmutableList());

        final int amount = items.size();

        // Matrix to store the max value at each n-th item
        final int[][] matrix = new int[amount + 1][capacity + 1];

        for (int i = 1; i <= amount; ++i) {
            for (int j = 0; j <= capacity; ++j) {
                final ImmutableItem item = items.get(i - 1);

                if (item.getWeight() > j) {
                    matrix[i][j] = matrix[i - 1][j];
                } else {
                    // we maximize value at this rank in the matrix
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i - 1][j - item.getWeight()] + item.getValue());
                }
            }
        }

        int res = matrix[amount][capacity];
        int w = capacity;

        final List<ImmutableItem> foundItems = Lists.newArrayList();

        for (int i = amount; i > 0 && res > 0; --i) {
            final ImmutableItem item = items.get(i - 1);

            if (res != matrix[i - 1][w]) {
                foundItems.add(item);
                // remove items value and weight
                res -= item.getValue();
                w -= item.getWeight();
            }
        }

        return new WrappedResult(ImmutableList.copyOf(foundItems), matrix[amount][capacity], capacity);
    }

    /**
     * Checks the specified item for valid data.
     *
     * @param item to check
     */
    private void checkItem(final @NonNull Item item) {
        // only need to check nullness
    }

    /**
     * Simple {@link KnapsackProblem.Result} with {@code Lombok} data.
     *
     * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
     * @see com.github.jiizuz.algorithmanalysis.algorithm.knapsackproblem.KnapsackProblem.Result
     * @since 1.4
     */
    @Data
    private static class WrappedResult implements Result {

        /**
         * {@inheritDoc}
         */
        @NonNull
        private final List<Item> items;

        /**
         * {@inheritDoc}
         */
        private final long value;

        /**
         * {@inheritDoc}
         */
        private final int capacity;
    }
}