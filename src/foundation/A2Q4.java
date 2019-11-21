package foundation;

import util.Affine;
import util.CryptoTools;

/**
 * Affine known alpha decryption use case.
 * 
 * @author Ashkan Moatamed
 */
public class A2Q4 {
	/**
	 * Dependencies: <code>
	 * 		1. util.Affine
	 * 		2. util.CryptoTools
	 * </code>
	 */

	/**
	 * Known ciphertext.
	 */
	private static final byte[] CIPHERTEXT = CryptoTools.clean("tcabtiqmfheqqmrmvmtmaq".toUpperCase().getBytes());

	/**
	 * Partially known key.
	 */
	private static final int AFFINE_ALPHA = Affine.validateAlpha(3);

	/**
	 * Prevent instantiation.
	 */
	private A2Q4() {
		// Empty by design.
	}

	@SuppressWarnings("null")
	public static void main(String[] args) {
		final byte[] ciphertext = A2Q4.CIPHERTEXT;
		if (ciphertext.length == 0) {
			System.out.println("The ciphertext is empty.\n");
			return;
		}

		// Exhaustive attack to find beta.
		/*
		 * Try every possible key pair for the affine cipher and for each key, compute the dot product of
		 * the letter probabilities of the decrypted text and the probabilities of the English letters. The
		 * most likely key is the one that has the maximum dot product. This method is basically finding the
		 * maximal dot product with the English alphabet which determines proximity to English.
		 */
		int probableBeta = 0;
		double maxDotProduct = -1.0, dotProduct = 0.0;
		byte[] probablePlaintext = null, decrytedText = null;
		final Affine a = new Affine(A2Q4.AFFINE_ALPHA);
		for (int beta = Affine.MIN_BETA_VALUE; beta <= Affine.MAX_CIPHER_KEY_VALUE; ++beta) {
			a.beta(beta); // Set the affine object's beta attribute.

			// Decrypt the ciphertext using alpha and beta.
			decrytedText = a.decrypt(ciphertext);

			/*
			 * Compute dot product and keep track of the maximum dot product, the associated key and decrypted
			 * text.
			 */
			dotProduct = CryptoTools.getEnglishProbabilitiesDotProduct(decrytedText);
			if (Double.compare(maxDotProduct, dotProduct) < 0) { // i.e., maxDotProduct < dotProduct
				maxDotProduct = dotProduct;
				probableBeta = beta;
				probablePlaintext = decrytedText;
			}
			System.out.println("Affine key " + a.toString() + " gives dot product " + dotProduct + ".");
			System.out.println("Plaintext is:");
			for (int i = 0; i != decrytedText.length; ++i) {
				System.out.print((char) decrytedText[i]);
			}
			System.out.println("\n");
		}

		// Print probable affine key and probable plaintext.
		System.out.println("Probable affine key is " + Affine.toString(A2Q4.AFFINE_ALPHA, probableBeta) + ".\n");
		System.out.println("Probable plaintext is:");
		for (int i = 0; i != probablePlaintext.length; ++i) {
			System.out.print((char) probablePlaintext[i]);
		}
		System.out.println('\n');
	}
}
