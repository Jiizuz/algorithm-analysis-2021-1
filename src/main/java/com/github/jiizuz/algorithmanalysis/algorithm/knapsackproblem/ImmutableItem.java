package com.github.jiizuz.algorithmanalysis.algorithm.knapsackproblem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;

/**
 * {@link Item} implementation that promises to be {@code immutable}.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.algorithm.knapsackproblem.Item
 * @since 1.4
 */
@Builder
@ToString
@AllArgsConstructor
public final class ImmutableItem implements Item {

    /**
     * Name of the {@link Item}.
     */
    private final String name;

    /**
     * Value of the {@link Item}.
     */
    private final int value;

    /**
     * Weight of the {@link Item}.
     */
    private final int weight;

    /**
     * Wraps the specified {@link Item} into an {@link ImmutableItem}.
     *
     * @param item to wrap over
     * @return the immutable wrapped item
     */
    @NonNull
    public static ImmutableItem wrap(final @NonNull Item item) {
        if (item instanceof ImmutableItem) {
            return (ImmutableItem) item;
        }

        return builder()
                .name(item.getName())
                .value(item.getValue())
                .weight(item.getWeight())
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     *
     * @throws UnsupportedOperationException always
     */
    @Override
    public void setName(final String name) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("immutable item");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     *
     * @throws UnsupportedOperationException always
     */
    @Override
    public void setValue(final int value) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("immutable item");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWeight() {
        return weight;
    }

    /**
     * {@inheritDoc}
     *
     * @throws UnsupportedOperationException always
     */
    @Override
    public void setWeight(final int weight) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("immutable item");
    }
}
