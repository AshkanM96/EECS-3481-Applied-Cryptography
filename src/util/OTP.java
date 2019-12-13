package util;

/**
 * OTP cipher. This class is just a simple wrapper for <code>^</code> and <code>Binary.xor</code>.
 * 
 * @author Ashkan Moatamed
 */
public class OTP {
	/**
	 * No dependencies.
	 */

	/**
	 * Prevent instantiation.
	 */
	private OTP() {
		// Empty by design.
	}

	/**
	 * Apply the given key to the given char.
	 * 
	 * @param k
	 *            the given key
	 * 
	 * @param data
	 *            the given char
	 * 
	 * @return <code>(char) (k ^ data)</code>.
	 */
	public static char apply(char k, char data) {
		return ((char) (k ^ data));
	}

	/**
	 * Apply the given key to the given byte.
	 * 
	 * @param k
	 *            the given key
	 * 
	 * @param data
	 *            the given byte
	 * 
	 * @return <code>(byte) (k ^ data)</code>.
	 */
	public static byte apply(byte k, byte data) {
		return ((byte) (k ^ data));
	}

	/**
	 * Apply the given key to the given char array.
	 * 
	 * @param key
	 *            the given key
	 * 
	 * @param data
	 *            the given char array
	 * 
	 * @return <code>Binary.xor(key, data)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(key == null) || (data == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>key.length != data.length</code>
	 */
	public static char[] apply(char[] key, char[] data) throws NullPointerException, IllegalArgumentException {
		if (key.length != data.length) {
			throw new IllegalArgumentException();
		}

		final char[] result = new char[data.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = (char) (key[i] ^ data[i]);
		}
		return result;
	}

	/**
	 * Apply the given key to the given byte array.
	 * 
	 * @param key
	 *            the given key
	 * 
	 * @param data
	 *            the given byte array
	 * 
	 * @return <code>Binary.xor(key, data)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(key == null) || (data == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>key.length != data.length</code>
	 */
	public static byte[] apply(byte[] key, byte[] data) throws NullPointerException, IllegalArgumentException {
		if (key.length != data.length) {
			throw new IllegalArgumentException();
		}

		final byte[] result = new byte[data.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = (byte) (key[i] ^ data[i]);
		}
		return result;
	}

	/**
	 * Given a single ciphertext char and its associated plaintext char, compute the appropriate OTP
	 * cipher key.
	 * 
	 * @param c
	 *            the given ciphertext char
	 * 
	 * @param p
	 *            the given plaintext char
	 * 
	 * @return <code>(char) (c ^ p)</code>.
	 */
	public static char key(char c, char p) {
		return ((char) (c ^ p));
	}

	/**
	 * Given a single ciphertext byte and its associated plaintext byte, compute the appropriate OTP
	 * cipher key.
	 * 
	 * @param c
	 *            the given ciphertext byte
	 * 
	 * @param p
	 *            the given plaintext byte
	 * 
	 * @return <code>(byte) (c ^ p)</code>.
	 */
	public static byte key(byte c, byte p) {
		return ((byte) (c ^ p));
	}

	/**
	 * Given a ciphertext char array and its associated plaintext char array, compute the appropriate
	 * OTP cipher key.
	 * 
	 * @param c
	 *            the given ciphertext char array
	 * 
	 * @param p
	 *            the given plaintext char array
	 * 
	 * @return <code>Binary.xor(c, p)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(c == null) || (p == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>c.length != p.length</code>
	 */
	public static char[] key(char[] c, char[] p) throws NullPointerException, IllegalArgumentException {
		if (c.length != p.length) {
			throw new IllegalArgumentException();
		}

		final char[] result = new char[c.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = (char) (c[i] ^ p[i]);
		}
		return result;
	}

	/**
	 * Given a ciphertext byte array and its associated plaintext byte array, compute the appropriate
	 * OTP cipher key.
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @param p
	 *            the given plaintext byte array
	 * 
	 * @return <code>Binary.xor(c, p)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(c == null) || (p == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>c.length != p.length</code>
	 */
	public static byte[] key(byte[] c, byte[] p) throws NullPointerException, IllegalArgumentException {
		if (c.length != p.length) {
			throw new IllegalArgumentException();
		}

		final byte[] result = new byte[c.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = (byte) (c[i] ^ p[i]);
		}
		return result;
	}
}
