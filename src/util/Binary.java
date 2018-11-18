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
	public static final int BITS_PER_BYTE = 8;

	/**
	 * Mask for byte conversion.
	 */
	public static final int BYTE_CONVERTER = 0xFF;

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
		final StringBuilder result = new StringBuilder();
		final String binStr = Integer.toBinaryString(b & Binary.BYTE_CONVERTER);
		// Pad result with zeroes on the left to make it have Binary.BITS_PER_BYTE chars in total.
		for (int i = Binary.BITS_PER_BYTE - binStr.length(); i != 0; --i) {
			result.append('0');
		}
		return result.append(binStr).toString();
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
	 *            the given byte
	 * 
	 * @return The number of "on" bits (i.e., equal to 1) in the given byte.
	 */
	public static int countOnes(byte b) {
		final String binStr = Integer.toBinaryString(b & Binary.BYTE_CONVERTER);
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
}
