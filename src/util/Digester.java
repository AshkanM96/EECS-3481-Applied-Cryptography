package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A simple wrapper over Java's MessageDigest class.
 * 
 * @author Ashkan Moatamed
 */
public class Digester {
	/**
	 * No dependencies.
	 */

	/**
	 * All supported Hashing algorithms.
	 * 
	 * @author Ashkan Moatamed
	 */
	public static enum ALGO_HASH {
		MD2, // MD2.toString() returns MD2.name() which is "MD2".
		MD5, // MD5.toString() returns MD5.name() which is "MD5".
		SHA_1 {
			@Override
			public final String toString() {
				return "SHA-1";
			}
		},
		SHA_224 {
			@Override
			public final String toString() {
				return "SHA-224";
			}
		},
		SHA_256 {
			@Override
			public final String toString() {
				return "SHA-256";
			}
		},
		SHA_384 {
			@Override
			public final String toString() {
				return "SHA-384";
			}
		},
		SHA_512 {
			@Override
			public final String toString() {
				return "SHA-512";
			}
		};
	};

	/**
	 * @param algo
	 *            the given hashing algorithm
	 * 
	 * @return <code>MessageDigest.getInstance(algo.toString())</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>algo == null</code>
	 */
	public static MessageDigest getMessageDigest(ALGO_HASH algo) throws NullPointerException {
		try {
			return MessageDigest.getInstance(algo.toString());
		} catch (NoSuchAlgorithmException ex) {
			throw new ExceptionInInitializerError(algo.toString() + " hash algorithm is not provided!");
		}
	}

	/**
	 * Hashing digesters.
	 */
	protected static MessageDigest[] digesters = null;

	/**
	 * Prevent instantiation.
	 */
	private Digester() {
		// Empty by design.
	}

	@Override
	protected Object clone() throws CloneNotSupportedException { // semi-copy
		throw new CloneNotSupportedException();
	}

	/**
	 * Initialize <code>Digester.digesters[algo.ordinal()]</code> using the following:
	 * 
	 * <pre>
	 * <code>
	 * if (Digester.digesters == null) {
	 * 	Digester.digesters = new MessageDigest[ALGO_HASH.values().length];
	 * }
	 * 
	 * final int index = algo.ordinal();
	 * if (Digester.digesters[index] == null) {
	 * 	Digester.digesters[index] = Digester.getMessageDigest(algo);
	 * }
	 * </code>
	 * </pre>
	 * 
	 * @param algo
	 *            the given hashing algorithm
	 * 
	 * @param data
	 *            the given byte array
	 * 
	 * @return <code>Digester.digesters[algo.ordinal()].digest(data)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(algo == null) || (data == null)</code>
	 */
	public static byte[] digest(ALGO_HASH algo, byte[] data) throws NullPointerException {
		// Construct Digester.digesters if needed. Executed at most once.
		if (Digester.digesters == null) {
			Digester.digesters = new MessageDigest[ALGO_HASH.values().length];
		}

		// Construct Digester.digesters[index] if needed. Executed at most once.
		final int index = algo.ordinal();
		MessageDigest digester = Digester.digesters[index];
		if (digester == null) {
			digester = Digester.digesters[index] = Digester.getMessageDigest(algo);
		}

		// Compute and return the digested byte[].
		return digester.digest(data);
	}
}
