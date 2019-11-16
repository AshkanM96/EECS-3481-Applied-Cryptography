package util;

import java.math.BigInteger;

/**
 * Fermat's compositeness test.
 * 
 * @author Ashkan Moatamed
 */
public class Fermat {
	/**
	 * No dependencies.
	 */

	/**
	 * BigInteger objects are immutable. Therefore, it is "safe" to make the following final attributes
	 * public.
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
	 *             If <code>BigIntUtil.isEven(n) || (n <= 1)</code>
	 */
	public Fermat(BigInteger n) throws NullPointerException, IllegalArgumentException {
		if (!n.testBit(0)) { // i.e., BigIntUtil.isEven(n)
			throw new IllegalArgumentException();
		} else if (n.compareTo(BigInteger.ONE) <= 0) { // i.e., n <= 1
			throw new IllegalArgumentException();
		}
		// n is an odd integer greater than 1

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
		return ((other == null) ? false : ((this == other) ? true : this.n.equals(other.n)));
	}

	/**
	 * Perform Fermat's compositeness test on <code>this.n</code> with the given base. <br>
	 * Precondition: <code>base != null</code> <br>
	 * Precondition: <code>(1 < base) && (base < this.n)</code> <br>
	 * Precondition: <code>gcd(this.n, base) == 1</code>
	 * 
	 * @param base
	 *            the given base
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return The resulting TestResultFermat object.
	 */
	protected TestResultFermat testCoprimeFixedInput(BigInteger base, boolean print) {
		final BigInteger r = base.modPow(this.n_minus_1, this.n);
		final boolean isInconclusive = r.equals(BigInteger.ONE);
		// Only print if requested.
		if (print) {
			System.out.println(isInconclusive ? "Test is inconclusive with base " + base + ".\n"
					: base + " is a witness of n's compositeness.\n");
		}
		return new TestResultFermat(this.n, isInconclusive, base);
	}

	/**
	 * Perform Fermat's compositeness test on <code>this.n</code> with the given base.
	 * 
	 * @param base
	 *            the given base
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return The resulting TestResultFermat object.
	 * 
	 * @throws NullPointerException
	 *             If <code>base == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(base <= 1) || (this.n <= base) || (gcd(this.n, base) != 1)</code>
	 */
	public TestResultFermat testCoprime(BigInteger base, boolean print)
			throws IllegalArgumentException, NullPointerException {
		if (base.compareTo(BigInteger.ONE) <= 0) { // i.e., base <= 1
			throw new IllegalArgumentException();
		} else if (this.n.compareTo(base) <= 0) { // i.e., this.n <= base
			throw new IllegalArgumentException();
		} else if (!this.n.gcd(base).equals(BigInteger.ONE)) { // i.e., gcd(this.n, base) != 1
			throw new IllegalArgumentException();
		}
		// (1 < base) && (base < this.n) && (gcd(this.n, base) == 1)
		return this.testCoprimeFixedInput(base, print);
	}

	/**
	 * Perform Fermat's compositeness test on <code>this.n</code> with the given base.
	 * 
	 * @param base
	 *            the given base
	 * 
	 * @return <code>this.testCoprime(base, false)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>base == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(base <= 1) || (this.n <= base) || (gcd(this.n, base) != 1)</code>
	 */
	public TestResultFermat testCoprime(BigInteger base) throws IllegalArgumentException, NullPointerException {
		return this.testCoprime(base, false);
	}

	/**
	 * Perform Fermat's compositeness test on <code>this.n</code> with the given base. <br>
	 * Precondition: <code>base != null</code> <br>
	 * Precondition: <code>(1 < base) && (base < this.n)</code>
	 * 
	 * @param base
	 *            the given base
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return The resulting TestResultFermat object.
	 */
	protected TestResultFermat testFixedInput(BigInteger base, boolean print) {
		final BigInteger gcd = this.n.gcd(base);
		if (gcd.equals(BigInteger.ONE)) { // i.e., gcd(this.n, base) == 1
			return this.testCoprimeFixedInput(base, print);
		}
		// gcd(this.n, base) != 1

		// Only print if requested.
		if (print) {
			System.out.println("gcd(n, " + base + ") == " + gcd + " != 1");
			System.out.println("Therefore, " + base + " is a witness of n's compositeness.\n");
		}
		return new TestResultFermat(this.n, false, base);
	}

	/**
	 * Perform Fermat's compositeness test on <code>this.n</code> with the given base.
	 * 
	 * @param base
	 *            the given base
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return The resulting TestResultFermat object.
	 * 
	 * @throws NullPointerException
	 *             If <code>base == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(base <= 1) || (this.n <= base)</code>
	 */
	public TestResultFermat test(BigInteger base, boolean print) throws NullPointerException, IllegalArgumentException {
		if (base.compareTo(BigInteger.ONE) <= 0) { // i.e., base <= 1
			throw new IllegalArgumentException();
		} else if (this.n.compareTo(base) <= 0) { // i.e., this.n <= base
			throw new IllegalArgumentException();
		}
		// (1 < base) && (base < this.n)
		return this.testFixedInput(base, print);
	}

	/**
	 * Perform Fermat's compositeness test on <code>this.n</code> with the given base.
	 * 
	 * @param base
	 *            the given base
	 * 
	 * @return <code>this.test(base, false)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>base == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(base <= 1) || (this.n <= base)</code>
	 */
	public TestResultFermat test(BigInteger base) throws NullPointerException, IllegalArgumentException {
		return this.test(base, false);
	}

	// --------------------------------------------------
	// Nested wrapper class.
	// --------------------------------------------------

	/**
	 * Simple wrapper for compositeness test result. Saves the number, the test result, and the witness.
	 * <br>
	 * 
	 * It's main usage is for the return type of <code>Fermat::test</code> and
	 * <code>Fermat::testCoprime</code>.
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
