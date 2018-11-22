package util;

import java.math.BigInteger;

/**
 * Miller-Rabin's compositeness test.
 * 
 * @author Ashkan Moatamed
 */
public class MillerRabin {
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
	 * Construct a MillerRabin object from the given number.
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
	public MillerRabin(BigInteger n) throws NullPointerException, IllegalArgumentException {
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
		return ((obj instanceof MillerRabin) ? this.equals((MillerRabin) obj) : false);
	}

	/**
	 * @param other
	 *            the given MillerRabin object
	 * 
	 * @see #equals(Object)
	 */
	public boolean equals(MillerRabin other) {
		return (this == other ? true : this.n.equals(other.n));
	}

	/**
	 * Perform Miller-Rabin's compositeness test on <code>this.n</code> with the given base. <br>
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
	 * @return The resulting TestResultMillerRabin object.
	 */
	protected TestResultMillerRabin testCoprimeFixedInput(BigInteger b, boolean print) {
		BigInteger r = b.modPow(this.n_minus_1, this.n);

		// Only print if requested.
		if (print) {
			System.out.println("r_1 == " + r + ", exp_1 == " + this.n_minus_1);
		}

		/*
		 * Check to see if the very first calculation resulted in a non-1 value and thus this.n is composite
		 * by Fermat's test which means that we cannot find the super factors since we cannot perform the
		 * Square-Root test.
		 */
		if (!r.equals(BigInteger.ONE)) {
			// Only print if requested.
			if (print) {
				System.out.println(b + " is a witness of n's compositeness by Fermat's test.\n");
			}

			// Create Result and return it.
			return new TestResultMillerRabin(this.n, false, b);
		}

		/*
		 * this.n_minus_1 is guaranteed to be even since this.n is enforced to be odd in the ctor which
		 * means that the division by 2 will not have a remainder.
		 */
		BigInteger exp = this.n_minus_1.divide(BigIntegerUtil.TWO);
		// Loop until r != 1.
		for (long i = 2; (r = b.modPow(exp, this.n)).equals(BigInteger.ONE); ++i) {
			// Only print if requested.
			if (print) {
				System.out.println("r_" + i + " == " + r + ", exp_" + i + " == " + exp);
			}

			// Check to see if the exponent is even.
			if (!BigIntegerUtil.isEven(exp)) {
				// Exponent is odd but r is still 1 therefore the test is inconclusive.

				// Only print if requested.
				if (print) {
					System.out.println("\nTest is inconclusive with base " + b
							+ " since the exponent has reached an odd number but the remainder is still 1.\n");
				}

				// Create Result and return it.
				return new TestResultMillerRabin(this.n, true, b);
			}

			// Compute the next exponent since we know that the current exponent is even.
			exp = exp.divide(BigIntegerUtil.TWO);
		}
		// Only print if requested.
		if (print) {
			/*
			 * Printing a newline here, circumvents the need to add a \n to the beginning of all of the
			 * following print statements.
			 */
			System.out.println();
		}

		// Check to see if r is -1 (mod n).
		if (r.equals(this.n_minus_1)) {
			// Only print if requested.
			if (print) {
				System.out
						.println("Test is inconclusive with base " + b + " since the final remainder is -1 (mod n).\n");
			}

			// Create Result and return it.
			return new TestResultMillerRabin(this.n, true, b);
		}

		// Apply the Square-Root test to r, 1, and this.n.
		final BigInteger superFactor1 = this.n.gcd(r.subtract(BigInteger.ONE));
		final BigInteger superFactor2 = this.n.gcd(r.add(BigInteger.ONE));

		// Only print if requested.
		if (print) {
			System.out.println("GCD(n, " + r + " - 1) == " + superFactor1);
			System.out.println("and\nGCD(n, " + r + " + 1) == " + superFactor2);
			System.out.println("\n" + b + " is a witness of n's compositeness.\n");
		}

		// Create Result and return it.
		return new TestResultMillerRabin(this.n, false, b, superFactor1, superFactor2);
	}

	/**
	 * Perform Miller-Rabin's compositeness test on <code>this.n</code> with the given base.
	 * 
	 * @param b
	 *            the given base
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return The resulting TestResultMillerRabin object.
	 * 
	 * @throws NullPointerException
	 *             If <code>b == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(b.compareTo(BigInteger.ONE) <= 0) || (this.n.compareTo(b) <= 0)
	 *             || (!this.n.gcd(b).equals(BigInteger.ONE))</code>
	 */
	public TestResultMillerRabin testCoprime(BigInteger b, boolean print)
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
	 * Perform Miller-Rabin's compositeness test on <code>this.n</code> with the given base.
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
	public TestResultMillerRabin testCoprime(BigInteger b) throws IllegalArgumentException, NullPointerException {
		return this.testCoprime(b, false);
	}

	/**
	 * Perform Miller-Rabin's compositeness test on <code>this.n</code> with the given base. <br>
	 * Precondition: <code>(BigInteger.ONE.compareTo(b) < 0) && (b.compareTo(this.n) < 0)</code>
	 * 
	 * @param b
	 *            the given base
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return The resulting TestResultMillerRabin object.
	 */
	protected TestResultMillerRabin testFixedInput(BigInteger b, boolean print) {
		// Check to see if gcd(this.n, b) == 1.
		final BigInteger gcd = this.n.gcd(b);
		if (gcd.equals(BigInteger.ONE)) {
			// Apply Miller-Rabin's theorem since gcd(this.n, b) == 1.
			return this.testCoprimeFixedInput(b, print);
		}
		// Since 1 < b < this.n with gcd(this.n, b) != 1, then b is a factor of this.n.

		// Only print if requested.
		if (print) {
			System.out.println("gcd(n, " + b + ") == " + gcd + " != 1.");
			System.out.println("Therefore, " + b + " is a witness of n's compositeness.\n");
		}

		// Create Result and return it.
		return new TestResultMillerRabin(this.n, false, b, gcd);
	}

	/**
	 * Perform Miller-Rabin's compositeness test on <code>this.n</code> with the given base.
	 * 
	 * @param b
	 *            the given base
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return The resulting TestResultMillerRabin object.
	 * 
	 * @throws NullPointerException
	 *             If <code>b == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(b.compareTo(BigInteger.ONE) <= 0) || (this.n.compareTo(b) <= 0)</code>
	 */
	public TestResultMillerRabin test(BigInteger b, boolean print)
			throws NullPointerException, IllegalArgumentException {
		if (b.compareTo(BigInteger.ONE) <= 0) { // i.e., b <= BigInteger.ONE
			throw new IllegalArgumentException();
		} else if (this.n.compareTo(b) <= 0) { // i.e., this.n <= b
			throw new IllegalArgumentException();
		}
		// At this point, we know that 1 < b < this.n.
		return this.testFixedInput(b, print);
	}

	/**
	 * Perform Miller-Rabin's compositeness test on <code>this.n</code> with the given base.
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
	public TestResultMillerRabin test(BigInteger b) throws NullPointerException, IllegalArgumentException {
		return this.test(b, false);
	}

	// --------------------------------------------------
	// Nested wrapper class.
	// --------------------------------------------------

	/**
	 * Simple wrapper for compositeness test result. Saves the number, the test result, the witness, and
	 * the two super factors. <br>
	 * 
	 * It's main usage is for the return type of <code>MillerRabin.test</code>.
	 * 
	 * @author Ashkan Moatamed
	 */
	public static class TestResultMillerRabin {
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
		 * Contains some of the factors of the number being tested.
		 */
		public final BigInteger superFactor1;

		/**
		 * Contains some of the factors of the number being tested.
		 */
		public final BigInteger superFactor2;

		/**
		 * Construct a TestResultMillerRabin object from the given attributes.
		 * 
		 * @param n
		 *            the given number
		 * 
		 * @param isInconclusive
		 *            indicates whether the test was inconclusive
		 * 
		 * @param witness
		 *            the given potential witness
		 * 
		 * @param superFactor1
		 *            the first given super factor
		 * 
		 * @param superFactor2
		 *            the second given super factor
		 */
		public TestResultMillerRabin(BigInteger n, boolean isInconclusive, BigInteger witness, BigInteger superFactor1,
				BigInteger superFactor2) {
			this.n = n;
			this.isInconclusive = isInconclusive;
			this.witness = witness;
			this.superFactor1 = superFactor1;
			this.superFactor2 = superFactor2;
		}

		/**
		 * <code>this(n, isInconclusive, witness, superFactor1, null)</code>.
		 * 
		 * @param n
		 *            the given number
		 * 
		 * @param isInconclusive
		 *            indicates whether the test was inconclusive
		 * 
		 * @param witness
		 *            the given potential witness
		 * 
		 * @param superFactor1
		 *            the first given super factor
		 */
		public TestResultMillerRabin(BigInteger n, boolean isInconclusive, BigInteger witness,
				BigInteger superFactor1) {
			this(n, isInconclusive, witness, superFactor1, null);
		}

		/**
		 * <code>this(n, isInconclusive, witness, null)</code>.
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
		public TestResultMillerRabin(BigInteger n, boolean isInconclusive, BigInteger witness) {
			this(n, isInconclusive, witness, null);
		}

		/**
		 * Copy ctor.
		 * 
		 * @param other
		 *            the given TestResultMillerRabin object
		 * 
		 * @throws NullPointerException
		 *             If <code>other == null</code>
		 */
		public TestResultMillerRabin(TestResultMillerRabin other) throws NullPointerException {
			this(other.n, other.isInconclusive, other.witness, other.superFactor1, other.superFactor2);
		}

		/**
		 * @return <code>!this.isInconclusive</code>.
		 */
		public boolean isComposite() {
			return (!this.isInconclusive);
		}
	}
}
