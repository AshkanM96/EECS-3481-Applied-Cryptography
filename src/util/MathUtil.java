package util;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Utility math methods in addition to Java's Math class.
 * 
 * @author Ashkan Moatamed
 */
public class MathUtil {
	/**
	 * Dependencies: <code>
	 * 		1. util.NumUtil
	 * 		2. util.InvalidModulusException
	 * 		3. util.UndefinedInverseException
	 * </code>
	 */

	/**
	 * <code>ln(2)</code>.
	 */
	public static final double LOG_2 = Math.log(2);

	/**
	 * Prevent instantiation.
	 */
	private MathUtil() {
		// Empty by design.
	}

	/**
	 * Returns the base <code>2</code> logarithm of a <code>double</code> value. Special cases:
	 * <ul>
	 * <li>If the argument is <code>NaN</code> or <code>less than zero</code>, then the result is
	 * <code>NaN</code>.
	 * <li>If the argument is <code>positive infinity</code>, then the result is
	 * <code>positive infinity</code>.
	 * <li>If the argument is <code>positive zero</code> or <code>negative zero</code>, then the result
	 * is <code>negative infinity</code>.
	 * <li>If the argument is equal to <code>2<sup>p</sup></code> for integer <code>p</code>, then the
	 * result is <code>p</code>.
	 * </ul>
	 * The computed result must be within <code>1 ulp</code> of the exact result. Results must be
	 * <code>semi-monotonic</code>.
	 *
	 * @param n
	 *            the given number
	 * 
	 * @return The base <code>2</code> logarithm of <code>n</code>.
	 */
	public static double log2(double n) {
		return (Math.log(n) / MathUtil.LOG_2);
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return <code>(n < 0) ? -1 : ((n == 0) ? 0 : 1)</code>.
	 */
	public static int signum(long n) {
		return ((n < 0L) ? -1 : ((n == 0L) ? 0 : 1));
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return <code>(n < 0) ? -1 : ((n == 0) ? 0 : 1)</code>.
	 */
	public static int signum(int n) {
		return ((n < 0) ? -1 : ((n == 0) ? 0 : 1));
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return <code>(n < 0) ? -1 : ((n == 0) ? 0 : 1)</code>.
	 */
	public static int signum(short n) {
		return ((n < 0) ? -1 : ((n == 0) ? 0 : 1));
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return <code>(n < 0) ? -1 : ((n == 0) ? 0 : 1)</code>.
	 */
	public static int signum(byte n) {
		return ((n < 0) ? -1 : ((n == 0) ? 0 : 1));
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return <code>true</code> if and only if the given number is even.
	 */
	public static boolean isEven(long n) {
		// Odd numbers have their lowest bit set.
		// By using bitwise and, we can check this fact.
		return ((n &= 1L) == 0L);
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return <code>true</code> if and only if the given number is even.
	 */
	public static boolean isEven(int n) {
		// Odd numbers have their lowest bit set.
		// By using bitwise and, we can check this fact.
		return ((n &= 1) == 0);
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return <code>true</code> if and only if the given number is even.
	 */
	public static boolean isEven(short n) {
		// Odd numbers have their lowest bit set.
		// By using bitwise and, we can check this fact.
		return ((n &= 1) == 0);
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return <code>true</code> if and only if the given number is even.
	 */
	public static boolean isEven(byte n) {
		// Odd numbers have their lowest bit set.
		// By using bitwise and, we can check this fact.
		return ((n &= 1) == 0);
	}

	/**
	 * Precondition: <code>!((a == Long.MIN_VALUE) && ((b == 0) || (b == Long.MIN_VALUE)))</code> <br>
	 * Precondition: <code>!((b == Long.MIN_VALUE) && ((a == 0) || (a == Long.MIN_VALUE)))</code>
	 * 
	 * @param a
	 *            the first given number
	 * 
	 * @param b
	 *            the second given number
	 * 
	 * @return The greatest common divisor of the two numbers.
	 */
	protected static long gcdFixedInput(long a, long b) {
		// gcd is non-negative so make a and b non-negative.
		if (a < 0L) {
			a *= -1L;
		}
		if (b < 0L) {
			b *= -1L;
		}

		// Assume a is smaller than b.
		long min = a, max = b;
		// Fix min and max if needed.
		if (max < min) {
			final long tmp = max;
			max = min;
			min = tmp;
		}
		// (min == Math.min(a, b)) && (max == Math.max(a, b))

		// Loop until min == 0 since gcd(max, 0) == max.
		for (long remainder = 0L; min != 0L; /* Update inside. */) {
			// Compute the remainder upon integer division.
			remainder = max % min;

			// Update max and min using the following fact: gcd(max, min) == gcd(min, remainder)
			max = min;
			min = remainder;
		}
		return ((max < 0L) ? (max *= -1L) : max);
	}

	/**
	 * @param a
	 *            the first given number
	 * 
	 * @param b
	 *            the second given number
	 * 
	 * @return The greatest common divisor of the two numbers.
	 * 
	 * @throws ArithmeticException
	 *             If <code>((a == Long.MIN_VALUE) && ((b == 0) || (b == Long.MIN_VALUE)))
	 *             || ((b == Long.MIN_VALUE) && ((a == 0) || (a == Long.MIN_VALUE)))</code>
	 */
	public static long gcd(long a, long b) throws ArithmeticException {
		// Handle the degenerate cases where the result cannot be represented as a non-negative long.
		if (a == Long.MIN_VALUE) {
			if ((b == 0L) || (b == Long.MIN_VALUE)) {
				// gcd(a, 0) == a == gcd(a, a) but a's absolute value is not representable as a non-negative long.
				throw new ArithmeticException();
			}
		} else if (b == Long.MIN_VALUE) { // a != Long.MIN_VALUE
			if (a == 0L) {
				// gcd(0, b) == b but b's absolute value is not representable as a non-negative long.
				throw new ArithmeticException();
			}
		}
		return MathUtil.gcdFixedInput(a, b);
	}

	/**
	 * @param a
	 *            the first given number
	 * 
	 * @param b
	 *            the second given number
	 * 
	 * @return The greatest common divisor of the two numbers.
	 * 
	 * @throws ArithmeticException
	 *             If <code>((a == Integer.MIN_VALUE) && ((b == 0) || (b == Integer.MIN_VALUE)))
	 *             || ((b == Integer.MIN_VALUE) && ((a == 0) || (a == Integer.MIN_VALUE)))</code>
	 */
	public static int gcd(int a, int b) throws ArithmeticException {
		// Handle the degenerate cases where the result cannot be represented as a non-negative int.
		if (a == Integer.MIN_VALUE) {
			if ((b == 0) || (b == Integer.MIN_VALUE)) {
				// gcd(a, 0) == a == gcd(a, a) but a's absolute value is not representable as a non-negative int.
				throw new ArithmeticException();
			}
		} else if (b == Integer.MIN_VALUE) { // a != Integer.MIN_VALUE
			if (a == 0) {
				// gcd(0, b) == b but b's absolute value is not representable as a non-negative int.
				throw new ArithmeticException();
			}
		}
		return ((int) MathUtil.gcdFixedInput(a, b));
	}

	/**
	 * @param a
	 *            the first given number
	 * 
	 * @param b
	 *            the second given number
	 * 
	 * @return The greatest common divisor of the two numbers.
	 * 
	 * @throws ArithmeticException
	 *             If <code>((a == Short.MIN_VALUE) && ((b == 0) || (b == Short.MIN_VALUE)))
	 *             || ((b == Short.MIN_VALUE) && ((a == 0) || (a == Short.MIN_VALUE)))</code>
	 */
	public static short gcd(short a, short b) throws ArithmeticException {
		// Handle the degenerate cases where the result cannot be represented as a non-negative short.
		if (a == Short.MIN_VALUE) {
			if ((b == 0) || (b == Short.MIN_VALUE)) {
				// gcd(a, 0) == a == gcd(a, a) but a's absolute value is not representable as a non-negative short.
				throw new ArithmeticException();
			}
		} else if (b == Short.MIN_VALUE) { // a != Short.MIN_VALUE
			if (a == 0) {
				// gcd(0, b) == b but b's absolute value is not representable as a non-negative short.
				throw new ArithmeticException();
			}
		}
		return ((short) MathUtil.gcdFixedInput(a, b));
	}

	/**
	 * @param a
	 *            the first given number
	 * 
	 * @param b
	 *            the second given number
	 * 
	 * @return The greatest common divisor of the two numbers.
	 * 
	 * @throws ArithmeticException
	 *             If <code>((a == Byte.MIN_VALUE) && ((b == 0) || (b == Byte.MIN_VALUE)))
	 *             || ((b == Byte.MIN_VALUE) && ((a == 0) || (a == Byte.MIN_VALUE)))</code>
	 */
	public static byte gcd(byte a, byte b) {
		// Handle the degenerate cases where the result cannot be represented as a non-negative byte.
		if (a == Byte.MIN_VALUE) {
			if ((b == 0) || (b == Byte.MIN_VALUE)) {
				// gcd(a, 0) == a == gcd(a, a) but a's absolute value is not representable as a non-negative byte.
				throw new ArithmeticException();
			}
		} else if (b == Byte.MIN_VALUE) { // a != Byte.MIN_VALUE
			if (a == 0) {
				// gcd(0, b) == b but b's absolute value is not representable as a non-negative byte.
				throw new ArithmeticException();
			}
		}
		return ((byte) MathUtil.gcdFixedInput(a, b));
	}

	/**
	 * Precondition: <code>(a != Long.MIN_VALUE) && (b != Long.MIN_VALUE)</code> <br>
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
	 * @return The resulting long array.
	 */
	protected static long[] gcdExtendedFixedInput(long a, long b) {
		// Handle the special cases where at least one of the two numbers is 0.
		if (a == 0L) {
			if (b == 0L) {
				// 0 * 0 + 0 * 0 == 0
				return new long[] { 0L, 0L, 0L };
			}
			// b != 0

			// 0 * a + 1 * b == b == gcd(0, b)
			return new long[] { 0L, 1L, b };
		} else if (b == 0L) { // a != 0
			// 1 * a + 0 * b == a == gcd(a, 0)
			return new long[] { 1L, 0L, a };
		}
		// (a != 0) && (b != 0)

		// The algorithm only works for a > 0 and b > 0 so compute and save absolute values and signs.
		long abs_a = a, abs_b = b;
		int sign_a = 1;
		if (a < 0L) {
			abs_a *= (sign_a = -1);
		}
		// (abs_a == Math.abs(a)) && (sign_a == MathUtil.signum(a))
		if (b < 0L) {
			abs_b *= -1;
		}
		// abs_b == Math.abs(b)

		// The algorithm is from Introduction to Mathematical Cryptography 2nd Edition Exercise 1.12.
		long gcd = abs_a, x = 1L;
		{
			long u = 0L, v = abs_b, remainder = 0L, quotient = gcd, tmp = 0L;
			do {
				// Compute the quotient and the remainder.
				remainder = gcd - (quotient /= v) * v;
				// (quotient == gcd / v) && (remainder == gcd % v)

				// Update all of the variables.
				tmp = (x -= (quotient *= u));
				quotient = gcd = v;
				x = u;
				u = tmp;
				v = remainder;
			} while (v != 0L);
		}
		/**
		 * <code>x * abs_a + y * abs_b == gcd where y == (gcd - x * abs_a) / abs_b</code> <br>
		 * So, <code>(x * sign_a) * a + (y * sign_b) * b == gcd where y == (gcd - x * abs_a) / abs_b</code>
		 * <br>
		 * So, <code>x' * a + y' * b == gcd where x' == a * sign_a and y' == y * sign_b</code> <br>
		 * But since <code>b == sign_b * abs_b</code>, we can conclude that
		 * <code>y' == (gcd - x * abs_a) / b</code>
		 */
		/*
		 * Have to use BigInteger to compute y' since every intermediate result in the formula for y' may
		 * overflow a long. However, note that y' itself fits in a long, and so we can call longValue
		 * instead of longValueExact and not worry about a potential overflow of y'.
		 */
		final long y = BigInteger.valueOf(gcd).subtract(BigInteger.valueOf(x).multiply(BigInteger.valueOf(abs_a)))
				.divide(BigInteger.valueOf(b)).longValue();
		return new long[] { x *= sign_a, y, gcd };
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
	 * @return The resulting long array.
	 * 
	 * @throws ArithmeticException
	 *             If <code>(a == Long.MIN_VALUE) || (b == Long.MIN_VALUE)</code>
	 */
	public static long[] gcdExtended(long a, long b) throws ArithmeticException {
		/*
		 * Handle the degenerate cases where a's or b's absolute value is not representable as a
		 * non-negative long.
		 */
		if ((a == Long.MIN_VALUE) || (b == Long.MIN_VALUE)) {
			throw new ArithmeticException();
		}
		// (a != Long.MIN_VALUE) && (b != Long.MIN_VALUE)
		return MathUtil.gcdExtendedFixedInput(a, b);
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
	 * @return The resulting integer array.
	 * 
	 * @throws ArithmeticException
	 *             If <code>(a == Integer.MIN_VALUE) || (b == Integer.MIN_VALUE)</code>
	 */
	public static int[] gcdExtended(int a, int b) throws ArithmeticException {
		/*
		 * Handle the degenerate cases where a's or b's absolute value is not representable as a
		 * non-negative int.
		 */
		if ((a == Integer.MIN_VALUE) || (b == Integer.MIN_VALUE)) {
			throw new ArithmeticException();
		}
		// (a != Integer.MIN_VALUE) && (b != Integer.MIN_VALUE)
		final long[] result = MathUtil.gcdExtendedFixedInput(a, b);
		return new int[] { (int) result[0], (int) result[1], (int) result[2] };
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
	 * @return The resulting short array.
	 * 
	 * @throws ArithmeticException
	 *             If <code>(a == Short.MIN_VALUE) || (b == Short.MIN_VALUE)</code>
	 */
	public static short[] gcdExtended(short a, short b) throws ArithmeticException {
		/*
		 * Handle the degenerate cases where a's or b's absolute value is not representable as a
		 * non-negative short.
		 */
		if ((a == Short.MIN_VALUE) || (b == Short.MIN_VALUE)) {
			throw new ArithmeticException();
		}
		// (a != Short.MIN_VALUE) && (b != Short.MIN_VALUE)
		final long[] result = MathUtil.gcdExtendedFixedInput(a, b);
		return new short[] { (short) result[0], (short) result[1], (short) result[2] };
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
	 * @return The resulting byte array.
	 * 
	 * @throws ArithmeticException
	 *             If <code>(a == Byte.MIN_VALUE) || (b == Byte.MIN_VALUE)</code>
	 */
	public static byte[] gcdExtended(byte a, byte b) throws ArithmeticException {
		/*
		 * Handle the degenerate cases where a's or b's absolute value is not representable as a
		 * non-negative byte.
		 */
		if ((a == Byte.MIN_VALUE) || (b == Byte.MIN_VALUE)) {
			throw new ArithmeticException();
		}
		// (a != Byte.MIN_VALUE) && (b != Byte.MIN_VALUE)
		final long[] result = MathUtil.gcdExtendedFixedInput(a, b);
		return new byte[] { (byte) result[0], (byte) result[1], (byte) result[2] };
	}

	/**
	 * Note that this function does not check for overflows which may have occurred during the
	 * calculations. <br>
	 * Precondition: <code>(a != Long.MIN_VALUE) || (b != Long.MIN_VALUE)</code>
	 * 
	 * @param a
	 *            the first given number
	 * 
	 * @param b
	 *            the second given number
	 * 
	 * @return The least common multiple of the two numbers.
	 */
	protected static long lcmFixedInput(long a, long b) {
		// lcm(0, b) == 0 == lcm(a, 0)
		if ((a == 0L) || (b == 0L)) {
			return 0L;
		}
		// (a != 0) && (b != 0)

		// lcm is non-negative so make a and b non-negative.
		if (a < 0L) {
			a *= -1L;
		}
		// (a > 0) || (a == Long.MIN_VALUE)
		if (b < 0L) {
			b *= -1L;
		}
		// (b > 0) || (b == Long.MIN_VALUE)

		// lcm(a, b) == (a * b) / gcd(a, b)
		/**
		 * It's fine to do <code>a /= MathUtil.gcdFixedInput(a, b)</code> instead of
		 * <code>a / MathUtil.gcdFixedInput(a, b)</code> since we don't need the value of <code>a</code> to
		 * remain unchanged at this point.
		 */
		long result = (a /= MathUtil.gcdFixedInput(a, b)) * b;
		return ((result < 0L) ? (result *= -1L) : result);
	}

	/**
	 * Note that this function does not check for overflows which may have occurred during the
	 * calculations.
	 * 
	 * @param a
	 *            the first given number
	 * 
	 * @param b
	 *            the second given number
	 * 
	 * @return The least common multiple of the two numbers.
	 * 
	 * @throws ArithmeticException
	 *             If <code>(a == Long.MIN_VALUE) && (b == Long.MIN_VALUE)</code>
	 */
	public static long lcm(long a, long b) throws ArithmeticException {
		/**
		 * Check for the precondition of <code>MathUtil.gcd(long, long)</code> assuming non-zero a and b.
		 */
		if ((a == Long.MIN_VALUE) && (b == Long.MIN_VALUE)) {
			throw new ArithmeticException();
		}
		// (a != Long.MIN_VALUE) || (b != Long.MIN_VALUE)
		return MathUtil.lcmFixedInput(a, b);
	}

	/**
	 * Note that this function does not check for overflows which may have occurred during the
	 * calculations.
	 * 
	 * @param a
	 *            the first given number
	 * 
	 * @param b
	 *            the second given number
	 * 
	 * @return The least common multiple of the two numbers.
	 * 
	 * @throws ArithmeticException
	 *             If <code>(a == Integer.MIN_VALUE) && (b == Integer.MIN_VALUE)</code>
	 */
	public static int lcm(int a, int b) throws ArithmeticException {
		/**
		 * Check for the precondition of <code>MathUtil.gcd(int, int)</code> assuming non-zero a and b.
		 */
		if ((a == Integer.MIN_VALUE) && (b == Integer.MIN_VALUE)) {
			throw new ArithmeticException();
		}
		// (a != Integer.MIN_VALUE) || (b != Integer.MIN_VALUE)
		return ((int) MathUtil.lcmFixedInput(a, b));
	}

	/**
	 * Note that this function does not check for overflows which may have occurred during the
	 * calculations.
	 * 
	 * @param a
	 *            the first given number
	 * 
	 * @param b
	 *            the second given number
	 * 
	 * @return The least common multiple of the two numbers.
	 * 
	 * @throws ArithmeticException
	 *             If <code>(a == Short.MIN_VALUE) && (b == Short.MIN_VALUE)</code>
	 */
	public static short lcm(short a, short b) throws ArithmeticException {
		/**
		 * Check for the precondition of <code>MathUtil.gcd(short, short)</code> assuming non-zero a and b.
		 */
		if ((a == Short.MIN_VALUE) && (b == Short.MIN_VALUE)) {
			throw new ArithmeticException();
		}
		// (a != Short.MIN_VALUE) || (b != Short.MIN_VALUE)
		return ((short) MathUtil.lcmFixedInput(a, b));
	}

	/**
	 * Note that this function does not check for overflows which may have occurred during the
	 * calculations.
	 * 
	 * @param a
	 *            the first given number
	 * 
	 * @param b
	 *            the second given number
	 * 
	 * @return The least common multiple of the two numbers.
	 * 
	 * @throws ArithmeticException
	 *             If <code>(a == Byte.MIN_VALUE) && (b == Byte.MIN_VALUE)</code>
	 */
	public static byte lcm(byte a, byte b) throws ArithmeticException {
		/**
		 * Check for the precondition of <code>MathUtil.gcd(byte, byte)</code> assuming non-zero a and b.
		 */
		if ((a == Byte.MIN_VALUE) && (b == Byte.MIN_VALUE)) {
			throw new ArithmeticException();
		}
		// (a != Byte.MIN_VALUE) || (b != Byte.MIN_VALUE)
		return ((byte) MathUtil.lcmFixedInput(a, b));
	}

	/**
	 * Precondition: <code>(a != Long.MIN_VALUE) && (b != Long.MIN_VALUE)</code>
	 * 
	 * @param a
	 *            the first given number
	 * 
	 * @param b
	 *            the second given number
	 * 
	 * @return The least common multiple of the two numbers.
	 * 
	 * @throws ArithmeticException
	 *             If <code>Long.MAX_VALUE < lcm(a, b)</code>
	 */
	protected static long lcmExactFixedInput(long a, long b) throws ArithmeticException {
		// lcm(0, b) == 0 == lcm(a, 0)
		if ((a == 0L) || (b == 0L)) {
			return 0L;
		}
		// (a != 0) && (b != 0)

		// lcm is non-negative so make a and b non-negative.
		if (a < 0L) {
			a *= -1L;
		}
		// a > 0
		if (b < 0L) {
			b *= -1L;
		}
		// b > 0

		// lcm(a, b) == (a * b) / gcd(a, b)
		/**
		 * It's fine to do <code>a /= MathUtil.gcdFixedInput(a, b)</code> instead of
		 * <code>a / MathUtil.gcdFixedInput(a, b)</code> since we don't need the value of <code>a</code> to
		 * remain unchanged at this point.
		 */
		return Math.multiplyExact(a /= MathUtil.gcdFixedInput(a, b), b);
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
	 * @throws ArithmeticException
	 *             If <code>(a == Long.MIN_VALUE) || (b == Long.MIN_VALUE)
	 *             || (Long.MAX_VALUE < lcm(a, b))</code>
	 */
	public static long lcmExact(long a, long b) throws ArithmeticException {
		/*
		 * Handle the degenerate cases where a's or b's absolute value is not representable as a
		 * non-negative long.
		 */
		if ((a == Long.MIN_VALUE) || (b == Long.MIN_VALUE)) {
			throw new ArithmeticException();
		}
		// (a != Long.MIN_VALUE) && (b != Long.MIN_VALUE)
		return MathUtil.lcmExactFixedInput(a, b);
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
	 * @throws ArithmeticException
	 *             If <code>(a == Integer.MIN_VALUE) || (b == Integer.MIN_VALUE)
	 *             || (Integer.MAX_VALUE < lcm(a, b))</code>
	 */
	public static int lcmExact(int a, int b) throws ArithmeticException {
		/*
		 * Handle the degenerate cases where a's or b's absolute value is not representable as a
		 * non-negative int.
		 */
		if ((a == Integer.MIN_VALUE) || (b == Integer.MIN_VALUE)) {
			throw new ArithmeticException();
		}
		// (a != Integer.MIN_VALUE) && (b != Integer.MIN_VALUE)
		final long result = MathUtil.lcmExactFixedInput(a, b);
		if (Integer.MAX_VALUE < result) {
			throw new ArithmeticException();
		}
		return ((int) result);
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
	 * @throws ArithmeticException
	 *             If <code>(a == Short.MIN_VALUE) || (b == Short.MIN_VALUE)
	 *             || (Short.MAX_VALUE < lcm(a, b))</code>
	 */
	public static short lcmExact(short a, short b) throws ArithmeticException {
		/*
		 * Handle the degenerate cases where a's or b's absolute value is not representable as a
		 * non-negative short.
		 */
		if ((a == Short.MIN_VALUE) || (b == Short.MIN_VALUE)) {
			throw new ArithmeticException();
		}
		// (a != Short.MIN_VALUE) && (b != Short.MIN_VALUE)
		final long result = MathUtil.lcmExactFixedInput(a, b);
		if (Short.MAX_VALUE < result) {
			throw new ArithmeticException();
		}
		return ((short) result);
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
	 * @throws ArithmeticException
	 *             If <code>(a == Byte.MIN_VALUE) || (b == Byte.MIN_VALUE)
	 *             || (Byte.MAX_VALUE < lcm(a, b))</code>
	 */
	public static byte lcmExact(byte a, byte b) throws ArithmeticException {
		/*
		 * Handle the degenerate cases where a's or b's absolute value is not representable as a
		 * non-negative byte.
		 */
		if ((a == Byte.MIN_VALUE) || (b == Byte.MIN_VALUE)) {
			throw new ArithmeticException();
		}
		// (a != Byte.MIN_VALUE) && (b != Byte.MIN_VALUE)
		final long result = MathUtil.lcmExactFixedInput(a, b);
		if (Byte.MAX_VALUE < result) {
			throw new ArithmeticException();
		}
		return ((byte) result);
	}

	/**
	 * Compute <code>n<sup>p</sup></code> using the Fast Power (a.k.a. Successive Squaring) Algorithm.
	 * <br>
	 * Note that this function does not check for overflows which may have occurred during the
	 * calculations.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param p
	 *            the given power
	 * 
	 * @return <code>n<sup>p</sup></code>.
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(p < 0) && (n == 0)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(p == 0) && (n == 0)</code>
	 */
	public static long pow(long n, long p) throws UndefinedInverseException, ArithmeticException {
		if (p < 0L) {
			if (n == 0L) {
				throw new UndefinedInverseException();
			}
			// n != 0
			return 0L; // floor(1 / n) == 0
		}
		// p >= 0

		if (n == 0L) {
			if (p == 0L) {
				throw new ArithmeticException();
			}
			return 0L;
		} else if (n == 1L) {
			return 1L;
		}
		// (n != 0) && (n != 1)

		long result = 1L;
		boolean notExit = (p != 0L);
		for (long n_to_2_to_i = n; notExit; /* Update inside. */) {
			/**
			 * Don't do <code>(p &= 1L) != 0L</code> since we need the value of <code>p</code> to remain
			 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
			 * will mutate <code>p</code>.
			 */
			if ((p & 1L) != 0L) {
				result *= n_to_2_to_i;
			}
			// The following is meant to be an assignment of notExit and p.
			if (notExit = ((p /= 2L) != 0L)) {
				n_to_2_to_i *= n_to_2_to_i;
			}
		}
		return result;
	}

	/**
	 * Compute <code>n<sup>p</sup></code> using the Fast Power (a.k.a. Successive Squaring) Algorithm.
	 * <br>
	 * Note that this function does not check for overflows which may have occurred during the
	 * calculations.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param p
	 *            the given power
	 * 
	 * @return <code>n<sup>p</sup></code>.
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(p < 0) && (n == 0)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(p == 0) && (n == 0)</code>
	 */
	public static int pow(int n, int p) throws UndefinedInverseException, ArithmeticException {
		return ((int) MathUtil.pow((long) n, (long) p));
	}

	/**
	 * Compute <code>n<sup>p</sup></code> using the Fast Power (a.k.a. Successive Squaring) Algorithm.
	 * <br>
	 * Note that this function does not check for overflows which may have occurred during the
	 * calculations.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param p
	 *            the given power
	 * 
	 * @return <code>n<sup>p</sup></code>.
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(p < 0) && (n == 0)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(p == 0) && (n == 0)</code>
	 */
	public static short pow(short n, short p) throws UndefinedInverseException, ArithmeticException {
		return ((short) MathUtil.pow((long) n, (long) p));
	}

	/**
	 * Compute <code>n<sup>p</sup></code> using the Fast Power (a.k.a. Successive Squaring) Algorithm.
	 * <br>
	 * Note that this function does not check for overflows which may have occurred during the
	 * calculations.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param p
	 *            the given power
	 * 
	 * @return <code>n<sup>p</sup></code>.
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(p < 0) && (n == 0)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(p == 0) && (n == 0)</code>
	 */
	public static byte pow(byte n, byte p) throws UndefinedInverseException, ArithmeticException {
		return ((byte) MathUtil.pow((long) n, (long) p));
	}

	/**
	 * Compute <code>n<sup>p</sup></code> using the Fast Power (a.k.a. Successive Squaring) Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param p
	 *            the given power
	 * 
	 * @return <code>n<sup>p</sup></code>.
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(p < 0) && (n == 0)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>((p == 0) && (n == 0))
	 *             || ((Long.MAX_VALUE < n<sup>p</sup>) || (n<sup>p</sup> < Long.MIN_VALUE))</code>
	 */
	public static long powExact(long n, byte p) throws UndefinedInverseException, ArithmeticException {
		if (p < 0L) {
			if (n == 0L) {
				throw new UndefinedInverseException();
			}
			// n != 0
			return 0L; // floor(1 / n) == 0
		}
		// p >= 0

		if (n == 0L) {
			if (p == 0) {
				throw new IllegalArgumentException();
			}
			return 0L;
		} else if (n == 1L) {
			return 1L;
		}
		// (n != 0) && (n != 1)

		long result = 1L;
		boolean notExit = (p != 0);
		for (long n_to_2_to_i = n; notExit; /* Update inside. */) {
			/**
			 * Don't do <code>(p &= 1) != 0</code> since we need the value of <code>p</code> to remain
			 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
			 * will mutate <code>p</code>.
			 */
			if ((p & 1) != 0) {
				result = Math.multiplyExact(result, n_to_2_to_i);
			}
			// The following is meant to be an assignment of notExit and p.
			if (notExit = ((p /= 2) != 0)) {
				n_to_2_to_i = Math.multiplyExact(n_to_2_to_i, n_to_2_to_i);
			}
		}
		return result;
	}

	/**
	 * Compute <code>n<sup>p</sup></code> using the Fast Power (a.k.a. Successive Squaring) Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param p
	 *            the given power
	 * 
	 * @return <code>n<sup>p</sup></code>.
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(p < 0) && (n == 0)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>((p == 0) && (n == 0))
	 *             || ((Integer.MAX_VALUE < n<sup>p</sup>) || (n<sup>p</sup> < Integer.MIN_VALUE))</code>
	 */
	public static int powExact(int n, byte p) throws UndefinedInverseException, ArithmeticException {
		final long result = MathUtil.powExact((long) n, p);
		if ((Integer.MAX_VALUE < result) || (result < Integer.MIN_VALUE)) {
			throw new ArithmeticException();
		}
		// (Integer.MIN_VALUE <= result) && (result <= Integer.MAX_VALUE)
		return ((int) result);
	}

	/**
	 * Compute <code>n<sup>p</sup></code> using the Fast Power (a.k.a. Successive Squaring) Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param p
	 *            the given power
	 * 
	 * @return <code>n<sup>p</sup></code>.
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(p < 0) && (n == 0)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>((p == 0) && (n == 0))
	 *             || ((Short.MAX_VALUE < n<sup>p</sup>) || (n<sup>p</sup> < Short.MIN_VALUE))</code>
	 */
	public static short powExact(short n, byte p) throws UndefinedInverseException, ArithmeticException {
		final long result = MathUtil.powExact((long) n, p);
		if ((Short.MAX_VALUE < result) || (result < Short.MIN_VALUE)) {
			throw new ArithmeticException();
		}
		// (Short.MIN_VALUE <= result) && (result <= Short.MAX_VALUE)
		return ((short) result);
	}

	/**
	 * Compute <code>n<sup>p</sup></code> using the Fast Power (a.k.a. Successive Squaring) Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param p
	 *            the given power
	 * 
	 * @return <code>n<sup>p</sup></code>.
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(p < 0) && (n == 0)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>((p == 0) && (n == 0))
	 *             || ((Byte.MAX_VALUE < n<sup>p</sup>) || (n<sup>p</sup> < Byte.MIN_VALUE))</code>
	 */
	public static byte powExact(byte n, byte p) throws UndefinedInverseException, ArithmeticException {
		final long result = MathUtil.powExact((long) n, p);
		if ((Byte.MAX_VALUE < result) || (result < Byte.MIN_VALUE)) {
			throw new ArithmeticException();
		}
		// (Byte.MIN_VALUE <= result) && (result <= Byte.MAX_VALUE)
		return ((byte) result);
	}

	/**
	 * Precondition: <code>m > 0</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>n (mod m)</code>.
	 */
	protected static long modFixedInput(long n, long m) {
		return (((n %= m) < 0L) ? (n += m) : n);
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>n (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static long mod(long n, long m) throws InvalidModulusException {
		if (m < 1L) {
			throw new InvalidModulusException();
		}
		// m >= 1
		// i.e., m > 0
		return MathUtil.modFixedInput(n, m);
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>n (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static int mod(int n, int m) throws InvalidModulusException {
		if (m < 1) {
			throw new InvalidModulusException();
		}
		// m >= 1
		// i.e., m > 0
		return ((int) MathUtil.modFixedInput(n, m));
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>n (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static short mod(short n, short m) throws InvalidModulusException {
		if (m < 1) {
			throw new InvalidModulusException();
		}
		// m >= 1
		// i.e., m > 0
		return ((short) MathUtil.modFixedInput(n, m));
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>n (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static byte mod(byte n, byte m) throws InvalidModulusException {
		if (m < 1) {
			throw new InvalidModulusException();
		}
		// m >= 1
		// i.e., m > 0
		return ((byte) MathUtil.modFixedInput(n, m));
	}

	/**
	 * Precondition: <code>m > 1</code> <br>
	 * Precondition: <code>(1 <= n) && (n <= m - 1)</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>n<sup>-1</sup> (mod m)</code>.
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n, m) != 1</code>
	 */
	protected static long modInverseFixedInput(long n, long m) throws UndefinedInverseException {
		/**
		 * No need to check <code>gcd(n, m) != 1</code> since if that is the case, then in the body of the
		 * following loop, <code>remainder</code> will become <code>0</code> in some iteration.
		 */
		long x = 1L;
		for (long y = 0L, quotient = 0L, remainder = m, tmp = 0L; 1L < n; /* Update inside. */) {
			// The following is meant to be an assignment of tmp.
			if ((tmp = remainder) == 0L) {
				throw new UndefinedInverseException();
			}
			// Update quotient, remainder, and n.
			remainder = n - (quotient = (n / tmp)) * tmp;
			n = tmp;

			// Update x and y.
			tmp = y;
			y = x - quotient * y;
			x = tmp;
		}
		return ((x < 0L) ? (x += m) : x);
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>n<sup>-1</sup> (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 2</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n (mod m), m) != 1</code>
	 */
	public static long modInverse(long n, long m) throws InvalidModulusException, UndefinedInverseException {
		if (m < 2L) {
			throw new InvalidModulusException();
		}
		// m >= 2
		// i.e., m > 1

		// Fix n to be in [0, m - 1] \cap \doubleZ and handle the <code>n == 0</code> case.
		if ((n %= m) < 0L) {
			n += m;
		}
		if (n == 0L) {
			throw new UndefinedInverseException();
		}
		// n != 0
		// i.e., (1 <= n) && (n <= m - 1)
		return MathUtil.modInverseFixedInput(n, m);
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>n<sup>-1</sup> (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 2</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n (mod m), m) != 1</code>
	 */
	public static int modInverse(int n, int m) throws InvalidModulusException, UndefinedInverseException {
		if (m < 2) {
			throw new InvalidModulusException();
		}
		// m >= 2
		// i.e., m > 1

		// Fix n to be in [0, m - 1] \cap \doubleZ and handle the <code>n == 0</code> case.
		if ((n %= m) < 0) {
			n += m;
		}
		if (n == 0) {
			throw new UndefinedInverseException();
		}
		// n != 0
		// i.e., (1 <= n) && (n <= m - 1)
		return ((int) MathUtil.modInverseFixedInput(n, m));
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>n<sup>-1</sup> (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 2</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n (mod m), m) != 1</code>
	 */
	public static short modInverse(short n, short m) throws InvalidModulusException, UndefinedInverseException {
		if (m < 2) {
			throw new InvalidModulusException();
		}
		// m >= 2
		// i.e., m > 1

		// Fix n to be in [0, m - 1] \cap \doubleZ and handle the <code>n == 0</code> case.
		if ((n %= m) < 0) {
			n += m;
		}
		if (n == 0) {
			throw new UndefinedInverseException();
		}
		// n != 0
		// i.e., (1 <= n) && (n <= m - 1)
		return ((short) MathUtil.modInverseFixedInput(n, m));
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>n<sup>-1</sup> (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 2</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n (mod m), m) != 1</code>
	 */
	public static byte modInverse(byte n, byte m) throws InvalidModulusException, UndefinedInverseException {
		if (m < 2) {
			throw new InvalidModulusException();
		}
		// m >= 2
		// i.e., m > 1

		// Fix n to be in [0, m - 1] \cap \doubleZ and handle the <code>n == 0</code> case.
		if ((n %= m) < 0) {
			n += m;
		}
		if (n == 0) {
			throw new UndefinedInverseException();
		}
		// n != 0
		// i.e., (1 <= n) && (n <= m - 1)
		return ((byte) MathUtil.modInverseFixedInput(n, m));
	}

	/**
	 * Precondition: <code>m > 0</code> <br>
	 * Precondition: <code>|n| < m</code> <br>
	 * Postcondition: <code>|Result| <= (m / 2)</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>N</code> where <code>N (mod m) == n (mod m)</code>.
	 */
	protected static long modMinFixedInput(long n, long m) {
		if (n < 0L) {
			/**
			 * By the precondition on <code>n</code>, we know that <code>(-m + 1 <= n <= -1)</code>. Therefore,
			 * <code>(1 <= other <= m - 1)</code> and <code>-n</code> will not overflow since
			 * <code>(1 <= -n <= m - 1 < m <= Long.MAX_VALUE)</code>. <br>
			 * <br>
			 * 
			 * Don't do <code>n += m</code> or <code>n *= -1</code> since we need the value of <code>n</code> to
			 * remain unchanged.
			 */
			final long other = n + m, abs_n = -n;
			return ((abs_n < other) ? n : other);
		}
		// n >= 0
		/**
		 * By the precondition on <code>n</code>, we know that <code>(0 <= n <= m - 1)</code>. Therefore,
		 * <code>(-m <= other <= -1)</code> and so <code>other < 0</code> but <code>-other</code> will not
		 * overflow since <code>(1 <= -other <= m <= Long.MAX_VALUE)</code>. <br>
		 * <br>
		 * 
		 * Don't do <code>n -= m</code> or <code>other *= -1</code> since we need the value of
		 * <code>n</code> and <code>other</code> to remain unchanged.
		 */
		final long other = n - m, abs_other = -other;
		return ((abs_other < n) ? other : n);
	}

	/**
	 * Postcondition: <code>|Result| <= (m / 2)</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>N</code> where <code>N (mod m) == n (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static long modMin(long n, long m) throws InvalidModulusException {
		if (m < 1L) {
			throw new InvalidModulusException();
		}
		// m >= 1
		// i.e., m > 0
		return MathUtil.modMinFixedInput(n %= m, m);
	}

	/**
	 * Postcondition: <code>|Result| <= (m / 2)</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>N</code> where <code>N (mod m) == n (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static int modMin(int n, int m) throws InvalidModulusException {
		if (m < 1) {
			throw new InvalidModulusException();
		}
		// m >= 1
		// i.e., m > 0
		return ((int) MathUtil.modMinFixedInput(n %= m, m));
	}

	/**
	 * Postcondition: <code>|Result| <= (m / 2)</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>N</code> where <code>N (mod m) == n (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static short modMin(short n, short m) throws InvalidModulusException {
		if (m < 1) {
			throw new InvalidModulusException();
		}
		// m >= 1
		// i.e., m > 0
		return ((short) MathUtil.modMinFixedInput(n %= m, m));
	}

	/**
	 * Postcondition: <code>|Result| <= (m / 2)</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>N</code> where <code>N (mod m) == n (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static byte modMin(byte n, byte m) throws InvalidModulusException {
		if (m < 1) {
			throw new InvalidModulusException();
		}
		// m >= 1
		// i.e., m > 0
		return ((byte) MathUtil.modMinFixedInput(n %= m, m));
	}

	/**
	 * Precondition: <code>m > 0</code> <br>
	 * Precondition: <code>|a| <= (m / 2)</code> <br>
	 * Precondition: <code>|b| <= (m / 2)</code> <br>
	 * Postcondition: <code>|Result| <= (m / 2)</code>
	 * 
	 * @param a
	 *            the first given number
	 * 
	 * @param b
	 *            the second given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>N</code> where <code>N (mod m) == a + b (mod m)</code>.
	 */
	protected static long modAddFixedInput(long a, long b, long m) {
		return MathUtil.modMinFixedInput((a += b) % m, m);
	}

	/**
	 * @param a
	 *            the first given number
	 * 
	 * @param b
	 *            the second given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>a + b (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static long modAdd(long a, long b, long m) throws InvalidModulusException {
		if (m < 1L) {
			throw new InvalidModulusException();
		}
		// m >= 1
		// i.e., m > 0
		a = MathUtil.modMinFixedInput(a %= m, m);
		b = MathUtil.modMinFixedInput(b %= m, m);
		return (((a = (a += b) % m) < 0L) ? (a += m) : a);
	}

	/**
	 * @param a
	 *            the first given number
	 * 
	 * @param b
	 *            the second given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>a + b (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static int modAdd(int a, int b, int m) throws InvalidModulusException {
		if (m < 1) {
			throw new InvalidModulusException();
		}
		// m >= 1
		// i.e., m > 0
		a = (int) MathUtil.modMinFixedInput(a %= m, m);
		b = (int) MathUtil.modMinFixedInput(b %= m, m);
		return (((a = (a += b) % m) < 0) ? (a += m) : a);
	}

	/**
	 * @param a
	 *            the first given number
	 * 
	 * @param b
	 *            the second given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>a + b (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static short modAdd(short a, short b, short m) throws InvalidModulusException {
		if (m < 1) {
			throw new InvalidModulusException();
		}
		// m >= 1
		// i.e., m > 0
		a = (short) MathUtil.modMinFixedInput(a %= m, m);
		b = (short) MathUtil.modMinFixedInput(b %= m, m);
		return (((a = (short) ((a += b) % m)) < 0) ? (a += m) : a);
	}

	/**
	 * @param a
	 *            the first given number
	 * 
	 * @param b
	 *            the second given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>a + b (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static byte modAdd(byte a, byte b, byte m) throws InvalidModulusException {
		if (m < 1) {
			throw new InvalidModulusException();
		}
		// m >= 1
		// i.e., m > 0
		a = (byte) MathUtil.modMinFixedInput(a %= m, m);
		b = (byte) MathUtil.modMinFixedInput(b %= m, m);
		return (((a = (byte) ((a += b) % m)) < 0) ? (a += m) : a);
	}

	/**
	 * Precondition: <code>m > 0</code> <br>
	 * Precondition: <code>|a| <= (m / 2)</code> <br>
	 * Precondition: <code>|b| <= (m / 2)</code> <br>
	 * Postcondition: <code>|Result| <= (m / 2)</code>
	 * 
	 * @param a
	 *            the first given number
	 * 
	 * @param b
	 *            the second given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>N</code> where <code>N (mod m) == a - b (mod m)</code>.
	 */
	protected static long modSubFixedInput(long a, long b, long m) {
		return MathUtil.modMinFixedInput((a -= b) % m, m);
	}

	/**
	 * @param a
	 *            the first given number
	 * 
	 * @param b
	 *            the second given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>a - b (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static long modSub(long a, long b, long m) throws InvalidModulusException {
		if (m < 1L) {
			throw new InvalidModulusException();
		}
		// m >= 1
		// i.e., m > 0
		a = MathUtil.modMinFixedInput(a %= m, m);
		b = MathUtil.modMinFixedInput(b %= m, m);
		return (((a = (a -= b) % m) < 0L) ? (a += m) : a);
	}

	/**
	 * @param a
	 *            the first given number
	 * 
	 * @param b
	 *            the second given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>a - b (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static int modSub(int a, int b, int m) throws InvalidModulusException {
		if (m < 1) {
			throw new InvalidModulusException();
		}
		// m >= 1
		// i.e., m > 0
		a = (int) MathUtil.modMinFixedInput(a %= m, m);
		b = (int) MathUtil.modMinFixedInput(b %= m, m);
		return (((a = (a -= b) % m) < 0) ? (a += m) : a);
	}

	/**
	 * @param a
	 *            the first given number
	 * 
	 * @param b
	 *            the second given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>a - b (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static short modSub(short a, short b, short m) throws InvalidModulusException {
		if (m < 1) {
			throw new InvalidModulusException();
		}
		// m >= 1
		// i.e., m > 0
		a = (short) MathUtil.modMinFixedInput(a %= m, m);
		b = (short) MathUtil.modMinFixedInput(b %= m, m);
		return (((a = (short) ((a -= b) % m)) < 0) ? (a += m) : a);
	}

	/**
	 * @param a
	 *            the first given number
	 * 
	 * @param b
	 *            the second given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>a - b (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static byte modSub(byte a, byte b, byte m) throws InvalidModulusException {
		if (m < 1) {
			throw new InvalidModulusException();
		}
		// m >= 1
		// i.e., m > 0
		a = (byte) MathUtil.modMinFixedInput(a %= m, m);
		b = (byte) MathUtil.modMinFixedInput(b %= m, m);
		return (((a = (byte) ((a -= b) % m)) < 0) ? (a += m) : a);
	}

	/**
	 * First attempt to multiply <code>a</code> and <code>b</code> normally using
	 * <code>Math.multiplyExact</code> in <code>O(1) time</code>. If an overflow occurs during the
	 * multiplication, then perform <code>O(lg(min(a (mod m), b (mod m))))</code> many additions in
	 * <code>mod m</code> in <code>O(lg(min(a (mod m), b (mod m)))) time</code>. <br>
	 * Precondition: <code>m > 0</code> <br>
	 * Precondition: <code>|a| <= (m / 2)</code> <br>
	 * Precondition: <code>|b| <= (m / 2)</code> <br>
	 * Postcondition: <code>|Result| <= (m / 2)</code>
	 * 
	 * @param a
	 *            the first given number
	 * 
	 * @param b
	 *            the second given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>N</code> where <code>N (mod m) == a * b (mod m)</code>.
	 */
	protected static long modMultFixedInput(long a, long b, long m) {
		try {
			return MathUtil.modMinFixedInput(Math.multiplyExact(a, b) % m, m);
		} catch (ArithmeticException ex) {
			// a * b >= Long.MAX_VALUE
		}
		// (a != 0) && (b != 0)

		/**
		 * The following algorithm will never trigger an overflow due to the following invariants: <br>
		 * 1. <code>(|max| <= (m / 2)) implies (-(m / 2) <= max <= (m / 2))</code> <br>
		 * 2. <code>(|result| <= (m / 2)) implies (-(m / 2) <= result <= (m / 2))</code> <br>
		 * Therefore, when adding <code>result</code> and <code>max</code> or doubling <code>max</code>, we
		 * will get a number in <code>[-m, m] \cap \doubleZ</code>. Since
		 * <code>2 <= m <= Long.MAX_VALUE</code>, then every value in the result set is a valid
		 * <code>long</code> and so no overflows. The algorithm's runtime is in <code>O(lg(min))</code>.
		 */
		// The algorithm only works for min >= 0.

		// Assume a (mod m) is smaller than b (mod m).
		/**
		 * Don't do <code>(a < 0L) ? (a += m) : a</code> or <code>(b < 0L) ? (b += m) : b</code> since we
		 * still need to set <code>max</code> to either <code>b</code> or <code>a</code>. Note that the
		 * difference is the <code>+=</code> instead of the <code>+</code> which will mutate <code>a</code>
		 * and <code>b</code>.
		 */
		long min = (a < 0L) ? (a + m) : a, tmp = (b < 0L) ? (b + m) : b, max = b;
		// Fix min and max if needed.
		if (tmp < min) {
			min = tmp;
			max = a;
		}
		/**
		 * <code>(min == Math.min(MathUtil.mod(a, m), MathUtil.mod(b, m))) && (max == (min == MathUtil.mod(a, m)) ? b : a)</code>
		 */

		long result = 0L;
		for (boolean notExit = (min != 0L); notExit; /* Update inside. */) {
			/**
			 * Don't do <code>(min &= 1L) != 0L</code> since we need the value of <code>min</code> to remain
			 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
			 * will mutate <code>min</code>.
			 */
			if ((min & 1L) != 0L) { // i.e., !MathUtil.isEven(min)
				result = MathUtil.modMinFixedInput((result += max) % m, m);
			}
			// The following is meant to be an assignment of notExit and min.
			if (notExit = ((min /= 2L) != 0L)) {
				max = MathUtil.modMinFixedInput((max += max) % m, m); // Double max (mod m).
			}
		}
		return result;
	}

	/**
	 * @param a
	 *            the first given number
	 * 
	 * @param b
	 *            the second given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>a * b (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static long modMult(long a, long b, long m) throws InvalidModulusException {
		if (m < 1L) {
			throw new InvalidModulusException();
		}
		// m >= 1
		// i.e., m > 0
		long result = MathUtil.modMultFixedInput(MathUtil.modMinFixedInput(a %= m, m),
				MathUtil.modMinFixedInput(b %= m, m), m);
		return ((result < 0L) ? (result += m) : result);
	}

	/**
	 * @param a
	 *            the first given number
	 * 
	 * @param b
	 *            the second given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>a * b (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static int modMult(int a, int b, int m) throws InvalidModulusException {
		if (m < 1) {
			throw new InvalidModulusException();
		}
		// m >= 1
		// i.e., m > 0
		int result = (int) MathUtil.modMultFixedInput(MathUtil.modMinFixedInput(a %= m, m),
				MathUtil.modMinFixedInput(b %= m, m), m);
		return ((result < 0) ? (result += m) : result);
	}

	/**
	 * @param a
	 *            the first given number
	 * 
	 * @param b
	 *            the second given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>a * b (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static short modMult(short a, short b, short m) throws InvalidModulusException {
		if (m < 1) {
			throw new InvalidModulusException();
		}
		// m >= 1
		// i.e., m > 0
		short result = (short) MathUtil.modMultFixedInput(MathUtil.modMinFixedInput(a %= m, m),
				MathUtil.modMinFixedInput(b %= m, m), m);
		return ((result < 0) ? (result += m) : result);
	}

	/**
	 * @param a
	 *            the first given number
	 * 
	 * @param b
	 *            the second given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>a * b (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static byte modMult(byte a, byte b, byte m) throws InvalidModulusException {
		if (m < 1) {
			throw new InvalidModulusException();
		}
		// m >= 1
		// i.e., m > 0
		byte result = (byte) MathUtil.modMultFixedInput(MathUtil.modMinFixedInput(a %= m, m),
				MathUtil.modMinFixedInput(b %= m, m), m);
		return ((result < 0) ? (result += m) : result);
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
	 * @return The resulting long array.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>(m1 < 1) || (m2 < 1)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>Long.MAX_VALUE < lcm(m1, m2)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n1 != n2 (mod gcd(m1, m2))</code>
	 */
	public static long[] crt(long n1, long m1, long n2, long m2, boolean justAnswer)
			throws InvalidModulusException, ArithmeticException, IllegalArgumentException {
		if ((m1 < 1L) || (m2 < 1L)) {
			throw new InvalidModulusException();
		}
		// (m1 >= 1) && (m2 >= 1)
		// i.e., (m1 > 0) && (m2 > 0)

		// Find integers x and y such that x * m1 + y * m2 == gcd(m1, m2).
		final long[] x_y_gcd = MathUtil.gcdExtendedFixedInput(m1, m2);
		final long gcd = x_y_gcd[2];
		final boolean coprime = (gcd == 1L);

		// Compute the new modulus which is the least common multiple of m1 and m2.
		final long og_m1 = m1, og_m2 = m2;
		long m = 0L; // lcm(m1, m2) == (m1 * m2) / gcd(m1, m2)
		if (coprime) { // i.e., gcd == 1
			m = Math.multiplyExact(m1, m2);
		} else { // i.e., gcd != 1
			// Handle the invalid case.
			if (MathUtil.modFixedInput(n1, gcd) != MathUtil.modFixedInput(n2, gcd)) {
				throw new IllegalArgumentException();
			}
			m = Math.multiplyExact(m1 /= gcd, m2);
			m2 /= gcd;
		}
		// (m1 == og_m1 / gcd) && (m2 == og_m2 / gcd) && (m == lcm(og_m1, og_m2))

		// Fix all variables to be in [-m / 2, m / 2] \cap \doubleZ.
		long m1_inverse = MathUtil.modMinFixedInput(x_y_gcd[0] %= og_m2, m),
				m2_inverse = MathUtil.modMinFixedInput(x_y_gcd[1] %= og_m1, m);
		n1 = MathUtil.modMinFixedInput(n1 %= m, m);
		n2 = MathUtil.modMinFixedInput(n2 %= m, m);
		m1 = MathUtil.modMinFixedInput(m1, m);
		m2 = MathUtil.modMinFixedInput(m2, m);

		/*
		 * Apply the C.R.T. formula for two congruences but maintain all variables being in [-m / 2, m / 2]
		 * \cap \doubleZ during calculations.
		 */
		long lhs = MathUtil.modMultFixedInput(MathUtil.modMultFixedInput(n1, m2, m), m2_inverse, m);
		final long rhs = MathUtil.modMultFixedInput(MathUtil.modMultFixedInput(n2, m1, m), m1_inverse, m);
		if ((lhs = (lhs += rhs) % m) < 0L) {
			lhs += m;
		}

		// Return either just the answer or the answer along with all of the extra information.
		if (justAnswer) {
			return new long[] { lhs, m };
		}
		return new long[] { lhs, m, gcd, (((m1_inverse = x_y_gcd[0]) < 0L) ? (m1_inverse += og_m2) : m1_inverse),
				(((m2_inverse = x_y_gcd[1]) < 0L) ? (m2_inverse += og_m1) : m2_inverse) };
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
	 * @return The resulting long array.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>(m1 < 1) || (m2 < 1)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>Long.MAX_VALUE < lcm(m1, m2)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n1 != n2 (mod gcd(m1, m2))</code>
	 */
	public static long[] crt(long n1, long m1, long n2, long m2)
			throws InvalidModulusException, ArithmeticException, IllegalArgumentException {
		return MathUtil.crt(n1, m1, n2, m2, true);
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
	 * @return The resulting integer array.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>(m1 < 1) || (m2 < 1)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n1 != n2 (mod gcd(m1, m2))</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>Integer.MAX_VALUE < lcm(m1, m2)</code>
	 */
	public static int[] crt(int n1, int m1, int n2, int m2, boolean justAnswer)
			throws InvalidModulusException, IllegalArgumentException, ArithmeticException {
		final long[] result = MathUtil.crt((long) n1, (long) m1, (long) n2, (long) m2, justAnswer);
		final long m = result[1]; // lcm(m1, m2)
		if (Integer.MAX_VALUE < m) {
			throw new ArithmeticException();
		}

		// Return either just the answer or the answer along with all of the extra information.
		if (justAnswer) {
			return new int[] { (int) result[0], (int) m };
		}
		return new int[] { (int) result[0], (int) m, (int) result[2], (int) result[3], (int) result[4] };
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
	 * @return The resulting integer array.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>(m1 < 1) || (m2 < 1)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n1 != n2 (mod gcd(m1, m2))</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>Integer.MAX_VALUE < lcm(m1, m2)</code>
	 */
	public static int[] crt(int n1, int m1, int n2, int m2)
			throws InvalidModulusException, IllegalArgumentException, ArithmeticException {
		return MathUtil.crt(n1, m1, n2, m2, true);
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
	 * @return The resulting short array.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>(m1 < 1) || (m2 < 1)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n1 != n2 (mod gcd(m1, m2))</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>Short.MAX_VALUE < lcm(m1, m2)</code>
	 */
	public static short[] crt(short n1, short m1, short n2, short m2, boolean justAnswer)
			throws InvalidModulusException, IllegalArgumentException, ArithmeticException {
		final long[] result = MathUtil.crt((long) n1, (long) m1, (long) n2, (long) m2, justAnswer);
		final long m = result[1]; // lcm(m1, m2)
		if (Short.MAX_VALUE < m) {
			throw new ArithmeticException();
		}

		// Return either just the answer or the answer along with all of the extra information.
		if (justAnswer) {
			return new short[] { (short) result[0], (short) m };
		}
		return new short[] { (short) result[0], (short) m, (short) result[2], (short) result[3], (short) result[4] };
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
	 * @return The resulting short array.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>(m1 < 1) || (m2 < 1)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n1 != n2 (mod gcd(m1, m2))</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>Short.MAX_VALUE < lcm(m1, m2)</code>
	 */
	public static short[] crt(short n1, short m1, short n2, short m2)
			throws InvalidModulusException, IllegalArgumentException, ArithmeticException {
		return MathUtil.crt(n1, m1, n2, m2, true);
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
	 * @return The resulting byte array.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>(m1 < 1) || (m2 < 1)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n1 != n2 (mod gcd(m1, m2))</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>Byte.MAX_VALUE < lcm(m1, m2)</code>
	 */
	public static byte[] crt(byte n1, byte m1, byte n2, byte m2, boolean justAnswer)
			throws InvalidModulusException, IllegalArgumentException, ArithmeticException {
		final long[] result = MathUtil.crt((long) n1, (long) m1, (long) n2, (long) m2, justAnswer);
		final long m = result[1]; // lcm(m1, m2)
		if (Byte.MAX_VALUE < m) {
			throw new ArithmeticException();
		}

		// Return either just the answer or the answer along with all of the extra information.
		if (justAnswer) {
			return new byte[] { (byte) result[0], (byte) m };
		}
		return new byte[] { (byte) result[0], (byte) m, (byte) result[2], (byte) result[3], (byte) result[4] };
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
	 * @return The resulting byte array.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>(m1 < 1) || (m2 < 1)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n1 != n2 (mod gcd(m1, m2))</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>Byte.MAX_VALUE < lcm(m1, m2)</code>
	 */
	public static byte[] crt(byte n1, byte m1, byte n2, byte m2)
			throws InvalidModulusException, IllegalArgumentException, ArithmeticException {
		return MathUtil.crt(n1, m1, n2, m2, true);
	}

	/**
	 * Compute <code>n<sup>p</sup> (mod m)</code> using the Fast Power (a.k.a. Successive Squaring)
	 * Algorithm. <br>
	 * Note that this function does not check for the special cases <code>n == &plusmn;1 (mod m)</code>
	 * and so it will still take <code>O(lg(p))</code> steps even though the answer can be trivially
	 * determined in <code>O(1)</code> steps. Therefore, for the best performance, it is recommended to
	 * check those cases before calling this function. The reason why it does not check for the special
	 * cases, is that this function is specified as protected and is only called by other public
	 * functions which do handle those special cases themselves (in their own unique ways) and so
	 * checking for the special cases here, would only serve to decrease the overall runtime. <br>
	 * Precondition: <code>m > 1</code> <br>
	 * Precondition: <code>0 < |n| < m</code> <br>
	 * Precondition: <code>p >= 0</code> <br>
	 * Postcondition: <code>|Result| <= (m / 2)</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param p
	 *            the given power
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>N</code> where <code>N (mod m) == n<sup>p</sup> (mod m)</code>.
	 */
	protected static long modPowFixedInput(long n, long p, long m) {
		long result = 1L;
		boolean notExit = (p != 0L);
		for (long n_to_2_to_i = MathUtil.modMinFixedInput(n, m); notExit; /* Update inside. */) {
			/**
			 * Don't do <code>(p &= 1L) != 0L</code> since we need the value of <code>p</code> to remain
			 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
			 * will mutate <code>p</code>.
			 */
			if ((p & 1L) != 0L) { // i.e., !MathUtil.isEven(p)
				result = MathUtil.modMultFixedInput(result, n_to_2_to_i, m);
			}
			// The following is meant to be an assignment of notExit and p.
			if (notExit = ((p /= 2L) != 0L)) {
				n_to_2_to_i = MathUtil.modMultFixedInput(n_to_2_to_i, n_to_2_to_i, m); // Square n_to_2_to_i (mod m).
			}
		}
		return result;
	}

	/**
	 * Compute <code>n<sup>p</sup> (mod m)</code> using the Fast Power (a.k.a. Successive Squaring)
	 * Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param p
	 *            the given power
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>n<sup>p</sup> (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(p < 0) && ((n (mod m) == 0) || (gcd(n (mod m), m) != 1))</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(p == 0) && (n (mod m) == 0)</code>
	 */
	public static long modPow(long n, long p, long m)
			throws InvalidModulusException, UndefinedInverseException, ArithmeticException {
		if (m < 2L) { // i.e., (m < 1) || (m == 1)
			if (m < 1L) {
				throw new InvalidModulusException();
			}
			// m >= 1
			// i.e., m == 1
			// i.e., n == 0 (mod m)
			if (p < 0L) {
				throw new UndefinedInverseException();
			} else if (p == 0L) {
				throw new ArithmeticException();
			}
			return 0L;
		}
		// m >= 2

		// Fix n to be in [0, m - 1] \cap \doubleZ and handle the simple special cases.
		if ((n %= m) < 0L) {
			n += m;
		}
		if (n < 2L) { // i.e., (n == 0) || (n == 1)
			if (n == 0L) {
				if (p < 0L) {
					throw new UndefinedInverseException();
				} else if (p == 0L) {
					throw new ArithmeticException();
				}
				return 0L;
			}
			// n != 0
			// i.e., n == 1
			// 1 to any power is 1.
			return 1L;
		}
		// n >= 2
		// i.e., (1 < n) && (n <= m - 1) && (m > 2)
		if (n == m - 1L) { // i.e., n == -1 (mod m)
			/**
			 * <code>-1<sup>p</sup> (mod m)</code> is: <br>
			 * <code>1 (mod m)</code> if <code>p</code> is even <br>
			 * <code>-1 (mod m)</code> if <code>p</code> is odd <br>
			 * <br>
			 * 
			 * It's fine to do <code>(p &= 1L) == 0L</code> instead of <code>(p & 1L) == 0L</code> since we
			 * don't need the value of <code>p</code> to remain unchanged. Note that the difference is the
			 * <code>&=</code> instead of the <code>&</code> which will mutate <code>p</code>.
			 */
			return (((p &= 1L) == 0L) ? 1L : n);
		}
		// n != m - 1
		// i.e., (1 < n) && (n < m - 1) && (m > 3)

		/**
		 * <code>n<sup>p</sup> (mod m)</code> is: <br>
		 * <code>n<sup>|p|</sup> (mod m)</code> if <code>p > 0</code> <br>
		 * <code>1</code> if <code>p == 0</code> <br>
		 * <code>(n<sup>-1</sup> (mod m))<sup>|p|</sup> (mod m)</code> if <code>p < 0</code>
		 */
		if (0L < p) {
			long result = MathUtil.modPowFixedInput(n, p, m);
			return ((result < 0L) ? (result += m) : result);
		} else if (p == 0L) {
			return 1L;
		}
		// p < 0
		// Compute the multiplicative inverse of n in mod m.
		final long n_inverse = MathUtil.modInverseFixedInput(n, m);
		// Handle the degenerate case where p's absolute value is not representable as a non-negative long.
		if (p == Long.MIN_VALUE) { // i.e., -p == p < 0
			/**
			 * <code>n<sup>(-2<sup>63</sup>)</sup> (mod m) == (n<sup>-1</sup>)<sup>(2<sup>63</sup> - 1)</sup> * n<sup>-1</sup> (mod m)</code>
			 */
			long result = MathUtil.modPowFixedInput(n_inverse, Long.MAX_VALUE, m);
			result = MathUtil.modMultFixedInput(result, MathUtil.modMinFixedInput(n_inverse, m), m);
			return ((result < 0L) ? (result += m) : result);
		}
		// p != Long.MIN_VALUE
		// i.e., -p > 0
		/**
		 * It's fine to do <code>p *= -1</code> instead of <code>-1 * p</code> since we don't need the value
		 * of <code>p</code> to remain unchanged. Note that the difference is the <code>*=</code> instead of
		 * the <code>*</code> which will mutate <code>p</code>.
		 */
		long result = MathUtil.modPowFixedInput(n_inverse, p *= -1L, m);
		return ((result < 0L) ? (result += m) : result);
	}

	/**
	 * Compute <code>n<sup>p</sup> (mod m)</code> using the Fast Power (a.k.a. Successive Squaring)
	 * Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param p
	 *            the given power
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>n<sup>p</sup> (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(p < 0) && ((n (mod m) == 0) || (gcd(n (mod m), m) != 1))</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(p == 0) && (n (mod m) == 0)</code>
	 */
	public static int modPow(int n, int p, int m)
			throws InvalidModulusException, UndefinedInverseException, ArithmeticException {
		return ((int) MathUtil.modPow((long) n, (long) p, (long) m));
	}

	/**
	 * Compute <code>n<sup>p</sup> (mod m)</code> using the Fast Power (a.k.a. Successive Squaring)
	 * Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param p
	 *            the given power
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>n<sup>p</sup> (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(p < 0) && ((n (mod m) == 0) || (gcd(n (mod m), m) != 1))</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(p == 0) && (n (mod m) == 0)</code>
	 */
	public static short modPow(short n, short p, short m)
			throws InvalidModulusException, UndefinedInverseException, ArithmeticException {
		return ((short) MathUtil.modPow((long) n, (long) p, (long) m));
	}

	/**
	 * Compute <code>n<sup>p</sup> (mod m)</code> using the Fast Power (a.k.a. Successive Squaring)
	 * Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param p
	 *            the given power
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>n<sup>p</sup> (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(p < 0) && ((n (mod m) == 0) || (gcd(n (mod m), m) != 1))</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(p == 0) && (n (mod m) == 0)</code>
	 */
	public static byte modPow(byte n, byte p, byte m)
			throws InvalidModulusException, UndefinedInverseException, ArithmeticException {
		return ((byte) MathUtil.modPow((long) n, (long) p, (long) m));
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
	 * @throws IllegalArgumentException
	 *             If <code>end < begin</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>Integer.MAX_VALUE < (end - begin)</code>
	 */
	protected static int powersLength(long begin, long end) throws IllegalArgumentException, ArithmeticException {
		// Validate begin and end.
		if (end < begin) {
			throw new IllegalArgumentException();
		}
		// begin <= end

		if (begin == end) {
			return 0;
		}
		// begin < end

		if (-1L < begin) { // i.e., begin >= 0
			// 0 <= begin < end so end - begin will not overflow a long.
			end -= begin;
			if (Integer.MAX_VALUE < end) {
				throw new ArithmeticException();
			}
			return ((int) end);
		}
		// begin <= -1
		// i.e., begin < 0

		// Therefore, Integer.MAX_VALUE + begin will not overflow a long.
		if (Integer.MAX_VALUE + begin < end) {
			throw new ArithmeticException();
		}
		// end <= Integer.MAX_VALUE + begin

		/*
		 * Handle the degenerate case where begin's absolute value is not representable as a non-negative
		 * long.
		 */
		if (begin == Long.MIN_VALUE) { // i.e., -begin == begin < 0
			// -begin == Long.MAX_VALUE + 1 so length == end + (Long.MAX_VALUE + 1)
			/**
			 * Due to the above check, we know that <code>end <= Integer.MAX_VALUE + Long.MIN_VALUE</code> which
			 * is much less than <code>0</code> and so <code>end + Long.MAX_VALUE + 1</code> will not overflow a
			 * long.
			 */
			return ((int) ((end += Long.MAX_VALUE) + 1L));
		}
		// begin != Long.MIN_VALUE
		// i.e., -begin > 0
		return ((int) (end -= begin));
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
	 * @return The resulting long array.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>end < begin</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>Integer.MAX_VALUE < (end - begin)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(begin < 0) && ((n (mod m) == 0) || (gcd(n (mod m), m) != 1))</code>
	 */
	public static long[] modPowers(long n, long m, long begin, long end)
			throws InvalidModulusException, IllegalArgumentException, ArithmeticException, UndefinedInverseException {
		if (m < 1L) {
			throw new InvalidModulusException();
		}
		// m >= 1
		// i.e., m > 0

		// Compute the resulting array length.
		final int length = MathUtil.powersLength(begin, end);
		// (begin <= end) && ((end - begin) <= Integer.MAX_VALUE)

		// Create the resulting long array and handle the simple special cases.
		final long[] result = new long[length];
		if (length == 0) {
			// Nothing to do here.
			return result;
		}
		// length != 0
		// i.e., length > 0

		// Fix n to be in [0, m - 1] \cap \doubleZ.
		if ((n %= m) < 0L) {
			n += m;
		}

		if (n < 2L) { // i.e., (n == 0) || (n == 1)
			if (n == 0L) {
				/**
				 * This case is needed since 0 to any positive power is 0 and so any non-zero assignment of
				 * <code>result[i]</code> will be wrong in this case. Furthermore, note that we are defining
				 * <code>0<sup>0</sup> == 0</code> here even though it is undefined in math.
				 */
				if (begin < 0L) {
					throw new UndefinedInverseException();
				}
				// begin >= 0
				return result;
			}
			// n != 0
			// i.e., n == 1
			/*
			 * This case is only an optimization since 1 to any power is 1 and so the loop will do extra
			 * unnecessary work to arrive at the same result.
			 */
			Arrays.fill(result, 1L);
			return result;
		}
		// n >= 2
		// i.e., (1 < n) && (n <= m - 1) && (m > 2)
		if (n == m - 1L) { // i.e., n == -1 (mod m)
			/*
			 * This case is only an optimization since -1 to any even power is 1 and otherwise is -1. So the
			 * loop will do extra unnecessary work to arrive at the same result.
			 */
			/**
			 * It's fine to do <code>(begin &= 1L) == 0L</code> instead of <code>(begin & 1L) == 0L</code> since
			 * we don't need the value of <code>begin</code> to remain unchanged. Note that the difference is
			 * the <code>&=</code> instead of the <code>&</code> which will mutate <code>begin</code>.
			 */
			boolean evenPow = ((begin &= 1L) == 0L); // i.e., MathUtil.isEven(begin)
			for (int i = 0; i != length; ++i, evenPow = !evenPow) {
				result[i] = evenPow ? 1L : n;
			}
			return result;
		}
		// n != m - 1
		// i.e., (1 < n) && (n < m - 1) && (m > 3)

		// Fix n to be in [-m / 2, m / 2] \cap \doubleZ.
		n = MathUtil.modMinFixedInput(n, m);

		// Fill and return the resulting long array.
		long n_to_i = (begin == 0L) ? 1L : MathUtil.modPow(n, begin, m);
		for (int i = 0; i != length; ++i, n_to_i = MathUtil.modMultFixedInput(n_to_i, n, m)) {
			/**
			 * Don't do <code>(n_to_i < 0L) ? (n_to_i += m) : n_to_i</code> since we want to maintain the
			 * following invariant <code>|n_to_i| <= (m / 2)</code>. Note that the difference is the
			 * <code>+=</code> instead of the <code>+</code> which will set <code>n_to_i</code> to
			 * <code>n_to_i (mod m)</code> which may violate the invariant.
			 */
			result[i] = (n_to_i < 0L) ? (n_to_i + m) : n_to_i;
		}
		return result;
	}

	/**
	 * Note that this function defines <code>0<sup>0</sup> == 0</code> even though it is undefined in
	 * math. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.length == end</code> <br>
	 * Postcondition: <code>(valid i) implies (Result[i] == n<sup>i</sup> (mod m))</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param end
	 *            the given end power
	 * 
	 * @return <code>MathUtil.modPowers(n, m, 0L, end)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>end < 0</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>Integer.MAX_VALUE < end</code>
	 */
	public static long[] modPowers(long n, long m, long end)
			throws InvalidModulusException, IllegalArgumentException, ArithmeticException {
		return MathUtil.modPowers(n, m, 0L, end);
	}

	/**
	 * Note that this function defines <code>0<sup>0</sup> == 0</code> even though it is undefined in
	 * math. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.length == m</code> <br>
	 * Postcondition: <code>(valid i) implies (Result[i] == n<sup>i</sup> (mod m))</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>MathUtil.modPowers(n, m, m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>Integer.MAX_VALUE < m</code>
	 */
	public static long[] modPowers(long n, long m) throws InvalidModulusException, ArithmeticException {
		return MathUtil.modPowers(n, m, m);
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
	 * @return The resulting integer array.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>end < begin</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>Integer.MAX_VALUE < (end - begin)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(begin < 0) && ((n (mod m) == 0) || (gcd(n (mod m), m) != 1))</code>
	 */
	public static int[] modPowers(int n, int m, int begin, int end)
			throws InvalidModulusException, IllegalArgumentException, ArithmeticException, UndefinedInverseException {
		if (m < 1) {
			throw new InvalidModulusException();
		}
		// m >= 1
		// i.e., m > 0

		// Compute the resulting array length.
		final int length = MathUtil.powersLength(begin, end);
		// (begin <= end) && ((end - begin) <= Integer.MAX_VALUE)

		// Create the resulting integer array and handle the simple special cases.
		final int[] result = new int[length];
		if (length == 0) {
			// Nothing to do here.
			return result;
		}
		// length != 0
		// i.e., length > 0

		// Fix n to be in [0, m - 1] \cap \doubleZ.
		if ((n %= m) < 0) {
			n += m;
		}

		if (n < 2) { // i.e., (n == 0) || (n == 1)
			if (n == 0) {
				/**
				 * This case is needed since 0 to any positive power is 0 and so any non-zero assignment of
				 * <code>result[i]</code> will be wrong in this case. Furthermore, note that we are defining
				 * <code>0<sup>0</sup> == 0</code> here even though it is undefined in math.
				 */
				if (begin < 0) {
					throw new UndefinedInverseException();
				}
				// begin >= 0
				return result;
			}
			// n != 0
			// i.e., n == 1
			/*
			 * This case is only an optimization since 1 to any power is 1 and so the loop will do extra
			 * unnecessary work to arrive at the same result.
			 */
			Arrays.fill(result, 1);
			return result;
		}
		// n >= 2
		// i.e., (1 < n) && (n <= m - 1) && (m > 2)
		if (n == m - 1) { // i.e., n == -1 (mod m)
			/*
			 * This case is only an optimization since -1 to any even power is 1 and otherwise is -1. So the
			 * loop will do extra unnecessary work to arrive at the same result.
			 */
			/**
			 * It's fine to do <code>(begin &= 1) == 0</code> instead of <code>(begin & 1) == 0</code> since we
			 * don't need the value of <code>begin</code> to remain unchanged. Note that the difference is the
			 * <code>&=</code> instead of the <code>&</code> which will mutate <code>begin</code>.
			 */
			boolean evenPow = ((begin &= 1) == 0); // i.e., MathUtil.isEven(begin)
			for (int i = 0; i != length; ++i, evenPow = !evenPow) {
				result[i] = evenPow ? 1 : n;
			}
			return result;
		}
		// n != m - 1
		// i.e., (1 < n) && (n < m - 1) && (m > 3)

		// Fix n to be in [-m / 2, m / 2] \cap \doubleZ.
		n = (int) MathUtil.modMinFixedInput(n, m);

		// Fill and return the resulting int array.
		int n_to_i = (begin == 0) ? 1 : (int) MathUtil.modPow((long) n, (long) begin, (long) m);
		for (int i = 0; i != length; ++i, n_to_i = (int) MathUtil.modMultFixedInput(n_to_i, n, m)) {
			/**
			 * Don't do <code>(n_to_i < 0) ? (n_to_i += m) : n_to_i</code> since we want to maintain the
			 * following invariant <code>|n_to_i| <= (m / 2)</code>. Note that the difference is the
			 * <code>+=</code> instead of the <code>+</code> which will set <code>n_to_i</code> to
			 * <code>n_to_i (mod m)</code> which may violate the invariant.
			 */
			result[i] = (n_to_i < 0) ? (n_to_i + m) : n_to_i;
		}
		return result;
	}

	/**
	 * Note that this function defines <code>0<sup>0</sup> == 0</code> even though it is undefined in
	 * math. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.length == end</code> <br>
	 * Postcondition: <code>(valid i) implies (Result[i] == n<sup>i</sup> (mod m))</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param end
	 *            the given end power
	 * 
	 * @return <code>MathUtil.modPowers(n, m, 0, end)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>end < 0</code>
	 */
	public static int[] modPowers(int n, int m, int end) throws InvalidModulusException, IllegalArgumentException {
		return MathUtil.modPowers(n, m, 0, end);
	}

	/**
	 * Note that this function defines <code>0<sup>0</sup> == 0</code> even though it is undefined in
	 * math. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.length == m</code> <br>
	 * Postcondition: <code>(valid i) implies (Result[i] == n<sup>i</sup> (mod m))</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>MathUtil.modPowers(n, m, m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static int[] modPowers(int n, int m) throws InvalidModulusException {
		return MathUtil.modPowers(n, m, m);
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
	 * @return The resulting short array.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>end < begin</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(begin < 0) && ((n (mod m) == 0) || (gcd(n (mod m), m) != 1))</code>
	 */
	public static short[] modPowers(short n, short m, short begin, short end)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		if (m < 1) {
			throw new InvalidModulusException();
		}
		// m >= 1
		// i.e., m > 0

		// Compute the resulting array length.
		final int length = MathUtil.powersLength(begin, end);
		// (begin <= end) && ((end - begin) <= Integer.MAX_VALUE)

		// Create the resulting short array and handle the simple special cases.
		final short[] result = new short[length];
		if (length == 0) {
			// Nothing to do here.
			return result;
		}
		// length != 0
		// i.e., length > 0

		// Fix n to be in [0, m - 1] \cap \doubleZ.
		if ((n %= m) < 0) {
			n += m;
		}

		if (n < 2) { // i.e., (n == 0) || (n == 1)
			if (n == 0) {
				/**
				 * This case is needed since 0 to any positive power is 0 and so any non-zero assignment of
				 * <code>result[i]</code> will be wrong in this case. Furthermore, note that we are defining
				 * <code>0<sup>0</sup> == 0</code> here even though it is undefined in math.
				 */
				if (begin < 0) {
					throw new UndefinedInverseException();
				}
				// begin >= 0
				return result;
			}
			// n != 0
			// i.e., n == 1
			/*
			 * This case is only an optimization since 1 to any power is 1 and so the loop will do extra
			 * unnecessary work to arrive at the same result.
			 */
			Arrays.fill(result, (short) 1);
			return result;
		}
		// n >= 2
		// i.e., (1 < n) && (n <= m - 1) && (m > 2)
		if (n == m - 1) { // i.e., n == -1 (mod m)
			/*
			 * This case is only an optimization since -1 to any even power is 1 and otherwise is -1. So the
			 * loop will do extra unnecessary work to arrive at the same result.
			 */
			/**
			 * It's fine to do <code>(begin &= 1) == 0</code> instead of <code>(begin & 1) == 0</code> since we
			 * don't need the value of <code>begin</code> to remain unchanged. Note that the difference is the
			 * <code>&=</code> instead of the <code>&</code> which will mutate <code>begin</code>.
			 */
			boolean evenPow = ((begin &= 1) == 0); // i.e., MathUtil.isEven(begin)
			for (int i = 0; i != length; ++i, evenPow = !evenPow) {
				result[i] = evenPow ? 1 : n;
			}
			return result;
		}
		// n != m - 1
		// i.e., (1 < n) && (n < m - 1) && (m > 3)

		// Fix n to be in [-m / 2, m / 2] \cap \doubleZ.
		n = (short) MathUtil.modMinFixedInput(n, m);

		// Fill and return the resulting short array.
		short n_to_i = (begin == 0) ? 1 : (short) MathUtil.modPow((long) n, (long) begin, (long) m);
		for (int i = 0; i != length; ++i, n_to_i = (short) MathUtil.modMultFixedInput(n_to_i, n, m)) {
			/**
			 * Don't do <code>(n_to_i < 0) ? (n_to_i += m) : n_to_i</code> since we want to maintain the
			 * following invariant <code>|n_to_i| <= (m / 2)</code>. Note that the difference is the
			 * <code>+=</code> instead of the <code>+</code> which will set <code>n_to_i</code> to
			 * <code>n_to_i (mod m)</code> which may violate the invariant.
			 */
			result[i] = (short) ((n_to_i < 0) ? (n_to_i + m) : n_to_i);
		}
		return result;
	}

	/**
	 * Note that this function defines <code>0<sup>0</sup> == 0</code> even though it is undefined in
	 * math. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.length == end</code> <br>
	 * Postcondition: <code>(valid i) implies (Result[i] == n<sup>i</sup> (mod m))</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param end
	 *            the given end power
	 * 
	 * @return <code>MathUtil.modPowers(n, m, (short) 0, end)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>end < 0</code>
	 */
	public static short[] modPowers(short n, short m, short end)
			throws InvalidModulusException, IllegalArgumentException {
		return MathUtil.modPowers(n, m, (short) 0, end);
	}

	/**
	 * Note that this function defines <code>0<sup>0</sup> == 0</code> even though it is undefined in
	 * math. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.length == m</code> <br>
	 * Postcondition: <code>(valid i) implies (Result[i] == n<sup>i</sup> (mod m))</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>MathUtil.modPowers(n, m, m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static short[] modPowers(short n, short m) throws InvalidModulusException {
		return MathUtil.modPowers(n, m, m);
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
	 * @return The resulting byte array.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>end < begin</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(begin < 0) && ((n (mod m) == 0) || (gcd(n (mod m), m) != 1))</code>
	 */
	public static byte[] modPowers(byte n, byte m, byte begin, byte end)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		if (m < 1) {
			throw new InvalidModulusException();
		}
		// m >= 1
		// i.e., m > 0

		// Compute the resulting array length.
		final int length = MathUtil.powersLength(begin, end);
		// (begin <= end) && ((end - begin) <= Integer.MAX_VALUE)

		// Create the resulting byte array and handle the simple special cases.
		final byte[] result = new byte[length];
		if (length == 0) {
			// Nothing to do here.
			return result;
		}
		// length != 0
		// i.e., length > 0

		// Fix n to be in [0, m - 1] \cap \doubleZ.
		if ((n %= m) < 0) {
			n += m;
		}

		if (n < 2) { // i.e., (n == 0) || (n == 1)
			if (n == 0) {
				/**
				 * This case is needed since 0 to any positive power is 0 and so any non-zero assignment of
				 * <code>result[i]</code> will be wrong in this case. Furthermore, note that we are defining
				 * <code>0<sup>0</sup> == 0</code> here even though it is undefined in math.
				 */
				if (begin < 0) {
					throw new UndefinedInverseException();
				}
				// begin >= 0
				return result;
			}
			// n != 0
			// i.e., n == 1
			/*
			 * This case is only an optimization since 1 to any power is 1 and so the loop will do extra
			 * unnecessary work to arrive at the same result.
			 */
			Arrays.fill(result, (byte) 1);
			return result;
		}
		// n >= 2
		// i.e., (1 < n) && (n <= m - 1) && (m > 2)
		if (n == m - 1) { // i.e., n == -1 (mod m)
			/*
			 * This case is only an optimization since -1 to any even power is 1 and otherwise is -1. So the
			 * loop will do extra unnecessary work to arrive at the same result.
			 */
			/**
			 * It's fine to do <code>(begin &= 1) == 0</code> instead of <code>(begin & 1) == 0</code> since we
			 * don't need the value of <code>begin</code> to remain unchanged. Note that the difference is the
			 * <code>&=</code> instead of the <code>&</code> which will mutate <code>begin</code>.
			 */
			boolean evenPow = ((begin &= 1) == 0); // i.e., MathUtil.isEven(begin)
			for (int i = 0; i != length; ++i, evenPow = !evenPow) {
				result[i] = evenPow ? 1 : n;
			}
			return result;
		}
		// n != m - 1
		// i.e., (1 < n) && (n < m - 1) && (m > 3)

		// Fix n to be in [-m / 2, m / 2] \cap \doubleZ.
		n = (byte) MathUtil.modMinFixedInput(n, m);

		// Fill and return the resulting byte array.
		byte n_to_i = (begin == 0) ? 1 : (byte) MathUtil.modPow((long) n, (long) begin, (long) m);
		for (int i = 0; i != length; ++i, n_to_i = (byte) MathUtil.modMultFixedInput(n_to_i, n, m)) {
			/**
			 * Don't do <code>(n_to_i < 0) ? (n_to_i += m) : n_to_i</code> since we want to maintain the
			 * following invariant <code>|n_to_i| <= (m / 2)</code>. Note that the difference is the
			 * <code>+=</code> instead of the <code>+</code> which will set <code>n_to_i</code> to
			 * <code>n_to_i (mod m)</code> which may violate the invariant.
			 */
			result[i] = (byte) ((n_to_i < 0) ? (n_to_i + m) : n_to_i);
		}
		return result;
	}

	/**
	 * Note that this function defines <code>0<sup>0</sup> == 0</code> even though it is undefined in
	 * math. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.length == end</code> <br>
	 * Postcondition: <code>(valid i) implies (Result[i] == n<sup>i</sup> (mod m))</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param end
	 *            the given end power
	 * 
	 * @return <code>MathUtil.modPowers(n, m, (byte) 0, end)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>end < 0</code>
	 */
	public static byte[] modPowers(byte n, byte m, byte end) throws InvalidModulusException, IllegalArgumentException {
		return MathUtil.modPowers(n, m, (byte) 0, end);
	}

	/**
	 * Note that this function defines <code>0<sup>0</sup> == 0</code> even though it is undefined in
	 * math. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.length == m</code> <br>
	 * Postcondition: <code>(valid i) implies (Result[i] == n<sup>i</sup> (mod m))</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>MathUtil.modPowers(n, m, m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static byte[] modPowers(byte n, byte m) throws InvalidModulusException {
		return MathUtil.modPowers(n, m, m);
	}

	/**
	 * Check the following trivial cases: <br>
	 * 1. <code>n == target</code> <br>
	 * 2. <code>(n == 0) || (n == 1)</code> <br>
	 * 3. <code>target == 1</code> <br>
	 * 4. <code>n == m - 1</code> <br>
	 * Precondition: <code>m > 0</code> <br>
	 * Precondition: <code>(0 <= n) && (n <= m - 1)</code> <br>
	 * Precondition: <code>(0 <= target) && (target <= m - 1)</code> <br>
	 * Postcondition:
	 * <code>((Result != null) && (Result == -1)) implies ((m > 3) && (1 < n) && (n < m - 1) && (target != 1) && (n != target))</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> if such an
	 *         <code>x</code> exists and <code>null</code> otherwise. Note that this function only
	 *         checks the trivial cases and if <code>x</code> cannot be determined from them, it returns
	 *         <code>-1</code>.
	 */
	protected static Long discreteLogTrivialFixedInput(long n, long target, long m) {
		if (n == target) {
			return 1L;
		}
		// n != target

		if (n < 2L) { // i.e., (n == 0) || (n == 1)
			// i.e., ((n == 0) && (target != 0)) || ((n == 1) && (target != 1))
			return null;
		}
		// n >= 2
		// i.e., (1 < n) && (n <= m - 1) && (m > 2)

		if (target == 1L) {
			// n to the power of 0 is 1 except when n is 0 which we know isn't the case.
			return 0L;
		}
		// target != 1

		/**
		 * It's fine to do <code>++n</code> instead of <code>n + 1</code> since we don't need the value of
		 * <code>n</code> to remain unchanged.
		 */
		if (++n == m) { // i.e., n == -1 (mod m)
			// -1 to any even power is 1 (but target != 1) and otherwise is -1 (but target != -1).
			return null;
		}
		// n != m - 1
		// i.e., (1 < n) && (n < m - 1) && (m > 3)

		// Cannot determine the answer since it is non-trivial.
		return -1L;
	}

	/**
	 * Perform a linear search for <code>x</code> in <code>[begin, end)</code> such that
	 * <code>n<sup>x</sup> (mod m) == target</code>. <br>
	 * Note that this function does not check for the special cases
	 * <code>n == 0, &plusmn;1, target (mod m)</code>, or <code>target == 1</code> and so it will still
	 * take <code>O(end - begin)</code> steps even though the answer can be trivially determined in
	 * <code>O(1)</code> steps. Therefore, for the best performance, it is recommended to check those
	 * cases before calling this function. The reason why it does not check for the special cases, is
	 * that this function is specified as protected and is only called by other public functions which
	 * do handle those special cases themselves (in their own unique ways) and so checking for the
	 * special cases here, would only serve to decrease the overall runtime. <br>
	 * Precondition: <code>m > 2</code> <br>
	 * Precondition: <code>(1 < n) && (n < m - 1)</code> <br>
	 * Precondition: <code>(0 <= target) && (target <= m - 1)</code> <br>
	 * Precondition: <code>begin < end</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
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
	 * @param n_to_begin
	 *            <code>n<sup>begin</sup> (mod m)</code>
	 * 
	 * @return <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> if such an
	 *         <code>x</code> exists and <code>null</code> otherwise.
	 */
	protected static Long discreteLogLinearSearchFixedInput(long n, long target, long m, long begin, long end,
			long n_to_begin) {
		// Fix n to be in [-m / 2, m / 2] \cap \doubleZ.
		n = MathUtil.modMinFixedInput(n, m);
		// Fix target to be in [-m / 2, m / 2] \cap \doubleZ.
		target = MathUtil.modMinFixedInput(target, m);
		// Fix n_to_begin to be in [-m / 2, m / 2] \cap \doubleZ.
		n_to_begin = MathUtil.modMinFixedInput(n_to_begin, m);

		// Iteratively compute n to the power of (i + begin) in mod m and compare the result to target.
		if (n_to_begin == target) {
			return begin;
		}
		for (long i = begin + 1L, n_to_i = n_to_begin; i != end; ++i) {
			// Update n_to_i.
			if ((n_to_i = MathUtil.modMultFixedInput(n_to_i, n, m)) == n_to_begin) {
				/**
				 * This will only happen when <code>n</code>'s multiplicative order has been reached and
				 * <code>n_to_i</code> has wrapped back to <code>n_to_begin</code>. Note that for some choices of
				 * <code>n</code> and <code>m</code>, <code>n_to_i</code> will never wrap back to <code>1</code> but
				 * it may wrap back to <code>n_to_begin</code>. <code>n</code>'s multiplicative order is
				 * <code>i - begin</code>.
				 */
				break;
			}

			// Check for a match.
			if (n_to_i == target) {
				return i;
			}
		}
		// No power of n in [begin, end) \cap \doubleZ resulted in target.
		return null;
	}

	/**
	 * Perform a linear search for <code>x</code> such that
	 * <code>n<sup>x</sup> (mod m) == target</code>. Note that even if the result is not
	 * <code>null</code>, it is still not guaranteed to be in <code>[begin, end)</code>. The purpose of
	 * <code>begin</code> and <code>end</code> is to bound the linear search for <code>x</code> in the
	 * general case.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
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
	 * @return <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> if such an
	 *         <code>x</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>end < begin</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(begin < 0) && (gcd(n (mod m), m) != 1)</code>
	 */
	public static Long discreteLogLinearSearch(long n, long target, long m, long begin, long end)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		if (m < 1L) {
			throw new InvalidModulusException();
		} else if (end < begin) {
			throw new IllegalArgumentException();
		}
		// (m >= 1) && (begin <= end)
		// i.e., (m > 0) && (begin <= end)

		// Fix n to be in [0, m - 1] \cap \doubleZ.
		if ((n %= m) < 0L) {
			n += m;
		}
		// Fix target to be in [0, m - 1] \cap \doubleZ.
		if ((target %= m) < 0L) {
			target += m;
		}

		// Handle the simple special cases.
		final Long result = MathUtil.discreteLogTrivialFixedInput(n, target, m);
		if (result == null) {
			return null;
		} else if (result != -1L) { // i.e., result is trivial.
			return result;
		}
		// (m > 3) && (1 < n) && (n < m - 1) && (target != 1) && (n != target)
		if (begin == end) {
			return null;
		}
		// begin != end
		// i.e., begin < end
		return MathUtil.discreteLogLinearSearchFixedInput(n, target, m, begin, end,
				(begin == 1L) ? n : MathUtil.modPow(n, begin, m));
	}

	/**
	 * Perform a linear search for <code>x</code> such that
	 * <code>n<sup>x</sup> (mod m) == target</code>. Note that even if the result is not
	 * <code>null</code>, it is still not guaranteed to be in <code>[1, end)</code>. The purpose of
	 * <code>end</code> is to bound the linear search for <code>x</code> in the general case.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param end
	 *            the given end power
	 * 
	 * @return <code>MathUtil.discreteLogLinearSearch(n, target, m, 1L, end)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>end < 1</code>
	 */
	public static Long discreteLogLinearSearch(long n, long target, long m, long end)
			throws InvalidModulusException, IllegalArgumentException {
		return MathUtil.discreteLogLinearSearch(n, target, m, 1L, end);
	}

	/**
	 * Perform a linear search for <code>x</code> such that
	 * <code>n<sup>x</sup> (mod m) == target</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>MathUtil.discreteLogLinearSearch(n, target, m, m - 1L)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static Long discreteLogLinearSearch(long n, long target, long m) throws InvalidModulusException {
		return MathUtil.discreteLogLinearSearch(n, target, m, m - 1L);
	}

	/**
	 * Perform a linear search for <code>x</code> such that
	 * <code>n<sup>x</sup> (mod m) == target</code>. Note that even if the result is not
	 * <code>null</code>, it is still not guaranteed to be in <code>[begin, end)</code>. The purpose of
	 * <code>begin</code> and <code>end</code> is to bound the linear search for <code>x</code> in the
	 * general case.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
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
	 * @return <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> if such an
	 *         <code>x</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>end < begin</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(begin < 0) && (gcd(n (mod m), m) != 1)</code>
	 */
	public static Integer discreteLogLinearSearch(int n, int target, int m, int begin, int end)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		final Long result = MathUtil.discreteLogLinearSearch((long) n, (long) target, (long) m, (long) begin,
				(long) end);
		return ((result == null) ? null : result.intValue());
	}

	/**
	 * Perform a linear search for <code>x</code> such that
	 * <code>n<sup>x</sup> (mod m) == target</code>. Note that even if the result is not
	 * <code>null</code>, it is still not guaranteed to be in <code>[1, end)</code>. The purpose of
	 * <code>end</code> is to bound the linear search for <code>x</code> in the general case.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param end
	 *            the given end power
	 * 
	 * @return <code>MathUtil.discreteLogLinearSearch(n, target, m, 1, end)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>end < 1</code>
	 */
	public static Integer discreteLogLinearSearch(int n, int target, int m, int end)
			throws InvalidModulusException, IllegalArgumentException {
		return MathUtil.discreteLogLinearSearch(n, target, m, 1, end);
	}

	/**
	 * Perform a linear search for <code>x</code> such that
	 * <code>n<sup>x</sup> (mod m) == target</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>MathUtil.discreteLogLinearSearch(n, target, m, m - 1)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static Integer discreteLogLinearSearch(int n, int target, int m) throws InvalidModulusException {
		return MathUtil.discreteLogLinearSearch(n, target, m, m - 1);
	}

	/**
	 * Perform a linear search for <code>x</code> such that
	 * <code>n<sup>x</sup> (mod m) == target</code>. Note that even if the result is not
	 * <code>null</code>, it is still not guaranteed to be in <code>[begin, end)</code>. The purpose of
	 * <code>begin</code> and <code>end</code> is to bound the linear search for <code>x</code> in the
	 * general case.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
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
	 * @return <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> if such an
	 *         <code>x</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>end < begin</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(begin < 0) && (gcd(n (mod m), m) != 1)</code>
	 */
	public static Short discreteLogLinearSearch(short n, short target, short m, short begin, short end)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		final Long result = MathUtil.discreteLogLinearSearch((long) n, (long) target, (long) m, (long) begin,
				(long) end);
		return ((result == null) ? null : result.shortValue());
	}

	/**
	 * Perform a linear search for <code>x</code> such that
	 * <code>n<sup>x</sup> (mod m) == target</code>. Note that even if the result is not
	 * <code>null</code>, it is still not guaranteed to be in <code>[1, end)</code>. The purpose of
	 * <code>end</code> is to bound the linear search for <code>x</code> in the general case.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param end
	 *            the given end power
	 * 
	 * @return <code>MathUtil.discreteLogLinearSearch(n, target, m, (short) 1, end)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>end < 1</code>
	 */
	public static Short discreteLogLinearSearch(short n, short target, short m, short end)
			throws InvalidModulusException, IllegalArgumentException {
		return MathUtil.discreteLogLinearSearch(n, target, m, (short) 1, end);
	}

	/**
	 * Perform a linear search for <code>x</code> such that
	 * <code>n<sup>x</sup> (mod m) == target</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>MathUtil.discreteLogLinearSearch(n, target, m, (short) (m - 1))</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static Short discreteLogLinearSearch(short n, short target, short m) throws InvalidModulusException {
		return MathUtil.discreteLogLinearSearch(n, target, m, (short) (m - 1));
	}

	/**
	 * Perform a linear search for <code>x</code> such that
	 * <code>n<sup>x</sup> (mod m) == target</code>. Note that even if the result is not
	 * <code>null</code>, it is still not guaranteed to be in <code>[begin, end)</code>. The purpose of
	 * <code>begin</code> and <code>end</code> is to bound the linear search for <code>x</code> in the
	 * general case.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
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
	 * @return <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> if such an
	 *         <code>x</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>end < begin</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(begin < 0) && (gcd(n (mod m), m) != 1)</code>
	 */
	public static Byte discreteLogLinearSearch(byte n, byte target, byte m, byte begin, byte end)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		final Long result = MathUtil.discreteLogLinearSearch((long) n, (long) target, (long) m, (long) begin,
				(long) end);
		return ((result == null) ? null : result.byteValue());
	}

	/**
	 * Perform a linear search for <code>x</code> such that
	 * <code>n<sup>x</sup> (mod m) == target</code>. Note that even if the result is not
	 * <code>null</code>, it is still not guaranteed to be in <code>[1, end)</code>. The purpose of
	 * <code>end</code> is to bound the linear search for <code>x</code> in the general case.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param end
	 *            the given end power
	 * 
	 * @return <code>MathUtil.discreteLogLinearSearch(n, target, m, (byte) 1, end)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>end < 1</code>
	 */
	public static Byte discreteLogLinearSearch(byte n, byte target, byte m, byte end)
			throws InvalidModulusException, IllegalArgumentException {
		return MathUtil.discreteLogLinearSearch(n, target, m, (byte) 1, end);
	}

	/**
	 * Perform a linear search for <code>x</code> such that
	 * <code>n<sup>x</sup> (mod m) == target</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>MathUtil.discreteLogLinearSearch(n, target, m, (byte) (m - 1))</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static Byte discreteLogLinearSearch(byte n, byte target, byte m) throws InvalidModulusException {
		return MathUtil.discreteLogLinearSearch(n, target, m, (byte) (m - 1));
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using Shanks'
	 * Babystep-Giantstep Algorithm. <br>
	 * Note that this function does not check for the special cases
	 * <code>n == 0, &plusmn;1, target (mod m)</code>, or <code>target == 1</code> and so it will still
	 * take <code>O(bound)</code> steps even though the answer can be trivially determined in
	 * <code>O(1)</code> steps. Therefore, for the best performance, it is recommended to check those
	 * cases before calling this function. The reason why it does not check for the special cases, is
	 * that this function is specified as protected and is only called by other public functions which
	 * do handle those special cases themselves (in their own unique ways) and so checking for the
	 * special cases here, would only serve to decrease the overall runtime. <br>
	 * Precondition: <code>m > 2</code> <br>
	 * Precondition: <code>(1 < n) && (n < m - 1)</code> <br>
	 * Precondition: <code>(0 <= target) && (target <= m - 1)</code> <br>
	 * Precondition: <code>(2 <= bound) && (bound <= Integer.MAX_VALUE)</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param bound
	 *            <code>((long) Math.sqrt(upperOrder)) + 1</code> where <code>upperOrder</code> is the
	 *            upperbound on the multiplicative order of the given number (i.e., the multiplicative
	 *            order of <code>n</code> in <code>mod m</code> is &le; <code>upperOrder</code>)
	 *            satisfying the following precondition:
	 *            <code>(2 <= upperOrder) && (upperOrder <= m - 1)</code>
	 * 
	 * @param generateBoth
	 *            specifies whether both the babylist and the giantlist should be generated and stored
	 *            simultaneously instead of fully generating the babylist first and then generating the
	 *            giantlist in-place
	 * 
	 * @param hash
	 *            specifies whether the data structure used to store the lists, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code>
	 * 
	 * @param n_inverse
	 *            <code>n<sup>-1</sup> (mod m)</code>
	 * 
	 * @return <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> if such an
	 *         <code>x</code> exists and <code>null</code> otherwise.
	 */
	protected static Long discreteLogBabyGiantFixedInput(long n, long target, long m, long bound, boolean generateBoth,
			boolean hash, long n_inverse) {
		final long giant_factor = MathUtil.modPowFixedInput(n_inverse, bound, m);

		// Fix n to be in [-m / 2, m / 2] \cap \doubleZ.
		n = MathUtil.modMinFixedInput(n, m);
		// Fix target to be in [-m / 2, m / 2] \cap \doubleZ.
		target = MathUtil.modMinFixedInput(target, m);

		// Shanks' Babystep-Giantstep Algorithm.
		final Map<Long, Long> babylist = hash ? new HashMap<Long, Long>((int) bound) : new TreeMap<Long, Long>();
		babylist.put(1L, 0L);
		if (generateBoth) {
			/**
			 * <code>generateBoth</code> so generate both the babylist and the giantlist simultaneously one
			 * element at a time.
			 */
			final Map<Long, Long> giantlist = hash ? new HashMap<Long, Long>((int) bound) : new TreeMap<Long, Long>();
			giantlist.put(target, 0L);
			Long baby_index = null, giant_index = null;
			Long order_n = null, order_giant_factor = null;
			for (long index = 1L, baby = 1L, giant = target; index != bound; ++index) {
				// Only update baby and babylist if n's multiplicative order hasn't been reached.
				if (order_n == null) {
					baby = MathUtil.modMultFixedInput(baby, n, m);
					if ((baby_index = babylist.put(baby, index)) != null) {
						/**
						 * This will only happen when <code>n</code>'s multiplicative order has been reached and
						 * <code>baby</code> has wrapped back to <code>n</code>. Note that for some choices of
						 * <code>n</code> and <code>m</code>, <code>baby</code> will never wrap back to <code>1</code>
						 * but it may wrap back to <code>n</code>.
						 */
						order_n = index - baby_index;
						if (order_giant_factor != null) {
							/**
							 * This will only happen when the multiplicative order of both <code>n</code> and
							 * <code>giant_factor</code> has been reached and both <code>baby</code> and
							 * <code>giant</code> have wrapped back to their original values at some iteration. Thus, we
							 * can conclude that an answer cannot be found. Note that it is enough to check if
							 * <code>giant_factor</code>'s multiplicative order has been reached when <code>n</code>'s
							 * multiplicative order has been reached for the very first time.
							 */
							break;
						}
					}
				}
				// Only update giant and giantlist if giant_factor's multiplicative order hasn't been reached.
				if (order_giant_factor == null) {
					giant = MathUtil.modMultFixedInput(giant, giant_factor, m);
					if ((giant_index = giantlist.put(giant, index)) != null) {
						/**
						 * This will only happen when <code>giant_factor</code>'s multiplicative order has been reached
						 * and <code>giant</code> has wrapped back to <code>target</code>.
						 */
						order_giant_factor = index - giant_index;
						if (order_n != null) {
							/**
							 * This will only happen when the multiplicative order of both <code>n</code> and
							 * <code>giant_factor</code> has been reached and both <code>baby</code> and
							 * <code>giant</code> have wrapped back to their original values at some iteration. Thus, we
							 * can conclude that an answer cannot be found. Note that it is enough to check if
							 * <code>n</code>'s multiplicative order has been reached when <code>giant_factor</code>'s
							 * multiplicative order has been reached for the very first time.
							 */
							break;
						}
					}
				}

				/**
				 * At this point, we know that <code>(order_n == null) || (order_giant_factor == null)</code> so at
				 * least one set of the variables has been updated (i.e., either baby and babylist or giant and
				 * giantlist or all of them have been updated) which means that we should search for a match.
				 */

				// Search for a match between babylist and giantlist.
				/**
				 * The following result expressions will never overflow since the maximum value is
				 * <code>(bound - 1) * bound + (bound - 1) == bound<sup>2</sup> - 1</code>. However, due to the
				 * precondition on <code>bound</code> (i.e.,
				 * <code>(2 <= bound) && (bound <= Integer.MAX_VALUE == 2<sup>31</sup> - 1)</code>), we can conclude
				 * that
				 * <code>bound<sup>2</sup> - 1 <= (2<sup>62</sup> - 2<sup>32</sup> + 1) - 1 == 2<sup>62</sup> - 2<sup>32</sup></code>
				 * which is much smaller than <code>2<sup>63</sup> - 1 == Long.MAX_VALUE</code>.
				 */
				if (baby == giant) {
					return (index *= (bound + 1L));
				} else if ((baby_index = babylist.get(giant)) != null) {
					return ((index *= bound) + baby_index);
				} else if ((giant_index = giantlist.get(baby)) != null) {
					return ((giant_index *= bound) + index);
				}
			}
			return null;
		}
		/**
		 * <code>!generateBoth</code> so fully generate the babylist and then generate the giantlist
		 * in-place.
		 */
		babylist.put(n, 1L);
		for (long baby_index = 2L, baby = n; baby_index != bound; ++baby_index) {
			// Update baby.
			if ((baby = MathUtil.modMultFixedInput(baby, n, m)) == n) {
				/**
				 * This will only happen when <code>n</code>'s multiplicative order has been reached and
				 * <code>baby</code> has wrapped back to <code>n</code>. Note that for some choices of
				 * <code>n</code> and <code>m</code>, <code>baby</code> will never wrap back to <code>1</code> but
				 * it may wrap back to <code>n</code>. <code>n</code>'s multiplicative order is
				 * <code>baby_index - 1</code>.
				 */
				break;
			}

			// Check for a match.
			if (baby == target) {
				return baby_index;
			}

			// Update babylist.
			babylist.put(baby, baby_index);
		}
		Long baby_index = null;
		for (long giant_index = 1L, giant = target; giant_index != bound; ++giant_index) {
			// Update giant.
			if ((giant = MathUtil.modMultFixedInput(giant, giant_factor, m)) == target) {
				/**
				 * This will only happen when <code>giant_factor</code>'s multiplicative order has been reached and
				 * <code>giant</code> has wrapped back to <code>target</code>. <code>giant_factor</code>'s
				 * multiplicative order is <code>giant_index</code>.
				 */
				break;
			}

			// Search for a match between babylist and giantlist.
			if ((baby_index = babylist.get(giant)) != null) {
				/**
				 * The following result expression will never overflow since the maximum value is
				 * <code>(bound - 1) * bound + (bound - 1) == bound<sup>2</sup> - 1</code>. However, due to the
				 * precondition on <code>bound</code> (i.e.,
				 * <code>(2 <= bound) && (bound <= Integer.MAX_VALUE == 2<sup>31</sup> - 1)</code>), we can conclude
				 * that
				 * <code>bound<sup>2</sup> - 1 <= (2<sup>62</sup> - 2<sup>32</sup> + 1) - 1 == 2<sup>62</sup> - 2<sup>32</sup></code>
				 * which is much smaller than <code>2<sup>63</sup> - 1 == Long.MAX_VALUE</code>.
				 */
				return ((giant_index *= bound) + baby_index);
			}
		}
		return null;
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using Shanks'
	 * Babystep-Giantstep Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param generateBoth
	 *            specifies whether both the babylist and the giantlist should be generated and stored
	 *            simultaneously instead of fully generating the babylist first and then generating the
	 *            giantlist in-place
	 * 
	 * @param hash
	 *            specifies whether the data structure used to store the lists, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code>
	 * 
	 * @return <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> if such an
	 *         <code>x</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>Integer.MAX_VALUE < (((long) Math.sqrt(upperOrder)) + 1)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n (mod m), m) != 1</code>
	 */
	public static Long discreteLogBabyGiant(long n, long target, long m, long upperOrder, boolean generateBoth,
			boolean hash)
			throws InvalidModulusException, IllegalArgumentException, ArithmeticException, UndefinedInverseException {
		if (m < 1L) {
			throw new InvalidModulusException();
		} else if ((upperOrder < 1L) || (m - 1L < upperOrder)) {
			throw new IllegalArgumentException();
		}
		// (m >= 1) && (1 <= upperOrder) && (upperOrder <= m - 1)
		// i.e., (m > 0) && (1 <= upperOrder) && (upperOrder <= m - 1)

		// Fix n to be in [0, m - 1] \cap \doubleZ.
		if ((n %= m) < 0L) {
			n += m;
		}
		// Fix target to be in [0, m - 1] \cap \doubleZ.
		if ((target %= m) < 0L) {
			target += m;
		}

		// Handle the simple special cases.
		final Long result = MathUtil.discreteLogTrivialFixedInput(n, target, m);
		if (result == null) {
			return null;
		} else if (result != -1L) { // i.e., result is trivial.
			return result;
		}
		// (m > 3) && (1 < n) && (n < m - 1) && (target != 1) && (n != target)
		if (upperOrder == 1L) {
			// upperOrder == 1 implies that n == 1 but we know that this isn't the case.
			return null;
		}
		// upperOrder != 1
		// i.e., (2 <= upperOrder) && (upperOrder <= m - 1)

		// Applying Math.floor before casting to long is unnecessary and it causes a large slow down.
		final long bound = ((long) Math.sqrt(upperOrder)) + 1L; // bound >= 2
		if (Integer.MAX_VALUE < bound) {
			throw new ArithmeticException();
		}
		// bound <= Integer.MAX_VALUE
		return MathUtil.discreteLogBabyGiantFixedInput(n, target, m, bound, generateBoth, hash,
				MathUtil.modInverseFixedInput(n, m));
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using Shanks'
	 * Babystep-Giantstep Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param generateBoth
	 *            specifies whether both the babylist and the giantlist should be generated and stored
	 *            simultaneously instead of fully generating the babylist first and then generating the
	 *            giantlist in-place
	 * 
	 * @return <code>MathUtil.discreteLogBabyGiant(n, target, m, upperOrder, generateBoth, true)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>Integer.MAX_VALUE < (((long) Math.sqrt(upperOrder)) + 1)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n (mod m), m) != 1</code>
	 */
	public static Long discreteLogBabyGiant(long n, long target, long m, long upperOrder, boolean generateBoth)
			throws InvalidModulusException, IllegalArgumentException, ArithmeticException, UndefinedInverseException {
		return MathUtil.discreteLogBabyGiant(n, target, m, upperOrder, generateBoth, true);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using Shanks'
	 * Babystep-Giantstep Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @return <code>MathUtil.discreteLogBabyGiant(n, target, m, upperOrder, true)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>Integer.MAX_VALUE < (((long) Math.sqrt(upperOrder)) + 1)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n (mod m), m) != 1</code>
	 */
	public static Long discreteLogBabyGiant(long n, long target, long m, long upperOrder)
			throws InvalidModulusException, IllegalArgumentException, ArithmeticException, UndefinedInverseException {
		return MathUtil.discreteLogBabyGiant(n, target, m, upperOrder, true);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using Shanks'
	 * Babystep-Giantstep Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>MathUtil.discreteLogBabyGiant(n, target, m, m - 1L)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>Integer.MAX_VALUE < (((long) Math.sqrt(m - 1)) + 1)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n (mod m), m) != 1</code>
	 */
	public static Long discreteLogBabyGiant(long n, long target, long m)
			throws InvalidModulusException, ArithmeticException, UndefinedInverseException {
		return MathUtil.discreteLogBabyGiant(n, target, m, m - 1L);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using Shanks'
	 * Babystep-Giantstep Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param generateBoth
	 *            specifies whether both the babylist and the giantlist should be generated and stored
	 *            simultaneously instead of fully generating the babylist first and then generating the
	 *            giantlist in-place
	 * 
	 * @param hash
	 *            specifies whether the data structure used to store the lists, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code>
	 * 
	 * @return <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> if such an
	 *         <code>x</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n (mod m), m) != 1</code>
	 */
	public static Integer discreteLogBabyGiant(int n, int target, int m, int upperOrder, boolean generateBoth,
			boolean hash) throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		final Long result = MathUtil.discreteLogBabyGiant((long) n, (long) target, (long) m, (long) upperOrder,
				generateBoth, hash);
		return ((result == null) ? null : result.intValue());
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using Shanks'
	 * Babystep-Giantstep Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param generateBoth
	 *            specifies whether both the babylist and the giantlist should be generated and stored
	 *            simultaneously instead of fully generating the babylist first and then generating the
	 *            giantlist in-place
	 * 
	 * @return <code>MathUtil.discreteLogBabyGiant(n, target, m, upperOrder, generateBoth, true)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n (mod m), m) != 1</code>
	 */
	public static Integer discreteLogBabyGiant(int n, int target, int m, int upperOrder, boolean generateBoth)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		return MathUtil.discreteLogBabyGiant(n, target, m, upperOrder, generateBoth, true);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using Shanks'
	 * Babystep-Giantstep Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @return <code>MathUtil.discreteLogBabyGiant(n, target, m, upperOrder, true)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n (mod m), m) != 1</code>
	 */
	public static Integer discreteLogBabyGiant(int n, int target, int m, int upperOrder)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		return MathUtil.discreteLogBabyGiant(n, target, m, upperOrder, true);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using Shanks'
	 * Babystep-Giantstep Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>MathUtil.discreteLogBabyGiant(n, target, m, m - 1)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n (mod m), m) != 1</code>
	 */
	public static Integer discreteLogBabyGiant(int n, int target, int m)
			throws InvalidModulusException, UndefinedInverseException {
		return MathUtil.discreteLogBabyGiant(n, target, m, m - 1);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using Shanks'
	 * Babystep-Giantstep Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param generateBoth
	 *            specifies whether both the babylist and the giantlist should be generated and stored
	 *            simultaneously instead of fully generating the babylist first and then generating the
	 *            giantlist in-place
	 * 
	 * @param hash
	 *            specifies whether the data structure used to store the lists, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code>
	 * 
	 * @return <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> if such an
	 *         <code>x</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n (mod m), m) != 1</code>
	 */
	public static Short discreteLogBabyGiant(short n, short target, short m, short upperOrder, boolean generateBoth,
			boolean hash) throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		final Long result = MathUtil.discreteLogBabyGiant((long) n, (long) target, (long) m, (long) upperOrder,
				generateBoth, hash);
		return ((result == null) ? null : result.shortValue());
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using Shanks'
	 * Babystep-Giantstep Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param generateBoth
	 *            specifies whether both the babylist and the giantlist should be generated and stored
	 *            simultaneously instead of fully generating the babylist first and then generating the
	 *            giantlist in-place
	 * 
	 * @return <code>MathUtil.discreteLogBabyGiant(n, target, m, upperOrder, generateBoth, true)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n (mod m), m) != 1</code>
	 */
	public static Short discreteLogBabyGiant(short n, short target, short m, short upperOrder, boolean generateBoth)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		return MathUtil.discreteLogBabyGiant(n, target, m, upperOrder, generateBoth, true);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using Shanks'
	 * Babystep-Giantstep Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @return <code>MathUtil.discreteLogBabyGiant(n, target, m, upperOrder, true)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n (mod m), m) != 1</code>
	 */
	public static Short discreteLogBabyGiant(short n, short target, short m, short upperOrder)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		return MathUtil.discreteLogBabyGiant(n, target, m, upperOrder, true);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using Shanks'
	 * Babystep-Giantstep Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>MathUtil.discreteLogBabyGiant(n, target, m, (short) (m - 1))</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n (mod m), m) != 1</code>
	 */
	public static Short discreteLogBabyGiant(short n, short target, short m)
			throws InvalidModulusException, UndefinedInverseException {
		return MathUtil.discreteLogBabyGiant(n, target, m, (short) (m - 1));
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using Shanks'
	 * Babystep-Giantstep Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param generateBoth
	 *            specifies whether both the babylist and the giantlist should be generated and stored
	 *            simultaneously instead of fully generating the babylist first and then generating the
	 *            giantlist in-place
	 * 
	 * @param hash
	 *            specifies whether the data structure used to store the lists, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code>
	 * 
	 * @return <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> if such an
	 *         <code>x</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n (mod m), m) != 1</code>
	 */
	public static Byte discreteLogBabyGiant(byte n, byte target, byte m, byte upperOrder, boolean generateBoth,
			boolean hash) throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		final Long result = MathUtil.discreteLogBabyGiant((long) n, (long) target, (long) m, (long) upperOrder,
				generateBoth, hash);
		return ((result == null) ? null : result.byteValue());
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using Shanks'
	 * Babystep-Giantstep Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param generateBoth
	 *            specifies whether both the babylist and the giantlist should be generated and stored
	 *            simultaneously instead of fully generating the babylist first and then generating the
	 *            giantlist in-place
	 * 
	 * @return <code>MathUtil.discreteLogBabyGiant(n, target, m, upperOrder, generateBoth, true)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n (mod m), m) != 1</code>
	 */
	public static Byte discreteLogBabyGiant(byte n, byte target, byte m, byte upperOrder, boolean generateBoth)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		return MathUtil.discreteLogBabyGiant(n, target, m, upperOrder, generateBoth, true);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using Shanks'
	 * Babystep-Giantstep Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @return <code>MathUtil.discreteLogBabyGiant(n, target, m, upperOrder, true)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n (mod m), m) != 1</code>
	 */
	public static Byte discreteLogBabyGiant(byte n, byte target, byte m, byte upperOrder)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		return MathUtil.discreteLogBabyGiant(n, target, m, upperOrder, true);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using Shanks'
	 * Babystep-Giantstep Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>MathUtil.discreteLogBabyGiant(n, target, m, (byte) (m - 1))</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n (mod m), m) != 1</code>
	 */
	public static Byte discreteLogBabyGiant(byte n, byte target, byte m)
			throws InvalidModulusException, UndefinedInverseException {
		return MathUtil.discreteLogBabyGiant(n, target, m, (byte) (m - 1));
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm. <br>
	 * Precondition: <code>m > 2</code> <br>
	 * Precondition: <code>(0 <= n) && (n <= m - 1)</code> <br>
	 * Precondition: <code>(0 <= target) && (target <= m - 1)</code> <br>
	 * Precondition: <code>NumUtil.isPrimeSqrt(p)</code> <br>
	 * Precondition: <code>(0 < e) && (e < 63)</code> <br>
	 * Precondition: <code>p_to_e == MathUtil.powExact(p, e)</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param p
	 *            the given unique prime divisor of <code>p_to_e</code>
	 * 
	 * @param e
	 *            the given power of <code>p</code> in <code>p_to_e</code>
	 * 
	 * @param p_to_e
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>p_to_e</code>)
	 * 
	 * @param linearSearchIfNotBabyGiant
	 *            specifies whether a Linear-Search for <code>x</code> should be used when Shanks'
	 *            Babystep-Giantstep Algorithm cannot be used
	 * 
	 * @param simple
	 *            specifies whether the simple version of the algorithm should be used (i.e., specifies
	 *            whether the discrete log problem modulo <code>p<sup>e</sup></code> should be directly
	 *            solved by elementary methods without iteratively computing each <code>p</code>-adic
	 *            digit of the solution separately)
	 * 
	 * @param generateBothBabyGiant
	 *            specifies whether both the babylist and the giantlist should be generated and stored
	 *            simultaneously instead of fully generating the babylist first and then generating the
	 *            giantlist in-place when using Shanks' Babystep-Giantstep Algorithm
	 * 
	 * @param hashBabyGiant
	 *            specifies whether the data structure used to store the lists, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code> when using Shanks'
	 *            Babystep-Giantstep Algorithm
	 * 
	 * @return <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> if such an
	 *         <code>x</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws ArithmeticException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && ((simple && (Integer.MAX_VALUE < (((long) Math.sqrt(p_to_e)) + 1)))
	 *             	|| ((!simple) && (Integer.MAX_VALUE < (((long) Math.sqrt(p)) + 1))))</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && ((gcd(n, m) != 1) || (gcd(n<sup>p<sup>(e - 1)</sup></sup> (mod m), m) != 1))</code>
	 */
	protected static Long discreteLogPohligHellmanFixedInput(long n, long target, long m, long p, long e, long p_to_e,
			boolean linearSearchIfNotBabyGiant, boolean simple, boolean generateBothBabyGiant, boolean hashBabyGiant)
			throws ArithmeticException, UndefinedInverseException {
		// Adjust simple if needed.
		if (e == 1L) {
			simple = true;
		}

		// Handle the simple special cases.
		final Long result = MathUtil.discreteLogTrivialFixedInput(n, target, m);
		if (result == null) {
			return null;
		} else if (result != -1L) { // i.e., result is trivial.
			return result;
		}
		// (m > 3) && (1 < n) && (n < m - 1) && (target != 1) && (n != target)
		boolean linearSearch = false; // linear search flag

		if (simple) {
			/**
			 * <code>simple</code> so just solve the problem directly using Babystep-Giantstep or Linear-Search
			 * (i.e., elementary methods).
			 */
			long n_inverse = 0L;

			// Applying Math.floor before casting to long is unnecessary and it causes a large slow down.
			final long bound = ((long) Math.sqrt(p_to_e)) + 1L; // bound >= 2
			if (Integer.MAX_VALUE < bound) {
				if (linearSearchIfNotBabyGiant) {
					// Set the linear search flag denoting the fact that we have to use Linear-Search.
					linearSearch = true;
				} else { // i.e., !linearSearchIfNotBabyGiant
					/*
					 * If we cannot find x using Babystep-Giantstep and we are not permitted to use Linear-Search, then
					 * propagate the exception.
					 */
					throw new ArithmeticException();
				}
			} else { // i.e., bound <= Integer.MAX_VALUE
				try {
					n_inverse = MathUtil.modInverseFixedInput(n, m);
				} catch (UndefinedInverseException ex) {
					if (linearSearchIfNotBabyGiant) {
						// Set the linear search flag denoting the fact that we have to use Linear-Search.
						linearSearch = true;
					} else { // i.e., !linearSearchIfNotBabyGiant
						/*
						 * If we cannot find x using Babystep-Giantstep and we are not permitted to use Linear-Search,
						 * then propagate the exception.
						 */
						throw ex;
					}
				}
			}

			if (linearSearch) {
				// Runtime is in <code>O(p_to_e)</code>.
				return MathUtil.discreteLogLinearSearchFixedInput(n, target, m, 1L, p_to_e, n);
			}
			// Runtime is in <code>O(sqrt(p_to_e))</code>.
			return MathUtil.discreteLogBabyGiantFixedInput(n, target, m, bound, generateBothBabyGiant, hashBabyGiant,
					n_inverse);
		}
		/**
		 * <code>!simple</code> so iteratively compute the <code>p</code>-adic digits of the result
		 * separately by repeatedly shifting out all but one unknown digit in the exponent, and then compute
		 * that one unknown digit by elementary methods.
		 */
		final long n_inverse = MathUtil.modInverseFixedInput(n, m);
		final long p_to_e_minus_1 = p_to_e / p;
		long nu = MathUtil.modPowFixedInput(n, p_to_e_minus_1, m), nu_inverse = 0L; // order(nu) <= p
		// Fix nu to be in [0, m - 1] \cap \doubleZ.
		if (nu < 0L) {
			nu += m;
		}
		// (0 <= nu) && (nu <= m - 1)

		// Applying Math.floor before casting to long is unnecessary and it causes a large slow down.
		final long bound = ((long) Math.sqrt(p)) + 1L; // bound >= 2
		if (Integer.MAX_VALUE < bound) {
			if (linearSearchIfNotBabyGiant) {
				// Set the linear search flag denoting the fact that we have to use Linear-Search.
				linearSearch = true;
			} else { // i.e., !linearSearchIfNotBabyGiant
				/*
				 * If we cannot find x using Babystep-Giantstep and we are not permitted to use Linear-Search, then
				 * propagate the exception.
				 */
				throw new ArithmeticException();
			}
		} else { // i.e., bound <= Integer.MAX_VALUE
			try {
				nu_inverse = MathUtil.modInverseFixedInput(nu, m);
			} catch (UndefinedInverseException ex) {
				if (linearSearchIfNotBabyGiant) {
					// Set the linear search flag denoting the fact that we have to use Linear-Search.
					linearSearch = true;
				} else { // i.e., !linearSearchIfNotBabyGiant
					/*
					 * If we cannot find x using Babystep-Giantstep and we are not permitted to use Linear-Search, then
					 * propagate the exception.
					 */
					throw ex;
				}
			}
		}

		/*
		 * In the implementation, we have chosen to duplicate the entire loop instead of writing it once and
		 * having it evaluate the linearSearch boolean in every iteration for efficiency purposes.
		 */
		if (linearSearch) { // i.e., ((bound > Integer.MAX_VALUE) || (gcd(nu, m) != 1)) && linearSearchIfNotBabyGiant
			// The algorithm is from https://en.wikipedia.org/wiki/Pohlig%E2%80%93Hellman_algorithm.
			long x = 0L;
			Long d_k = null;
			for (long k = 0L, target_k = 0L, p_to_k = 1L; k != e; ++k, p_to_k *= p) {
				/**
				 * Compute
				 * <code>target_k == (n<sup>-x<sub>k</sub></sup> * target)<sup>p<sup>(e - 1 - k)</sup></sup> (mod m)</code>.
				 */
				target_k = MathUtil.modMultFixedInput(MathUtil.modPowFixedInput(n_inverse, x, m), target, m);
				if (target_k != 0L) {
					target_k = MathUtil.modPowFixedInput(target_k, p_to_e_minus_1 / p_to_k, m);
				}
				// Fix target_k to be in [0, m - 1] \cap \doubleZ.
				if (target_k < 0L) {
					target_k += m;
				}
				// (0 <= target_k) && (target_k <= m - 1)

				// Handle the simple special cases.
				d_k = MathUtil.discreteLogTrivialFixedInput(nu, target_k, m);
				if (d_k == null) {
					return null;
				} else if (d_k == -1L) { // i.e., d_k is non-trivial.
					// i.e., (m > 3) && (1 < nu) && (nu < m - 1) && (target_k != 1) && (nu != target_k)

					// Runtime is in <code>O(p)</code>.
					d_k = MathUtil.discreteLogLinearSearchFixedInput(nu, target_k, m, 1L, p, nu);
					if (d_k == null) {
						return null;
					}
				}
				// (0 <= d_k) && (d_k <= p - 1)

				// Update x.
				/**
				 * We know that <code>x</code> is in [0, p_to_e - 1] \cap \doubleZ at all times. We are just
				 * calculating each digit of it in base <code>p</code> (i.e., the <code>d_k</code>'s) and so none of
				 * the calculations will ever overflow. <br>
				 * <br>
				 * 
				 * It's fine to do <code>d_k *= p_to_k</code> instead of <code>d_k * p_to_k</code> since we don't
				 * need the value of <code>d_k</code> to remain unchanged at this point. Note that the difference is
				 * the <code>*=</code> instead of the <code>*</code> which will mutate <code>d_k</code>.
				 */
				x += (d_k *= p_to_k);
			}
			return x;
		}
		// i.e., (bound <= Integer.MAX_VALUE) && (gcd(nu, m) == 1)
		// The algorithm is from https://en.wikipedia.org/wiki/Pohlig%E2%80%93Hellman_algorithm.
		long x = 0L;
		Long d_k = null;
		for (long k = 0L, target_k = 0L, p_to_k = 1L; k != e; ++k, p_to_k *= p) {
			/**
			 * Compute
			 * <code>target_k == (n<sup>-x<sub>k</sub></sup> * target)<sup>p<sup>(e - 1 - k)</sup></sup> (mod m)</code>.
			 */
			target_k = MathUtil.modMultFixedInput(MathUtil.modPowFixedInput(n_inverse, x, m), target, m);
			if (target_k != 0L) {
				target_k = MathUtil.modPowFixedInput(target_k, p_to_e_minus_1 / p_to_k, m);
			}
			// Fix target_k to be in [0, m - 1] \cap \doubleZ.
			if (target_k < 0L) {
				target_k += m;
			}
			// (0 <= target_k) && (target_k <= m - 1)

			// Handle the simple special cases.
			d_k = MathUtil.discreteLogTrivialFixedInput(nu, target_k, m);
			if (d_k == null) {
				return null;
			} else if (d_k == -1L) { // i.e., d_k is non-trivial.
				// i.e., (m > 3) && (1 < nu) && (nu < m - 1) && (target_k != 1) && (nu != target_k)

				// Runtime is in <code>O(sqrt(p))</code>.
				d_k = MathUtil.discreteLogBabyGiantFixedInput(nu, target_k, m, bound, generateBothBabyGiant,
						hashBabyGiant, nu_inverse);
				if (d_k == null) {
					return null;
				}
			}
			// We know that (0 <= d_k) but we don't know if (d_k <= p - 1) or not.

			// Update x.
			/**
			 * We know that <code>x</code> is in [0, p_to_e - 1] \cap \doubleZ at all times. We are just
			 * calculating each digit of it in base <code>p</code> (i.e., the <code>d_k</code>'s) and so none of
			 * the calculations will ever overflow. <br>
			 * <br>
			 * 
			 * It's fine to do <code>d_k %= p</code> instead of <code>d_k % p</code> since we don't need the
			 * value of <code>d_k</code> to remain unchanged at this point. Note that the difference is the
			 * <code>%=</code> instead of the <code>%</code> which will mutate <code>d_k</code>.
			 */
			x += ((d_k %= p) * p_to_k);
		}
		return x;
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm. <br>
	 * Note that this function does not check for the special cases
	 * <code>n == 0, &plusmn;1, target (mod m)</code>, or <code>target == 1</code> and so it will still
	 * take <code>O(linearSearchIfNotBabyGiant ? upperOrder : sqrt(upperOrder))</code> steps even though
	 * the answer can be trivially determined in <code>O(1)</code> steps. Therefore, for the best
	 * performance, it is recommended to check those cases before calling this function. The reason why
	 * it does not check for the special cases, is that this function is specified as protected and is
	 * only called by other public functions which do handle those special cases themselves (in their
	 * own unique ways) and so checking for the special cases here, would only serve to decrease the
	 * overall runtime. <br>
	 * Precondition: <code>m > 2</code> <br>
	 * Precondition: <code>(1 < n) && (n < m - 1)</code> <br>
	 * Precondition: <code>(0 <= target) && (target <= m - 1)</code> <br>
	 * Precondition: <code>(2 <= upperOrder) && (upperOrder <= m - 1)</code> <br>
	 * Precondition: <code>(upperOrderFactors != null) && (!upperOrderFactors.isEmpty())</code> <br>
	 * Precondition:
	 * 
	 * <pre>
	 * <code>
	 * for (final Map.Entry&lt;Long, Byte&gt; entry : upperOrderFactors.entrySet()) {
	 * 	assert ((entry.getKey() != null) && NumUtil.isPrimeSqrt(entry.getKey()));
	 * 	assert ((entry.getValue() != null) && (0 < entry.getValue()) && (entry.getValue() < 63));
	 * }
	 * </code>
	 * </pre>
	 * 
	 * Precondition:
	 * 
	 * <pre>
	 * <code>
	 * long UpperOrder = 1L;
	 * for (final Map.Entry&lt;Long, Byte&gt; entry : upperOrderFactors.entrySet()) {
	 * 	UpperOrder = Math.multiplyExact(UpperOrder, MathUtil.powExact(entry.getKey(), entry.getValue()));
	 * }
	 * assert (upperOrder == UpperOrder);
	 * </code>
	 * </pre>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param linearSearchIfNotBabyGiant
	 *            specifies whether a Linear-Search for <code>x</code> should be used when Shanks'
	 *            Babystep-Giantstep Algorithm cannot be used
	 * 
	 * @param simple
	 *            specifies whether the simple version of the algorithm should be used (i.e., solving
	 *            the discrete log problem modulo <code>p<sub>i</sub><sup>e<sub>i</sub></sup></code>
	 *            directly by elementary methods where <code>p<sub>i</sub></code> is a prime factor of
	 *            <code>upperOrder</code> with power <code>e<sub>i</sub></code>)
	 * 
	 * @param upperOrderFactors
	 *            the given map containing the (distinct) prime divisors of <code>upperOrder</code> as
	 *            keys and their associated powers as values
	 * 
	 * @param generateBothBabyGiant
	 *            specifies whether both the babylist and the giantlist should be generated and stored
	 *            simultaneously instead of fully generating the babylist first and then generating the
	 *            giantlist in-place when using Shanks' Babystep-Giantstep Algorithm
	 * 
	 * @param hashBabyGiant
	 *            specifies whether the data structure used to store the lists, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code> when using Shanks'
	 *            Babystep-Giantstep Algorithm
	 * 
	 * @return <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> if such an
	 *         <code>x</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws ArithmeticException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && ((simple && (Integer.MAX_VALUE < (((long) Math.sqrt(max(p<sub>i</sub><sup>e<sub>i</sub></sup>))) + 1)))
	 *             	|| ((!simple) && (Integer.MAX_VALUE < (((long) Math.sqrt(max(p<sub>i</sub>))) + 1))))</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && ((gcd(n, m) != 1) || (gcd(n<sup>p<sub>i</sub><sup>(e<sub>i</sub> - 1)</sup></sup> (mod m), m) != 1))</code>
	 */
	protected static Long discreteLogPohligHellmanFixedInput(long n, long target, long m, long upperOrder,
			boolean linearSearchIfNotBabyGiant, boolean simple, Map<Long, Byte> upperOrderFactors,
			boolean generateBothBabyGiant, boolean hashBabyGiant)
			throws ArithmeticException, UndefinedInverseException {
		if ((upperOrderFactors.size() == 1) && upperOrderFactors.containsKey(upperOrder)) { // i.e., upperOrder is prime
			// Therefore, we have to use Babystep-Giantstep or Linear-Search.
			boolean linearSearch = false; // linear search flag
			long n_inverse = 0L;

			// Applying Math.floor before casting to long is unnecessary and it causes a large slow down.
			final long bound = ((long) Math.sqrt(upperOrder)) + 1L; // bound >= 2
			if (Integer.MAX_VALUE < bound) {
				if (linearSearchIfNotBabyGiant) {
					// Set the linear search flag denoting the fact that we have to use Linear-Search.
					linearSearch = true;
				} else { // i.e., !linearSearchIfNotBabyGiant
					/*
					 * If we cannot find x using Babystep-Giantstep and we are not permitted to use Linear-Search, then
					 * propagate the exception.
					 */
					throw new ArithmeticException();
				}
			} else { // i.e., bound <= Integer.MAX_VALUE
				try {
					n_inverse = MathUtil.modInverseFixedInput(n, m);
				} catch (UndefinedInverseException ex) {
					if (linearSearchIfNotBabyGiant) {
						// Set the linear search flag denoting the fact that we have to use Linear-Search.
						linearSearch = true;
					} else { // i.e., !linearSearchIfNotBabyGiant
						/*
						 * If we cannot find x using Babystep-Giantstep and we are not permitted to use Linear-Search,
						 * then propagate the exception.
						 */
						throw ex;
					}
				}
			}

			if (linearSearch) {
				// Runtime is in <code>O(upperOrder)</code>.
				return MathUtil.discreteLogLinearSearchFixedInput(n, target, m, 1L, upperOrder, n);
			}
			// Runtime is in <code>O(sqrt(upperOrder))</code>.
			return MathUtil.discreteLogBabyGiantFixedInput(n, target, m, bound, generateBothBabyGiant, hashBabyGiant,
					n_inverse);
		}
		// i.e., upperOrder is not prime.

		/*
		 * In the implementation, we have chosen to unravel the first iteration of the loop and duplicate it
		 * before the loop to optimize away one extra call to the crt method. If we had not duplicated the
		 * code, we could just initialize x to 0 and m_i to 1 and get rid of the duplicated code before the
		 * loop.
		 */
		final Iterator<Map.Entry<Long, Byte>> it = upperOrderFactors.entrySet().iterator();

		// Retrieve the first prime base and its power.
		Map.Entry<Long, Byte> entry = it.next();
		// Compute the congruence with solution <code>(mod p<sub>i</sub><sup>e<sub>i</sub></sup>)</code>.
		long p_i = entry.getKey(), e_i = entry.getValue(), p_i_to_e_i = MathUtil.pow(p_i, e_i),
				quotient = upperOrder / p_i_to_e_i;
		long n_i = MathUtil.modPowFixedInput(n, quotient, m), target_i = MathUtil.modPowFixedInput(target, quotient, m),
				m_i = p_i_to_e_i;
		if (n_i < 0L) {
			n_i += m;
		}
		// (0 <= n_i) && (n_i <= m - 1) && (order(n_i) <= p_i_to_e_i)
		if (target_i < 0L) {
			target_i += m;
		}
		// (0 <= target_i) && (target_i <= m - 1)

		// Solve the congruence with solution <code>(mod p<sub>i</sub><sup>e<sub>i</sub></sup>)</code>.
		Long x = MathUtil.discreteLogPohligHellmanFixedInput(n_i, target_i, m, p_i, e_i, p_i_to_e_i,
				linearSearchIfNotBabyGiant, simple, generateBothBabyGiant, hashBabyGiant);
		if (x == null) {
			return null;
		}

		// Process the remaining factors of upperOrder.
		long[] crt_result = null;
		for (Long x_i = null; it.hasNext(); /* Update inside. */) {
			// Retrieve the next prime base and its power.
			entry = it.next();
			p_i = entry.getKey();
			e_i = entry.getValue();
			// Compute the congruence with solution <code>(mod p<sub>i</sub><sup>e<sub>i</sub></sup>)</code>.
			quotient = upperOrder / (p_i_to_e_i = MathUtil.pow(p_i, e_i));
			if ((n_i = MathUtil.modPowFixedInput(n, quotient, m)) < 0L) {
				n_i += m;
			}
			// (0 <= n_i) && (n_i <= m - 1) && (order(n_i) <= p_i_to_e_i)
			if ((target_i = MathUtil.modPowFixedInput(target, quotient, m)) < 0L) {
				target_i += m;
			}
			// (0 <= target_i) && (target_i <= m - 1)

			// Solve the congruence with solution <code>(mod p<sub>i</sub><sup>e<sub>i</sub></sup>)</code>.
			x_i = MathUtil.discreteLogPohligHellmanFixedInput(n_i, target_i, m, p_i, e_i, p_i_to_e_i,
					linearSearchIfNotBabyGiant, simple, generateBothBabyGiant, hashBabyGiant);
			if (x_i == null) {
				return null;
			}

			// Use C.R.T. to combine the other solutions with the first.
			crt_result = MathUtil.crt(x, m_i, x_i, p_i_to_e_i, true);
			x = crt_result[0];
			m_i = crt_result[1];
		}
		// <code>(m_i == upperOrder) && (x != null) && (n<sup>x</sup> (mod m) == target)</code>
		return x;
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param linearSearchIfNotBabyGiant
	 *            specifies whether a Linear-Search for <code>x</code> should be used when Shanks'
	 *            Babystep-Giantstep Algorithm cannot be used
	 * 
	 * @param simple
	 *            specifies whether the simple version of the algorithm should be used (i.e., solving
	 *            the discrete log problem modulo <code>p<sub>i</sub><sup>e<sub>i</sub></sup></code>
	 *            directly by elementary methods where <code>p<sub>i</sub></code> is a prime factor of
	 *            <code>upperOrder</code> with power <code>e<sub>i</sub></code>)
	 * 
	 * @param hashFactor
	 *            specifies whether the data structure used to store the factors, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code> when factoring
	 *            <code>upperOrder</code>
	 * 
	 * @param generateBothBabyGiant
	 *            specifies whether both the babylist and the giantlist should be generated and stored
	 *            simultaneously instead of fully generating the babylist first and then generating the
	 *            giantlist in-place when using Shanks' Babystep-Giantstep Algorithm
	 * 
	 * @param hashBabyGiant
	 *            specifies whether the data structure used to store the lists, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code> when using Shanks'
	 *            Babystep-Giantstep Algorithm
	 * 
	 * @return <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> if such an
	 *         <code>x</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && ((simple && (Integer.MAX_VALUE < (((long) Math.sqrt(max(p<sub>i</sub><sup>e<sub>i</sub></sup>))) + 1)))
	 *             	|| ((!simple) && (Integer.MAX_VALUE < (((long) Math.sqrt(max(p<sub>i</sub>))) + 1))))</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && ((gcd(n (mod m), m) != 1) || (gcd(n<sup>p<sub>i</sub><sup>(e<sub>i</sub> - 1)</sup></sup> (mod m), m) != 1))</code>
	 */
	public static Long discreteLogPohligHellman(long n, long target, long m, long upperOrder,
			boolean linearSearchIfNotBabyGiant, boolean simple, boolean hashFactor, boolean generateBothBabyGiant,
			boolean hashBabyGiant)
			throws InvalidModulusException, IllegalArgumentException, ArithmeticException, UndefinedInverseException {
		if (m < 1L) {
			throw new InvalidModulusException();
		} else if ((upperOrder < 1L) || (m - 1L < upperOrder)) {
			throw new IllegalArgumentException();
		}
		// (m >= 1) && (1 <= upperOrder) && (upperOrder <= m - 1)
		// i.e., (m > 0) && (1 <= upperOrder) && (upperOrder <= m - 1)

		// Fix n to be in [0, m - 1] \cap \doubleZ.
		if ((n %= m) < 0L) {
			n += m;
		}
		// Fix target to be in [0, m - 1] \cap \doubleZ.
		if ((target %= m) < 0L) {
			target += m;
		}

		// Handle the simple special cases.
		final Long result = MathUtil.discreteLogTrivialFixedInput(n, target, m);
		if (result == null) {
			return null;
		} else if (result != -1L) { // i.e., result is trivial.
			return result;
		}
		// (m > 3) && (1 < n) && (n < m - 1) && (target != 1) && (n != target)
		if (upperOrder == 1L) {
			// upperOrder == 1 implies that n == 1 but we know that this isn't the case.
			return null;
		}
		// upperOrder != 1
		// i.e., (2 <= upperOrder) && (upperOrder <= m - 1)

		// Factor upperOrder and then perform the Pohlig-Hellman Algorithm.
		return MathUtil.discreteLogPohligHellmanFixedInput(n, target, m, upperOrder, linearSearchIfNotBabyGiant, simple,
				NumUtil.factorSqrt(upperOrder, hashFactor, false), generateBothBabyGiant, hashBabyGiant);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param linearSearchIfNotBabyGiant
	 *            specifies whether a Linear-Search for <code>x</code> should be used when Shanks'
	 *            Babystep-Giantstep Algorithm cannot be used
	 * 
	 * @param simple
	 *            specifies whether the simple version of the algorithm should be used (i.e., solving
	 *            the discrete log problem modulo <code>p<sub>i</sub><sup>e<sub>i</sub></sup></code>
	 *            directly by elementary methods where <code>p<sub>i</sub></code> is a prime factor of
	 *            <code>upperOrder</code> with power <code>e<sub>i</sub></code>)
	 * 
	 * @param hashFactor
	 *            specifies whether the data structure used to store the factors, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code> when factoring
	 *            <code>upperOrder</code>
	 * 
	 * @param generateBothBabyGiant
	 *            specifies whether both the babylist and the giantlist should be generated and stored
	 *            simultaneously instead of fully generating the babylist first and then generating the
	 *            giantlist in-place when using Shanks' Babystep-Giantstep Algorithm
	 * 
	 * @return <code>MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, simple, hashFactor, generateBothBabyGiant, true)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && ((simple && (Integer.MAX_VALUE < (((long) Math.sqrt(max(p<sub>i</sub><sup>e<sub>i</sub></sup>))) + 1)))
	 *             	|| ((!simple) && (Integer.MAX_VALUE < (((long) Math.sqrt(max(p<sub>i</sub>))) + 1))))</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && ((gcd(n (mod m), m) != 1) || (gcd(n<sup>p<sub>i</sub><sup>(e<sub>i</sub> - 1)</sup></sup> (mod m), m) != 1))</code>
	 */
	public static Long discreteLogPohligHellman(long n, long target, long m, long upperOrder,
			boolean linearSearchIfNotBabyGiant, boolean simple, boolean hashFactor, boolean generateBothBabyGiant)
			throws InvalidModulusException, IllegalArgumentException, ArithmeticException, UndefinedInverseException {
		return MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, simple,
				hashFactor, generateBothBabyGiant, true);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param linearSearchIfNotBabyGiant
	 *            specifies whether a Linear-Search for <code>x</code> should be used when Shanks'
	 *            Babystep-Giantstep Algorithm cannot be used
	 * 
	 * @param simple
	 *            specifies whether the simple version of the algorithm should be used (i.e., solving
	 *            the discrete log problem modulo <code>p<sub>i</sub><sup>e<sub>i</sub></sup></code>
	 *            directly by elementary methods where <code>p<sub>i</sub></code> is a prime factor of
	 *            <code>upperOrder</code> with power <code>e<sub>i</sub></code>)
	 * 
	 * @param hashFactor
	 *            specifies whether the data structure used to store the factors, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code> when factoring
	 *            <code>upperOrder</code>
	 * 
	 * @return <code>MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, simple, hashFactor, true)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && ((simple && (Integer.MAX_VALUE < (((long) Math.sqrt(max(p<sub>i</sub><sup>e<sub>i</sub></sup>))) + 1)))
	 *             	|| ((!simple) && (Integer.MAX_VALUE < (((long) Math.sqrt(max(p<sub>i</sub>))) + 1))))</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && ((gcd(n (mod m), m) != 1) || (gcd(n<sup>p<sub>i</sub><sup>(e<sub>i</sub> - 1)</sup></sup> (mod m), m) != 1))</code>
	 */
	public static Long discreteLogPohligHellman(long n, long target, long m, long upperOrder,
			boolean linearSearchIfNotBabyGiant, boolean simple, boolean hashFactor)
			throws InvalidModulusException, IllegalArgumentException, ArithmeticException, UndefinedInverseException {
		return MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, simple,
				hashFactor, true);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param linearSearchIfNotBabyGiant
	 *            specifies whether a Linear-Search for <code>x</code> should be used when Shanks'
	 *            Babystep-Giantstep Algorithm cannot be used
	 * 
	 * @param simple
	 *            specifies whether the simple version of the algorithm should be used (i.e., solving
	 *            the discrete log problem modulo <code>p<sub>i</sub><sup>e<sub>i</sub></sup></code>
	 *            directly by elementary methods where <code>p<sub>i</sub></code> is a prime factor of
	 *            <code>upperOrder</code> with power <code>e<sub>i</sub></code>)
	 * 
	 * @return <code>MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, simple, false)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && ((simple && (Integer.MAX_VALUE < (((long) Math.sqrt(max(p<sub>i</sub><sup>e<sub>i</sub></sup>))) + 1)))
	 *             	|| ((!simple) && (Integer.MAX_VALUE < (((long) Math.sqrt(max(p<sub>i</sub>))) + 1))))</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && ((gcd(n (mod m), m) != 1) || (gcd(n<sup>p<sub>i</sub><sup>(e<sub>i</sub> - 1)</sup></sup> (mod m), m) != 1))</code>
	 */
	public static Long discreteLogPohligHellman(long n, long target, long m, long upperOrder,
			boolean linearSearchIfNotBabyGiant, boolean simple)
			throws InvalidModulusException, IllegalArgumentException, ArithmeticException, UndefinedInverseException {
		return MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, simple, false);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param linearSearchIfNotBabyGiant
	 *            specifies whether a Linear-Search for <code>x</code> should be used when Shanks'
	 *            Babystep-Giantstep Algorithm cannot be used
	 * 
	 * @return <code>MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, false)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && (Integer.MAX_VALUE < (((long) Math.sqrt(max(p<sub>i</sub>))) + 1))</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && ((gcd(n (mod m), m) != 1) || (gcd(n<sup>p<sub>i</sub><sup>(e<sub>i</sub> - 1)</sup></sup> (mod m), m) != 1))</code>
	 */
	public static Long discreteLogPohligHellman(long n, long target, long m, long upperOrder,
			boolean linearSearchIfNotBabyGiant)
			throws InvalidModulusException, IllegalArgumentException, ArithmeticException, UndefinedInverseException {
		return MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, false);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @return <code>MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, false)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>Integer.MAX_VALUE < (((long) Math.sqrt(max(p<sub>i</sub>))) + 1)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If
	 *             <code>(gcd(n (mod m), m) != 1) || (gcd(n<sup>p<sub>i</sub><sup>(e<sub>i</sub> - 1)</sup></sup> (mod m), m) != 1)</code>
	 */
	public static Long discreteLogPohligHellman(long n, long target, long m, long upperOrder)
			throws InvalidModulusException, IllegalArgumentException, ArithmeticException, UndefinedInverseException {
		return MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, false);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>MathUtil.discreteLogPohligHellman(n, target, m, m - 1L)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>Integer.MAX_VALUE < (((long) Math.sqrt(max(p<sub>i</sub>))) + 1)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n (mod m), m) != 1</code>
	 */
	public static Long discreteLogPohligHellman(long n, long target, long m)
			throws InvalidModulusException, ArithmeticException, UndefinedInverseException {
		return MathUtil.discreteLogPohligHellman(n, target, m, m - 1L);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param linearSearchIfNotBabyGiant
	 *            specifies whether a Linear-Search for <code>x</code> should be used when Shanks'
	 *            Babystep-Giantstep Algorithm cannot be used
	 * 
	 * @param simple
	 *            specifies whether the simple version of the algorithm should be used (i.e., solving
	 *            the discrete log problem modulo <code>p<sub>i</sub><sup>e<sub>i</sub></sup></code>
	 *            directly by elementary methods where <code>p<sub>i</sub></code> is a prime factor of
	 *            <code>upperOrder</code> with power <code>e<sub>i</sub></code>)
	 * 
	 * @param hashFactor
	 *            specifies whether the data structure used to store the factors, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code> when factoring
	 *            <code>upperOrder</code>
	 * 
	 * @param generateBothBabyGiant
	 *            specifies whether both the babylist and the giantlist should be generated and stored
	 *            simultaneously instead of fully generating the babylist first and then generating the
	 *            giantlist in-place when using Shanks' Babystep-Giantstep Algorithm
	 * 
	 * @param hashBabyGiant
	 *            specifies whether the data structure used to store the lists, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code> when using Shanks'
	 *            Babystep-Giantstep Algorithm
	 * 
	 * @return <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> if such an
	 *         <code>x</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && ((gcd(n (mod m), m) != 1) || (gcd(n<sup>p<sub>i</sub><sup>(e<sub>i</sub> - 1)</sup></sup> (mod m), m) != 1))</code>
	 */
	public static Integer discreteLogPohligHellman(int n, int target, int m, int upperOrder,
			boolean linearSearchIfNotBabyGiant, boolean simple, boolean hashFactor, boolean generateBothBabyGiant,
			boolean hashBabyGiant) throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		final Long result = MathUtil.discreteLogPohligHellman((long) n, (long) target, (long) m, (long) upperOrder,
				linearSearchIfNotBabyGiant, simple, hashFactor, generateBothBabyGiant, hashBabyGiant);
		return ((result == null) ? null : result.intValue());
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param linearSearchIfNotBabyGiant
	 *            specifies whether a Linear-Search for <code>x</code> should be used when Shanks'
	 *            Babystep-Giantstep Algorithm cannot be used
	 * 
	 * @param simple
	 *            specifies whether the simple version of the algorithm should be used (i.e., solving
	 *            the discrete log problem modulo <code>p<sub>i</sub><sup>e<sub>i</sub></sup></code>
	 *            directly by elementary methods where <code>p<sub>i</sub></code> is a prime factor of
	 *            <code>upperOrder</code> with power <code>e<sub>i</sub></code>)
	 * 
	 * @param hashFactor
	 *            specifies whether the data structure used to store the factors, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code> when factoring
	 *            <code>upperOrder</code>
	 * 
	 * @param generateBothBabyGiant
	 *            specifies whether both the babylist and the giantlist should be generated and stored
	 *            simultaneously instead of fully generating the babylist first and then generating the
	 *            giantlist in-place when using Shanks' Babystep-Giantstep Algorithm
	 * 
	 * @return <code>MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, simple, hashFactor, generateBothBabyGiant, true)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && ((gcd(n (mod m), m) != 1) || (gcd(n<sup>p<sub>i</sub><sup>(e<sub>i</sub> - 1)</sup></sup> (mod m), m) != 1))</code>
	 */
	public static Integer discreteLogPohligHellman(int n, int target, int m, int upperOrder,
			boolean linearSearchIfNotBabyGiant, boolean simple, boolean hashFactor, boolean generateBothBabyGiant)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		return MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, simple,
				hashFactor, generateBothBabyGiant, true);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param linearSearchIfNotBabyGiant
	 *            specifies whether a Linear-Search for <code>x</code> should be used when Shanks'
	 *            Babystep-Giantstep Algorithm cannot be used
	 * 
	 * @param simple
	 *            specifies whether the simple version of the algorithm should be used (i.e., solving
	 *            the discrete log problem modulo <code>p<sub>i</sub><sup>e<sub>i</sub></sup></code>
	 *            directly by elementary methods where <code>p<sub>i</sub></code> is a prime factor of
	 *            <code>upperOrder</code> with power <code>e<sub>i</sub></code>)
	 * 
	 * @param hashFactor
	 *            specifies whether the data structure used to store the factors, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code> when factoring
	 *            <code>upperOrder</code>
	 * 
	 * @return <code>MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, simple, hashFactor, true)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && ((gcd(n (mod m), m) != 1) || (gcd(n<sup>p<sub>i</sub><sup>(e<sub>i</sub> - 1)</sup></sup> (mod m), m) != 1))</code>
	 */
	public static Integer discreteLogPohligHellman(int n, int target, int m, int upperOrder,
			boolean linearSearchIfNotBabyGiant, boolean simple, boolean hashFactor)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		return MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, simple,
				hashFactor, true);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param linearSearchIfNotBabyGiant
	 *            specifies whether a Linear-Search for <code>x</code> should be used when Shanks'
	 *            Babystep-Giantstep Algorithm cannot be used
	 * 
	 * @param simple
	 *            specifies whether the simple version of the algorithm should be used (i.e., solving
	 *            the discrete log problem modulo <code>p<sub>i</sub><sup>e<sub>i</sub></sup></code>
	 *            directly by elementary methods where <code>p<sub>i</sub></code> is a prime factor of
	 *            <code>upperOrder</code> with power <code>e<sub>i</sub></code>)
	 * 
	 * @return <code>MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, simple, false)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && ((gcd(n (mod m), m) != 1) || (gcd(n<sup>p<sub>i</sub><sup>(e<sub>i</sub> - 1)</sup></sup> (mod m), m) != 1))</code>
	 */
	public static Integer discreteLogPohligHellman(int n, int target, int m, int upperOrder,
			boolean linearSearchIfNotBabyGiant, boolean simple)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		return MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, simple, false);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param linearSearchIfNotBabyGiant
	 *            specifies whether a Linear-Search for <code>x</code> should be used when Shanks'
	 *            Babystep-Giantstep Algorithm cannot be used
	 * 
	 * @return <code>MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, false)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && ((gcd(n (mod m), m) != 1) || (gcd(n<sup>p<sub>i</sub><sup>(e<sub>i</sub> - 1)</sup></sup> (mod m), m) != 1))</code>
	 */
	public static Integer discreteLogPohligHellman(int n, int target, int m, int upperOrder,
			boolean linearSearchIfNotBabyGiant)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		return MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, false);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @return <code>MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, false)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If
	 *             <code>(gcd(n (mod m), m) != 1) || (gcd(n<sup>p<sub>i</sub><sup>(e<sub>i</sub> - 1)</sup></sup> (mod m), m) != 1)</code>
	 */
	public static Integer discreteLogPohligHellman(int n, int target, int m, int upperOrder)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		return MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, false);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>MathUtil.discreteLogPohligHellman(n, target, m, m - 1)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n (mod m), m) != 1</code>
	 */
	public static Integer discreteLogPohligHellman(int n, int target, int m)
			throws InvalidModulusException, UndefinedInverseException {
		return MathUtil.discreteLogPohligHellman(n, target, m, m - 1);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param linearSearchIfNotBabyGiant
	 *            specifies whether a Linear-Search for <code>x</code> should be used when Shanks'
	 *            Babystep-Giantstep Algorithm cannot be used
	 * 
	 * @param simple
	 *            specifies whether the simple version of the algorithm should be used (i.e., solving
	 *            the discrete log problem modulo <code>p<sub>i</sub><sup>e<sub>i</sub></sup></code>
	 *            directly by elementary methods where <code>p<sub>i</sub></code> is a prime factor of
	 *            <code>upperOrder</code> with power <code>e<sub>i</sub></code>)
	 * 
	 * @param hashFactor
	 *            specifies whether the data structure used to store the factors, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code> when factoring
	 *            <code>upperOrder</code>
	 * 
	 * @param generateBothBabyGiant
	 *            specifies whether both the babylist and the giantlist should be generated and stored
	 *            simultaneously instead of fully generating the babylist first and then generating the
	 *            giantlist in-place when using Shanks' Babystep-Giantstep Algorithm
	 * 
	 * @param hashBabyGiant
	 *            specifies whether the data structure used to store the lists, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code> when using Shanks'
	 *            Babystep-Giantstep Algorithm
	 * 
	 * @return <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> if such an
	 *         <code>x</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && ((gcd(n (mod m), m) != 1) || (gcd(n<sup>p<sub>i</sub><sup>(e<sub>i</sub> - 1)</sup></sup> (mod m), m) != 1))</code>
	 */
	public static Short discreteLogPohligHellman(short n, short target, short m, short upperOrder,
			boolean linearSearchIfNotBabyGiant, boolean simple, boolean hashFactor, boolean generateBothBabyGiant,
			boolean hashBabyGiant) throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		final Long result = MathUtil.discreteLogPohligHellman((long) n, (long) target, (long) m, (long) upperOrder,
				linearSearchIfNotBabyGiant, simple, hashFactor, generateBothBabyGiant, hashBabyGiant);
		return ((result == null) ? null : result.shortValue());
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param linearSearchIfNotBabyGiant
	 *            specifies whether a Linear-Search for <code>x</code> should be used when Shanks'
	 *            Babystep-Giantstep Algorithm cannot be used
	 * 
	 * @param simple
	 *            specifies whether the simple version of the algorithm should be used (i.e., solving
	 *            the discrete log problem modulo <code>p<sub>i</sub><sup>e<sub>i</sub></sup></code>
	 *            directly by elementary methods where <code>p<sub>i</sub></code> is a prime factor of
	 *            <code>upperOrder</code> with power <code>e<sub>i</sub></code>)
	 * 
	 * @param hashFactor
	 *            specifies whether the data structure used to store the factors, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code> when factoring
	 *            <code>upperOrder</code>
	 * 
	 * @param generateBothBabyGiant
	 *            specifies whether both the babylist and the giantlist should be generated and stored
	 *            simultaneously instead of fully generating the babylist first and then generating the
	 *            giantlist in-place when using Shanks' Babystep-Giantstep Algorithm
	 * 
	 * @return <code>MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, simple, hashFactor, generateBothBabyGiant, true)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && ((gcd(n (mod m), m) != 1) || (gcd(n<sup>p<sub>i</sub><sup>(e<sub>i</sub> - 1)</sup></sup> (mod m), m) != 1))</code>
	 */
	public static Short discreteLogPohligHellman(short n, short target, short m, short upperOrder,
			boolean linearSearchIfNotBabyGiant, boolean simple, boolean hashFactor, boolean generateBothBabyGiant)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		return MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, simple,
				hashFactor, generateBothBabyGiant, true);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param linearSearchIfNotBabyGiant
	 *            specifies whether a Linear-Search for <code>x</code> should be used when Shanks'
	 *            Babystep-Giantstep Algorithm cannot be used
	 * 
	 * @param simple
	 *            specifies whether the simple version of the algorithm should be used (i.e., solving
	 *            the discrete log problem modulo <code>p<sub>i</sub><sup>e<sub>i</sub></sup></code>
	 *            directly by elementary methods where <code>p<sub>i</sub></code> is a prime factor of
	 *            <code>upperOrder</code> with power <code>e<sub>i</sub></code>)
	 * 
	 * @param hashFactor
	 *            specifies whether the data structure used to store the factors, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code> when factoring
	 *            <code>upperOrder</code>
	 * 
	 * @return <code>MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, simple, hashFactor, true)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && ((gcd(n (mod m), m) != 1) || (gcd(n<sup>p<sub>i</sub><sup>(e<sub>i</sub> - 1)</sup></sup> (mod m), m) != 1))</code>
	 */
	public static Short discreteLogPohligHellman(short n, short target, short m, short upperOrder,
			boolean linearSearchIfNotBabyGiant, boolean simple, boolean hashFactor)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		return MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, simple,
				hashFactor, true);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param linearSearchIfNotBabyGiant
	 *            specifies whether a Linear-Search for <code>x</code> should be used when Shanks'
	 *            Babystep-Giantstep Algorithm cannot be used
	 * 
	 * @param simple
	 *            specifies whether the simple version of the algorithm should be used (i.e., solving
	 *            the discrete log problem modulo <code>p<sub>i</sub><sup>e<sub>i</sub></sup></code>
	 *            directly by elementary methods where <code>p<sub>i</sub></code> is a prime factor of
	 *            <code>upperOrder</code> with power <code>e<sub>i</sub></code>)
	 * 
	 * @return <code>MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, simple, false)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && ((gcd(n (mod m), m) != 1) || (gcd(n<sup>p<sub>i</sub><sup>(e<sub>i</sub> - 1)</sup></sup> (mod m), m) != 1))</code>
	 */
	public static Short discreteLogPohligHellman(short n, short target, short m, short upperOrder,
			boolean linearSearchIfNotBabyGiant, boolean simple)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		return MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, simple, false);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param linearSearchIfNotBabyGiant
	 *            specifies whether a Linear-Search for <code>x</code> should be used when Shanks'
	 *            Babystep-Giantstep Algorithm cannot be used
	 * 
	 * @return <code>MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, false)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && ((gcd(n (mod m), m) != 1) || (gcd(n<sup>p<sub>i</sub><sup>(e<sub>i</sub> - 1)</sup></sup> (mod m), m) != 1))</code>
	 */
	public static Short discreteLogPohligHellman(short n, short target, short m, short upperOrder,
			boolean linearSearchIfNotBabyGiant)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		return MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, false);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @return <code>MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, false)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If
	 *             <code>(gcd(n (mod m), m) != 1) || (gcd(n<sup>p<sub>i</sub><sup>(e<sub>i</sub> - 1)</sup></sup> (mod m), m) != 1)</code>
	 */
	public static Short discreteLogPohligHellman(short n, short target, short m, short upperOrder)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		return MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, false);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>MathUtil.discreteLogPohligHellman(n, target, m, (short) (m - 1))</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n (mod m), m) != 1</code>
	 */
	public static Short discreteLogPohligHellman(short n, short target, short m)
			throws InvalidModulusException, UndefinedInverseException {
		return MathUtil.discreteLogPohligHellman(n, target, m, (short) (m - 1));
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param linearSearchIfNotBabyGiant
	 *            specifies whether a Linear-Search for <code>x</code> should be used when Shanks'
	 *            Babystep-Giantstep Algorithm cannot be used
	 * 
	 * @param simple
	 *            specifies whether the simple version of the algorithm should be used (i.e., solving
	 *            the discrete log problem modulo <code>p<sub>i</sub><sup>e<sub>i</sub></sup></code>
	 *            directly by elementary methods where <code>p<sub>i</sub></code> is a prime factor of
	 *            <code>upperOrder</code> with power <code>e<sub>i</sub></code>)
	 * 
	 * @param hashFactor
	 *            specifies whether the data structure used to store the factors, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code> when factoring
	 *            <code>upperOrder</code>
	 * 
	 * @param generateBothBabyGiant
	 *            specifies whether both the babylist and the giantlist should be generated and stored
	 *            simultaneously instead of fully generating the babylist first and then generating the
	 *            giantlist in-place when using Shanks' Babystep-Giantstep Algorithm
	 * 
	 * @param hashBabyGiant
	 *            specifies whether the data structure used to store the lists, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code> when using Shanks'
	 *            Babystep-Giantstep Algorithm
	 * 
	 * @return <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> if such an
	 *         <code>x</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && ((gcd(n (mod m), m) != 1) || (gcd(n<sup>p<sub>i</sub><sup>(e<sub>i</sub> - 1)</sup></sup> (mod m), m) != 1))</code>
	 */
	public static Byte discreteLogPohligHellman(byte n, byte target, byte m, byte upperOrder,
			boolean linearSearchIfNotBabyGiant, boolean simple, boolean hashFactor, boolean generateBothBabyGiant,
			boolean hashBabyGiant) throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		final Long result = MathUtil.discreteLogPohligHellman((long) n, (long) target, (long) m, (long) upperOrder,
				linearSearchIfNotBabyGiant, simple, hashFactor, generateBothBabyGiant, hashBabyGiant);
		return ((result == null) ? null : result.byteValue());
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param linearSearchIfNotBabyGiant
	 *            specifies whether a Linear-Search for <code>x</code> should be used when Shanks'
	 *            Babystep-Giantstep Algorithm cannot be used
	 * 
	 * @param simple
	 *            specifies whether the simple version of the algorithm should be used (i.e., solving
	 *            the discrete log problem modulo <code>p<sub>i</sub><sup>e<sub>i</sub></sup></code>
	 *            directly by elementary methods where <code>p<sub>i</sub></code> is a prime factor of
	 *            <code>upperOrder</code> with power <code>e<sub>i</sub></code>)
	 * 
	 * @param hashFactor
	 *            specifies whether the data structure used to store the factors, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code> when factoring
	 *            <code>upperOrder</code>
	 * 
	 * @param generateBothBabyGiant
	 *            specifies whether both the babylist and the giantlist should be generated and stored
	 *            simultaneously instead of fully generating the babylist first and then generating the
	 *            giantlist in-place when using Shanks' Babystep-Giantstep Algorithm
	 * 
	 * @return <code>MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, simple, hashFactor, generateBothBabyGiant, true)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && ((gcd(n (mod m), m) != 1) || (gcd(n<sup>p<sub>i</sub><sup>(e<sub>i</sub> - 1)</sup></sup> (mod m), m) != 1))</code>
	 */
	public static Byte discreteLogPohligHellman(byte n, byte target, byte m, byte upperOrder,
			boolean linearSearchIfNotBabyGiant, boolean simple, boolean hashFactor, boolean generateBothBabyGiant)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		return MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, simple,
				hashFactor, generateBothBabyGiant, true);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param linearSearchIfNotBabyGiant
	 *            specifies whether a Linear-Search for <code>x</code> should be used when Shanks'
	 *            Babystep-Giantstep Algorithm cannot be used
	 * 
	 * @param simple
	 *            specifies whether the simple version of the algorithm should be used (i.e., solving
	 *            the discrete log problem modulo <code>p<sub>i</sub><sup>e<sub>i</sub></sup></code>
	 *            directly by elementary methods where <code>p<sub>i</sub></code> is a prime factor of
	 *            <code>upperOrder</code> with power <code>e<sub>i</sub></code>)
	 * 
	 * @param hashFactor
	 *            specifies whether the data structure used to store the factors, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code> when factoring
	 *            <code>upperOrder</code>
	 * 
	 * @return <code>MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, simple, hashFactor, true)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && ((gcd(n (mod m), m) != 1) || (gcd(n<sup>p<sub>i</sub><sup>(e<sub>i</sub> - 1)</sup></sup> (mod m), m) != 1))</code>
	 */
	public static Byte discreteLogPohligHellman(byte n, byte target, byte m, byte upperOrder,
			boolean linearSearchIfNotBabyGiant, boolean simple, boolean hashFactor)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		return MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, simple,
				hashFactor, true);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param linearSearchIfNotBabyGiant
	 *            specifies whether a Linear-Search for <code>x</code> should be used when Shanks'
	 *            Babystep-Giantstep Algorithm cannot be used
	 * 
	 * @param simple
	 *            specifies whether the simple version of the algorithm should be used (i.e., solving
	 *            the discrete log problem modulo <code>p<sub>i</sub><sup>e<sub>i</sub></sup></code>
	 *            directly by elementary methods where <code>p<sub>i</sub></code> is a prime factor of
	 *            <code>upperOrder</code> with power <code>e<sub>i</sub></code>)
	 * 
	 * @return <code>MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, simple, false)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && ((gcd(n (mod m), m) != 1) || (gcd(n<sup>p<sub>i</sub><sup>(e<sub>i</sub> - 1)</sup></sup> (mod m), m) != 1))</code>
	 */
	public static Byte discreteLogPohligHellman(byte n, byte target, byte m, byte upperOrder,
			boolean linearSearchIfNotBabyGiant, boolean simple)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		return MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, simple, false);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @param linearSearchIfNotBabyGiant
	 *            specifies whether a Linear-Search for <code>x</code> should be used when Shanks'
	 *            Babystep-Giantstep Algorithm cannot be used
	 * 
	 * @return <code>MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, false)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(!linearSearchIfNotBabyGiant)
	 *             && ((gcd(n (mod m), m) != 1) || (gcd(n<sup>p<sub>i</sub><sup>(e<sub>i</sub> - 1)</sup></sup> (mod m), m) != 1))</code>
	 */
	public static Byte discreteLogPohligHellman(byte n, byte target, byte m, byte upperOrder,
			boolean linearSearchIfNotBabyGiant)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		return MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, linearSearchIfNotBabyGiant, false);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param upperOrder
	 *            the given upperbound on the multiplicative order of the given number (i.e., the
	 *            multiplicative order of <code>n</code> in <code>mod m</code> is &le;
	 *            <code>upperOrder</code>)
	 * 
	 * @return <code>MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, false)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 1) || (m - 1 < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If
	 *             <code>(gcd(n (mod m), m) != 1) || (gcd(n<sup>p<sub>i</sub><sup>(e<sub>i</sub> - 1)</sup></sup> (mod m), m) != 1)</code>
	 */
	public static Byte discreteLogPohligHellman(byte n, byte target, byte m, byte upperOrder)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		return MathUtil.discreteLogPohligHellman(n, target, m, upperOrder, false);
	}

	/**
	 * Compute <code>x</code> such that <code>n<sup>x</sup> (mod m) == target</code> using the
	 * Pohlig-Hellman Algorithm.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>MathUtil.discreteLogPohligHellman(n, target, m, (byte) (m - 1))</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n (mod m), m) != 1</code>
	 */
	public static Byte discreteLogPohligHellman(byte n, byte target, byte m)
			throws InvalidModulusException, UndefinedInverseException {
		return MathUtil.discreteLogPohligHellman(n, target, m, (byte) (m - 1));
	}
}
