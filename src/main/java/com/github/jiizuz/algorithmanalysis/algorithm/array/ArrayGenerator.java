package com.github.jiizuz.algorithmanalysis.algorithm.array;

import lombok.experimental.UtilityClass;

/**
 * <b>Utility Class</b> to generate various types of arrays.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @since 1.0
 */
@UtilityClass
public class ArrayGenerator {

    /**
     * Generates a random array of numbers in the range of the specified
     * from and to range, (both inclusive), being the minimum and maximum
     * values included in the returned array.
     *
     * @param length of the array
     * @param from   minimum value in the array (inclusive)
     * @param to     maximum possible value in the array (inclusive)
     * @return the generated array of bytes
     */
    public byte[] generateRandom(final int length, final byte from, final byte to) {
        final byte[] arr = new byte[length];

        for (int i = 0; i < length; ++i) {
            arr[i] = (byte) (Math.random() * (to - from) + from);
        }

        return arr;
    }

    /**
     * Generates a random array of numbers in the range of the specified
     * from and to range, (both inclusive), being the minimum and maximum
     * values included in the returned array.
     *
     * @param length of the array
     * @param from   minimum value in the array (inclusive)
     * @param to     maximum possible value in the array (inclusive)
     * @return the generated array of shorts
     */
    public short[] generateRandom(final int length, final short from, final short to) {
        final short[] arr = new short[length];

        for (int i = 0; i < length; ++i) {
            arr[i] = (short) (Math.random() * (to - from) + from);
        }

        return arr;
    }

    /**
     * Generates a random array of numbers in the range of the specified
     * from and to range, (both inclusive), being the minimum and maximum
     * values included in the returned array.
     *
     * @param length of the array
     * @param from   minimum value in the array (inclusive)
     * @param to     maximum possible value in the array (inclusive)
     * @return the generated array of integers
     */
    public int[] generateRandom(final int length, final int from, final int to) {
        final int[] arr = new int[length];

        for (int i = 0; i < length; ++i) {
            arr[i] = (int) (Math.random() * (to - from) + from);
        }

        return arr;
    }

    /**
     * Generates a random array of numbers in the range of the specified
     * from and to range, (both inclusive), being the minimum and maximum
     * values included in the returned array.
     *
     * @param length of the array
     * @param from   minimum value in the array (inclusive)
     * @param to     maximum possible value in the array (inclusive)
     * @return the generated array of longs
     */
    public long[] generateRandom(final int length, final long from, final long to) {
        final long[] arr = new long[length];

        for (int i = 0; i < length; ++i) {
            arr[i] = (long) (Math.random() * (to - from) + from);
        }

        return arr;
    }

    /**
     * Generates a random array of numbers in the range of the specified
     * from and to range, (both inclusive), being the minimum and maximum
     * values included in the returned array.
     *
     * @param length of the array
     * @param from   minimum value in the array (inclusive)
     * @param to     maximum possible value in the array (inclusive)
     * @return the generated array of floats
     */
    public float[] generateRandom(final int length, final float from, final float to) {
        final float[] arr = new float[length];

        for (int i = 0; i < length; ++i) {
            arr[i] = (float) (Math.random() * (to - from) + from);
        }

        return arr;
    }

    /**
     * Generates a random array of numbers in the range of the specified
     * from and to range, (both inclusive), being the minimum and maximum
     * values included in the returned array.
     *
     * @param length of the array
     * @param from   minimum value in the array (inclusive)
     * @param to     maximum possible value in the array (inclusive)
     * @return the generated array of doubles
     */
    public double[] generateRandom(final int length, final double from, final double to) {
        final double[] arr = new double[length];

        for (int i = 0; i < length; ++i) {
            arr[i] = Math.random() * (to - from) + from;
        }

        return arr;
    }

    /**
     * Generates a random array of numbers in the range from <tt>0</tt>
     * to the specified maximum, (inclusive), being the minimum and maximum
     * values included in the returned array.
     *
     * @param length  of the array
     * @param maximum maximum possible value in the array (inclusive)
     * @return the generated array of bytes
     */
    public byte[] generateRandom(final int length, final byte maximum) {
        final byte[] arr = new byte[length];

        for (int i = 0; i < length; ++i) {
            arr[i] = (byte) (Math.random() * maximum);
        }

        return arr;
    }

    /**
     * Generates a random array of numbers in the range from <tt>0</tt>
     * to the specified maximum, (inclusive), being the minimum and maximum
     * values included in the returned array.
     *
     * @param length  of the array
     * @param maximum maximum possible value in the array (inclusive)
     * @return the generated array of shorts
     */
    public short[] generateRandom(final int length, final short maximum) {
        final short[] arr = new short[length];

        for (int i = 0; i < length; ++i) {
            arr[i] = (short) (Math.random() * maximum);
        }

        return arr;
    }

    /**
     * Generates a random array of numbers in the range from <tt>0</tt>
     * to the specified maximum, (inclusive), being the minimum and maximum
     * values included in the returned array.
     *
     * @param length  of the array
     * @param maximum maximum possible value in the array (inclusive)
     * @return the generated array of integers
     */
    public int[] generateRandom(final int length, final int maximum) {
        final int[] arr = new int[length];

        for (int i = 0; i < length; ++i) {
            arr[i] = (int) (Math.random() * maximum);
        }

        return arr;
    }

    /**
     * Generates a random array of numbers in the range from <tt>0</tt>
     * to the specified maximum, (inclusive), being the minimum and maximum
     * values included in the returned array.
     *
     * @param length  of the array
     * @param maximum maximum possible value in the array (inclusive)
     * @return the generated array of longs
     */
    public long[] generateRandom(final int length, final long maximum) {
        final long[] arr = new long[length];

        for (int i = 0; i < length; ++i) {
            arr[i] = (long) (Math.random() * maximum);
        }

        return arr;
    }

    /**
     * Generates a random array of numbers in the range from <tt>0.0</tt>
     * to the specified maximum, (inclusive), being the minimum and maximum
     * values included in the returned array.
     *
     * @param length  of the array
     * @param maximum maximum possible value in the array (inclusive)
     * @return the generated array of floats
     */
    public float[] generateRandom(final int length, final float maximum) {
        final float[] arr = new float[length];

        for (int i = 0; i < length; ++i) {
            arr[i] = (float) (Math.random() * maximum);
        }

        return arr;
    }

    /**
     * Generates a random array of numbers in the range from <tt>0.0</tt>
     * to the specified maximum, (inclusive), being the minimum and maximum
     * values included in the returned array.
     *
     * @param length  of the array
     * @param maximum maximum possible value in the array (inclusive)
     * @return the generated array of doubles
     */
    public double[] generateRandom(final int length, final double maximum) {
        final double[] arr = new double[length];

        for (int i = 0; i < length; ++i) {
            arr[i] = Math.random() * maximum;
        }

        return arr;
    }

    /**
     * Generates and returns an array with the specified length with numbers
     * in descending order from the specified value (inclusive).
     *
     * @param length of the array to return
     * @param from   value to start from the values
     * @return the generated array
     */
    public byte[] generateDescending(final int length, byte from) {
        final byte[] arr = new byte[length];

        for (int i = 0; i < length; ++i) {
            arr[i] = from--;
        }

        return arr;
    }

    /**
     * Generates and returns an array with the specified length with numbers
     * in descending order from the specified value (inclusive).
     *
     * @param length of the array to return
     * @param from   value to start from the values
     * @return the generated array
     */
    public short[] generateDescending(final int length, short from) {
        final short[] arr = new short[length];

        for (int i = 0; i < length; ++i) {
            arr[i] = from--;
        }

        return arr;
    }

    /**
     * Generates and returns an array with the specified length with numbers
     * in descending order from the specified value (inclusive).
     *
     * @param length of the array to return
     * @param from   value to start from the values
     * @return the generated array
     */
    public int[] generateDescending(final int length, int from) {
        final int[] arr = new int[length];

        for (int i = 0; i < length; ++i) {
            arr[i] = from--;
        }

        return arr;
    }

    /**
     * Generates and returns an array with the specified length with numbers
     * in descending order from the specified value (inclusive).
     *
     * @param length of the array to return
     * @param from   value to start from the values
     * @return the generated array
     */
    public long[] generateDescending(final int length, long from) {
        final long[] arr = new long[length];

        for (int i = 0; i < length; ++i) {
            arr[i] = from--;
        }

        return arr;
    }

    /**
     * Generates and returns an array with the specified length with numbers
     * in descending order from the specified value (inclusive).
     *
     * @param length of the array to return
     * @param from   value to start from the values
     * @param diff   between values
     * @return the generated array
     */
    public float[] generateDescending(final int length, float from, final float diff) {
        final float[] arr = new float[length];

        for (int i = 0; i < length; ++i) {
            arr[i] = from;
            from -= diff;
        }

        return arr;
    }

    /**
     * Generates and returns an array with the specified length with numbers
     * in descending order from the specified value (inclusive).
     *
     * @param length of the array to return
     * @param from   value to start from the values
     * @param diff   between values
     * @return the generated array
     */
    public double[] generateDescending(final int length, double from, final double diff) {
        final double[] arr = new double[length];

        for (int i = 0; i < length; ++i) {
            arr[i] = from;
            from -= diff;
        }

        return arr;
    }

    /**
     * Generates and returns an array with the specified length with
     * numbers in ascending order from the specified value (inclusive).
     *
     * @param length of the array to return
     * @param from   value to start from the values
     * @return the generated array
     */
    public byte[] generateAscending(final int length, byte from) {
        final byte[] arr = new byte[length];

        for (int i = 0; i < length; ++i) {
            arr[i] = from++;
        }

        return arr;
    }

    /**
     * Generates and returns an array with the specified length with
     * numbers in ascending order from the specified value (inclusive).
     *
     * @param length of the array to return
     * @param from   value to start from the values
     * @return the generated array
     */
    public short[] generateAscending(final int length, short from) {
        final short[] arr = new short[length];

        for (int i = 0; i < length; ++i) {
            arr[i] = from++;
        }

        return arr;
    }

    /**
     * Generates and returns an array with the specified length with
     * numbers in ascending order from the specified value (inclusive).
     *
     * @param length of the array to return
     * @param from   value to start from the values
     * @return the generated array
     */
    public int[] generateAscending(final int length, int from) {
        final int[] arr = new int[length];

        for (int i = 0; i < length; ++i) {
            arr[i] = from++;
        }

        return arr;
    }

    /**
     * Generates and returns an array with the specified length with
     * numbers in ascending order from the specified value (inclusive).
     *
     * @param length of the array to return
     * @param from   value to start from the values
     * @return the generated array
     */
    public long[] generateAscending(final int length, long from) {
        final long[] arr = new long[length];

        for (int i = 0; i < length; ++i) {
            arr[i] = from++;
        }

        return arr;
    }

    /**
     * Generates and returns an array with the specified length with
     * numbers in ascending order from the specified value (inclusive).
     *
     * @param length of the array to return
     * @param from   value to start from the values
     * @return the generated array
     */
    public float[] generateAscending(final int length, float from, final float diff) {
        final float[] arr = new float[length];

        for (int i = 0; i < length; ++i) {
            arr[i] = from;
            from += diff;
        }

        return arr;
    }

    /**
     * Generates and returns an array with the specified length with
     * numbers in ascending order from the specified value (inclusive).
     *
     * @param length of the array to return
     * @param from   value to start from the values
     * @return the generated array
     */
    public double[] generateAscending(final int length, double from, final double diff) {
        final double[] arr = new double[length];

        for (int i = 0; i < length; ++i) {
            arr[i] = from;
            from += diff;
        }

        return arr;
    }
}
