package util;

import java.math.BigInteger;

/**
 * Utility BigInteger methods in addition to Java's BigInteger class.
 * 
 * @author Ashkan Moatamed
 */
public class BigIntegerUtil {
	/**
	 * No dependencies.
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
	 * @return <code>n.getLowestSetBit() != 0</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>n == null</code>
	 */
	public static boolean isEven(BigInteger n) throws NullPointerException {
		return (n.getLowestSetBit() != 0);
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

		// lcm is non-negative so make a and b non-negative.
		a = a.abs();
		b = a.abs();

		// lcm(a, b) == (a * b) / gcd(a, b)
		return (a.divide(a.gcd(b))).multiply(b);
	}
}
