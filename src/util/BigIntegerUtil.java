package util;

import java.math.BigInteger;

/**
 * Utility BigInteger methods in addition to Java's BigInteger class.
 * 
 * @author Ashkan Moatamed
 */
public class BigIntegerUtil {
	/**
	 * Dependencies: <code>
	 * 		1. util.InvalidModulusException
	 * 		2. util.UndefinedInverseException
	 * </code>
	 */

	/**
	 * <code>BigInteger.valueOf(-10)</code>.
	 */
	public static final BigInteger NEGATIVE_TEN = BigInteger.valueOf(-10);

	/**
	 * <code>BigInteger.valueOf(-5)</code>.
	 */
	public static final BigInteger NEGATIVE_FIVE = BigInteger.valueOf(-5);

	/**
	 * <code>BigInteger.valueOf(-2)</code>.
	 */
	public static final BigInteger NEGATIVE_TWO = BigInteger.valueOf(-2);

	/**
	 * <code>BigInteger.valueOf(-1)</code>.
	 */
	public static final BigInteger NEGATIVE_ONE = BigInteger.valueOf(-1);

	/**
	 * <code>BigInteger.valueOf(2)</code>.
	 */
	public static final BigInteger TWO = BigInteger.valueOf(2);

	/**
	 * <code>BigInteger.valueOf(5)</code>.
	 */
	public static final BigInteger FIVE = BigInteger.valueOf(5);

	/**
	 * Prevent instantiation.
	 */
	private BigIntegerUtil() {
		// Empty by design.
	}

	/**
	 * @param n
	 *            the given BigInteger object
	 * 
	 * @return <code>n.signum() == -1</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>n == null</code>
	 */
	public static boolean isNegative(BigInteger n) throws NullPointerException {
		return (n.signum() == -1);
	}

	/**
	 * @param n
	 *            the given BigInteger object
	 * 
	 * @param args
	 *            any number of BigInteger object(s)
	 * 
	 * @throws NullPointerException
	 *             If
	 *             <code>(n == null) || (args == null) || ((valid i) implies (args[i] == null))</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(!BigIntegerUtil.isNegative(n)) || ((valid i) implies (!BigIntegerUtil.isNegative(args[i])))</code>
	 */
	public static void ensureNegative(BigInteger n, BigInteger... args)
			throws NullPointerException, IllegalArgumentException {
		if (!BigIntegerUtil.isNegative(n)) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i != args.length; ++i) {
			if (!BigIntegerUtil.isNegative(args[i])) {
				throw new IllegalArgumentException();
			}
		}
	}

	/**
	 * @param n
	 *            the given BigInteger object
	 * 
	 * @return <code>n.signum() == 0</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>n == null</code>
	 */
	public static boolean isZero(BigInteger n) throws NullPointerException {
		return (n.signum() == 0);
	}

	/**
	 * @param n
	 *            the given BigInteger object
	 * 
	 * @param args
	 *            any number of BigInteger object(s)
	 * 
	 * @throws NullPointerException
	 *             If
	 *             <code>(n == null) || (args == null) || ((valid i) implies (args[i] == null))</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(!BigIntegerUtil.isZero(n)) || ((valid i) implies (!BigIntegerUtil.isZero(args[i])))</code>
	 */
	public static void ensureZero(BigInteger n, BigInteger... args)
			throws NullPointerException, IllegalArgumentException {
		if (!BigIntegerUtil.isZero(n)) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i != args.length; ++i) {
			if (!BigIntegerUtil.isZero(args[i])) {
				throw new IllegalArgumentException();
			}
		}
	}

	/**
	 * @param n
	 *            the given BigInteger object
	 * 
	 * @return <code>n.signum() == 1</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>n == null</code>
	 */
	public static boolean isPositive(BigInteger n) throws NullPointerException {
		return (n.signum() == 1);
	}

	/**
	 * @param n
	 *            the given BigInteger object
	 * 
	 * @param args
	 *            any number of BigInteger object(s)
	 * 
	 * @throws NullPointerException
	 *             If
	 *             <code>(n == null) || (args == null) || ((valid i) implies (args[i] == null))</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(!BigIntegerUtil.isPositive(n)) || ((valid i) implies (!BigIntegerUtil.isPositive(args[i])))</code>
	 */
	public static void ensurePositive(BigInteger n, BigInteger... args)
			throws NullPointerException, IllegalArgumentException {
		if (!BigIntegerUtil.isPositive(n)) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i != args.length; ++i) {
			if (!BigIntegerUtil.isPositive(args[i])) {
				throw new IllegalArgumentException();
			}
		}
	}

	/**
	 * @param n
	 *            the given BigInteger object
	 * 
	 * @return <code>!n.testBit(0)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>n == null</code>
	 */
	public static boolean isEven(BigInteger n) throws NullPointerException {
		return (!n.testBit(0));
	}

	/**
	 * @param n
	 *            the given BigInteger object
	 * 
	 * @param args
	 *            any number of BigInteger object(s)
	 * 
	 * @throws NullPointerException
	 *             If
	 *             <code>(n == null) || (args == null) || ((valid i) implies (args[i] == null))</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(!BigIntegerUtil.isEven(n)) || ((valid i) implies (!BigIntegerUtil.isEven(args[i])))</code>
	 */
	public static void ensureEven(BigInteger n, BigInteger... args)
			throws NullPointerException, IllegalArgumentException {
		if (!BigIntegerUtil.isEven(n)) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i != args.length; ++i) {
			if (!BigIntegerUtil.isEven(args[i])) {
				throw new IllegalArgumentException();
			}
		}
	}

	/**
	 * @param n
	 *            the given BigInteger object
	 * 
	 * @param args
	 *            any number of BigInteger object(s)
	 * 
	 * @throws NullPointerException
	 *             If
	 *             <code>(n == null) || (args == null) || ((valid i) implies (args[i] == null))</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(BigIntegerUtil.isEven(n)) || ((valid i) implies (BigIntegerUtil.isEven(args[i])))</code>
	 */
	public static void ensureOdd(BigInteger n, BigInteger... args)
			throws NullPointerException, IllegalArgumentException {
		if (BigIntegerUtil.isEven(n)) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i != args.length; ++i) {
			if (BigIntegerUtil.isEven(args[i])) {
				throw new IllegalArgumentException();
			}
		}
	}

	/**
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.length == 3</code> <br>
	 * Postcondition: <code>Result[2] == gcd(a, b)</code> <br>
	 * Postcondition: <code>Result[0] * a + Result[1] * b == gcd(a, b)</code>
	 * 
	 * @param a
	 *            the first given number
	 * 
	 * @param b
	 *            the second given number
	 * 
	 * @return The resulting BigInteger array.
	 * 
	 * @throws NullPointerException
	 *             If <code>(a == null) || (b == null)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(a < 0) || (b < 0)</code>
	 */
	public static BigInteger[] gcdExtended(BigInteger a, BigInteger b)
			throws NullPointerException, ArithmeticException {
		final int sign_a = a.signum(), sign_b = b.signum();
		if ((sign_a == -1) || (sign_b == -1)) {
			throw new ArithmeticException();
		}
		// (a >= 0) && (b >= 0)

		// Handle the special cases where at least one of the two numbers is 0.
		if (sign_a == 0) {
			if (sign_b == 0) {
				// 0 * 0 + 0 * 0 == 0
				return new BigInteger[] { BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO };
			}
			// b != 0

			// 0 * a + 1 * b == b == gcd(0, b)
			return new BigInteger[] { BigInteger.ZERO, BigInteger.ONE, b };
		} else if (sign_b == 0) { // a != 0
			// 1 * a + 0 * b == a == gcd(a, 0)
			return new BigInteger[] { BigInteger.ONE, BigInteger.ZERO, a };
		}
		// (a != 0) && (b != 0)
		// i.e., (a > 0) && (b > 0)

		// Algorithm is from Introduction to Mathematical Cryptography 2nd Edition Exercise 1.12.
		BigInteger gcd = a, x = BigInteger.ONE;
		{
			BigInteger u = BigInteger.ZERO, v = b, tmp = null;
			BigInteger[] qr = null;
			do {
				// Compute the quotient and the remainder.
				qr = gcd.divideAndRemainder(v);
				// (qr[0] == gcd / v) && (qr[1] == gcd % v)

				// Update all of the variables.
				tmp = x.subtract(qr[0].multiply(u));
				gcd = v;
				x = u;
				u = tmp;
				v = qr[1];
			} while (v.signum() != 0);
		}
		// x * a + y * b == gcd where y == (gcd - x * a) / b
		return new BigInteger[] { x, gcd.subtract(x.multiply(a)).divide(b), gcd };
	}

	/**
	 * @param a
	 *            the first given number
	 * 
	 * @param b
	 *            the second given number
	 * 
	 * @return The least common multiple of the two numbers.
	 * 
	 * @throws NullPointerException
	 *             If <code>(a == null) || (b == null)</code>
	 */
	public static BigInteger lcm(BigInteger a, BigInteger b) throws NullPointerException {
		// lcm(0, b) == 0 == lcm(a, 0)
		if ((a.signum() == 0) || (b.signum() == 0)) {
			return BigInteger.ZERO;
		}
		// (a != 0) && (b != 0)

		// lcm is non-negative so make a and b non-negative.
		a = a.abs();
		b = a.abs();

		// lcm(a, b) == (a * b) / gcd(a, b)
		return (a.divide(a.gcd(b))).multiply(b);
	}

	/**
	 * Chinese Remainder Theorem. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.length == 2</code> <br>
	 * Postcondition:
	 * <code>Result[0] == n1 * m2 * (m2<sup>-1</sup> (mod m1)) + n2 * m1 * (m1<sup>-1</sup> (mod m2)) (mod m1 * m2)</code>
	 * <br>
	 * Postcondition: <code>Result[1] == m1 * m2</code>
	 * 
	 * @param n1
	 *            the first given number
	 * 
	 * @param m1
	 *            the first given modulus
	 * 
	 * @param n2
	 *            the second given number
	 * 
	 * @param m2
	 *            the second given modulus
	 * 
	 * @param modBefore
	 *            specifies whether <code>n1</code> and <code>n2</code> should be modded by
	 *            <code>m1</code> and <code>m2</code> respectively before applying the formula
	 * 
	 * @param modAfterEveryStep
	 *            specifies whether every intermediate result in the formula should be modded by
	 *            <code>m1 * m2</code>
	 * 
	 * @return The resulting BigInteger array.
	 * 
	 * @throws NullPointerException
	 *             If <code>(n1 == null) || (m1 == null) || (n2 == null) || (m2 == null)</code>
	 * 
	 * @throws InvalidModulusException
	 *             If <code>(m1 <= 0) || (m2 <= 0)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(m1, m2) != 1</code>
	 */
	public static BigInteger[] crt(BigInteger n1, BigInteger m1, BigInteger n2, BigInteger m2, boolean modBefore,
			boolean modAfterEveryStep) throws NullPointerException, InvalidModulusException, UndefinedInverseException {
		if ((n1 == null) || (n2 == null)) {
			throw new NullPointerException();
		} else if ((m1.signum() != 1) || (m2.signum() != 1)) {
			throw new InvalidModulusException();
		}
		// (m1 > 0) && (m2 > 0)

		// Compute the new modulus.
		final BigInteger m = m1.multiply(m2);

		// Find integers x and y such that x * m1 + y * m2 == gcd(m1, m2).
		final BigInteger[] result = BigIntegerUtil.gcdExtended(m1, m2);
		if (!result[2].equals(BigInteger.ONE)) {
			throw new UndefinedInverseException();
		}

		// Mod n1 and n2 before the computation if requested.
		if (modBefore) {
			n1 = n1.mod(m1);
			n2 = n2.mod(m2);
		}

		// Apply the C.R.T. formula for two congruences.
		final BigInteger m1_inverse = result[0].mod(m2), m2_inverse = result[1].mod(m1);
		BigInteger lhs = n1.multiply(m2);
		if (modAfterEveryStep) {
			lhs = lhs.mod(m).multiply(m2_inverse).mod(m);
		} else {
			lhs = lhs.multiply(m2_inverse);
		}
		BigInteger rhs = n2.multiply(m1);
		if (modAfterEveryStep) {
			rhs = rhs.mod(m).multiply(m1_inverse).mod(m);
		} else {
			rhs = rhs.multiply(m1_inverse);
		}
		return new BigInteger[] { lhs.add(rhs).mod(m), m };
	}

	/**
	 * Chinese Remainder Theorem. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.length == 2</code> <br>
	 * Postcondition:
	 * <code>Result[0] == n1 * m2 * (m2<sup>-1</sup> (mod m1)) + n2 * m1 * (m1<sup>-1</sup> (mod m2)) (mod m1 * m2)</code>
	 * <br>
	 * Postcondition: <code>Result[1] == m1 * m2</code>
	 * 
	 * @param n1
	 *            the first given number
	 * 
	 * @param m1
	 *            the first given modulus
	 * 
	 * @param n2
	 *            the second given number
	 * 
	 * @param m2
	 *            the second given modulus
	 * 
	 * @param modBefore
	 *            specifies whether <code>n1</code> and <code>n2</code> should be modded by
	 *            <code>m1</code> and <code>m2</code> respectively before applying the formula
	 * 
	 * @return <code>BigIntegerUtil.crt(n1, m1, n2, m2, modBefore, true)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(n1 == null) || (m1 == null) || (n2 == null) || (m2 == null)</code>
	 * 
	 * @throws InvalidModulusException
	 *             If <code>(m1 <= 0) || (m2 <= 0)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(m1, m2) != 1</code>
	 */
	public static BigInteger[] crt(BigInteger n1, BigInteger m1, BigInteger n2, BigInteger m2, boolean modBefore)
			throws NullPointerException, InvalidModulusException, UndefinedInverseException {
		return BigIntegerUtil.crt(n1, m1, n2, m2, modBefore, true);
	}

	/**
	 * Chinese Remainder Theorem. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.length == 2</code> <br>
	 * Postcondition:
	 * <code>Result[0] == n1 * m2 * (m2<sup>-1</sup> (mod m1)) + n2 * m1 * (m1<sup>-1</sup> (mod m2)) (mod m1 * m2)</code>
	 * <br>
	 * Postcondition: <code>Result[1] == m1 * m2</code>
	 * 
	 * @param n1
	 *            the first given number
	 * 
	 * @param m1
	 *            the first given modulus
	 * 
	 * @param n2
	 *            the second given number
	 * 
	 * @param m2
	 *            the second given modulus
	 * 
	 * @return <code>BigIntegerUtil.crt(n1, m1, n2, m2, true)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(n1 == null) || (m1 == null) || (n2 == null) || (m2 == null)</code>
	 * 
	 * @throws InvalidModulusException
	 *             If <code>(m1 <= 0) || (m2 <= 0)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(m1, m2) != 1</code>
	 */
	public static BigInteger[] crt(BigInteger n1, BigInteger m1, BigInteger n2, BigInteger m2)
			throws NullPointerException, InvalidModulusException, UndefinedInverseException {
		return BigIntegerUtil.crt(n1, m1, n2, m2, true);
	}
}
