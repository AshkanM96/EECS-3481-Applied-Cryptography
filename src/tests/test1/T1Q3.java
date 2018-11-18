package tests.test1;

import java.io.FileNotFoundException;
import java.io.IOException;

import util.Affine;
import util.CryptoTools;

public class T1Q3 {
	/**
	 * Dependencies: <code>
	 * 		1. util.Affine
	 * 		2. util.CryptoTools
	 * </code>
	 */

	public static void main(String[] args) throws FileNotFoundException, IOException {
		final byte[] ciphertext = CryptoTools.fileToBytes("./src/tests/test1/Q32.ct");
		final Affine.CryptoInfoAffine result = Affine.kcaExhaustive(ciphertext, true);
		final int alpha = result.alphas[0];
		final int beta = result.betas[0];
		final String plaintext = CryptoTools.toString(result.plaintexts[0]);
		System.out.println("Affine key is: " + Affine.toString(alpha, beta));
		System.out.println("First 50 chars of plaintext are:");
		System.out.println(plaintext.substring(0, 50));
	}
}
