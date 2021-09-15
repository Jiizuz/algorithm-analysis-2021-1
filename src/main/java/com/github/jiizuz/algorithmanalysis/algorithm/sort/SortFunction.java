package com.github.jiizuz.algorithmanalysis.algorithm.sort;

import java.util.function.Function;

/**
 * {@link Function} to signal that it will receive an array of
 * numbers and will sort them in ascending order and then return
 * the same array object reference.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see java.util.function.Function
 * @since 1.0
 */
@FunctionalInterface
public interface SortFunction extends Function<long[], long[]> {

}
