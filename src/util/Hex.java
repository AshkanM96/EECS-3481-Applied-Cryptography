package util;

/**
 * Utility hex methods.
 * 
 * @author Professor Hamzeh Roumani
 * @author Ashkan Moatamed
 */
public class Hex {
	/**
	 * Dependencies: <code>
	 * 		1. util.Binary
	 * </code>
	 */

	/**
	 * Hexadecimal radix value.
	 */
	public static final int RADIX = 16;

	/**
	 * Number of bits needed to store one hex digit.
	 */
	public static final int BITS_PER_HEXD = 4; // (int) MathUtil.log(Hex.RADIX, Binary.RADIX)

	/**
	 * Number of hex digits needed to store one byte.
	 */
	public static final int HEXD_PER_BYTE = Binary.BITS_PER_BYTE / Hex.BITS_PER_HEXD;

	/**
	 * Number of hex digits needed to store one char.
	 */
	public static final int HEXD_PER_CHAR = Binary.BITS_PER_CHAR / Hex.BITS_PER_HEXD;

	/**
	 * Number of hex digits needed to store one short.
	 */
	public static final int HEXD_PER_SHORT = Binary.BITS_PER_SHORT / Hex.BITS_PER_HEXD;

	/**
	 * Number of hex digits needed to store one int.
	 */
	public static final int HEXD_PER_INT = Binary.BITS_PER_INT / Hex.BITS_PER_HEXD;

	/**
	 * Number of hex digits needed to store one long.
	 */
	public static final int HEXD_PER_LONG = Binary.BITS_PER_LONG / Hex.BITS_PER_HEXD;

	/**
	 * Prevent instantiation.
	 */
	private Hex() {
		// Empty by design.
	}

	/**
	 * Given a byte, convert it to a string of hex digits.
	 * 
	 * @param b
	 *            the given byte
	 * 
	 * @return The resulting string.
	 */
	public static String toString(byte b) {
		final StringBuilder sb = new StringBuilder(Hex.HEXD_PER_BYTE);
		/**
		 * Note that we have to use <code>Integer.toHexString</code> instead of
		 * <code>Integer.toString(int, Hex.RADIX)</code>, since the latter includes a negative sign in its
		 * returned string when it needs to. Moreover, we should use the bit mask when passing the given
		 * byte to <code>Integer.toHexString</code>, since the binary representation of the value of
		 * <code>b</code> differs when being represented as an int instead of a byte when <code>b</code> is
		 * negative.
		 */
		final String result = Integer.toHexString(b & Binary.MASK_BYTE_TO_INT);
		// Pad with zeroes on the left to have Hex.HEXD_PER_BYTE chars in total.
		for (int count = Hex.HEXD_PER_BYTE - result.length(); count != 0; --count) {
			sb.append('0');
		}
		return sb.append(result).toString();
	}

	/**
	 * Given a char, convert it to a string of hex digits.
	 * 
	 * @param c
	 *            the given char
	 * 
	 * @return The resulting string.
	 */
	public static String toString(char c) {
		final StringBuilder sb = new StringBuilder(Hex.HEXD_PER_CHAR);
		/**
		 * Note that we have to use <code>Integer.toHexString</code> instead of
		 * <code>Integer.toString(int, Hex.RADIX)</code>, since the latter includes a negative sign in its
		 * returned string when it needs to. Moreover, we should use the bit mask when passing the given
		 * char to <code>Integer.toHexString</code>, since the binary representation of the value of
		 * <code>c</code> differs when being represented as an int instead of a char when <code>c</code> is
		 * negative.
		 */
		final String result = Integer.toHexString(c & Binary.MASK_CHAR_TO_INT);
		// Pad with zeroes on the left to have Hex.HEXD_PER_CHAR chars in total.
		for (int count = Hex.HEXD_PER_CHAR - result.length(); count != 0; --count) {
			sb.append('0');
		}
		return sb.append(result).toString();
	}

	/**
	 * Given a short, convert it to a string of hex digits.
	 * 
	 * @param s
	 *            the given short
	 * 
	 * @return The resulting string.
	 */
	public static String toString(short s) {
		final StringBuilder sb = new StringBuilder(Hex.HEXD_PER_SHORT);
		/**
		 * Note that we have to use <code>Integer.toHexString</code> instead of
		 * <code>Integer.toString(int, Hex.RADIX)</code>, since the latter includes a negative sign in its
		 * returned string when it needs to. Moreover, we should use the bit mask when passing the given
		 * short to <code>Integer.toHexString</code>, since the binary representation of the value of
		 * <code>s</code> differs when being represented as an int instead of a short when <code>s</code> is
		 * negative.
		 */
		final String result = Integer.toHexString(s & Binary.MASK_SHORT_TO_INT);
		// Pad with zeroes on the left to have Hex.HEXD_PER_SHORT chars in total.
		for (int count = Hex.HEXD_PER_SHORT - result.length(); count != 0; --count) {
			sb.append('0');
		}
		return sb.append(result).toString();
	}

	/**
	 * Given an int, convert it to a string of hex digits.
	 * 
	 * @param i
	 *            the given int
	 * 
	 * @return The resulting string.
	 */
	public static String toString(int i) {
		final StringBuilder sb = new StringBuilder(Hex.HEXD_PER_INT);
		/**
		 * Note that we have to use <code>Integer.toHexString</code> instead of
		 * <code>Integer.toString(int, Hex.RADIX)</code>, since the latter includes a negative sign in its
		 * returned string when it needs to.
		 */
		final String result = Integer.toHexString(i);
		// Pad with zeroes on the left to have Hex.HEXD_PER_INT chars in total.
		for (int count = Hex.HEXD_PER_INT - result.length(); count != 0; --count) {
			sb.append('0');
		}
		return sb.append(result).toString();
	}

	/**
	 * Given a long, convert it to a string of hex digits.
	 * 
	 * @param l
	 *            the given long
	 * 
	 * @return The resulting string.
	 */
	public static String toString(long l) {
		final StringBuilder sb = new StringBuilder(Hex.HEXD_PER_LONG);
		/**
		 * Note that we have to use <code>Long.toHexString</code> instead of
		 * <code>Long.toString(long, Hex.RADIX)</code>, since the latter includes a negative sign in its
		 * returned string when it needs to.
		 */
		final String result = Long.toHexString(l);
		// Pad with zeroes on the left to have Hex.HEXD_PER_LONG chars in total.
		for (int count = Hex.HEXD_PER_LONG - result.length(); count != 0; --count) {
			sb.append('0');
		}
		return sb.append(result).toString();
	}

	/**
	 * Given a byte array, convert it to a string of hex digits.
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
		final StringBuilder sb = new StringBuilder(Hex.HEXD_PER_BYTE * data.length);
		for (int i = 0; i != data.length; ++i) {
			sb.append(Hex.toString(data[i]));
		}
		return sb.toString();
	}

	/**
	 * Given a char array, convert it to a string of hex digits.
	 * 
	 * @param data
	 *            the given char array
	 * 
	 * @return The resulting string.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static String toString(char[] data) throws NullPointerException {
		final StringBuilder sb = new StringBuilder(Hex.HEXD_PER_CHAR * data.length);
		for (int i = 0; i != data.length; ++i) {
			sb.append(Hex.toString(data[i]));
		}
		return sb.toString();
	}

	/**
	 * Given a short array, convert it to a string of hex digits.
	 * 
	 * @param data
	 *            the given short array
	 * 
	 * @return The resulting string.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static String toString(short[] data) throws NullPointerException {
		final StringBuilder sb = new StringBuilder(Hex.HEXD_PER_SHORT * data.length);
		for (int i = 0; i != data.length; ++i) {
			sb.append(Hex.toString(data[i]));
		}
		return sb.toString();
	}

	/**
	 * Given an int array, convert it to a string of hex digits.
	 * 
	 * @param data
	 *            the given int array
	 * 
	 * @return The resulting string.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static String toString(int[] data) throws NullPointerException {
		final StringBuilder sb = new StringBuilder(Hex.HEXD_PER_INT * data.length);
		for (int i = 0; i != data.length; ++i) {
			sb.append(Hex.toString(data[i]));
		}
		return sb.toString();
	}

	/**
	 * Given a long array, convert it to a string of hex digits.
	 * 
	 * @param data
	 *            the given long array
	 * 
	 * @return The resulting string.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static String toString(long[] data) throws NullPointerException {
		final StringBuilder sb = new StringBuilder(Hex.HEXD_PER_LONG * data.length);
		for (int i = 0; i != data.length; ++i) {
			sb.append(Hex.toString(data[i]));
		}
		return sb.toString();
	}

	/**
	 * Given a string of hex digits, convert it to a byte array.
	 * 
	 * @param s
	 *            the given string
	 * 
	 * @return The resulting byte array.
	 * 
	 * @throws NullPointerException
	 *             If <code>s == null</code>
	 * 
	 * @throws NumberFormatException
	 *             Thrown by
	 *             <code>Integer.parseInt(s.substring(i, i + Hex.HEXD_PER_BYTE), Hex.RADIX)</code> where
	 *             <code>i</code> is valid
	 */
	public static byte[] toBytes(String s) throws NullPointerException, NumberFormatException {
		final int s_length = s.length();
		final int quotient = s_length / Hex.HEXD_PER_BYTE, remainder = s_length - Hex.HEXD_PER_BYTE * quotient;
		// remainder == s_length % Hex.HEXD_PER_BYTE

		byte[] result = null;
		int result_idx = 0;
		if (remainder == 0) {
			result = new byte[quotient];
		} else {
			result = new byte[1 + quotient];
			result[0] = (byte) Integer.parseInt(s.substring(0, remainder), Hex.RADIX);
			result_idx = 1;
		}
		for (int s_idx = remainder; s_idx != s_length; s_idx += Hex.HEXD_PER_BYTE, ++result_idx) {
			result[result_idx] = (byte) Integer.parseInt(s.substring(s_idx, s_idx + Hex.HEXD_PER_BYTE), Hex.RADIX);
		}
		return result;
	}

	/**
	 * Given a string of hex digits, convert it to a char array.
	 * 
	 * @param s
	 *            the given string
	 * 
	 * @return The resulting char array.
	 * 
	 * @throws NullPointerException
	 *             If <code>s == null</code>
	 * 
	 * @throws NumberFormatException
	 *             Thrown by
	 *             <code>Integer.parseInt(s.substring(i, i + Hex.HEXD_PER_CHAR), Hex.RADIX)</code> where
	 *             <code>i</code> is valid
	 */
	public static char[] toChars(String s) throws NullPointerException, NumberFormatException {
		final int s_length = s.length();
		final int quotient = s_length / Hex.HEXD_PER_CHAR, remainder = s_length - Hex.HEXD_PER_CHAR * quotient;
		// remainder == s_length % Hex.HEXD_PER_CHAR

		char[] result = null;
		int result_idx = 0;
		if (remainder == 0) {
			result = new char[quotient];
		} else {
			result = new char[1 + quotient];
			result[0] = (char) Integer.parseInt(s.substring(0, remainder), Hex.RADIX);
			result_idx = 1;
		}
		for (int s_idx = remainder; s_idx != s_length; s_idx += Hex.HEXD_PER_CHAR, ++result_idx) {
			result[result_idx] = (char) Integer.parseInt(s.substring(s_idx, s_idx + Hex.HEXD_PER_CHAR), Hex.RADIX);
		}
		return result;
	}

	/**
	 * Given a string of hex digits, convert it to a short array.
	 * 
	 * @param s
	 *            the given string
	 * 
	 * @return The resulting short array.
	 * 
	 * @throws NullPointerException
	 *             If <code>s == null</code>
	 * 
	 * @throws NumberFormatException
	 *             Thrown by
	 *             <code>Integer.parseInt(s.substring(i, i + Hex.HEXD_PER_SHORT), Hex.RADIX)</code>
	 *             where <code>i</code> is valid
	 */
	public static short[] toShorts(String s) throws NullPointerException, NumberFormatException {
		final int s_length = s.length();
		final int quotient = s_length / Hex.HEXD_PER_SHORT, remainder = s_length - Hex.HEXD_PER_SHORT * quotient;
		// remainder == s_length % Hex.HEXD_PER_SHORT

		short[] result = null;
		int result_idx = 0;
		if (remainder == 0) {
			result = new short[quotient];
		} else {
			result = new short[1 + quotient];
			result[0] = (short) Integer.parseInt(s.substring(0, remainder), Hex.RADIX);
			result_idx = 1;
		}
		for (int s_idx = remainder; s_idx != s_length; s_idx += Hex.HEXD_PER_SHORT, ++result_idx) {
			result[result_idx] = (short) Integer.parseInt(s.substring(s_idx, s_idx + Hex.HEXD_PER_SHORT), Hex.RADIX);
		}
		return result;
	}

	/**
	 * Given a string of hex digits, convert it to an int array.
	 * 
	 * @param s
	 *            the given string
	 * 
	 * @return The resulting int array.
	 * 
	 * @throws NullPointerException
	 *             If <code>s == null</code>
	 * 
	 * @throws NumberFormatException
	 *             Thrown by
	 *             <code>Integer.parseInt(s.substring(i, i + Hex.HEXD_PER_INT), Hex.RADIX)</code> where
	 *             <code>i</code> is valid
	 */
	public static int[] toInts(String s) throws NullPointerException, NumberFormatException {
		final int s_length = s.length();
		final int quotient = s_length / Hex.HEXD_PER_INT, remainder = s_length - Hex.HEXD_PER_INT * quotient;
		// remainder == s_length % Hex.HEXD_PER_INT

		int[] result = null;
		int result_idx = 0;
		if (remainder == 0) {
			result = new int[quotient];
		} else {
			result = new int[1 + quotient];
			result[0] = Integer.parseInt(s.substring(0, remainder), Hex.RADIX);
			result_idx = 1;
		}
		for (int s_idx = remainder; s_idx != s_length; s_idx += Hex.HEXD_PER_INT, ++result_idx) {
			result[result_idx] = Integer.parseInt(s.substring(s_idx, s_idx + Hex.HEXD_PER_INT), Hex.RADIX);
		}
		return result;
	}

	/**
	 * Given a string of hex digits, convert it to a long array.
	 * 
	 * @param s
	 *            the given string
	 * 
	 * @return The resulting long array.
	 * 
	 * @throws NullPointerException
	 *             If <code>s == null</code>
	 * 
	 * @throws NumberFormatException
	 *             Thrown by
	 *             <code>Long.parseLong(s.substring(i, i + Hex.HEXD_PER_LONG), Hex.RADIX)</code> where
	 *             <code>i</code> is valid
	 */
	public static long[] toLongs(String s) throws NullPointerException, NumberFormatException {
		final int s_length = s.length();
		final int quotient = s_length / Hex.HEXD_PER_LONG, remainder = s_length - Hex.HEXD_PER_LONG * quotient;
		// remainder == s_length % Hex.HEXD_PER_LONG

		long[] result = null;
		int result_idx = 0;
		if (remainder == 0) {
			result = new long[quotient];
		} else {
			result = new long[1 + quotient];
			result[0] = Long.parseLong(s.substring(0, remainder), Hex.RADIX);
			result_idx = 1;
		}
		for (int s_idx = remainder; s_idx != s_length; s_idx += Hex.HEXD_PER_LONG, ++result_idx) {
			result[result_idx] = Long.parseLong(s.substring(s_idx, s_idx + Hex.HEXD_PER_LONG), Hex.RADIX);
		}
		return result;
	}
}
