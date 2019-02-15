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
	 * Largest prime representable in a byte.
	 */
	public static final byte LARGEST_PRIME_BYTE = 127;

	/**
	 * Largest prime representable in a short.
	 */
	public static final short LARGEST_PRIME_SHORT = 32749;

	/**
	 * Largest prime representable in an int.
	 */
	public static final int LARGEST_PRIME_INT = 2147483647;

	/**
	 * Largest prime representable in a long.
	 */
	public static final long LARGEST_PRIME_LONG = 9223372036854775783L;

	/**
	 * Prevent instantiation.
	 */
	private MathUtil() {
		// Empty by design.
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
		a = Math.abs(a);
		b = Math.abs(b);

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
		return Math.abs(max);
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
		if (b < 0L) {
			abs_b *= -1;
		}

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
		return new long[] { x * sign_a, y, gcd };
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
		final long[] result = MathUtil.gcdExtendedFixedInput(a, b);
		return new byte[] { (byte) result[0], (byte) result[1], (byte) result[2] };
	}

	/**
	 * Note that the final result of this function may have overflowed and wrapped around which is why
	 * it returns the absolute value of the final result.
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
	 *             If <code>((a == Long.MIN_VALUE) && (b == Long.MIN_VALUE))
	 *             || (((a / MathUtil.gcd(a, b)) * b) == Long.MIN_VALUE)</code>
	 */
	public static long lcm(long a, long b) throws ArithmeticException {
		// lcm(0, b) == 0 == lcm(a, 0)
		if ((a == 0L) || (b == 0L)) {
			return 0L;
		}
		// (a != 0) && (b != 0)

		// lcm is non-negative so make a and b non-negative.
		a = Math.abs(a);
		b = Math.abs(b);

		// lcm(a, b) == (a * b) / gcd(a, b)
		final long result = Math.abs((a / MathUtil.gcd(a, b)) * b);
		// Math.abs(Long.MIN_VALUE) == Long.MIN_VALUE
		if (result == Long.MIN_VALUE) {
			throw new ArithmeticException();
		}
		return result;
	}

	/**
	 * Note that the final result of this function may have overflowed and wrapped around which is why
	 * it returns the absolute value of the final result.
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
	 *             If <code>((a / MathUtil.gcd(a, b)) * b) == Integer.MIN_VALUE</code>
	 */
	public static int lcm(int a, int b) {
		final int result = Math.abs((int) MathUtil.lcm((long) a, (long) b));
		// Math.abs(Integer.MIN_VALUE) == Integer.MIN_VALUE
		if (result == Integer.MIN_VALUE) {
			throw new ArithmeticException();
		}
		return result;
	}

	/**
	 * Note that the final result of this function may have overflowed and wrapped around which is why
	 * it returns the absolute value of the final result.
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
	 *             If <code>((a / MathUtil.gcd(a, b)) * b) == Short.MIN_VALUE</code>
	 */
	public static short lcm(short a, short b) {
		final short result = (short) Math.abs((short) MathUtil.lcm((long) a, (long) b));
		// ((short) Math.abs(Short.MIN_VALUE)) == Short.MIN_VALUE
		if (result == Short.MIN_VALUE) {
			throw new ArithmeticException();
		}
		return result;
	}

	/**
	 * Note that the final result of this function may have overflowed and wrapped around which is why
	 * it returns the absolute value of the final result.
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
	 *             If <code>((a / MathUtil.gcd(a, b)) * b) == Byte.MIN_VALUE</code>
	 */
	public static byte lcm(byte a, byte b) {
		final byte result = (byte) Math.abs((byte) MathUtil.lcm((long) a, (long) b));
		// ((byte) Math.abs(Byte.MIN_VALUE)) == Byte.MIN_VALUE
		if (result == Byte.MIN_VALUE) {
			throw new ArithmeticException();
		}
		return result;
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
	 *             If <code>m <= 0</code>
	 */
	public static long mod(long n, long m) throws InvalidModulusException {
		if (m <= 0L) {
			throw new InvalidModulusException();
		}
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
	 *             If <code>m <= 0</code>
	 */
	public static int mod(int n, int m) throws InvalidModulusException {
		return ((int) MathUtil.mod((long) n, (long) m));
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
	 *             If <code>m <= 0</code>
	 */
	public static short mod(short n, short m) throws InvalidModulusException {
		return ((short) MathUtil.mod((long) n, (long) m));
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
	 *             If <code>m <= 0</code>
	 */
	public static byte mod(byte n, byte m) throws InvalidModulusException {
		return ((byte) MathUtil.mod((long) n, (long) m));
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
	 *             If <code>m <= 1</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n, m) != 1</code>
	 */
	public static long modInverse(long n, long m) throws InvalidModulusException, UndefinedInverseException {
		if (m <= 1L) {
			throw new InvalidModulusException();
		}
		// m > 1

		// Fix n to be in [0, m - 1] \cap \doubleZ.
		n = MathUtil.mod(n, m);
		if (n == 0L) {
			/**
			 * Handle <code>n == 0</code> case separately because the loop will never actually execute (since
			 * the loop condition is <code>n > 1</code>) and so the function will return <code>1</code> as the
			 * multiplicative inverse of <code>0</code> which is wrong since <code>0</code> does not have a
			 * multiplicative inverse.
			 */
			throw new UndefinedInverseException();
		}
		// (1 <= n) && (n <= m - 1)

		/**
		 * No need to check <code>gcd(n, m) != 1</code> since if that is the case, then in the body of the
		 * following loop, remainder will become 0 in some iteration and so the code handles the non-coprime
		 * case indirectly.
		 */
		long x = 1L;
		for (long y = 0L, quotient = 0L, remainder = m, tmp = 0L; n > 1L; /* Update inside. */) {
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
	 *             If <code>m <= 1</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n, m) != 1</code>
	 */
	public static int modInverse(int n, int m) throws InvalidModulusException, UndefinedInverseException {
		return ((int) MathUtil.modInverse((long) n, (long) m));
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
	 *             If <code>m <= 1</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n, m) != 1</code>
	 */
	public static short modInverse(short n, short m) throws InvalidModulusException, UndefinedInverseException {
		return ((short) MathUtil.modInverse((long) n, (long) m));
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
	 *             If <code>m <= 1</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n, m) != 1</code>
	 */
	public static byte modInverse(byte n, byte m) throws InvalidModulusException, UndefinedInverseException {
		return ((byte) MathUtil.modInverse((long) n, (long) m));
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
			 * <code>(1 <= -n <= m - 1 < m <= Long.MAX_VALUE)</code>.
			 */
			final long other = n + m, abs_n = -n;
			return ((abs_n < other) ? n : other);
		}
		// n >= 0

		/**
		 * By the precondition on <code>n</code>, we know that <code>(0 <= n <= m - 1)</code>. Therefore,
		 * <code>(-m <= other <= -1)</code> and so <code>other < 0</code> but <code>-other</code> will not
		 * overflow since <code>(1 <= -other <= m <= Long.MAX_VALUE)</code>.
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
	 *             If <code>m <= 0</code>
	 */
	public static long modMin(long n, long m) throws InvalidModulusException {
		if (m <= 0L) {
			throw new InvalidModulusException();
		}
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
	 *             If <code>m <= 0</code>
	 */
	public static int modMin(int n, int m) throws InvalidModulusException {
		if (m <= 0) {
			throw new InvalidModulusException();
		}
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
	 *             If <code>m <= 0</code>
	 */
	public static short modMin(short n, short m) throws InvalidModulusException {
		if (m <= 0) {
			throw new InvalidModulusException();
		}
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
	 *             If <code>m <= 0</code>
	 */
	public static byte modMin(byte n, byte m) throws InvalidModulusException {
		if (m <= 0) {
			throw new InvalidModulusException();
		}
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
	 *             If <code>m <= 0</code>
	 */
	public static long modAdd(long a, long b, long m) throws InvalidModulusException {
		if (m <= 0L) {
			throw new InvalidModulusException();
		}
		long result = MathUtil.modAddFixedInput(MathUtil.modMinFixedInput(a %= m, m),
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
	 * @return <code>a + b (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m <= 0</code>
	 */
	public static int modAdd(int a, int b, int m) throws InvalidModulusException {
		if (m <= 0) {
			throw new InvalidModulusException();
		}
		int result = (int) MathUtil.modAddFixedInput(MathUtil.modMinFixedInput(a %= m, m),
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
	 * @return <code>a + b (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m <= 0</code>
	 */
	public static short modAdd(short a, short b, short m) throws InvalidModulusException {
		if (m <= 0) {
			throw new InvalidModulusException();
		}
		short result = (short) MathUtil.modAddFixedInput(MathUtil.modMinFixedInput(a %= m, m),
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
	 * @return <code>a + b (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m <= 0</code>
	 */
	public static byte modAdd(byte a, byte b, byte m) throws InvalidModulusException {
		if (m <= 0) {
			throw new InvalidModulusException();
		}
		byte result = (byte) MathUtil.modAddFixedInput(MathUtil.modMinFixedInput(a %= m, m),
				MathUtil.modMinFixedInput(b %= m, m), m);
		return ((result < 0) ? (result += m) : result);
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
	 *             If <code>m <= 0</code>
	 */
	public static long modSub(long a, long b, long m) throws InvalidModulusException {
		if (m <= 0L) {
			throw new InvalidModulusException();
		}
		long result = MathUtil.modSubFixedInput(MathUtil.modMinFixedInput(a %= m, m),
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
	 * @return <code>a - b (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m <= 0</code>
	 */
	public static int modSub(int a, int b, int m) throws InvalidModulusException {
		if (m <= 0) {
			throw new InvalidModulusException();
		}
		int result = (int) MathUtil.modSubFixedInput(MathUtil.modMinFixedInput(a %= m, m),
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
	 * @return <code>a - b (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m <= 0</code>
	 */
	public static short modSub(short a, short b, short m) throws InvalidModulusException {
		if (m <= 0) {
			throw new InvalidModulusException();
		}
		short result = (short) MathUtil.modSubFixedInput(MathUtil.modMinFixedInput(a %= m, m),
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
	 * @return <code>a - b (mod m)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m <= 0</code>
	 */
	public static byte modSub(byte a, byte b, byte m) throws InvalidModulusException {
		if (m <= 0) {
			throw new InvalidModulusException();
		}
		byte result = (byte) MathUtil.modSubFixedInput(MathUtil.modMinFixedInput(a %= m, m),
				MathUtil.modMinFixedInput(b %= m, m), m);
		return ((result < 0) ? (result += m) : result);
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
		long min = (a < 0L) ? (a + m) : a, tmp = (b < 0L) ? (b + m) : b, max = b;
		// Fix min and max if needed.
		if (tmp < min) {
			min = tmp;
			max = a;
		}
		// min == Math.min(MathUtil.mod(a, m), MathUtil.mod(b, m))

		long result = 0L;
		for (; min != 0L; min /= 2L) {
			if (!MathUtil.isEven(min)) {
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
	 *             If <code>m <= 0</code>
	 */
	public static long modMult(long a, long b, long m) throws InvalidModulusException {
		if (m <= 0L) {
			throw new InvalidModulusException();
		}
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
	 *             If <code>m <= 0</code>
	 */
	public static int modMult(int a, int b, int m) throws InvalidModulusException {
		if (m <= 0) {
			throw new InvalidModulusException();
		}
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
	 *             If <code>m <= 0</code>
	 */
	public static short modMult(short a, short b, short m) throws InvalidModulusException {
		if (m <= 0) {
			throw new InvalidModulusException();
		}
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
	 *             If <code>m <= 0</code>
	 */
	public static byte modMult(byte a, byte b, byte m) throws InvalidModulusException {
		if (m <= 0) {
			throw new InvalidModulusException();
		}
		byte result = (byte) MathUtil.modMultFixedInput(MathUtil.modMinFixedInput(a %= m, m),
				MathUtil.modMinFixedInput(b %= m, m), m);
		return ((result < 0) ? (result += m) : result);
	}

	/**
	 * Compute <code>n<sup>p</sup> (mod m)</code> using the fast power (a.k.a., successive squaring)
	 * algorithm. <br>
	 * Note that this function does not check the special cases <code>n (mod m) == 1</code> or
	 * <code>n (mod m) == -1 (mod m) == m - 1</code> and so it will still take <code>O(lg(p))</code>
	 * steps even though the answer can be trivially determined in <code>O(1)</code> steps. Therefore,
	 * for the best performance, it is recommended to check those cases before calling this function.
	 * The reason why it does not check for the special cases, is that this function is specified as
	 * protected and is only called by other public functions which do handle those special cases
	 * themselves (in their own unique ways) and so checking for the special cases here, would only
	 * serve to slow down the overall runtime. <br>
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
			if (!MathUtil.isEven(p)) {
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
	 *             If <code>m <= 0</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(p < 0) && (gcd(n, m) != 1)</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(p == 0) && (n (mod m) == 0)</code>
	 */
	public static long modPow(long n, long p, long m)
			throws InvalidModulusException, UndefinedInverseException, ArithmeticException {
		// Handle the simple special case.
		if (m == 1L) {
			return 0L;
		}
		// m != 1

		// Fix n to be in [0, m - 1] \cap \doubleZ.
		n = MathUtil.mod(n, m);
		// m > 0
		// i.e., m > 1
		if (n == 0L) {
			/**
			 * We could just check <code>p == 0L</code> here, and the case when <code>p < 0L</code> would still
			 * be handled by the rest of code since the <code>modInverse</code> function would have been called
			 * and it would have thrown the UndefinedInverseException. However, that will take longer since more
			 * code has to be executed to arrive at the same result which is why for the sake of efficiency,
			 * we're performing the check here.
			 */
			if (p < 0L) {
				throw new UndefinedInverseException();
			} else if (p == 0L) {
				throw new ArithmeticException();
			}
			return 0L;
		} else if (n == 1L) {
			// 1 to any power is 1.
			return 1L;
		} else if (n == m - 1L) { // i.e., n == -1 (mod m)
			/**
			 * <code>-1<sup>p</sup> (mod m)</code> is: <br>
			 * <code>-1 (mod m)</code> if <code>p</code> is odd <br>
			 * <code>1 (mod m)</code> if <code>p</code> is even
			 */
			return (MathUtil.isEven(p) ? 1L : n);
		}
		// (1 < n) && (n < m - 1)

		/**
		 * <code>n<sup>p</sup> (mod m)</code> is: <br>
		 * <code>(n<sup>-1</sup> (mod m))<sup>|p|</sup> (mod m)</code> if <code>p < 0</code> <br>
		 * <code>1</code> if <code>p == 0</code> <br>
		 * <code>n<sup>|p|</sup> (mod m)</code> if <code>p > 0</code>
		 */
		if (p < 0L) {
			final long n_inverse = MathUtil.modInverse(n, m);
			// Handle the degenerate case where p's absolute value is not representable as a non-negative long.
			if (p == Long.MIN_VALUE) { // i.e., -p == p < 0
				/**
				 * <code>n<sup>(-2<sup>63</sup>)</sup> (mod m) == (n<sup>-1</sup>)<sup>(2<sup>63</sup> - 1)</sup> * n<sup>-1</sup> (mod m)</code>
				 */
				long result = MathUtil.modPowFixedInput(n_inverse, Long.MAX_VALUE, m);
				result = MathUtil.modMultFixedInput(result, MathUtil.modMinFixedInput(n_inverse, m), m);
				return ((result < 0L) ? (result += m) : result);
			}
			// -p > 0
			long result = MathUtil.modPowFixedInput(n_inverse, -p, m);
			return ((result < 0L) ? (result += m) : result);
		} else if (p == 0L) {
			return 1L;
		}
		// p > 0
		long result = MathUtil.modPowFixedInput(n, p, m);
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
	 *             If <code>m <= 0</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(p < 0) && (gcd(n, m) != 1)</code>
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
	 *             If <code>m <= 0</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(p < 0) && (gcd(n, m) != 1)</code>
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
	 *             If <code>m <= 0</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(p < 0) && (gcd(n, m) != 1)</code>
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
	 *             If <code>m <= 0</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 0) || (upperOrder > m)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n, m) != 1</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>((long) Math.floor(Math.sqrt(upperOrder)) + 1) > Integer.MAX_VALUE</code>
	 */
	public static Long discreteLogBabyGiant(long n, long target, long m, long upperOrder, boolean generateBoth,
			boolean hash)
			throws InvalidModulusException, IllegalArgumentException, UndefinedInverseException, ArithmeticException {
		// Fix n to be in [0, m - 1] \cap \doubleZ.
		n = MathUtil.mod(n, m);
		// m > 0
		// Fix target to be in [0, m - 1] \cap \doubleZ.
		target = MathUtil.mod(target, m);

		// Validate upperOrder.
		if ((upperOrder < 0L) || (upperOrder > m)) {
			throw new IllegalArgumentException();
		}
		// (0 <= upperOrder) && (upperOrder <= m)

		// Handle the simple special cases.
		if (n == 0L) {
			// 0 to any non-zero power 0. 0 to the power of 0 is undefined.
			return ((target == 0L) ? 1L : null);
		} else if (target == 1L) {
			// n to the power of 0 is 1 except when n is 0 which we know isn't the case.
			return 0L;
		}
		// (n != 0) && (target != 1) && (m != 1)
		// i.e., (n != 0) && (target != 1) && (m > 1)

		if (n == 1L) {
			// 1 to any power is 1 (but target != 1).
			return null;
		} else if (n == m - 1L) { // i.e., n == -1 (mod m)
			// -1 to any even power is 1 (but target != 1) and otherwise is -1.
			return ((target == n) ? 1L : null);
		}
		// (1 < n) && (n < m - 1)

		if (upperOrder == 0L) {
			return null;
		}
		// upperOrder != 0
		// i.e., (1 <= upperOrder) && (upperOrder <= m)

		// Fix n to be in [-m / 2, m / 2] \cap \doubleZ.
		n = MathUtil.modMinFixedInput(n, m);
		// Fix target to be in [-m / 2, m / 2] \cap \doubleZ.
		target = MathUtil.modMinFixedInput(target, m);
		// Compute and save <code>n<sup>-1</sup> (mod m)</code> for later.
		final long n_inverse = MathUtil.modInverse(n, m);

		// Shanks' Babystep Giantstep Algorithm.
		final long bound = ((long) Math.floor(Math.sqrt(upperOrder))) + 1L; // bound >= 2
		if (bound > Integer.MAX_VALUE) {
			throw new ArithmeticException();
		}
		// bound <= Integer.MAX_VALUE
		final long giant_factor = MathUtil.modPowFixedInput(n_inverse, bound, m);

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
				if ((giant = MathUtil.modMultFixedInput(giant, giant_factor, m)) == target) {
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
	 *             If <code>m <= 0</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 0) || (upperOrder > m)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n, m) != 1</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>((long) Math.floor(Math.sqrt(upperOrder)) + 1) > Integer.MAX_VALUE</code>
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
	 *             If <code>m <= 0</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 0) || (upperOrder > m)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n, m) != 1</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>((long) Math.floor(Math.sqrt(upperOrder)) + 1) > Integer.MAX_VALUE</code>
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
	 *             If <code>m <= 0</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n, m) != 1</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>((long) Math.floor(Math.sqrt(m)) + 1) > Integer.MAX_VALUE</code>
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
	 *             If <code>m <= 0</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 0) || (upperOrder > m)</code>
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
	 *             If <code>m <= 0</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 0) || (upperOrder > m)</code>
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
	 *             If <code>m <= 0</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 0) || (upperOrder > m)</code>
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
	 *             If <code>m <= 0</code>
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
	 *             If <code>m <= 0</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 0) || (upperOrder > m)</code>
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
	 *             If <code>m <= 0</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 0) || (upperOrder > m)</code>
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
	 *             If <code>m <= 0</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 0) || (upperOrder > m)</code>
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
	 *             If <code>m <= 0</code>
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
	 *             If <code>m <= 0</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 0) || (upperOrder > m)</code>
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
	 *             If <code>m <= 0</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 0) || (upperOrder > m)</code>
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
	 *             If <code>m <= 0</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(upperOrder < 0) || (upperOrder > m)</code>
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
	 *             If <code>m <= 0</code>
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
	 * @throws IllegalArgumentException
	 *             If <code>begin > end</code>
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m <= 0</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(begin < 0) && (gcd(n, m) != 1)</code>
	 */
	public static Long discreteLogLinearSearch(long n, long target, long m, long begin, long end)
			throws IllegalArgumentException, InvalidModulusException, UndefinedInverseException {
		// Validate begin and end.
		if (begin > end) {
			throw new IllegalArgumentException();
		}
		// begin <= end

		// Fix n to be in [0, m - 1] \cap \doubleZ.
		n = MathUtil.mod(n, m);
		// m > 0
		// Fix target to be in [0, m - 1] \cap \doubleZ.
		target = MathUtil.mod(target, m);

		// Handle the simple special cases.
		if (n == 0L) {
			// 0 to any non-zero power 0. 0 to the power of 0 is undefined.
			return ((target == 0L) ? 1L : null);
		} else if (target == 1L) {
			// n to the power of 0 is 1 except when n is 0 which we know isn't the case.
			return 0L;
		}
		// (n != 0) && (target != 1) && (m != 1)
		// i.e., (n != 0) && (target != 1) && (m > 1)

		if (n == 1L) {
			// 1 to any power is 1 (but target != 1).
			return null;
		} else if (n == m - 1L) { // i.e., n == -1 (mod m)
			// -1 to any even power is 1 (but target != 1) and otherwise is -1.
			return ((target == n) ? 1L : null);
		}
		// (1 < n) && (n < m - 1)

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
	 *             If <code>m <= 0</code>
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
	 * @throws IllegalArgumentException
	 *             If <code>begin > end</code>
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m <= 0</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(begin < 0) && (gcd(n, m) != 1)</code>
	 */
	public static Integer discreteLogLinearSearch(int n, int target, int m, int begin, int end)
			throws IllegalArgumentException, InvalidModulusException, UndefinedInverseException {
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
	 *             If <code>m <= 0</code>
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
	 * @throws IllegalArgumentException
	 *             If <code>begin > end</code>
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m <= 0</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(begin < 0) && (gcd(n, m) != 1)</code>
	 */
	public static Short discreteLogLinearSearch(short n, short target, short m, short begin, short end)
			throws IllegalArgumentException, InvalidModulusException, UndefinedInverseException {
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
	 *             If <code>m <= 0</code>
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
	 * @throws IllegalArgumentException
	 *             If <code>begin > end</code>
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m <= 0</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(begin < 0) && (gcd(n, m) != 1)</code>
	 */
	public static Byte discreteLogLinearSearch(byte n, byte target, byte m, byte begin, byte end)
			throws IllegalArgumentException, InvalidModulusException, UndefinedInverseException {
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
	 *             If <code>m <= 0</code>
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
			long result = end -= begin;
			if (result > Integer.MAX_VALUE) {
				throw new ArithmeticException();
			}
			return ((int) result);
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
		// -begin > 0
		return ((int) (end -= begin));
	}

	/**
	 * This function defines <code>0<sup>0</sup> == 0</code> even though it is undefined in math. <br>
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
	 * @throws IllegalArgumentException
	 *             If <code>begin > end</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(end - begin) > Integer.MAX_VALUE</code>
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m <= 0</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(begin < 0) && (gcd(n, m) != 1)</code>
	 */
	public static long[] powers(long n, int m, long begin, long end)
			throws IllegalArgumentException, ArithmeticException, InvalidModulusException, UndefinedInverseException {
		final int length = MathUtil.powersLength(begin, end);

		// Fix n to be in [0, m - 1] \cap \doubleZ.
		n = MathUtil.mod(n, m);
		// m > 0

		// Create resulting long array and handle the simple special cases.
		final long[] result = new long[length];
		if (n == 0L) {
			/**
			 * This case is needed since 0 to any non-zero power is 0 and so any non-zero assignment of
			 * <code>result[i]</code> will be wrong in this case. Furthermore, note that we are defining
			 * <code>0<sup>0</sup> == 0</code> here even though it is undefined in math.
			 */
			return result;
		}
		// (n != 0) && (m != 1)
		// i.e., (n != 0) && (m > 1)

		if (n == 1L) {
			/*
			 * This case is only an optimization since 1 to any power is 1 and so the loop will do extra
			 * unnecessary work to arrive at the same result.
			 */
			Arrays.fill(result, 1L);
			return result;
		} else if (n == m - 1L) {
			/*
			 * This case is only an optimization since -1 to any even power is 1 and otherwise is -1. So the
			 * loop will do extra unnecessary work to arrive at the same result.
			 */
			for (int i = MathUtil.isEven(begin) ? 1 : 0; i < length; i += 2) {
				result[i] = n;
			}
			return result;
		}
		// (1 < n) && (n < m - 1)

		// Fix n to be in [-m / 2, m / 2] \cap \doubleZ.
		n = MathUtil.modMinFixedInput(n, m);

		// Fill and return resulting long array.
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
	 * This function defines <code>0<sup>0</sup> == 0</code> even though it is undefined in math. <br>
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
	 *             If <code>m <= 0</code>
	 */
	public static long[] powers(long n, int m) throws InvalidModulusException {
		return MathUtil.powers(n, m, 0L, m);
	}

	/**
	 * This function defines <code>0<sup>0</sup> == 0</code> even though it is undefined in math. <br>
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
	 * @return The resulting int array.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>begin > end</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(end - begin) > Integer.MAX_VALUE</code>
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m <= 0</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(begin < 0) && (gcd(n, m) != 1)</code>
	 */
	public static int[] powers(int n, int m, long begin, long end)
			throws IllegalArgumentException, ArithmeticException, InvalidModulusException, UndefinedInverseException {
		final int length = MathUtil.powersLength(begin, end);

		// Fix n to be in [0, m - 1] \cap \doubleZ.
		n = MathUtil.mod(n, m);
		// m > 0

		// Create resulting int array and handle the simple special cases.
		final int[] result = new int[length];
		if (n == 0) {
			/**
			 * This case is needed since 0 to any non-zero power is 0 and so any non-zero assignment of
			 * <code>result[i]</code> will be wrong in this case. Furthermore, note that we are defining
			 * <code>0<sup>0</sup> == 0</code> here even though it is undefined in math.
			 */
			return result;
		}
		// (n != 0) && (m != 1)
		// i.e., (n != 0) && (m > 1)

		if (n == 1) {
			/*
			 * This case is only an optimization since 1 to any power is 1 and so the loop will do extra
			 * unnecessary work to arrive at the same result.
			 */
			Arrays.fill(result, 1);
			return result;
		} else if (n == m - 1) {
			/*
			 * This case is only an optimization since -1 to any even power is 1 and otherwise is -1. So the
			 * loop will do extra unnecessary work to arrive at the same result.
			 */
			for (int i = MathUtil.isEven(begin) ? 1 : 0; i < length; i += 2) {
				result[i] = n;
			}
			return result;
		}
		// (1 < n) && (n < m - 1)

		// Fix n to be in [-m / 2, m / 2] \cap \doubleZ.
		n = (int) MathUtil.modMinFixedInput(n, m);

		// Fill and return resulting int array.
		int n_to_i = (int) MathUtil.modPow(n, begin, m);
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
	 * This function defines <code>0<sup>0</sup> == 0</code> even though it is undefined in math. <br>
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
	 * @return The resulting int array.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m <= 0</code>
	 */
	public static int[] powers(int n, int m) throws InvalidModulusException {
		return MathUtil.powers(n, m, 0L, m);
	}

	/**
	 * This function defines <code>0<sup>0</sup> == 0</code> even though it is undefined in math. <br>
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
	 * @throws IllegalArgumentException
	 *             If <code>begin > end</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(end - begin) > Integer.MAX_VALUE</code>
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m <= 0</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(begin < 0) && (gcd(n, m) != 1)</code>
	 */
	public static short[] powers(short n, short m, long begin, long end)
			throws IllegalArgumentException, ArithmeticException, InvalidModulusException, UndefinedInverseException {
		final int length = MathUtil.powersLength(begin, end);

		// Fix n to be in [0, m - 1] \cap \doubleZ.
		n = MathUtil.mod(n, m);
		// m > 0

		// Create resulting short array and handle the simple special cases.
		final short[] result = new short[length];
		if (n == 0) {
			/**
			 * This case is needed since 0 to any non-zero power is 0 and so any non-zero assignment of
			 * <code>result[i]</code> will be wrong in this case. Furthermore, note that we are defining
			 * <code>0<sup>0</sup> == 0</code> here even though it is undefined in math.
			 */
			return result;
		}
		// (n != 0) && (m != 1)
		// i.e., (n != 0) && (m > 1)

		if (n == 1) {
			/*
			 * This case is only an optimization since 1 to any power is 1 and so the loop will do extra
			 * unnecessary work to arrive at the same result.
			 */
			Arrays.fill(result, (short) 1);
			return result;
		} else if (n == m - 1) {
			/*
			 * This case is only an optimization since -1 to any even power is 1 and otherwise is -1. So the
			 * loop will do extra unnecessary work to arrive at the same result.
			 */
			for (int i = MathUtil.isEven(begin) ? 1 : 0; i < length; i += 2) {
				result[i] = n;
			}
			return result;
		}
		// (1 < n) && (n < m - 1)

		// Fix n to be in [-m / 2, m / 2] \cap \doubleZ.
		n = (short) MathUtil.modMinFixedInput(n, m);

		// Fill and return resulting short array.
		short n_to_i = (short) MathUtil.modPow(n, begin, m);
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
	 * This function defines <code>0<sup>0</sup> == 0</code> even though it is undefined in math. <br>
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
	 *             If <code>m <= 0</code>
	 */
	public static short[] powers(short n, short m) throws InvalidModulusException {
		return MathUtil.powers(n, m, 0L, m);
	}

	/**
	 * This function defines <code>0<sup>0</sup> == 0</code> even though it is undefined in math. <br>
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
	 * @throws IllegalArgumentException
	 *             If <code>begin > end</code>
	 * 
	 * @throws ArithmeticException
	 *             If <code>(end - begin) > Integer.MAX_VALUE</code>
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m <= 0</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>(begin < 0) && (gcd(n, m) != 1)</code>
	 */
	public static byte[] powers(byte n, byte m, long begin, long end)
			throws IllegalArgumentException, ArithmeticException, InvalidModulusException, UndefinedInverseException {
		final int length = MathUtil.powersLength(begin, end);

		// Fix n to be in [0, m - 1] \cap \doubleZ.
		n = MathUtil.mod(n, m);
		// m > 0

		// Create resulting byte array and handle the simple special cases.
		final byte[] result = new byte[length];
		if (n == 0) {
			/**
			 * This case is needed since 0 to any non-zero power is 0 and so any non-zero assignment of
			 * <code>result[i]</code> will be wrong in this case. Furthermore, note that we are defining
			 * <code>0<sup>0</sup> == 0</code> here even though it is undefined in math.
			 */
			return result;
		}
		// (n != 0) && (m != 1)
		// i.e., (n != 0) && (m > 1)

		if (n == 1) {
			/*
			 * This case is only an optimization since 1 to any power is 1 and so the loop will do extra
			 * unnecessary work to arrive at the same result.
			 */
			Arrays.fill(result, (byte) 1);
			return result;
		} else if (n == m - 1) {
			/*
			 * This case is only an optimization since -1 to any even power is 1 and otherwise is -1. So the
			 * loop will do extra unnecessary work to arrive at the same result.
			 */
			for (int i = MathUtil.isEven(begin) ? 1 : 0; i < length; i += 2) {
				result[i] = n;
			}
			return result;
		}
		// (1 < n) && (n < m - 1)

		// Fix n to be in [-m / 2, m / 2] \cap \doubleZ.
		n = (byte) MathUtil.modMinFixedInput(n, m);

		// Fill and return resulting byte array.
		byte n_to_i = (byte) MathUtil.modPow(n, begin, m);
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
	 * This function defines <code>0<sup>0</sup> == 0</code> even though it is undefined in math. <br>
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
	 *             If <code>m <= 0</code>
	 */
	public static byte[] powers(byte n, byte m) throws InvalidModulusException {
		return MathUtil.powers(n, m, 0L, m);
	}

	/**
	 * Chinese Remainder Theorem.
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
	 * @return <code>n1 * m2 * (m2<sup>-1</sup> (mod m1)) + n2 * m1 * (m1<sup>-1</sup> (mod m2)) (mod m1 * m2)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>(m1 <= 0) || (m2 <= 0)</code>
	 * 
	 * @throws ArithmeticException
	 *             Thrown by <code>Math.multiplyExact(m1, m2)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(m1, m2) != 1</code>
	 */
	public static long crt(long n1, long m1, long n2, long m2)
			throws InvalidModulusException, ArithmeticException, UndefinedInverseException {
		if ((m1 <= 0L) || (m2 <= 0L)) {
			throw new InvalidModulusException();
		}
		// (m1 > 0) && (m2 > 0)

		// Compute the new modulus.
		final long m = Math.multiplyExact(m1, m2);

		// Find integers x and y such that x * m1 + y * m2 == gcd(m1, m2).
		final long[] result = MathUtil.gcdExtended(m1, m2);
		if (result[2] != 1L) {
			throw new UndefinedInverseException();
		}

		// Fix all variables to be in [-m / 2, m / 2] \cap \doubleZ.
		final long m1_inverse = MathUtil.modMinFixedInput(result[0] %= m2, m),
				m2_inverse = MathUtil.modMinFixedInput(result[1] %= m1, m);
		n1 = MathUtil.modMinFixedInput(n1 %= m, m);
		n2 = MathUtil.modMinFixedInput(n2 %= m, m);
		m1 = MathUtil.modMinFixedInput(m1, m);
		m2 = MathUtil.modMinFixedInput(m2, m);

		/*
		 * Apply the C.R.T. formula for two congruences but maintain all variables being in [-m / 2, m / 2]
		 * \cap \doubleZ.
		 */
		long lhs = MathUtil.modMultFixedInput(MathUtil.modMultFixedInput(n1, m2, m), m2_inverse, m);
		final long rhs = MathUtil.modMultFixedInput(MathUtil.modMultFixedInput(n2, m1, m), m1_inverse, m);
		return (((lhs = (lhs += rhs) % m) < 0L) ? (lhs += m) : lhs);
	}

	/**
	 * Chinese Remainder Theorem.
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
	 * @return <code>n1 * m2 * (m2<sup>-1</sup> (mod m1)) + n2 * m1 * (m1<sup>-1</sup> (mod m2)) (mod m1 * m2)</code>.
	 * 
	 * @throws ArithmeticException
	 *             If <code>((long) m1) * ((long) m2) > Integer.MAX_VALUE</code>
	 * 
	 * @throws InvalidModulusException
	 *             If <code>(m1 <= 0) || (m2 <= 0)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(m1, m2) != 1</code>
	 */
	public static int crt(int n1, int m1, int n2, int m2)
			throws ArithmeticException, InvalidModulusException, UndefinedInverseException {
		if (((long) m1) * ((long) m2) > Integer.MAX_VALUE) {
			throw new ArithmeticException();
		}
		return ((int) MathUtil.crt((long) n1, (long) m1, (long) n2, (long) m2));
	}

	/**
	 * Chinese Remainder Theorem.
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
	 * @return <code>n1 * m2 * (m2<sup>-1</sup> (mod m1)) + n2 * m1 * (m1<sup>-1</sup> (mod m2)) (mod m1 * m2)</code>.
	 * 
	 * @throws ArithmeticException
	 *             If <code>((long) m1) * ((long) m2) > Short.MAX_VALUE</code>
	 * 
	 * @throws InvalidModulusException
	 *             If <code>(m1 <= 0) || (m2 <= 0)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(m1, m2) != 1</code>
	 */
	public static short crt(short n1, short m1, short n2, short m2)
			throws ArithmeticException, InvalidModulusException, UndefinedInverseException {
		if (((long) m1) * ((long) m2) > Short.MAX_VALUE) {
			throw new ArithmeticException();
		}
		return ((short) MathUtil.crt((long) n1, (long) m1, (long) n2, (long) m2));
	}

	/**
	 * Chinese Remainder Theorem.
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
	 * @return <code>n1 * m2 * (m2<sup>-1</sup> (mod m1)) + n2 * m1 * (m1<sup>-1</sup> (mod m2)) (mod m1 * m2)</code>.
	 * 
	 * @throws ArithmeticException
	 *             If <code>((long) m1) * ((long) m2) > Byte.MAX_VALUE</code>
	 * 
	 * @throws InvalidModulusException
	 *             If <code>(m1 <= 0) || (m2 <= 0)</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(m1, m2) != 1</code>
	 */
	public static byte crt(byte n1, byte m1, byte n2, byte m2)
			throws ArithmeticException, InvalidModulusException, UndefinedInverseException {
		if (((long) m1) * ((long) m2) > Byte.MAX_VALUE) {
			throw new ArithmeticException();
		}
		return ((byte) MathUtil.crt((long) n1, (long) m1, (long) n2, (long) m2));
	}
}
