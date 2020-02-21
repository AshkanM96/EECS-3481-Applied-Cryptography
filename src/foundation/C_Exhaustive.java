package foundation;

import java.io.FileNotFoundException;
import java.io.IOException;

import util.Caesar;
import util.CryptoTools;

/**
 * Caesar exhaustive decryption use case.
 * 
 * @author Ashkan Moatamed
 */
public class C_Exhaustive {
	/**
	 * Dependencies: <code>
	 * 		1. foundation.FoundationUtil
	 * 		2. util.Caesar
	 * 		3. util.CryptoTools
	 * </code>
	 */

	/**
	 * File name.
	 */
	private static final String FILE_NAME = "MSG2";

	/**
	 * Input ciphertext location.
	 */
	private static final String CIPHERTEXT_LOCATION = FoundationUtil.inCipherPath(C_Exhaustive.FILE_NAME);

	/**
	 * Output probable plaintext location.
	 */
	private static final String PROBABLE_PLAINTEXT_LOCATION = FoundationUtil
			.outPlainPath(FoundationUtil.outNameExhaustive(C_Exhaustive.FILE_NAME));

	/**
	 * Prevent instantiation.
	 */
	private C_Exhaustive() {
		// Empty by design.
	}

	@Override
	protected Object clone() throws CloneNotSupportedException { // semi-copy
		throw new CloneNotSupportedException();
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// Read the ciphertext from the file.
		final byte[] ciphertext = CryptoTools.clean(CryptoTools.fileToBytes(C_Exhaustive.CIPHERTEXT_LOCATION));
		System.out.println("Read ciphertext from " + C_Exhaustive.CIPHERTEXT_LOCATION + ".\n");
		if (ciphertext.length == 0) {
			System.out.println("The ciphertext is empty.\n");
			return;
		}

		// Launch an exhaustive KCA and print intermediate messages.
		final byte[] probablePlaintext = Caesar.kcaExhaustive(ciphertext, true).plaintext;

		// Write the probable plaintext to a file.
		CryptoTools.bytesToFile(probablePlaintext, C_Exhaustive.PROBABLE_PLAINTEXT_LOCATION);
		System.out.println("Wrote probable plaintext to " + C_Exhaustive.PROBABLE_PLAINTEXT_LOCATION + ".\n");
	}
}
