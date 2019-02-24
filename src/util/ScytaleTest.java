package util;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * JUnit4 tests for Scytale.
 * 
 * @author Ashkan Moatamed
 */
public class ScytaleTest {
	/**
	 * Dependencies: <code>
	 * 		1. util.Scytale
	 * 		2. util.CryptoTools
	 * 		3. util.ArrayUtil
	 * </code>
	 */

	private static final boolean PRINT = false;

	/**
	 * Testing Scytale::apply(char[]).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test01() {
		final int[][] key = { { 1, 0, 0 }, { 0, 0, 1 }, { 0, 1, 0 } };
		final Scytale s = new Scytale(key);
		s.prepareKey();
		assertTrue("isValidKey", s.isValidKey());

		final char[] plaintext = "ABC".toCharArray();
		final char[] ciphertext = s.apply(plaintext); // ACB
		assertTrue("Correct ciphertext", Arrays.equals(ciphertext, "ACB".toCharArray()));
		final char[] decrytedText = s.apply(ciphertext);
		assertTrue("Correct decrytedText", Arrays.equals(decrytedText, plaintext));

		if (ScytaleTest.PRINT) {
			System.out.println("test01");
			System.out.println("\"" + CryptoTools.toString(plaintext) + "\" encrypted is \""
					+ CryptoTools.toString(ciphertext) + "\"");
			System.out.println("\"" + CryptoTools.toString(ciphertext) + "\" decrypted is \""
					+ CryptoTools.toString(decrytedText) + "\"");
			System.out.println();
		}
	}

	/**
	 * Testing Scytale::apply(byte[]).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test02() {
		final int[][] key = { { 1, 0, 0 }, { 0, 0, 1 }, { 0, 1, 0 } };
		final Scytale s = new Scytale(key);
		s.prepareKey();
		assertTrue("isValidKey", s.isValidKey());

		final byte[] plaintext = "ABC".getBytes();
		final byte[] ciphertext = s.apply(plaintext); // ACB
		assertTrue("Correct ciphertext", Arrays.equals(ciphertext, "ACB".getBytes()));
		final byte[] decrytedText = s.apply(ciphertext);
		assertTrue("Correct decrytedText", Arrays.equals(decrytedText, plaintext));

		if (ScytaleTest.PRINT) {
			System.out.println("test02");
			System.out.println("\"" + CryptoTools.toString(plaintext) + "\" encrypted is \""
					+ CryptoTools.toString(ciphertext) + "\"");
			System.out.println("\"" + CryptoTools.toString(ciphertext) + "\" decrypted is \""
					+ CryptoTools.toString(decrytedText) + "\"");
			System.out.println();
		}
	}

	/**
	 * Testing the effects of order on Scytale::apply(char[]).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test03() {
		final int[][] key = { { 1, 0, 0 }, { 0, 0, 1 }, { 0, 1, 0 } };
		final Scytale s = new Scytale(key);
		s.prepareKey();
		assertTrue("isValidKey", s.isValidKey());

		final char[][] plaintexts = { "GET".toCharArray(), "SET".toCharArray(), "LET".toCharArray(),
				"ALL".toCharArray(), "SAY".toCharArray(), "JOB".toCharArray(), "ZIP".toCharArray(), "JAW".toCharArray(),
				"JOY".toCharArray(), "MAX".toCharArray(), "MIN".toCharArray() };
		final int numTexts = plaintexts.length;
		assertTrue("numTexts lower bound", numTexts >= 10);
		final char[][] expectedCiphertexts = { "GTE".toCharArray(), "STE".toCharArray(), "LTE".toCharArray(),
				"ALL".toCharArray(), "SYA".toCharArray(), "JBO".toCharArray(), "ZPI".toCharArray(), "JWA".toCharArray(),
				"JYO".toCharArray(), "MXA".toCharArray(), "MNI".toCharArray() };
		assertTrue("Correct numTexts", numTexts == expectedCiphertexts.length);
		for (int i = 0; i != numTexts; ++i) {
			assertTrue("Length at " + i, plaintexts[i].length == expectedCiphertexts[i].length);
		}

		final int[] indices = new int[numTexts];
		for (int i = 0; i != numTexts; ++i) {
			indices[i] = i;
		}
		ArrayUtil.shuffle(indices);

		if (ScytaleTest.PRINT) {
			System.out.println("test03");
		}

		final char[][] ciphertexts = new char[numTexts][];
		for (final int index : indices) {
			ciphertexts[index] = s.apply(plaintexts[index]);
			if (ScytaleTest.PRINT) {
				System.out.println("ciphertexts[" + index + "] == \"" + CryptoTools.toString(ciphertexts[index])
						+ "\", expectedCiphertexts[" + index + "] == \""
						+ CryptoTools.toString(expectedCiphertexts[index]) + "\"");
			}
			assertTrue("Correct ciphertexts[" + index + "]",
					Arrays.equals(ciphertexts[index], expectedCiphertexts[index]));
		}
		assertTrue("Correct ciphertexts", ArrayUtil.equals(ciphertexts, expectedCiphertexts));

		if (ScytaleTest.PRINT) {
			System.out.println();
		}
	}

	/**
	 * Testing the effects of order on Scytale::apply(byte[]).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test04() {
		final int[][] key = { { 1, 0, 0 }, { 0, 0, 1 }, { 0, 1, 0 } };
		final Scytale s = new Scytale(key);
		s.prepareKey();
		assertTrue("isValidKey", s.isValidKey());

		final byte[][] plaintexts = { "GET".getBytes(), "SET".getBytes(), "LET".getBytes(), "ALL".getBytes(),
				"SAY".getBytes(), "JOB".getBytes(), "ZIP".getBytes(), "JAW".getBytes(), "JOY".getBytes(),
				"MAX".getBytes(), "MIN".getBytes() };
		final int numTexts = plaintexts.length;
		assertTrue("numTexts lower bound", numTexts >= 10);
		final byte[][] expectedCiphertexts = { "GTE".getBytes(), "STE".getBytes(), "LTE".getBytes(), "ALL".getBytes(),
				"SYA".getBytes(), "JBO".getBytes(), "ZPI".getBytes(), "JWA".getBytes(), "JYO".getBytes(),
				"MXA".getBytes(), "MNI".getBytes() };
		assertTrue("Correct numTexts", numTexts == expectedCiphertexts.length);
		for (int i = 0; i != numTexts; ++i) {
			assertTrue("Length at " + i, plaintexts[i].length == expectedCiphertexts[i].length);
		}

		final int[] indices = new int[numTexts];
		for (int i = 0; i != numTexts; ++i) {
			indices[i] = i;
		}
		ArrayUtil.shuffle(indices);

		if (ScytaleTest.PRINT) {
			System.out.println("test04");
		}

		final byte[][] ciphertexts = new byte[numTexts][];
		for (final int index : indices) {
			ciphertexts[index] = s.apply(plaintexts[index]);
			if (ScytaleTest.PRINT) {
				System.out.println("ciphertexts[" + index + "] == \"" + CryptoTools.toString(ciphertexts[index])
						+ "\", expectedCiphertexts[" + index + "] == \""
						+ CryptoTools.toString(expectedCiphertexts[index]) + "\"");
			}
			assertTrue("Correct ciphertexts[" + index + "]",
					Arrays.equals(ciphertexts[index], expectedCiphertexts[index]));
		}
		assertTrue("Correct ciphertexts", ArrayUtil.equals(ciphertexts, expectedCiphertexts));

		if (ScytaleTest.PRINT) {
			System.out.println();
		}
	}

	/**
	 * Testing Scytale.place(char[], int).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test05() {
		final int numCols = 3;
		final char[] plaintext = "THEKEYOFTHISCODESHIFTISTHREEZZ".toCharArray();
		final char[] ciphertext = Scytale.place(plaintext, numCols); // TKOHCEIIHEHEFIOSFSRZEYTSDHTTEZ
		assertTrue("Correct ciphertext", Arrays.equals(ciphertext, "TKOHCEIIHEHEFIOSFSRZEYTSDHTTEZ".toCharArray()));
		final char[] decrytedText = Scytale.place(ciphertext, MatrixInt.otherDim(plaintext.length, numCols));
		assertTrue("Correct decrytedText", Arrays.equals(decrytedText, plaintext));

		if (ScytaleTest.PRINT) {
			System.out.println("test05");
			System.out.println("\"" + CryptoTools.toString(plaintext) + "\" encrypted is \""
					+ CryptoTools.toString(ciphertext) + "\"");
			System.out.println("\"" + CryptoTools.toString(ciphertext) + "\" decrypted is \""
					+ CryptoTools.toString(decrytedText) + "\"");
			System.out.println();
		}
	}

	/**
	 * Testing Scytale.place(byte[], int).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test06() {
		final int numCols = 3;
		final byte[] plaintext = "THEKEYOFTHISCODESHIFTISTHREEZZ".getBytes();
		final byte[] ciphertext = Scytale.place(plaintext, numCols); // TKOHCEIIHEHEFIOSFSRZEYTSDHTTEZ
		assertTrue("Correct ciphertext", Arrays.equals(ciphertext, "TKOHCEIIHEHEFIOSFSRZEYTSDHTTEZ".getBytes()));
		final byte[] decrytedText = Scytale.place(ciphertext, MatrixInt.otherDim(plaintext.length, numCols));
		assertTrue("Correct decrytedText", Arrays.equals(decrytedText, plaintext));

		if (ScytaleTest.PRINT) {
			System.out.println("test06");
			System.out.println("\"" + CryptoTools.toString(plaintext) + "\" encrypted is \""
					+ CryptoTools.toString(ciphertext) + "\"");
			System.out.println("\"" + CryptoTools.toString(ciphertext) + "\" decrypted is \""
					+ CryptoTools.toString(decrytedText) + "\"");
			System.out.println();
		}
	}

	/**
	 * Testing the effects of order on Scytale.place(char[], int).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test07() {
		final int numCols = 2;
		final char[][] plaintexts = { "ABLE".toCharArray(), "JOKE".toCharArray(), "JAZZ".toCharArray(),
				"QUIZ".toCharArray(), "JUMP".toCharArray(), "MAZE".toCharArray(), "GAZE".toCharArray(),
				"JAVA".toCharArray(), "CAMP".toCharArray(), "BIND".toCharArray(), "HELP".toCharArray() };
		final int numTexts = plaintexts.length;
		assertTrue("numTexts lower bound", numTexts >= 10);
		final char[][] expectedCiphertexts = { "ALBE".toCharArray(), "JKOE".toCharArray(), "JZAZ".toCharArray(),
				"QIUZ".toCharArray(), "JMUP".toCharArray(), "MZAE".toCharArray(), "GZAE".toCharArray(),
				"JVAA".toCharArray(), "CMAP".toCharArray(), "BNID".toCharArray(), "HLEP".toCharArray() };
		assertTrue("Correct numTexts", numTexts == expectedCiphertexts.length);
		for (int i = 0; i != numTexts; ++i) {
			assertTrue("Length at " + i, plaintexts[i].length == expectedCiphertexts[i].length);
		}

		final int[] indices = new int[numTexts];
		for (int i = 0; i != numTexts; ++i) {
			indices[i] = i;
		}
		ArrayUtil.shuffle(indices);

		if (ScytaleTest.PRINT) {
			System.out.println("test07");
		}

		final char[][] ciphertexts = new char[numTexts][];
		for (final int index : indices) {
			ciphertexts[index] = Scytale.place(plaintexts[index], numCols);
			if (ScytaleTest.PRINT) {
				System.out.println("ciphertexts[" + index + "] == \"" + CryptoTools.toString(ciphertexts[index])
						+ "\", expectedCiphertexts[" + index + "] == \""
						+ CryptoTools.toString(expectedCiphertexts[index]) + "\"");
			}
			assertTrue("Correct ciphertexts[" + index + "]",
					Arrays.equals(ciphertexts[index], expectedCiphertexts[index]));
		}
		assertTrue("Correct ciphertexts", ArrayUtil.equals(ciphertexts, expectedCiphertexts));

		if (ScytaleTest.PRINT) {
			System.out.println();
		}
	}

	/**
	 * Testing the effects of order on Scytale.place(byte[], int).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test08() {
		final int numCols = 2;
		final byte[][] plaintexts = { "ABLE".getBytes(), "JOKE".getBytes(), "JAZZ".getBytes(), "QUIZ".getBytes(),
				"JUMP".getBytes(), "MAZE".getBytes(), "GAZE".getBytes(), "JAVA".getBytes(), "CAMP".getBytes(),
				"BIND".getBytes(), "HELP".getBytes() };
		final int numTexts = plaintexts.length;
		assertTrue("numTexts lower bound", numTexts >= 10);
		final byte[][] expectedCiphertexts = { "ALBE".getBytes(), "JKOE".getBytes(), "JZAZ".getBytes(),
				"QIUZ".getBytes(), "JMUP".getBytes(), "MZAE".getBytes(), "GZAE".getBytes(), "JVAA".getBytes(),
				"CMAP".getBytes(), "BNID".getBytes(), "HLEP".getBytes() };
		assertTrue("Correct numTexts", numTexts == expectedCiphertexts.length);
		for (int i = 0; i != numTexts; ++i) {
			assertTrue("Length at " + i, plaintexts[i].length == expectedCiphertexts[i].length);
		}

		final int[] indices = new int[numTexts];
		for (int i = 0; i != numTexts; ++i) {
			indices[i] = i;
		}
		ArrayUtil.shuffle(indices);

		if (ScytaleTest.PRINT) {
			System.out.println("test08");
		}

		final byte[][] ciphertexts = new byte[numTexts][];
		for (final int index : indices) {
			ciphertexts[index] = Scytale.place(plaintexts[index], numCols);
			if (ScytaleTest.PRINT) {
				System.out.println("ciphertexts[" + index + "] == \"" + CryptoTools.toString(ciphertexts[index])
						+ "\", expectedCiphertexts[" + index + "] == \""
						+ CryptoTools.toString(expectedCiphertexts[index]) + "\"");
			}
			assertTrue("Correct ciphertexts[" + index + "]",
					Arrays.equals(ciphertexts[index], expectedCiphertexts[index]));
		}
		assertTrue("Correct ciphertexts", ArrayUtil.equals(ciphertexts, expectedCiphertexts));

		if (ScytaleTest.PRINT) {
			System.out.println();
		}
	}
}
