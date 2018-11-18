package foundation;

import util.Affine;
import util.CryptoTools;

/**
 * Affine known pair decryption use case.
 * 
 * @author Ashkan Moatamed
 */
public class A2Q3 {
	/**
	 * Dependencies: <code>
	 * 		1. util.Affine
	 * 		2. util.CryptoTools
	 * </code>
	 */

	/**
	 * Known ciphertext.
	 */
	private static final byte[] CIPHERTEXT = CryptoTools
			.clean("edsgickxhuklzveqzvkxwkzukcvuh".toUpperCase().getBytes());

	/**
	 * Partially known plaintext.
	 */
	private static final byte[] PLAINTEXT_PARTIAL = CryptoTools.clean("if".toUpperCase().getBytes());

	/**
	 * Prevent instantiation.
	 */
	private A2Q3() {
		// Empty by design.
	}

	public static void main(String[] args) {
		final byte[] ciphertext = A2Q3.CIPHERTEXT;
		if (ciphertext.length == 0) {
			System.out.println("The ciphertext is empty.\n");
			return;
		}

		// Find all affine keys from the two known pair mappings.
		final int[][] keys = Affine.key(ciphertext[0], A2Q3.PLAINTEXT_PARTIAL[0], ciphertext[1],
				A2Q3.PLAINTEXT_PARTIAL[1]);
		final int numKeys = keys[0].length;
		System.out.println("There " + (numKeys == 1 ? "is only " : "are ") + numKeys + " appropriate affine key"
				+ (numKeys == 1 ? ".\n" : "s.\n"));

		// Decrypt the ciphertext using each found affine key.
		final Affine a = new Affine();
		for (int keyNum = 0; keyNum != numKeys; ++keyNum) {
			// Set the affine object's alpha and beta attributes.
			a.alpha(keys[0][keyNum]);
			a.beta(keys[1][keyNum]);

			// Decrypt the ciphertext using the affine key.
			System.out.println("Probable affine key " + (keyNum + 1) + " is " + a.toString() + ".\n");
			System.out.println("Probable plaintext " + (keyNum + 1) + " is:");
			for (final byte b : ciphertext) {
				System.out.print(a.decrypt(b));
			}
			System.out.println('\n');
		}
	}
}
