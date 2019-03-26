package util;

import java.math.BigInteger;

/**
 * Miller-Rabin's compositeness test implemented in the top-down way.
 * 
 * @author Ashkan Moatamed
 */
public class MillerRabinTopDown {
	/**
	 * Dependencies: <code>
	 * 		1. util.SinglyLinkedList
	 * </code>
	 */

	/**
	 * BigInteger objects are immutable. Therefore, it is "safe" to make the following final attributes
	 * public.
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
	 * Iterator over a singly linked list of all exponents.
	 */
	protected final SinglyLinkedList.SLLIterator<BigInteger> exponents_it;

	/**
	 * Construct a MillerRabinTopDown object from the given number.
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
	public MillerRabinTopDown(BigInteger n) throws NullPointerException, IllegalArgumentException {
		if (n.compareTo(MillerRabinTopDown.TWO) <= 0) { // i.e., n <= 2
			throw new IllegalArgumentException();
		} else if (!n.testBit(0)) { // i.e., BigIntUtil.isEven(n)
			throw new IllegalArgumentException();
		}
		// n is an odd integer greater than 2

		// The following is meant to be an assignment of this.n and this.n_minus_1.
		this.n_minus_1 = (this.n = n).subtract(BigInteger.ONE);

		/**
		 * After extensive experimentations, we have concluded that this version of MillerRabinTopDown which
		 * computes and saves the exponent "on-the-go", has the best overall performance compared to the two
		 * following top-down alternatives: <br>
		 * 1. Not saving the exponents and just computing them whenever required in the test methods. <br>
		 * 2. Computing all exponents in the ctor and then just iterating through the list in the test
		 * methods.
		 */

		// Fill the singly linked list managed by the iterator with only the first exponent.
		this.exponents_it = new SinglyLinkedList<BigInteger>().iterator(true);
		/*
		 * this.n_minus_1 is guaranteed to be even since this.n is enforced to be odd which means that the
		 * division by 2 will not have a remainder.
		 */
		final BigInteger exp = this.n_minus_1.shiftRight(1);
		this.exponents_it.insert(exp);
		this.exponents_it.next();
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
		return ((obj instanceof MillerRabinTopDown) ? this.equals((MillerRabinTopDown) obj) : false);
	}

	/**
	 * @param other
	 *            the given MillerRabinTopDown object
	 * 
	 * @see #equals(Object)
	 */
	public boolean equals(MillerRabinTopDown other) {
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
	 * @return The resulting TestResultMillerRabinTopDown object.
	 */
	protected TestResultMillerRabinTopDown testCoprimeFixedInput(BigInteger base, boolean print) {
		BigInteger r = base.modPow(this.n_minus_1, this.n);
		// Only print if requested.
		if (print) {
			System.out.println("r_0 == " + r + ", exp_0 == " + this.n_minus_1);
		}

		/*
		 * Check to see if the very first calculation resulted in a non-one value which would imply that
		 * this.n is composite by Fermat's test. However, we cannot find the super factors since we cannot
		 * perform the Square-Root test.
		 */
		if (!r.equals(BigInteger.ONE)) {
			// Only print if requested.
			if (print) {
				System.out.println("\n" + base + " is a witness of n's compositeness by Fermat's test.\n");
			}
			return new TestResultMillerRabinTopDown(this.n, false, base);
		}

		/*
		 * Move the iterator to the first position so that this.exponents_it.next() returns the first
		 * element.
		 */
		this.exponents_it.begin();
		BigInteger exp = this.exponents_it.next();
		// Loop until r != 1.
		int i = 1;
		for (/* Already initialized. */; (r = base.modPow(exp, this.n)).equals(BigInteger.ONE); ++i) {
			// Only print if requested.
			if (print) {
				System.out.println("r_" + i + " == 1, exp_" + i + " == " + exp);
			}

			// Check to see if the exponent iterator still has more elements.
			if (this.exponents_it.hasNext()) {
				// Retrieve the next exponent from the iterator.
				exp = this.exponents_it.next();
			} else {
				// Check to see if the exponent is even.
				if (exp.testBit(0)) { // i.e., !BigIntUtil.isEven(exp)
					// Exponent is odd but r is still 1 therefore the test is inconclusive.
					// Only print if requested.
					if (print) {
						System.out.println("\nTest is inconclusive with base " + base
								+ " since the exponent has reached an odd number but the remainder is still 1.\n");
					}
					return new TestResultMillerRabinTopDown(this.n, true, base);
				}
				// !exp.testBit(0)
				// i.e., BigIntUtil.isEven(exp)
				exp = exp.shiftRight(1);
				// Save the next exponent so that it isn't recomputed later and then advance the iterator.
				this.exponents_it.insert(exp);
				this.exponents_it.next();
			}
		}
		// Only print if requested.
		if (print) {
			System.out.println("r_" + i + " == " + r + ", exp_" + i + " == " + exp);
		}

		// Check to see if r is -1 (mod n).
		if (r.equals(this.n_minus_1)) {
			// Only print if requested.
			if (print) {
				System.out.println(
						"\nTest is inconclusive with base " + base + " since the final remainder is -1 (mod n).\n");
			}
			return new TestResultMillerRabinTopDown(this.n, true, base);
		}

		// Apply the Square-Root test to r, 1, and this.n.
		final BigInteger superFactor1 = this.n.gcd(r.subtract(BigInteger.ONE));
		final BigInteger superFactor2 = this.n.gcd(r.add(BigInteger.ONE));
		// Only print if requested.
		if (print) {
			System.out.println("\ngcd(n, " + r + " - 1) == " + superFactor1);
			System.out.println("and\ngcd(n, " + r + " + 1) == " + superFactor2);
			System.out.println("\n" + base + " is a witness of n's compositeness.\n");
		}
		return new TestResultMillerRabinTopDown(this.n, false, base, superFactor1, superFactor2);
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
	 * @return The resulting TestResultMillerRabinTopDown object.
	 * 
	 * @throws NullPointerException
	 *             If <code>base == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(base <= 1) || (this.n_minus_1 <= base) || (gcd(this.n, base) != 1)</code>
	 */
	public TestResultMillerRabinTopDown testCoprime(BigInteger base, boolean print)
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
	public TestResultMillerRabinTopDown testCoprime(BigInteger base)
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
	 * @return The resulting TestResultMillerRabinTopDown object.
	 */
	protected TestResultMillerRabinTopDown testFixedInput(BigInteger base, boolean print) {
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
		return new TestResultMillerRabinTopDown(this.n, false, base, gcd, this.n.divide(gcd));
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
	 * @return The resulting TestResultMillerRabinTopDown object.
	 * 
	 * @throws NullPointerException
	 *             If <code>base == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(base <= 1) || (this.n_minus_1 <= base)</code>
	 */
	public TestResultMillerRabinTopDown test(BigInteger base, boolean print)
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
	public TestResultMillerRabinTopDown test(BigInteger base) throws NullPointerException, IllegalArgumentException {
		return this.test(base, false);
	}

	// --------------------------------------------------
	// Nested wrapper class.
	// --------------------------------------------------

	/**
	 * Simple wrapper for compositeness test result. Saves the number, the test result, the witness, and
	 * the two super factors. <br>
	 * 
	 * It's main usage is for the return type of <code>MillerRabinTopDown::test</code> and
	 * <code>MillerRabinTopDown::testCoprime</code>.
	 * 
	 * @author Ashkan Moatamed
	 */
	public static class TestResultMillerRabinTopDown {
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
		 * Construct a TestResultMillerRabinTopDown object from the given attributes.
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
		public TestResultMillerRabinTopDown(BigInteger n, boolean isInconclusive, BigInteger witness,
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
		public TestResultMillerRabinTopDown(BigInteger n, boolean isInconclusive, BigInteger witness) {
			this(n, isInconclusive, witness, null, null);
		}

		/**
		 * Copy ctor.
		 * 
		 * @param other
		 *            the given TestResultMillerRabinTopDown object
		 * 
		 * @throws NullPointerException
		 *             If <code>other == null</code>
		 */
		public TestResultMillerRabinTopDown(TestResultMillerRabinTopDown other) throws NullPointerException {
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
