package com.github.jiizuz.algorithmanalysis.algorithm.chess.util;

import com.google.common.base.MoreObjects;
import lombok.NonNull;

/**
 * <b>Algebraic notation</b> (or <b>AN</b>) is the standard method for
 * recording and describing the moves in a game of chess. It is based
 * on a system of coordinates to uniquely identify each square on the
 * chessboard. It is used by most books, magazines, and newspapers.
 * In English-speaking countries, the parallel method of
 * <a href="https://en.wikipedia.org/wiki/Descriptive_notation">descriptive notation</a>
 * was generally used in chess publications until about 1980. A few
 * players still use descriptive notation, but it is no longer recognized
 * by <a href="https://en.wikipedia.org/wiki/FIDE">FIDE</a>, the
 * international chess governing body.
 *
 * <p>Algebraic notation exists in various forms and languages and is
 * based on a system developed by <a href="https://en.wikipedia.org/wiki/Philipp_Stamma"><b>Philipp Stamma</b></a>.
 * Stamma used the modern names of the squares, but he used <i>p</i> for
 * pawn moves and the original file of a piece (<i>a</i> through <i>h</i>)
 * instead of the initial letter of the piece name.
 *
 * <p>The term "algebraic notation" may be considered a misnomer as the
 * system is unrelated to algebra. Other terms such as "standard notation"
 * and "coordinate notation" have been proposed, but the traditional term
 * persists.
 *
 * <p>Because the components of AlgebraicNotation are mutable, storing notations
 * long term may be dangerous if passing code modifies the notation later. If
 * you want to keep around a notation, it may be wise to call <tt>clone()</tt>
 * in order to get a copy.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @apiNote This class is mutable and not Thread-Safe.
 * @see <a href="https://en.wikipedia.org/wiki/Algebraic_notation_(chess)">Algebraic notation</a>
 * @see java.lang.Comparable
 * @since 1.5
 */
public class AlgebraicNotation implements Comparable<AlgebraicNotation>, Cloneable {

    private char file;
    private byte rank;

    /**
     * Construct the algebraic notation with specified file/rank components.
     *
     * @param file File component
     * @param rank Rank component
     */
    public AlgebraicNotation(final char file, final byte rank) {
        this.file = Character.toLowerCase(file);
        this.rank = rank;
    }

    /**
     * Construct the algebraic notation with all components as 'a8'.
     */
    public AlgebraicNotation() {
        this.file = 'a';
        this.rank = 8;
    }

    /**
     * Gets the File component.
     *
     * @return the File component
     */
    public char getFile() {
        return file;
    }

    /**
     * Set the File component.
     *
     * @param file the new File component
     * @return this notation
     */
    @NonNull
    public AlgebraicNotation setFile(final char file) {
        this.file = file;
        return this;
    }

    /**
     * Gets the Rank component.
     *
     * @return the Rank component
     */
    public byte getRank() {
        return rank;
    }

    /**
     * Set the Rank component.
     *
     * @param rank the new Rank component
     * @return this notation
     */
    @NonNull
    public AlgebraicNotation setRank(final byte rank) {
        this.rank = rank;
        return this;
    }

    // Comparable

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final @NonNull AlgebraicNotation o) {
        final int res = Character.compare(file, o.file);

        return res != 0 ? res : Byte.compare(rank, o.rank);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = 373;
        result = 37 * result + file;
        result = 37 * result + rank;
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof AlgebraicNotation)) return false;
        final AlgebraicNotation notation = (AlgebraicNotation) o;
        return file == notation.file && rank == notation.rank;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AlgebraicNotation clone() {
        try {
            return (AlgebraicNotation) super.clone();
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("file", file)
                .add("rank", rank)
                .toString();
    }
}