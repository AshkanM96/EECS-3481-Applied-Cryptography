package symmetric;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import util.CipherEngUtil;
import util.CryptoTools;
import util.Hex;
import util.SymCipherEng;

/**
 * AES decryption use case.
 * 
 * @author Ashkan Moatamed
 */
public class B2Q2 {
	/**
	 * Dependencies: <code>
	 * 		1. util.Hex
	 * 		2. util.CipherEngUtil
	 * 		3. util.SymCipherEng
	 * 		4. util.CryptoTools
	 * </code>
	 */

	/**
	 * Known key.
	 */
	private static final byte[] KEY = Hex.toBytes("9F0DCEDB322F3C6873F9256E01376BA4");

	/**
	 * Known initialization vector.
	 */
	private static final byte[] IV = Hex.toBytes("20FC19123087BF6CAC8D0F1254123004");

	/**
	 * Known ciphertext.
	 */
	private static final byte[] CIPHERTEXT = Hex.toBytes("F38ADBA8A7B4CC613578355032205D50");

	/**
	 * Prevent instantiation.
	 */
	private B2Q2() {
		// Empty by design.
	}

	public static void main(String[] args) throws InvalidKeyException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException {
		// Create the cipher engine with the appropriate attributes.
		final SymCipherEng engine = new SymCipherEng(SymCipherEng.ALGO_SYM.AES, B2Q2.KEY, CipherEngUtil.OPMODE.CBC,
				CipherEngUtil.PADDING.PKCS5Padding, B2Q2.IV);

		// Decrypt the ciphertext using the engine to get the plaintext.
		final byte[] plaintext = engine.decrypt(B2Q2.CIPHERTEXT);
		System.out.println("The plaintext is:\n" + CryptoTools.toString(plaintext) + "\n");
	}
}
