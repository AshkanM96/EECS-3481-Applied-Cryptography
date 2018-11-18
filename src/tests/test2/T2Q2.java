package tests.test2;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import util.Binary;
import util.CipherEngUtil;
import util.CryptoTools;
import util.Hex;
import util.SymCipherEng;

public class T2Q2 {
	/**
	 * Dependencies: <code>
	 * 		1. util.Binary
	 * 		2. util.Hex
	 * 		3. util.CipherEngUtil
	 * 		4. util.SymCipherEng
	 * 		5. util.CryptoTools
	 * </code>
	 */

	public static void main(String[] args) throws InvalidKeyException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException {
		final byte[] key = Hex.toBytes("4F75725269676874");
		final byte[] iv = Hex.toBytes("496E566563746F72");
		final SymCipherEng engine = new SymCipherEng(SymCipherEng.ALGO_SYM.DES, key, CipherEngUtil.MODE.ECB,
				CipherEngUtil.PADDING.NoPadding);

		final byte[] ciphertext = Hex.toBytes("A557E4C89356F55AD012625648BE3F22D5E777DAC5172E09");
		final byte[] plaintext = new byte[ciphertext.length];

		// SAE Mode of Operation:
		/*
		 * The first block of the ciphertext was xored with the the IV which is why we initialize the
		 * previous ciphertext block to the IV.
		 */
		final byte[] ciphertextBlock = new byte[SymCipherEng.DES_BLOCK_SIZE];
		byte[] plaintextBlock = null;
		final byte[] prev_ciphertextBlock = new byte[SymCipherEng.DES_BLOCK_SIZE];
		System.arraycopy(iv, 0, prev_ciphertextBlock, 0, SymCipherEng.DES_BLOCK_SIZE);
		for (int plaintextPos = 0; plaintextPos != plaintext.length; plaintextPos += SymCipherEng.DES_BLOCK_SIZE) {
			// Take the next subset of the ciphertext of length DES block size (i.e., 8 bytes).
			System.arraycopy(ciphertext, plaintextPos, ciphertextBlock, 0, SymCipherEng.DES_BLOCK_SIZE);
			/*
			 * Xor the current ciphertext block with the previous ciphertext block and then decrypt it to get
			 * the current plaintext block.
			 */
			plaintextBlock = engine.decrypt(Binary.xor(prev_ciphertextBlock, ciphertextBlock));
			// Copy the current plaintext block into the complete plaintext array.
			System.arraycopy(plaintextBlock, 0, plaintext, plaintextPos, SymCipherEng.DES_BLOCK_SIZE);
			// Update the pointer to the previous ciphertext block.
			System.arraycopy(ciphertextBlock, 0, prev_ciphertextBlock, 0, SymCipherEng.DES_BLOCK_SIZE);
		}

		System.out.println(CryptoTools.toString(plaintext));
	}
}
