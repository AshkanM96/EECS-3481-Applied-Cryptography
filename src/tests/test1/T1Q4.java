package tests.test1;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import util.CryptoTools;

public class T1Q4 {
	/**
	 * Dependencies: <code>
	 * 		1. util.CryptoTools
	 * </code>
	 */

	private static final char[] ALPHABET = "ABCD".toCharArray();

	// private static final double[] ALPHABET_PROBABILITIES = { 0.5, 0.2, 0.1, 0.2 };

	public static void main(String[] args) throws FileNotFoundException, IOException {
		final byte[] ciphertext = CryptoTools.fileToBytes("./src/tests/test1/Q42.ct");

		final int keyLength = 3;

		/*
		 * Take subsets of length keyLength from the ciphertext and find the most frequent letter. That
		 * letter is probably the mapping of 'A' since 'A' is the most common letter in ALPHABET and the
		 * subset is just ALPHABET letters shifted by the same character of the vigenere cipher key.
		 */
		final char[] keyWord = new char[keyLength];
		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int[] freq = null;
		int maxFreq = 0;
		char maxFreqLetter = '\0';
		for (int keyWordFilledIndex = 0; keyWordFilledIndex != keyLength; ++keyWordFilledIndex) {
			for (int i = keyWordFilledIndex; i < ciphertext.length; i += keyLength) {
				bos.write(ciphertext[i]);
			}
			freq = CryptoTools.getFrequencies(bos.toByteArray());
			bos.reset();

			for (int i = 0; i != ALPHABET.length; ++i) {
				System.out.println("freq[" + (char) ('A' + i) + "] == " + freq[i]);
			}

			/*
			 * 'A' is the most common letter in ALPHABET and as such the letter with the highest frequency is
			 * probably the mapping of 'A'. Save that mapping letter in maxFreqLetter so that the
			 * keyWordFilledIndex^th character of the vigenere key can be found from it.
			 */
			maxFreq = freq[0];
			maxFreqLetter = (char) ('A' + 0);
			for (int i = 1, f = 0; i != ALPHABET.length; ++i) {
				if ((f = freq[i]) > maxFreq) {
					maxFreq = f;
					maxFreqLetter = (char) ('A' + i);
				}
			}
			keyWord[keyWordFilledIndex] = (char) ((maxFreqLetter - 'A' + ALPHABET.length) % ALPHABET.length + 'A');

			System.out.println("Probable mapping of \'A\' is \'" + maxFreqLetter + "\'.");
			System.out.println("keyWord[" + keyWordFilledIndex + "] == \'" + keyWord[keyWordFilledIndex] + "\'\n");
		}

		System.out.println("Vigenere key word is: " + CryptoTools.toString(keyWord));

		// Decrypt the ciphertext using keyWord.
		final byte[] plaintext = new byte[ciphertext.length];
		for (int i = 0, index = 0; i != plaintext.length; ++i) {
			plaintext[i] = (byte) ((ciphertext[i] - 'A' - (keyWord[index] - 'A') + ALPHABET.length) % ALPHABET.length
					+ 'A');
			if (++index == keyLength) {
				index = 0;
			}
			// index = (index + 1) % key.length;
		}

		System.out.println("Plaintext is:");
		for (final byte b : plaintext) {
			System.out.print((char) b);
		}
		System.out.println('\n');
	}
}
