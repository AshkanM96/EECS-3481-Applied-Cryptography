package util;

import java.util.Arrays;
import java.util.Random;

/**
 * Utility array methods in addition to Java's Arrays class.
 * 
 * @author Ashkan Moatamed
 */
public class ArrayUtil {
	/**
	 * No dependencies.
	 */

	/**
	 * Pseudo Random Number Generator.
	 */
	private static Random prng = null;

	/**
	 * Prevent instantiation.
	 */
	private ArrayUtil() {
		// Empty by design.
	}

	/**
	 * @param lhs
	 *            the given left hand side boolean[][]
	 * 
	 * @param rhs
	 *            the given right hand side boolean[][]
	 * 
	 * @return <code>true</code> if and only if <code>Arrays.deepEquals(lhs, rhs)</code>.
	 */
	public static boolean equals(boolean[][] lhs, boolean[][] rhs) {
		if (lhs == rhs) {
			return true;
		} else if ((lhs == null) || (rhs == null)) {
			return false;
		}

		// Check lengths.
		if (lhs.length != rhs.length) {
			return false;
		}
		// Check individual entries.
		for (int i = 0; i != lhs.length; ++i) {
			if (!Arrays.equals(lhs[i], rhs[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param lhs
	 *            the given left hand side byte[][]
	 * 
	 * @param rhs
	 *            the given right hand side byte[][]
	 * 
	 * @return <code>true</code> if and only if <code>Arrays.deepEquals(lhs, rhs)</code>.
	 */
	public static boolean equals(byte[][] lhs, byte[][] rhs) {
		if (lhs == rhs) {
			return true;
		} else if ((lhs == null) || (rhs == null)) {
			return false;
		}

		// Check lengths.
		if (lhs.length != rhs.length) {
			return false;
		}
		// Check individual entries.
		for (int i = 0; i != lhs.length; ++i) {
			if (!Arrays.equals(lhs[i], rhs[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param lhs
	 *            the given left hand side char[][]
	 * 
	 * @param rhs
	 *            the given right hand side char[][]
	 * 
	 * @return <code>true</code> if and only if <code>Arrays.deepEquals(lhs, rhs)</code>.
	 */
	public static boolean equals(char[][] lhs, char[][] rhs) {
		if (lhs == rhs) {
			return true;
		} else if ((lhs == null) || (rhs == null)) {
			return false;
		}

		// Check lengths.
		if (lhs.length != rhs.length) {
			return false;
		}
		// Check individual entries.
		for (int i = 0; i != lhs.length; ++i) {
			if (!Arrays.equals(lhs[i], rhs[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param lhs
	 *            the given left hand side short[][]
	 * 
	 * @param rhs
	 *            the given right hand side short[][]
	 * 
	 * @return <code>true</code> if and only if <code>Arrays.deepEquals(lhs, rhs)</code>.
	 */
	public static boolean equals(short[][] lhs, short[][] rhs) {
		if (lhs == rhs) {
			return true;
		} else if ((lhs == null) || (rhs == null)) {
			return false;
		}

		// Check lengths.
		if (lhs.length != rhs.length) {
			return false;
		}
		// Check individual entries.
		for (int i = 0; i != lhs.length; ++i) {
			if (!Arrays.equals(lhs[i], rhs[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param lhs
	 *            the given left hand side int[][]
	 * 
	 * @param rhs
	 *            the given right hand side int[][]
	 * 
	 * @return <code>true</code> if and only if <code>Arrays.deepEquals(lhs, rhs)</code>.
	 */
	public static boolean equals(int[][] lhs, int[][] rhs) {
		if (lhs == rhs) {
			return true;
		} else if ((lhs == null) || (rhs == null)) {
			return false;
		}

		// Check lengths.
		if (lhs.length != rhs.length) {
			return false;
		}
		// Check individual entries.
		for (int i = 0; i != lhs.length; ++i) {
			if (!Arrays.equals(lhs[i], rhs[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param lhs
	 *            the given left hand side long[][]
	 * 
	 * @param rhs
	 *            the given right hand side long[][]
	 * 
	 * @return <code>true</code> if and only if <code>Arrays.deepEquals(lhs, rhs)</code>.
	 */
	public static boolean equals(long[][] lhs, long[][] rhs) {
		if (lhs == rhs) {
			return true;
		} else if ((lhs == null) || (rhs == null)) {
			return false;
		}

		// Check lengths.
		if (lhs.length != rhs.length) {
			return false;
		}
		// Check individual entries.
		for (int i = 0; i != lhs.length; ++i) {
			if (!Arrays.equals(lhs[i], rhs[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param lhs
	 *            the given left hand side float[][]
	 * 
	 * @param rhs
	 *            the given right hand side float[][]
	 * 
	 * @return <code>true</code> if and only if <code>Arrays.deepEquals(lhs, rhs)</code>.
	 */
	public static boolean equals(float[][] lhs, float[][] rhs) {
		if (lhs == rhs) {
			return true;
		} else if ((lhs == null) || (rhs == null)) {
			return false;
		}

		// Check lengths.
		if (lhs.length != rhs.length) {
			return false;
		}
		// Check individual entries.
		for (int i = 0; i != lhs.length; ++i) {
			if (!Arrays.equals(lhs[i], rhs[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param lhs
	 *            the given left hand side double[][]
	 * 
	 * @param rhs
	 *            the given right hand side double[][]
	 * 
	 * @return <code>true</code> if and only if <code>Arrays.deepEquals(lhs, rhs)</code>.
	 */
	public static boolean equals(double[][] lhs, double[][] rhs) {
		if (lhs == rhs) {
			return true;
		} else if ((lhs == null) || (rhs == null)) {
			return false;
		}

		// Check lengths.
		if (lhs.length != rhs.length) {
			return false;
		}
		// Check individual entries.
		for (int i = 0; i != lhs.length; ++i) {
			if (!Arrays.equals(lhs[i], rhs[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Postcondition: <code>Result == Arrays.deepHashCode(a)</code>
	 * 
	 * @param a
	 *            the given boolean[][]
	 * 
	 * @return The resulting hash code.
	 */
	public static int hashCode(boolean[][] a) {
		if (a == null) {
			return 0;
		}

		final int prime = 31;
		int result = 0;
		for (int i = 0; i != a.length; ++i) {
			result = prime * result + Arrays.hashCode(a[i]);
		}
		return result;
	}

	/**
	 * Postcondition: <code>Result == Arrays.deepHashCode(a)</code>
	 * 
	 * @param a
	 *            the given byte[][]
	 * 
	 * @return The resulting hash code.
	 */
	public static int hashCode(byte[][] a) {
		if (a == null) {
			return 0;
		}

		final int prime = 31;
		int result = 0;
		for (int i = 0; i != a.length; ++i) {
			result = prime * result + Arrays.hashCode(a[i]);
		}
		return result;
	}

	/**
	 * Postcondition: <code>Result == Arrays.deepHashCode(a)</code>
	 * 
	 * @param a
	 *            the given char[][]
	 * 
	 * @return The resulting hash code.
	 */
	public static int hashCode(char[][] a) {
		if (a == null) {
			return 0;
		}

		final int prime = 31;
		int result = 0;
		for (int i = 0; i != a.length; ++i) {
			result = prime * result + Arrays.hashCode(a[i]);
		}
		return result;
	}

	/**
	 * Postcondition: <code>Result == Arrays.deepHashCode(a)</code>
	 * 
	 * @param a
	 *            the given short[][]
	 * 
	 * @return The resulting hash code.
	 */
	public static int hashCode(short[][] a) {
		if (a == null) {
			return 0;
		}

		final int prime = 31;
		int result = 0;
		for (int i = 0; i != a.length; ++i) {
			result = prime * result + Arrays.hashCode(a[i]);
		}
		return result;
	}

	/**
	 * Postcondition: <code>Result == Arrays.deepHashCode(a)</code>
	 * 
	 * @param a
	 *            the given int[][]
	 * 
	 * @return The resulting hash code.
	 */
	public static int hashCode(int[][] a) {
		if (a == null) {
			return 0;
		}

		final int prime = 31;
		int result = 0;
		for (int i = 0; i != a.length; ++i) {
			result = prime * result + Arrays.hashCode(a[i]);
		}
		return result;
	}

	/**
	 * Postcondition: <code>Result == Arrays.deepHashCode(a)</code>
	 * 
	 * @param a
	 *            the given long[][]
	 * 
	 * @return The resulting hash code.
	 */
	public static int hashCode(long[][] a) {
		if (a == null) {
			return 0;
		}

		final int prime = 31;
		int result = 0;
		for (int i = 0; i != a.length; ++i) {
			result = prime * result + Arrays.hashCode(a[i]);
		}
		return result;
	}

	/**
	 * Postcondition: <code>Result == Arrays.deepHashCode(a)</code>
	 * 
	 * @param a
	 *            the given float[][]
	 * 
	 * @return The resulting hash code.
	 */
	public static int hashCode(float[][] a) {
		if (a == null) {
			return 0;
		}

		final int prime = 31;
		int result = 0;
		for (int i = 0; i != a.length; ++i) {
			result = prime * result + Arrays.hashCode(a[i]);
		}
		return result;
	}

	/**
	 * Postcondition: <code>Result == Arrays.deepHashCode(a)</code>
	 * 
	 * @param a
	 *            the given double[][]
	 * 
	 * @return The resulting hash code.
	 */
	public static int hashCode(double[][] a) {
		if (a == null) {
			return 0;
		}

		final int prime = 31;
		int result = 0;
		for (int i = 0; i != a.length; ++i) {
			result = prime * result + Arrays.hashCode(a[i]);
		}
		return result;
	}

	/**
	 * Precondition: <code>a != null</code> <br>
	 * Precondition: <code>(0 <= i) && (i < a.length)</code> <br>
	 * Precondition: <code>(0 <= j) && (j < a.length)</code>
	 * 
	 * @param a
	 *            the given boolean[]
	 * 
	 * @param i
	 *            the given first index
	 * 
	 * @param j
	 *            the given second index
	 */
	protected static void swap(boolean[] a, int i, int j) {
		final boolean tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	/**
	 * Precondition: <code>a != null</code> <br>
	 * Precondition: <code>(0 <= i) && (i < a.length)</code> <br>
	 * Precondition: <code>(0 <= j) && (j < a.length)</code>
	 * 
	 * @param a
	 *            the given byte[]
	 * 
	 * @param i
	 *            the given first index
	 * 
	 * @param j
	 *            the given second index
	 */
	protected static void swap(byte[] a, int i, int j) {
		final byte tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	/**
	 * Precondition: <code>a != null</code> <br>
	 * Precondition: <code>(0 <= i) && (i < a.length)</code> <br>
	 * Precondition: <code>(0 <= j) && (j < a.length)</code>
	 * 
	 * @param a
	 *            the given char[]
	 * 
	 * @param i
	 *            the given first index
	 * 
	 * @param j
	 *            the given second index
	 */
	protected static void swap(char[] a, int i, int j) {
		final char tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	/**
	 * Precondition: <code>a != null</code> <br>
	 * Precondition: <code>(0 <= i) && (i < a.length)</code> <br>
	 * Precondition: <code>(0 <= j) && (j < a.length)</code>
	 * 
	 * @param a
	 *            the given short[]
	 * 
	 * @param i
	 *            the given first index
	 * 
	 * @param j
	 *            the given second index
	 */
	protected static void swap(short[] a, int i, int j) {
		final short tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	/**
	 * Precondition: <code>a != null</code> <br>
	 * Precondition: <code>(0 <= i) && (i < a.length)</code> <br>
	 * Precondition: <code>(0 <= j) && (j < a.length)</code>
	 * 
	 * @param a
	 *            the given int[]
	 * 
	 * @param i
	 *            the given first index
	 * 
	 * @param j
	 *            the given second index
	 */
	protected static void swap(int[] a, int i, int j) {
		final int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	/**
	 * Precondition: <code>a != null</code> <br>
	 * Precondition: <code>(0 <= i) && (i < a.length)</code> <br>
	 * Precondition: <code>(0 <= j) && (j < a.length)</code>
	 * 
	 * @param a
	 *            the given long[]
	 * 
	 * @param i
	 *            the given first index
	 * 
	 * @param j
	 *            the given second index
	 */
	protected static void swap(long[] a, int i, int j) {
		final long tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	/**
	 * Precondition: <code>a != null</code> <br>
	 * Precondition: <code>(0 <= i) && (i < a.length)</code> <br>
	 * Precondition: <code>(0 <= j) && (j < a.length)</code>
	 * 
	 * @param a
	 *            the given float[]
	 * 
	 * @param i
	 *            the given first index
	 * 
	 * @param j
	 *            the given second index
	 */
	protected static void swap(float[] a, int i, int j) {
		final float tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	/**
	 * Precondition: <code>a != null</code> <br>
	 * Precondition: <code>(0 <= i) && (i < a.length)</code> <br>
	 * Precondition: <code>(0 <= j) && (j < a.length)</code>
	 * 
	 * @param a
	 *            the given double[]
	 * 
	 * @param i
	 *            the given first index
	 * 
	 * @param j
	 *            the given second index
	 */
	protected static void swap(double[] a, int i, int j) {
		final double tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	/**
	 * Precondition: <code>a != null</code> <br>
	 * Precondition: <code>(0 <= i) && (i < a.length)</code> <br>
	 * Precondition: <code>(0 <= j) && (j < a.length)</code>
	 * 
	 * @param a
	 *            the given Object[]
	 * 
	 * @param i
	 *            the given first index
	 * 
	 * @param j
	 *            the given second index
	 */
	protected static void swap(Object[] a, int i, int j) {
		final Object tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	/**
	 * @param a
	 *            the given boolean[]
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 */
	public static void shuffle(boolean[] a) throws NullPointerException {
		// Nothing to shuffle.
		if (a.length < 2) {
			return;
		}

		// Construct ArrayUtil.prng if needed. Executed at most once.
		if (ArrayUtil.prng == null) {
			ArrayUtil.prng = new Random();
		}

		// Fisher-Yates Algorithm.
		for (int i = a.length - 1; i != 0; --i) {
			ArrayUtil.swap(a, i, ArrayUtil.prng.nextInt(i + 1));
		}
	}

	/**
	 * @param a
	 *            the given byte[]
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 */
	public static void shuffle(byte[] a) throws NullPointerException {
		// Nothing to shuffle.
		if (a.length < 2) {
			return;
		}

		// Construct ArrayUtil.prng if needed. Executed at most once.
		if (ArrayUtil.prng == null) {
			ArrayUtil.prng = new Random();
		}

		// Fisher-Yates Algorithm.
		for (int i = a.length - 1; i != 0; --i) {
			ArrayUtil.swap(a, i, ArrayUtil.prng.nextInt(i + 1));
		}
	}

	/**
	 * @param a
	 *            the given char[]
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 */
	public static void shuffle(char[] a) throws NullPointerException {
		// Nothing to shuffle.
		if (a.length < 2) {
			return;
		}

		// Construct ArrayUtil.prng if needed. Executed at most once.
		if (ArrayUtil.prng == null) {
			ArrayUtil.prng = new Random();
		}

		// Fisher-Yates Algorithm.
		for (int i = a.length - 1; i != 0; --i) {
			ArrayUtil.swap(a, i, ArrayUtil.prng.nextInt(i + 1));
		}
	}

	/**
	 * @param a
	 *            the given short[]
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 */
	public static void shuffle(short[] a) throws NullPointerException {
		// Nothing to shuffle.
		if (a.length < 2) {
			return;
		}

		// Construct ArrayUtil.prng if needed. Executed at most once.
		if (ArrayUtil.prng == null) {
			ArrayUtil.prng = new Random();
		}

		// Fisher-Yates Algorithm.
		for (int i = a.length - 1; i != 0; --i) {
			ArrayUtil.swap(a, i, ArrayUtil.prng.nextInt(i + 1));
		}
	}

	/**
	 * @param a
	 *            the given int[]
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 */
	public static void shuffle(int[] a) throws NullPointerException {
		// Nothing to shuffle.
		if (a.length < 2) {
			return;
		}

		// Construct ArrayUtil.prng if needed. Executed at most once.
		if (ArrayUtil.prng == null) {
			ArrayUtil.prng = new Random();
		}

		// Fisher-Yates Algorithm.
		for (int i = a.length - 1; i != 0; --i) {
			ArrayUtil.swap(a, i, ArrayUtil.prng.nextInt(i + 1));
		}
	}

	/**
	 * @param a
	 *            the given long[]
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 */
	public static void shuffle(long[] a) throws NullPointerException {
		// Nothing to shuffle.
		if (a.length < 2) {
			return;
		}

		// Construct ArrayUtil.prng if needed. Executed at most once.
		if (ArrayUtil.prng == null) {
			ArrayUtil.prng = new Random();
		}

		// Fisher-Yates Algorithm.
		for (int i = a.length - 1; i != 0; --i) {
			ArrayUtil.swap(a, i, ArrayUtil.prng.nextInt(i + 1));
		}
	}

	/**
	 * @param a
	 *            the given float[]
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 */
	public static void shuffle(float[] a) throws NullPointerException {
		// Nothing to shuffle.
		if (a.length < 2) {
			return;
		}

		// Construct ArrayUtil.prng if needed. Executed at most once.
		if (ArrayUtil.prng == null) {
			ArrayUtil.prng = new Random();
		}

		// Fisher-Yates Algorithm.
		for (int i = a.length - 1; i != 0; --i) {
			ArrayUtil.swap(a, i, ArrayUtil.prng.nextInt(i + 1));
		}
	}

	/**
	 * @param a
	 *            the given double[]
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 */
	public static void shuffle(double[] a) throws NullPointerException {
		// Nothing to shuffle.
		if (a.length < 2) {
			return;
		}

		// Construct ArrayUtil.prng if needed. Executed at most once.
		if (ArrayUtil.prng == null) {
			ArrayUtil.prng = new Random();
		}

		// Fisher-Yates Algorithm.
		for (int i = a.length - 1; i != 0; --i) {
			ArrayUtil.swap(a, i, ArrayUtil.prng.nextInt(i + 1));
		}
	}

	/**
	 * @param a
	 *            the given Object[]
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 */
	public static void shuffle(Object[] a) throws NullPointerException {
		// Nothing to shuffle.
		if (a.length < 2) {
			return;
		}

		// Construct ArrayUtil.prng if needed. Executed at most once.
		if (ArrayUtil.prng == null) {
			ArrayUtil.prng = new Random();
		}

		// Fisher-Yates Algorithm.
		for (int i = a.length - 1; i != 0; --i) {
			ArrayUtil.swap(a, i, ArrayUtil.prng.nextInt(i + 1));
		}
	}
}
