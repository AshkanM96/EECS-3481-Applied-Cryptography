package asymmetric;

import java.math.BigInteger;

import util.RSA;

/**
 * RSA correctness use case with small p, q, and e.
 * 
 * @author Ashkan Moatamed
 */
public class C2Q1 {
	/**
	 * Dependencies: <code>
	 * 		1. util.RSA
	 * </code>
	 */

	/**
	 * Known prime factor.
	 */
	private static final BigInteger P = BigInteger.valueOf(5);

	/**
	 * Known prime factor.
	 */
	private static final BigInteger Q = BigInteger.valueOf(11);

	/**
	 * Known public key.
	 */
	private static final BigInteger E = BigInteger.valueOf(3);

	/**
	 * Prevent instantiation.
	 */
	private C2Q1() {
		// Empty by design.
	}

	public static void main(String[] args) {
		// Create the cipher engine with the appropriate attributes.
		final RSA r = RSA.knownFactors(C2Q1.P, C2Q1.Q, C2Q1.E);

		// Keep track of whether all tests passed.
		boolean allPass = true;

		// Loop through all numbers in [1, n) coprime with n.
		final BigInteger n = r.n;
		for (BigInteger m = BigInteger.ONE, c = null, mPrime; !m.equals(n); m = m.add(BigInteger.ONE)) {
			if (m.gcd(n).equals(BigInteger.ONE)) {
				// Compute the ciphertext by encrypting with public key.
				c = r.apply(m, true);
				// Compute the plaintext by decrypting with private key.
				mPrime = r.apply(c, false);

				// Check to see if the decryption negated the encryption or not.
				if (!mPrime.equals(m)) {
					allPass = false;
					System.out.println("FAILED:\nm == " + m + "\nmPrime == " + mPrime + "\n");
				}
			}
		}

		// Print all pass message if all tests passed.
		if (allPass) {
			System.out.println("All tests passed.\n");
		}
	}
}
