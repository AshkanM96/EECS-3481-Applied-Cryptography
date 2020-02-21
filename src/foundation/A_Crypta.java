package foundation;

import java.io.FileNotFoundException;
import java.io.IOException;

import util.Affine;
import util.CryptoTools;

/**
 * Affine cryptanalytic decryption use case.
 * 
 * @author Ashkan Moatamed
 */
public class A_Crypta {
	/**
	 * Dependencies: <code>
	 * 		1. foundation.FoundationUtil
	 * 		2. util.Affine
	 * 		3. util.CryptoTools
	 * </code>
	 */

	/**
	 * File name.
	 */
	private static final String FILE_NAME = "MSG3";

	/**
	 * Input ciphertext location.
	 */
	private static final String CIPHERTEXT_LOCATION = FoundationUtil.inCipherPath(A_Crypta.FILE_NAME);

	/**
	 * Prevent instantiation.
	 */
	private A_Crypta() {
		// Empty by design.
	}

	@Override
	protected Object clone() throws CloneNotSupportedException { // semi-copy
		throw new CloneNotSupportedException();
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// Read the ciphertext from the file.
		final byte[] ciphertext = CryptoTools.clean(CryptoTools.fileToBytes(A_Crypta.CIPHERTEXT_LOCATION));
		System.out.println("Read ciphertext from " + A_Crypta.CIPHERTEXT_LOCATION + ".\n");
		if (ciphertext.length < 2) {
			System.out.println("The ciphertext has less than 2 chars.\n");
			return;
		}

		// Launch a cryptanalytic KCA and print intermediate messages.
		Affine.kcaCryptanalytic(ciphertext, true);
	}
}
