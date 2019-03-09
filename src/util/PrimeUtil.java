package util;

import java.math.BigInteger;

/**
 * Utility prime constants and methods.
 * 
 * @author Ashkan Moatamed
 */
public class PrimeUtil {
	/**
	 * No dependencies.
	 */

	/**
	 * The first prime which is also the only prime that is 2 (mod 6).
	 */
	public static final byte FIRST_PRIME = 2;

	/**
	 * The second prime which is also the only prime that is 3 (mod 6).
	 */
	public static final byte SECOND_PRIME = 3;

	/**
	 * Largest prime less than or equal to Byte.MAX_VALUE which is Byte.MAX_VALUE itself.
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
	 * Largest prime less than or equal to Integer.MAX_VALUE which is Integer.MAX_VALUE itself.
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
	 * The first safe prime.
	 */
	public static final byte FIRST_SAFE_PRIME = 5;

	/**
	 * Largest safe prime less than or equal to Byte.MAX_VALUE.
	 */
	public static final byte LARGEST_SAFE_PRIME_BYTE = 107;

	/**
	 * Smallest safe prime greater than Byte.MAX_VALUE.
	 */
	public static final short SMALLEST_SAFE_PRIME_NOT_BYTE = 167;

	/**
	 * Largest safe prime less than or equal to Short.MAX_VALUE.
	 */
	public static final short LARGEST_SAFE_PRIME_SHORT = 32603;

	/**
	 * Smallest safe prime greater than Short.MAX_VALUE.
	 */
	public static final int SMALLEST_SAFE_PRIME_NOT_SHORT = 32843;

	/**
	 * Largest safe prime less than or equal to Integer.MAX_VALUE.
	 */
	public static final int LARGEST_SAFE_PRIME_INT = 2147483579;

	/**
	 * Smallest safe prime greater than Integer.MAX_VALUE.
	 */
	public static final long SMALLEST_SAFE_PRIME_NOT_INT = 2147483783L;

	/**
	 * Largest safe prime less than or equal to Long.MAX_VALUE.
	 */
	public static final long LARGEST_SAFE_PRIME_LONG = 9223372036854771239L;

	/**
	 * Smallest safe prime greater than Long.MAX_VALUE.
	 */
	public static final BigInteger SMALLEST_SAFE_PRIME_NOT_LONG = new BigInteger("9223372036854778487");

	/**
	 * Prevent instantiation.
	 */
	private PrimeUtil() {
		// Empty by design.
	}

	/**
	 * Runtime is in <code>O(sqrt(n))</code>. <br>
	 * Precondition: <code>n > 3</code> <br>
	 * Precondition: <code>(n % 2 != 0) && (n % 3 != 0)</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>true</code> if and only if the given number is a prime.
	 */
	protected static boolean isPrimeSqrtFixedInput(long n) {
		/**
		 * <pre>
		 * <code>
		 * Any integer i, is one of 0, 1, 2, 3, 4, or 5 (mod 6).
		 * However, if i is greater than 3, then it can only be a prime if it is 1 or 5 (mod 6) since otherwise
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
		// Applying Math.floor before casting to long is unnecessary and it causes a large slow down.
		final long bound = ((long) Math.sqrt(n)) + 1L; // bound >= 3
		final long maxI = bound + 1L; // maxI >= 4
		for (long i = 5L; i < maxI; i += 4L) {
			/**
			 * It's fine to do <code>i += 2</code> instead of <code>i + 2</code> since we don't need the value
			 * of <code>i</code> to remain unchanged but quite the opposite. We have actually separated the
			 * incrementation of <code>i</code> by <code>6</code>, into an incrementation by <code>2</code> and
			 * an incrementation by <code>4</code> due to the need to add <code>2</code> to <code>i</code> at
			 * this point and the fact that <code>+=</code> is faster than <code>+</code> due to it not creating
			 * a temporary. Note that the difference is the <code>+=</code> instead of the <code>+</code> which
			 * will mutate <code>i</code>.
			 */
			// Check if i (i.e., -1 (mod 6)) or i + 2 (i.e., 1 (mod 6)) is a factor of n.
			if ((n % i == 0L) || (n % (i += 2L) == 0L)) {
				return false;
			}
		}
		/**
		 * For all <code>n</code>, <code>n</code> can have at most one factor greater than
		 * <code>sqrt(n)</code>. <br>
		 * <br>
		 * 
		 * Therefore, due to the above loop, we know that <code>n</code> is a prime at this point.
		 */
		return true;
	}

	/**
	 * Runtime is in <code>O(sqrt(n))</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>true</code> if and only if the given number is a prime.
	 */
	public static boolean isPrimeSqrt(long n) {
		if (n < 2L) {
			// The first prime is 2.
			return false;
		} else if (n < 4L) { // i.e., (n == 2) || (n == 3)
			return true;
		} else if (((n & 1L) == 0L) || (n % 3L == 0L)) {
			/**
			 * Don't do <code>(n &= 1L) == 0L</code> since we need the value of <code>n</code> to remain
			 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
			 * will mutate <code>n</code>.
			 */
			// n is an integer greater than 3 and is divisible by 2 or 3 (or both).
			return false;
		}
		// n is an odd integer greater than 3 and not divisible by 3.
		return PrimeUtil.isPrimeSqrtFixedInput(n);
	}

	/**
	 * Runtime is in <code>O(sqrt(n))</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>true</code> if and only if the given number is a prime.
	 */
	public static boolean isPrimeSqrt(int n) {
		if (n < 2) {
			// The first prime is 2.
			return false;
		} else if (n < 4) { // i.e., (n == 2) || (n == 3)
			return true;
		} else if (((n & 1) == 0) || (n % 3 == 0)) {
			/**
			 * Don't do <code>(n &= 1) == 0</code> since we need the value of <code>n</code> to remain
			 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
			 * will mutate <code>n</code>.
			 */
			// n is an integer greater than 3 and is divisible by 2 or 3 (or both).
			return false;
		}
		// n is an odd integer greater than 3 and not divisible by 3.
		return PrimeUtil.isPrimeSqrtFixedInput(n);
	}

	/**
	 * Runtime is in <code>O(sqrt(n))</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>true</code> if and only if the given number is a prime.
	 */
	public static boolean isPrimeSqrt(short n) {
		if (n < 2) {
			// The first prime is 2.
			return false;
		} else if (n < 4) { // i.e., (n == 2) || (n == 3)
			return true;
		} else if (((n & 1) == 0) || (n % 3 == 0)) {
			/**
			 * Don't do <code>(n &= 1) == 0</code> since we need the value of <code>n</code> to remain
			 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
			 * will mutate <code>n</code>.
			 */
			// n is an integer greater than 3 and is divisible by 2 or 3 (or both).
			return false;
		}
		// n is an odd integer greater than 3 and not divisible by 3.
		return PrimeUtil.isPrimeSqrtFixedInput(n);
	}

	/**
	 * Runtime is in <code>O(sqrt(n))</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>true</code> if and only if the given number is a prime.
	 */
	public static boolean isPrimeSqrt(byte n) {
		if (n < 2) {
			// The first prime is 2.
			return false;
		} else if (n < 4) { // i.e., (n == 2) || (n == 3)
			return true;
		} else if (((n & 1) == 0) || (n % 3 == 0)) {
			/**
			 * Don't do <code>(n &= 1) == 0</code> since we need the value of <code>n</code> to remain
			 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
			 * will mutate <code>n</code>.
			 */
			// n is an integer greater than 3 and is divisible by 2 or 3 (or both).
			return false;
		}
		// n is an odd integer greater than 3 and not divisible by 3.
		return PrimeUtil.isPrimeSqrtFixedInput(n);
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return The first prime greater than <code>n</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n >= PrimeUtil.LARGEST_PRIME_LONG</code>
	 */
	public static long primeAfter(long n) throws IllegalArgumentException {
		if (n >= PrimeUtil.LARGEST_PRIME_LONG) {
			throw new IllegalArgumentException();
		}
		// n < PrimeUtil.LARGEST_PRIME_LONG

		if (n < 4L) { // i.e., (n < 2) || (n == 2) || (n == 3)
			return ((n < 2L) ? 2L : ((n == 2L) ? 3L : 5L));
		}
		// n >= 4

		/**
		 * <pre>
		 * <code>
		 * Any integer n, is one of 0, 1, 2, 3, 4, or 5 (mod 6).
		 * However, if n is greater than 3, then it can only be a prime if it is 1 or 5 (mod 6) since otherwise
		 * it would be divisible by 2 or 3 (or both in the case of 0 (mod 6)).
		 * </code>
		 * </pre>
		 * 
		 * Don't do <code>n %= 6L</code> since we need the value of <code>n</code> to remain unchanged. Note
		 * that the difference is the <code>%=</code> instead of the <code>%</code> which will mutate
		 * <code>n</code>.
		 */
		final long mod6 = n % 6L;
		if (mod6 == 0L) { // i.e., n == 6, 12, 18, 24, ...
			// Since n is 0 (mod 6), check if n + 1 is a prime separately.
			final long p = n + 1L;
			if (PrimeUtil.isPrimeSqrtFixedInput(p)) {
				return p;
			}
			// n >= 24

			// Update n to be the first integer which is 5 (mod 6) and greater than the original value of n.
			n += 5L; // n >= 29
		} else if (mod6 == 5L) { // i.e., n == 5, 11, 17, 23, ...
			// Since n is 5 (mod 6), check if n + 2 is a prime separately.
			final long p = n + 2L;
			if (PrimeUtil.isPrimeSqrtFixedInput(p)) {
				return p;
			}
			// n >= 23

			// Update n to be the first integer which is 5 (mod 6) and greater than the original value of n.
			n += 6L; // n >= 29
		} else { // i.e., (mod6 == 1) || (mod6 == 2) || (mod6 == 3) || (mod6 == 4)
			// Update n to be the first integer which is 5 (mod 6) and greater than the original value of n.
			n += 5L - mod6; // n >= 5
		}
		// (n % 6 == 5) && (n >= 5)
		for (/* Already initialized. */; true; n += 4L) {
			/**
			 * It's fine to do <code>n += 2</code> instead of <code>n + 2</code> since we don't need the value
			 * of <code>n</code> to remain unchanged but quite the opposite. We have actually separated the
			 * incrementation of <code>n</code> by <code>6</code>, into an incrementation by <code>2</code> and
			 * an incrementation by <code>4</code> due to the need to add <code>2</code> to <code>n</code> at
			 * this point and the fact that <code>+=</code> is faster than <code>+</code> due to it not creating
			 * a temporary. Note that the difference is the <code>+=</code> instead of the <code>+</code> which
			 * will mutate <code>n</code>.
			 */
			// Check if n (i.e., -1 (mod 6)) or n + 2 (i.e., 1 (mod 6)) is a prime.
			if (PrimeUtil.isPrimeSqrtFixedInput(n)) {
				break;
			} else if (PrimeUtil.isPrimeSqrtFixedInput(n += 2L)) {
				break;
			}
		}
		return n;
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return The first prime greater than <code>n</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n >= PrimeUtil.LARGEST_PRIME_INT</code>
	 */
	public static int primeAfter(int n) throws IllegalArgumentException {
		if (n >= PrimeUtil.LARGEST_PRIME_INT) {
			throw new IllegalArgumentException();
		}
		// n < PrimeUtil.LARGEST_PRIME_INT
		return ((int) PrimeUtil.primeAfter((long) n));
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return The first prime greater than <code>n</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n >= PrimeUtil.LARGEST_PRIME_SHORT</code>
	 */
	public static short primeAfter(short n) throws IllegalArgumentException {
		if (n >= PrimeUtil.LARGEST_PRIME_SHORT) {
			throw new IllegalArgumentException();
		}
		// n < PrimeUtil.LARGEST_PRIME_SHORT
		return ((short) PrimeUtil.primeAfter((long) n));
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return The first prime greater than <code>n</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n >= PrimeUtil.LARGEST_PRIME_BYTE</code>
	 */
	public static byte primeAfter(byte n) throws IllegalArgumentException {
		if (n >= PrimeUtil.LARGEST_PRIME_BYTE) {
			throw new IllegalArgumentException();
		}
		// n < PrimeUtil.LARGEST_PRIME_BYTE
		return ((byte) PrimeUtil.primeAfter((long) n));
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return The first prime smaller than <code>n</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n < 3</code>
	 */
	public static long primeBefore(long n) throws IllegalArgumentException {
		if (n < 4L) { // i.e., (n < 3) || (n == 3)
			if (n < 3L) {
				// The first prime is 2.
				throw new IllegalArgumentException();
			}
			// n >= 3
			// i.e., n == 3
			return 2L;
		}
		// n >= 4

		/**
		 * <pre>
		 * <code>
		 * Any integer n, is one of 0, 1, 2, 3, 4, or 5 (mod 6).
		 * However, if n is greater than 3, then it can only be a prime if it is 1 or 5 (mod 6) since otherwise
		 * it would be divisible by 2 or 3 (or both in the case of 0 (mod 6)).
		 * </code>
		 * </pre>
		 * 
		 * Don't do <code>n %= 6L</code> since we need the value of <code>n</code> to remain unchanged. Note
		 * that the difference is the <code>%=</code> instead of the <code>%</code> which will mutate
		 * <code>n</code>.
		 */
		final long mod6 = n % 6L;
		if (mod6 == 0L) { // i.e., n == 6, 12, 18, 24, 30, 36, ...
			// Since n is 0 (mod 6), check if n - 1 is a prime separately.
			final long p = n - 1L;
			if (PrimeUtil.isPrimeSqrtFixedInput(p)) {
				return p;
			}
			// n >= 36

			// Update n to be the first integer which is 1 (mod 6) and smaller than the original value of n.
			n -= 5L; // n >= 31
		} else if (mod6 == 1L) { // i.e., n == 7, 13, 19, 25, 31, 37, ...
			// Since n is 1 (mod 6), check if n - 2 is a prime separately.
			final long p = n - 2L;
			if (PrimeUtil.isPrimeSqrtFixedInput(p)) {
				return p;
			}
			// n >= 37

			// Update n to be the first integer which is 1 (mod 6) and smaller than the original value of n.
			n -= 6L; // n >= 31
		} else { // i.e., (mod6 == 2) || (mod6 == 3) || (mod6 == 4) || (mod6 == 5)
			if (n == mod6) { // i.e., (n == 4) || (n == 5)
				return 3L;
			}
			// n >= 8

			// Update n to be the first integer which is 1 (mod 6) and smaller than the original value of n.
			n += 1L - mod6; // n >= 7
		}
		// (n % 6 == 1) && (n >= 7)
		for (/* Already initialized. */; true; n -= 4L) {
			/**
			 * It's fine to do <code>n -= 2</code> instead of <code>n - 2</code> since we don't need the value
			 * of <code>n</code> to remain unchanged but quite the opposite. We have actually separated the
			 * decrementation of <code>n</code> by <code>6</code>, into a decrementation by <code>2</code> and a
			 * decrementation by <code>4</code> due to the need to subtract <code>2</code> from <code>n</code>
			 * at this point and the fact that <code>-=</code> is faster than <code>-</code> due to it not
			 * creating a temporary. Note that the difference is the <code>-=</code> instead of the
			 * <code>-</code> which will mutate <code>n</code>.
			 */
			// Check if n (i.e., 1 (mod 6)) or n - 2 (i.e., -1 (mod 6)) is a prime.
			if (PrimeUtil.isPrimeSqrtFixedInput(n)) {
				break;
			} else if (PrimeUtil.isPrimeSqrtFixedInput(n -= 2L)) {
				break;
			}
		}
		return n;
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return The first prime smaller than <code>n</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n < 3</code>
	 */
	public static int primeBefore(int n) throws IllegalArgumentException {
		return ((int) PrimeUtil.primeBefore((long) n));
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return The first prime smaller than <code>n</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n < 3</code>
	 */
	public static short primeBefore(short n) throws IllegalArgumentException {
		return ((short) PrimeUtil.primeBefore((long) n));
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return The first prime smaller than <code>n</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n < 3</code>
	 */
	public static byte primeBefore(byte n) throws IllegalArgumentException {
		return ((byte) PrimeUtil.primeBefore((long) n));
	}

	/**
	 * Runtime is in <code>O(sqrt(n))</code>. <br>
	 * Precondition: <code>n > 7</code> <br>
	 * Precondition: <code>n % 12 == 11</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>true</code> if and only if the given number is a safe prime.
	 */
	protected static boolean isSafePrimeSqrtFixedInput(long n) {
		// A safe prime is a prime that is one more than a multiple of 2.
		if (PrimeUtil.isPrimeSqrtFixedInput(n)) { // i.e., n is a prime greater than 7.
			final long N = (n - 1L) / 2L; // n >= 11 so N >= 5
			if (((N & 1L) == 0L) || (N % 3L == 0L)) {
				// N is an integer greater than 4 and is divisible by 2 or 3 (or both).
				return false;
			}
			return PrimeUtil.isPrimeSqrtFixedInput(N);
		}
		return false;
	}

	/**
	 * Runtime is in <code>O(sqrt(n))</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>true</code> if and only if the given number is a safe prime.
	 */
	public static boolean isSafePrimeSqrt(long n) {
		if (n < 5L) {
			// The first safe prime is 5.
			return false;
		} else if (n < 8L) { // i.e., (n == 5) || (n == 6) || (n == 7)
			return (n != 6L);
		} else if (n % 12L != 11L) {
			/**
			 * <pre>
			 * <code>
			 * Any integer n, is one of 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, or 11 (mod 12).
			 * However, if n is greater than 7, then it can only be a safe prime if it is 11 (mod 12) since otherwise
			 * it would be divisible by 2 or 3 (or both in the cases of 0, or 6 (mod 12))
			 * or (n - 1) / 2 would be divisible by 2 or 3 (or both in the case of 1 (mod 12)).
			 * </code>
			 * </pre>
			 */
			return false;
		}
		// n is an odd integer greater than 7 equivalent to 11 (mod 12).
		return PrimeUtil.isSafePrimeSqrtFixedInput(n);
	}

	/**
	 * Runtime is in <code>O(sqrt(n))</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>true</code> if and only if the given number is a safe prime.
	 */
	public static boolean isSafePrimeSqrt(int n) {
		if (n < 5) {
			// The first safe prime is 5.
			return false;
		} else if (n < 8) { // i.e., (n == 5) || (n == 6) || (n == 7)
			return (n != 6);
		} else if (n % 12 != 11) {
			/**
			 * <pre>
			 * <code>
			 * Any integer n, is one of 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, or 11 (mod 12).
			 * However, if n is greater than 7, then it can only be a safe prime if it is 11 (mod 12) since otherwise
			 * it would be divisible by 2 or 3 (or both in the cases of 0, or 6 (mod 12))
			 * or (n - 1) / 2 would be divisible by 2 or 3 (or both in the case of 1 (mod 12)).
			 * </code>
			 * </pre>
			 */
			return false;
		}
		// n is an odd integer greater than 7 equivalent to 11 (mod 12).
		return PrimeUtil.isSafePrimeSqrtFixedInput(n);
	}

	/**
	 * Runtime is in <code>O(sqrt(n))</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>true</code> if and only if the given number is a safe prime.
	 */
	public static boolean isSafePrimeSqrt(short n) {
		if (n < 5) {
			// The first safe prime is 5.
			return false;
		} else if (n < 8) { // i.e., (n == 5) || (n == 6) || (n == 7)
			return (n != 6);
		} else if (n % 12 != 11) {
			/**
			 * <pre>
			 * <code>
			 * Any integer n, is one of 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, or 11 (mod 12).
			 * However, if n is greater than 7, then it can only be a safe prime if it is 11 (mod 12) since otherwise
			 * it would be divisible by 2 or 3 (or both in the cases of 0, or 6 (mod 12))
			 * or (n - 1) / 2 would be divisible by 2 or 3 (or both in the case of 1 (mod 12)).
			 * </code>
			 * </pre>
			 */
			return false;
		}
		// n is an odd integer greater than 7 equivalent to 11 (mod 12).
		return PrimeUtil.isSafePrimeSqrtFixedInput(n);
	}

	/**
	 * Runtime is in <code>O(sqrt(n))</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>true</code> if and only if the given number is a safe prime.
	 */
	public static boolean isSafePrimeSqrt(byte n) {
		if (n < 5) {
			// The first safe prime is 5.
			return false;
		} else if (n < 8) { // i.e., (n == 5) || (n == 6) || (n == 7)
			return (n != 6);
		} else if (n % 12 != 11) {
			/**
			 * <pre>
			 * <code>
			 * Any integer n, is one of 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, or 11 (mod 12).
			 * However, if n is greater than 7, then it can only be a safe prime if it is 11 (mod 12) since otherwise
			 * it would be divisible by 2 or 3 (or both in the cases of 0, or 6 (mod 12))
			 * or (n - 1) / 2 would be divisible by 2 or 3 (or both in the case of 1 (mod 12)).
			 * </code>
			 * </pre>
			 */
			return false;
		}
		// n is an odd integer greater than 7 equivalent to 11 (mod 12).
		return PrimeUtil.isSafePrimeSqrtFixedInput(n);
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return The first safe prime greater than <code>n</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n >= PrimeUtil.LARGEST_SAFE_PRIME_LONG</code>
	 */
	public static long safePrimeAfter(long n) throws IllegalArgumentException {
		if (n >= PrimeUtil.LARGEST_SAFE_PRIME_LONG) {
			throw new IllegalArgumentException();
		}
		// n < PrimeUtil.LARGEST_SAFE_PRIME_LONG

		if (n < 8L) { // i.e., (n < 5) || (n == 5) || (n == 6) || (n == 7)
			return ((n < 5L) ? 5L : ((n < 7L) ? 7L : 11L));
		}
		// n >= 8

		/**
		 * <pre>
		 * <code>
		 * Any integer n, is one of 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, or 11 (mod 12).
		 * However, if n is greater than 7, then it can only be a safe prime if it is 11 (mod 12) since otherwise
		 * it would be divisible by 2 or 3 (or both in the cases of 0, or 6 (mod 12))
		 * or (n - 1) / 2 would be divisible by 2 or 3 (or both in the case of 1 (mod 12)).
		 * </code>
		 * </pre>
		 * 
		 * Don't do <code>n %= 12L</code> since we need the value of <code>n</code> to remain unchanged.
		 * Note that the difference is the <code>%=</code> instead of the <code>%</code> which will mutate
		 * <code>n</code>.
		 */
		final long mod12 = n % 12L;
		if (mod12 == 11L) { // i.e., n == 11, ...
			// Update n to be the first integer which is 11 (mod 12) and greater than the original value of n.
			n += 12L; // n >= 23
		} else { // i.e., (0 <= mod12) && (mod12 <= 10)
			// Update n to be the first integer which is 11 (mod 12) and greater than the original value of n.
			n += 11L - mod12; // n >= 23
		}
		// (n % 12 == 11) && (n >= 23)
		for (/* Already initialized. */; true; n += 12L) {
			// Check if n (i.e., 11 (mod 12)) is a safe prime.
			if (PrimeUtil.isSafePrimeSqrtFixedInput(n)) {
				break;
			}
		}
		return n;
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return The first safe prime greater than <code>n</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n >= PrimeUtil.LARGEST_SAFE_PRIME_INT</code>
	 */
	public static int safePrimeAfter(int n) throws IllegalArgumentException {
		if (n >= PrimeUtil.LARGEST_SAFE_PRIME_INT) {
			throw new IllegalArgumentException();
		}
		// n < PrimeUtil.LARGEST_SAFE_PRIME_INT
		return ((int) PrimeUtil.safePrimeAfter((long) n));
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return The first safe prime greater than <code>n</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n >= PrimeUtil.LARGEST_SAFE_PRIME_SHORT</code>
	 */
	public static short safePrimeAfter(short n) throws IllegalArgumentException {
		if (n >= PrimeUtil.LARGEST_SAFE_PRIME_SHORT) {
			throw new IllegalArgumentException();
		}
		// n < PrimeUtil.LARGEST_SAFE_PRIME_SHORT
		return ((short) PrimeUtil.safePrimeAfter((long) n));
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return The first safe prime greater than <code>n</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n >= PrimeUtil.LARGEST_SAFE_PRIME_BYTE</code>
	 */
	public static byte safePrimeAfter(byte n) throws IllegalArgumentException {
		if (n >= PrimeUtil.LARGEST_SAFE_PRIME_BYTE) {
			throw new IllegalArgumentException();
		}
		// n < PrimeUtil.LARGEST_SAFE_PRIME_BYTE
		return ((byte) PrimeUtil.safePrimeAfter((long) n));
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return The first safe prime smaller than <code>n</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n < 6</code>
	 */
	public static long safePrimeBefore(long n) throws IllegalArgumentException {
		if (n < 8L) { // i.e., (n < 6) || (n == 6) || (n == 7)
			if (n < 6L) {
				// The first safe prime is 5.
				throw new IllegalArgumentException();
			}
			// n >= 6
			// i.e., (n == 6) || (n == 7)
			return 5L;
		}
		// n >= 8

		/**
		 * <pre>
		 * <code>
		 * Any integer n, is one of 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, or 11 (mod 12).
		 * However, if n is greater than 7, then it can only be a safe prime if it is 11 (mod 12) since otherwise
		 * it would be divisible by 2 or 3 (or both in the cases of 0, or 6 (mod 12))
		 * or (n - 1) / 2 would be divisible by 2 or 3 (or both in the case of 1 (mod 12)).
		 * </code>
		 * </pre>
		 * 
		 * Don't do <code>n %= 12L</code> since we need the value of <code>n</code> to remain unchanged.
		 * Note that the difference is the <code>%=</code> instead of the <code>%</code> which will mutate
		 * <code>n</code>.
		 */
		final long mod12 = n % 12L;
		if (mod12 == 11L) { // i.e., n == 11, 23, ...
			if (n == 11L) {
				return 7L;
			}
			// n >= 23

			// Update n to be the first integer which is 11 (mod 12) and smaller than the original value of n.
			n -= 12L; // n >= 11
		} else { // i.e., (0 <= mod12) && (mod12 <= 10)
			if (n < 13L) { // i.e., ((8 <= n) && (n <= 10)) || (n == 12)
				return ((n == 12L) ? 11L : 7L);
			}
			// n >= 13

			// Update n to be the first integer which is 11 (mod 12) and smaller than the original value of n.
			n -= 1L + mod12; // n >= 11
		}
		// (n % 12 == 11) && (n >= 11)
		for (/* Already initialized. */; true; n -= 12L) {
			// Check if n (i.e., 11 (mod 12)) is a safe prime.
			if (PrimeUtil.isSafePrimeSqrtFixedInput(n)) {
				break;
			}
		}
		return n;
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return The first safe prime smaller than <code>n</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n < 6</code>
	 */
	public static int safePrimeBefore(int n) throws IllegalArgumentException {
		return ((int) PrimeUtil.safePrimeBefore((long) n));
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return The first safe prime smaller than <code>n</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n < 6</code>
	 */
	public static short safePrimeBefore(short n) throws IllegalArgumentException {
		return ((short) PrimeUtil.safePrimeBefore((long) n));
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return The first safe prime smaller than <code>n</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n < 6</code>
	 */
	public static byte safePrimeBefore(byte n) throws IllegalArgumentException {
		return ((byte) PrimeUtil.safePrimeBefore((long) n));
	}
}
