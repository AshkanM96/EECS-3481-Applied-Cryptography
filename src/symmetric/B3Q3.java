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
 * DES decryption use case.
 * 
 * @author Ashkan Moatamed
 */
public class B3Q3 {
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
	private static final byte[] KEY = "CSE@YORK".getBytes();

	/**
	 * Known initialization vector.
	 */
	private static final byte[] IV = Hex.toBytes("4E51297B424F90D8");

	/**
	 * Known ciphertext.
	 */
	private static final byte[] CIPHERTEXT = Hex.toBytes("B2ACD6ADF010DDC4");

	/**
	 * Prevent instantiation.
	 */
	private B3Q3() {
		// Empty by design.
	}

	@Override
	protected Object clone() throws CloneNotSupportedException { // semi-copy
		throw new CloneNotSupportedException();
	}

	public static void main(String[] args) throws InvalidKeyException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException {
		// Create the cipher engine with the appropriate attributes.
		final SymCipherEng engine = new SymCipherEng(SymCipherEng.ALGO_SYM.DES, B3Q3.KEY, CipherEngUtil.OPMODE.CBC,
				CipherEngUtil.PADDING.PKCS5Padding, B3Q3.IV);

		// Decrypt the ciphertext using the engine to get the plaintext.
		final byte[] plaintext = engine.decrypt(B3Q3.CIPHERTEXT);
		System.out.println("The plaintext is:\n" + CryptoTools.toString(plaintext) + "\n");
	}
}
