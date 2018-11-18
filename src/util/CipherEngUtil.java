package util;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/**
 * Utility methods for Java's Cipher class wrapper types such as SymCipherEng and RSACipherEng.
 * 
 * @author Ashkan Moatamed
 */
public class CipherEngUtil {
	/**
	 * No dependencies.
	 */

	/**
	 * All supported modes of operation.
	 * 
	 * @author Ashkan Moatamed
	 */
	public static enum MODE {
		ECB, CBC;
	};

	/**
	 * Default mode of operation.
	 */
	public static final MODE DEFAULT_MODE = MODE.ECB;

	/**
	 * All supported padding algorithms.
	 * 
	 * @author Ashkan Moatamed
	 */
	public static enum PADDING {
		NoPadding, PKCS5Padding;
	};

	/**
	 * Default padding algorithm.
	 */
	public static final PADDING DEFAULT_PADDING = PADDING.NoPadding;

	/**
	 * Prevent instantiation.
	 */
	private CipherEngUtil() {
		// Empty by design.
	}

	/**
	 * @param algo
	 *            the given algorithm
	 * 
	 * @param mode
	 *            the given mode of operation
	 * 
	 * @param padding
	 *            the given padding
	 * 
	 * @return <code>algo + "/" + mode.name() + "/" + padding.name()</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(algo == null) || (mode == null) || (padding == null)</code>
	 */
	public static String getAlgoName(String algo, MODE mode, PADDING padding) throws NullPointerException {
		if (algo == null) {
			throw new NullPointerException();
		}

		final StringBuilder sb = new StringBuilder();
		sb.append(algo).append('/').append(mode.name()).append('/').append(padding.name());
		return sb.toString();
	}

	/**
	 * @param algo
	 *            the given algorithm
	 * 
	 * @param mode
	 *            the given mode of operation
	 * 
	 * @param padding
	 *            the given padding
	 * 
	 * @return <code>Cipher.getInstance(CipherEngUtil.getAlgoName(algo, mode, padding))</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(algo == null) || (mode == null) || (padding == null)</code>
	 * 
	 * @throws NoSuchAlgorithmException
	 *             Thrown by <code>Cipher::getInstance</code>
	 * 
	 * @throws NoSuchPaddingException
	 *             Thrown by <code>Cipher::getInstance</code>
	 */
	public static Cipher getEngine(String algo, MODE mode, PADDING padding)
			throws NullPointerException, NoSuchAlgorithmException, NoSuchPaddingException {
		return Cipher.getInstance(CipherEngUtil.getAlgoName(algo, mode, padding));
	}
}
