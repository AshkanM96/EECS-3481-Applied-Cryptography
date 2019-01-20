package util;

import java.util.Arrays;

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
			return new long[] { b, 0L, 1L };
		} else if (b == 0L) { // a != 0
			// 1 * a + 0 * b == a == gcd(a, 0)
			return new long[] { a, 1L, 0L };
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

		// Fix n to be in [0, m - 1] \cap \doubleN.
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
		 * following loop, remainder will become 0 in some iteration and as a result when calculating the
		 * quotient (i.e., dividing n by remainder) an ArithmeticException will automatically be thrown.
		 */
		try {
			// Note that try blocks do not slow down the code unless an exception is thrown.

			// Loop until (n == 0L) or (n == 1L).
			long x = 1L;
			for (long y = 0L, quotient = 0L, remainder = m, tmp = 0L; n > 1L; /* Update inside. */) {
				// Update quotient, remainder, and n.
				tmp = remainder;
				remainder = n - (quotient = (n / tmp)) * tmp;
				n = tmp;

				// Update x and y.
				tmp = y;
				y = x - quotient * y;
				x = tmp;
			}
			return ((x < 0L) ? (x += m) : x);
		} catch (ArithmeticException ex) {
			/**
			 * The cause of this exception is a division by 0 and is due to <code>gcd(n, m) != 1</code> as
			 * explained in the comments above the try.
			 */
			throw new UndefinedInverseException();
		}
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
	 * Compute <code>n<sup>p</sup> (mod m)</code> using the fast power (i.e., successive squaring)
	 * algorithm. <br>
	 * Precondition: <code>m > 1</code> <br>
	 * Precondition: <code>(n == MathUtil.mod(n, m)) && (n > 1)</code> <br>
	 * Precondition: <code>p >= 0</code>
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
	 * @throws ArithmeticException
	 *             If <code>any of the multiplications overflows a long</code>
	 */
	protected static long modPowFixedInput(long n, long p, long m) throws ArithmeticException {
		long result = 1L;
		for (long n_to_2_to_i = n; p != 0L; p /= 2L) {
			if (MathUtil.isEven(p)) {
				result = Math.multiplyExact(result, n_to_2_to_i) % m;
			}
			n_to_2_to_i = Math.multiplyExact(n_to_2_to_i, n_to_2_to_i) % m; // Square n_to_2_to_i (mod m).
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
	 *             If <code>((p == 0) && (n (mod m) == 0))
	 *             || (any of the multiplications overflows a long)</code>
	 */
	public static long modPow(long n, long p, long m)
			throws InvalidModulusException, UndefinedInverseException, ArithmeticException {
		// Handle the simple special case.
		if (m == 1L) {
			return 0L;
		}

		// Fix n to be in [0, m - 1] \cap \doubleN.
		n = MathUtil.mod(n, m);
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
			// p > 0L
			return 0L;
		} else if (n == 1L) {
			return 1L;
		}
		// (2 <= n) && (n <= m - 1)

		// Handle the degenerate case where p's absolute value is not representable as a non-negative long.
		if (p == Long.MIN_VALUE) {
			/**
			 * <code>n<sup>(-2<sup>63</sup>)</sup> (mod m) = (n<sup>-1</sup>)<sup>(2<sup>63</sup> - 1)</sup> * n<sup>-1</sup> (mod m)</code>
			 */
			final long n_inverse = MathUtil.modInverse(n, m);
			long result = MathUtil.modPowFixedInput(n_inverse, Long.MAX_VALUE, m);
			return (Math.multiplyExact(result, n_inverse) % m);
		}

		/**
		 * <code>n<sup>p</sup> (mod m)</code> is: <br>
		 * <code>(n<sup>-1</sup> (mod m))<sup>abs(p)</sup> (mod m)</code> if <code>p < 0</code> <br>
		 * <code>n<sup>abs(p)</sup> (mod m)</code> if <code>p >= 0</code>
		 */
		return ((p < 0L) ? MathUtil.modPowFixedInput(MathUtil.modInverse(n, m), -p, m)
				: MathUtil.modPowFixedInput(n, p, m));
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
	 * @throws ArithmeticException
	 *             If <code>any of the multiplications overflows a long</code>
	 */
	public static Long discreteLog(long n, long target, long m) throws InvalidModulusException, ArithmeticException {
		// Fix n to be in [0, m - 1] \cap \doubleN.
		n = MathUtil.mod(n, m);
		// Fix target to be in [0, m - 1] \cap \doubleN.
		target = MathUtil.mod(target, m);

		// Handle the simple special case.
		if (target == 1L) {
			// n to the power of 0 is 1 except when n is 0.
			return ((n == 0L) ? null : 0L);
		}

		// Iteratively compute n to the power of i and compare the result to target.
		for (long i = 1L, n_to_i = n; i != m; ++i) {
			if (n_to_i == target) {
				return i;
			}
			n_to_i = Math.multiplyExact(n_to_i, n) % m;
		}
		// No power of n from [1, m - 1] resulted in target.
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
	public static Integer discreteLog(int n, int target, int m) throws InvalidModulusException {
		final Long result = MathUtil.discreteLog((long) n, (long) target, (long) m);
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
	public static Short discreteLog(short n, short target, short m) throws InvalidModulusException {
		final Long result = MathUtil.discreteLog((long) n, (long) target, (long) m);
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
	public static Byte discreteLog(byte n, byte target, byte m) throws InvalidModulusException {
		final Long result = MathUtil.discreteLog((long) n, (long) target, (long) m);
		return ((result == null) ? null : result.byteValue());
	}

	/**
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
	 * 
	 * @throws ArithmeticException
	 *             If <code>any of the multiplications overflows a long</code>
	 */
	public static long[] powers(long n, int m) throws InvalidModulusException, OutOfMemoryError, ArithmeticException {
		// Fix n to be in [0, m - 1] \cap \doubleN.
		n = MathUtil.mod(n, m);

		// Create resulting long[] and handle the simple special case.
		final long[] result = new long[m];
		if (n == 0L) {
			/**
			 * This case is needed since 0 to any non-zero power is 0 and so <code>result[0] = 1;</code> will be
			 * wrong in this case. Note that we are defining 0 to the power of 0 here even though it is
			 * undefined in math.
			 */
			return result;
		} else if (n == 1L) {
			/*
			 * This case is only an optimization since 1 to any power is 1 and so the loop will do extra
			 * unnecessary work to arrive at the same result.
			 */
			Arrays.fill(result, 1L);
			return result;
		}
		// (2 <= n) && (n <= m - 1)

		// Fill and return resulting long[].
		result[0] = 1L; // <code>n<sup>0</sup> (mod m) = 1</code>
		long n_to_i = 1L;
		for (int i = 1; i != m; ++i) {
			result[i] = n_to_i = Math.multiplyExact(n_to_i, n) % m;
		}
		return result;
	}

	/**
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
	 * 
	 * @throws ArithmeticException
	 *             If <code>any of the multiplications overflows an int</code>
	 */
	public static int[] powers(int n, int m) throws InvalidModulusException, OutOfMemoryError, ArithmeticException {
		// Fix n to be in [0, m - 1] \cap \doubleN.
		n = MathUtil.mod(n, m);

		// Create resulting int[] and handle the simple special case.
		final int[] result = new int[m];
		if (n == 0) {
			/**
			 * This case is needed since 0 to any non-zero power is 0 and so <code>result[0] = 1;</code> will be
			 * wrong in this case. Note that we are defining 0 to the power of 0 here even though it is
			 * undefined in math.
			 */
			return result;
		} else if (n == 1) {
			/*
			 * This case is only an optimization since 1 to any power is 1 and so the loop will do extra
			 * unnecessary work to arrive at the same result.
			 */
			Arrays.fill(result, 1);
			return result;
		}
		// (2 <= n) && (n <= m - 1)

		// Fill and return resulting int[].
		result[0] = 1; // <code>n<sup>0</sup> (mod m) = 1</code>
		int n_to_i = 1;
		for (int i = 1; i != m; ++i) {
			result[i] = n_to_i = Math.multiplyExact(n_to_i, n) % m;
		}
		return result;
	}

	/**
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
	 * 
	 * @throws ArithmeticException
	 *             If <code>any of the multiplications overflows a short</code>
	 */
	@SuppressWarnings("cast")
	public static short[] powers(short n, short m) throws InvalidModulusException, ArithmeticException {
		// Fix n to be in [0, m - 1] \cap \doubleN.
		n = MathUtil.mod(n, m);

		// Create resulting short[] and handle the simple special case.
		final short[] result = new short[m];
		if (n == 0) {
			/**
			 * This case is needed since 0 to any non-zero power is 0 and so <code>result[0] = 1;</code> will be
			 * wrong in this case. Note that we are defining 0 to the power of 0 here even though it is
			 * undefined in math.
			 */
			return result;
		} else if (n == 1) {
			/*
			 * This case is only an optimization since 1 to any power is 1 and so the loop will do extra
			 * unnecessary work to arrive at the same result.
			 */
			Arrays.fill(result, (short) 1);
			return result;
		}
		// (2 <= n) && (n <= m - 1)

		// Fill and return resulting short[].
		result[0] = 1; // <code>n<sup>0</sup> (mod m) = 1</code>
		short n_to_i = 1;
		for (int i = 1, tmp = 0; i != m; ++i) {
			tmp = ((int) n_to_i) * ((int) n);
			if ((result[i] = n_to_i = (short) tmp) != tmp) {
				throw new ArithmeticException();
			}
		}
		return result;
	}

	/**
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
	 * 
	 * @throws ArithmeticException
	 *             If <code>any of the multiplications overflows a byte</code>
	 */
	@SuppressWarnings("cast")
	public static byte[] powers(byte n, byte m) throws InvalidModulusException, ArithmeticException {
		// Fix n to be in [0, m - 1] \cap \doubleN.
		n = MathUtil.mod(n, m);

		// Create resulting byte[] and handle the simple special case.
		final byte[] result = new byte[m];
		if (n == 0) {
			/**
			 * This case is needed since 0 to any non-zero power is 0 and so <code>result[0] = 1;</code> will be
			 * wrong in this case. Note that we are defining 0 to the power of 0 here even though it is
			 * undefined in math.
			 */
			return result;
		} else if (n == 1) {
			/*
			 * This case is only an optimization since 1 to any power is 1 and so the loop will do extra
			 * unnecessary work to arrive at the same result.
			 */
			Arrays.fill(result, (byte) 1);
			return result;
		}
		// (2 <= n) && (n <= m - 1)

		// Fill and return resulting short[].
		result[0] = 1; // <code>n<sup>0</sup> (mod m) = 1</code>
		byte n_to_i = 1;
		for (int i = 1, tmp = 0; i != m; ++i) {
			tmp = ((int) n_to_i) * ((int) n);
			if ((result[i] = n_to_i = (byte) tmp) != tmp) {
				throw new ArithmeticException();
			}
		}
		return result;
	}
}
