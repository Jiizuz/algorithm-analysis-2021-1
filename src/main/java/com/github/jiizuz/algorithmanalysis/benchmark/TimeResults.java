package com.github.jiizuz.algorithmanalysis.benchmark;

import it.unimi.dsi.fastutil.ints.IntList;
import lombok.NonNull;

/**
 * Tracks the resultant times of the tests made in a <tt>Benchmark</tt>.
 *
 * <p>When a {@link TimeResults} is <tt>closed</tt> it will not allow
 * more times registers.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @implSpec this class is not thread-safe
 * @see java.lang.AutoCloseable
 * @since 1.0
 */
public interface TimeResults extends AutoCloseable {

    /**
     * Closes this {@link TimeResults}.
     *
     * @throws IllegalStateException if the results are already closed
     */
    @Override
    void close() throws IllegalStateException;

    /**
     * Tracks the specified <tt>Time</tt> in the results.
     *
     * @param time to register in the results
     * @throws IllegalArgumentException if the time is negative
     * @throws IllegalStateException    if these results are already closed
     * @apiNote the time must be measured in <tt>nanoseconds</tt>
     */
    void register(int time) throws IllegalArgumentException, IllegalStateException;

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
