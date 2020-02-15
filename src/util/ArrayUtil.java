package util;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
	 * Prevent instantiation.
	 */
	private ArrayUtil() {
		// Empty by design.
	}

	/**
	 * @param copy
	 *            specifies whether the array should be copied
	 * 
	 * @param args
	 *            any number of boolean primitives
	 * 
	 * @return <code>(copy ? Arrays.copyOf(args, args.length) : args)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>args == null</code>
	 */
	public static boolean[] toArrayPrimitive(boolean copy, boolean... args) throws NullPointerException {
		return (copy ? Arrays.copyOf(args, args.length) : args);
	}

	/**
	 * @param copy
	 *            specifies whether the array should be copied
	 * 
	 * @param args
	 *            any number of byte primitives
	 * 
	 * @return <code>(copy ? Arrays.copyOf(args, args.length) : args)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>args == null</code>
	 */
	public static byte[] toArrayPrimitive(boolean copy, byte... args) throws NullPointerException {
		return (copy ? Arrays.copyOf(args, args.length) : args);
	}

	/**
	 * @param args
	 *            any number of byte primitives
	 * 
	 * @return <code>args</code>.
	 */
	public static byte[] toArrayPrimitive(byte... args) {
		return args;
	}

	/**
	 * @param copy
	 *            specifies whether the array should be copied
	 * 
	 * @param args
	 *            any number of char primitives
	 * 
	 * @return <code>(copy ? Arrays.copyOf(args, args.length) : args)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>args == null</code>
	 */
	public static char[] toArrayPrimitive(boolean copy, char... args) throws NullPointerException {
		return (copy ? Arrays.copyOf(args, args.length) : args);
	}

	/**
	 * @param args
	 *            any number of char primitives
	 * 
	 * @return <code>args</code>.
	 */
	public static char[] toArrayPrimitive(char... args) {
		return args;
	}

	/**
	 * @param copy
	 *            specifies whether the array should be copied
	 * 
	 * @param args
	 *            any number of short primitives
	 * 
	 * @return <code>(copy ? Arrays.copyOf(args, args.length) : args)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>args == null</code>
	 */
	public static short[] toArrayPrimitive(boolean copy, short... args) throws NullPointerException {
		return (copy ? Arrays.copyOf(args, args.length) : args);
	}

	/**
	 * @param args
	 *            any number of short primitives
	 * 
	 * @return <code>args</code>.
	 */
	public static short[] toArrayPrimitive(short... args) {
		return args;
	}

	/**
	 * @param copy
	 *            specifies whether the array should be copied
	 * 
	 * @param args
	 *            any number of int primitives
	 * 
	 * @return <code>(copy ? Arrays.copyOf(args, args.length) : args)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>args == null</code>
	 */
	public static int[] toArrayPrimitive(boolean copy, int... args) throws NullPointerException {
		return (copy ? Arrays.copyOf(args, args.length) : args);
	}

	/**
	 * @param args
	 *            any number of int primitives
	 * 
	 * @return <code>args</code>.
	 */
	public static int[] toArrayPrimitive(int... args) {
		return args;
	}

	/**
	 * @param copy
	 *            specifies whether the array should be copied
	 * 
	 * @param args
	 *            any number of long primitives
	 * 
	 * @return <code>(copy ? Arrays.copyOf(args, args.length) : args)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>args == null</code>
	 */
	public static long[] toArrayPrimitive(boolean copy, long... args) throws NullPointerException {
		return (copy ? Arrays.copyOf(args, args.length) : args);
	}

	/**
	 * @param args
	 *            any number of long primitives
	 * 
	 * @return <code>args</code>.
	 */
	public static long[] toArrayPrimitive(long... args) {
		return args;
	}

	/**
	 * @param copy
	 *            specifies whether the array should be copied
	 * 
	 * @param args
	 *            any number of float primitives
	 * 
	 * @return <code>(copy ? Arrays.copyOf(args, args.length) : args)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>args == null</code>
	 */
	public static float[] toArrayPrimitive(boolean copy, float... args) throws NullPointerException {
		return (copy ? Arrays.copyOf(args, args.length) : args);
	}

	/**
	 * @param args
	 *            any number of float primitives
	 * 
	 * @return <code>args</code>.
	 */
	public static float[] toArrayPrimitive(float... args) {
		return args;
	}

	/**
	 * @param copy
	 *            specifies whether the array should be copied
	 * 
	 * @param args
	 *            any number of double primitives
	 * 
	 * @return <code>(copy ? Arrays.copyOf(args, args.length) : args)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>args == null</code>
	 */
	public static double[] toArrayPrimitive(boolean copy, double... args) throws NullPointerException {
		return (copy ? Arrays.copyOf(args, args.length) : args);
	}

	/**
	 * @param args
	 *            any number of double primitives
	 * 
	 * @return <code>args</code>.
	 */
	public static double[] toArrayPrimitive(double... args) {
		return args;
	}

	/**
	 * @param <T>
	 *            the common type of all of the given objects
	 * 
	 * @param copy
	 *            specifies whether the array should be copied
	 * 
	 * @param args
	 *            any number of objects of type <code>T</code>
	 * 
	 * @return <code>(copy ? Arrays.copyOf(args, args.length) : args)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>args == null</code>
	 */
	@SafeVarargs
	public static <T> T[] toArray(boolean copy, T... args) throws NullPointerException {
		return (copy ? Arrays.copyOf(args, args.length) : args);
	}

	/**
	 * @param <T>
	 *            the common type of all of the given objects
	 * 
	 * @param args
	 *            any number of objects of type <code>T</code>
	 * 
	 * @return <code>args</code>.
	 */
	@SafeVarargs
	public static <T> T[] toArray(T... args) {
		return args;
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
	 * Postcondition: <code>(Result != null) && Result.equals(Arrays.deepToString(a))</code>
	 * 
	 * @param a
	 *            the given boolean[][]
	 * 
	 * @return The resulting String.
	 */
	public static String toString(boolean[][] a) {
		if (a == null) {
			return "null";
		}

		final int length = a.length;
		if (length == 0) {
			return "[]";
		}

		final StringBuilder sb = new StringBuilder();
		sb.append('[').append(Arrays.toString(a[0]));
		for (int i = 1; i != length; ++i) {
			sb.append(", ").append(Arrays.toString(a[i]));
		}
		return sb.append(']').toString();
	}

	/**
	 * Postcondition: <code>(Result != null) && Result.equals(Arrays.deepToString(a))</code>
	 * 
	 * @param a
	 *            the given byte[][]
	 * 
	 * @return The resulting String.
	 */
	public static String toString(byte[][] a) {
		if (a == null) {
			return "null";
		}

		final int length = a.length;
		if (length == 0) {
			return "[]";
		}

		final StringBuilder sb = new StringBuilder();
		sb.append('[').append(Arrays.toString(a[0]));
		for (int i = 1; i != length; ++i) {
			sb.append(", ").append(Arrays.toString(a[i]));
		}
		return sb.append(']').toString();
	}

	/**
	 * Postcondition: <code>(Result != null) && Result.equals(Arrays.deepToString(a))</code>
	 * 
	 * @param a
	 *            the given char[][]
	 * 
	 * @return The resulting String.
	 */
	public static String toString(char[][] a) {
		if (a == null) {
			return "null";
		}

		final int length = a.length;
		if (length == 0) {
			return "[]";
		}

		final StringBuilder sb = new StringBuilder();
		sb.append('[').append(Arrays.toString(a[0]));
		for (int i = 1; i != length; ++i) {
			sb.append(", ").append(Arrays.toString(a[i]));
		}
		return sb.append(']').toString();
	}

	/**
	 * Postcondition: <code>(Result != null) && Result.equals(Arrays.deepToString(a))</code>
	 * 
	 * @param a
	 *            the given short[][]
	 * 
	 * @return The resulting String.
	 */
	public static String toString(short[][] a) {
		if (a == null) {
			return "null";
		}

		final int length = a.length;
		if (length == 0) {
			return "[]";
		}

		final StringBuilder sb = new StringBuilder();
		sb.append('[').append(Arrays.toString(a[0]));
		for (int i = 1; i != length; ++i) {
			sb.append(", ").append(Arrays.toString(a[i]));
		}
		return sb.append(']').toString();
	}

	/**
	 * Postcondition: <code>(Result != null) && Result.equals(Arrays.deepToString(a))</code>
	 * 
	 * @param a
	 *            the given int[][]
	 * 
	 * @return The resulting String.
	 */
	public static String toString(int[][] a) {
		if (a == null) {
			return "null";
		}

		final int length = a.length;
		if (length == 0) {
			return "[]";
		}

		final StringBuilder sb = new StringBuilder();
		sb.append('[').append(Arrays.toString(a[0]));
		for (int i = 1; i != length; ++i) {
			sb.append(", ").append(Arrays.toString(a[i]));
		}
		return sb.append(']').toString();
	}

	/**
	 * Postcondition: <code>(Result != null) && Result.equals(Arrays.deepToString(a))</code>
	 * 
	 * @param a
	 *            the given long[][]
	 * 
	 * @return The resulting String.
	 */
	public static String toString(long[][] a) {
		if (a == null) {
			return "null";
		}

		final int length = a.length;
		if (length == 0) {
			return "[]";
		}

		final StringBuilder sb = new StringBuilder();
		sb.append('[').append(Arrays.toString(a[0]));
		for (int i = 1; i != length; ++i) {
			sb.append(", ").append(Arrays.toString(a[i]));
		}
		return sb.append(']').toString();
	}

	/**
	 * Postcondition: <code>(Result != null) && Result.equals(Arrays.deepToString(a))</code>
	 * 
	 * @param a
	 *            the given float[][]
	 * 
	 * @return The resulting String.
	 */
	public static String toString(float[][] a) {
		if (a == null) {
			return "null";
		}

		final int length = a.length;
		if (length == 0) {
			return "[]";
		}

		final StringBuilder sb = new StringBuilder();
		sb.append('[').append(Arrays.toString(a[0]));
		for (int i = 1; i != length; ++i) {
			sb.append(", ").append(Arrays.toString(a[i]));
		}
		return sb.append(']').toString();
	}

	/**
	 * Postcondition: <code>(Result != null) && Result.equals(Arrays.deepToString(a))</code>
	 * 
	 * @param a
	 *            the given double[][]
	 * 
	 * @return The resulting String.
	 */
	public static String toString(double[][] a) {
		if (a == null) {
			return "null";
		}

		final int length = a.length;
		if (length == 0) {
			return "[]";
		}

		final StringBuilder sb = new StringBuilder();
		sb.append('[').append(Arrays.toString(a[0]));
		for (int i = 1; i != length; ++i) {
			sb.append(", ").append(Arrays.toString(a[i]));
		}
		return sb.append(']').toString();
	}

	/**
	 * @param a
	 *            the given boolean[]
	 * 
	 * @param i
	 *            the given index
	 * 
	 * @return <code>i</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If <code>(i < 0) || (a.length <= i)</code>
	 */
	public static int validateIndex(boolean[] a, int i) throws NullPointerException, ArrayIndexOutOfBoundsException {
		if ((i < 0) || (a.length <= i)) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return i;
	}

	/**
	 * @param a
	 *            the given byte[]
	 * 
	 * @param i
	 *            the given index
	 * 
	 * @return <code>i</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If <code>(i < 0) || (a.length <= i)</code>
	 */
	public static int validateIndex(byte[] a, int i) throws NullPointerException, ArrayIndexOutOfBoundsException {
		if ((i < 0) || (a.length <= i)) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return i;
	}

	/**
	 * @param a
	 *            the given char[]
	 * 
	 * @param i
	 *            the given index
	 * 
	 * @return <code>i</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If <code>(i < 0) || (a.length <= i)</code>
	 */
	public static int validateIndex(char[] a, int i) throws NullPointerException, ArrayIndexOutOfBoundsException {
		if ((i < 0) || (a.length <= i)) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return i;
	}

	/**
	 * @param a
	 *            the given short[]
	 * 
	 * @param i
	 *            the given index
	 * 
	 * @return <code>i</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If <code>(i < 0) || (a.length <= i)</code>
	 */
	public static int validateIndex(short[] a, int i) throws NullPointerException, ArrayIndexOutOfBoundsException {
		if ((i < 0) || (a.length <= i)) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return i;
	}

	/**
	 * @param a
	 *            the given int[]
	 * 
	 * @param i
	 *            the given index
	 * 
	 * @return <code>i</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If <code>(i < 0) || (a.length <= i)</code>
	 */
	public static int validateIndex(int[] a, int i) throws NullPointerException, ArrayIndexOutOfBoundsException {
		if ((i < 0) || (a.length <= i)) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return i;
	}

	/**
	 * @param a
	 *            the given long[]
	 * 
	 * @param i
	 *            the given index
	 * 
	 * @return <code>i</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If <code>(i < 0) || (a.length <= i)</code>
	 */
	public static int validateIndex(long[] a, int i) throws NullPointerException, ArrayIndexOutOfBoundsException {
		if ((i < 0) || (a.length <= i)) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return i;
	}

	/**
	 * @param a
	 *            the given float[]
	 * 
	 * @param i
	 *            the given index
	 * 
	 * @return <code>i</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If <code>(i < 0) || (a.length <= i)</code>
	 */
	public static int validateIndex(float[] a, int i) throws NullPointerException, ArrayIndexOutOfBoundsException {
		if ((i < 0) || (a.length <= i)) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return i;
	}

	/**
	 * @param a
	 *            the given double[]
	 * 
	 * @param i
	 *            the given index
	 * 
	 * @return <code>i</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If <code>(i < 0) || (a.length <= i)</code>
	 */
	public static int validateIndex(double[] a, int i) throws NullPointerException, ArrayIndexOutOfBoundsException {
		if ((i < 0) || (a.length <= i)) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return i;
	}

	/**
	 * @param a
	 *            the given Object[]
	 * 
	 * @param i
	 *            the given index
	 * 
	 * @return <code>i</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If <code>(i < 0) || (a.length <= i)</code>
	 */
	public static int validateIndex(Object[] a, int i) throws NullPointerException, ArrayIndexOutOfBoundsException {
		if ((i < 0) || (a.length <= i)) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return i;
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
	 *            the first given index
	 * 
	 * @param j
	 *            the second given index
	 */
	protected static void swapFixedInput(boolean[] a, int i, int j) {
		final boolean tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	/**
	 * @param a
	 *            the given boolean[]
	 * 
	 * @param i
	 *            the first given index
	 * 
	 * @param j
	 *            the first given index
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If <code>(i < 0) || (a.length <= i) || (j < 0) || (a.length <= j)</code>
	 */
	public static void swap(boolean[] a, int i, int j) throws NullPointerException, ArrayIndexOutOfBoundsException {
		ArrayUtil.swapFixedInput(a, ArrayUtil.validateIndex(a, i), ArrayUtil.validateIndex(a, j));
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
	 *            the first given index
	 * 
	 * @param j
	 *            the second given index
	 */
	protected static void swapFixedInput(byte[] a, int i, int j) {
		final byte tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	/**
	 * @param a
	 *            the given byte[]
	 * 
	 * @param i
	 *            the first given index
	 * 
	 * @param j
	 *            the first given index
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If <code>(i < 0) || (a.length <= i) || (j < 0) || (a.length <= j)</code>
	 */
	public static void swap(byte[] a, int i, int j) throws NullPointerException, ArrayIndexOutOfBoundsException {
		ArrayUtil.swapFixedInput(a, ArrayUtil.validateIndex(a, i), ArrayUtil.validateIndex(a, j));
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
	 *            the first given index
	 * 
	 * @param j
	 *            the second given index
	 */
	protected static void swapFixedInput(char[] a, int i, int j) {
		final char tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	/**
	 * @param a
	 *            the given char[]
	 * 
	 * @param i
	 *            the first given index
	 * 
	 * @param j
	 *            the first given index
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If <code>(i < 0) || (a.length <= i) || (j < 0) || (a.length <= j)</code>
	 */
	public static void swap(char[] a, int i, int j) throws NullPointerException, ArrayIndexOutOfBoundsException {
		ArrayUtil.swapFixedInput(a, ArrayUtil.validateIndex(a, i), ArrayUtil.validateIndex(a, j));
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
	 *            the first given index
	 * 
	 * @param j
	 *            the second given index
	 */
	protected static void swapFixedInput(short[] a, int i, int j) {
		final short tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	/**
	 * @param a
	 *            the given short[]
	 * 
	 * @param i
	 *            the first given index
	 * 
	 * @param j
	 *            the first given index
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If <code>(i < 0) || (a.length <= i) || (j < 0) || (a.length <= j)</code>
	 */
	public static void swap(short[] a, int i, int j) throws NullPointerException, ArrayIndexOutOfBoundsException {
		ArrayUtil.swapFixedInput(a, ArrayUtil.validateIndex(a, i), ArrayUtil.validateIndex(a, j));
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
	 *            the first given index
	 * 
	 * @param j
	 *            the second given index
	 */
	protected static void swapFixedInput(int[] a, int i, int j) {
		final int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	/**
	 * @param a
	 *            the given int[]
	 * 
	 * @param i
	 *            the first given index
	 * 
	 * @param j
	 *            the first given index
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If <code>(i < 0) || (a.length <= i) || (j < 0) || (a.length <= j)</code>
	 */
	public static void swap(int[] a, int i, int j) throws NullPointerException, ArrayIndexOutOfBoundsException {
		ArrayUtil.swapFixedInput(a, ArrayUtil.validateIndex(a, i), ArrayUtil.validateIndex(a, j));
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
	 *            the first given index
	 * 
	 * @param j
	 *            the second given index
	 */
	protected static void swapFixedInput(long[] a, int i, int j) {
		final long tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	/**
	 * @param a
	 *            the given long[]
	 * 
	 * @param i
	 *            the first given index
	 * 
	 * @param j
	 *            the first given index
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If <code>(i < 0) || (a.length <= i) || (j < 0) || (a.length <= j)</code>
	 */
	public static void swap(long[] a, int i, int j) throws NullPointerException, ArrayIndexOutOfBoundsException {
		ArrayUtil.swapFixedInput(a, ArrayUtil.validateIndex(a, i), ArrayUtil.validateIndex(a, j));
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
	 *            the first given index
	 * 
	 * @param j
	 *            the second given index
	 */
	protected static void swapFixedInput(float[] a, int i, int j) {
		final float tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	/**
	 * @param a
	 *            the given float[]
	 * 
	 * @param i
	 *            the first given index
	 * 
	 * @param j
	 *            the first given index
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If <code>(i < 0) || (a.length <= i) || (j < 0) || (a.length <= j)</code>
	 */
	public static void swap(float[] a, int i, int j) throws NullPointerException, ArrayIndexOutOfBoundsException {
		ArrayUtil.swapFixedInput(a, ArrayUtil.validateIndex(a, i), ArrayUtil.validateIndex(a, j));
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
	 *            the first given index
	 * 
	 * @param j
	 *            the second given index
	 */
	protected static void swapFixedInput(double[] a, int i, int j) {
		final double tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	/**
	 * @param a
	 *            the given double[]
	 * 
	 * @param i
	 *            the first given index
	 * 
	 * @param j
	 *            the first given index
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If <code>(i < 0) || (a.length <= i) || (j < 0) || (a.length <= j)</code>
	 */
	public static void swap(double[] a, int i, int j) throws NullPointerException, ArrayIndexOutOfBoundsException {
		ArrayUtil.swapFixedInput(a, ArrayUtil.validateIndex(a, i), ArrayUtil.validateIndex(a, j));
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
	 *            the first given index
	 * 
	 * @param j
	 *            the second given index
	 */
	protected static void swapFixedInput(Object[] a, int i, int j) {
		final Object tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	/**
	 * @param a
	 *            the given Object[]
	 * 
	 * @param i
	 *            the first given index
	 * 
	 * @param j
	 *            the first given index
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If <code>(i < 0) || (a.length <= i) || (j < 0) || (a.length <= j)</code>
	 */
	public static void swap(Object[] a, int i, int j) throws NullPointerException, ArrayIndexOutOfBoundsException {
		ArrayUtil.swapFixedInput(a, ArrayUtil.validateIndex(a, i), ArrayUtil.validateIndex(a, j));
	}

	/**
	 * @param a
	 *            the given boolean[]
	 * 
	 * @param prng
	 *            source of random bits used to generate indices
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 */
	public static void shuffle(boolean[] a, Random prng) throws NullPointerException {
		if (a.length < 2) { // i.e., Nothing to shuffle.
			return;
		}
		// 2 <= a.length
		if (prng == null) {
			prng = ThreadLocalRandom.current();
		}

		// Fisher-Yates Algorithm.
		for (int i = a.length - 1; i != 0; --i) {
			ArrayUtil.swapFixedInput(a, i, prng.nextInt(i + 1));
		}
	}

	/**
	 * @param a
	 *            the given boolean[]
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 */
	public static void shuffle(boolean[] a) throws NullPointerException {
		ArrayUtil.shuffle(a, null);
	}

	/**
	 * @param a
	 *            the given byte[]
	 * 
	 * @param prng
	 *            source of random bits used to generate indices
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 */
	public static void shuffle(byte[] a, Random prng) throws NullPointerException {
		if (a.length < 2) { // i.e., Nothing to shuffle.
			return;
		}
		// 2 <= a.length
		if (prng == null) {
			prng = ThreadLocalRandom.current();
		}

		// Fisher-Yates Algorithm.
		for (int i = a.length - 1; i != 0; --i) {
			ArrayUtil.swapFixedInput(a, i, prng.nextInt(i + 1));
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
		ArrayUtil.shuffle(a, null);
	}

	/**
	 * @param a
	 *            the given char[]
	 * 
	 * @param prng
	 *            source of random bits used to generate indices
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 */
	public static void shuffle(char[] a, Random prng) throws NullPointerException {
		if (a.length < 2) { // i.e., Nothing to shuffle.
			return;
		}
		// 2 <= a.length
		if (prng == null) {
			prng = ThreadLocalRandom.current();
		}

		// Fisher-Yates Algorithm.
		for (int i = a.length - 1; i != 0; --i) {
			ArrayUtil.swapFixedInput(a, i, prng.nextInt(i + 1));
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
		ArrayUtil.shuffle(a, null);
	}

	/**
	 * @param a
	 *            the given short[]
	 * 
	 * @param prng
	 *            source of random bits used to generate indices
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 */
	public static void shuffle(short[] a, Random prng) throws NullPointerException {
		if (a.length < 2) { // i.e., Nothing to shuffle.
			return;
		}
		// 2 <= a.length
		if (prng == null) {
			prng = ThreadLocalRandom.current();
		}

		// Fisher-Yates Algorithm.
		for (int i = a.length - 1; i != 0; --i) {
			ArrayUtil.swapFixedInput(a, i, prng.nextInt(i + 1));
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
		ArrayUtil.shuffle(a, null);
	}

	/**
	 * @param a
	 *            the given int[]
	 * 
	 * @param prng
	 *            source of random bits used to generate indices
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 */
	public static void shuffle(int[] a, Random prng) throws NullPointerException {
		if (a.length < 2) { // i.e., Nothing to shuffle.
			return;
		}
		// 2 <= a.length
		if (prng == null) {
			prng = ThreadLocalRandom.current();
		}

		// Fisher-Yates Algorithm.
		for (int i = a.length - 1; i != 0; --i) {
			ArrayUtil.swapFixedInput(a, i, prng.nextInt(i + 1));
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
		ArrayUtil.shuffle(a, null);
	}

	/**
	 * @param a
	 *            the given long[]
	 * 
	 * @param prng
	 *            source of random bits used to generate indices
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 */
	public static void shuffle(long[] a, Random prng) throws NullPointerException {
		if (a.length < 2) { // i.e., Nothing to shuffle.
			return;
		}
		// 2 <= a.length
		if (prng == null) {
			prng = ThreadLocalRandom.current();
		}

		// Fisher-Yates Algorithm.
		for (int i = a.length - 1; i != 0; --i) {
			ArrayUtil.swapFixedInput(a, i, prng.nextInt(i + 1));
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
		ArrayUtil.shuffle(a, null);
	}

	/**
	 * @param a
	 *            the given float[]
	 * 
	 * @param prng
	 *            source of random bits used to generate indices
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 */
	public static void shuffle(float[] a, Random prng) throws NullPointerException {
		if (a.length < 2) { // i.e., Nothing to shuffle.
			return;
		}
		// 2 <= a.length
		if (prng == null) {
			prng = ThreadLocalRandom.current();
		}

		// Fisher-Yates Algorithm.
		for (int i = a.length - 1; i != 0; --i) {
			ArrayUtil.swapFixedInput(a, i, prng.nextInt(i + 1));
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
		ArrayUtil.shuffle(a, null);
	}

	/**
	 * @param a
	 *            the given double[]
	 * 
	 * @param prng
	 *            source of random bits used to generate indices
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 */
	public static void shuffle(double[] a, Random prng) throws NullPointerException {
		if (a.length < 2) { // i.e., Nothing to shuffle.
			return;
		}
		// 2 <= a.length
		if (prng == null) {
			prng = ThreadLocalRandom.current();
		}

		// Fisher-Yates Algorithm.
		for (int i = a.length - 1; i != 0; --i) {
			ArrayUtil.swapFixedInput(a, i, prng.nextInt(i + 1));
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
		ArrayUtil.shuffle(a, null);
	}

	/**
	 * @param a
	 *            the given Object[]
	 * 
	 * @param prng
	 *            source of random bits used to generate indices
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 */
	public static void shuffle(Object[] a, Random prng) throws NullPointerException {
		if (a.length < 2) { // i.e., Nothing to shuffle.
			return;
		}
		// 2 <= a.length
		if (prng == null) {
			prng = ThreadLocalRandom.current();
		}

		// Fisher-Yates Algorithm.
		for (int i = a.length - 1; i != 0; --i) {
			ArrayUtil.swapFixedInput(a, i, prng.nextInt(i + 1));
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
		ArrayUtil.shuffle(a, null);
	}
}
