package foundation;

import java.io.FileNotFoundException;
import java.io.IOException;

import util.CryptoTools;
import util.Vigenere;

/**
 * Vigenere cryptanalytic decryption use case.
 * 
 * @author Ashkan Moatamed
 */
public class V_Crypta2 {
	/**
	 * Dependencies: <code>
	 * 		1. foundation.FoundationUtil
	 * 		2. util.Vigenere
	 * 		3. util.CryptoTools
	 * </code>
	 */

	/**
	 * File name.
	 */
	private static final String FILE_NAME = "RPT2";

	/**
	 * Input ciphertext location.
	 */
	private static final String CIPHERTEXT_LOCATION = FoundationUtil.inCipherPath(V_Crypta2.FILE_NAME);

	/**
	 * Output probable plaintext location.
	 */
	private static final String PROBABLE_PLAINTEXT_LOCATION = FoundationUtil
			.outPlainPath(FoundationUtil.outNameCryptanalytic(V_Crypta2.FILE_NAME));

	/**
	 * Prevent instantiation.
	 */
	private V_Crypta2() {
		// Empty by design.
	}

	@Override
	protected Object clone() throws CloneNotSupportedException { // semi-copy
		throw new CloneNotSupportedException();
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// Read the ciphertext from the file.
		final byte[] ciphertext = CryptoTools.clean(CryptoTools.fileToBytes(V_Crypta2.CIPHERTEXT_LOCATION));
		System.out.println("Read ciphertext from " + V_Crypta2.CIPHERTEXT_LOCATION + ".\n");
		if (ciphertext.length == 0) {
			System.out.println("The ciphertext is empty.\n");
			return;
		}

		// Launch a cryptanalytic KCA and print intermediate messages.
		final byte[] probablePlaintext = Vigenere.kcaCryptanalytic(ciphertext, true).plaintext;

		// Write the probable plaintext to a file.
		CryptoTools.bytesToFile(probablePlaintext, V_Crypta2.PROBABLE_PLAINTEXT_LOCATION);
		System.out.println("Wrote probable plaintext to " + V_Crypta2.PROBABLE_PLAINTEXT_LOCATION + ".\n");
	}
}
