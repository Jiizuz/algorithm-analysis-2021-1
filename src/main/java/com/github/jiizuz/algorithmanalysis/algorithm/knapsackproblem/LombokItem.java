package com.github.jiizuz.algorithmanalysis.algorithm.knapsackproblem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * {@link Item} that uses {@code Lombok} utility to generate the class.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.algorithm.knapsackproblem.Item
 * @since 1.4
 */
@Data
@Builder
@AllArgsConstructor
public final class LombokItem implements Item {

    String name;
    int value;
    int weight;
}
