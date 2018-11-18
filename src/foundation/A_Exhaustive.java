package foundation;

import java.io.FileNotFoundException;
import java.io.IOException;

import util.Affine;
import util.CryptoTools;

/**
 * Affine exhaustive decryption use case.
 * 
 * @author Ashkan Moatamed
 */
public class A_Exhaustive {
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
	private static final String CIPHERTEXT_LOCATION = FoundationUtil.inCipherPath(A_Exhaustive.FILE_NAME);

	/**
	 * Output probable plaintext location.
	 */
	private static final String PROBABLE_PLAINTEXT_LOCATION = FoundationUtil
			.outPlainPath(FoundationUtil.outNameExhaustive(A_Exhaustive.FILE_NAME));

	/**
	 * Prevent instantiation.
	 */
	private A_Exhaustive() {
		// Empty by design.
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// Read the ciphertext from the file.
		final byte[] ciphertext = CryptoTools.clean(CryptoTools.fileToBytes(A_Exhaustive.CIPHERTEXT_LOCATION));
		System.out.println("Read ciphertext from " + A_Exhaustive.CIPHERTEXT_LOCATION + ".\n");
		if (ciphertext.length < 2) {
			System.out.println("The ciphertext has less than 2 chars.\n");
			return;
		}

		// Launch an exhaustive KCA and print intermediate messages.
		final byte[] probablePlaintext = Affine.kcaExhaustive(ciphertext, true).plaintexts[0];

		// Write the probable plaintext to a file.
		CryptoTools.bytesToFile(probablePlaintext, A_Exhaustive.PROBABLE_PLAINTEXT_LOCATION);
		System.out.println("Wrote probable plaintext to " + A_Exhaustive.PROBABLE_PLAINTEXT_LOCATION + ".\n");
	}
}
