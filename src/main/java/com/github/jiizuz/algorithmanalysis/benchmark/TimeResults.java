package com.github.jiizuz.algorithmanalysis.benchmark;

import it.unimi.dsi.fastutil.ints.IntList;
import lombok.NonNull;

/**
 * Tracks the resultant times of the tests made in a <tt>Benchmark</tt>.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @implSpec this class is not thread-safe
 * @since 1.0
 */
public interface TimeResults {

    /**
     * Tracks the specified <tt>Time</tt> in the results.
     *
     * @param time to register in the results
     * @throws IllegalArgumentException if the time is negative
     * @apiNote the time must be measured in <tt>nanoseconds</tt>
     */
    void register(int time);

    /**
     * Returns the <tt>Times</tt> registered in the results.
     *
     * @return the <tt>Times</tt> registered in the results
     * @implSpec the returned {@link IntList} is <tt>immutable</tt>
     */
    @NonNull
    IntList getTimes();

    /**
     * Dumps the registered data in the results.
     */
    void dump();
}
