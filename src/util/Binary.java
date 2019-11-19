package util;

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
	 * Number of bits per one char.
	 */
	public static final int BITS_PER_CHAR = Character.SIZE;

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
	 * Mask for char to int conversion using & (i.e., bitwise and).
	 */
	public static final int MASK_CHAR_TO_INT = 0xFFFF;

	/**
	 * Mask for short to int conversion using & (i.e., bitwise and).
	 */
	public static final int MASK_SHORT_TO_INT = 0xFFFF;

	/**
	 * Mask for byte to long conversion using & (i.e., bitwise and).
	 */
	public static final long MASK_BYTE_TO_LONG = 0xFFL;

	/**
	 * Mask for char to long conversion using & (i.e., bitwise and).
	 */
	public static final long MASK_CHAR_TO_LONG = 0xFFFFL;

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
		final StringBuilder sb = new StringBuilder(Binary.BITS_PER_BYTE);
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
		final StringBuilder sb = new StringBuilder(data.length);
		for (int i = 0; i != data.length; ++i) {
			sb.append(data[i] ? "1" : "0"); // i.e., Binary.toString(data[i])
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
		final StringBuilder sb = new StringBuilder(data.length);
		for (int i = 0; i != data.length; ++i) {
			sb.append(Binary.toString(data[i]));
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
		int result = 0;
		for (int int_b = b; int_b != 0; int_b >>>= 1) {
			/**
			 * Don't do <code>int_b &= 1</code> since we need the value of <code>int_b</code> to remain
			 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
			 * will mutate <code>int_b</code>.
			 */
			result += (int_b & 1);
		}
		return result;
	}

	/**
	 * @param c
	 *            the given char
	 * 
	 * @return The number of "on" bits (i.e., equal to 1) in the given char.
	 */
	public static int countOnes(char c) {
		int result = 0;
		for (int int_c = c; int_c != 0; int_c >>>= 1) {
			/**
			 * Don't do <code>int_c &= 1</code> since we need the value of <code>int_c</code> to remain
			 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
			 * will mutate <code>int_c</code>.
			 */
			result += (int_c & 1);
		}
		return result;
	}

	/**
	 * @param s
	 *            the given short
	 * 
	 * @return The number of "on" bits (i.e., equal to 1) in the given short.
	 */
	public static int countOnes(short s) {
		int result = 0;
		for (int int_s = s; int_s != 0; int_s >>>= 1) {
			/**
			 * Don't do <code>int_s &= 1</code> since we need the value of <code>int_s</code> to remain
			 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
			 * will mutate <code>int_s</code>.
			 */
			result += (int_s & 1);
		}
		return result;
	}

	/**
	 * @param i
	 *            the given int
	 * 
	 * @return The number of "on" bits (i.e., equal to 1) in the given int.
	 */
	public static int countOnes(int i) {
		int result = 0;
		for (/* Already initialized. */; i != 0; i >>>= 1) {
			/**
			 * Don't do <code>i &= 1</code> since we need the value of <code>i</code> to remain unchanged. Note
			 * that the difference is the <code>&=</code> instead of the <code>&</code> which will mutate
			 * <code>i</code>.
			 */
			result += (i & 1);
		}
		return result;
	}

	/**
	 * @param l
	 *            the given long
	 * 
	 * @return The number of "on" bits (i.e., equal to 1) in the given long.
	 */
	public static int countOnes(long l) {
		int result = 0;
		for (/* Already initialized. */; l != 0L; l >>>= 1L) {
			/**
			 * Don't do <code>l &= 1L</code> since we need the value of <code>l</code> to remain unchanged. Note
			 * that the difference is the <code>&=</code> instead of the <code>&</code> which will mutate
			 * <code>l</code>.
			 */
			result += (l & 1L);
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
		for (int i = 0; i != data.length; ++i) {
			result += (data[i] ? 1 : 0); // i.e., Binary.countOnes(data[i])
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
		for (int i = 0; i != data.length; ++i) {
			result += Binary.countOnes(data[i]);
		}
		return result;
	}

	/**
	 * @param data
	 *            the given char array
	 * 
	 * @return The number of "on" bits (i.e., equal to 1) in the given char array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static int countOnes(char[] data) throws NullPointerException {
		int result = 0;
		for (int i = 0; i != data.length; ++i) {
			result += Binary.countOnes(data[i]);
		}
		return result;
	}

	/**
	 * @param data
	 *            the given short array
	 * 
	 * @return The number of "on" bits (i.e., equal to 1) in the given short array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static int countOnes(short[] data) throws NullPointerException {
		int result = 0;
		for (int i = 0; i != data.length; ++i) {
			result += Binary.countOnes(data[i]);
		}
		return result;
	}

	/**
	 * @param data
	 *            the given int array
	 * 
	 * @return The number of "on" bits (i.e., equal to 1) in the given int array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static int countOnes(int[] data) throws NullPointerException {
		int result = 0;
		for (int i = 0; i != data.length; ++i) {
			result += Binary.countOnes(data[i]);
		}
		return result;
	}

	/**
	 * @param data
	 *            the given long array
	 * 
	 * @return The number of "on" bits (i.e., equal to 1) in the given long array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static int countOnes(long[] data) throws NullPointerException {
		int result = 0;
		for (int i = 0; i != data.length; ++i) {
			result += Binary.countOnes(data[i]);
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
		return (b ? 0 : 1); // i.e., 1 - Binary.countOnes(b)
	}

	/**
	 * @param b
	 *            the given byte
	 * 
	 * @return The number of "off" bits (i.e., equal to 0) in the given byte.
	 */
	public static int countZeroes(byte b) {
		/**
		 * The same algorithm that was used for <code>Binary.countOnes(byte)</code> may not work here.
		 * Instead, we count all of the ones, and then subtract them from the total number of bits.
		 */
		return (Binary.BITS_PER_BYTE - Binary.countOnes(b));
	}

	/**
	 * @param c
	 *            the given char
	 * 
	 * @return The number of "off" bits (i.e., equal to 0) in the given char.
	 */
	public static int countZeroes(char c) {
		/**
		 * The same algorithm that was used for <code>Binary.countOnes(char)</code> may not work here.
		 * Instead, we count all of the ones, and then subtract them from the total number of bits.
		 */
		return (Binary.BITS_PER_CHAR - Binary.countOnes(c));
	}

	/**
	 * @param s
	 *            the given short
	 * 
	 * @return The number of "off" bits (i.e., equal to 0) in the given short.
	 */
	public static int countZeroes(short s) {
		/**
		 * The same algorithm that was used for <code>Binary.countOnes(short)</code> may not work here.
		 * Instead, we count all of the ones, and then subtract them from the total number of bits.
		 */
		return (Binary.BITS_PER_SHORT - Binary.countOnes(s));
	}

	/**
	 * @param i
	 *            the given int
	 * 
	 * @return The number of "off" bits (i.e., equal to 0) in the given int.
	 */
	public static int countZeroes(int i) {
		/**
		 * The same algorithm that was used for <code>Binary.countOnes(int)</code> may not work here.
		 * Instead, we count all of the ones, and then subtract them from the total number of bits.
		 */
		return (Binary.BITS_PER_INT - Binary.countOnes(i));
	}

	/**
	 * @param l
	 *            the given long
	 * 
	 * @return The number of "off" bits (i.e., equal to 0) in the given long.
	 */
	public static int countZeroes(long l) {
		/**
		 * The same algorithm that was used for <code>Binary.countOnes(long)</code> may not work here.
		 * Instead, we count all of the ones, and then subtract them from the total number of bits.
		 */
		return (Binary.BITS_PER_LONG - Binary.countOnes(l));
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
		for (int i = 0; i != data.length; ++i) {
			result += (data[i] ? 0 : 1); // i.e., Binary.countZeroes(data[i])
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
		for (int i = 0; i != data.length; ++i) {
			result += (Binary.BITS_PER_BYTE - Binary.countOnes(data[i])); // i.e., Binary.countZeroes(data[i])
		}
		return result;
	}

	/**
	 * @param data
	 *            the given char array
	 * 
	 * @return The number of "off" bits (i.e., equal to 0) in the given char array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static int countZeroes(char[] data) throws NullPointerException {
		int result = 0;
		for (int i = 0; i != data.length; ++i) {
			result += (Binary.BITS_PER_CHAR - Binary.countOnes(data[i])); // i.e., Binary.countZeroes(data[i]);
		}
		return result;
	}

	/**
	 * @param data
	 *            the given short array
	 * 
	 * @return The number of "off" bits (i.e., equal to 0) in the given short array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static int countZeroes(short[] data) throws NullPointerException {
		int result = 0;
		for (int i = 0; i != data.length; ++i) {
			result += (Binary.BITS_PER_SHORT - Binary.countOnes(data[i])); // i.e., Binary.countZeroes(data[i])
		}
		return result;
	}

	/**
	 * @param data
	 *            the given int array
	 * 
	 * @return The number of "off" bits (i.e., equal to 0) in the given int array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static int countZeroes(int[] data) throws NullPointerException {
		int result = 0;
		for (int i = 0; i != data.length; ++i) {
			result += (Binary.BITS_PER_INT - Binary.countOnes(data[i])); // i.e., Binary.countZeroes(data[i])
		}
		return result;
	}

	/**
	 * @param data
	 *            the given long array
	 * 
	 * @return The number of "off" bits (i.e., equal to 0) in the given long array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static int countZeroes(long[] data) throws NullPointerException {
		int result = 0;
		for (int i = 0; i != data.length; ++i) {
			result += (Binary.BITS_PER_LONG - Binary.countOnes(data[i])); // i.e., Binary.countZeroes(data[i])
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
		final boolean[] result = new boolean[data.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = !data[i];
		}
		return result;
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
		final byte[] result = new byte[data.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = (byte) ~data[i];
		}
		return result;
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
		final char[] result = new char[data.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = (char) ~data[i];
		}
		return result;
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
		final short[] result = new short[data.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = (short) ~data[i];
		}
		return result;
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
		final int[] result = new int[data.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = ~data[i];
		}
		return result;
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
		final long[] result = new long[data.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = ~data[i];
		}
		return result;
	}

	/**
	 * <code>lhs &= rhs</code>.
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
			lhs[i] &= rhs[i];
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
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final boolean[] result = new boolean[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = lhs[i] && rhs[i];
		}
		return result;
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
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final byte[] result = new byte[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = (byte) (lhs[i] & rhs[i]);
		}
		return result;
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
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final char[] result = new char[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = (char) (lhs[i] & rhs[i]);
		}
		return result;
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
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final short[] result = new short[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = (short) (lhs[i] & rhs[i]);
		}
		return result;
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
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final int[] result = new int[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = lhs[i] & rhs[i];
		}
		return result;
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
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final long[] result = new long[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = lhs[i] & rhs[i];
		}
		return result;
	}

	/**
	 * <code>lhs |= rhs</code>.
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
			lhs[i] |= rhs[i];
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
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final boolean[] result = new boolean[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = lhs[i] || rhs[i];
		}
		return result;
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
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final byte[] result = new byte[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = (byte) (lhs[i] | rhs[i]);
		}
		return result;
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
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final char[] result = new char[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = (char) (lhs[i] | rhs[i]);
		}
		return result;
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
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final short[] result = new short[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = (short) (lhs[i] | rhs[i]);
		}
		return result;
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
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final int[] result = new int[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = lhs[i] | rhs[i];
		}
		return result;
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
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final long[] result = new long[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = lhs[i] | rhs[i];
		}
		return result;
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
			lhs[i] ^= rhs[i];
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
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final boolean[] result = new boolean[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = lhs[i] ^ rhs[i];
		}
		return result;
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
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final byte[] result = new byte[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = (byte) (lhs[i] ^ rhs[i]);
		}
		return result;
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
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final char[] result = new char[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = (char) (lhs[i] ^ rhs[i]);
		}
		return result;
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
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final short[] result = new short[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = (short) (lhs[i] ^ rhs[i]);
		}
		return result;
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
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final int[] result = new int[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = lhs[i] ^ rhs[i];
		}
		return result;
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
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final long[] result = new long[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = lhs[i] ^ rhs[i];
		}
		return result;
	}
}
