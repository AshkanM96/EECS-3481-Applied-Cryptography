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
	 * 		2. util.MathUtil
	 * </code>
	 */

	/**
	 * Radix used for parsing.
	 */
	public static final int RADIX = 16;

	/**
	 * Prevent instantiation.
	 */
	private Hex() {
		// Empty by design.
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
	 *             Thrown by <code>Integer.parseInt(s.substring(i, i + 2), Hex.RADIX)</code> where
	 *             <code>i</code> is <code>valid</code>
	 */
	public static byte[] toBytes(String s) throws NullPointerException, NumberFormatException {
		final int l = s.length();
		if (!MathUtil.isEven(l)) {
			// When the string has an odd length, prepend a '0' to it.
			s = "0" + s;
		}

		final int half = l / 2;
		final byte[] data = new byte[half];
		for (int i = 0, twice_i = 0; i != half; ++i, twice_i += 2) {
			data[i] = (byte) Integer.parseInt(s.substring(twice_i, twice_i + 2), Hex.RADIX);
		}
		return data;
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
		final StringBuilder sb = new StringBuilder();
		for (int i = 0, tmp = 0; i != data.length; ++i) {
			tmp = data[i] & Binary.BYTE_CONVERTER;
			if (tmp < Hex.RADIX) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(tmp));
		}
		return sb.toString().toUpperCase();
	}
}
