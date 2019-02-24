package asymmetric;

import java.math.BigInteger;

import util.CryptoTools;

/**
 * ElGamal decryption use case.
 * 
 * @author Ashkan Moatamed
 */
public class D3Q2 {
	/**
	 * Dependencies: <code>
	 * 		1. util.CryptoTools
	 * </code>
	 */

	/**
	 * Known public prime.
	 */
	private static final BigInteger P = BigInteger.valueOf(17L);

	/**
	 * Known public base.
	 */
	private static final BigInteger G = BigInteger.valueOf(3L);

	/**
	 * Known Bob's private key.
	 */
	private static final BigInteger b = BigInteger.valueOf(6L);

	/**
	 * Known Bob's public key.
	 */
	private static final BigInteger B = BigInteger.valueOf(15L);

	/**
	 * Known ciphertext pair first element.
	 */
	private static final BigInteger R = BigInteger.valueOf(7L);

	/**
	 * Known ciphertext pair second element.
	 */
	private static final BigInteger T = BigInteger.valueOf(6L);

	/**
	 * Prevent instantiation.
	 */
	private D3Q2() {
		// Empty by design.
	}

	public static void main(String[] args) {
		assert (D3Q2.B.equals(D3Q2.G.modPow(D3Q2.b, D3Q2.P)));

		// m = t * r ^ (-b) (mod p)
		// m = t * (1 / r (mod p)) ^ b (mod p)
		final BigInteger rModInverse = D3Q2.R.modInverse(D3Q2.P);
		final BigInteger m = D3Q2.T.multiply(rModInverse.modPow(D3Q2.b, D3Q2.P)).mod(D3Q2.P);
		CryptoTools.printlnASCII(new String(m.toByteArray()), System.out);
		System.out.println(m.toString() + "\n");
	}
}
