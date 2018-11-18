package util;

/**
 * Utility math methods in addition to Java's Math class.
 * 
 * @author Ashkan Moatamed
 */
public class MathUtil {
	/**
	 * No dependencies.
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
		return ((n & 1) == 0);
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
		return ((n & 1) == 0);
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
		return ((n & 1) == 0);
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
		return ((n & 1) == 0);
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
			if ((b == 0) || (b == Long.MIN_VALUE)) {
				// gcd(a, 0) == a == gcd(a, a) but a's absolute value is not representable as a long.
				throw new ArithmeticException();
			}
		} else if (b == Long.MIN_VALUE) { // a != Long.MIN_VALUE
			if (a == 0) {
				// gcd(0, b) == b but b's absolute value is not representable as a long.
				throw new ArithmeticException();
			}
		}

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

		// Loop until min == 0 since gcd(max, 0) == max.
		for (long remainder = 0; min != 0; /* Update inside. */) {
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
	 *             If <code>((a == Integer.MIN_VALUE) && ((b == 0) || (b == Integer.MIN_VALUE)))
	 *             || ((b == Integer.MIN_VALUE) && ((a == 0) || (a == Integer.MIN_VALUE)))</code>
	 */
	public static int gcd(int a, int b) throws ArithmeticException {
		// Handle the degenerate cases where the result cannot be represented as a non-negative int.
		if (a == Integer.MIN_VALUE) {
			if ((b == 0) || (b == Integer.MIN_VALUE)) {
				// gcd(a, 0) == a == gcd(a, a) but a's absolute value is not representable as an int.
				throw new ArithmeticException();
			}
		} else if (b == Integer.MIN_VALUE) { // a != Integer.MIN_VALUE
			if (a == 0) {
				// gcd(0, b) == b but b's absolute value is not representable as an int.
				throw new ArithmeticException();
			}
		}
		return ((int) MathUtil.gcd((long) a, (long) b));
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
				// gcd(a, 0) == a == gcd(a, a) but a's absolute value is not representable as a short.
				throw new ArithmeticException();
			}
		} else if (b == Short.MIN_VALUE) { // a != Short.MIN_VALUE
			if (a == 0) {
				// gcd(0, b) == b but b's absolute value is not representable as a short.
				throw new ArithmeticException();
			}
		}
		return ((short) MathUtil.gcd((long) a, (long) b));
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
				// gcd(a, 0) == a == gcd(a, a) but a's absolute value is not representable as a byte.
				throw new ArithmeticException();
			}
		} else if (b == Byte.MIN_VALUE) { // a != Byte.MIN_VALUE
			if (a == 0) {
				// gcd(0, b) == b but b's absolute value is not representable as a byte.
				throw new ArithmeticException();
			}
		}
		return ((byte) MathUtil.gcd((long) a, (long) b));
	}

	/**
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.length == 3</code> <br>
	 * Postcondition: <code>Result[0] == gcd(a, b)</code> <br>
	 * Postcondition: <code>Result[1] * max(a, b) + Result[2] * min(a, b) == gcd(a, b)</code>
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
		if ((a < 0) || (b < 0)) {
			throw new ArithmeticException();
		}

		// Handle the simple special cases to avoid creating two extra long[3].
		if (a == 0) {
			// 1 * max(0, b) + 0 * min(0, b) == gcd(0, b) == b
			return new long[] { b, 1, 0 };
		} else if (b == 0) { // a != 0
			// 1 * max(a, 0) + 0 * min(a, 0) == gcd(a, 0) == a
			return new long[] { a, 1, 0 };
		}

		// Assume a is smaller than b.
		long min = a, max = b;
		// Fix min and max if needed.
		if (max < min) {
			final long tmp = max;
			max = min;
			min = tmp;
		}

		final long[] remainders = { max, 1, 0 };
		final long[] result = { min, 0, 1 };
		final long[] tmp = new long[3];
		for (long quotient = 0, remainders_0 = max, result_0 = min; remainders_0
				- result_0 * (remainders_0 / result_0) > 0; remainders_0 = remainders[0], result_0 = result[0]) {
			// Save old values of result in tmp.
			System.arraycopy(result, 0, tmp, 0, 3);

			// Update result.
			quotient = remainders_0 / result_0;
			for (int i = 0; i != 3; ++i) {
				result[i] = (remainders[i] - result[i] * quotient);
			}

			// Copy old values of result saved in tmp, into remainders.
			System.arraycopy(tmp, 0, remainders, 0, 3);
		}
		return result;
	}

	/**
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.length == 3</code> <br>
	 * Postcondition: <code>Result[0] == gcd(a, b)</code> <br>
	 * Postcondition: <code>Result[1] * max(a, b) + Result[2] * min(a, b) == gcd(a, b)</code>
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
	 * Postcondition: <code>Result[0] == gcd(a, b)</code> <br>
	 * Postcondition: <code>Result[1] * max(a, b) + Result[2] * min(a, b) == gcd(a, b)</code>
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
	 * Postcondition: <code>Result[0] == gcd(a, b)</code> <br>
	 * Postcondition: <code>Result[1] * max(a, b) + Result[2] * min(a, b) == gcd(a, b)</code>
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
		// lcm(0, b) == 0 == lcm(a, 0)
		if ((a == 0) || (b == 0)) {
			return 0;
		}

		// lcm is non-negative so make a and b non-negative.
		a = Math.abs(a);
		b = Math.abs(b);

		// lcm(a, b) == (a * b) / gcd(a, b)
		return ((a / MathUtil.gcd(a, b)) * b);
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
	 *             If <code>(a == Integer.MIN_VALUE) && (b == Integer.MIN_VALUE)</code>
	 */
	public static int lcm(int a, int b) {
		// Handle the degenerate case where the result cannot be represented as a non-negative int.
		if ((a == Integer.MIN_VALUE) && (b == Integer.MIN_VALUE)) {
			throw new ArithmeticException();
		}
		return ((int) MathUtil.lcm((long) a, (long) b));
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
	 *             If <code>(a == Short.MIN_VALUE) && (b == Short.MIN_VALUE)</code>
	 */
	public static short lcm(short a, short b) {
		// Handle the degenerate case where the result cannot be represented as a non-negative short.
		if ((a == Short.MIN_VALUE) && (b == Short.MIN_VALUE)) {
			throw new ArithmeticException();
		}
		return ((short) MathUtil.lcm((long) a, (long) b));
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
	 *             If <code>(a == Byte.MIN_VALUE) && (b == Byte.MIN_VALUE)</code>
	 */
	public static byte lcm(byte a, byte b) {
		// Handle the degenerate case where the result cannot be represented as a non-negative byte.
		if ((a == Byte.MIN_VALUE) && (b == Byte.MIN_VALUE)) {
			throw new ArithmeticException();
		}
		return ((byte) MathUtil.lcm((long) a, (long) b));
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
	 * @throws ArithmeticException
	 *             If <code>m <= 0</code>
	 */
	public static long mod(long n, long m) throws ArithmeticException {
		if (m <= 0) {
			throw new ArithmeticException();
		}

		// Compute the remainder upon integer division.
		final long remainder = n % m;
		// Fix the remainder to be non-negative.
		return ((remainder < 0) ? (remainder + m) : remainder);
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
	 * @throws ArithmeticException
	 *             If <code>m <= 0</code>
	 */
	public static int mod(int n, int m) throws ArithmeticException {
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
	 * @throws ArithmeticException
	 *             If <code>m <= 0</code>
	 */
	public static short mod(short n, short m) throws ArithmeticException {
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
	 * @throws ArithmeticException
	 *             If <code>m <= 0</code>
	 */
	public static byte mod(byte n, byte m) throws ArithmeticException {
		return ((byte) MathUtil.mod((long) n, (long) m));
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
	 * @throws ArithmeticException
	 *             If <code>(m <= 0) || (gcd(n, m) != 1)</code>
	 */
	public static long modInverse(long n, long m) throws ArithmeticException {
		// Handle the simple special case.
		if (m == 1) {
			return 0;
		}

		/*
		 * Do not fix n to be in [0, m - 1] \cap \doubleN since it actually slows down the entire function
		 * instead of speeding it up (i.e., no need for <code>n = MathUtil.mod(n, m);</code>).
		 */

		/*
		 * No need to check gcd(n, m) != 1 since if that is the case, then in the body of the following
		 * loop, remainder will become 0 in some iteration and as a result when calculating the quotient
		 * (i.e., dividing n by remainder) an ArithmeticException will automatically be thrown.
		 */
		try {
			// Note that try blocks do not slow down the code unless an exception is thrown.

			// Loop until n == 0 or n == 1.
			long x = 1;
			for (long y = 0, quotient = 0, remainder = m, tmp = m; n > 1; /* Update inside. */) {
				// Compute the quotient.
				quotient = n / remainder;

				// Update remainder and n.
				tmp = remainder;
				remainder = n % remainder;
				n = tmp;

				// Update x and y.
				tmp = y;
				y = x - quotient * y;
				x = tmp;
			}
			return ((x < 0) ? (x + m) : x);
		} catch (ArithmeticException ex) {
			/*
			 * The cause of this exception is a division by 0 as explained in the comments above the try and it
			 * is only because gcd(n, m) != 1.
			 */

			// Throw a new ArithmeticException since the caught one (i.e., ex) has message "/ by zero".
			throw new ArithmeticException();
		}
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
	 * @throws ArithmeticException
	 *             If <code>(m <= 0) || (gcd(n, m) != 1)</code>
	 */
	public static int modInverse(int n, int m) throws ArithmeticException {
		return ((int) MathUtil.modInverse((long) n, (long) m));
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
	 * @throws ArithmeticException
	 *             If <code>(m <= 0) || (gcd(n, m) != 1)</code>
	 */
	public static short modInverse(short n, short m) throws ArithmeticException {
		return ((short) MathUtil.modInverse((long) n, (long) m));
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
	 * @throws ArithmeticException
	 *             If <code>(m <= 0) || (gcd(n, m) != 1)</code>
	 */
	public static byte modInverse(byte n, byte m) throws ArithmeticException {
		return ((byte) MathUtil.modInverse((long) n, (long) m));
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
	 * @throws ArithmeticException
	 *             If <code>(m <= 0) || (p == Long.MIN_VALUE) || ((p < 0) && (gcd(n, m) != 1))</code>
	 */
	public static long modPow(long n, long p, long m) throws ArithmeticException {
		// Handle the degenerate case where p's absolute value is not representable as a long.
		if (p == Long.MIN_VALUE) {
			throw new ArithmeticException();
		}
		return ((p < 0) ? MathUtil.modPow(MathUtil.modInverse(n, m), -p, m)
				: ((p == 0) ? 1 : MathUtil.modPowRecur(MathUtil.mod(n, m), p, m)));
	}

	/**
	 * Compute <code>n<sup>p</sup> (mod m)</code> using the recursive fast power algorithm. <br>
	 * Precondition: <code>m >= 0</code> <br>
	 * Precondition: <code>n == MathUtil.mod(n, m)</code> <br>
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
	 */
	protected static long modPowRecur(long n, long p, long m) {
		// Base case.
		if (p == 0) {
			return 1;
		}

		// General recursive case.
		long tmp = MathUtil.modPowRecur(n, p / 2, m) % m;
		tmp = (tmp * tmp) % m;
		return (MathUtil.isEven(p) ? tmp : ((n * tmp) % m));
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
	 * @throws ArithmeticException
	 *             If <code>(m <= 0) || (p == Integer.MIN_VALUE) || ((p < 0) && (gcd(n, m) != 1))</code>
	 */
	public static int modPow(int n, int p, int m) throws ArithmeticException {
		// Handle the degenerate case where p's absolute value is not representable as an int.
		if (p == Integer.MIN_VALUE) {
			throw new ArithmeticException();
		}
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
	 * @throws ArithmeticException
	 *             If <code>(m <= 0) || (p == Short.MIN_VALUE) || ((p < 0) && (gcd(n, m) != 1))</code>
	 */
	public static short modPow(short n, short p, short m) throws ArithmeticException {
		// Handle the degenerate case where p's absolute value is not representable as a short.
		if (p == Short.MIN_VALUE) {
			throw new ArithmeticException();
		}
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
	 * @throws ArithmeticException
	 *             If <code>(m <= 0) || (p == Byte.MIN_VALUE) || ((p < 0) && (gcd(n, m) != 1))</code>
	 */
	public static byte modPow(byte n, byte p, byte m) throws ArithmeticException {
		// Handle the degenerate case where p's absolute value is not representable as a byte.
		if (p == Byte.MIN_VALUE) {
			throw new ArithmeticException();
		}
		return ((byte) MathUtil.modPow((long) n, (long) p, (long) m));
	}
}
