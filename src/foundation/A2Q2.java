package foundation;

import util.Caesar;
import util.CryptoTools;

/**
 * Caesar cryptanalytic decryption use case.
 * 
 * @author Ashkan Moatamed
 */
public class A2Q2 {
	/**
	 * Dependencies: <code>
	 * 		1. util.Caesar
	 * 		2. util.CryptoTools
	 * </code>
	 */

	/**
	 * Known ciphertext.
	 */
	private static final byte[] CIPHERTEXT = CryptoTools.clean("lcllewljazlnnzmvyiylhrmhza".toUpperCase().getBytes());

	/**
	 * Prevent instantiation.
	 */
	private A2Q2() {
		// Empty by design.
	}

	@Override
	protected Object clone() throws CloneNotSupportedException { // semi-copy
		throw new CloneNotSupportedException();
	}

	public static void main(String[] args) {
		final byte[] ciphertext = A2Q2.CIPHERTEXT;
		if (ciphertext.length == 0) {
			System.out.println("The ciphertext is empty.\n");
			return;
		}

		// Launch a cryptanalytic KCA and print intermediate messages.
		Caesar.kcaCryptanalytic(ciphertext, true);
	}
}
