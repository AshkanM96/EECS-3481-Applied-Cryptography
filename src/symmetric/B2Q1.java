package symmetric;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import util.Binary;
import util.CipherEngUtil;
import util.CryptoTools;
import util.Hex;
import util.SymCipherEng;

/**
 * DES diffusion (i.e., plaintext-ciphertext correspondence) avalanche use case.
 * 
 * @author Ashkan Moatamed
 */
public class B2Q1 {
	/**
	 * Dependencies: <code>
	 * 		1. util.Binary
	 * 		2. util.Hex
	 * 		3. util.CipherEngUtil
	 * 		4. util.SymCipherEng
	 * 		5. util.CryptoTools
	 * </code>
	 */

	/**
	 * Known key.
	 */
	private static final byte[] KEY = "universe".getBytes();

	/**
	 * Known plaintext.
	 */
	private static final byte[] PLAINTEXT = "Facebook".getBytes();

	/**
	 * Specifies whether intermediate messages should be printed to the standard output stream during
	 * each flip test run.
	 */
	private static final boolean PRINT_RUN = false;

	/**
	 * Number of times to flip one bit in the plaintext and then computing the ciphertext.
	 */
	private static final int MAX_RUNS = 100;

	/**
	 * Prevent instantiation.
	 */
	private B2Q1() {
		// Empty by design.
	}

	public static void main(String[] args) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
			InvalidAlgorithmParameterException {
		System.out.println("The key is:\n" + CryptoTools.toString(B2Q1.KEY) + "\n");
		System.out.println("The plaintext is:\n" + CryptoTools.toString(B2Q1.PLAINTEXT) + "\n");

		// Create the cipher engine with the appropriate attributes.
		final SymCipherEng engine = new SymCipherEng(SymCipherEng.ALGO_SYM.DES, B2Q1.KEY, CipherEngUtil.OPMODE.ECB,
				CipherEngUtil.PADDING.NoPadding);

		// Encrypt the plaintext using the engine to get the ciphertext.
		final byte[] ciphertext = engine.encrypt(B2Q1.PLAINTEXT);
		System.out.println("The ciphertext as a hex string is:\n" + Hex.toString(ciphertext) + "\n");
		System.out.println("The ciphertext as a binary string is:\n" + Binary.toString(ciphertext) + "\n");

		// Only print if requested.
		if (B2Q1.PRINT_RUN) {
			System.out.println();
		}

		final Random prng = ThreadLocalRandom.current();
		// Save the number of bits in the plaintext.
		final int numBits = Math.multiplyExact(Binary.BITS_PER_BYTE, B2Q1.PLAINTEXT.length);

		byte[] flippedCiphertext = null;
		long numDiffBits = 0L, totalDiffBitCount = 0L;
		for (int run = 0, flipBitIndex = 0; run != B2Q1.MAX_RUNS; ++run) {
			// Select a random bit to be flipped, and then flip it in the plaintext.
			flipBitIndex = prng.nextInt(numBits);
			Binary.flipBitEquals(B2Q1.PLAINTEXT, flipBitIndex);

			/*
			 * Encrypt the flipped plaintext using the engine, and then xor it with the original ciphertext, and
			 * then count the number of "on" bits to measure how different they are from each other.
			 */
			flippedCiphertext = engine.encrypt(B2Q1.PLAINTEXT);
			numDiffBits = Binary.countOnes(Binary.xor(ciphertext, flippedCiphertext));
			// Accumulate the count of the number of different bits across all tests.
			totalDiffBitCount = Math.addExact(totalDiffBitCount, numDiffBits);

			// Only print if requested.
			if (B2Q1.PRINT_RUN) {
				System.out.println("Flipping the " + (flipBitIndex + 1) + "^th bit in the plaintext.\n");
				System.out.println(
						"The flipped ciphertext as a binary string is:\n" + Binary.toString(flippedCiphertext) + "\n");
				System.out.println("The ciphertext and the flipped ciphertext differ in " + numDiffBits + " position"
						+ (numDiffBits != 1 ? "s.\n\n" : ".\n\n"));
			}

			// Restore the plaintext to its original value by flipping the selected bit again.
			Binary.flipBitEquals(B2Q1.PLAINTEXT, flipBitIndex);
		}
		System.out.println("An average of " + (1.0 * totalDiffBitCount / B2Q1.MAX_RUNS)
				+ " bits in the ciphertext were changed as a result of flipping one bit in the plaintext across "
				+ B2Q1.MAX_RUNS + " tests.\n");
	}
}
