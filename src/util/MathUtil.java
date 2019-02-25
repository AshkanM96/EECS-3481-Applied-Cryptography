package util;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
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
	 * 		1. util.InvalidModulusException
	 * 		2. util.UndefinedInverseException
	 * </code>
	 */

	/**
	 * <code>ln(2)</code>.
	 */
	public static final double LOG_2 = Math.log(2);

	/**
	 * Largest prime less than or equal to Byte.MAX_VALUE.
	 */
	public static final byte LARGEST_PRIME_BYTE = 127;

	/**
	 * Smallest prime greater than Byte.MAX_VALUE.
	 */
	public static final short SMALLEST_PRIME_NOT_BYTE = 131;

	/**
	 * Largest prime less than or equal to Short.MAX_VALUE.
	 */
	public static final short LARGEST_PRIME_SHORT = 32749;

	/**
	 * Smallest prime greater than Short.MAX_VALUE.
	 */
	public static final int SMALLEST_PRIME_NOT_SHORT = 32771;

	/**
	 * Largest prime less than or equal to Integer.MAX_VALUE.
	 */
	public static final int LARGEST_PRIME_INT = 2147483647;

	/**
	 * Smallest prime greater than Integer.MAX_VALUE.
	 */
	public static final long SMALLEST_PRIME_NOT_INT = 2147483659L;

	/**
	 * Largest prime less than or equal to Long.MAX_VALUE.
	 */
	public static final long LARGEST_PRIME_LONG = 9223372036854775783L;

	/**
	 * Smallest prime greater than Long.MAX_VALUE.
	 */
	public static final BigInteger SMALLEST_PRIME_NOT_LONG = new BigInteger("9223372036854775837");

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
	 * @return <code>true</code> if and only if the given number is even.
	 */
	public static boolean isEven(byte n) {
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
	public static boolean isEven(long n) {
		// Odd numbers have their lowest bit set.
		// By using bitwise and, we can check this fact.
		return ((n &= 1L) == 0L);
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
	public static int signum(int n) {
		return ((n < 0) ? -1 : ((n == 0) ? 0 : 1));
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

			// Update max and min using: gcd(max, min) == gcd(min, remainder)
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

		// Algorithm is from Introduction to Mathematical Cryptography 2nd Edition Exercise 1.12.
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
		 * overflow a long.
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
	 *             If <code>lcm(a, b) > Long.MAX_VALUE</code>
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
	 *             || (lcm(a, b) > Long.MAX_VALUE)</code>
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
	 *             || (lcm(a, b) > Integer.MAX_VALUE)</code>
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
		if (result > Integer.MAX_VALUE) {
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
	 *             || (lcm(a, b) > Short.MAX_VALUE)</code>
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
		if (result > Short.MAX_VALUE) {
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
	 *             || (lcm(a, b) > Byte.MAX_VALUE)</code>
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
		if (result > Byte.MAX_VALUE) {
			throw new ArithmeticException();
		}
		return ((byte) result);
	}

	/**
	 * Compute <code>phi(n)</code> in <code>O(n * lg(n)) time</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return The value of Euler's totient function for the given number (i.e., <code>phi(n)</code>).
	 */
	public static long eulerTotientLinear(long n) {
		/**
		 * <code>phi(n) == 0</code> if <code>n < 1</code> (since no integers in range <code>[1, n]</code>)
		 * <br>
		 * <code>phi(n) == 1</code> if <code>n == 1</code> (since <code>gcd(1, 1) == 1</code>) <br>
		 * <code>phi(n) == n - 1</code> if <code>(n == 2) || (n == 3)</code> (since <code>n</code> is prime)
		 */
		if (n < 4L) {
			/**
			 * It's fine to do <code>n -= 1</code> instead of <code>n - 1</code> since we don't need the value
			 * of <code>n</code> to remain unchanged at that point. Note that the difference is the
			 * <code>-=</code> instead of the <code>-</code> which will mutate <code>n</code>.
			 */
			return ((n < 1L) ? 0L : ((n == 1L) ? 1L : (n -= 1L)));
		}
		// n >= 4

		/**
		 * <code>gcd(n, 1) == 1</code> and <code>gcd(n, n - 1) == 1</code> for all <code>n >= 1</code> so
		 * initialize result to <code>2</code> and check all of the integers in <code>[2, n - 2]</code> in
		 * the loop for coprimeness.
		 */
		final long maxI = n - 1L; // maxI >= 3
		/**
		 * Check <code>i == 2</code> separately since it can be done more efficiently than the general case.
		 * We know that <code>gcd(n, 2) == 1</code> if and only if <code>n</code> is odd. So if
		 * <code>n</code> is odd, initialize result to <code>3</code> and otherwise to <code>2</code> as
		 * before. Afterwards, proceed to check all of the integers in <code>[3, n - 2]</code>. Therefore,
		 * the runtime is in <code>O(sum(lg(i) from i = 3 to i = n - 2))</code> which can be upperbounded by
		 * <code>O(lg(n!))</code> which is in <code>O(n * lg(n))</code>. <br>
		 * <br>
		 * 
		 * Don't do <code>(n &= 1L) != 0L</code> since we need the value of <code>n</code> to remain
		 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
		 * will mutate <code>n</code>.
		 */
		long result = ((n & 1L) != 0L) ? 3L : 2L;
		for (long i = 3L; i != maxI; ++i) {
			if (MathUtil.gcdFixedInput(n, i) == 1L) {
				++result;
			}
		}
		return result;
	}

	/**
	 * Compute <code>phi(n)</code> in <code>O(n * lg(n)) time</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return The value of Euler's totient function for the given number (i.e., <code>phi(n)</code>).
	 */
	public static int eulerTotientLinear(int n) {
		return ((int) MathUtil.eulerTotientLinear((long) n));
	}

	/**
	 * Compute <code>phi(n)</code> in <code>O(n * lg(n)) time</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return The value of Euler's totient function for the given number (i.e., <code>phi(n)</code>).
	 */
	public static short eulerTotientLinear(short n) {
		return ((short) MathUtil.eulerTotientLinear((long) n));
	}

	/**
	 * Compute <code>phi(n)</code> in <code>O(n * lg(n)) time</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return The value of Euler's totient function for the given number (i.e., <code>phi(n)</code>).
	 */
	public static byte eulerTotientLinear(byte n) {
		return ((byte) MathUtil.eulerTotientLinear((long) n));
	}

	/**
	 * Compute <code>phi(n)</code> in <code>O(sqrt(n)) time</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return The value of Euler's totient function for the given number (i.e., <code>phi(n)</code>).
	 */
	public static long eulerTotientSqrt(long n) {
		/**
		 * <code>phi(n) == 0</code> if <code>n < 1</code> (since no integers in range <code>[1, n]</code>)
		 * <br>
		 * <code>phi(n) == 1</code> if <code>n == 1</code> (since <code>gcd(1, 1) == 1</code>) <br>
		 * <code>phi(n) == n - 1</code> if <code>(n == 2) || (n == 3)</code> (since <code>n</code> is prime)
		 */
		if (n < 4L) {
			/**
			 * It's fine to do <code>n -= 1</code> instead of <code>n - 1</code> since we don't need the value
			 * of <code>n</code> to remain unchanged at that point. Note that the difference is the
			 * <code>-=</code> instead of the <code>-</code> which will mutate <code>n</code>.
			 */
			return ((n < 1L) ? 0L : ((n == 1L) ? 1L : (n -= 1L)));
		}
		// n >= 4

		/**
		 * <pre>
		 * <code>
		 * Any integer i, is one of 0, 1, 2, 3, 4, or 5 (mod 6).
		 * However, if i is greater than 3, then it can only be prime if it is 1 or 5 (mod 6) since otherwise
		 * it would be divisible by 2 or 3 (or both in the case of 0 (mod 6)).
		 * </code>
		 * </pre>
		 * 
		 * Therefore, we only have to check numbers that are either <code>1 (mod 6)</code> or
		 * <code>-1 (mod 6)</code> (i.e., <code>5 (mod 6)</code>) if we check <code>i == 2</code> and
		 * <code>i == 3</code> separately. As a result, we will only check two-thirds of all odd values of
		 * <code>i</code> in the loop which are also only one-third of all values of <code>i</code>. Thus,
		 * by doing this, we can cut the total runtime by a third (i.e., runtime is in
		 * <code>O(sqrt(n) / 3)</code> instead of <code>O(sqrt(n))</code>).
		 */
		long result = n;
		/**
		 * Don't do <code>(n &= 1L) == 0L</code> since we need the value of <code>n</code> to remain
		 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
		 * will mutate <code>n</code>.
		 */
		// Check if 2 is a factor of n.
		if ((n & 1L) == 0L) { // i.e., n % 2 == 0
			// Update n and result.
			do {
				n /= 2L;
			} while ((n & 1L) == 0L); // i.e., n % 2 == 0
			result -= (result / 2L);

			// Check if n has no more factors.
			if (n == 1L) {
				return result;
			}
		}
		// Check if 3 is a factor of n.
		if (n % 3L == 0L) {
			// Update n and result.
			do {
				n /= 3L;
			} while (n % 3L == 0L);
			result -= (result / 3L);

			// Check if n has no more factors.
			if (n == 1L) {
				return result;
			}
		}
		/**
		 * We know that
		 * <code>phi(n) = product(phi(p<sub>i</sub><sup>e<sub>i</sub></sup>) from i = 1 to i = t)</code>
		 * where <code>p<sub>i</sub></code>'s are prime factors of <code>n</code> with natural powers
		 * <code>e<sub>i</sub></code> and natural number <code>t</code>. After a little bit of
		 * simplification, we can find that
		 * <code>phi(n) = n * product((1 - <sup>1</sup>&frasl;<sub>p<sub>i</sub></sub>) from i = 1 to i = t)</code>.
		 */
		final long bound = ((long) Math.sqrt(n)) + 1L; // bound >= 3
		final long maxI = bound + 1L; // maxI >= 4
		for (long i = 5L, i_plus_2 = 0L; i < maxI; i += 6L) {
			// Check if i (i.e., -1 (mod 6)) is a factor of n.
			if (n % i == 0L) {
				// Update n and result.
				do {
					n /= i;
				} while (n % i == 0L);
				result -= (result / i);

				// Check if n has no more factors.
				if (n == 1L) {
					return result;
				}
			}

			/**
			 * Don't do <code>i += 2</code> since we need the value of <code>i</code> to remain unchanged. Note
			 * that the difference is the <code>+=</code> instead of the <code>+</code> which will mutate
			 * <code>i</code>.
			 */
			i_plus_2 = i + 2L;
			// Check if i_plus_2 (i.e., 1 (mod 6)) is a factor of n.
			if (n % i_plus_2 == 0L) {
				// Update n and result.
				do {
					n /= i_plus_2;
				} while (n % i_plus_2 == 0L);
				result -= (result / i_plus_2);

				// Check if n has no more factors.
				if (n == 1L) {
					return result;
				}
			}
		}
		// For all n, n can have at most one factor greater than sqrt(n) so check if it has such a factor.
		return ((n != 1L) ? (result -= (result / n)) : result);
	}

	/**
	 * Compute <code>phi(n)</code> in <code>O(sqrt(n)) time</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return The value of Euler's totient function for the given number (i.e., <code>phi(n)</code>).
	 */
	public static int eulerTotientSqrt(int n) {
		return ((int) MathUtil.eulerTotientSqrt((long) n));
	}

	/**
	 * Compute <code>phi(n)</code> in <code>O(sqrt(n)) time</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return The value of Euler's totient function for the given number (i.e., <code>phi(n)</code>).
	 */
	public static short eulerTotientSqrt(short n) {
		return ((short) MathUtil.eulerTotientSqrt((long) n));
	}

	/**
	 * Compute <code>phi(n)</code> in <code>O(sqrt(n)) time</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return The value of Euler's totient function for the given number (i.e., <code>phi(n)</code>).
	 */
	public static byte eulerTotientSqrt(byte n) {
		return ((byte) MathUtil.eulerTotientSqrt((long) n));
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
	 *             If <code>gcd(n, m) != 1</code>
	 */
	public static long modInverse(long n, long m) throws InvalidModulusException, UndefinedInverseException {
		if (m < 2L) {
			throw new InvalidModulusException();
		}
		// m >= 2
		// i.e., m > 1

		// Fix n to be in [0, m - 1] \cap \doubleZ and handle the <code>n == 0</code> case.
		if ((n = MathUtil.modFixedInput(n, m)) == 0L) {
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
	 *             If <code>gcd(n, m) != 1</code>
	 */
	public static int modInverse(int n, int m) throws InvalidModulusException, UndefinedInverseException {
		if (m < 2) {
			throw new InvalidModulusException();
		}
		// m >= 2
		// i.e., m > 1

		// Fix n to be in [0, m - 1] \cap \doubleZ and handle the <code>n == 0</code> case.
		if ((n = (int) MathUtil.modFixedInput(n, m)) == 0) {
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
	 *             If <code>gcd(n, m) != 1</code>
	 */
	public static short modInverse(short n, short m) throws InvalidModulusException, UndefinedInverseException {
		if (m < 2) {
			throw new InvalidModulusException();
		}
		// m >= 2
		// i.e., m > 1

		// Fix n to be in [0, m - 1] \cap \doubleZ and handle the <code>n == 0</code> case.
		if ((n = (short) MathUtil.modFixedInput(n, m)) == 0) {
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
	 *             If <code>gcd(n, m) != 1</code>
	 */
	public static byte modInverse(byte n, byte m) throws InvalidModulusException, UndefinedInverseException {
		if (m < 2) {
			throw new InvalidModulusException();
		}
		// m >= 2
		// i.e., m > 1

		// Fix n to be in [0, m - 1] \cap \doubleZ and handle the <code>n == 0</code> case.
		if ((n = (byte) MathUtil.modFixedInput(n, m)) == 0) {
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
	 * <code>mod m</code>. <br>
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
		// min == Math.min(MathUtil.mod(a, m), MathUtil.mod(b, m))

		long result = 0L;
		for (; min != 0L; min /= 2L) {
			/**
			 * Don't do <code>(min &= 1L) != 0L</code> since we need the value of <code>min</code> to remain
			 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
			 * will mutate <code>min</code>.
			 */
			if ((min & 1L) != 0L) { // i.e., !MathUtil.isEven(min)
				result = MathUtil.modMinFixedInput((result += max) % m, m);
			}
			max = MathUtil.modMinFixedInput((max += max) % m, m); // Double max (mod m).
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
	 * Postcondition: <code>Result.length == 3</code> <br>
	 * Postcondition:
	 * <code>Result[0] == n1 * (<sup>m2</sup>&frasl;<sub>gcd(m1, m2)</sub>) * (m2<sup>-1</sup> (mod m1))
	 * + n2 * (<sup>m1</sup>&frasl;<sub>gcd(m1, m2)</sub>) * (m1<sup>-1</sup> (mod m2)) (mod lcm(m1, m2))</code>
	 * <br>
	 * Postcondition: <code>Result[1] == lcm(m1, m2)</code> <br>
	 * Postcondition: <code>Result[2] == gcd(m1, m2)</code>
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
	 *             If <code>lcm(m1, m2) > Long.MAX_VALUE</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n1 != n2 (mod gcd(m1, m2))</code>
	 */
	public static long[] crt(long n1, long m1, long n2, long m2)
			throws InvalidModulusException, ArithmeticException, IllegalArgumentException {
		if ((m1 < 1L) || (m2 < 1L)) {
			throw new InvalidModulusException();
		}
		// (m1 >= 1) && (m2 >= 1)
		// i.e., (m1 > 0) && (m2 > 0)

		// Find integers x and y such that x * m1 + y * m2 == gcd(m1, m2).
		final long[] result = MathUtil.gcdExtendedFixedInput(m1, m2);
		final long gcd = result[2];

		// Compute the new modulus which is the least common multiple of m1 and m2.
		final long og_m1 = m1, og_m2 = m2;
		final long m = Math.multiplyExact(m1 /= gcd, m2);

		// Handle the invalid case and update the coprime flag if needed.
		final boolean coprime = (gcd == 1L);
		if (!coprime) { // i.e., gcd != 1
			if (MathUtil.modFixedInput(n1, gcd) != MathUtil.modFixedInput(n2, gcd)) {
				throw new IllegalArgumentException();
			}
			m2 /= gcd;
		}

		// Fix all variables to be in [-m / 2, m / 2] \cap \doubleZ.
		final long m1_inverse = MathUtil.modMinFixedInput(result[0] %= og_m2, m),
				m2_inverse = MathUtil.modMinFixedInput(result[1] %= og_m1, m);
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
		return new long[] { lhs, m, gcd };
	}

	/**
	 * Chinese Remainder Theorem. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.length == 3</code> <br>
	 * Postcondition:
	 * <code>Result[0] == n1 * (<sup>m2</sup>&frasl;<sub>gcd(m1, m2)</sub>) * (m2<sup>-1</sup> (mod m1))
	 * + n2 * (<sup>m1</sup>&frasl;<sub>gcd(m1, m2)</sub>) * (m1<sup>-1</sup> (mod m2)) (mod lcm(m1, m2))</code>
	 * <br>
	 * Postcondition: <code>Result[1] == lcm(m1, m2)</code> <br>
	 * Postcondition: <code>Result[2] == gcd(m1, m2)</code>
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
	 *             If <code>lcm(m1, m2) > Integer.MAX_VALUE</code>
	 */
	public static int[] crt(int n1, int m1, int n2, int m2)
			throws InvalidModulusException, IllegalArgumentException, ArithmeticException {
		final long[] result = MathUtil.crt((long) n1, (long) m1, (long) n2, (long) m2);
		final long m = result[1]; // lcm(m1, m2)
		if (m > Integer.MAX_VALUE) {
			throw new ArithmeticException();
		}
		return new int[] { (int) result[0], (int) m, (int) result[2] };
	}

	/**
	 * Chinese Remainder Theorem. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.length == 3</code> <br>
	 * Postcondition:
	 * <code>Result[0] == n1 * (<sup>m2</sup>&frasl;<sub>gcd(m1, m2)</sub>) * (m2<sup>-1</sup> (mod m1))
	 * + n2 * (<sup>m1</sup>&frasl;<sub>gcd(m1, m2)</sub>) * (m1<sup>-1</sup> (mod m2)) (mod lcm(m1, m2))</code>
	 * <br>
	 * Postcondition: <code>Result[1] == lcm(m1, m2)</code> <br>
	 * Postcondition: <code>Result[2] == gcd(m1, m2)</code>
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
	 *             If <code>lcm(m1, m2) > Short.MAX_VALUE</code>
	 */
	public static short[] crt(short n1, short m1, short n2, short m2)
			throws InvalidModulusException, IllegalArgumentException, ArithmeticException {
		final long[] result = MathUtil.crt((long) n1, (long) m1, (long) n2, (long) m2);
		final long m = result[1]; // lcm(m1, m2)
		if (m > Short.MAX_VALUE) {
			throw new ArithmeticException();
		}
		return new short[] { (short) result[0], (short) m, (short) result[2] };
	}

	/**
	 * Chinese Remainder Theorem. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.length == 3</code> <br>
	 * Postcondition:
	 * <code>Result[0] == n1 * (<sup>m2</sup>&frasl;<sub>gcd(m1, m2)</sub>) * (m2<sup>-1</sup> (mod m1))
	 * + n2 * (<sup>m1</sup>&frasl;<sub>gcd(m1, m2)</sub>) * (m1<sup>-1</sup> (mod m2)) (mod lcm(m1, m2))</code>
	 * <br>
	 * Postcondition: <code>Result[1] == lcm(m1, m2)</code> <br>
	 * Postcondition: <code>Result[2] == gcd(m1, m2)</code>
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
	 *             If <code>lcm(m1, m2) > Byte.MAX_VALUE</code>
	 */
	public static byte[] crt(byte n1, byte m1, byte n2, byte m2)
			throws InvalidModulusException, IllegalArgumentException, ArithmeticException {
		final long[] result = MathUtil.crt((long) n1, (long) m1, (long) n2, (long) m2);
		final long m = result[1]; // lcm(m1, m2)
		if (m > Byte.MAX_VALUE) {
			throw new ArithmeticException();
		}
		return new byte[] { (byte) result[0], (byte) m, (byte) result[2] };
	}

	/**
	 * Compute <code>n<sup>p</sup> (mod m)</code> using the fast power (a.k.a., successive squaring)
	 * algorithm. <br>
	 * Note that this function does not check for the special cases <code>n == 1 (mod m)</code> or
	 * <code>n == -1 (mod m)</code> and so it will still take <code>O(lg(p))</code> steps even though
	 * the answer can be trivially determined in <code>O(1)</code> steps. Therefore, for the best
	 * performance, it is recommended to check those cases before calling this function. The reason why
	 * it does not check for the special cases, is that this function is specified as protected and is
	 * only called by other public functions which do handle those special cases themselves (in their
	 * own unique ways) and so checking for the special cases here, would only serve to slow down the
	 * overall runtime. <br>
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
		for (long n_to_2_to_i = MathUtil.modMinFixedInput(n, m); p != 0L; p /= 2L) {
			/**
			 * Don't do <code>(p &= 1L) != 0L</code> since we need the value of <code>p</code> to remain
			 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
			 * will mutate <code>p</code>.
			 */
			if ((p & 1L) != 0L) { // i.e., !MathUtil.isEven(p)
				result = MathUtil.modMultFixedInput(result, n_to_2_to_i, m);
			}
			n_to_2_to_i = MathUtil.modMultFixedInput(n_to_2_to_i, n_to_2_to_i, m); // Square n_to_2_to_i (mod m).
		}
		return result;
	}

	/**
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
	 *             If <code>(p < 0) && ((n (mod m) == 0) || (gcd(n, m) != 1))</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(p == 0) && (n (mod m) == 0)</code>
	 */
	public static long modPow(long n, long p, long m)
			throws InvalidModulusException, UndefinedInverseException, ArithmeticException {
		if (m < 2L) { // i.e., (m < 1) || (m == 1)
			if (m == 1L) {
				if (p < 0L) {
					throw new UndefinedInverseException();
				} else if (p == 0L) {
					throw new ArithmeticException();
				}
				return 0L;
			}
			// m != 1
			// i.e., m < 1
			throw new InvalidModulusException();
		}
		// m >= 2

		// Fix n to be in [0, m - 1] \cap \doubleZ and handle the simple special cases.
		n = MathUtil.modFixedInput(n, m);
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
			 * <code>-1 (mod m)</code> if <code>p</code> is odd
			 */
			/**
			 * It's fine to do <code>(p &= 1L) == 0L</code> instead of <code>(p & 1L) == 0L</code> since we
			 * don't need the value of <code>p</code> to remain unchanged. Note that the difference is the
			 * <code>&=</code> instead of the <code>&</code> which will mutate <code>p</code>.
			 */
			return (((p &= 1L) == 0L) ? 1L : n);
		}
		// n != m - 1
		// i.e., (1 < n) && (n < m - 1)

		/**
		 * <code>n<sup>p</sup> (mod m)</code> is: <br>
		 * <code>n<sup>|p|</sup> (mod m)</code> if <code>p > 0</code> <br>
		 * <code>1</code> if <code>p == 0</code> <br>
		 * <code>(n<sup>-1</sup> (mod m))<sup>|p|</sup> (mod m)</code> if <code>p < 0</code>
		 */
		if (p > 0L) {
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
	 *             If <code>(p < 0) && ((n (mod m) == 0) || (gcd(n, m) != 1))</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(p == 0) && (n (mod m) == 0)</code>
	 */
	public static int modPow(int n, int p, int m) throws ArithmeticException {
		return ((int) MathUtil.modPow((long) n, (long) p, (long) m));
	}

	/**
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
	 *             If <code>(p < 0) && ((n (mod m) == 0) || (gcd(n, m) != 1))</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(p == 0) && (n (mod m) == 0)</code>
	 */
	public static short modPow(short n, short p, short m) throws ArithmeticException {
		return ((short) MathUtil.modPow((long) n, (long) p, (long) m));
	}

	/**
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
	 *             If <code>(p < 0) && ((n (mod m) == 0) || (gcd(n, m) != 1))</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(p == 0) && (n (mod m) == 0)</code>
	 */
	public static byte modPow(byte n, byte p, byte m) throws ArithmeticException {
		return ((byte) MathUtil.modPow((long) n, (long) p, (long) m));
	}

	/**
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
	 *            the given upperbound on the multiplicative order of the given number
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
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 0) || (m < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n, m) != 1</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(((long) Math.sqrt(upperOrder)) + 1) > Integer.MAX_VALUE</code>
	 */
	public static Long discreteLogBabyGiant(long n, long target, long m, long upperOrder, boolean generateBoth,
			boolean hash)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException, ArithmeticException {
		if (m < 1L) {
			throw new InvalidModulusException();
		} else if ((upperOrder < 0L) || (m < upperOrder)) {
			throw new IllegalArgumentException();
		}
		// (m >= 1) && (0 <= upperOrder) && (upperOrder <= m)
		// i.e., (m > 0) && (0 <= upperOrder) && (upperOrder <= m)

		// Fix n to be in [0, m - 1] \cap \doubleZ.
		n = MathUtil.modFixedInput(n, m);
		// Fix target to be in [0, m - 1] \cap \doubleZ.
		target = MathUtil.modFixedInput(target, m);

		// Handle the simple special cases.
		if (n < 2L) { // i.e., (n == 0) || (n == 1)
			if (n == 0L) {
				// 0 to any non-zero power is 0 and 0 to the power of 0 is undefined.
				return ((target == 0L) ? 1L : null);
			}
			// n != 0
			// i.e., n == 1

			// 1 to any power is 1.
			return ((target == 1L) ? 0L : null);
		}
		// n >= 2
		// i.e., (1 < n) && (n <= m - 1) && (m > 2)
		if (target == 1L) {
			// n to the power of 0 is 1 except when n is 0 which we know isn't the case.
			return 0L;
		}
		// target != 1
		if (n == m - 1L) { // i.e., n == -1 (mod m)
			// -1 to any even power is 1 (but target != 1) and otherwise is -1.
			return ((target == n) ? 1L : null);
		}
		// n != m - 1
		// i.e., (1 < n) && (n < m - 1)
		if (upperOrder == 0L) {
			return null;
		}
		// upperOrder != 0
		// i.e., (1 <= upperOrder) && (upperOrder <= m)

		// Applying Math.floor before casting to long is unnecessary and it causes a large slow down.
		final long bound = ((long) Math.sqrt(upperOrder)) + 1L; // bound >= 2
		if (bound > Integer.MAX_VALUE) {
			throw new ArithmeticException();
		}
		// bound <= Integer.MAX_VALUE

		// Compute and save <code>n<sup>-1</sup> (mod m)</code> and <code>n<sup>-bound</sup> (mod m)</code>.
		final long n_inverse = MathUtil.modInverseFixedInput(n, m);
		final long giant_factor = MathUtil.modPowFixedInput(n_inverse, bound, m);

		// Fix n to be in [-m / 2, m / 2] \cap \doubleZ.
		n = MathUtil.modMinFixedInput(n, m);
		// Fix target to be in [-m / 2, m / 2] \cap \doubleZ.
		target = MathUtil.modMinFixedInput(target, m);

		// Shanks' Babystep Giantstep Algorithm.
		final Map<Long, Long> babylist = (hash ? new HashMap<Long, Long>((int) bound) : new TreeMap<Long, Long>());
		if (generateBoth) {
			final Map<Long, Long> giantlist = (hash ? new HashMap<Long, Long>((int) bound) : new TreeMap<Long, Long>());
			Long baby_index = null, giant_index = null;
			Long order_n = null, order_giant_factor = null;
			for (long index = 0L, baby = 1L, giant = target; index != bound; ++index) {
				// Update the two lists.
				babylist.putIfAbsent(baby, index);
				giantlist.putIfAbsent(giant, index);

				// Search for match between the two lists.
				/**
				 * The following expressions will never overflow since the maximum value is
				 * <code>(bound - 1) * bound + (bound - 1) == bound<sup>2</sup> - 1</code>. However, since we
				 * enforce <code>bound <= Integer.MAX_VALUE == 2<sup>31</sup> - 1</code> then we can conclude that
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

				// Update baby and giant.
				if (((baby = MathUtil.modMultFixedInput(baby, n, m)) == n) && (index != 0L)) {
					/**
					 * This will only happen when <code>n</code>'s multiplicative order has been reached and
					 * <code>baby</code> has wrapped back to <code>n</code>. Note that for some choices of
					 * <code>n</code> and <code>m</code>, <code>baby</code> will never wrap back to <code>1</code> but
					 * it may wrap back to <code>n</code>.
					 */
					if (order_n == null) {
						order_n = index;
					}
				}
				if (((giant = MathUtil.modMultFixedInput(giant, giant_factor, m)) == target) && (index != 0L)) {
					/**
					 * This will only happen when <code>giant_factor</code>'s multiplicative order has been reached and
					 * <code>giant</code> has wrapped back to <code>target</code>.
					 */
					if (order_giant_factor == null) {
						order_giant_factor = index + 1L;
					}
				}
				if ((order_n != null) && (order_giant_factor != null)) {
					/**
					 * This will only happen when the multiplicative order of both <code>n</code> and
					 * <code>giant_factor</code> has been reached and both <code>baby</code> and <code>giant</code> have
					 * wrapped back to their original values thus we can conclude that an answer cannot be found.
					 */
					break;
				}
			}
			return null;
		}
		// !generateBoth so fully generate the babylist and then generate the giantlist in-place.
		babylist.put(1L, 0L);
		for (long baby_index = 1L, baby = n; baby_index != bound; ++baby_index) {
			// Check for match.
			if (baby == target) {
				return baby_index;
			}

			// Update babylist and baby.
			babylist.put(baby, baby_index);
			if ((baby = MathUtil.modMultFixedInput(baby, n, m)) == n) {
				/**
				 * This will only happen when <code>n</code>'s multiplicative order has been reached and
				 * <code>baby</code> has wrapped back to <code>n</code>. Note that for some choices of
				 * <code>n</code> and <code>m</code>, <code>baby</code> will never wrap back to <code>1</code> but
				 * it may wrap back to <code>n</code>. <code>n</code>'s multiplicative order is
				 * <code>baby_index</code>.
				 */
				break;
			}
		}
		Long baby_index = null;
		for (long giant_index = 0L, giant = target; giant_index != bound; ++giant_index) {
			// Search for match between the two lists.
			if ((baby_index = babylist.get(giant)) != null) {
				/**
				 * The following expression will never overflow since its maximum value is
				 * <code>(bound - 1) * bound + (bound - 1) == bound<sup>2</sup> - 1</code>. However, since we
				 * enforce <code>bound <= Integer.MAX_VALUE == 2<sup>31</sup> - 1</code> then we can conclude that
				 * <code>bound<sup>2</sup> - 1 <= (2<sup>62</sup> - 2<sup>32</sup> + 1) - 1 == 2<sup>62</sup> - 2<sup>32</sup></code>
				 * which is much smaller than <code>2<sup>63</sup> - 1 == Long.MAX_VALUE</code>.
				 */
				return ((giant_index *= bound) + baby_index);
			}

			// Update giant.
			if ((giant = MathUtil.modMultFixedInput(giant, giant_factor, m)) == target) {
				/**
				 * This will only happen when <code>giant_factor</code>'s multiplicative order has been reached and
				 * <code>giant</code> has wrapped back to <code>target</code>. <code>giant_factor</code>'s
				 * multiplicative order is <code>giant_index</code>.
				 */
				break;
			}
		}
		return null;
	}

	/**
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
	 *            the given upperbound on the multiplicative order of the given number
	 * 
	 * @param generateBoth
	 *            specifies whether both the babylist and the giantlist should be generated and stored
	 *            simultaneously instead of fully generating the babylist first and then generating the
	 *            giantlist in-place
	 * 
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 0) || (m < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n, m) != 1</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(((long) Math.sqrt(upperOrder)) + 1) > Integer.MAX_VALUE</code>
	 */
	public static Long discreteLogBabyGiant(long n, long target, long m, long upperOrder, boolean generateBoth)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException, ArithmeticException {
		return MathUtil.discreteLogBabyGiant(n, target, m, upperOrder, generateBoth, true);
	}

	/**
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
	 *            the given upperbound on the multiplicative order of the given number
	 * 
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 0) || (m < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n, m) != 1</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(((long) Math.sqrt(upperOrder)) + 1) > Integer.MAX_VALUE</code>
	 */
	public static Long discreteLogBabyGiant(long n, long target, long m, long upperOrder)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException, ArithmeticException {
		return MathUtil.discreteLogBabyGiant(n, target, m, upperOrder, true);
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n, m) != 1</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(((long) Math.sqrt(m)) + 1) > Integer.MAX_VALUE</code>
	 */
	public static Long discreteLogBabyGiant(long n, long target, long m)
			throws InvalidModulusException, UndefinedInverseException, ArithmeticException {
		return MathUtil.discreteLogBabyGiant(n, target, m, m);
	}

	/**
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
	 *            the given upperbound on the multiplicative order of the given number
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
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 0) || (m < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n, m) != 1</code>
	 */
	public static Integer discreteLogBabyGiant(int n, int target, int m, int upperOrder, boolean generateBoth,
			boolean hash) throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		final Long result = MathUtil.discreteLogBabyGiant((long) n, (long) target, (long) m, (long) upperOrder,
				generateBoth, hash);
		return ((result == null) ? null : result.intValue());
	}

	/**
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
	 *            the given upperbound on the multiplicative order of the given number
	 * 
	 * @param generateBoth
	 *            specifies whether both the babylist and the giantlist should be generated and stored
	 *            simultaneously instead of fully generating the babylist first and then generating the
	 *            giantlist in-place
	 * 
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 0) || (m < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n, m) != 1</code>
	 */
	public static Integer discreteLogBabyGiant(int n, int target, int m, int upperOrder, boolean generateBoth)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		return MathUtil.discreteLogBabyGiant(n, target, m, upperOrder, generateBoth, true);
	}

	/**
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
	 *            the given upperbound on the multiplicative order of the given number
	 * 
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 0) || (m < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n, m) != 1</code>
	 */
	public static Integer discreteLogBabyGiant(int n, int target, int m, int upperOrder)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		return MathUtil.discreteLogBabyGiant(n, target, m, upperOrder, true);
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n, m) != 1</code>
	 */
	public static Integer discreteLogBabyGiant(int n, int target, int m)
			throws InvalidModulusException, UndefinedInverseException {
		return MathUtil.discreteLogBabyGiant(n, target, m, m);
	}

	/**
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
	 *            the given upperbound on the multiplicative order of the given number
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
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 0) || (m < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n, m) != 1</code>
	 */
	public static Short discreteLogBabyGiant(short n, short target, short m, short upperOrder, boolean generateBoth,
			boolean hash) throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		final Long result = MathUtil.discreteLogBabyGiant((long) n, (long) target, (long) m, (long) upperOrder,
				generateBoth, hash);
		return ((result == null) ? null : result.shortValue());
	}

	/**
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
	 *            the given upperbound on the multiplicative order of the given number
	 * 
	 * @param generateBoth
	 *            specifies whether both the babylist and the giantlist should be generated and stored
	 *            simultaneously instead of fully generating the babylist first and then generating the
	 *            giantlist in-place
	 * 
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 0) || (m < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n, m) != 1</code>
	 */
	public static Short discreteLogBabyGiant(short n, short target, short m, short upperOrder, boolean generateBoth)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		return MathUtil.discreteLogBabyGiant(n, target, m, upperOrder, generateBoth, true);
	}

	/**
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
	 *            the given upperbound on the multiplicative order of the given number
	 * 
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 0) || (m < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n, m) != 1</code>
	 */
	public static Short discreteLogBabyGiant(short n, short target, short m, short upperOrder)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		return MathUtil.discreteLogBabyGiant(n, target, m, upperOrder, true);
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n, m) != 1</code>
	 */
	public static Short discreteLogBabyGiant(short n, short target, short m)
			throws InvalidModulusException, UndefinedInverseException {
		return MathUtil.discreteLogBabyGiant(n, target, m, m);
	}

	/**
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
	 *            the given upperbound on the multiplicative order of the given number
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
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 0) || (m < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n, m) != 1</code>
	 */
	public static Byte discreteLogBabyGiant(byte n, byte target, byte m, byte upperOrder, boolean generateBoth,
			boolean hash) throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		final Long result = MathUtil.discreteLogBabyGiant((long) n, (long) target, (long) m, (long) upperOrder,
				generateBoth, hash);
		return ((result == null) ? null : result.byteValue());
	}

	/**
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
	 *            the given upperbound on the multiplicative order of the given number
	 * 
	 * @param generateBoth
	 *            specifies whether both the babylist and the giantlist should be generated and stored
	 *            simultaneously instead of fully generating the babylist first and then generating the
	 *            giantlist in-place
	 * 
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 0) || (m < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n, m) != 1</code>
	 */
	public static Byte discreteLogBabyGiant(byte n, byte target, byte m, byte upperOrder, boolean generateBoth)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		return MathUtil.discreteLogBabyGiant(n, target, m, upperOrder, generateBoth, true);
	}

	/**
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
	 *            the given upperbound on the multiplicative order of the given number
	 * 
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 0) || (m < upperOrder)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n, m) != 1</code>
	 */
	public static Byte discreteLogBabyGiant(byte n, byte target, byte m, byte upperOrder)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		return MathUtil.discreteLogBabyGiant(n, target, m, upperOrder, true);
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @param target
	 *            the given target
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n, m) != 1</code>
	 */
	public static Byte discreteLogBabyGiant(byte n, byte target, byte m)
			throws InvalidModulusException, UndefinedInverseException {
		return MathUtil.discreteLogBabyGiant(n, target, m, m);
	}

	/**
	 * Perform a linear search for <code>p</code> such that
	 * <code>n<sup>p</sup> (mod m) == target</code>. Note that even if the result is not
	 * <code>null</code>, it is still not guaranteed to be in <code>[begin, end)</code>. The purpose of
	 * <code>begin</code> and <code>end</code> is to bound the linear search for <code>p</code> in the
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
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>begin > end</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(begin < 0) && (gcd(n, m) != 1)</code>
	 */
	public static Long discreteLogLinearSearch(long n, long target, long m, long begin, long end)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		if (m < 1L) {
			throw new InvalidModulusException();
		} else if (begin > end) {
			throw new IllegalArgumentException();
		}
		// (m >= 1) && (begin <= end)
		// i.e., (m > 0) && (begin <= end)

		// Fix n to be in [0, m - 1] \cap \doubleZ.
		n = MathUtil.modFixedInput(n, m);
		// Fix target to be in [0, m - 1] \cap \doubleZ.
		target = MathUtil.modFixedInput(target, m);

		// Handle the simple special cases.
		if (n < 2L) { // i.e., (n == 0) || (n == 1)
			if (n == 0L) {
				// 0 to any non-zero power is 0 and 0 to the power of 0 is undefined.
				return ((target == 0L) ? 1L : null);
			}
			// n != 0
			// i.e., n == 1

			// 1 to any power is 1.
			return ((target == 1L) ? 0L : null);
		}
		// n >= 2
		// i.e., (1 < n) && (n <= m - 1) && (m > 2)
		if (target == 1L) {
			// n to the power of 0 is 1 except when n is 0 which we know isn't the case.
			return 0L;
		}
		// target != 1
		if (n == m - 1L) { // i.e., n == -1 (mod m)
			// -1 to any even power is 1 (but target != 1) and otherwise is -1.
			return ((target == n) ? 1L : null);
		}
		// n != m - 1
		// i.e., (1 < n) && (n < m - 1)
		if (begin == end) {
			return null;
		}
		// begin != end
		// i.e., begin < end

		// Fix n to be in [-m / 2, m / 2] \cap \doubleZ.
		n = MathUtil.modMinFixedInput(n, m);
		// Fix target to be in [-m / 2, m / 2] \cap \doubleZ.
		target = MathUtil.modMinFixedInput(target, m);

		// Iteratively compute n to the power of (i + begin) in mod m and compare the result to target.
		long n_to_i = MathUtil.modPow(n, begin, m);
		for (long i = begin; i != end; ++i) {
			// Check for match.
			if (n_to_i == target) {
				return i;
			}

			// Update n_to_i.
			if (((n_to_i = modMultFixedInput(n_to_i, n, m)) == n) && (i != begin)) {
				/**
				 * This will only happen when <code>n</code>'s multiplicative order has been reached and
				 * <code>n_to_i</code> has wrapped back to <code>n</code>. Note that for some choices of
				 * <code>n</code> and <code>m</code>, <code>n_to_i</code> will never wrap back to <code>1</code> but
				 * it may wrap back to <code>n</code>. <code>n</code>'s multiplicative order is
				 * <code>i - begin</code>.
				 */
				break;
			}
		}
		// No power of n resulted in target.
		return null;
	}

	/**
	 * <code>MathUtil.discreteLogLinearSearch(n, target, m, 0, m)</code>.
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
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static Long discreteLogLinearSearch(long n, long target, long m) throws InvalidModulusException {
		return MathUtil.discreteLogLinearSearch(n, target, m, 0L, m);
	}

	/**
	 * Perform a linear search for <code>p</code> such that
	 * <code>n<sup>p</sup> (mod m) == target</code>. Note that even if the result is not
	 * <code>null</code>, it is still not guaranteed to be in <code>[begin, end)</code>. The purpose of
	 * <code>begin</code> and <code>end</code> is to bound the linear search for <code>p</code> in the
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
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>begin > end</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(begin < 0) && (gcd(n, m) != 1)</code>
	 */
	public static Integer discreteLogLinearSearch(int n, int target, int m, int begin, int end)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		final Long result = MathUtil.discreteLogLinearSearch((long) n, (long) target, (long) m, (long) begin,
				(long) end);
		return ((result == null) ? null : result.intValue());
	}

	/**
	 * <code>MathUtil.discreteLogLinearSearch(n, target, m, 0, m)</code>.
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
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static Integer discreteLogLinearSearch(int n, int target, int m) throws InvalidModulusException {
		return MathUtil.discreteLogLinearSearch(n, target, m, 0, m);
	}

	/**
	 * Perform a linear search for <code>p</code> such that
	 * <code>n<sup>p</sup> (mod m) == target</code>. Note that even if the result is not
	 * <code>null</code>, it is still not guaranteed to be in <code>[begin, end)</code>. The purpose of
	 * <code>begin</code> and <code>end</code> is to bound the linear search for <code>p</code> in the
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
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>begin > end</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(begin < 0) && (gcd(n, m) != 1)</code>
	 */
	public static Short discreteLogLinearSearch(short n, short target, short m, short begin, short end)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		final Long result = MathUtil.discreteLogLinearSearch((long) n, (long) target, (long) m, (long) begin,
				(long) end);
		return ((result == null) ? null : result.shortValue());
	}

	/**
	 * <code>MathUtil.discreteLogLinearSearch(n, target, m, (short) 0, m)</code>.
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
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static Short discreteLogLinearSearch(short n, short target, short m) throws InvalidModulusException {
		return MathUtil.discreteLogLinearSearch(n, target, m, (short) 0, m);
	}

	/**
	 * Perform a linear search for <code>p</code> such that
	 * <code>n<sup>p</sup> (mod m) == target</code>. Note that even if the result is not
	 * <code>null</code>, it is still not guaranteed to be in <code>[begin, end)</code>. The purpose of
	 * <code>begin</code> and <code>end</code> is to bound the linear search for <code>p</code> in the
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
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>begin > end</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(begin < 0) && (gcd(n, m) != 1)</code>
	 */
	public static Byte discreteLogLinearSearch(byte n, byte target, byte m, byte begin, byte end)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException {
		final Long result = MathUtil.discreteLogLinearSearch((long) n, (long) target, (long) m, (long) begin,
				(long) end);
		return ((result == null) ? null : result.byteValue());
	}

	/**
	 * <code>MathUtil.discreteLogLinearSearch(n, target, m, (byte) 0, m)</code>.
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
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static Byte discreteLogLinearSearch(byte n, byte target, byte m) throws InvalidModulusException {
		return MathUtil.discreteLogLinearSearch(n, target, m, (byte) 0, m);
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
	 *             If <code>begin > end</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(end - begin) > Integer.MAX_VALUE</code>
	 */
	protected static int powersLength(long begin, long end) throws IllegalArgumentException, ArithmeticException {
		// Validate begin and end.
		if (begin > end) {
			throw new IllegalArgumentException();
		}
		// begin <= end

		if (begin == end) {
			return 0;
		}
		// begin < end

		if (begin >= 0L) {
			// 0 <= begin < end so end - begin will not overflow a long.
			end -= begin;
			if (end > Integer.MAX_VALUE) {
				throw new ArithmeticException();
			}
			return ((int) end);
		}
		// begin < 0

		// Therefore, Integer.MAX_VALUE + begin will not overflow a long.
		if (end > Integer.MAX_VALUE + begin) {
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
	 *             If <code>begin > end</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(end - begin) > Integer.MAX_VALUE</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(begin < 0) && (gcd(n, m) != 1)</code>
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

		// Fix n to be in [0, m - 1] \cap \doubleZ.
		n = MathUtil.modFixedInput(n, m);

		// Create the resulting long array and handle the simple special cases.
		final long[] result = new long[length];
		if (length == 0) {
			// Nothing to do here.
			return result;
		}
		// length != 0
		// i.e., length > 0
		if (n < 2L) { // i.e., (n == 0) || (n == 1)
			if (n == 0L) {
				/**
				 * This case is needed since 0 to any non-zero power is 0 and so any non-zero assignment of
				 * <code>result[i]</code> will be wrong in this case. Furthermore, note that we are defining
				 * <code>0<sup>0</sup> == 0</code> here even though it is undefined in math.
				 */
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
		// i.e., (1 < n) && (n < m - 1)

		// Fix n to be in [-m / 2, m / 2] \cap \doubleZ.
		n = MathUtil.modMinFixedInput(n, m);

		// Fill and return the resulting long array.
		long n_to_i = MathUtil.modPow(n, begin, m);
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
	 * Postcondition: <code>Result.length == m</code> <br>
	 * Postcondition: <code>(valid i) implies (Result[i] == n<sup>i</sup> (mod m))</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return The resulting long array.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>m > Integer.MAX_VALUE</code>
	 */
	public static long[] modPowers(long n, long m) throws InvalidModulusException, ArithmeticException {
		return MathUtil.modPowers(n, m, 0L, m);
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
	 *             If <code>begin > end</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(end - begin) > Integer.MAX_VALUE</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(begin < 0) && (gcd(n, m) != 1)</code>
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

		// Fix n to be in [0, m - 1] \cap \doubleZ.
		n = (int) MathUtil.modFixedInput(n, m);

		// Create the resulting integer array and handle the simple special cases.
		final int[] result = new int[length];
		if (length == 0) {
			// Nothing to do here.
			return result;
		}
		// length != 0
		// i.e., length > 0
		if (n < 2) { // i.e., (n == 0) || (n == 1)
			if (n == 0) {
				/**
				 * This case is needed since 0 to any non-zero power is 0 and so any non-zero assignment of
				 * <code>result[i]</code> will be wrong in this case. Furthermore, note that we are defining
				 * <code>0<sup>0</sup> == 0</code> here even though it is undefined in math.
				 */
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
		// i.e., (1 < n) && (n < m - 1)

		// Fix n to be in [-m / 2, m / 2] \cap \doubleZ.
		n = (int) MathUtil.modMinFixedInput(n, m);

		// Fill and return the resulting int array.
		int n_to_i = MathUtil.modPow(n, begin, m);
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
	 * Postcondition: <code>Result.length == m</code> <br>
	 * Postcondition: <code>(valid i) implies (Result[i] == n<sup>i</sup> (mod m))</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return The resulting integer array.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static int[] modPowers(int n, int m) throws InvalidModulusException {
		return MathUtil.modPowers(n, m, 0, m);
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
	 *             If <code>begin > end</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(begin < 0) && (gcd(n, m) != 1)</code>
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

		// Fix n to be in [0, m - 1] \cap \doubleZ.
		n = (short) MathUtil.modFixedInput(n, m);

		// Create the resulting short array and handle the simple special cases.
		final short[] result = new short[length];
		if (length == 0) {
			// Nothing to do here.
			return result;
		}
		// length != 0
		// i.e., length > 0
		if (n < 2) { // i.e., (n == 0) || (n == 1)
			if (n == 0) {
				/**
				 * This case is needed since 0 to any non-zero power is 0 and so any non-zero assignment of
				 * <code>result[i]</code> will be wrong in this case. Furthermore, note that we are defining
				 * <code>0<sup>0</sup> == 0</code> here even though it is undefined in math.
				 */
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
		// i.e., (1 < n) && (n < m - 1)

		// Fix n to be in [-m / 2, m / 2] \cap \doubleZ.
		n = (short) MathUtil.modMinFixedInput(n, m);

		// Fill and return the resulting short array.
		short n_to_i = MathUtil.modPow(n, begin, m);
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
	 * Postcondition: <code>Result.length == m</code> <br>
	 * Postcondition: <code>(valid i) implies (Result[i] == n<sup>i</sup> (mod m))</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return The resulting short array.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static short[] modPowers(short n, short m) throws InvalidModulusException {
		return MathUtil.modPowers(n, m, (short) 0, m);
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
	 *             If <code>begin > end</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(begin < 0) && (gcd(n, m) != 1)</code>
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

		// Fix n to be in [0, m - 1] \cap \doubleZ.
		n = (byte) MathUtil.modFixedInput(n, m);

		// Create the resulting byte array and handle the simple special cases.
		final byte[] result = new byte[length];
		if (length == 0) {
			// Nothing to do here.
			return result;
		}
		// length != 0
		// i.e., length > 0
		if (n < 2) { // i.e., (n == 0) || (n == 1)
			if (n == 0) {
				/**
				 * This case is needed since 0 to any non-zero power is 0 and so any non-zero assignment of
				 * <code>result[i]</code> will be wrong in this case. Furthermore, note that we are defining
				 * <code>0<sup>0</sup> == 0</code> here even though it is undefined in math.
				 */
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
		// i.e., (1 < n) && (n < m - 1)

		// Fix n to be in [-m / 2, m / 2] \cap \doubleZ.
		n = (byte) MathUtil.modMinFixedInput(n, m);

		// Fill and return the resulting byte array.
		byte n_to_i = MathUtil.modPow(n, begin, m);
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
	 * Postcondition: <code>Result.length == m</code> <br>
	 * Postcondition: <code>(valid i) implies (Result[i] == n<sup>i</sup> (mod m))</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return The resulting byte array.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static byte[] modPowers(byte n, byte m) throws InvalidModulusException {
		return MathUtil.modPowers(n, m, (byte) 0, m);
	}
}
