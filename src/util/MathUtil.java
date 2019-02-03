package util;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Utility math methods in addition to Java's Math class.
 * 
 * @author Ashkan Moatamed
 */
public class MathUtil {
	/**
	 * Dependencies: <code>
	 * 		1. util.InvalidModulusException
	 * 		1. util.UndefinedInverseException
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
	 *             If <code>(a < 0) || (b < 0)</code>
	 */
	public static long[] gcdExtended(long a, long b) throws ArithmeticException {
		if ((a < 0L) || (b < 0L)) {
			throw new ArithmeticException();
		}

		// Handle the special cases where at least one of the two numbers is 0.
		if (a == 0L) {
			if (b == 0L) {
				// 0 * 0 + 0 * 0 == 0
				return new long[] { 0L, 0L, 0L };
			} // b != 0

			// 0 * a + 1 * b == b == gcd(0, b)
			return new long[] { 0L, 1L, b };
		} else if (b == 0L) { // a != 0
			// 1 * a + 0 * b == a == gcd(a, 0)
			return new long[] { 1L, 0L, a };
		}
		// (a != 0) && (b != 0)

		// Algorithm is from Introduction to Mathematical Cryptography 2nd Edition Exercise 1.12.
		long gcd = a, x = 1L;
		{
			long u = 0L, v = b, remainder = 0L, quotient = gcd, tmp = 0L;
			do {
				// Compute the quotient and the remainder.
				remainder = gcd - (quotient /= v) * v;
				// (remainder == gcd % v) && (gcd == quotient * v + remainder)

				// Update all of the variables.
				tmp = x - quotient * u;
				quotient = gcd = v;
				x = u;
				u = tmp;
				v = remainder;
			} while (v != 0L);
		}
		// x * a + y * b == gcd where y == (gcd - x * a) / b
		return new long[] { x, (gcd - x * a) / b, gcd };
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
	 *             If <code>(a < 0) || (b < 0)</code>
	 */
	public static int[] gcdExtended(int a, int b) throws ArithmeticException {
		final long[] result = MathUtil.gcdExtended((long) a, (long) b);
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
	 *             If <code>(a < 0) || (b < 0)</code>
	 */
	public static short[] gcdExtended(short a, short b) throws ArithmeticException {
		final long[] result = MathUtil.gcdExtended((long) a, (long) b);
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
	 *             If <code>(a < 0) || (b < 0)</code>
	 */
	public static byte[] gcdExtended(byte a, byte b) throws ArithmeticException {
		final long[] result = MathUtil.gcdExtended((long) a, (long) b);
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
	 * We first attempt to multiply <code>a</code> and <code>b</code> normally using
	 * <code>Math.multiplyExact</code> in <code>O(1) time</code>. If an overflow occurs during the
	 * multiplication, then we perform <code>O(lg(min(a (mod m), b (mod m))))</code> many additions in
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
		/**
		 * The following multiplication will not overflow so long as
		 * <code>(m / 2)<sup>2</sup> <= Long.MAX_VALUE</code>. Its runtime is in <code>O(1)</code>.
		 */
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
	 * Precondition: <code>m > 1</code> <br>
	 * Precondition: <code>|n| < m</code> <br>
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
	protected static long modPowHelper(long n, long p, long m) {
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

		// Handle the degenerate case where p's absolute value is not representable as a non-negative long.
		if (p == Long.MIN_VALUE) {
			/**
			 * <code>n<sup>(-2<sup>63</sup>)</sup> (mod m) == (n<sup>-1</sup>)<sup>(2<sup>63</sup> - 1)</sup> * n<sup>-1</sup> (mod m)</code>
			 */
			final long n_inverse = MathUtil.modInverse(n, m);
			long result = MathUtil.modPowHelper(n_inverse, Long.MAX_VALUE, m);
			result = MathUtil.modMultFixedInput(result, MathUtil.modMinFixedInput(n_inverse, m), m);
			return ((result < 0L) ? (result += m) : result);
		}

		/**
		 * <code>n<sup>p</sup> (mod m)</code> is: <br>
		 * <code>(n<sup>-1</sup> (mod m))<sup>|p|</sup> (mod m)</code> if <code>p < 0</code> <br>
		 * <code>n<sup>|p|</sup> (mod m)</code> if <code>p >= 0</code>
		 */
		long result = (p < 0L) ? MathUtil.modPowHelper(MathUtil.modInverse(n, m), -p, m)
				: MathUtil.modPowHelper(n, p, m);
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
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m <= 0</code>
	 * 
	 * @throws UndefinedInverseException
	 *             If <code>gcd(n, m) != 1</code>
	 * 
	 * @throws OutOfMemoryError
	 *             If <code>((long) Math.ceil(Math.sqrt(m))) > Integer.MAX_VALUE</code>
	 */
	public static Long discreteLogBabyGiant(long n, long target, long m)
			throws InvalidModulusException, UndefinedInverseException, OutOfMemoryError {
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

		// Fix n to be in [-m / 2, m / 2] \cap \doubleZ.
		n = MathUtil.modMinFixedInput(n, m);
		// Fix target to be in [-m / 2, m / 2] \cap \doubleZ.
		target = MathUtil.modMinFixedInput(target, m);
		// Compute and save <code>n<sup>-1</sup> (mod m)</code> for later.
		final long n_inverse = MathUtil.modInverse(n, m);

		// Shanks' Babystep Giantstep Algorithm.
		final long bound = (long) Math.ceil(Math.sqrt(m)); // bound >= 2
		if (bound > Integer.MAX_VALUE) {
			throw new OutOfMemoryError();
		}
		final HashMap<Long, Long> table = new HashMap<Long, Long>((int) bound);
		for (long i = 0L, n_to_i = 1L; i != bound; ++i) {
			table.put(n_to_i, i);
			n_to_i = MathUtil.modMultFixedInput(n_to_i, n, m);
		}
		final long factor = MathUtil.modPowHelper(n_inverse, bound, m);
		Long j = null;
		for (long i = 0L, guess = target; i != bound; ++i) {
			if ((j = table.get(guess)) != null) {
				/**
				 * The following expression will never overflow since its maximum value is
				 * <code>(bound - 1) * bound + (bound - 1) == bound<sup>2</sup> - 1</code>. However, since we
				 * enforce <code>bound <= Integer.MAX_VALUE == 2<sup>31</sup> - 1</code> then we can conclude that
				 * <code>bound<sup>2</sup> - 1 <= (2<sup>62</sup> - 2<sup>32</sup> + 1) - 1 == 2<sup>62</sup> - 2<sup>32</sup>
				 * << 2<sup>63</sup> - 1 == Long.MAX_VALUE</code>.
				 */
				return ((i *= bound) + j);
			}
			guess = MathUtil.modMultFixedInput(guess, factor, m);
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
		final Long result = MathUtil.discreteLogBabyGiant((long) n, (long) target, (long) m);
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
		final Long result = MathUtil.discreteLogBabyGiant((long) n, (long) target, (long) m);
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
		final Long result = MathUtil.discreteLogBabyGiant((long) n, (long) target, (long) m);
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
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m <= 0</code>
	 */
	public static Long discreteLogLinearSearch(long n, long target, long m) throws InvalidModulusException {
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

		// Fix n to be in [-m / 2, m / 2] \cap \doubleZ.
		n = MathUtil.modMinFixedInput(n, m);
		// Fix target to be in [-m / 2, m / 2] \cap \doubleZ.
		target = MathUtil.modMinFixedInput(target, m);

		// Iteratively compute n to the power of i in mod m and compare the result to target.
		for (long i = 1L, n_to_i = n; i != m; ++i) {
			if (n_to_i == target) {
				return i;
			}
			n_to_i = MathUtil.modMultFixedInput(n_to_i, n, m);
		}
		// No power of n from [1, m - 1] \cap \doubleN resulted in target.
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
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m <= 0</code>
	 */
	public static Integer discreteLogLinearSearch(int n, int target, int m) throws InvalidModulusException {
		final Long result = MathUtil.discreteLogLinearSearch((long) n, (long) target, (long) m);
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
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m <= 0</code>
	 */
	public static Short discreteLogLinearSearch(short n, short target, short m) throws InvalidModulusException {
		final Long result = MathUtil.discreteLogLinearSearch((long) n, (long) target, (long) m);
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
	 * @return <code>p</code> such that <code>n<sup>p</sup> (mod m) == target</code> if such a
	 *         <code>p</code> exists and <code>null</code> otherwise.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m <= 0</code>
	 */
	public static Byte discreteLogLinearSearch(byte n, byte target, byte m) throws InvalidModulusException {
		final Long result = MathUtil.discreteLogLinearSearch((long) n, (long) target, (long) m);
		return ((result == null) ? null : result.byteValue());
	}

	/**
	 * This function defines <code>0<sup>0</sup> == 0</code> even though it is undefined in math. <br>
	 * Postcondition: <code>Result != null</code> <br>
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
	 * 
	 * @throws OutOfMemoryError
	 *             Thrown by <code>new long[m]</code>
	 */
	public static long[] powers(long n, int m) throws InvalidModulusException, OutOfMemoryError {
		// Fix n to be in [0, m - 1] \cap \doubleZ.
		n = MathUtil.mod(n, m);
		// m > 0

		// Create resulting long[] and handle the simple special cases.
		final long[] result = new long[m];
		if (n == 0L) {
			/**
			 * This case is needed since 0 to any non-zero power is 0 and so <code>result[0] = 1;</code> will be
			 * wrong in this case. Note that we are defining <code>0<sup>0</sup></code> here even though it is
			 * undefined in math.
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
			for (int i = 1; i < m; i += 2) {
				result[i] = n;
			}
			return result;
		}
		// (1 < n) && (n < m - 1)

		// Fill and return resulting long[].
		result[0] = 1L; // <code>n<sup>0</sup> (mod m) == 1</code>
		result[1] = n; // <code>n<sup>1</sup> (mod m) == n</code>
		long n_to_i = n = MathUtil.modMinFixedInput(n, m);
		for (int i = 2; i != m; ++i) {
			n_to_i = MathUtil.modMultFixedInput(n_to_i, n, m);
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
	 * 
	 * @throws OutOfMemoryError
	 *             Thrown by <code>new int[m]</code>
	 */
	public static int[] powers(int n, int m) throws InvalidModulusException, OutOfMemoryError {
		// Fix n to be in [0, m - 1] \cap \doubleZ.
		n = MathUtil.mod(n, m);
		// m > 0

		// Create resulting int[] and handle the simple special cases.
		final int[] result = new int[m];
		if (n == 0L) {
			/**
			 * This case is needed since 0 to any non-zero power is 0 and so <code>result[0] = 1;</code> will be
			 * wrong in this case. Note that we are defining <code>0<sup>0</sup></code> here even though it is
			 * undefined in math.
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
			for (int i = 1; i < m; i += 2) {
				result[i] = n;
			}
			return result;
		}
		// (1 < n) && (n < m - 1)

		// Fill and return resulting int[].
		result[0] = 1; // <code>n<sup>0</sup> (mod m) == 1</code>
		result[1] = n; // <code>n<sup>1</sup> (mod m) == n</code>
		int n_to_i = n = (int) MathUtil.modMinFixedInput(n, m);
		for (int i = 2; i != m; ++i) {
			n_to_i = (int) MathUtil.modMultFixedInput(n_to_i, n, m);
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
		// Fix n to be in [0, m - 1] \cap \doubleZ.
		n = MathUtil.mod(n, m);
		// m > 0

		// Create resulting short[] and handle the simple special cases.
		final short[] result = new short[m];
		if (n == 0L) {
			/**
			 * This case is needed since 0 to any non-zero power is 0 and so <code>result[0] = 1;</code> will be
			 * wrong in this case. Note that we are defining <code>0<sup>0</sup></code> here even though it is
			 * undefined in math.
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
			for (int i = 1; i < m; i += 2) {
				result[i] = n;
			}
			return result;
		}
		// (1 < n) && (n < m - 1)

		// Fill and return resulting short[].
		result[0] = 1; // <code>n<sup>0</sup> (mod m) == 1</code>
		result[1] = n; // <code>n<sup>1</sup> (mod m) == n</code>
		short n_to_i = n = (short) MathUtil.modMinFixedInput(n, m);
		for (int i = 2; i != m; ++i) {
			n_to_i = (short) MathUtil.modMultFixedInput(n_to_i, n, m);
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
		// Fix n to be in [0, m - 1] \cap \doubleZ.
		n = MathUtil.mod(n, m);
		// m > 0

		// Create resulting byte[] and handle the simple special cases.
		final byte[] result = new byte[m];
		if (n == 0L) {
			/**
			 * This case is needed since 0 to any non-zero power is 0 and so <code>result[0] = 1;</code> will be
			 * wrong in this case. Note that we are defining <code>0<sup>0</sup></code> here even though it is
			 * undefined in math.
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
			for (int i = 1; i < m; i += 2) {
				result[i] = n;
			}
			return result;
		}
		// (1 < n) && (n < m - 1)

		// Fill and return resulting short[].
		result[0] = 1; // <code>n<sup>0</sup> (mod m) == 1</code>
		result[1] = n; // <code>n<sup>1</sup> (mod m) == n</code>
		byte n_to_i = n = (byte) MathUtil.modMinFixedInput(n, m);
		for (int i = 2; i != m; ++i) {
			n_to_i = (byte) MathUtil.modMultFixedInput(n_to_i, n, m);
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
}
