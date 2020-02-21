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
	 * The number being tested.
	 */
	private BigInteger n;

	/**
	 * <code>this.n.subtract(BigInteger.ONE)</code>.
	 */
	private BigInteger n_minus_1;

	/**
	 * <code>this.n_minus_1.getLowestSetBit()</code>.
	 */
	public final int max_power_of_2;

	/**
	 * <code>this.n_minus_1.shiftRight(this.max_power_of_2)</code>.
	 */
	private BigInteger max_odd_factor;

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
	 *             If <code>BigIntUtil.isEven(n) || (n <= 1)</code>
	 */
	public MillerRabinBottomUp(BigInteger n) throws NullPointerException, IllegalArgumentException {
		if (!n.testBit(0)) { // i.e., BigIntUtil.isEven(n)
			throw new IllegalArgumentException();
		} else if (n.compareTo(BigInteger.ONE) <= 0) { // i.e., n <= 1
			throw new IllegalArgumentException();
		}
		// n is an odd integer greater than 1

		// The following is meant to be an assignment of this.n and this.n_minus_1.
		this.n_minus_1 = (this.n = n).subtract(BigInteger.ONE);
		/*
		 * this.n_minus_1 is guaranteed to be even since this.n is enforced to be odd which means that
		 * dividing it by the largest power of 2 that it contains, will not have a remainder.
		 */
		// The following is meant to be an assignment of this.max_power_of_2 and this.max_odd_factor.
		this.max_odd_factor = this.n_minus_1.shiftRight(this.max_power_of_2 = this.n_minus_1.getLowestSetBit());
	}

	/**
	 * Since this class is immutable, there is no need for a copy ctor.
	 */

	@Override
	protected Object clone() throws CloneNotSupportedException { // semi-copy
		throw new CloneNotSupportedException();
	}

	@Override
	protected void finalize() { // semi-dtor
		this.n = null;
		this.n_minus_1 = null;
		this.max_odd_factor = null;
	}

	/**
	 * @return <code>this.n</code>.
	 */
	public BigInteger getN() {
		return this.n;
	}

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
	 * Precondition: <code>base != null</code> <br>
	 * Precondition: <code>(1 < base) && (base < this.n_minus_1)</code> <br>
	 * Precondition: <code>gcd(this.n, base) == 1</code>
	 * 
	 * @param base
	 *            the given base
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return The resulting TestResultMillerRabinBottomUp object.
	 */
	protected TestResultMillerRabinBottomUp testCoprimeFixedInput(BigInteger base, boolean print) {
		BigInteger r = base.modPow(this.max_odd_factor, this.n);
		// Only print if requested.
		if (print) {
			System.out.println("r_" + this.max_power_of_2 + " == " + r + ", exp_" + this.max_power_of_2 + " == "
					+ this.max_odd_factor + " * 2^0");
		}

		/*
		 * Check to see if the very first calculation resulted in a one and thus all of the other remainders
		 * are also one which makes the test inconclusive.
		 */
		if (r.equals(BigInteger.ONE)) { // i.e., r == 1
			// Only print if requested.
			if (print) {
				System.out.println(
						"\nTest is inconclusive with base " + base + " since the final remainder is 1 (mod n).\n");
			}
			return new TestResultMillerRabinBottomUp(this.n, true, base);
		}
		// r != 1

		BigInteger prev_r = null;
		// Loop until i == -1.
		int i = this.max_power_of_2 - 1;
		do {
			prev_r = r; // Save the previous remainder for the Square-Root test.
			r = r.multiply(r).mod(this.n); // Square r (mod this.n).
			// Only print if requested.
			if (print) {
				System.out.println("r_" + i + " == " + r + ", exp_" + i + " == " + this.max_odd_factor + " * 2^"
						+ (this.max_power_of_2 - i));
			}

			// Check to see if r is 1 (mod n).
			if (r.equals(BigInteger.ONE)) { // i.e., r == 1
				// Check to see if prev_r is -1 (mod n).
				if (prev_r.equals(this.n_minus_1)) { // i.e., prev_r == n - 1
					// Only print if requested.
					if (print) {
						System.out.println("\nTest is inconclusive with base " + base
								+ " since the final remainder is -1 (mod n).\n");
					}
					return new TestResultMillerRabinBottomUp(this.n, true, base);
				}
				// prev_r != n - 1

				// Apply the Square-Root test to prev_r, 1, and this.n.
				final BigInteger superFactor1 = this.n.gcd(prev_r.subtract(BigInteger.ONE));
				final BigInteger superFactor2 = this.n.gcd(prev_r.add(BigInteger.ONE));
				// Only print if requested.
				if (print) {
					System.out.println("\ngcd(n, " + prev_r + " - 1) == " + superFactor1);
					System.out.println("and\ngcd(n, " + prev_r + " + 1) == " + superFactor2);
					System.out.println("\n" + base + " is a witness of n's compositeness.\n");
				}
				return new TestResultMillerRabinBottomUp(this.n, false, base, superFactor1, superFactor2);
			}
			// r != 1
		} while (--i != -1);
		/*
		 * At this point, we know that i == -1 and that r != 1, thus this.n is composite by Fermat's test.
		 * However, we cannot find the super factors since we cannot perform the Square-Root test.
		 */
		// Only print if requested.
		if (print) {
			System.out.println("\n" + base + " is a witness of n's compositeness by Fermat's test.\n");
		}
		return new TestResultMillerRabinBottomUp(this.n, false, base);
	}

	/**
	 * Perform Miller-Rabin's compositeness test on <code>this.n</code> with the given base.
	 * 
	 * @param base
	 *            the given base
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return The resulting TestResultMillerRabinBottomUp object.
	 * 
	 * @throws NullPointerException
	 *             If <code>base == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(base <= 1) || (this.n_minus_1 <= base) || (gcd(this.n, base) != 1)</code>
	 */
	public TestResultMillerRabinBottomUp testCoprime(BigInteger base, boolean print)
			throws IllegalArgumentException, NullPointerException {
		if (base.compareTo(BigInteger.ONE) <= 0) { // i.e., base <= 1
			throw new IllegalArgumentException();
		} else if (this.n_minus_1.compareTo(base) <= 0) { // i.e., this.n_minus_1 <= base
			throw new IllegalArgumentException();
		} else if (!this.n.gcd(base).equals(BigInteger.ONE)) { // i.e., gcd(this.n, base) != 1
			throw new IllegalArgumentException();
		}
		// (1 < base) && (base < this.n_minus_1) && (gcd(this.n, base) == 1)
		return this.testCoprimeFixedInput(base, print);
	}

	/**
	 * Perform Miller-Rabin's compositeness test on <code>this.n</code> with the given base.
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
	 *             If <code>(base <= 1) || (this.n_minus_1 <= base) || (gcd(this.n, base) != 1)</code>
	 */
	public TestResultMillerRabinBottomUp testCoprime(BigInteger base)
			throws IllegalArgumentException, NullPointerException {
		return this.testCoprime(base, false);
	}

	/**
	 * Perform Miller-Rabin's compositeness test on <code>this.n</code> with the given base. <br>
	 * Precondition: <code>base != null</code> <br>
	 * Precondition: <code>(1 < base) && (base < this.n_minus_1)</code> <br>
	 * 
	 * @param base
	 *            the given base
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return The resulting TestResultMillerRabinBottomUp object.
	 */
	protected TestResultMillerRabinBottomUp testFixedInput(BigInteger base, boolean print) {
		final BigInteger gcd = this.n.gcd(base);
		if (gcd.equals(BigInteger.ONE)) { // i.e., gcd(this.n, base) == 1
			return this.testCoprimeFixedInput(base, print);
		}
		// gcd(this.n, base) != 1

		// Only print if requested.
		if (print) {
			System.out.println("gcd(n, " + base + ") == " + gcd + " != 1.");
			System.out.println("Therefore, " + base + " is a witness of n's compositeness.\n");
		}
		return new TestResultMillerRabinBottomUp(this.n, false, base, gcd, this.n.divide(gcd));
	}

	/**
	 * Perform Miller-Rabin's compositeness test on <code>this.n</code> with the given base.
	 * 
	 * @param base
	 *            the given base
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return The resulting TestResultMillerRabinBottomUp object.
	 * 
	 * @throws NullPointerException
	 *             If <code>base == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(base <= 1) || (this.n_minus_1 <= base)</code>
	 */
	public TestResultMillerRabinBottomUp test(BigInteger base, boolean print)
			throws NullPointerException, IllegalArgumentException {
		if (base.compareTo(BigInteger.ONE) <= 0) { // i.e., base <= 1
			throw new IllegalArgumentException();
		} else if (this.n_minus_1.compareTo(base) <= 0) { // i.e., this.n_minus_1 <= base
			throw new IllegalArgumentException();
		}
		// (1 < base) && (base < this.n_minus_1)
		return this.testFixedInput(base, print);
	}

	/**
	 * Perform Miller-Rabin's compositeness test on <code>this.n</code> with the given base.
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
	 *             If <code>(base <= 1) || (this.n_minus_1 <= base)</code>
	 */
	public TestResultMillerRabinBottomUp test(BigInteger base) throws NullPointerException, IllegalArgumentException {
		return this.test(base, false);
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
		private BigInteger n;

		/**
		 * Indicates whether the test was inconclusive.
		 */
		public final boolean isInconclusive;

		/**
		 * A potential witness of the number being tested. An actual witness if
		 * <code>!this.isInconclusive</code>.
		 */
		private BigInteger witness;

		/**
		 * Contains some of the factors of the number being tested.
		 */
		private BigInteger superFactor1;

		/**
		 * Contains some of the factors of the number being tested.
		 */
		private BigInteger superFactor2;

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
		 * Since this class is immutable, there is no need for a copy ctor.
		 */

		@Override
		protected Object clone() throws CloneNotSupportedException { // semi-copy
			throw new CloneNotSupportedException();
		}

		@Override
		protected void finalize() { // semi-dtor
			this.n = null;
			this.witness = null;
			this.superFactor1 = null;
			this.superFactor2 = null;
		}

		/**
		 * @return <code>this.n</code>.
		 */
		public BigInteger getN() {
			return this.n;
		}

		/**
		 * @return <code>!this.isInconclusive</code>.
		 */
		public boolean isComposite() {
			return (!this.isInconclusive);
		}

		/**
		 * @return <code>this.witness</code>.
		 */
		public BigInteger getWitness() {
			return this.witness;
		}

		/**
		 * @return <code>this.superFactor1</code>.
		 */
		public BigInteger getSuperFactor1() {
			return this.superFactor1;
		}

		/**
		 * @return <code>this.superFactor2</code>.
		 */
		public BigInteger getSuperFactor2() {
			return this.superFactor2;
		}
	}
}
