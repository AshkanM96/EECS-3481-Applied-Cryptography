package symmetric;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import util.Binary;
import util.CipherEngUtil;
import util.CryptoTools;
import util.Hex;
import util.SymCipherEng;

/**
 * DES with custom mode of operation, decryption use case.
 * 
 * @author Ashkan Moatamed
 */
public class B3Q4 {
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
	private static final byte[] KEY = Hex.toBytes("6B79466F724D4F50");

	/**
	 * Known initialization vector.
	 */
	private static final byte[] IV = Hex.toBytes("6976466F724D4F50");

	/**
	 * Known ciphertext.
	 */
	private static final byte[] CIPHERTEXT = Hex.toBytes("437DBAB5607137A5CFC1031114634087");

	/**
	 * Prevent instantiation.
	 */
	private B3Q4() {
		// Empty by design.
	}

	@Override
	protected Object clone() throws CloneNotSupportedException { // semi-copy
		throw new CloneNotSupportedException();
	}

	public static void main(String[] args) throws InvalidKeyException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException {
		// Create the cipher engine with the appropriate attributes.
		final SymCipherEng engine = new SymCipherEng(SymCipherEng.ALGO_SYM.DES, B3Q4.KEY, CipherEngUtil.OPMODE.ECB,
				CipherEngUtil.PADDING.NoPadding);

		// Decrypt the ciphertext using the engine to get the plaintext.
		final byte[] plaintext = new byte[B3Q4.CIPHERTEXT.length];

		// York Mode of Operation:
		/*
		 * The first block of the plaintext was xored with the complement of the IV which is why we
		 * initialize the previous ciphertext block to the IV.
		 */
		final byte[] ciphertextBlock = new byte[SymCipherEng.DES_BLOCK_SIZE];
		byte[] plaintextBlock = null;
		final byte[] prev_ciphertextBlock = new byte[SymCipherEng.DES_BLOCK_SIZE];
		System.arraycopy(B3Q4.IV, 0, prev_ciphertextBlock, 0, SymCipherEng.DES_BLOCK_SIZE);
		for (int plaintextPos = 0; plaintextPos != plaintext.length; plaintextPos += SymCipherEng.DES_BLOCK_SIZE) {
			// Take the next subset of the ciphertext of length DES block size (i.e., 8 bytes).
			System.arraycopy(B3Q4.CIPHERTEXT, plaintextPos, ciphertextBlock, 0, SymCipherEng.DES_BLOCK_SIZE);
			/*
			 * Decrypt the current ciphertext block and then xor it with the previous ciphertext block to get
			 * the current plaintext block. Note that it is safe to use Binary.xorEquals since Binary.comp
			 * returns a new array.
			 */
			plaintextBlock = Binary.xorEquals(Binary.comp(prev_ciphertextBlock), engine.decrypt(ciphertextBlock));
			// Copy the current plaintext block into the complete plaintext array.
			System.arraycopy(plaintextBlock, 0, plaintext, plaintextPos, SymCipherEng.DES_BLOCK_SIZE);
			// Update the pointer to the previous ciphertext block.
			System.arraycopy(ciphertextBlock, 0, prev_ciphertextBlock, 0, SymCipherEng.DES_BLOCK_SIZE);
		}

		// Print the plaintext.
		System.out.println("The plaintext is:\n" + CryptoTools.toString(plaintext) + "\n");
	}
}
