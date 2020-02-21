package foundation;

import util.Caesar;
import util.CryptoTools;

/**
 * Caesar exhaustive decryption use case.
 * 
 * @author Ashkan Moatamed
 */
public class A2Q1 {
	/**
	 * Dependencies: <code>
	 * 		1. util.Caesar
	 * 		2. util.CryptoTools
	 * </code>
	 */

	/**
	 * Known ciphertext.
	 */
	private static final byte[] CIPHERTEXT = CryptoTools.clean("ycvejqwvhqtdtwvwu".toUpperCase().getBytes());

	/**
	 * Prevent instantiation.
	 */
	private A2Q1() {
		// Empty by design.
	}

	@Override
	protected Object clone() throws CloneNotSupportedException { // semi-copy
		throw new CloneNotSupportedException();
	}

	public static void main(String[] args) {
		final byte[] ciphertext = A2Q1.CIPHERTEXT;
		if (ciphertext.length == 0) {
			System.out.println("The ciphertext is empty.\n");
			return;
		}

		// Launch an exhaustive KCA and print intermediate messages.
		Caesar.kcaExhaustive(ciphertext, true);
	}
}
