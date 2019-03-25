package util;

import java.math.BigInteger;

/**
 * Miller-Rabin's compositeness test implemented in the bottom-up way.
 * 
 * @author Ashkan Moatamed
 */
public class MillerRabinBottomUp {
	/**
	 * No dependencies.
	 */

	/**
	 * BigInteger objects and int primitives are immutable. Therefore, it is "safe" to make the
	 * following final attributes public.
	 */

	/**
	 * <code>BigInteger.valueOf(2)</code>.
	 */
	public static final BigInteger TWO = BigInteger.valueOf(2L);

	/**
	 * The number being tested.
	 */
	public final BigInteger n;

	/**
	 * <code>this.n.subtract(BigInteger.ONE)</code>.
	 */
	public final BigInteger n_minus_1;

	/**
	 * <code>this.n_minus_1.getLowestSetBit()</code>.
	 */
	public final int max_power_of_2;

	/**
	 * <code>this.n_minus_1.shiftRight(this.max_power_of_2)</code>.
	 */
	public final BigInteger max_odd_factor;

	/**
	 * Construct a MillerRabinBottomUp object from the given number.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @throws NullPointerException
	 *             If <code>n == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(n <= 2) || (BigIntUtil.isEven(n))</code>
	 */
	public MillerRabinBottomUp(BigInteger n) throws NullPointerException, IllegalArgumentException {
		if (n.compareTo(MillerRabinBottomUp.TWO) <= 0) { // i.e., n <= 2
			throw new IllegalArgumentException();
		} else if (!n.testBit(0)) { // i.e., BigIntUtil.isEven(n)
			throw new IllegalArgumentException();
		}
		// n is an odd integer greater than 2

		// The following is meant to be an assignment of this.n and this.n_minus_1.
		this.n_minus_1 = (this.n = n).subtract(BigInteger.ONE);
		// The following is meant to be an assignment of this.max_power_of_2 and this.max_odd_factor.
		this.max_odd_factor = this.n_minus_1.shiftRight(this.max_power_of_2 = this.n_minus_1.getLowestSetBit());
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
		return ((obj instanceof MillerRabinBottomUp) ? this.equals((MillerRabinBottomUp) obj) : false);
	}

	/**
	 * @param other
	 *            the given MillerRabinBottomUp object
	 * 
	 * @see #equals(Object)
	 */
	public boolean equals(MillerRabinBottomUp other) {
		return ((other == null) ? false : ((this == other) ? true : this.n.equals(other.n)));
	}

	/**
	 * Perform Miller-Rabin's compositeness test on <code>this.n</code> with the given base. <br>
	 * Precondition: <code>b != null</code> <br>
	 * Precondition: <code>(1 < b) && (b < this.n_minus_1)</code> <br>
	 * Precondition: <code>gcd(this.n, b) == 1</code>
	 * 
	 * @param b
	 *            the given base
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return The resulting TestResultMillerRabinBottomUp object.
	 */
	protected TestResultMillerRabinBottomUp testCoprimeFixedInput(BigInteger b, boolean print) {
		BigInteger r = b.modPow(this.max_odd_factor, this.n);
		// Only print if requested.
		if (print) {
			System.out.println("r_" + this.max_power_of_2 + " == " + r + ", exp_" + this.max_power_of_2 + " == "
					+ this.max_odd_factor + " * 2^0");
		}

		/*
		 * Check to see if the very first calculation resulted in a one and thus all other remainder are
		 * also one which is why the test is inconclusive.
		 */
		if (r.equals(BigInteger.ONE)) {
			// Only print if requested.
			if (print) {
				System.out.println(
						"\nTest is inconclusive with base " + b + " since the final remainder is 1 (mod n).\n");
			}
			return new TestResultMillerRabinBottomUp(this.n, true, b);
		}

		BigInteger prev_r = null;
		// Loop until i == -1.
		int i = this.max_power_of_2 - 1;
		do {
			// Check to see if r is -1 (mod n).
			if (r.equals(this.n_minus_1)) {
				// Only print if requested.
				if (print) {
					System.out.println(
							"\nTest is inconclusive with base " + b + " since the final remainder is -1 (mod n).\n");
				}
				return new TestResultMillerRabinBottomUp(this.n, true, b);
			}

			prev_r = r; // Save the previous remainder for the Square-Root test.
			r = r.multiply(r).mod(this.n); // Square r (mod this.n).
			// Only print if requested.
			if (print) {
				System.out.println("r_" + i + " == " + r + ", exp_" + i + " == " + this.max_odd_factor + " * 2^"
						+ (this.max_power_of_2 - i));
			}

			// Check to see if r is 1 (mod n).
			if (r.equals(BigInteger.ONE)) {
				// Apply the Square-Root test to prev_r, 1, and this.n.
				final BigInteger superFactor1 = this.n.gcd(prev_r.subtract(BigInteger.ONE));
				final BigInteger superFactor2 = this.n.gcd(prev_r.add(BigInteger.ONE));
				// Only print if requested.
				if (print) {
					System.out.println("\ngcd(n, " + prev_r + " - 1) == " + superFactor1);
					System.out.println("and\ngcd(n, " + prev_r + " + 1) == " + superFactor2);
					System.out.println("\n" + b + " is a witness of n's compositeness.\n");
				}
				return new TestResultMillerRabinBottomUp(this.n, false, b, superFactor1, superFactor2);
			}
		} while (--i != -1);
		/*
		 * At this point, we know that i == -1 and that r != 1 thus this.n is composite by Fermat's test.
		 * However, we cannot find the super factors since we cannot perform the Square-Root test.
		 */
		// Only print if requested.
		if (print) {
			System.out.println("\n" + b + " is a witness of n's compositeness by Fermat's test.\n");
		}
		return new TestResultMillerRabinBottomUp(this.n, false, b);
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
	 * @return The resulting TestResultMillerRabinBottomUp object.
	 * 
	 * @throws NullPointerException
	 *             If <code>b == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(b <= 1) || (this.n_minus_1 <= b) || (gcd(this.n, b) != 1)</code>
	 */
	public TestResultMillerRabinBottomUp testCoprime(BigInteger b, boolean print)
			throws IllegalArgumentException, NullPointerException {
		if (b.compareTo(BigInteger.ONE) <= 0) { // i.e., b <= 1
			throw new IllegalArgumentException();
		} else if (this.n_minus_1.compareTo(b) <= 0) { // i.e., this.n_minus_1 <= b
			throw new IllegalArgumentException();
		} else if (!this.n.gcd(b).equals(BigInteger.ONE)) { // i.e., gcd(this.n, b) != 1
			throw new IllegalArgumentException();
		}
		// (1 < b) && (b < this.n_minus_1) && (gcd(this.n, b) == 1)
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
	 *             If <code>(b <= 1) || (this.n_minus_1 <= b) || (gcd(this.n, b) != 1)</code>
	 */
	public TestResultMillerRabinBottomUp testCoprime(BigInteger b)
			throws IllegalArgumentException, NullPointerException {
		return this.testCoprime(b, false);
	}

	/**
	 * Perform Miller-Rabin's compositeness test on <code>this.n</code> with the given base. <br>
	 * Precondition: <code>b != null</code> <br>
	 * Precondition: <code>(1 < b) && (b < this.n_minus_1)</code> <br>
	 * 
	 * @param b
	 *            the given base
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return The resulting TestResultMillerRabinBottomUp object.
	 */
	protected TestResultMillerRabinBottomUp testFixedInput(BigInteger b, boolean print) {
		final BigInteger gcd = this.n.gcd(b);
		if (gcd.equals(BigInteger.ONE)) { // i.e., gcd(this.n, b) == 1
			return this.testCoprimeFixedInput(b, print);
		}
		// gcd(this.n, b) != 1

		// Only print if requested.
		if (print) {
			System.out.println("gcd(n, " + b + ") == " + gcd + " != 1.");
			System.out.println("Therefore, " + b + " is a witness of n's compositeness.\n");
		}
		return new TestResultMillerRabinBottomUp(this.n, false, b, gcd, this.n.divide(gcd));
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
	 * @return The resulting TestResultMillerRabinBottomUp object.
	 * 
	 * @throws NullPointerException
	 *             If <code>b == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(b <= 1) || (this.n_minus_1 <= b)</code>
	 */
	public TestResultMillerRabinBottomUp test(BigInteger b, boolean print)
			throws NullPointerException, IllegalArgumentException {
		if (b.compareTo(BigInteger.ONE) <= 0) { // i.e., b <= 1
			throw new IllegalArgumentException();
		} else if (this.n_minus_1.compareTo(b) <= 0) { // i.e., this.n_minus_1 <= b
			throw new IllegalArgumentException();
		}
		// (1 < b) && (b < this.n_minus_1)
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
	 *             If <code>(b <= 1) || (this.n_minus_1 <= b)</code>
	 */
	public TestResultMillerRabinBottomUp test(BigInteger b) throws NullPointerException, IllegalArgumentException {
		return this.test(b, false);
	}

	// --------------------------------------------------
	// Nested wrapper class.
	// --------------------------------------------------

	/**
	 * Simple wrapper for compositeness test result. Saves the number, the test result, the witness, and
	 * the two super factors. <br>
	 * 
	 * It's main usage is for the return type of <code>MillerRabinBottomUp::test</code> and
	 * <code>MillerRabinBottomUp::testCoprime</code>.
	 * 
	 * @author Ashkan Moatamed
	 */
	public static class TestResultMillerRabinBottomUp {
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
		 * Construct a TestResultMillerRabinBottomUp object from the given attributes.
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
		public TestResultMillerRabinBottomUp(BigInteger n, boolean isInconclusive, BigInteger witness,
				BigInteger superFactor1, BigInteger superFactor2) {
			this.n = n;
			this.isInconclusive = isInconclusive;
			this.witness = witness;
			this.superFactor1 = superFactor1;
			this.superFactor2 = superFactor2;
		}

		/**
		 * <code>this(n, isInconclusive, witness, null, null)</code>.
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
		public TestResultMillerRabinBottomUp(BigInteger n, boolean isInconclusive, BigInteger witness) {
			this(n, isInconclusive, witness, null, null);
		}

		/**
		 * Copy ctor.
		 * 
		 * @param other
		 *            the given TestResultMillerRabinBottomUp object
		 * 
		 * @throws NullPointerException
		 *             If <code>other == null</code>
		 */
		public TestResultMillerRabinBottomUp(TestResultMillerRabinBottomUp other) throws NullPointerException {
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