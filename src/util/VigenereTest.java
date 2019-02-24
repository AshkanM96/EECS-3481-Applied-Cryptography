package util;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * JUnit4 tests for Vigenere.
 * 
 * @author Ashkan Moatamed
 */
public class VigenereTest {
	/**
	 * Dependencies: <code>
	 * 		1. util.Vigenere
	 * 		2. util.ArrayUtil
	 * </code>
	 */

	/**
	 * Testing Vigenere::encrypt(char[]), Vigenere::decrypt(char[]).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test01() {
		final char[] keyWord = "YORK".toCharArray();
		final Vigenere v = new Vigenere(keyWord);

		final char[] plaintext = "ATTHESTARTOFTHEFIRSTGAMETHEPRO".toCharArray();
		final char[] expectedCiphertext = "YHKRCGKKPHFPRVVPGFJDEODORVVZPC".toCharArray();
		assertTrue("Correct length", plaintext.length == expectedCiphertext.length);
		final char[] ciphertext = v.encrypt(plaintext);
		assertTrue("Correct ciphertext", Arrays.equals(ciphertext, expectedCiphertext));

		final char[] decrytedText = v.decrypt(ciphertext);
		assertTrue("Correct decrytedText", Arrays.equals(decrytedText, plaintext));
	}

	/**
	 * Testing Vigenere::encrypt(byte[]), Vigenere::decrypt(byte[]).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test02() {
		final char[] keyWord = "YORK".toCharArray();
		final Vigenere v = new Vigenere(keyWord);

		final byte[] plaintext = "ATTHESTARTOFTHEFIRSTGAMETHEPRO".getBytes();
		final byte[] expectedCiphertext = "YHKRCGKKPHFPRVVPGFJDEODORVVZPC".getBytes();
		assertTrue("Correct length", plaintext.length == expectedCiphertext.length);
		final byte[] ciphertext = v.encrypt(plaintext);
		assertTrue("Correct ciphertext", Arrays.equals(ciphertext, expectedCiphertext));

		final byte[] decrytedText = v.decrypt(ciphertext);
		assertTrue("Correct decrytedText", Arrays.equals(decrytedText, plaintext));
	}

	/**
	 * Testing Vigenere.decrypt(char[]), Vigenere::encrypt(char[]).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test03() {
		final char[] keyWord = "YORK".toCharArray();
		final Vigenere v = new Vigenere(keyWord);

		final char[] ciphertext = "YHKRCGKKPHFPRVVPGFJDEODORVVZPC".toCharArray();
		final char[] expectedPlaintext = "ATTHESTARTOFTHEFIRSTGAMETHEPRO".toCharArray();
		assertTrue("Correct length", ciphertext.length == expectedPlaintext.length);
		final char[] plaintext = Vigenere.decrypt(keyWord, ciphertext);
		assertTrue("Correct plaintext", Arrays.equals(plaintext, expectedPlaintext));

		final char[] encrytedText = v.encrypt(plaintext);
		assertTrue("Correct encrytedText", Arrays.equals(encrytedText, ciphertext));
	}

	/**
	 * Testing Vigenere.decrypt(byte[]), Vigenere::encrypt(byte[]).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test04() {
		final char[] keyWord = "YORK".toCharArray();
		final Vigenere v = new Vigenere(keyWord);

		final byte[] ciphertext = "YHKRCGKKPHFPRVVPGFJDEODORVVZPC".getBytes();
		final byte[] expectedPlaintext = "ATTHESTARTOFTHEFIRSTGAMETHEPRO".getBytes();
		assertTrue("Correct length", ciphertext.length == expectedPlaintext.length);
		final byte[] plaintext = Vigenere.decrypt(keyWord, ciphertext);
		assertTrue("Correct plaintext", Arrays.equals(plaintext, expectedPlaintext));

		final byte[] encrytedText = v.encrypt(plaintext);
		assertTrue("Correct encrytedText", Arrays.equals(encrytedText, ciphertext));
	}

	/**
	 * Testing the effects of order on Vigenere::encrypt(char[]), Vigenere::decrypt(char[]), and
	 * Vigenere.decrypt(char[]).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test05() {
		final char[] keyWord = "KEYWORD".toCharArray();
		final Vigenere v = new Vigenere(keyWord);

		{
			v.resetIndex();
			final char[][] plaintexts = { "PLAINTEXTNUMBERONE".toCharArray(), "PLAINTEXTNUMBERTWO".toCharArray(),
					"PLAINTEXTNUMBERTHREE".toCharArray() };
			final int numTexts = plaintexts.length;
			assertTrue("numTexts lower bound", numTexts >= 1);
			final char[][] expectedCiphertexts = { "ZPYEBKHHXLQASHBSLA".toCharArray(),
					"DCDSRRALKQEQZAFKZY".toCharArray(), "TJWWEWOBRJIDEOVRDFVH".toCharArray() };
			assertTrue("Correct numTexts", numTexts == expectedCiphertexts.length);
			for (int i = 0; i != numTexts; ++i) {
				assertTrue("Length at " + i, plaintexts[i].length == expectedCiphertexts[i].length);
			}

			final char[][] ciphertexts = new char[numTexts][];
			for (int i = 0; i != numTexts; ++i) {
				// Continue encrypting from wherever left off in keyword.
				ciphertexts[i] = v.encrypt(plaintexts[i]);
				assertTrue("Correct ciphertexts[" + i + "]", Arrays.equals(ciphertexts[i], expectedCiphertexts[i]));
			}
			assertTrue("Correct ciphertexts", ArrayUtil.equals(ciphertexts, expectedCiphertexts));

			final char[][] decryptedTexts = new char[numTexts][];
			for (int i = numTexts - 1; i > -1; --i) {
				// Decryption has to be performed in reverse order using Vigenere::decrypt(char[]).
				decryptedTexts[i] = v.decrypt(ciphertexts[i]);
				assertTrue("Correct decryptedTexts[" + i + "]", Arrays.equals(decryptedTexts[i], plaintexts[i]));
			}
			assertTrue("Correct decryptedTexts", ArrayUtil.equals(decryptedTexts, plaintexts));
		}

		{
			v.resetIndex();
			final char[][] plaintexts = { "PLAINTEXTNUMBERONE".toCharArray(), "PLAINTEXTNUMBERTWO".toCharArray(),
					"PLAINTEXTNUMBERTHREE".toCharArray() };
			final int numTexts = plaintexts.length;
			assertTrue("numTexts lower bound", numTexts >= 1);
			final char[][] expectedCiphertexts = { "ZPYEBKHHXLQASHBSLA".toCharArray(),
					"ZPYEBKHHXLQASHBXUK".toCharArray(), "ZPYEBKHHXLQASHBXFNSV".toCharArray() };
			assertTrue("Correct numTexts", numTexts == expectedCiphertexts.length);
			for (int i = 0; i != numTexts; ++i) {
				assertTrue("Length at " + i, plaintexts[i].length == expectedCiphertexts[i].length);
			}

			final char[][] ciphertexts = new char[numTexts][];
			for (int i = 0; i != numTexts; ++i) {
				// Every time start encrypting from the beginning of keyword.
				v.resetIndex();
				ciphertexts[i] = v.encrypt(plaintexts[i]);
				assertTrue("Correct ciphertexts[" + i + "]", Arrays.equals(ciphertexts[i], expectedCiphertexts[i]));
			}
			assertTrue("Correct ciphertexts", ArrayUtil.equals(ciphertexts, expectedCiphertexts));

			final int[] indices = new int[numTexts];
			for (int i = 0; i != numTexts; ++i) {
				indices[i] = i;
			}
			ArrayUtil.shuffle(indices);

			final char[][] decryptedTexts = new char[numTexts][];
			for (final int index : indices) {
				// Decryption can be performed in any order using Vigenere.decrypt(char[]).
				decryptedTexts[index] = Vigenere.decrypt(keyWord, ciphertexts[index]);
				assertTrue("Correct decryptedTexts[" + index + "]",
						Arrays.equals(decryptedTexts[index], plaintexts[index]));
			}
			assertTrue("Correct decryptedTexts", ArrayUtil.equals(decryptedTexts, plaintexts));
		}
	}

	/**
	 * Testing the effects of order on Vigenere::encrypt(byte[]), Vigenere::decrypt(byte[]), and
	 * Vigenere.decrypt(byte[]).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test06() {
		final char[] keyWord = "KEYWORD".toCharArray();
		final Vigenere v = new Vigenere(keyWord);

		{
			v.resetIndex();
			final byte[][] plaintexts = { "PLAINTEXTNUMBERONE".getBytes(), "PLAINTEXTNUMBERTWO".getBytes(),
					"PLAINTEXTNUMBERTHREE".getBytes() };
			final int numTexts = plaintexts.length;
			assertTrue("numTexts lower bound", numTexts >= 1);
			final byte[][] expectedCiphertexts = { "ZPYEBKHHXLQASHBSLA".getBytes(), "DCDSRRALKQEQZAFKZY".getBytes(),
					"TJWWEWOBRJIDEOVRDFVH".getBytes() };
			assertTrue("Correct numTexts", numTexts == expectedCiphertexts.length);
			for (int i = 0; i != numTexts; ++i) {
				assertTrue("Length at " + i, plaintexts[i].length == expectedCiphertexts[i].length);
			}

			final byte[][] ciphertexts = new byte[numTexts][];
			for (int i = 0; i != numTexts; ++i) {
				// Continue encrypting from wherever left off in keyword.
				ciphertexts[i] = v.encrypt(plaintexts[i]);
				assertTrue("Correct ciphertexts[" + i + "]", Arrays.equals(ciphertexts[i], expectedCiphertexts[i]));
			}
			assertTrue("Correct ciphertexts", ArrayUtil.equals(ciphertexts, expectedCiphertexts));

			final byte[][] decryptedTexts = new byte[numTexts][];
			for (int i = numTexts - 1; i > -1; --i) {
				// Decryption has to be performed in reverse order using Vigenere::decrypt(byte[]).
				decryptedTexts[i] = v.decrypt(ciphertexts[i]);
				assertTrue("Correct decryptedTexts[" + i + "]", Arrays.equals(decryptedTexts[i], plaintexts[i]));
			}
			assertTrue("Correct decryptedTexts", ArrayUtil.equals(decryptedTexts, plaintexts));
		}

		{
			v.resetIndex();
			final byte[][] plaintexts = { "PLAINTEXTNUMBERONE".getBytes(), "PLAINTEXTNUMBERTWO".getBytes(),
					"PLAINTEXTNUMBERTHREE".getBytes() };
			final int numTexts = plaintexts.length;
			assertTrue("numTexts lower bound", numTexts >= 1);
			final byte[][] expectedCiphertexts = { "ZPYEBKHHXLQASHBSLA".getBytes(), "ZPYEBKHHXLQASHBXUK".getBytes(),
					"ZPYEBKHHXLQASHBXFNSV".getBytes() };
			assertTrue("Correct numTexts", numTexts == expectedCiphertexts.length);
			for (int i = 0; i != numTexts; ++i) {
				assertTrue("Length at " + i, plaintexts[i].length == expectedCiphertexts[i].length);
			}

			final byte[][] ciphertexts = new byte[numTexts][];
			for (int i = 0; i != numTexts; ++i) {
				// Every time start encrypting from the beginning of keyword.
				v.resetIndex();
				ciphertexts[i] = v.encrypt(plaintexts[i]);
				assertTrue("Correct ciphertexts[" + i + "]", Arrays.equals(ciphertexts[i], expectedCiphertexts[i]));
			}
			assertTrue("Correct ciphertexts", ArrayUtil.equals(ciphertexts, expectedCiphertexts));

			final int[] indices = new int[numTexts];
			for (int i = 0; i != numTexts; ++i) {
				indices[i] = i;
			}
			ArrayUtil.shuffle(indices);

			final byte[][] decryptedTexts = new byte[numTexts][];
			for (final int index : indices) {
				// Decryption can be performed in any order using Vigenere.decrypt(byte[]).
				decryptedTexts[index] = Vigenere.decrypt(keyWord, ciphertexts[index]);
				assertTrue("Correct decryptedTexts[" + index + "]",
						Arrays.equals(decryptedTexts[index], plaintexts[index]));
			}
			assertTrue("Correct decryptedTexts", ArrayUtil.equals(decryptedTexts, plaintexts));
		}
	}
}
