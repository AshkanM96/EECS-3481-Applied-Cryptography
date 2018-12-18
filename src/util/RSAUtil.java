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

/**
 * Utility RSA methods.
 * 
 * @author Ashkan Moatamed
 */
public class RSAUtil {
	/**
	 * Dependencies: <code>
	 * 		1. util.BigIntegerUtil
	 * </code>
	 */

	/**
	 * Cipher algorithm.
	 */
	public static final String ALGORITHM = "RSA";

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
			// n is the same and so no need to set it again.
			d = rsaPrivateKey.getPrivateExponent();
		} else {
			throw new IllegalArgumentException();
		}

		// Create Result and return it.
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
	 *             Thrown by <code>BigIntegerUtil.ensurePositive(p, q, phi)</code>
	 */
	public static BigInteger phi(BigInteger p, BigInteger q) throws NullPointerException, IllegalArgumentException {
		// Ensure all parameters are positive.
		BigIntegerUtil.ensurePositive(p, q);

		// Save p - 1 and ensure that it is positive.
		final BigInteger p_minus_1 = p.subtract(BigInteger.ONE);
		BigIntegerUtil.ensurePositive(p_minus_1);
		// Save q - 1 and ensure that it is positive.
		final BigInteger q_minus_1 = q.subtract(BigInteger.ONE);
		BigIntegerUtil.ensurePositive(q_minus_1);

		// phi = (p - 1) * (q - 1)
		return p_minus_1.multiply(q_minus_1);
	}

	/**
	 * @param phi
	 *            the given Euler's totient function for the cipher modulus
	 * 
	 * @param e
	 *            the given cipher public key
	 * 
	 * @return The cipher private key.
	 * 
	 * @throws NullPointerException
	 *             If <code>(phi == null) || (e == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             Thrown by <code>BigIntegerUtil.ensurePositive(phi, e)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>gcd(e, phi) != 1</code>
	 */
	public static BigInteger key(BigInteger phi, BigInteger e)
			throws NullPointerException, IllegalArgumentException, ArithmeticException {
		// Ensure all parameters are positive.
		BigIntegerUtil.ensurePositive(phi, e);

		// Return the private key.
		return e.modInverse(phi);
	}

	/**
	 * @param primeFactor
	 *            one of the two prime factors of the cipher modulus
	 * 
	 * @param n
	 *            the given cipher modulus
	 * 
	 * @param e
	 *            the given cipher public key
	 * 
	 * @return The cipher private key.
	 * 
	 * @throws NullPointerException
	 *             If <code>(primeFactor == null) || (n == null) || (e == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             Thrown by <code>BigIntegerUtil.ensurePositive(primeFactor, n, e, phi)</code> or
	 *             <code>BigIntegerUtil.ensureZero(n.divideAndRemainder(primeFactor)[1])</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>gcd(e, phi) != 1</code>
	 */
	public static BigInteger key(BigInteger primeFactor, BigInteger n, BigInteger e)
			throws NullPointerException, IllegalArgumentException, ArithmeticException {
		// Ensure all parameters are positive.
		BigIntegerUtil.ensurePositive(primeFactor, n, e);

		// Compute the other prime factor of n through division.
		final BigInteger[] result = n.divideAndRemainder(primeFactor);
		final BigInteger otherPrimeFactor = result[0];
		// result[1] == (n % primeFactor) != 0 means that
		// primeFactor is not actually a factor of n.
		BigIntegerUtil.ensureZero(result[1]);

		// Save primeFactor - 1 and ensure that it is positive.
		final BigInteger primeFactor_minus_1 = primeFactor.subtract(BigInteger.ONE);
		BigIntegerUtil.ensurePositive(primeFactor_minus_1);
		// Save otherPrimeFactor - 1 and ensure that it is positive.
		final BigInteger otherPrimeFactor_minus_1 = otherPrimeFactor.subtract(BigInteger.ONE);
		BigIntegerUtil.ensurePositive(otherPrimeFactor_minus_1);

		// Compute the Euler's totient function for n.
		final BigInteger phi = primeFactor_minus_1.multiply(otherPrimeFactor_minus_1);

		// Return the private key.
		return e.modInverse(phi);
	}
}
