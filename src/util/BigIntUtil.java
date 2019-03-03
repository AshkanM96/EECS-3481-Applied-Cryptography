package util;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Utility BigInteger methods in addition to Java's BigInteger class.
 * 
 * @author Ashkan Moatamed
 */
public class BigIntUtil {
	/**
	 * Dependencies: <code>
	 * 		1. util.InvalidModulusException
	 * 		2. util.UndefinedInverseException
	 * </code>
	 */

	/**
	 * <code>BigInteger.valueOf(-10)</code>.
	 */
	public static final BigInteger NEG_TEN = BigInteger.valueOf(-10L);

	/**
	 * <code>BigInteger.valueOf(-7)</code>.
	 */
	public static final BigInteger NEG_SEVEN = BigInteger.valueOf(-7L);

	/**
	 * <code>BigInteger.valueOf(-5)</code>.
	 */
	public static final BigInteger NEG_FIVE = BigInteger.valueOf(-5L);

	/**
	 * <code>BigInteger.valueOf(-3)</code>.
	 */
	public static final BigInteger NEG_THREE = BigInteger.valueOf(-3L);

	/**
	 * <code>BigInteger.valueOf(-2)</code>.
	 */
	public static final BigInteger NEG_TWO = BigInteger.valueOf(-2L);

	/**
	 * <code>BigInteger.valueOf(-1)</code>.
	 */
	public static final BigInteger NEG_ONE = BigInteger.valueOf(-1L);

	/**
	 * <code>BigInteger.ZERO</code>.
	 */
	public static final BigInteger ZERO = BigInteger.ZERO;

	/**
	 * <code>BigInteger.ONE</code>.
	 */
	public static final BigInteger ONE = BigInteger.ONE;

	/**
	 * <code>BigInteger.valueOf(2)</code>.
	 */
	public static final BigInteger TWO = BigInteger.valueOf(2L);

	/**
	 * <code>BigInteger.valueOf(3)</code>.
	 */
	public static final BigInteger THREE = BigInteger.valueOf(3L);

	/**
	 * <code>BigInteger.valueOf(5)</code>.
	 */
	public static final BigInteger FIVE = BigInteger.valueOf(5L);

	/**
	 * <code>BigInteger.valueOf(7)</code>.
	 */
	public static final BigInteger SEVEN = BigInteger.valueOf(7L);

	/**
	 * <code>BigInteger.TEN</code>.
	 */
	public static final BigInteger TEN = BigInteger.TEN;

	/**
	 * Prevent instantiation.
	 */
	private BigIntUtil() {
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
		return (n.signum() == -1); // i.e., n < 0
	}

	/**
	 * @param n
	 *            the given BigInteger object
	 * 
	 * @param args
	 *            any number of BigInteger objects
	 * 
	 * @throws NullPointerException
	 *             If
	 *             <code>(n == null) || (args == null) || ((valid i) implies (args[i] == null))</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(n >= 0) || ((valid i) implies (args[i] >= 0))</code>
	 */
	public static void ensureNegative(BigInteger n, BigInteger... args)
			throws NullPointerException, IllegalArgumentException {
		if (n.signum() != -1) { // i.e., n >= 0
			throw new IllegalArgumentException();
		}
		for (int i = 0; i != args.length; ++i) {
			if (args[i].signum() != -1) { // i.e., args[i] >= 0
				throw new IllegalArgumentException();
			}
		}
	}

	/**
	 * @param n
	 *            the given BigInteger object
	 * 
	 * @param args
	 *            any number of BigInteger objects
	 * 
	 * @throws NullPointerException
	 *             If
	 *             <code>(n == null) || (args == null) || ((valid i) implies (args[i] == null))</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(n < 0) || ((valid i) implies (args[i] < 0))</code>
	 */
	public static void ensureNonNegative(BigInteger n, BigInteger... args)
			throws NullPointerException, IllegalArgumentException {
		if (n.signum() == -1) { // i.e., n < 0
			throw new IllegalArgumentException();
		}
		for (int i = 0; i != args.length; ++i) {
			if (args[i].signum() == -1) { // i.e., args[i] < 0
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
		return (n.signum() == 0); // i.e., n == 0
	}

	/**
	 * @param n
	 *            the given BigInteger object
	 * 
	 * @param args
	 *            any number of BigInteger objects
	 * 
	 * @throws NullPointerException
	 *             If
	 *             <code>(n == null) || (args == null) || ((valid i) implies (args[i] == null))</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(n != 0) || ((valid i) implies (args[i] != 0))</code>
	 */
	public static void ensureZero(BigInteger n, BigInteger... args)
			throws NullPointerException, IllegalArgumentException {
		if (n.signum() != 0) { // i.e., n != 0
			throw new IllegalArgumentException();
		}
		for (int i = 0; i != args.length; ++i) {
			if (args[i].signum() != 0) { // i.e., args[i] != 0
				throw new IllegalArgumentException();
			}
		}
	}

	/**
	 * @param n
	 *            the given BigInteger object
	 * 
	 * @param args
	 *            any number of BigInteger objects
	 * 
	 * @throws NullPointerException
	 *             If
	 *             <code>(n == null) || (args == null) || ((valid i) implies (args[i] == null))</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(n == 0) || ((valid i) implies (args[i] == 0))</code>
	 */
	public static void ensureNonZero(BigInteger n, BigInteger... args)
			throws NullPointerException, IllegalArgumentException {
		if (n.signum() == 0) { // i.e., n == 0
			throw new IllegalArgumentException();
		}
		for (int i = 0; i != args.length; ++i) {
			if (args[i].signum() == 0) { // i.e., args[i] == 0
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
		return (n.signum() == 1); // i.e., n > 0
	}

	/**
	 * @param n
	 *            the given BigInteger object
	 * 
	 * @param args
	 *            any number of BigInteger objects
	 * 
	 * @throws NullPointerException
	 *             If
	 *             <code>(n == null) || (args == null) || ((valid i) implies (args[i] == null))</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(n <= 0) || ((valid i) implies (args[i] <= 0))</code>
	 */
	public static void ensurePositive(BigInteger n, BigInteger... args)
			throws NullPointerException, IllegalArgumentException {
		if (n.signum() != 1) { // i.e., n <= 0
			throw new IllegalArgumentException();
		}
		for (int i = 0; i != args.length; ++i) {
			if (args[i].signum() != 1) { // i.e., args[i] <= 0
				throw new IllegalArgumentException();
			}
		}
	}

	/**
	 * @param n
	 *            the given BigInteger object
	 * 
	 * @param args
	 *            any number of BigInteger objects
	 * 
	 * @throws NullPointerException
	 *             If
	 *             <code>(n == null) || (args == null) || ((valid i) implies (args[i] == null))</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(n > 0) || ((valid i) implies (args[i] > 0))</code>
	 */
	public static void ensureNonPositive(BigInteger n, BigInteger... args)
			throws NullPointerException, IllegalArgumentException {
		if (n.signum() == 1) { // i.e., n > 0
			throw new IllegalArgumentException();
		}
		for (int i = 0; i != args.length; ++i) {
			if (args[i].signum() == 1) { // i.e., args[i] > 0
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
	 *            any number of BigInteger objects
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
		if (n.testBit(0)) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i != args.length; ++i) {
			if (args[i].testBit(0)) {
				throw new IllegalArgumentException();
			}
		}
	}

	/**
	 * @param n
	 *            the given BigInteger object
	 * 
	 * @param args
	 *            any number of BigInteger objects
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
		if (!n.testBit(0)) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i != args.length; ++i) {
			if (!args[i].testBit(0)) {
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
	 */
	public static BigInteger[] gcdExtended(BigInteger a, BigInteger b) throws NullPointerException {
		final int sign_a = a.signum(), sign_b = b.signum();
		// Handle the special cases where at least one of the two numbers is 0.
		if (sign_a == 0) { // i.e., a == 0
			if (sign_b == 0) { // i.e., b == 0
				// 0 * 0 + 0 * 0 == 0
				return new BigInteger[] { BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO };
			}
			// b != 0

			// 0 * a + 1 * b == b == gcd(0, b)
			return new BigInteger[] { BigInteger.ZERO, BigInteger.ONE, b };
		} else if (sign_b == 0) { // i.e., (b == 0) && (a != 0)
			// 1 * a + 0 * b == a == gcd(a, 0)
			return new BigInteger[] { BigInteger.ONE, BigInteger.ZERO, a };
		}
		// (a != 0) && (b != 0)

		// The algorithm only works for a > 0 and b > 0 so compute and save absolute values.
		BigInteger abs_a = a, abs_b = b;
		if (sign_a == -1) { // i.e., a < 0
			abs_a = a.negate();
		}
		if (sign_b == -1) { // i.e., b < 0
			abs_b = b.negate();
		}
		// (abs_a == a.abs()) && (abs_b == b.abs())

		// Algorithm is from Introduction to Mathematical Cryptography 2nd Edition Exercise 1.12.
		BigInteger gcd = abs_a, x = BigInteger.ONE;
		{
			BigInteger u = BigInteger.ZERO, v = abs_b, tmp = null;
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
		/**
		 * <code>x * abs_a + y * abs_b == gcd where y == (gcd - x * abs_a) / abs_b</code> <br>
		 * So, <code>(x * sign_a) * a + (y * sign_b) * b == gcd where y == (gcd - x * abs_a) / abs_b</code>
		 * <br>
		 * So, <code>x' * a + y' * b == gcd where x' == a * sign_a and y' == y * sign_b</code> <br>
		 * But since <code>b == sign_b * abs_b</code>, we can conclude that
		 * <code>y' == (gcd - x * abs_a) / b</code>
		 */
		return new BigInteger[] { (sign_a == -1) ? x.negate() : x, gcd.subtract(x.multiply(abs_a)).divide(b), gcd };
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
		final int sign_a = a.signum(), sign_b = b.signum();
		// lcm(0, b) == 0 == lcm(a, 0)
		if ((sign_a == 0) || (sign_b == 0)) { // i.e., (a == 0) || (b == 0)
			return BigInteger.ZERO;
		}
		// (a != 0) && (b != 0)

		// lcm is non-negative so make a and b non-negative.
		if (sign_a == -1) { // i.e., a < 0
			a = a.negate();
		}
		if (sign_b == -1) { // i.e., b < 0
			b = b.negate();
		}
		// (a > 0) && (b > 0)

		// lcm(a, b) == (a * b) / gcd(a, b)
		return a.divide(a.gcd(b)).multiply(b);
	}

	/**
	 * Chinese Remainder Theorem. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>justAnswer implies (Result.length == 2)</code> <br>
	 * Postcondition: <code>(!justAnswer) implies (Result.length == 5)</code> <br>
	 * Postcondition:
	 * <code>Result[0] == n1 * (<sup>m2</sup>&frasl;<sub>gcd(m1, m2)</sub>) * (m2<sup>-1</sup> (mod m1))
	 * + n2 * (<sup>m1</sup>&frasl;<sub>gcd(m1, m2)</sub>) * (m1<sup>-1</sup> (mod m2)) (mod lcm(m1, m2))</code>
	 * <br>
	 * Postcondition: <code>Result[1] == lcm(m1, m2)</code> <br>
	 * Postcondition:
	 * 
	 * <pre>
	 * <code>
	 * if (Result.length == 5) {
	 * 	assert (Result[2] == gcd(m1, m2));
	 * 	assert (Result[3] == m1<sup>-1</sup> (mod m2));
	 * 	assert (Result[4] == m2<sup>-1</sup> (mod m1));  
	 * }
	 * </code>
	 * </pre>
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
	 * @param justAnswer
	 *            specifies whether the resulting array should only contain the answer
	 * 
	 * @param modBefore
	 *            specifies whether <code>n1</code> and <code>n2</code> should be modded by
	 *            <code>m1</code> and <code>m2</code> respectively before applying the formula
	 * 
	 * @param modAfterEveryStep
	 *            specifies whether every intermediate result in the formula should be modded by the new
	 *            modulus (i.e., <code>lcm(m1, m2)</code>)
	 * 
	 * @return The resulting BigInteger array.
	 * 
	 * @throws NullPointerException
	 *             If <code>(n1 == null) || (m1 == null) || (n2 == null) || (m2 == null)</code>
	 * 
	 * @throws InvalidModulusException
	 *             If <code>(m1 < 1) || (m2 < 1)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n1 != n2 (mod gcd(m1, m2))</code>
	 */
	public static BigInteger[] crt(BigInteger n1, BigInteger m1, BigInteger n2, BigInteger m2, boolean justAnswer,
			boolean modBefore, boolean modAfterEveryStep)
			throws NullPointerException, InvalidModulusException, IllegalArgumentException {
		if ((n1 == null) || (n2 == null)) {
			throw new NullPointerException();
		} else if ((m1.signum() != 1) || (m2.signum() != 1)) { // i.e., (m1 <= 0) || (m2 <= 0)
			throw new InvalidModulusException();
		}
		// (m1 > 0) && (m2 > 0)

		// Find integers x and y such that x * m1 + y * m2 == gcd(m1, m2).
		final BigInteger[] x_y_gcd = BigIntUtil.gcdExtended(m1, m2);
		final BigInteger gcd = x_y_gcd[2];
		final boolean coprime = gcd.equals(BigInteger.ONE);

		// Compute the new modulus which is the least common multiple of m1 and m2.
		final BigInteger og_m1 = m1, og_m2 = m2;
		BigInteger m = null; // lcm(m1, m2) == (m1 * m2) / gcd(m1, m2)
		if (coprime) { // i.e., gcd == 1
			m = m1.multiply(m2);
		} else { // i.e., gcd != 1
			// Handle the invalid case.
			if (!n1.mod(gcd).equals(n2.mod(gcd))) {
				throw new IllegalArgumentException();
			}
			m = (m1 = m1.divide(gcd)).multiply(m2);
			m2 = m2.divide(gcd);
		}
		// (m1 == og_m1 / gcd) && (m2 == og_m2 / gcd) && (m == lcm(og_m1, og_m2))

		// Mod n1 and n2 before the computation if requested.
		if (modBefore) {
			n1 = n1.mod(og_m1);
			n2 = n2.mod(og_m2);
		}

		// Apply the C.R.T. formula for two congruences.
		final BigInteger m1_inverse = x_y_gcd[0].mod(og_m2), m2_inverse = x_y_gcd[1].mod(og_m1);
		BigInteger lhs = n1.multiply(m2), rhs = n2.multiply(m1);
		if (modAfterEveryStep) {
			lhs = lhs.mod(m).multiply(m2_inverse).mod(m);
			rhs = rhs.mod(m).multiply(m1_inverse).mod(m);
		} else {
			lhs = lhs.multiply(m2_inverse);
			rhs = rhs.multiply(m1_inverse);
		}
		lhs = lhs.add(rhs).mod(m);

		// Return either just the answer or the answer along with all of the extra information.
		if (justAnswer) {
			return new BigInteger[] { lhs, m };
		}
		return new BigInteger[] { lhs, m, gcd, m1_inverse, m2_inverse };
	}

	/**
	 * Chinese Remainder Theorem. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>justAnswer implies (Result.length == 2)</code> <br>
	 * Postcondition: <code>(!justAnswer) implies (Result.length == 5)</code> <br>
	 * Postcondition:
	 * <code>Result[0] == n1 * (<sup>m2</sup>&frasl;<sub>gcd(m1, m2)</sub>) * (m2<sup>-1</sup> (mod m1))
	 * + n2 * (<sup>m1</sup>&frasl;<sub>gcd(m1, m2)</sub>) * (m1<sup>-1</sup> (mod m2)) (mod lcm(m1, m2))</code>
	 * <br>
	 * Postcondition: <code>Result[1] == lcm(m1, m2)</code> <br>
	 * Postcondition:
	 * 
	 * <pre>
	 * <code>
	 * if (Result.length == 5) {
	 * 	assert (Result[2] == gcd(m1, m2));
	 * 	assert (Result[3] == m1<sup>-1</sup> (mod m2));
	 * 	assert (Result[4] == m2<sup>-1</sup> (mod m1));  
	 * }
	 * </code>
	 * </pre>
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
	 * @param justAnswer
	 *            specifies whether the resulting array should only contain the answer
	 * 
	 * @param modBefore
	 *            specifies whether <code>n1</code> and <code>n2</code> should be modded by
	 *            <code>m1</code> and <code>m2</code> respectively before applying the formula
	 * 
	 * @return The resulting BigInteger array.
	 * 
	 * @throws NullPointerException
	 *             If <code>(n1 == null) || (m1 == null) || (n2 == null) || (m2 == null)</code>
	 * 
	 * @throws InvalidModulusException
	 *             If <code>(m1 < 1) || (m2 < 1)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n1 != n2 (mod gcd(m1, m2))</code>
	 */
	public static BigInteger[] crt(BigInteger n1, BigInteger m1, BigInteger n2, BigInteger m2, boolean justAnswer,
			boolean modBefore) throws NullPointerException, InvalidModulusException, IllegalArgumentException {
		return BigIntUtil.crt(n1, m1, n2, m2, justAnswer, modBefore, true);
	}

	/**
	 * Chinese Remainder Theorem. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>justAnswer implies (Result.length == 2)</code> <br>
	 * Postcondition: <code>(!justAnswer) implies (Result.length == 5)</code> <br>
	 * Postcondition:
	 * <code>Result[0] == n1 * (<sup>m2</sup>&frasl;<sub>gcd(m1, m2)</sub>) * (m2<sup>-1</sup> (mod m1))
	 * + n2 * (<sup>m1</sup>&frasl;<sub>gcd(m1, m2)</sub>) * (m1<sup>-1</sup> (mod m2)) (mod lcm(m1, m2))</code>
	 * <br>
	 * Postcondition: <code>Result[1] == lcm(m1, m2)</code> <br>
	 * Postcondition:
	 * 
	 * <pre>
	 * <code>
	 * if (Result.length == 5) {
	 * 	assert (Result[2] == gcd(m1, m2));
	 * 	assert (Result[3] == m1<sup>-1</sup> (mod m2));
	 * 	assert (Result[4] == m2<sup>-1</sup> (mod m1));  
	 * }
	 * </code>
	 * </pre>
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
	 * @param justAnswer
	 *            specifies whether the resulting array should only contain the answer
	 * 
	 * @return The resulting BigInteger array.
	 * 
	 * @throws NullPointerException
	 *             If <code>(n1 == null) || (m1 == null) || (n2 == null) || (m2 == null)</code>
	 * 
	 * @throws InvalidModulusException
	 *             If <code>(m1 < 1) || (m2 < 1)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n1 != n2 (mod gcd(m1, m2))</code>
	 */
	public static BigInteger[] crt(BigInteger n1, BigInteger m1, BigInteger n2, BigInteger m2, boolean justAnswer)
			throws NullPointerException, InvalidModulusException, IllegalArgumentException {
		return BigIntUtil.crt(n1, m1, n2, m2, justAnswer, true);
	}

	/**
	 * Chinese Remainder Theorem. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.length == 2</code> <br>
	 * Postcondition:
	 * <code>Result[0] == n1 * (<sup>m2</sup>&frasl;<sub>gcd(m1, m2)</sub>) * (m2<sup>-1</sup> (mod m1))
	 * + n2 * (<sup>m1</sup>&frasl;<sub>gcd(m1, m2)</sub>) * (m1<sup>-1</sup> (mod m2)) (mod lcm(m1, m2))</code>
	 * <br>
	 * Postcondition: <code>Result[1] == lcm(m1, m2)</code>
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
	 * @return The resulting BigInteger array.
	 * 
	 * @throws NullPointerException
	 *             If <code>(n1 == null) || (m1 == null) || (n2 == null) || (m2 == null)</code>
	 * 
	 * @throws InvalidModulusException
	 *             If <code>(m1 < 1) || (m2 < 1)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n1 != n2 (mod gcd(m1, m2))</code>
	 */
	public static BigInteger[] crt(BigInteger n1, BigInteger m1, BigInteger n2, BigInteger m2)
			throws NullPointerException, InvalidModulusException, IllegalArgumentException {
		return BigIntUtil.crt(n1, m1, n2, m2, true);
	}

	/**
	 * @param begin
	 *            the given begin power
	 * 
	 * @param end
	 *            the given end power
	 * 
	 * @return <code>end - begin</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(begin == null) || (end == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>begin > end</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(end - begin) > Integer.MAX_VALUE</code>
	 */
	protected static int powersLength(BigInteger begin, BigInteger end)
			throws NullPointerException, IllegalArgumentException, ArithmeticException {
		// Validate begin and end.
		final int cmp = begin.compareTo(end);
		if (cmp > 0) { // i.e., begin > end
			throw new IllegalArgumentException();
		}
		// cmp <= 0
		// i.e., begin <= end
		return ((cmp == 0) ? 0 : end.subtract(begin).intValueExact());
	}

	/**
	 * Note that this function defines <code>0<sup>0</sup> == 0</code> even though it is undefined in
	 * math. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.length == end - begin</code> <br>
	 * Postcondition: <code>(valid i) implies (Result[i] == n<sup>(i + begin)</sup> (mod m))</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param begin
	 *            the given begin power
	 * 
	 * @param end
	 *            the given end power
	 * 
	 * @return The resulting BigInteger array.
	 * 
	 * @throws NullPointerException
	 *             If <code>(n == null) || (m == null) || (begin == null) || (end == null)</code>
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>begin > end</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(end - begin) > Integer.MAX_VALUE</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(begin < 0) && ((n (mod m) == 0) || (gcd(n, m) != 1))</code>
	 */
	public static BigInteger[] modPowers(BigInteger n, BigInteger m, BigInteger begin, BigInteger end)
			throws NullPointerException, InvalidModulusException, IllegalArgumentException, ArithmeticException,
			UndefinedInverseException {
		if (n == null) {
			throw new NullPointerException();
		} else if (m.signum() != 1) { // i.e., m <= 0
			throw new InvalidModulusException();
		}
		// m > 0

		// Compute the resulting array length.
		final int length = BigIntUtil.powersLength(begin, end);
		// (begin <= end) && ((end - begin) <= Integer.MAX_VALUE)

		// Create the resulting BigInteger array and handle the simple special cases.
		final BigInteger[] result = new BigInteger[length];
		if (length == 0) {
			// Nothing to do here.
			return result;
		}
		// length != 0
		// i.e., length > 0

		// Fix n to be in [0, m - 1] \cap \doubleZ.
		n = n.mod(m);

		if (n.signum() == 0) { // i.e., n == 0
			/**
			 * This case is needed since 0 to any positive power is 0 and so any non-zero assignment of
			 * <code>result[i]</code> will be wrong in this case. Furthermore, note that we are defining
			 * <code>0<sup>0</sup> == 0</code> here even though it is undefined in math.
			 */
			if (begin.signum() == -1) { // i.e., begin < 0
				throw new UndefinedInverseException();
			}
			// begin >= 0
			Arrays.fill(result, BigInteger.ZERO);
			return result;
		} else if (n.equals(BigInteger.ONE)) { // i.e., n == 1 (mod m)
			/*
			 * This case is only an optimization since 1 to any power is 1 and so the loop will do extra
			 * unnecessary work to arrive at the same result.
			 */
			Arrays.fill(result, BigInteger.ONE);
			return result;
		}
		// n >= 2
		// i.e., (1 < n) && (n <= m - 1) && (m > 2)
		if (n.add(BigInteger.ONE).equals(m)) { // i.e., n == -1 (mod m)
			/*
			 * This case is only an optimization since -1 to any even power is 1 and otherwise is -1. So the
			 * loop will do extra unnecessary work to arrive at the same result.
			 */
			boolean evenPow = !begin.testBit(0);
			for (int i = 0; i != length; ++i, evenPow = !evenPow) {
				result[i] = evenPow ? BigInteger.ONE : n;
			}
			return result;
		}
		// n != m - 1
		// i.e., (1 < n) && (n < m - 1) && (m > 3)

		// Fill and return the resulting BigInteger array.
		BigInteger n_to_i = n.modPow(begin, m);
		for (int i = 0; i != length; ++i, n_to_i = n_to_i.multiply(n).mod(m)) {
			result[i] = n_to_i;
		}
		return result;
	}
}