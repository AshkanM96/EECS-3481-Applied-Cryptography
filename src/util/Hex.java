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
	 * Prevent instantiation.
	 */
	private Hex() {
		// Empty by design.
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
		final StringBuilder sb = new StringBuilder(data.length);
		for (int i = 0, tmp = 0; i != data.length; ++i) {
			/**
			 * Note that we have to use <code>Integer.toHexString</code> instead of
			 * <code>Integer.toString(int, Hex.RADIX)</code>, since the latter includes a negative sign in its
			 * returned string when it needs to. Moreover, we should use the bit mask when passing the given
			 * byte to <code>Integer.toHexString</code>, since the binary representation of the value of
			 * <code>data[i]</code> differs when being represented as an int instead of a byte when
			 * <code>data[i]</code> is negative.
			 */
			tmp = data[i] & Binary.MASK_BYTE_TO_INT;
			if (tmp < Hex.RADIX) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(tmp).toUpperCase());
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
	 *             Thrown by <code>Integer.parseInt(s.substring(i, i + 2), Hex.RADIX)</code> where
	 *             <code>i</code> is <code>valid</code>
	 */
	public static byte[] toBytes(String s) throws NullPointerException, NumberFormatException {
		if ((s.length() & 1) != 0) { // i.e., !NumUtil.isEven(s.length())
			// When the string's length is not divisible by 2, prepend a '0' to it.
			s = "0" + s;
		}
		// s.length() % 2 == 0

		final int half = s.length() / 2;
		final byte[] data = new byte[half];
		for (int i = 0, twice_i = 0; i != half; ++i, twice_i += 2) {
			data[i] = (byte) Integer.parseInt(s.substring(twice_i, twice_i + 2), Hex.RADIX);
		}
		return data;
	}
}
