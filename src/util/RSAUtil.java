package util;

import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility RSA methods.
 * 
 * @author Ashkan Moatamed
 */
public class RSAUtil {
	/**
	 * Dependencies: <code>
	 * 		1. util.BigIntUtil
	 * </code>
	 */

	/**
	 * Cipher algorithm.
	 */
	public static final String ALGORITHM = "RSA";

	/**
	 * Default value for the <code>max_num_iters</code> argument of the <code>primeFactors</code>
	 * method.
	 */
	public static final int DEFAULT_MAX_NUM_ITERS = 1000;

	/**
	 * @return <code>KeyPairGenerator.getInstance(RSAUtil.ALGORITHM)</code>.
	 */
	public static KeyPairGenerator getKeyPairGenerator() {
		try {
			return KeyPairGenerator.getInstance(RSAUtil.ALGORITHM);
		} catch (NoSuchAlgorithmException ex) {
			throw new ExceptionInInitializerError(RSAUtil.ALGORITHM + " key generator is not provided!");
		}
	}

	/**
	 * Cipher key generator.
	 */
	protected static KeyPairGenerator keyGenerator = null;

	/**
	 * Prevent instantiation.
	 */
	private RSAUtil() {
		// Empty by design.
	}

	/**
	 * Initialize <code>RSAUtil.keyGenerator</code> using the following:
	 * 
	 * <pre>
	 * <code>
	 * if (RSAUtil.keyGenerator == null) {
	 * 	RSAUtil.keyGenerator = RSAUtil.getKeyPairGenerator();
	 * }
	 * 
	 * if (random == null) {
	 * 	RSAUtil.keyGenerator.initialize(keySize);
	 * } else {
	 * 	RSAUtil.keyGenerator.initialize(keySize, random);
	 * }
	 * </code>
	 * </pre>
	 * 
	 * @param keySize
	 *            algorithm-specific metric, such as modulus length, specified in number of bits
	 * 
	 * @param random
	 *            the source of randomness
	 * 
	 * @return <code>RSAUtil.keyGenerator.generateKeyPair()</code>.
	 * 
	 * @throws InvalidParameterException
	 *             Thrown by <code>KeyPairGenerator.generateKeyPair()</code>
	 */
	public static KeyPair generateKeyPair(int keySize, SecureRandom random) throws InvalidParameterException {
		// Construct RSAUtil.keyGenerator if needed. Executed at most once.
		if (RSAUtil.keyGenerator == null) {
			RSAUtil.keyGenerator = RSAUtil.getKeyPairGenerator();
		}

		// Initialize the key generator and return the generated key pair.
		if (random == null) {
			RSAUtil.keyGenerator.initialize(keySize);
		} else {
			RSAUtil.keyGenerator.initialize(keySize, random);
		}
		return RSAUtil.keyGenerator.generateKeyPair();
	}

	/**
	 * @param keySize
	 *            algorithm-specific metric, such as modulus length, specified in number of bits
	 * 
	 * @return <code>RSAUtil.generateKeyPair(keySize, null)</code>.
	 * 
	 * @throws InvalidParameterException
	 *             Thrown by <code>KeyPairGenerator.generateKeyPair()</code>
	 */
	public static KeyPair generateKeyPair(int keySize) throws InvalidParameterException {
		return RSAUtil.generateKeyPair(keySize, null);
	}

	/**
	 * Extract the RSA keys from the given KeyPair object. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.length == 3</code> <br>
	 * Postcondition: <code>Result[0] == n</code> <br>
	 * Postcondition: <code>Result[1] == e</code> <br>
	 * Postcondition: <code>Result[2] == d</code>
	 * 
	 * @param pair
	 *            the given KeyPair object
	 * 
	 * @return The resulting BigInteger array.
	 * 
	 * @throws NullPointerException
	 *             If <code>pair == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(!(pair.getPublic() instanceof RSAPublicKey)) || (!(pair.getPrivate() instanceof RSAPrivateKey))</code>
	 */
	public static BigInteger[] getKeys(KeyPair pair) throws NullPointerException, IllegalArgumentException {
		// Get the PublicKey and PrivateKey attributes from pair.
		final PublicKey publicKey = pair.getPublic();
		final PrivateKey privateKey = pair.getPrivate();

		// The RSA keys as BigInteger objects.
		BigInteger n = null, e = null, d = null;

		// Check to see if publicKey is an RSAPublicKey.
		if (publicKey instanceof RSAPublicKey) {
			final RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
			n = rsaPublicKey.getModulus();
			e = rsaPublicKey.getPublicExponent();
		} else {
			throw new IllegalArgumentException();
		}

		// Check to see if privateKey is an RSAPrivateKey.
		if (privateKey instanceof RSAPrivateKey) {
			final RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
			assert (n.equals(rsaPrivateKey
					.getModulus())) : "!((RSAPublicKey) pair.getPublic()).getModulus().equals(((RSAPrivateKey) pair.getPrivate()).getModulus())";
			d = rsaPrivateKey.getPrivateExponent();
		} else {
			throw new IllegalArgumentException();
		}

		// Create and fill the resulting BigInteger array.
		return new BigInteger[] { n, e, d };
	}

	/**
	 * @param n
	 *            the given cipher modulus
	 * 
	 * @param e
	 *            the given cipher public key
	 * 
	 * @param d
	 *            the given cipher private key
	 * 
	 * @return <code>"(" + n + ", " + e + ", " + d + ")"</code>.
	 */
	public static String toString(BigInteger n, BigInteger e, BigInteger d) {
		final StringBuilder sb = new StringBuilder();
		sb.append('(').append(n).append(", ").append(e).append(", ").append(d).append(')');
		return sb.toString();
	}

	/**
	 * @param p
	 *            one of the two prime factors of the cipher modulus
	 * 
	 * @param q
	 *            one of the two prime factors of the cipher modulus
	 * 
	 * @return <code>(p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE))</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(p == null) || (q == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(p <= 1) || (q <= 1)</code>
	 */
	public static BigInteger phi(BigInteger p, BigInteger q) throws NullPointerException, IllegalArgumentException {
		if ((p.signum() != 1) || (q.signum() != 1)) { // i.e., (p <= 0) || (q <= 0)
			throw new IllegalArgumentException();
		}
		// (0 < p) && (0 < q)

		// Save p - 1 and ensure that it is positive.
		final BigInteger p_minus_1 = p.subtract(BigInteger.ONE); // 0 <= p_minus_1
		if (p_minus_1.signum() != 1) { // i.e., p - 1 <= 0
			throw new IllegalArgumentException();
		}
		// 0 < p - 1
		// i.e., 1 < p
		// Save q - 1 and ensure that it is positive.
		final BigInteger q_minus_1 = q.subtract(BigInteger.ONE); // 0 <= q_minus_1
		if (q_minus_1.signum() != 1) { // i.e., q - 1 <= 0
			throw new IllegalArgumentException();
		}
		// 0 < q - 1
		// i.e., 1 < q

		// phi = (p - 1) * (q - 1)
		return p_minus_1.multiply(q_minus_1);
	}

	/**
	 * @param phi
	 *            the given value of Euler's totient function for the cipher modulus
	 * 
	 * @param k
	 *            the given known cipher key (i.e., either e or d)
	 * 
	 * @return The unknown cipher key.
	 * 
	 * @throws NullPointerException
	 *             If <code>(phi == null) || (k == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(phi <= 0) || (k <= 0) || (phi <= k)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>gcd(k, phi) != 1</code>
	 */
	public static BigInteger key(BigInteger phi, BigInteger k)
			throws NullPointerException, IllegalArgumentException, ArithmeticException {
		if ((phi.signum() != 1) || (k.signum() != 1)) { // i.e., (phi <= 0) || (k <= 0)
			throw new IllegalArgumentException();
		} else if (phi.compareTo(k) <= 0) { // i.e., phi <= k
			throw new IllegalArgumentException();
		}
		// (0 < phi) && (0 < k) && (k < phi)
		// i.e., (0 < k) && (k < phi)

		// Return the unknown key which is the modular inverse of the known key.
		return k.modInverse(phi);
	}

	/**
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.length == 3</code> <br>
	 * Postcondition: <code>Result[0] == <sup>n</sup>&frasl;<sub>p</sub></code> <br>
	 * Postcondition:
	 * <code>Result[1] == phi(n) == (p - 1) * ((<sup>n</sup>&frasl;<sub>p</sub>) - 1)</code> <br>
	 * Postcondition: <code>Result[2] == k<sup>-1</sup> (mod phi(n))</code>
	 * 
	 * @param p
	 *            one of the two prime factors of the cipher modulus
	 * 
	 * @param n
	 *            the given cipher modulus
	 * 
	 * @param k
	 *            the given known cipher key (i.e., either e or d)
	 * 
	 * @return The resulting BigInteger array.
	 * 
	 * @throws NullPointerException
	 *             If <code>(p == null) || (n == null) || (k == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(p <= 1) || (n <= 0) || (k <= 0) || (n <= k)
	 *             || ((n % p) != 0) || ((n / p) <= 1) || (phi <= k)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>gcd(k, phi) != 1</code>
	 */
	public static BigInteger[] key(BigInteger p, BigInteger n, BigInteger k)
			throws NullPointerException, IllegalArgumentException, ArithmeticException {
		if ((p.signum() != 1) || (n.signum() != 1) || (k.signum() != 1)) { // i.e., (p <= 0) || (n <= 0) || (k <= 0)
			throw new IllegalArgumentException();
		} else if (n.compareTo(k) <= 0) { // i.e., n <= k
			throw new IllegalArgumentException();
		}
		// (0 < p) && (0 < n) && (0 < k) && (k < n)
		// i.e., (0 < p) && (0 < k) && (k < n)

		// Compute the other prime factor of n through division.
		final BigInteger[] result = n.divideAndRemainder(p);
		final BigInteger q = result[0];
		if (result[1].signum() != 0) {
			// result[1] == (n % p) != 0, which means that p is not actually a factor of n.
			throw new IllegalArgumentException();
		}

		// Save p - 1 and ensure that it is positive.
		final BigInteger p_minus_1 = p.subtract(BigInteger.ONE); // 0 <= p_minus_1
		if (p_minus_1.signum() != 1) { // i.e., p - 1 <= 0
			throw new IllegalArgumentException();
		}
		// 0 < p - 1
		// i.e., 1 < p
		// Save q - 1 and ensure that it is positive.
		final BigInteger q_minus_1 = q.subtract(BigInteger.ONE); // 0 <= q_minus_1
		if (q_minus_1.signum() != 1) { // i.e., q - 1 <= 0
			throw new IllegalArgumentException();
		}
		// 0 < q - 1
		// i.e., 1 < q
		// Compute the value of Euler's totient function for the cipher modulus.
		final BigInteger phi = p_minus_1.multiply(q_minus_1);
		if (phi.compareTo(k) <= 0) { // i.e., phi <= k
			throw new IllegalArgumentException();
		}
		// k < phi

		// Compute the unknown key which is the modular inverse of the known key.
		final BigInteger kPrime = k.modInverse(phi);
		return new BigInteger[] { q, phi, kPrime };
	}

	/**
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.length == 2</code> <br>
	 * Postcondition: <code>Result[0]</code> is the smaller prime factor of <code>n</code> <br>
	 * Postcondition: <code>Result[1]</code> is the larger prime factor of <code>n</code>
	 * 
	 * @param n
	 *            the given cipher modulus
	 * 
	 * @param phi
	 *            the given value of Euler's totient function for the cipher modulus
	 * 
	 * @return The resulting BigInteger array.
	 * 
	 * @throws NullPointerException
	 *             If <code>(n == null) || (phi == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(n <= 0) || (phi <= 0) || (n <= phi)</code>
	 */
	public static BigInteger[] primeFactors(BigInteger n, BigInteger phi)
			throws NullPointerException, IllegalArgumentException {
		if ((n.signum() != 1) || (phi.signum() != 1)) { // i.e., (n <= 0) || (phi <= 0)
			throw new IllegalArgumentException();
		} else if (n.compareTo(phi) <= 0) { // i.e., n <= phi
			throw new IllegalArgumentException();
		}
		// (0 < n) && (0 < phi) && (phi < n)
		// i.e., (0 < phi) && (phi < n)

		/**
		 * We can easily see that <code>q</code> and <code>p</code> are roots of the quadratic equation
		 * <code>(x - q)(x - p) = 0</code>. However, we can rewrite the equation in terms of <code>n</code>
		 * and <code>phi</code> by a little bit of simplification as follows: <br>
		 * <code>(x - q)(x - p) = x * x - (q + p) * x + q * p = x * x + (phi - n - 1) * x + n = 0</code>
		 * <br>
		 * <br>
		 * 
		 * Thus, <code>a == 1</code>, <code>b == phi - n - 1</code>, and <code>c == n</code>, and so we can
		 * conclude that <code>q == (-b - d) / 2 == -((d + b) / 2)</code> and that
		 * <code>p == (-b + d) / 2 == (d - b) / 2</code> where <code>d == sqrt(b * b - 4 * n)</code>.
		 * However, we can simplify the formulas (and optimize the calculation) when <code>b</code> is even
		 * by absorbing the <code>1 / 2</code> into the sqrt to obtain the following: <br>
		 * <code>q == -b' - d' == -(d' + b')</code> and <code>p == -b' + d' == d' - b'</code> where
		 * <code>b' == b / 2</code> and <code>d' == d / 2 == sqrt((b / 2) * (b / 2) - n)</code>.
		 */
		final BigInteger b = phi.subtract(n).subtract(BigInteger.ONE); // b < 0
		if (!b.testBit(0)) { // i.e., BigIntUtil.isEven(b)
			final BigInteger bPrime = b.shiftRight(1); // bPrime < 0
			final BigInteger dPrime = BigIntUtil.sqrtFloor(bPrime.multiply(bPrime).subtract(n));
			final BigInteger q = dPrime.add(bPrime).negate();
			final BigInteger p = dPrime.subtract(bPrime);
			// q == (|b'| - d') < p == (|b'| + d')
			return new BigInteger[] { q, p };
		}
		// !BigIntUtil.isEven(b)
		final BigInteger d = BigIntUtil.sqrtFloor(b.multiply(b).subtract(n.shiftLeft(2)));
		final BigInteger q = d.add(b).shiftRight(1).negate();
		final BigInteger p = d.subtract(b).shiftRight(1);
		// q == (|b| - d) / 2 < p == (|b| + d) / 2
		return new BigInteger[] { q, p };
	}

	/**
	 * Postcondition: <code>(Result != null) implies (Result.length == 2)</code> <br>
	 * Postcondition: <code>(Result != null) implies Result[0]</code> is the smaller prime factor of
	 * <code>n</code> <br>
	 * Postcondition: <code>(Result != null) implies Result[1]</code> is the larger prime factor of
	 * <code>n</code>
	 * 
	 * @param n
	 *            the given cipher modulus
	 * 
	 * @param e
	 *            the given cipher public key
	 * 
	 * @param d
	 *            the given cipher private key
	 * 
	 * @param max_num_iters
	 *            the maximum number of iterations to run Miller-Rabin's compositeness test
	 * 
	 * @param rnd
	 *            source of random bits used to select candidates for Miller-Rabin's compositeness test
	 * 
	 * @return The resulting BigInteger array.
	 * 
	 * @throws NullPointerException
	 *             If <code>(n == null) || (e == null) || (d == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(n <= 1) || (e <= 0) || (d <= 0) || (n <= e) || (n <= d)
	 *             || (max_num_iters <= 0) || ((e == 1) && (d == 1))</code>
	 */
	public static BigInteger[] primeFactors(BigInteger n, BigInteger e, BigInteger d, int max_num_iters, Random rnd)
			throws NullPointerException, IllegalArgumentException {
		if ((n.signum() != 1) || (e.signum() != 1) || (d.signum() != 1)) { // i.e., (n <= 0) || (e <= 0) || (d <= 0)
			throw new IllegalArgumentException();
		} else if (n.compareTo(e) <= 0) { // i.e., n <= e
			throw new IllegalArgumentException();
		} else if (n.compareTo(d) <= 0) { // i.e., n <= d
			throw new IllegalArgumentException();
		} else if (max_num_iters <= 0) {
			throw new IllegalArgumentException();
		}
		// (0 < n) && (0 < e) && (0 < d) && (e < n) && (d < n) && (0 < max_num_iters)
		// i.e., (0 < e) && (0 < d) && (max(e, d) < n) && (0 < max_num_iters)

		// Fix null rnd.
		if (rnd == null) {
			rnd = ThreadLocalRandom.current();
		}

		// Save n - 1 and ensure that it is positive.
		final BigInteger n_minus_1 = n.subtract(BigInteger.ONE); // 0 <= n_minus_1
		if (n_minus_1.signum() != 1) { // i.e., n - 1 <= 0
			throw new IllegalArgumentException();
		}
		// 0 < n - 1
		// i.e., 1 < n
		// Save e * d - 1 and ensure that it is positive.
		final BigInteger k_times_phi = e.multiply(d).subtract(BigInteger.ONE); // 0 <= k_times_phi
		if (k_times_phi.signum() != 1) { // i.e., e * d - 1 <= 0
			// i.e., e * d <= 1
			// i.e., (e == 1) && (d == 1)
			throw new IllegalArgumentException();
		}
		// 0 < k_times_phi

		// Save the bit length of n - 1 since we need to generate random bases between 2 and n - 2.
		final int n_bitLength = n_minus_1.bitLength();
		// Find the largest power of 2 in k_times_phi and the largest odd factor of k_times_phi.
		final int max_power_of_2 = k_times_phi.getLowestSetBit();
		final BigInteger max_odd_factor = k_times_phi.shiftRight(max_power_of_2);

		// Perform Miller-Rabin's compositeness test at most max_num_iters times.
		BigInteger base = null, gcd = null, r = null, prev_r = null;
		int i = 0;
		do {
			// Generate a base in [2, n - 2] uniformly at random.
			do {
				base = new BigInteger(n_bitLength, rnd);
			} while ((base.compareTo(BigInteger.ONE) <= 0) || (n_minus_1.compareTo(base) <= 0));

			// Check if base and n have a non-trivial common divisor.
			gcd = n.gcd(base);
			if (!gcd.equals(BigInteger.ONE)) { // i.e., gcd(n, base) != 1
				final BigInteger q = gcd, p = n.divide(q);
				if (q.compareTo(p) <= 0) { // i.e., q <= p
					return new BigInteger[] { q, p };
				}
				// p < q
				return new BigInteger[] { p, q };
			}
			// gcd(n, base) == 1

			// Perform Miller-Rabin's compositeness test with the randomly generated base.
			r = base.modPow(max_odd_factor, n);
			/*
			 * Check to see if the very first calculation resulted in a one and thus all of the other remainders
			 * are also one which makes the test inconclusive.
			 */
			if (!r.equals(BigInteger.ONE)) { // i.e., r != 1
				// Loop until i == 0.
				i = max_power_of_2;
				do {
					// Check to see if r is -1 (mod n).
					if (r.equals(n_minus_1)) { // i.e., r == n - 1
						break; // Inconclusive.
					}
					// r != n - 1

					prev_r = r; // Save the previous remainder for the Square-Root test.
					r = r.multiply(r).mod(n); // Square r (mod n).

					// Check to see if r is 1 (mod n).
					if (r.equals(BigInteger.ONE)) { // i.e., r == 1
						// Apply the Square-Root test to prev_r, 1, and n.
						final BigInteger q = n.gcd(prev_r.subtract(BigInteger.ONE)), p = n.divide(q);
						if (q.compareTo(p) <= 0) { // i.e., q <= p
							return new BigInteger[] { q, p };
						}
						// p < q
						return new BigInteger[] { p, q };
					}
					// r != 1
				} while (--i != 0);
			}
			// Inconclusive.
		} while (--max_num_iters != 0);
		/*
		 * At this point, we have exhausted the allowed number of iterations so return null to signify the
		 * failure to find the prime factors of n (i.e., q and p).
		 */
		return null;
	}

	/**
	 * Postcondition: <code>(Result != null) implies (Result.length == 2)</code> <br>
	 * Postcondition: <code>(Result != null) implies Result[0]</code> is the smaller prime factor of
	 * <code>n</code> <br>
	 * Postcondition: <code>(Result != null) implies Result[1]</code> is the larger prime factor of
	 * <code>n</code>
	 * 
	 * @param n
	 *            the given cipher modulus
	 * 
	 * @param e
	 *            the given cipher public key
	 * 
	 * @param d
	 *            the given cipher private key
	 * 
	 * @param max_num_iters
	 *            the maximum number of iterations to run Miller-Rabin's compositeness test
	 * 
	 * @return The resulting BigInteger array.
	 * 
	 * @throws NullPointerException
	 *             If <code>(n == null) || (e == null) || (d == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(n <= 1) || (e <= 0) || (d <= 0) || (n <= e) || (n <= d)
	 *             || (max_num_iters <= 0) || ((e == 1) && (d == 1))</code>
	 */
	public static BigInteger[] primeFactors(BigInteger n, BigInteger e, BigInteger d, int max_num_iters)
			throws NullPointerException, IllegalArgumentException {
		return RSAUtil.primeFactors(n, e, d, max_num_iters, ThreadLocalRandom.current());
	}

	/**
	 * Postcondition: <code>(Result != null) implies (Result.length == 2)</code> <br>
	 * Postcondition: <code>(Result != null) implies Result[0]</code> is the smaller prime factor of
	 * <code>n</code> <br>
	 * Postcondition: <code>(Result != null) implies Result[1]</code> is the larger prime factor of
	 * <code>n</code>
	 * 
	 * @param n
	 *            the given cipher modulus
	 * 
	 * @param e
	 *            the given cipher public key
	 * 
	 * @param d
	 *            the given cipher private key
	 * 
	 * @return The resulting BigInteger array.
	 * 
	 * @throws NullPointerException
	 *             If <code>(n == null) || (e == null) || (d == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(n <= 1) || (e <= 0) || (d <= 0) || (n <= e) || (n <= d)
	 *             || ((e == 1) && (d == 1))</code>
	 */
	public static BigInteger[] primeFactors(BigInteger n, BigInteger e, BigInteger d)
			throws NullPointerException, IllegalArgumentException {
		return RSAUtil.primeFactors(n, e, d, RSAUtil.DEFAULT_MAX_NUM_ITERS);
	}
}
