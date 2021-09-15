package com.github.jiizuz.algorithmanalysis.benchmark;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntLists;
import lombok.NonNull;

import java.util.Objects;

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
     * {@link IntList} to store the registered times.
     */
    private IntList times = null;

    /**
     * {@inheritDoc}
     */
    @Override
    public void register(final int time) {
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
            times = new IntArrayList( 1000000000 );
        }
        return times;
    }
}
