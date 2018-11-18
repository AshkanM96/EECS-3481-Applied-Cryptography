package tests.test2;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import util.CipherEngUtil;
import util.CryptoTools;
import util.Hex;
import util.SymCipherEng;

public class T2Q1 {
	/**
	 * Dependencies: <code>
	 * 		1. util.Hex
	 * 		2. util.CipherEngUtil
	 * 		3. util.SymCipherEng
	 * 		4. util.CryptoTools
	 * </code>
	 */

	public static void main(String[] args) throws InvalidKeyException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException {
		final byte[] key = Hex.toBytes("444F204E4F542054454C4C2045564521");
		final byte[] iv = Hex.toBytes("20FC19123087BF6CAC8D0F1254123004");
		final SymCipherEng engine = new SymCipherEng(SymCipherEng.ALGO_SYM.AES, key, CipherEngUtil.MODE.CBC,
				CipherEngUtil.PADDING.PKCS5Padding, iv);

		final byte[] ciphertext = Hex.toBytes("184598AB83BF20D75D1F5DF4E11C23B356CCF4C1382FC836805D819F2AD4D174");
		final byte[] plaintext = engine.decrypt(ciphertext);
		System.out.println(CryptoTools.toString(plaintext));
	}
}
