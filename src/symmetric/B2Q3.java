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
public class B2Q3 {
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
	private static final byte[] KEY = "DO NOT TELL EVE!".getBytes();

	/**
	 * Known initialization vector.
	 */
	private static final byte[] IV = Hex.toBytes("20FC19123087BF6CAC8D0F1254123004");

	/**
	 * Known ciphertext.
	 */
	private static final byte[] CIPHERTEXT = Hex
			.toBytes("3188073EA5DB3F5C05B6307B3595607135F5D4B22F2C3EB710AA31377F78B997");

	/**
	 * Prevent instantiation.
	 */
	private B2Q3() {
		// Empty by design.
	}

	public static void main(String[] args) throws InvalidKeyException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException {
		// Create the cipher engine with the appropriate attributes.
		final SymCipherEng engine = new SymCipherEng(SymCipherEng.ALGO_SYM.AES, B2Q3.KEY, CipherEngUtil.OPMODE.CBC,
				CipherEngUtil.PADDING.PKCS5Padding, B2Q3.IV);

		// Decrypt the ciphertext using the engine to get the plaintext.
		final byte[] plaintext = engine.decrypt(B2Q3.CIPHERTEXT);
		System.out.println("The plaintext is:\n" + CryptoTools.toString(plaintext) + "\n");
	}
}
