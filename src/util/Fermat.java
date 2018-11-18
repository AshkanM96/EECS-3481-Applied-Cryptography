package util;

import java.math.BigInteger;

/**
 * Fermat's compositeness test.
 * 
 * @author Ashkan Moatamed
 */
public class Fermat {
	/**
	 * Dependencies: <code>
	 * 		1. util.BigIntegerUtil
	 * </code>
	 */

	/**
	 * BigInteger objects are immutable. Therefore, it is "safe" to make the following final class
	 * attribute public.
	 */

	/**
	 * The number being tested.
	 */
	public final BigInteger n;

	/**
	 * <code>this.n.subtract(BigInteger.ONE)</code>.
	 */
	public final BigInteger n_minus_1;

	/**
	 * Construct a Fermat object from the given number.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @throws NullPointerException
	 *             If <code>n == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(n.compareTo(BigIntegerUtil.TWO) <= 0) || (BigIntegerUtil.isEven(n))</code>
	 */
	public Fermat(BigInteger n) throws NullPointerException, IllegalArgumentException {
		if (n.compareTo(BigIntegerUtil.TWO) <= 0) { // i.e., n <= BigIntegerUtil.TWO
			throw new IllegalArgumentException();
		} else if (BigIntegerUtil.isEven(n)) { // Enforce n being odd.
			throw new IllegalArgumentException();
		}

		// The following is meant to be an assignment of this.n and this.n_minus_1.
		this.n_minus_1 = (this.n = n).subtract(BigInteger.ONE);
	}

	/**
	 * Since this class is immutable, there is no need for a copy ctor.
	 */

	@Override
	public String toString() {
		return this.n.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.n.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return ((obj instanceof Fermat) ? this.equals((Fermat) obj) : false);
	}

	/**
	 * @param other
	 *            the given Fermat object
	 * 
	 * @see #equals(Object)
	 */
	public boolean equals(Fermat other) {
		return (this == other ? true : this.n.equals(other.n));
	}

	/**
	 * Perform Fermat's compositeness test on <code>this.n</code> with the given base. <br>
	 * Precondition: <code>(BigInteger.ONE.compareTo(b) < 0) && (b.compareTo(this.n) < 0)</code> <br>
	 * Precondition: <code>this.n.gcd(b).equals(BigInteger.ONE)</code>
	 * 
	 * @param b
	 *            the given base
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return The resulting TestResultFermat object.
	 */
	protected TestResultFermat testCoprimeFixedInput(BigInteger b, boolean print) {
		final BigInteger r = b.modPow(this.n_minus_1, this.n);
		final boolean isInconclusive = r.equals(BigInteger.ONE);

		// Only print if requested.
		if (print) {
			System.out.println((isInconclusive ? "Test is inconclusive with base " + b + "."
					: b + " is a witness of n's compositeness.") + "\n");
		}

		// Create Result and return it.
		return new TestResultFermat(this.n, isInconclusive, b);
	}

	/**
	 * Perform Fermat's compositeness test on <code>this.n</code> with the given base.
	 * 
	 * @param b
	 *            the given base
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return The resulting TestResultFermat object.
	 * 
	 * @throws NullPointerException
	 *             If <code>b == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(b.compareTo(BigInteger.ONE) <= 0) || (this.n.compareTo(b) <= 0)
	 *             || (!this.n.gcd(b).equals(BigInteger.ONE))</code>
	 */
	public TestResultFermat testCoprime(BigInteger b, boolean print)
			throws IllegalArgumentException, NullPointerException {
		if (b.compareTo(BigInteger.ONE) <= 0) { // i.e., b <= BigInteger.ONE
			throw new IllegalArgumentException();
		} else if (this.n.compareTo(b) <= 0) { // i.e., this.n <= b
			throw new IllegalArgumentException();
		}
		// At this point, we know that 1 < b < this.n.
		if (!this.n.gcd(b).equals(BigInteger.ONE)) {
			throw new IllegalArgumentException();
		}
		return this.testCoprimeFixedInput(b, print);
	}

	/**
	 * Perform Fermat's compositeness test on <code>this.n</code> with the given base.
	 * 
	 * @param b
	 *            the given base
	 * 
	 * @return <code>this.testCoprime(b, false)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>b == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(b.compareTo(BigInteger.ONE) <= 0) || (this.n.compareTo(b) <= 0)
	 *             || (!this.n.gcd(b).equals(BigInteger.ONE))</code>
	 */
	public TestResultFermat testCoprime(BigInteger b) throws IllegalArgumentException, NullPointerException {
		return this.testCoprime(b, false);
	}

	/**
	 * Perform Fermat's compositeness test on <code>this.n</code> with the given base. <br>
	 * Precondition: <code>(BigInteger.ONE.compareTo(b) < 0) && (b.compareTo(this.n) < 0)</code>
	 * 
	 * @param b
	 *            the given base
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return The resulting TestResultFermat object.
	 */
	protected TestResultFermat testFixedInput(BigInteger b, boolean print) {
		// Check to see if gcd(this.n, b) == 1.
		final BigInteger gcd = this.n.gcd(b);
		if (gcd.equals(BigInteger.ONE)) {
			// Apply Fermat's little theorem since gcd(this.n, b) == 1.
			return this.testCoprimeFixedInput(b, print);
		}
		// Since 1 < b < this.n with gcd(this.n, b) != 1, then b is a factor of this.n.

		// Only print if requested.
		if (print) {
			System.out.println("gcd(n, " + b + ") == " + gcd + " != 1.");
			System.out.println("Therefore, " + b + " is a witness of n's compositeness.\n");
		}

		// Create Result and return it.
		return new TestResultFermat(this.n, false, b);
	}

	/**
	 * Perform Fermat's compositeness test on <code>this.n</code> with the given base.
	 * 
	 * @param b
	 *            the given base
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return The resulting TestResultFermat object.
	 * 
	 * @throws NullPointerException
	 *             If <code>b == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(b.compareTo(BigInteger.ONE) <= 0) || (this.n.compareTo(b) <= 0)</code>
	 */
	public TestResultFermat test(BigInteger b, boolean print) throws NullPointerException, IllegalArgumentException {
		if (b.compareTo(BigInteger.ONE) <= 0) { // i.e., b <= BigInteger.ONE
			throw new IllegalArgumentException();
		} else if (this.n.compareTo(b) <= 0) { // i.e., this.n <= b
			throw new IllegalArgumentException();
		}
		// At this point, we know that 1 < b < this.n.
		return this.testFixedInput(b, print);
	}

	/**
	 * Perform Fermat's compositeness test on <code>this.n</code> with the given base.
	 * 
	 * @param b
	 *            the given base
	 * 
	 * @return <code>this.test(b, false)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>b == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(b.compareTo(BigInteger.ONE) <= 0) || (this.n.compareTo(b) <= 0)</code>
	 */
	public TestResultFermat test(BigInteger b) throws NullPointerException, IllegalArgumentException {
		return this.test(b, false);
	}

	// --------------------------------------------------
	// Nested wrapper class.
	// --------------------------------------------------

	/**
	 * Simple wrapper for compositeness test result. Saves the number, the test result, and the witness.
	 * <br>
	 * 
	 * It's main usage is for the return type of <code>Fermat.test</code>.
	 * 
	 * @author Ashkan Moatamed
	 */
	public static class TestResultFermat {
		/**
		 * The number being tested.
		 */
		public final BigInteger n;

		/**
		 * Indicates whether the test was inconclusive.
		 */
		public final boolean isInconclusive;

		/**
		 * A potential witness of the number being tested. An actual witness if
		 * <code>!this.isInconclusive</code>.
		 */
		public final BigInteger witness;

		/**
		 * Construct a TestResultFermat object from the given attributes.
		 * 
		 * @param n
		 *            the given number
		 * 
		 * @param isInconclusive
		 *            indicates whether the test was inconclusive
		 * 
		 * @param witness
		 *            the given potential witness
		 */
		public TestResultFermat(BigInteger n, boolean isInconclusive, BigInteger witness) {
			this.n = n;
			this.isInconclusive = isInconclusive;
			this.witness = witness;
		}

		/**
		 * Copy ctor.
		 * 
		 * @param other
		 *            the given TestResultFermat object
		 * 
		 * @throws NullPointerException
		 *             If <code>other == null</code>
		 */
		public TestResultFermat(TestResultFermat other) throws NullPointerException {
			this(other.n, other.isInconclusive, other.witness);
		}

		/**
		 * @return <code>!this.isInconclusive</code>.
		 */
		public boolean isComposite() {
			return (!this.isInconclusive);
		}
	}
}
