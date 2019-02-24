package util;

import java.math.BigInteger;

/**
 * RSA cipher.
 * 
 * @author Ashkan Moatamed
 */
public class RSA {
	/**
	 * Dependencies: <code>
	 * 		1. util.RSAUtil
	 * </code>
	 */

	/**
	 * BigInteger objects are immutable. Therefore, it is "safe" to make the following final class
	 * attributes public.
	 */

	/**
	 * One of the two prime factors of the cipher modulus. <br>
	 * May be <code>null</code>. <br>
	 * <code>(this.p == null) == (this.q == null)</code>.
	 */
	public final BigInteger p;

	/**
	 * One of the two prime factors of the cipher modulus. <br>
	 * May be <code>null</code>. <br>
	 * <code>(this.q == null) == (this.p == null)</code>.
	 */
	public final BigInteger q;

	/**
	 * Cipher modulus. <br>
	 * Guaranteed to be non-<code>null</code>.
	 */
	public final BigInteger n;

	/**
	 * Cipher public key. <br>
	 * Guaranteed to be non-<code>null</code>.
	 */
	public final BigInteger e;

	/**
	 * Cipher private key. <br>
	 * Guaranteed to be non-<code>null</code>.
	 */
	public final BigInteger d;

	/**
	 * <code>this.d (mod (this.p - 1))</code>. <br>
	 * <code>(this.dP == null) == (this.p == null)</code>.
	 */
	public final BigInteger dP;

	/**
	 * <code>this.d (mod (this.q - 1))</code>. <br>
	 * <code>(this.dQ == null) == (this.q == null)</code>.
	 */
	public final BigInteger dQ;

	/**
	 * <code>1 / this.q (mod this.p)</code>. <br>
	 * <code>(this.qInv == null) == (this.q == null)</code>. <br>
	 * <code>(this.qInv == null) == (this.p == null)</code>.
	 */
	public final BigInteger qInv;

	/**
	 * Construct an RSA object with the given p, q, and e.
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
	 * @param dummy1
	 *            an (unused) object used to distinguish between this ctor and the n-e-d ctor
	 * 
	 * @param dummy2
	 *            an (unused) object used to distinguish between this ctor and the phi-n-e ctor
	 * 
	 * @throws NullPointerException
	 *             If <code>(p == null) || (q == null) || (e == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(p <= 1) || (q <= 1) || (e <= 0)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(gcd(e, phi) != 1) || (gcd(p, q) != 1)</code>
	 */
	protected RSA(BigInteger p, BigInteger q, BigInteger e, Object dummy1, Object dummy2)
			throws NullPointerException, IllegalArgumentException, ArithmeticException {
		if ((p.signum() != 1) || (q.signum() != 1) || (e.signum() != 1)) { // i.e., (p <= 0) || (q <= 0) || (e <= 0)
			throw new IllegalArgumentException();
		}
		// (p > 0) && (q > 0) && (e > 0)

		// Set p, q, and e.
		this.p = p;
		this.q = q;
		this.e = e;

		// Save p - 1 and ensure that it is positive.
		final BigInteger p_minus_1 = this.p.subtract(BigInteger.ONE);
		if (p_minus_1.signum() != 1) { // i.e., (p - 1 <= 0)
			throw new IllegalArgumentException();
		}
		// Save q - 1 and ensure that it is positive.
		final BigInteger q_minus_1 = this.q.subtract(BigInteger.ONE);
		if (q_minus_1.signum() != 1) { // i.e., (q - 1 <= 0)
			throw new IllegalArgumentException();
		}
		// Compute the value of Euler's totient function for the cipher modulus.
		final BigInteger phi = p_minus_1.multiply(q_minus_1);

		// Set n and d.
		this.n = this.p.multiply(this.q);
		this.d = this.e.modInverse(phi);

		// Set dP, dQ, and qInv.
		this.dP = this.d.mod(p_minus_1);
		this.dQ = this.d.mod(q_minus_1);
		this.qInv = this.q.modInverse(this.p);
	}

	/**
	 * Construct an RSA object with the given phi, n, and e.
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
	 * @param dummy
	 *            an (unused) object used to distinguish between this ctor and the n-e-d ctor
	 * 
	 * @throws NullPointerException
	 *             If <code>(phi == null) || (n == null) || (e == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(phi <= 0) || (n <= 0) || (e <= 0)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>gcd(e, phi) != 1</code>
	 */
	protected RSA(BigInteger phi, BigInteger n, BigInteger e, Object dummy)
			throws NullPointerException, IllegalArgumentException, ArithmeticException {
		if ((phi.signum() != 1) || (n.signum() != 1) || (e.signum() != 1)) { // i.e., (phi <= 0) || (n <= 0) || (e <= 0)
			throw new IllegalArgumentException();
		}
		// (phi > 0) && (n > 0) && (e > 0)

		// Set p, q, dP, dQ, and qInv.
		this.p = this.q = this.dP = this.dQ = this.qInv = null;

		// Set n, e, and d.
		this.n = n;
		this.e = e;
		this.d = this.e.modInverse(phi);
	}

	/**
	 * Construct an RSA object with the given n, e, and d.
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
	 * @throws NullPointerException
	 *             If <code>(n == null) || (e == null) || (d == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(n <= 0) || (e <= 0) || (d <= 0)</code>
	 */
	protected RSA(BigInteger n, BigInteger e, BigInteger d) throws NullPointerException, IllegalArgumentException {
		if ((n.signum() != 1) || (e.signum() != 1) || (d.signum() != 1)) { // i.e., (n <= 0) || (e <= 0) || (d <= 0)
			throw new IllegalArgumentException();
		}
		// (n > 0) && (e > 0) && (d > 0)

		// Set p, q, dP, dQ, and qInv.
		this.p = this.q = this.dP = this.dQ = this.qInv = null;

		// Set n, e, and d.
		this.n = n;
		this.e = e;
		this.d = d;
	}

	/**
	 * Since this class is immutable, there is no need for a copy ctor.
	 */

	/**
	 * RSA static factory: construct an RSA object with the given p, q, and e.
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
	 * @return The resulting RSA object.
	 * 
	 * @throws NullPointerException
	 *             If <code>(p == null) || (q == null) || (e == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(p <= 1) || (q <= 1) || (e <= 0)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(gcd(e, phi) != 1) || (gcd(p, q) != 1)</code>
	 */
	public static RSA knownFactors(BigInteger p, BigInteger q, BigInteger e)
			throws NullPointerException, IllegalArgumentException, ArithmeticException {
		return new RSA(p, q, e, null, null);
	}

	/**
	 * RSA static factory: construct an RSA object with the given phi, n, and e.
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
	 * @return The resulting RSA object.
	 * 
	 * @throws NullPointerException
	 *             If <code>(phi == null) || (n == null) || (e == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(phi <= 0) || (n <= 0) || (e <= 0)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>gcd(e, phi) != 1</code>
	 */
	public static RSA knownTotient(BigInteger phi, BigInteger n, BigInteger e)
			throws NullPointerException, IllegalArgumentException, ArithmeticException {
		return new RSA(phi, n, e, null);
	}

	/**
	 * RSA static factory: construct an RSA object with the given n, e, and d.
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
	 * @return The resulting RSA object.
	 * 
	 * @throws NullPointerException
	 *             If <code>(n == null) || (e == null) || (d == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(n <= 0) || (e <= 0) || (d <= 0)</code>
	 */
	public static RSA knownKeys(BigInteger n, BigInteger e, BigInteger d)
			throws NullPointerException, IllegalArgumentException {
		return new RSA(n, e, d);
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
		result = prime * result + this.n.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return ((obj instanceof RSA) ? this.equals((RSA) obj) : false);
	}

	/**
	 * @param other
	 *            the given RSA object
	 * 
	 * @see #equals(Object)
	 */
	public boolean equals(RSA other) {
		if (other == null) {
			return false;
		} else if (this == other) {
			return true;
		}
		/*
		 * After pointer equality check, first compare the cipher public key since it should be the
		 * smallest. Then compare the cipher private key since it should be the second smallest. Finally,
		 * compare the modulus since it should be the largest.
		 */
		return (this.e.equals(other.e) && this.d.equals(other.d) && this.n.equals(other.n));
	}

	/**
	 * Apply the requested key (either public or private) to the given message.
	 * 
	 * @param m
	 *            the given message
	 * 
	 * @param publicKey
	 *            specifies whether the public key should be applied to the given message
	 * 
	 * @return The resulting BigInteger object.
	 * 
	 * @throws NullPointerException
	 *             If <code>m == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>m <= 0</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>gcd(m, this.n) != 1</code>
	 */
	public BigInteger apply(BigInteger m, boolean publicKey)
			throws NullPointerException, IllegalArgumentException, ArithmeticException {
		if (m.signum() != 1) { // i.e, m <= 0
			throw new IllegalArgumentException();
		}
		// m > 0

		/*
		 * Apply the public key since it should be small enough that will not merit using the Chinese
		 * Remainder Theorem (i.e., C.R.T.).
		 */
		if (publicKey) {
			return m.modPow(this.e, this.n);
		}

		/*
		 * Check to see if we can use the Chinese Remainder Theorem (i.e., C.R.T.) instead of using the
		 * private key directly.
		 */
		if (this.p != null) {
			/**
			 * Due to class invariants, we know that: <br>
			 * 1. <code>this.q != null</code>. <br>
			 * 2. <code>this.dP != null</code>. <br>
			 * 3. <code>this.dQ != null</code>. <br>
			 * 4. <code>this.qInv != null</code>. <br>
			 * 
			 * Therefore, we can do the following: <br>
			 * 1. <code>m = [(mP - mQ) * this.q * this.qInv + mQ] (mod this.n)</code>.
			 */

			final BigInteger mP = m.modPow(this.dP, this.p);
			final BigInteger mQ = m.modPow(this.dQ, this.q);
			BigInteger result = mP.subtract(mQ);
			result = result.multiply(this.q).multiply(this.qInv);
			result = result.add(mQ).mod(this.n);
			return result;
		}

		/*
		 * At this point, we know that publicKey is false and that the Chinese Remainder Theorem (i.e.,
		 * C.R.T.) cannot be used. Therefore, just perform the standard calculation.
		 */
		return m.modPow(this.d, this.n);
	}

	/**
	 * @param m
	 *            the given message
	 * 
	 * @return <code>this.apply(m, true)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>m == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>m <= 0</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>gcd(m, this.n) != 1</code>
	 */
	public BigInteger apply(BigInteger m) throws NullPointerException, IllegalArgumentException, ArithmeticException {
		return this.apply(m, true);
	}

	/**
	 * Apply the requested key (either public or private) to the given message byte array.
	 * 
	 * @param m
	 *            the given message byte array
	 * 
	 * @param publicKey
	 *            specifies whether the public key should be applied to the given message byte array
	 * 
	 * @return <code>this.apply(new BigInteger(m), publicKey).toByteArray()</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>m == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>new BigInteger(m) <= 0</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>gcd(new BigInteger(m), this.n) != 1</code>
	 */
	public byte[] apply(byte[] m, boolean publicKey)
			throws NullPointerException, IllegalArgumentException, ArithmeticException {
		return this.apply(new BigInteger(m), publicKey).toByteArray();
	}

	/**
	 * @param m
	 *            the given message byte array
	 * 
	 * @return <code>this.apply(m, true)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>m == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>new BigInteger(m) <= 0</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>gcd(new BigInteger(m), this.n) != 1</code>
	 */
	public byte[] apply(byte[] m) throws NullPointerException, IllegalArgumentException, ArithmeticException {
		return this.apply(m, true);
	}
}
