package tests.test1;

import java.io.FileNotFoundException;
import java.io.IOException;

import util.CryptoTools;
import util.Vigenere;

public class T1Q1 {
	/**
	 * Dependencies: <code>
	 * 		1. util.Vigenere
	 * 		2. util.CryptoTools
	 * </code>
	 */

	public static void main(String[] args) throws FileNotFoundException, IOException {
		final byte[] ciphertext = CryptoTools.fileToBytes("./src/tests/test1/Q10.ct");
		final Vigenere.CryptoInfoVigenere result = Vigenere.kcaCryptanalytic(ciphertext, true);
		final String key = new String(result.keyWord);
		System.out.println("Vigenere key length is: " + key.length());
		final String plaintext = CryptoTools.toString(result.plaintext);
		System.out.println("First 50 chars of plaintext are:");
		System.out.println(plaintext.substring(0, 50));
	}
}
