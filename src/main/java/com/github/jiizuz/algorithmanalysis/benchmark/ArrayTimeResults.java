package com.github.jiizuz.algorithmanalysis.benchmark;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntLists;
import lombok.NonNull;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

/**
 * {@link TimeResults} implementation that uses a {@link IntArrayList}
 * initialized lazily when required.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see com.github.jiizuz.algorithmanalysis.benchmark.TimeResults
 * @since 1.0
 */
public class ArrayTimeResults implements TimeResults {

    /**
     * Initial capacity to use on the {@link IntList}.
     */
    private static final int INITIAL_CAPACITY = 1000000000;

    /**
     * Current {@link AutoCloseable} status.
     */
    private boolean closed = false;

    /**
     * {@link IntList} to store the registered times.
     */
    private IntList times = null;

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws IllegalStateException {
        checkIsNotClosed();

        setClosed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void register(final int time) throws IllegalStateException {
        checkArgument( time >= 0, "negative times are not allowed", time );
        checkIsNotClosed();

        times().add( time );
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    public IntList getTimes() {
        if ( Objects.isNull( times ) )
        {
            return IntLists.emptyList();
        }

        return IntLists.unmodifiable( times );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dump() {
        if ( Objects.nonNull( times ) )
        {
            times.clear();
            times = null;
        }
    }

    /**
     * Returns the {@link IntList}.
     *
     * <p>The List is lazily initialized if <tt>null</tt>
     *
     * @return the times {@link IntList}
     */
    @NonNull
    private IntList times() {
        if ( times == null )
        {
            times = new IntArrayList( INITIAL_CAPACITY );
        }
        return times;
    }

    /**
     * Checks this {@link TimeResults} are not closed yet.
     *
     * @throws IllegalStateException if these results are already closed
     */
    private void checkIsNotClosed() throws IllegalStateException {
        checkState( ! closed, "the results are already closed" );
    }

    /**
     * Closes this results and set the status to <tt>closed</tt>.
     */
    private void setClosed() {
        closed = true;
    }
}
