package foundation;

import java.io.FileNotFoundException;
import java.io.IOException;

import util.Caesar;
import util.CryptoTools;
import util.Digester;
import util.Hex;

/**
 * Caesar encryption use case.
 * 
 * @author Ashkan Moatamed
 */
public class C_Encrypt {
	/**
	 * Dependencies: <code>
	 * 		1. foundation.FoundationUtil
	 * 		2. util.Caesar
	 * 		3. util.CryptoTools
	 * 		4. util.Digester
	 * 		5. util.Hex
	 * </code>
	 */

	/**
	 * File name.
	 */
	private static final String FILE_NAME = "MSG1";

	/**
	 * Cleaned plaintext name.
	 */
	private static final String CLEANED_PLAINTEXT_NAME = C_Encrypt.FILE_NAME + "_clean";

	/**
	 * Input plaintext location.
	 */
	private static final String PLAINTEXT_LOCATION = FoundationUtil.inPlainPath(C_Encrypt.FILE_NAME);

	/**
	 * Output cleaned plaintext location.
	 */
	private static final String CLEANED_TEXT_LOCATION = FoundationUtil.outPlainPath(C_Encrypt.CLEANED_PLAINTEXT_NAME);

	/**
	 * Output ciphertext location.
	 */
	private static final String CIPHERTEXT_LOCATION = FoundationUtil.outCipherPath(C_Encrypt.FILE_NAME);

	/**
	 * Chosen caesar cipher key.
	 */
	private static final int CAESAR_KEY = 19;

	/**
	 * Expected ciphertext MD5 hash as hex string.
	 */
	private static final String EXPECTED_CIPHERTEXT_MD5_HASH_HEX = "2C422B741EF90FD4424EBC83692398B0";

	/**
	 * Prevent instantiation.
	 */
	private C_Encrypt() {
		// Empty by design.
	}

	@Override
	protected Object clone() throws CloneNotSupportedException { // semi-copy
		throw new CloneNotSupportedException();
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		/*
		 * Read the plaintext from the file and clean it up so that it only contains uppercase English
		 * letters.
		 */
		final byte[] originalPlaintext = CryptoTools.fileToBytes(C_Encrypt.PLAINTEXT_LOCATION);
		System.out.println("Read original plaintext from " + C_Encrypt.PLAINTEXT_LOCATION + ".\n");
		final byte[] cleanedPlaintext = CryptoTools.clean(originalPlaintext);
		System.out.println("Cleaned original plaintext.\n");
		CryptoTools.bytesToFile(cleanedPlaintext, C_Encrypt.CLEANED_TEXT_LOCATION);
		System.out.println("Wrote cleaned plaintext to " + C_Encrypt.CLEANED_TEXT_LOCATION + ".\n");

		// Encrypt the cleaned plaintext using a caesar cipher with the chosen key and write it to a file.
		final byte[] encrytedText = Caesar.encrypt(C_Encrypt.CAESAR_KEY, cleanedPlaintext);
		System.out.println("Encrypted cleaned plaintext with Caesar Cipher with key " + C_Encrypt.CAESAR_KEY + ".\n");
		CryptoTools.bytesToFile(encrytedText, C_Encrypt.CIPHERTEXT_LOCATION);
		System.out.println("Wrote ciphertext to " + C_Encrypt.CIPHERTEXT_LOCATION + ".\n");

		// Check correctness of the encrypted text by comparing
		// it against the expected MD5 hash as a hex string.
		final String encrytedTextMD5HashHex = Hex.toString(Digester.digest(Digester.ALGO_HASH.MD5, encrytedText));
		System.out.println("Ciphertext MD5 Hash as a hex string is: " + encrytedTextMD5HashHex + ".\n");
		System.out.println("Ciphertext MD5 Hash is "
				+ (C_Encrypt.EXPECTED_CIPHERTEXT_MD5_HASH_HEX.equals(encrytedTextMD5HashHex) ? "equal" : "not equal")
				+ " to expected.\n");

		// Compute the cleaned plaintext index of coincidence.
		final double IC = CryptoTools.getIC(cleanedPlaintext);
		System.out.println("Index of Coincidence of cleaned plaintext is " + IC + ".\n");
	}
}
