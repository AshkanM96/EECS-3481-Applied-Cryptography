package asymmetric;

import java.math.BigInteger;

import util.CryptoTools;
import util.RSA;

/**
 * RSA decryption use case with manual BigInteger manipulations using the Chinese Remainder Theorem.
 * 
 * @author Ashkan Moatamed
 */
public class RSA_CRT_Decrypt {
	/**
	 * Dependencies: <code>
	 * 		1. util.RSA
	 * 		2. util.CryptoTools
	 * </code>
	 */

	/**
	 * Known modulus prime factor.
	 */
	private static final BigInteger P = new BigInteger("37975227936943673922808872755445627854565536638199");

	/**
	 * Known modulus prime factor.
	 */
	private static final BigInteger Q = new BigInteger("40094690950920881030683735292761468389214899724061");

	/**
	 * Known public key.
	 */
	private static final BigInteger E = new BigInteger("74327");

	/**
	 * Known plaintext.
	 */
	private static final BigInteger PLAINTEXT = new BigInteger("Testing CRT for RSA".getBytes());

	/**
	 * Prevent instantiation.
	 */
	private RSA_CRT_Decrypt() {
		// Empty by design.
	}

	@Override
	protected Object clone() throws CloneNotSupportedException { // semi-copy
		throw new CloneNotSupportedException();
	}

	public static void main(String[] args) {
		// Create the cipher engine with the appropriate attributes.
		final RSA r = RSA.knownFactors(RSA_CRT_Decrypt.P, RSA_CRT_Decrypt.Q, RSA_CRT_Decrypt.E);

		// Encrypt the plaintext using the engine to get the ciphertext.
		final BigInteger ciphertext = r.apply(RSA_CRT_Decrypt.PLAINTEXT, true);

		// Decrypt the ciphertext using the engine to get the plaintext.
		final BigInteger plaintext = r.apply(ciphertext, false);
		System.out.println("The plaintext is:\n" + CryptoTools.toString(plaintext.toByteArray()) + "\n");
	}
}
