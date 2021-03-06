package util;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * RSA Cipher Engine. A simple wrapper over Java's Cipher class which only supports the RSA Cipher.
 * 
 * @author Ashkan Moatamed
 */
public class RSACipherEng {
	/**
	 * Dependencies: <code>
	 * 		1. util.RSAUtil
	 * 		2. util.CipherEngUtil
	 * </code>
	 */

	/**
	 * @return <code>KeyFactory.getInstance(RSAUtil.ALGORITHM)</code>.
	 */
	public static KeyFactory getKeyFactory() {
		try {
			return KeyFactory.getInstance(RSAUtil.ALGORITHM);
		} catch (NoSuchAlgorithmException ex) {
			throw new ExceptionInInitializerError(RSAUtil.ALGORITHM + " key factory is not provided!");
		}
	}

	/**
	 * Cipher key factory.
	 */
	protected static final KeyFactory KEY_FACTORY = RSACipherEng.getKeyFactory();

	/**
	 * Cipher engine.
	 */
	protected Cipher engine;

	/**
	 * Cipher modulus. <br>
	 * Guaranteed to be non-<code>null</code>.
	 */
	private BigInteger n;

	/**
	 * Cipher public key. <br>
	 * Guaranteed to be non-<code>null</code>.
	 */
	private BigInteger e;

	/**
	 * Cipher private key. <br>
	 * Guaranteed to be non-<code>null</code>.
	 */
	private BigInteger d;

	/**
	 * Cipher public key. <br>
	 * Guaranteed to be non-<code>null</code>.
	 */
	private RSAPublicKey publicKey;

	/**
	 * Cipher private key. <br>
	 * Guaranteed to be non-<code>null</code>.
	 */
	private RSAPrivateKey privateKey;

	/**
	 * Mode of operation.
	 */
	private CipherEngUtil.OPMODE opmode;

	/**
	 * Padding algorithm.
	 */
	private CipherEngUtil.PADDING padding;

	/**
	 * Construct an RSACipherEng object from the given attributes.
	 * 
	 * @param p
	 *            one of the two prime factors of the cipher modulus
	 * 
	 * @param q
	 *            one of the two prime factors of the cipher modulus
	 * 
	 * @param e
	 *            the given cipher public key
	 * 
	 * @param opmode
	 *            the given mode of operation
	 * 
	 * @param padding
	 *            the given padding algorithm
	 * 
	 * @param dummy1
	 *            an (unused) object used to distinguish between this ctor and the n-e-d ctor
	 * 
	 * @param dummy2
	 *            an (unused) object used to distinguish between this ctor and the phi-n-e ctor
	 * 
	 * @throws NullPointerException
	 *             If
	 *             <code>(p == null) || (q == null) || (e == null) || (opmode == null) || (padding == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(p <= 1) || (q <= 1) || (p == q) || (e <= 0) || (phi <= e)</code>
	 * 
	 * @throws NoSuchAlgorithmException
	 *             Thrown by <code>CipherEngUtil.getEngine(RSAUtil.ALGORITHM, opmode, padding)</code>
	 * 
	 * @throws NoSuchPaddingException
	 *             Thrown by <code>CipherEngUtil.getEngine(RSAUtil.ALGORITHM, opmode, padding)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>gcd(e, phi) != 1</code>
	 * 
	 * @throws InvalidKeySpecException
	 *             Thrown by
	 *             <code>RSACipherEng.KEY_FACTORY.generatePublic(new RSAPublicKeySpec(n, e))</code> or
	 *             <code>RSACipherEng.KEY_FACTORY.generatePrivate(new RSAPrivateKeySpec(n, d))</code>
	 */
	protected RSACipherEng(BigInteger p, BigInteger q, BigInteger e, CipherEngUtil.OPMODE opmode,
			CipherEngUtil.PADDING padding, Object dummy1, Object dummy2)
			throws NullPointerException, IllegalArgumentException, NoSuchAlgorithmException, NoSuchPaddingException,
			ArithmeticException, InvalidKeySpecException {
		if (e.signum() != 1) { // i.e., e <= 0
			throw new IllegalArgumentException();
		}
		// 0 < e
		final BigInteger phi = RSAUtil.phi(p, q);
		// (1 < p) && (1 < q) && (p != q)
		if (phi.compareTo(e) <= 0) { // i.e., phi <= e
			throw new IllegalArgumentException();
		}
		// e < phi

		// The following is meant to be an assignment of this.engine, this.opmode, and this.padding.
		this.engine = CipherEngUtil.getEngine(RSAUtil.ALGORITHM, this.opmode = opmode, this.padding = padding);

		// Set n, e, and d.
		this.n = p.multiply(q);
		this.e = e;
		this.d = this.e.modInverse(phi);

		// Set publicKey and privateKey.
		this.publicKey = (RSAPublicKey) RSACipherEng.KEY_FACTORY.generatePublic(new RSAPublicKeySpec(this.n, this.e));
		this.privateKey = (RSAPrivateKey) RSACipherEng.KEY_FACTORY
				.generatePrivate(new RSAPrivateKeySpec(this.n, this.d));
	}

	/**
	 * Construct an RSACipherEng object from the given attributes.
	 * 
	 * @param phi
	 *            the given value of Euler's totient function for the cipher modulus
	 * 
	 * @param n
	 *            the given cipher modulus
	 * 
	 * @param e
	 *            the given cipher public key
	 * 
	 * @param opmode
	 *            the given mode of operation
	 * 
	 * @param padding
	 *            the given padding algorithm
	 * 
	 * @param dummy
	 *            an (unused) object used to distinguish between this ctor and the n-e-d ctor
	 * 
	 * @throws NullPointerException
	 *             If
	 *             <code>(phi == null) || (n == null) || (e == null) || (opmode == null) || (padding == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(phi <= 0) || (n <= 0) || (e <= 0) || (n <= phi) || (phi <= e)</code>
	 * 
	 * @throws NoSuchAlgorithmException
	 *             Thrown by <code>CipherEngUtil.getEngine(RSAUtil.ALGORITHM, opmode, padding)</code>
	 * 
	 * @throws NoSuchPaddingException
	 *             Thrown by <code>CipherEngUtil.getEngine(RSAUtil.ALGORITHM, opmode, padding)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>gcd(e, phi) != 1</code>
	 * 
	 * @throws InvalidKeySpecException
	 *             Thrown by
	 *             <code>RSACipherEng.KEY_FACTORY.generatePublic(new RSAPublicKeySpec(n, e))</code> or
	 *             <code>RSACipherEng.KEY_FACTORY.generatePrivate(new RSAPrivateKeySpec(n, d))</code>
	 */
	protected RSACipherEng(BigInteger phi, BigInteger n, BigInteger e, CipherEngUtil.OPMODE opmode,
			CipherEngUtil.PADDING padding, Object dummy) throws NullPointerException, IllegalArgumentException,
			NoSuchAlgorithmException, NoSuchPaddingException, ArithmeticException, InvalidKeySpecException {
		if ((phi.signum() != 1) || (n.signum() != 1) || (e.signum() != 1)) { // i.e., (phi <= 0) || (n <= 0) || (e <= 0)
			throw new IllegalArgumentException();
		} else if (n.compareTo(phi) <= 0) { // i.e., n <= phi
			throw new IllegalArgumentException();
		} else if (phi.compareTo(e) <= 0) { // i.e., phi <= e
			throw new IllegalArgumentException();
		}
		// (0 < phi) && (0 < n) && (0 < e) && (phi < n) && (e < phi)
		// i.e., (0 < e) && (e < phi) && (phi < n)

		// The following is meant to be an assignment of this.engine, this.opmode, and this.padding.
		this.engine = CipherEngUtil.getEngine(RSAUtil.ALGORITHM, this.opmode = opmode, this.padding = padding);

		// Set n, e, and d.
		this.n = n;
		this.e = e;
		this.d = this.e.modInverse(phi);

		// Set publicKey and privateKey.
		this.publicKey = (RSAPublicKey) RSACipherEng.KEY_FACTORY.generatePublic(new RSAPublicKeySpec(this.n, this.e));
		this.privateKey = (RSAPrivateKey) RSACipherEng.KEY_FACTORY
				.generatePrivate(new RSAPrivateKeySpec(this.n, this.d));
	}

	/**
	 * Construct an RSACipherEng object from the given attributes.
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
	 * @param opmode
	 *            the given mode of operation
	 * 
	 * @param padding
	 *            the given padding algorithm
	 * 
	 * @throws NullPointerException
	 *             If
	 *             <code>(n == null) || (e == null) || (d == null) || (opmode == null) || (padding == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(n <= 0) || (e <= 0) || (d <= 0) || (n <= e) || (n <= d)</code>
	 * 
	 * @throws NoSuchAlgorithmException
	 *             Thrown by <code>CipherEngUtil.getEngine(RSAUtil.ALGORITHM, opmode, padding)</code>
	 * 
	 * @throws NoSuchPaddingException
	 *             Thrown by <code>CipherEngUtil.getEngine(RSAUtil.ALGORITHM, opmode, padding)</code>
	 * 
	 * @throws InvalidKeySpecException
	 *             Thrown by
	 *             <code>RSACipherEng.KEY_FACTORY.generatePublic(new RSAPublicKeySpec(n, e))</code> or
	 *             <code>RSACipherEng.KEY_FACTORY.generatePrivate(new RSAPrivateKeySpec(n, d))</code>
	 */
	protected RSACipherEng(BigInteger n, BigInteger e, BigInteger d, CipherEngUtil.OPMODE opmode,
			CipherEngUtil.PADDING padding) throws NullPointerException, IllegalArgumentException,
			NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException {
		if ((n.signum() != 1) || (e.signum() != 1) || (d.signum() != 1)) { // i.e., (n <= 0) || (e <= 0) || (d <= 0)
			throw new IllegalArgumentException();
		} else if (n.compareTo(e) <= 0) { // i.e., n <= e
			throw new IllegalArgumentException();
		} else if (n.compareTo(d) <= 0) { // i.e., n <= d
			throw new IllegalArgumentException();
		}
		// (0 < n) && (0 < e) && (0 < d) && (e < n) && (d < n)
		// i.e., (0 < e) && (0 < d) && (max(e, d) < n)

		// The following is meant to be an assignment of this.engine, this.opmode, and this.padding.
		this.engine = CipherEngUtil.getEngine(RSAUtil.ALGORITHM, this.opmode = opmode, this.padding = padding);

		// Set n, e, and d.
		this.n = n;
		this.e = e;
		this.d = d;

		// Set publicKey and privateKey.
		this.publicKey = (RSAPublicKey) RSACipherEng.KEY_FACTORY.generatePublic(new RSAPublicKeySpec(this.n, this.e));
		this.privateKey = (RSAPrivateKey) RSACipherEng.KEY_FACTORY
				.generatePrivate(new RSAPrivateKeySpec(this.n, this.d));
	}

	/**
	 * Copy ctor.
	 * 
	 * @param other
	 *            the given RSACipherEng object
	 * 
	 * @throws NullPointerException
	 *             If <code>other == null</code>
	 */
	public RSACipherEng(RSACipherEng other) throws NullPointerException {
		this.engine = other.engine; // Since Cipher is singleton (accessed through getInstance).
		this.n = other.n; // Since attribute is immutable, we can do a shallow copy.
		this.e = other.e; // Since attribute is immutable, we can do a shallow copy.
		this.d = other.d; // Since attribute is immutable, we can do a shallow copy.
		this.publicKey = other.publicKey; // Since attribute is immutable, we can do a shallow copy.
		this.privateKey = other.privateKey; // Since attribute is immutable, we can do a shallow copy.
		this.opmode = other.opmode; // Since enum type assignment is a deep enough copy.
		this.padding = other.padding; // Since enum type assignment is a deep enough copy.
	}

	@Override
	protected Object clone() throws CloneNotSupportedException { // semi-copy
		throw new CloneNotSupportedException("Use the copy ctor instead.");
	}

	@Override
	protected void finalize() { // semi-dtor
		this.engine = null;
		this.n = this.e = this.d = null;
		this.publicKey = null;
		this.privateKey = null;
		this.opmode = null;
		this.padding = null;
	}

	/**
	 * RSACipherEng static factory: construct an RSACipherEng object from the given attributes.
	 * 
	 * @param p
	 *            one of the two prime factors of the cipher modulus
	 * 
	 * @param q
	 *            one of the two prime factors of the cipher modulus
	 * 
	 * @param e
	 *            the given cipher public key
	 * 
	 * @param opmode
	 *            the given mode of operation
	 * 
	 * @param padding
	 *            the given padding algorithm
	 * 
	 * @return The resulting RSACipherEng object.
	 * 
	 * @throws NullPointerException
	 *             If
	 *             <code>(p == null) || (q == null) || (e == null) || (opmode == null) || (padding == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(p <= 1) || (q <= 1) || (p == q) || (e <= 0) || (phi <= e)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>gcd(e, phi) != 1</code>
	 * 
	 * @throws InvalidKeySpecException
	 *             Thrown by
	 *             <code>RSACipherEng.KEY_FACTORY.generatePublic(new RSAPublicKeySpec(n, e))</code> or
	 *             <code>RSACipherEng.KEY_FACTORY.generatePrivate(new RSAPrivateKeySpec(n, d))</code>
	 */
	public static RSACipherEng knownFactors(BigInteger p, BigInteger q, BigInteger e, CipherEngUtil.OPMODE opmode,
			CipherEngUtil.PADDING padding)
			throws NullPointerException, IllegalArgumentException, ArithmeticException, InvalidKeySpecException {
		try {
			return new RSACipherEng(p, q, e, opmode, padding, null, null);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
			throw new ExceptionInInitializerError();
		}
	}

	/**
	 * RSACipherEng static factory: construct an RSACipherEng object from the given attributes.
	 * 
	 * @param p
	 *            one of the two prime factors of the cipher modulus
	 * 
	 * @param q
	 *            one of the two prime factors of the cipher modulus
	 * 
	 * @param e
	 *            the given cipher public key
	 * 
	 * @param opmode
	 *            the given mode of operation
	 * 
	 * @return The resulting RSACipherEng object.
	 * 
	 * @throws NullPointerException
	 *             If <code>(p == null) || (q == null) || (e == null) || (opmode == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(p <= 1) || (q <= 1) || (p == q) || (e <= 0) || (phi <= e)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>gcd(e, phi) != 1</code>
	 * 
	 * @throws InvalidKeySpecException
	 *             Thrown by
	 *             <code>RSACipherEng.KEY_FACTORY.generatePublic(new RSAPublicKeySpec(n, e))</code> or
	 *             <code>RSACipherEng.KEY_FACTORY.generatePrivate(new RSAPrivateKeySpec(n, d))</code>
	 */
	public static RSACipherEng knownFactors(BigInteger p, BigInteger q, BigInteger e, CipherEngUtil.OPMODE opmode)
			throws NullPointerException, IllegalArgumentException, ArithmeticException, InvalidKeySpecException {
		return RSACipherEng.knownFactors(p, q, e, opmode, CipherEngUtil.DEFAULT_PADDING);
	}

	/**
	 * RSACipherEng static factory: construct an RSACipherEng object from the given attributes.
	 * 
	 * @param p
	 *            one of the two prime factors of the cipher modulus
	 * 
	 * @param q
	 *            one of the two prime factors of the cipher modulus
	 * 
	 * @param e
	 *            the given cipher public key
	 * 
	 * @return The resulting RSACipherEng object.
	 * 
	 * @throws NullPointerException
	 *             If <code>(p == null) || (q == null) || (e == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(p <= 1) || (q <= 1) || (p == q) || (e <= 0) || (phi <= e)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>gcd(e, phi) != 1</code>
	 * 
	 * @throws InvalidKeySpecException
	 *             Thrown by
	 *             <code>RSACipherEng.KEY_FACTORY.generatePublic(new RSAPublicKeySpec(n, e))</code> or
	 *             <code>RSACipherEng.KEY_FACTORY.generatePrivate(new RSAPrivateKeySpec(n, d))</code>
	 */
	public static RSACipherEng knownFactors(BigInteger p, BigInteger q, BigInteger e)
			throws NullPointerException, IllegalArgumentException, ArithmeticException, InvalidKeySpecException {
		return RSACipherEng.knownFactors(p, q, e, CipherEngUtil.DEFAULT_MODE);
	}

	/**
	 * RSACipherEng static factory: construct an RSACipherEng object from the given attributes.
	 * 
	 * @param phi
	 *            the given value of Euler's totient function for the cipher modulus
	 * 
	 * @param n
	 *            the given cipher modulus
	 * 
	 * @param e
	 *            the given cipher public key
	 * 
	 * @param opmode
	 *            the given mode of operation
	 * 
	 * @param padding
	 *            the given padding algorithm
	 * 
	 * @return The resulting RSACipherEng object.
	 * 
	 * @throws NullPointerException
	 *             If
	 *             <code>(phi == null) || (n == null) || (e == null) || (opmode == null) || (padding == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(phi <= 0) || (n <= 0) || (e <= 0) || (n <= phi) || (phi <= e)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>gcd(e, phi) != 1</code>
	 * 
	 * @throws InvalidKeySpecException
	 *             Thrown by
	 *             <code>RSACipherEng.KEY_FACTORY.generatePublic(new RSAPublicKeySpec(n, e))</code> or
	 *             <code>RSACipherEng.KEY_FACTORY.generatePrivate(new RSAPrivateKeySpec(n, d))</code>
	 */
	public static RSACipherEng knownTotient(BigInteger phi, BigInteger n, BigInteger e, CipherEngUtil.OPMODE opmode,
			CipherEngUtil.PADDING padding)
			throws NullPointerException, IllegalArgumentException, ArithmeticException, InvalidKeySpecException {
		try {
			return new RSACipherEng(phi, n, e, opmode, padding, null);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
			throw new ExceptionInInitializerError();
		}
	}

	/**
	 * RSACipherEng static factory: construct an RSACipherEng object from the given attributes.
	 * 
	 * @param phi
	 *            the given value of Euler's totient function for the cipher modulus
	 * 
	 * @param n
	 *            the given cipher modulus
	 * 
	 * @param e
	 *            the given cipher public key
	 * 
	 * @param opmode
	 *            the given mode of operation
	 * 
	 * @return The resulting RSACipherEng object.
	 * 
	 * @throws NullPointerException
	 *             If <code>(phi == null) || (n == null) || (e == null) || (opmode == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(phi <= 0) || (n <= 0) || (e <= 0) || (n <= phi) || (phi <= e)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>gcd(e, phi) != 1</code>
	 * 
	 * @throws InvalidKeySpecException
	 *             Thrown by
	 *             <code>RSACipherEng.KEY_FACTORY.generatePublic(new RSAPublicKeySpec(n, e))</code> or
	 *             <code>RSACipherEng.KEY_FACTORY.generatePrivate(new RSAPrivateKeySpec(n, d))</code>
	 */
	public static RSACipherEng knownTotient(BigInteger phi, BigInteger n, BigInteger e, CipherEngUtil.OPMODE opmode)
			throws NullPointerException, IllegalArgumentException, ArithmeticException, InvalidKeySpecException {
		return RSACipherEng.knownTotient(phi, n, e, opmode, CipherEngUtil.DEFAULT_PADDING);
	}

	/**
	 * RSACipherEng static factory: construct an RSACipherEng object from the given attributes.
	 * 
	 * @param phi
	 *            the given value of Euler's totient function for the cipher modulus
	 * 
	 * @param n
	 *            the given cipher modulus
	 * 
	 * @param e
	 *            the given cipher public key
	 * 
	 * @return The resulting RSACipherEng object.
	 * 
	 * @throws NullPointerException
	 *             If <code>(phi == null) || (n == null) || (e == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(phi <= 0) || (n <= 0) || (e <= 0) || (n <= phi) || (phi <= e)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>gcd(e, phi) != 1</code>
	 * 
	 * @throws InvalidKeySpecException
	 *             Thrown by
	 *             <code>RSACipherEng.KEY_FACTORY.generatePublic(new RSAPublicKeySpec(n, e))</code> or
	 *             <code>RSACipherEng.KEY_FACTORY.generatePrivate(new RSAPrivateKeySpec(n, d))</code>
	 */
	public static RSACipherEng knownTotient(BigInteger phi, BigInteger n, BigInteger e)
			throws NullPointerException, IllegalArgumentException, ArithmeticException, InvalidKeySpecException {
		return RSACipherEng.knownTotient(phi, n, e, CipherEngUtil.DEFAULT_MODE);
	}

	/**
	 * RSACipherEng static factory: construct an RSACipherEng object from the given attributes.
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
	 * @param opmode
	 *            the given mode of operation
	 * 
	 * @param padding
	 *            the given padding algorithm
	 * 
	 * @return The resulting RSACipherEng object.
	 * 
	 * @throws NullPointerException
	 *             If
	 *             <code>(n == null) || (e == null) || (d == null) || (opmode == null) || (padding == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(n <= 0) || (e <= 0) || (d <= 0) || (n <= e) || (n <= d)</code>
	 * 
	 * @throws InvalidKeySpecException
	 *             Thrown by
	 *             <code>RSACipherEng.KEY_FACTORY.generatePublic(new RSAPublicKeySpec(n, e))</code> or
	 *             <code>RSACipherEng.KEY_FACTORY.generatePrivate(new RSAPrivateKeySpec(n, d))</code>
	 */
	public static RSACipherEng knownKeys(BigInteger n, BigInteger e, BigInteger d, CipherEngUtil.OPMODE opmode,
			CipherEngUtil.PADDING padding)
			throws NullPointerException, IllegalArgumentException, InvalidKeySpecException {
		try {
			return new RSACipherEng(n, e, d, opmode, padding);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
			throw new ExceptionInInitializerError();
		}
	}

	/**
	 * RSACipherEng static factory: construct an RSACipherEng object from the given attributes.
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
	 * @param opmode
	 *            the given mode of operation
	 * 
	 * @return The resulting RSACipherEng object.
	 * 
	 * @throws NullPointerException
	 *             If <code>(n == null) || (e == null) || (d == null) || (opmode == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(n <= 0) || (e <= 0) || (d <= 0) || (n <= e) || (n <= d)</code>
	 * 
	 * @throws InvalidKeySpecException
	 *             Thrown by
	 *             <code>RSACipherEng.KEY_FACTORY.generatePublic(new RSAPublicKeySpec(n, e))</code> or
	 *             <code>RSACipherEng.KEY_FACTORY.generatePrivate(new RSAPrivateKeySpec(n, d))</code>
	 */
	public static RSACipherEng knownKeys(BigInteger n, BigInteger e, BigInteger d, CipherEngUtil.OPMODE opmode)
			throws NullPointerException, IllegalArgumentException, InvalidKeySpecException {
		return RSACipherEng.knownKeys(n, e, d, opmode, CipherEngUtil.DEFAULT_PADDING);
	}

	/**
	 * RSACipherEng static factory: construct an RSACipherEng object from the given attributes.
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
	 * @return The resulting RSACipherEng object.
	 * 
	 * @throws NullPointerException
	 *             If <code>(n == null) || (e == null) || (d == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(n <= 0) || (e <= 0) || (d <= 0) || (n <= e) || (n <= d)</code>
	 * 
	 * @throws InvalidKeySpecException
	 *             Thrown by
	 *             <code>RSACipherEng.KEY_FACTORY.generatePublic(new RSAPublicKeySpec(n, e))</code> or
	 *             <code>RSACipherEng.KEY_FACTORY.generatePrivate(new RSAPrivateKeySpec(n, d))</code>
	 */
	public static RSACipherEng knownKeys(BigInteger n, BigInteger e, BigInteger d)
			throws NullPointerException, IllegalArgumentException, InvalidKeySpecException {
		return RSACipherEng.knownKeys(n, e, d, CipherEngUtil.DEFAULT_MODE);
	}

	/**
	 * @return <code>this.n</code>.
	 */
	public BigInteger getN() {
		return this.n;
	}

	/**
	 * @return <code>this.e</code>.
	 */
	public BigInteger getE() {
		return this.e;
	}

	/**
	 * @return <code>this.d</code>.
	 */
	public BigInteger getD() {
		return this.d;
	}

	/**
	 * @return <code>this.opmode</code>.
	 */
	public CipherEngUtil.OPMODE opmode() {
		return this.opmode;
	}

	/**
	 * Set the calling object's mode of operation to the given mode of operation.
	 * 
	 * @param opmode
	 *            the given mode of operation
	 * 
	 * @throws NullPointerException
	 *             If <code>opmode == null</code>
	 */
	public void opmode(CipherEngUtil.OPMODE opmode) throws NullPointerException {
		// The following is meant to be an assignment of this.engine and this.opmode.
		try {
			this.engine = CipherEngUtil.getEngine(RSAUtil.ALGORITHM, opmode, this.padding);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
			throw new ExceptionInInitializerError();
		}
		this.opmode = opmode;
	}

	/**
	 * @return <code>this.padding</code>.
	 */
	public CipherEngUtil.PADDING padding() {
		return this.padding;
	}

	/**
	 * Set the calling object's padding algorithm to the given padding algorithm.
	 * 
	 * @param padding
	 *            the given padding algorithm
	 * 
	 * @throws NullPointerException
	 *             If <code>padding == null</code>
	 */
	public void padding(CipherEngUtil.PADDING padding) throws NullPointerException {
		// The following is meant to be an assignment of this.engine and this.padding.
		try {
			this.engine = CipherEngUtil.getEngine(RSAUtil.ALGORITHM, this.opmode, padding);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
			throw new ExceptionInInitializerError();
		}
		this.padding = padding;
	}

	/**
	 * @return <code>RSAUtil.toString(this.n, this.e, this.d)</code>.
	 */
	@Override
	public String toString() {
		return RSAUtil.toString(this.n, this.e, this.d);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.d.hashCode();
		result = prime * result + this.e.hashCode();
		result = prime * result + this.opmode.hashCode();
		result = prime * result + this.n.hashCode();
		result = prime * result + this.padding.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return ((obj instanceof RSACipherEng) ? this.equals((RSACipherEng) obj) : false);
	}

	/**
	 * @param other
	 *            the given RSACipherEng object
	 * 
	 * @see #equals(Object)
	 */
	public boolean equals(RSACipherEng other) {
		if (other == null) {
			return false;
		} else if (this == other) {
			return true;
		}

		// After pointer equality check, first compare the enum types.
		if ((this.opmode != other.opmode) || (this.padding != other.padding)) {
			return false;
		}
		/*
		 * Next, compare the cipher public key since it should be the smallest. Then compare the cipher
		 * private key since it should be the second smallest. Finally, compare the modulus since it should
		 * be the largest.
		 */
		return (this.e.equals(other.e) && this.d.equals(other.d) && this.n.equals(other.n));
	}

	/**
	 * Encrypt the given plaintext byte array.
	 * 
	 * @param p
	 *            the given plaintext byte array
	 * 
	 * @param publicKey
	 *            specifies whether the public key should be applied to the given plaintext byte array
	 * 
	 * @return The encrypted ciphertext byte array.
	 * 
	 * @throws NullPointerException
	 *             If <code>p == null</code>
	 * 
	 * @throws InvalidKeyException
	 *             Thrown by
	 *             <code>Cipher.init(Cipher.ENCRYPT_MODE, publicKey ? this.publicKey : this.privateKey)</code>
	 * 
	 * @throws BadPaddingException
	 *             Thrown by <code>Cipher.doFinal(p)</code>
	 * 
	 * @throws IllegalBlockSizeException
	 *             Thrown by <code>Cipher.doFinal(p)</code>
	 */
	public byte[] encrypt(byte[] p, boolean publicKey)
			throws NullPointerException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		this.engine.init(Cipher.ENCRYPT_MODE, publicKey ? this.publicKey : this.privateKey);
		return this.engine.doFinal(p);
	}

	/**
	 * Encrypt the given plaintext byte array.
	 * 
	 * @param p
	 *            the given plaintext byte array
	 * 
	 * @return <code>this.encrypt(p, true)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>p == null</code>
	 * 
	 * @throws InvalidKeyException
	 *             Thrown by <code>Cipher.init(Cipher.ENCRYPT_MODE, this.publicKey)</code>
	 * 
	 * @throws BadPaddingException
	 *             Thrown by <code>Cipher.doFinal(p)</code>
	 * 
	 * @throws IllegalBlockSizeException
	 *             Thrown by <code>Cipher.doFinal(p)</code>
	 */
	public byte[] encrypt(byte[] p)
			throws NullPointerException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		return this.encrypt(p, true);
	}

	/**
	 * Encrypt the given plaintext.
	 * 
	 * @param p
	 *            the given plaintext
	 * 
	 * @param publicKey
	 *            specifies whether the public key should be applied to the given plaintext
	 * 
	 * @return <code>new BigInteger(this.encrypt(p.toByteArray(), publicKey))</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>p == null</code>
	 * 
	 * @throws InvalidKeyException
	 *             Thrown by
	 *             <code>Cipher.init(Cipher.ENCRYPT_MODE, publicKey ? this.publicKey : this.privateKey)</code>
	 * 
	 * @throws BadPaddingException
	 *             Thrown by <code>Cipher.doFinal(p.toByteArray())</code>
	 * 
	 * @throws IllegalBlockSizeException
	 *             Thrown by <code>Cipher.doFinal(p.toByteArray())</code>
	 */
	public BigInteger encrypt(BigInteger p, boolean publicKey)
			throws NullPointerException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		return new BigInteger(this.encrypt(p.toByteArray(), publicKey));
	}

	/**
	 * Encrypt the given plaintext.
	 * 
	 * @param p
	 *            the given plaintext
	 * 
	 * @return <code>this.encrypt(p, true)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>p == null</code>
	 * 
	 * @throws InvalidKeyException
	 *             Thrown by <code>Cipher.init(Cipher.ENCRYPT_MODE, this.publicKey)</code>
	 * 
	 * @throws BadPaddingException
	 *             Thrown by <code>Cipher.doFinal(p.toByteArray())</code>
	 * 
	 * @throws IllegalBlockSizeException
	 *             Thrown by <code>Cipher.doFinal(p.toByteArray())</code>
	 */
	public BigInteger encrypt(BigInteger p)
			throws NullPointerException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		return this.encrypt(p, true);
	}

	/**
	 * Decrypt the given ciphertext byte array.
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @param privateKey
	 *            specifies whether the private key should be applied to the given ciphertext byte array
	 * 
	 * @return The decrypted plaintext byte array.
	 * 
	 * @throws NullPointerException
	 *             If <code>c == null</code>
	 * 
	 * @throws InvalidKeyException
	 *             Thrown by
	 *             <code>Cipher.init(Cipher.DECRYPT_MODE, privateKey ? this.privateKey : this.publicKey)</code>
	 * 
	 * @throws BadPaddingException
	 *             Thrown by <code>Cipher.doFinal(c)</code>
	 * 
	 * @throws IllegalBlockSizeException
	 *             Thrown by <code>Cipher.doFinal(c)</code>
	 */
	public byte[] decrypt(byte[] c, boolean privateKey)
			throws NullPointerException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		this.engine.init(Cipher.DECRYPT_MODE, privateKey ? this.privateKey : this.publicKey);
		return this.engine.doFinal(c);
	}

	/**
	 * Decrypt the given ciphertext byte array.
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @return <code>this.decrypt(c, true)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>c == null</code>
	 * 
	 * @throws InvalidKeyException
	 *             Thrown by <code>Cipher.init(Cipher.DECRYPT_MODE, this.privateKey)</code>
	 * 
	 * @throws BadPaddingException
	 *             Thrown by <code>Cipher.doFinal(c)</code>
	 * 
	 * @throws IllegalBlockSizeException
	 *             Thrown by <code>Cipher.doFinal(c)</code>
	 */
	public byte[] decrypt(byte[] c)
			throws NullPointerException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		return this.decrypt(c, true);
	}

	/**
	 * Decrypt the given ciphertext.
	 * 
	 * @param c
	 *            the given ciphertext
	 * 
	 * @param privateKey
	 *            specifies whether the private key should be applied to the given ciphertext
	 * 
	 * @return <code>new BigInteger(this.decrypt(c.toByteArray(), privateKey))</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>c == null</code>
	 * 
	 * @throws InvalidKeyException
	 *             Thrown by
	 *             <code>Cipher.init(Cipher.DECRYPT_MODE, privateKey ? this.privateKey : this.publicKey)</code>
	 * 
	 * @throws BadPaddingException
	 *             Thrown by <code>Cipher.doFinal(c.toByteArray())</code>
	 * 
	 * @throws IllegalBlockSizeException
	 *             Thrown by <code>Cipher.doFinal(c.toByteArray())</code>
	 */
	public BigInteger decrypt(BigInteger c, boolean privateKey)
			throws NullPointerException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		return new BigInteger(this.decrypt(c.toByteArray(), privateKey));
	}

	/**
	 * Decrypt the given ciphertext.
	 * 
	 * @param c
	 *            the given ciphertext
	 * 
	 * @return <code>this.decrypt(c, true)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>c == null</code>
	 * 
	 * @throws InvalidKeyException
	 *             Thrown by <code>Cipher.init(Cipher.DECRYPT_MODE, this.privateKey)</code>
	 * 
	 * @throws BadPaddingException
	 *             Thrown by <code>Cipher.doFinal(c.toByteArray())</code>
	 * 
	 * @throws IllegalBlockSizeException
	 *             Thrown by <code>Cipher.doFinal(c.toByteArray())</code>
	 */
	public BigInteger decrypt(BigInteger c)
			throws NullPointerException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		return this.decrypt(c, true);
	}
}
