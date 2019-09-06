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
	public static enum OPMODE {
		ECB, CBC;
	};

	/**
	 * Default mode of operation.
	 */
	public static final OPMODE DEFAULT_MODE = OPMODE.ECB;

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
	 * @param opmode
	 *            the given mode of operation
	 * 
	 * @param padding
	 *            the given padding
	 * 
	 * @return <code>algo + "/" + opmode.name() + "/" + padding.name()</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(algo == null) || (opmode == null) || (padding == null)</code>
	 */
	public static String getAlgoName(String algo, OPMODE opmode, PADDING padding) throws NullPointerException {
		if (algo == null) {
			throw new NullPointerException();
		}
		final StringBuilder sb = new StringBuilder();
		sb.append(algo).append('/').append(opmode.name()).append('/').append(padding.name());
		return sb.toString();
	}

	/**
	 * @param algo
	 *            the given algorithm
	 * 
	 * @param opmode
	 *            the given mode of operation
	 * 
	 * @param padding
	 *            the given padding
	 * 
	 * @return <code>Cipher.getInstance(CipherEngUtil.getAlgoName(algo, opmode, padding))</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(algo == null) || (opmode == null) || (padding == null)</code>
	 * 
	 * @throws NoSuchAlgorithmException
	 *             Thrown by
	 *             <code>Cipher.getInstance(CipherEngUtil.getAlgoName(algo, opmode, padding))</code>
	 * 
	 * @throws NoSuchPaddingException
	 *             Thrown by
	 *             <code>Cipher.getInstance(CipherEngUtil.getAlgoName(algo, opmode, padding))</code>
	 */
	public static Cipher getEngine(String algo, OPMODE opmode, PADDING padding)
			throws NullPointerException, NoSuchAlgorithmException, NoSuchPaddingException {
		return Cipher.getInstance(CipherEngUtil.getAlgoName(algo, opmode, padding));
	}
}
