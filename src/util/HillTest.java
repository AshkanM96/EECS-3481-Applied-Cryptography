package util;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * JUnit4 tests for Hill.
 * 
 * @author Ashkan Moatamed
 */
public class HillTest {
	/**
	 * Dependencies: <code>
	 * 		1. util.Hill
	 * 		2. util.CryptoTools
	 * 		3. util.ArrayUtil
	 * </code>
	 */

	private static final boolean PRINT = false;

	/**
	 * Testing Hill::encrypt(char[]), Hill::decrypt(char[]).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test01() {
		final int[][] key = { { 1, 2, 3 }, { 4, 5, 6 }, { 11, 9, 8 } };
		final Hill h = new Hill(key);
		h.prepareKey();
		assertTrue("isValidKey", h.isValidKey());

		final char[] plaintext = "ABC".toCharArray();
		final char[] ciphertext = h.encrypt(plaintext); // AXW
		assertTrue("Correct ciphertext", Arrays.equals(ciphertext, "AXW".toCharArray()));
		final char[] decrytedText = h.decrypt(ciphertext);
		assertTrue("Correct decrytedText", Arrays.equals(decrytedText, plaintext));

		if (HillTest.PRINT) {
			System.out.println("test01");
			System.out.println("\"" + CryptoTools.toString(plaintext) + "\" encrypted is \""
					+ CryptoTools.toString(ciphertext) + "\"");
			System.out.println("\"" + CryptoTools.toString(ciphertext) + "\" decrypted is \""
					+ CryptoTools.toString(decrytedText) + "\"");
			System.out.println();
		}
	}

	/**
	 * Testing Hill::encrypt(char[]), Hill::decrypt(char[]).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test02() {
		final int[][] key = { { 1, 2, 3 }, { 4, 5, 6 }, { 11, 9, 8 } };
		final Hill h = new Hill(key);
		h.prepareKey();
		assertTrue("isValidKey", h.isValidKey());

		final char[] plaintext = "KEY".toCharArray();
		final char[] ciphertext = h.encrypt(plaintext); // EWM
		assertTrue("Correct ciphertext", Arrays.equals(ciphertext, "EWM".toCharArray()));
		final char[] decrytedText = h.decrypt(ciphertext);
		assertTrue("Correct decrytedText", Arrays.equals(decrytedText, plaintext));

		if (HillTest.PRINT) {
			System.out.println("test02");
			System.out.println("\"" + CryptoTools.toString(plaintext) + "\" encrypted is \""
					+ CryptoTools.toString(ciphertext) + "\"");
			System.out.println("\"" + CryptoTools.toString(ciphertext) + "\" decrypted is \""
					+ CryptoTools.toString(decrytedText) + "\"");
			System.out.println();
		}
	}

	/**
	 * Testing Hill::encrypt(byte[]), Hill::decrypt(byte[]).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test03() {
		final int[][] key = { { 1, 2, 3 }, { 4, 5, 6 }, { 11, 9, 8 } };
		final Hill h = new Hill(key);
		h.prepareKey();
		assertTrue("isValidKey", h.isValidKey());

		final byte[] plaintext = "ABC".getBytes();
		final byte[] ciphertext = h.encrypt(plaintext); // AXW
		assertTrue("Correct ciphertext", Arrays.equals(ciphertext, "AXW".getBytes()));
		final byte[] decrytedText = h.decrypt(ciphertext);
		assertTrue("Correct decrytedText", Arrays.equals(decrytedText, plaintext));

		if (HillTest.PRINT) {
			System.out.println("test03");
			System.out.println("\"" + CryptoTools.toString(plaintext) + "\" encrypted is \""
					+ CryptoTools.toString(ciphertext) + "\"");
			System.out.println("\"" + CryptoTools.toString(ciphertext) + "\" decrypted is \""
					+ CryptoTools.toString(decrytedText) + "\"");
			System.out.println();
		}
	}

	/**
	 * Testing Hill::encrypt(byte[]), Hill::decrypt(byte[]).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test04() {
		final int[][] key = { { 1, 2, 3 }, { 4, 5, 6 }, { 11, 9, 8 } };
		final Hill h = new Hill(key);
		h.prepareKey();
		assertTrue("isValidKey", h.isValidKey());

		final byte[] plaintext = "KEY".getBytes();
		final byte[] ciphertext = h.encrypt(plaintext); // EWM
		assertTrue("Correct ciphertext", Arrays.equals(ciphertext, "EWM".getBytes()));
		final byte[] decrytedText = h.decrypt(ciphertext);
		assertTrue("Correct decrytedText", Arrays.equals(decrytedText, plaintext));

		if (HillTest.PRINT) {
			System.out.println("test04");
			System.out.println("\"" + CryptoTools.toString(plaintext) + "\" encrypted is \""
					+ CryptoTools.toString(ciphertext) + "\"");
			System.out.println("\"" + CryptoTools.toString(ciphertext) + "\" decrypted is \""
					+ CryptoTools.toString(decrytedText) + "\"");
			System.out.println();
		}
	}

	/**
	 * Testing Hill::decrypt(char[]), Hill::encrypt(char[]).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test05() {
		final int[][] key = { { 1, 2, 3 }, { 4, 5, 6 }, { 11, 9, 8 } };
		final Hill h = new Hill(key);
		h.prepareKey();
		assertTrue("isValidKey", h.isValidKey());

		final char[] ciphertext = "AXW".toCharArray();
		final char[] decrytedText = h.decrypt(ciphertext); // ABC
		assertTrue("Correct decrytedText", Arrays.equals(decrytedText, "ABC".toCharArray()));
		final char[] encrytedText = h.encrypt(decrytedText);
		assertTrue("Correct encrytedText", Arrays.equals(encrytedText, ciphertext));

		if (HillTest.PRINT) {
			System.out.println("test05");
			System.out.println("\"" + CryptoTools.toString(ciphertext) + "\" decrypted is \""
					+ CryptoTools.toString(decrytedText) + "\"");
			System.out.println("\"" + CryptoTools.toString(decrytedText) + "\" encrypted is \""
					+ CryptoTools.toString(encrytedText) + "\"");
			System.out.println();
		}
	}

	/**
	 * Testing Hill::decrypt(char[]), Hill::encrypt(char[]).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test06() {
		final int[][] key = { { 1, 2, 3 }, { 4, 5, 6 }, { 11, 9, 8 } };
		final Hill h = new Hill(key);
		h.prepareKey();
		assertTrue("isValidKey", h.isValidKey());

		final char[] ciphertext = "EWM".toCharArray();
		final char[] decrytedText = h.decrypt(ciphertext); // KEY
		assertTrue("Correct decrytedText", Arrays.equals(decrytedText, "KEY".toCharArray()));
		final char[] encrytedText = h.encrypt(decrytedText);
		assertTrue("Correct encrytedText", Arrays.equals(encrytedText, ciphertext));

		if (HillTest.PRINT) {
			System.out.println("test06");
			System.out.println("\"" + CryptoTools.toString(ciphertext) + "\" decrypted is \""
					+ CryptoTools.toString(decrytedText) + "\"");
			System.out.println("\"" + CryptoTools.toString(decrytedText) + "\" encrypted is \""
					+ CryptoTools.toString(encrytedText) + "\"");
			System.out.println();
		}
	}

	/**
	 * Testing Hill::decrypt(byte[]), Hill::encrypt(byte[]).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test07() {
		final int[][] key = { { 1, 2, 3 }, { 4, 5, 6 }, { 11, 9, 8 } };
		final Hill h = new Hill(key);
		h.prepareKey();
		assertTrue("isValidKey", h.isValidKey());

		final byte[] ciphertext = "AXW".getBytes();
		final byte[] decrytedText = h.decrypt(ciphertext); // ABC
		assertTrue("Correct decrytedText", Arrays.equals(decrytedText, "ABC".getBytes()));
		final byte[] encrytedText = h.encrypt(decrytedText);
		assertTrue("Correct encrytedText", Arrays.equals(encrytedText, ciphertext));

		if (HillTest.PRINT) {
			System.out.println("test07");
			System.out.println("\"" + CryptoTools.toString(ciphertext) + "\" decrypted is \""
					+ CryptoTools.toString(decrytedText) + "\"");
			System.out.println("\"" + CryptoTools.toString(decrytedText) + "\" encrypted is \""
					+ CryptoTools.toString(encrytedText) + "\"");
			System.out.println();
		}
	}

	/**
	 * Testing Hill::decrypt(byte[]), Hill::encrypt(byte[]).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test08() {
		final int[][] key = { { 1, 2, 3 }, { 4, 5, 6 }, { 11, 9, 8 } };
		final Hill h = new Hill(key);
		h.prepareKey();
		assertTrue("isValidKey", h.isValidKey());

		final byte[] ciphertext = "EWM".getBytes();
		final byte[] decrytedText = h.decrypt(ciphertext); // KEY
		assertTrue("Correct decrytedText", Arrays.equals(decrytedText, "KEY".getBytes()));
		final byte[] encrytedText = h.encrypt(decrytedText);
		assertTrue("Correct encrytedText", Arrays.equals(encrytedText, ciphertext));

		if (HillTest.PRINT) {
			System.out.println("test08");
			System.out.println("\"" + CryptoTools.toString(ciphertext) + "\" decrypted is \""
					+ CryptoTools.toString(decrytedText) + "\"");
			System.out.println("\"" + CryptoTools.toString(decrytedText) + "\" encrypted is \""
					+ CryptoTools.toString(encrytedText) + "\"");
			System.out.println();
		}
	}

	/**
	 * Testing the effects of order on Hill::encrypt(char[]), Hill::decrypt(char[]).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test09() {
		final int[][] key = { { 1, 2, 3 }, { 4, 5, 6 }, { 11, 9, 8 } };
		final Hill h = new Hill(key);
		h.prepareKey();
		assertTrue("isValidKey", h.isValidKey());

		final char[][] plaintexts = { "GET".toCharArray(), "SET".toCharArray(), "LET".toCharArray(),
				"ALL".toCharArray(), "SAY".toCharArray(), "JOB".toCharArray(), "ZIP".toCharArray(), "JAW".toCharArray(),
				"JOY".toCharArray(), "MAX".toCharArray(), "MIN".toCharArray() };
		final int numTexts = plaintexts.length;
		assertTrue("numTexts lower bound", 10 <= numTexts);
		final char[][] expectedCiphertexts = { "XVM".toCharArray(), "JTW".toCharArray(), "CFB".toCharArray(),
				"JYY".toCharArray(), "WSM".toCharArray(), "YTP".toCharArray(), "ORJ".toCharArray(), "RIV".toCharArray(),
				"RSR".toCharArray(), "FXM".toCharArray(), "FZG".toCharArray() };
		assertTrue("Correct numTexts", numTexts == expectedCiphertexts.length);
		for (int i = 0; i != numTexts; ++i) {
			assertTrue("Length at " + i, plaintexts[i].length == expectedCiphertexts[i].length);
		}

		final int[] indices = new int[numTexts];
		for (int i = 0; i != numTexts; ++i) {
			indices[i] = i;
		}
		ArrayUtil.shuffle(indices);

		if (HillTest.PRINT) {
			System.out.println("test09");
		}

		final char[][] ciphertexts = new char[numTexts][];
		for (final int index : indices) {
			ciphertexts[index] = h.encrypt(plaintexts[index]);
			if (HillTest.PRINT) {
				System.out.println("ciphertexts[" + index + "] == \"" + CryptoTools.toString(ciphertexts[index])
						+ "\", expectedCiphertexts[" + index + "] == \""
						+ CryptoTools.toString(expectedCiphertexts[index]) + "\"");
			}
			assertTrue("Correct ciphertexts[" + index + "]",
					Arrays.equals(ciphertexts[index], expectedCiphertexts[index]));
		}
		assertTrue("Correct ciphertexts", ArrayUtil.equals(ciphertexts, expectedCiphertexts));

		if (HillTest.PRINT) {
			System.out.println();
		}
	}

	/**
	 * Testing the effects of order on Hill::encrypt(byte[]), Hill::decrypt(byte[]).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test10() {
		final int[][] key = { { 1, 2, 3 }, { 4, 5, 6 }, { 11, 9, 8 } };
		final Hill h = new Hill(key);
		h.prepareKey();
		assertTrue("isValidKey", h.isValidKey());

		final byte[][] plaintexts = { "GET".getBytes(), "SET".getBytes(), "LET".getBytes(), "ALL".getBytes(),
				"SAY".getBytes(), "JOB".getBytes(), "ZIP".getBytes(), "JAW".getBytes(), "JOY".getBytes(),
				"MAX".getBytes(), "MIN".getBytes() };
		final int numTexts = plaintexts.length;
		assertTrue("numTexts lower bound", 10 <= numTexts);
		final byte[][] expectedCiphertexts = { "XVM".getBytes(), "JTW".getBytes(), "CFB".getBytes(), "JYY".getBytes(),
				"WSM".getBytes(), "YTP".getBytes(), "ORJ".getBytes(), "RIV".getBytes(), "RSR".getBytes(),
				"FXM".getBytes(), "FZG".getBytes() };
		assertTrue("Correct numTexts", numTexts == expectedCiphertexts.length);
		for (int i = 0; i != numTexts; ++i) {
			assertTrue("Length at " + i, plaintexts[i].length == expectedCiphertexts[i].length);
		}

		final int[] indices = new int[numTexts];
		for (int i = 0; i != numTexts; ++i) {
			indices[i] = i;
		}
		ArrayUtil.shuffle(indices);

		if (HillTest.PRINT) {
			System.out.println("test10");
		}

		final byte[][] ciphertexts = new byte[numTexts][];
		for (final int index : indices) {
			ciphertexts[index] = h.encrypt(plaintexts[index]);
			if (HillTest.PRINT) {
				System.out.println("ciphertexts[" + index + "] == \"" + CryptoTools.toString(ciphertexts[index])
						+ "\", expectedCiphertexts[" + index + "] == \""
						+ CryptoTools.toString(expectedCiphertexts[index]) + "\"");
			}
			assertTrue("Correct ciphertexts[" + index + "]",
					Arrays.equals(ciphertexts[index], expectedCiphertexts[index]));
		}
		assertTrue("Correct ciphertexts", ArrayUtil.equals(ciphertexts, expectedCiphertexts));

		if (HillTest.PRINT) {
			System.out.println();
		}
	}

	/**
	 * Testing the effects of order on Hill::decrypt(char[]), Hill::encrypt(char[]).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test11() {
		final int[][] key = { { 1, 2, 3 }, { 4, 5, 6 }, { 11, 9, 8 } };
		final Hill h = new Hill(key);
		h.prepareKey();
		assertTrue("isValidKey", h.isValidKey());

		final char[][] ciphertexts = { "XVM".toCharArray(), "JTW".toCharArray(), "CFB".toCharArray(),
				"JYY".toCharArray(), "WSM".toCharArray(), "YTP".toCharArray(), "ORJ".toCharArray(), "RIV".toCharArray(),
				"RSR".toCharArray(), "FXM".toCharArray(), "FZG".toCharArray() };
		final int numTexts = ciphertexts.length;
		assertTrue("numTexts lower bound", 10 <= numTexts);
		final char[][] expectedPlaintexts = { "GET".toCharArray(), "SET".toCharArray(), "LET".toCharArray(),
				"ALL".toCharArray(), "SAY".toCharArray(), "JOB".toCharArray(), "ZIP".toCharArray(), "JAW".toCharArray(),
				"JOY".toCharArray(), "MAX".toCharArray(), "MIN".toCharArray() };
		assertTrue("Correct numTexts", numTexts == ciphertexts.length);
		for (int i = 0; i != numTexts; ++i) {
			assertTrue("Length at " + i, ciphertexts[i].length == expectedPlaintexts[i].length);
		}

		final int[] indices = new int[numTexts];
		for (int i = 0; i != numTexts; ++i) {
			indices[i] = i;
		}
		ArrayUtil.shuffle(indices);

		if (HillTest.PRINT) {
			System.out.println("test11");
		}

		final char[][] plaintexts = new char[numTexts][];
		for (final int index : indices) {
			plaintexts[index] = h.decrypt(ciphertexts[index]);
			if (HillTest.PRINT) {
				System.out.println("plaintexts[" + index + "] == \"" + CryptoTools.toString(plaintexts[index])
						+ "\", expectedPlaintexts[" + index + "] == \""
						+ CryptoTools.toString(expectedPlaintexts[index]) + "\"");
			}
			assertTrue("Correct plaintexts[" + index + "]",
					Arrays.equals(plaintexts[index], expectedPlaintexts[index]));
		}
		assertTrue("Correct plaintexts", ArrayUtil.equals(plaintexts, expectedPlaintexts));

		if (HillTest.PRINT) {
			System.out.println();
		}
	}

	/**
	 * Testing the effects of order on Hill::decrypt(byte[]), Hill::encrypt(byte[]).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test12() {
		final int[][] key = { { 1, 2, 3 }, { 4, 5, 6 }, { 11, 9, 8 } };
		final Hill h = new Hill(key);
		h.prepareKey();
		assertTrue("isValidKey", h.isValidKey());

		final byte[][] ciphertexts = { "XVM".getBytes(), "JTW".getBytes(), "CFB".getBytes(), "JYY".getBytes(),
				"WSM".getBytes(), "YTP".getBytes(), "ORJ".getBytes(), "RIV".getBytes(), "RSR".getBytes(),
				"FXM".getBytes(), "FZG".getBytes() };
		final int numTexts = ciphertexts.length;
		assertTrue("numTexts lower bound", 10 <= numTexts);
		final byte[][] expectedPlaintexts = { "GET".getBytes(), "SET".getBytes(), "LET".getBytes(), "ALL".getBytes(),
				"SAY".getBytes(), "JOB".getBytes(), "ZIP".getBytes(), "JAW".getBytes(), "JOY".getBytes(),
				"MAX".getBytes(), "MIN".getBytes() };
		assertTrue("Correct numTexts", numTexts == ciphertexts.length);
		for (int i = 0; i != numTexts; ++i) {
			assertTrue("Length at " + i, ciphertexts[i].length == expectedPlaintexts[i].length);
		}

		final int[] indices = new int[numTexts];
		for (int i = 0; i != numTexts; ++i) {
			indices[i] = i;
		}
		ArrayUtil.shuffle(indices);

		if (HillTest.PRINT) {
			System.out.println("test12");
		}

		final byte[][] plaintexts = new byte[numTexts][];
		for (final int index : indices) {
			plaintexts[index] = h.decrypt(ciphertexts[index]);
			if (HillTest.PRINT) {
				System.out.println("plaintexts[" + index + "] == \"" + CryptoTools.toString(plaintexts[index])
						+ "\", expectedPlaintexts[" + index + "] == \""
						+ CryptoTools.toString(expectedPlaintexts[index]) + "\"");
			}
			assertTrue("Correct plaintexts[" + index + "]",
					Arrays.equals(plaintexts[index], expectedPlaintexts[index]));
		}
		assertTrue("Correct plaintexts", ArrayUtil.equals(plaintexts, expectedPlaintexts));

		if (HillTest.PRINT) {
			System.out.println();
		}
	}
}
