package symmetric;

import util.CryptoTools;
import util.Vigenere;

/**
 * Vigenere OTP exhaustive decryption use case.
 * 
 * @author Ashkan Moatamed
 */
public class B1Q3 {
	/**
	 * Dependencies: <code>
	 * 		1. util.Vigenere
	 * 		2. util.CryptoTools
	 * </code>
	 */

	/**
	 * Known key.
	 */
	private static final char[] KEY = CryptoTools.clean("JABHXPVOLLCIJ".toCharArray());

	/**
	 * Known plaintext.
	 */
	private static final char[] PLAINTEXT = CryptoTools.clean("SENDMOREMONEY".toCharArray());

	/**
	 * Prevent instantiation.
	 */
	private B1Q3() {
		// Empty by design.
	}

	@Override
	protected Object clone() throws CloneNotSupportedException { // semi-copy
		throw new CloneNotSupportedException();
	}

	public static void main(String[] args) {
		System.out.println("The key is:\n" + CryptoTools.toString(B1Q3.KEY) + "\n");
		System.out.println("The plaintext is:\n" + CryptoTools.toString(B1Q3.PLAINTEXT) + "\n");

		// Create the cipher engine with the appropriate attributes.
		final Vigenere v = new Vigenere(B1Q3.KEY);
		final char[] ciphertext = v.encrypt(B1Q3.PLAINTEXT);
		System.out.println("The ciphertext is:\n" + CryptoTools.toString(ciphertext) + "\n");

		/*
		 * Eve sniffs the ciphertext and exhaustively attacks it to find the plaintext. She could use the
		 * following wrong key which gives her a surprisingly different plaintext.
		 */
		final char[] wrongKey = "ZEWDWPTFTVMIE".toCharArray();
		System.out.println("The wrong key is:\n" + CryptoTools.toString(wrongKey) + "\n");
		final char[] wrongPlaintext = Vigenere.decrypt(wrongKey, ciphertext);
		System.out.println("The wrong plaintext is:\n" + CryptoTools.toString(wrongPlaintext) + "\n");
	}
}
