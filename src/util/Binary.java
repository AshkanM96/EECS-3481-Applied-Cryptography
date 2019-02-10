package util;

import java.util.Arrays;

/**
 * Utility binary methods.
 * 
 * @author Professor Hamzeh Roumani
 * @author Ashkan Moatamed
 */
public class Binary {
	/**
	 * No dependencies.
	 */

	/**
	 * Number of bits per one byte.
	 */
	public static final int BITS_PER_BYTE = Byte.SIZE;

	/**
	 * Number of bits per one short.
	 */
	public static final int BITS_PER_SHORT = Short.SIZE;

	/**
	 * Number of bits per one int.
	 */
	public static final int BITS_PER_INT = Integer.SIZE;

	/**
	 * Number of bits per one long.
	 */
	public static final int BITS_PER_LONG = Long.SIZE;

	/**
	 * Mask for byte to int conversion using & (i.e., bitwise and).
	 */
	public static final int MASK_BYTE_TO_INT = 0xFF;

	/**
	 * Mask for short to int conversion using & (i.e., bitwise and).
	 */
	public static final int MASK_SHORT_TO_INT = 0xFFFF;

	/**
	 * Mask for byte to long conversion using & (i.e., bitwise and).
	 */
	public static final long MASK_BYTE_TO_LONG = 0xFFL;

	/**
	 * Mask for short to long conversion using & (i.e., bitwise and).
	 */
	public static final long MASK_SHORT_TO_LONG = 0xFFFFL;

	/**
	 * Mask for int to long conversion using & (i.e., bitwise and).
	 */
	public static final long MASK_INT_TO_LONG = 0xFFFFFFFFL;

	/**
	 * Prevent instantiation.
	 */
	private Binary() {
		// Empty by design.
	}

	/**
	 * Given a boolean, convert it to a string of bits.
	 * 
	 * @param b
	 *            the given boolean
	 * 
	 * @return The resulting string.
	 */
	public static String toString(boolean b) {
		return (b ? "1" : "0");
	}

	/**
	 * Given a byte, convert it to a string of bits.
	 * 
	 * @param b
	 *            the given byte
	 * 
	 * @return The resulting string.
	 */
	public static String toString(byte b) {
		final StringBuilder sb = new StringBuilder();
		final String result = Integer.toBinaryString(b & Binary.MASK_BYTE_TO_INT);
		// Pad with zeroes on the left to have Binary.BITS_PER_BYTE chars in total.
		for (int i = Binary.BITS_PER_BYTE - result.length(); i != 0; --i) {
			sb.append('0');
		}
		return sb.append(result).toString();
	}

	/**
	 * Given a boolean array, convert it to a string of bits.
	 * 
	 * @param data
	 *            the given boolean array
	 * 
	 * @return The resulting string.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static String toString(boolean[] data) throws NullPointerException {
		final StringBuilder sb = new StringBuilder();
		for (final boolean b : data) {
			sb.append(Binary.toString(b));
		}
		return sb.toString();
	}

	/**
	 * Given a byte array, convert it to a string of bits.
	 * 
	 * @param data
	 *            the given byte array
	 * 
	 * @return The resulting string.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static String toString(byte[] data) throws NullPointerException {
		final StringBuilder sb = new StringBuilder();
		for (final byte b : data) {
			sb.append(Binary.toString(b));
		}
		return sb.toString();
	}

	/**
	 * @param b
	 *            the given boolean
	 * 
	 * @return The number of "on" bits (i.e., equal to 1) in the given boolean.
	 */
	public static int countOnes(boolean b) {
		return (b ? 1 : 0);
	}

	/**
	 * @param b
	 *            the given byte
	 * 
	 * @return The number of "on" bits (i.e., equal to 1) in the given byte.
	 */
	public static int countOnes(byte b) {
		final String binStr = Integer.toBinaryString(b & Binary.MASK_BYTE_TO_INT);
		int result = 0;
		for (int i = 0; i != binStr.length(); ++i) {
			if (binStr.charAt(i) == '1') {
				++result;
			}
		}
		return result;
	}

	/**
	 * @param data
	 *            the given boolean array
	 * 
	 * @return The number of "on" bits (i.e., equal to 1) in the given boolean array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static int countOnes(boolean[] data) throws NullPointerException {
		int result = 0;
		for (final boolean b : data) {
			result += Binary.countOnes(b);
		}
		return result;
	}

	/**
	 * @param data
	 *            the given byte array
	 * 
	 * @return The number of "on" bits (i.e., equal to 1) in the given byte array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static int countOnes(byte[] data) throws NullPointerException {
		int result = 0;
		for (final byte b : data) {
			result += Binary.countOnes(b);
		}
		return result;
	}

	/**
	 * @param b
	 *            the given boolean
	 * 
	 * @return The number of "off" bits (i.e., equal to 0) in the given boolean.
	 */
	public static int countZeroes(boolean b) {
		return (b ? 0 : 1);
	}

	/**
	 * @param b
	 *            the given byte
	 * 
	 * @return The number of "off" bits (i.e., equal to 0) in the given byte.
	 */
	public static int countZeroes(byte b) {
		/**
		 * <code>Integer.toBinaryString(b & Binary.BYTE_CONVERTER)</code> may not include some zeroes on the
		 * left end which is why the same algorithm used for <code>Binary::countOnes(byte)</code> may not
		 * work here. Instead, we count all of the ones and then subtract them from the total number of
		 * bits.
		 */
		return (Binary.BITS_PER_BYTE - Binary.countOnes(b));
	}

	/**
	 * @param data
	 *            the given boolean array
	 * 
	 * @return The number of "off" bits (i.e., equal to 0) in the given boolean array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static int countZeroes(boolean[] data) throws NullPointerException {
		int result = 0;
		for (final boolean b : data) {
			result += Binary.countZeroes(b);
		}
		return result;
	}

	/**
	 * @param data
	 *            the given byte array
	 * 
	 * @return The number of "off" bits (i.e., equal to 0) in the given byte array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static int countZeroes(byte[] data) throws NullPointerException {
		int result = 0;
		for (final byte b : data) {
			result += Binary.countZeroes(b);
		}
		return result;
	}

	/**
	 * <code>data = !data</code>.
	 * 
	 * @param data
	 *            the given boolean array
	 * 
	 * @return <code>data</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static boolean[] compEquals(boolean[] data) throws NullPointerException {
		for (int i = 0; i != data.length; ++i) {
			data[i] = !data[i];
		}
		return data;
	}

	/**
	 * <code>data ~= data</code>.
	 * 
	 * @param data
	 *            the given byte array
	 * 
	 * @return <code>data</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static byte[] compEquals(byte[] data) throws NullPointerException {
		for (int i = 0; i != data.length; ++i) {
			data[i] = (byte) ~data[i];
		}
		return data;
	}

	/**
	 * <code>data ~= data</code>.
	 * 
	 * @param data
	 *            the given char array
	 * 
	 * @return <code>data</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static char[] compEquals(char[] data) throws NullPointerException {
		for (int i = 0; i != data.length; ++i) {
			data[i] = (char) ~data[i];
		}
		return data;
	}

	/**
	 * <code>data ~= data</code>.
	 * 
	 * @param data
	 *            the given short array
	 * 
	 * @return <code>data</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static short[] compEquals(short[] data) throws NullPointerException {
		for (int i = 0; i != data.length; ++i) {
			data[i] = (short) ~data[i];
		}
		return data;
	}

	/**
	 * <code>data ~= data</code>.
	 * 
	 * @param data
	 *            the given int array
	 * 
	 * @return <code>data</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static int[] compEquals(int[] data) throws NullPointerException {
		for (int i = 0; i != data.length; ++i) {
			data[i] = ~data[i];
		}
		return data;
	}

	/**
	 * <code>data ~= data</code>.
	 * 
	 * @param data
	 *            the given long array
	 * 
	 * @return <code>data</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static long[] compEquals(long[] data) throws NullPointerException {
		for (int i = 0; i != data.length; ++i) {
			data[i] = ~data[i];
		}
		return data;
	}

	/**
	 * Compute the complement of the given boolean array.
	 * 
	 * @param data
	 *            the given boolean array
	 * 
	 * @return <code>Binary.compEquals(Arrays.copyOf(data, data.length))</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static boolean[] comp(boolean[] data) throws NullPointerException {
		return Binary.compEquals(Arrays.copyOf(data, data.length));
	}

	/**
	 * Compute the bitwise complement of the given byte array.
	 * 
	 * @param data
	 *            the given byte array
	 * 
	 * @return <code>Binary.compEquals(Arrays.copyOf(data, data.length))</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static byte[] comp(byte[] data) throws NullPointerException {
		return Binary.compEquals(Arrays.copyOf(data, data.length));
	}

	/**
	 * Compute the bitwise complement of the given char array.
	 * 
	 * @param data
	 *            the given char array
	 * 
	 * @return <code>Binary.compEquals(Arrays.copyOf(data, data.length))</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static char[] comp(char[] data) throws NullPointerException {
		return Binary.compEquals(Arrays.copyOf(data, data.length));
	}

	/**
	 * Compute the bitwise complement of the given short array.
	 * 
	 * @param data
	 *            the given short array
	 * 
	 * @return <code>Binary.compEquals(Arrays.copyOf(data, data.length))</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static short[] comp(short[] data) throws NullPointerException {
		return Binary.compEquals(Arrays.copyOf(data, data.length));
	}

	/**
	 * Compute the bitwise complement of the given int array.
	 * 
	 * @param data
	 *            the given int array
	 * 
	 * @return <code>Binary.compEquals(Arrays.copyOf(data, data.length))</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static int[] comp(int[] data) throws NullPointerException {
		return Binary.compEquals(Arrays.copyOf(data, data.length));
	}

	/**
	 * Compute the bitwise complement of the given long array.
	 * 
	 * @param data
	 *            the given long array
	 * 
	 * @return <code>Binary.compEquals(Arrays.copyOf(data, data.length))</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static long[] comp(long[] data) throws NullPointerException {
		return Binary.compEquals(Arrays.copyOf(data, data.length));
	}

	/**
	 * <code>lhs &&= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side boolean array
	 * 
	 * @param rhs
	 *            the given right hand side boolean array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static boolean[] andEquals(boolean[] lhs, boolean[] rhs)
			throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] = lhs[i] && rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs &= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side byte array
	 * 
	 * @param rhs
	 *            the given right hand side byte array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static byte[] andEquals(byte[] lhs, byte[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] &= rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs &= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side char array
	 * 
	 * @param rhs
	 *            the given right hand side char array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static char[] andEquals(char[] lhs, char[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] &= rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs &= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side short array
	 * 
	 * @param rhs
	 *            the given right hand side short array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static short[] andEquals(short[] lhs, short[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] &= rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs &= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side int array
	 * 
	 * @param rhs
	 *            the given right hand side int array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static int[] andEquals(int[] lhs, int[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] &= rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs &= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side long array
	 * 
	 * @param rhs
	 *            the given right hand side long array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static long[] andEquals(long[] lhs, long[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] &= rhs[i];
		}
		return lhs;
	}

	/**
	 * Compute the and of the two given boolean arrays.
	 * 
	 * @param lhs
	 *            the given left hand side boolean array
	 * 
	 * @param rhs
	 *            the given right hand side boolean array
	 * 
	 * @return <code>Binary.andEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static boolean[] and(boolean[] lhs, boolean[] rhs) throws NullPointerException, IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a copy construction.
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}
		return Binary.andEquals(Arrays.copyOf(lhs, lhs.length), rhs);
	}

	/**
	 * Compute the bitwise and of the two given byte arrays.
	 * 
	 * @param lhs
	 *            the given left hand side byte array
	 * 
	 * @param rhs
	 *            the given right hand side byte array
	 * 
	 * @return <code>Binary.andEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static byte[] and(byte[] lhs, byte[] rhs) throws NullPointerException, IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a copy construction.
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}
		return Binary.andEquals(Arrays.copyOf(lhs, lhs.length), rhs);
	}

	/**
	 * Compute the bitwise and of the two given char arrays.
	 * 
	 * @param lhs
	 *            the given left hand side char array
	 * 
	 * @param rhs
	 *            the given right hand side char array
	 * 
	 * @return <code>Binary.andEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static char[] and(char[] lhs, char[] rhs) throws NullPointerException, IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a copy construction.
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}
		return Binary.andEquals(Arrays.copyOf(lhs, lhs.length), rhs);
	}

	/**
	 * Compute the bitwise and of the two given short arrays.
	 * 
	 * @param lhs
	 *            the given left hand side short array
	 * 
	 * @param rhs
	 *            the given right hand side short array
	 * 
	 * @return <code>Binary.andEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static short[] and(short[] lhs, short[] rhs) throws NullPointerException, IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a copy construction.
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}
		return Binary.andEquals(Arrays.copyOf(lhs, lhs.length), rhs);
	}

	/**
	 * Compute the bitwise and of the two given int arrays.
	 * 
	 * @param lhs
	 *            the given left hand side int array
	 * 
	 * @param rhs
	 *            the given right hand side int array
	 * 
	 * @return <code>Binary.andEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static int[] and(int[] lhs, int[] rhs) throws NullPointerException, IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a copy construction.
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}
		return Binary.andEquals(Arrays.copyOf(lhs, lhs.length), rhs);
	}

	/**
	 * Compute the bitwise and of the two given long arrays.
	 * 
	 * @param lhs
	 *            the given left hand side long array
	 * 
	 * @param rhs
	 *            the given right hand side long array
	 * 
	 * @return <code>Binary.andEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static long[] and(long[] lhs, long[] rhs) throws NullPointerException, IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a copy construction.
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}
		return Binary.andEquals(Arrays.copyOf(lhs, lhs.length), rhs);
	}

	/**
	 * <code>lhs ||= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side boolean array
	 * 
	 * @param rhs
	 *            the given right hand side boolean array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static boolean[] orEquals(boolean[] lhs, boolean[] rhs)
			throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] = lhs[i] || rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs |= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side byte array
	 * 
	 * @param rhs
	 *            the given right hand side byte array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static byte[] orEquals(byte[] lhs, byte[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] |= rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs |= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side char array
	 * 
	 * @param rhs
	 *            the given right hand side char array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static char[] orEquals(char[] lhs, char[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] |= rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs |= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side short array
	 * 
	 * @param rhs
	 *            the given right hand side short array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static short[] orEquals(short[] lhs, short[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] |= rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs |= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side int array
	 * 
	 * @param rhs
	 *            the given right hand side int array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static int[] orEquals(int[] lhs, int[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] |= rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs |= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side long array
	 * 
	 * @param rhs
	 *            the given right hand side long array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static long[] orEquals(long[] lhs, long[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] |= rhs[i];
		}
		return lhs;
	}

	/**
	 * Compute the or of the two given boolean arrays.
	 * 
	 * @param lhs
	 *            the given left hand side boolean array
	 * 
	 * @param rhs
	 *            the given right hand side boolean array
	 * 
	 * @return <code>Binary.orEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static boolean[] or(boolean[] lhs, boolean[] rhs) throws NullPointerException, IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a copy construction.
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}
		return Binary.orEquals(Arrays.copyOf(lhs, lhs.length), rhs);
	}

	/**
	 * Compute the bitwise or of the two given byte arrays.
	 * 
	 * @param lhs
	 *            the given left hand side byte array
	 * 
	 * @param rhs
	 *            the given right hand side byte array
	 * 
	 * @return <code>Binary.orEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static byte[] or(byte[] lhs, byte[] rhs) throws NullPointerException, IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a copy construction.
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}
		return Binary.orEquals(Arrays.copyOf(lhs, lhs.length), rhs);
	}

	/**
	 * Compute the bitwise or of the two given char arrays.
	 * 
	 * @param lhs
	 *            the given left hand side char array
	 * 
	 * @param rhs
	 *            the given right hand side char array
	 * 
	 * @return <code>Binary.orEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static char[] or(char[] lhs, char[] rhs) throws NullPointerException, IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a copy construction.
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}
		return Binary.orEquals(Arrays.copyOf(lhs, lhs.length), rhs);
	}

	/**
	 * Compute the bitwise or of the two given short arrays.
	 * 
	 * @param lhs
	 *            the given left hand side short array
	 * 
	 * @param rhs
	 *            the given right hand side short array
	 * 
	 * @return <code>Binary.orEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static short[] or(short[] lhs, short[] rhs) throws NullPointerException, IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a copy construction.
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}
		return Binary.orEquals(Arrays.copyOf(lhs, lhs.length), rhs);
	}

	/**
	 * Compute the bitwise or of the two given int arrays.
	 * 
	 * @param lhs
	 *            the given left hand side int array
	 * 
	 * @param rhs
	 *            the given right hand side int array
	 * 
	 * @return <code>Binary.orEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static int[] or(int[] lhs, int[] rhs) throws NullPointerException, IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a copy construction.
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}
		return Binary.orEquals(Arrays.copyOf(lhs, lhs.length), rhs);
	}

	/**
	 * Compute the bitwise or of the two given long arrays.
	 * 
	 * @param lhs
	 *            the given left hand side long array
	 * 
	 * @param rhs
	 *            the given right hand side long array
	 * 
	 * @return <code>Binary.orEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static long[] or(long[] lhs, long[] rhs) throws NullPointerException, IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a copy construction.
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}
		return Binary.orEquals(Arrays.copyOf(lhs, lhs.length), rhs);
	}

	/**
	 * <code>lhs ^= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side boolean array
	 * 
	 * @param rhs
	 *            the given right hand side boolean array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static boolean[] xorEquals(boolean[] lhs, boolean[] rhs)
			throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			if (lhs[i]) {
				if (rhs[i]) {
					// Both are true.
					lhs[i] = false;
				} else {
					// One is true and one is false.
					lhs[i] = true;
				}
			} else {
				if (rhs[i]) {
					// One is true and one is false.
					lhs[i] = true;
				} else {
					// Both are false.
					lhs[i] = false;
				}
			}
		}
		return lhs;
	}

	/**
	 * <code>lhs ^= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side byte array
	 * 
	 * @param rhs
	 *            the given right hand side byte array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static byte[] xorEquals(byte[] lhs, byte[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] ^= rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs ^= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side char array
	 * 
	 * @param rhs
	 *            the given right hand side char array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static char[] xorEquals(char[] lhs, char[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] ^= rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs ^= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side short array
	 * 
	 * @param rhs
	 *            the given right hand side short array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static short[] xorEquals(short[] lhs, short[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] ^= rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs ^= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side int array
	 * 
	 * @param rhs
	 *            the given right hand side int array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static int[] xorEquals(int[] lhs, int[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] ^= rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs ^= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side long array
	 * 
	 * @param rhs
	 *            the given right hand side long array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static long[] xorEquals(long[] lhs, long[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] ^= rhs[i];
		}
		return lhs;
	}

	/**
	 * Compute the xor of the two given boolean arrays.
	 * 
	 * @param lhs
	 *            the given left hand side boolean array
	 * 
	 * @param rhs
	 *            the given right hand side boolean array
	 * 
	 * @return <code>Binary.xorEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static boolean[] xor(boolean[] lhs, boolean[] rhs) throws NullPointerException, IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a copy construction.
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}
		return Binary.xorEquals(Arrays.copyOf(lhs, lhs.length), rhs);
	}

	/**
	 * Compute the bitwise xor of the two given byte arrays.
	 * 
	 * @param lhs
	 *            the given left hand side byte array
	 * 
	 * @param rhs
	 *            the given right hand side byte array
	 * 
	 * @return <code>Binary.xorEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static byte[] xor(byte[] lhs, byte[] rhs) throws NullPointerException, IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a copy construction.
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}
		return Binary.xorEquals(Arrays.copyOf(lhs, lhs.length), rhs);
	}

	/**
	 * Compute the bitwise xor of the two given char arrays.
	 * 
	 * @param lhs
	 *            the given left hand side char array
	 * 
	 * @param rhs
	 *            the given right hand side char array
	 * 
	 * @return <code>Binary.xorEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static char[] xor(char[] lhs, char[] rhs) throws NullPointerException, IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a copy construction.
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}
		return Binary.xorEquals(Arrays.copyOf(lhs, lhs.length), rhs);
	}

	/**
	 * Compute the bitwise xor of the two given short arrays.
	 * 
	 * @param lhs
	 *            the given left hand side short array
	 * 
	 * @param rhs
	 *            the given right hand side short array
	 * 
	 * @return <code>Binary.xorEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static short[] xor(short[] lhs, short[] rhs) throws NullPointerException, IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a copy construction.
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}
		return Binary.xorEquals(Arrays.copyOf(lhs, lhs.length), rhs);
	}

	/**
	 * Compute the bitwise xor of the two given int arrays.
	 * 
	 * @param lhs
	 *            the given left hand side int array
	 * 
	 * @param rhs
	 *            the given right hand side int array
	 * 
	 * @return <code>Binary.xorEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static int[] xor(int[] lhs, int[] rhs) throws NullPointerException, IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a copy construction.
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}
		return Binary.xorEquals(Arrays.copyOf(lhs, lhs.length), rhs);
	}

	/**
	 * Compute the bitwise xor of the two given long arrays.
	 * 
	 * @param lhs
	 *            the given left hand side long array
	 * 
	 * @param rhs
	 *            the given right hand side long array
	 * 
	 * @return <code>Binary.xorEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static long[] xor(long[] lhs, long[] rhs) throws NullPointerException, IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a copy construction.
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}
		return Binary.xorEquals(Arrays.copyOf(lhs, lhs.length), rhs);
	}
}
