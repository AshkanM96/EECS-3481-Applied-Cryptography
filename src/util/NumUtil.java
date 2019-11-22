package util;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Utility math methods (and constants) in addition to MathUtil but mainly focused on number theory.
 * 
 * @author Ashkan Moatamed
 */
public class NumUtil {
	/**
	 * Dependencies: <code>
	 * 		1. util.MathUtil
	 * 		2. util.InvalidModulusException
	 * </code>
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
	 * The largest prime less than or equal to Byte.MAX_VALUE which is Byte.MAX_VALUE itself.
	 */
	public static final byte LARGEST_PRIME_BYTE = 127;

	/**
	 * The smallest prime greater than Byte.MAX_VALUE.
	 */
	public static final short SMALLEST_PRIME_NOT_BYTE = 131;

	/**
	 * The largest prime less than or equal to Short.MAX_VALUE.
	 */
	public static final short LARGEST_PRIME_SHORT = 32749;

	/**
	 * The smallest prime greater than Short.MAX_VALUE.
	 */
	public static final int SMALLEST_PRIME_NOT_SHORT = 32771;

	/**
	 * The largest prime less than or equal to Integer.MAX_VALUE which is Integer.MAX_VALUE itself.
	 */
	public static final int LARGEST_PRIME_INT = 2147483647;

	/**
	 * The smallest prime greater than Integer.MAX_VALUE.
	 */
	public static final long SMALLEST_PRIME_NOT_INT = 2147483659L;

	/**
	 * The largest prime less than or equal to Long.MAX_VALUE.
	 */
	public static final long LARGEST_PRIME_LONG = 9223372036854775783L;

	/**
	 * The smallest prime greater than Long.MAX_VALUE.
	 */
	public static final BigInteger SMALLEST_PRIME_NOT_LONG = new BigInteger("9223372036854775837");

	/**
	 * The first safe prime which is also the only safe prime that is 5 (mod 12).
	 */
	public static final byte FIRST_SAFE_PRIME = 5;

	/**
	 * The second safe prime which is also the only safe prime that is 7 (mod 12).
	 */
	public static final byte SECOND_SAFE_PRIME = 7;

	/**
	 * The largest safe prime less than or equal to Byte.MAX_VALUE.
	 */
	public static final byte LARGEST_SAFE_PRIME_BYTE = 107;

	/**
	 * The smallest safe prime greater than Byte.MAX_VALUE.
	 */
	public static final short SMALLEST_SAFE_PRIME_NOT_BYTE = 167;

	/**
	 * The largest safe prime less than or equal to Short.MAX_VALUE.
	 */
	public static final short LARGEST_SAFE_PRIME_SHORT = 32603;

	/**
	 * The smallest safe prime greater than Short.MAX_VALUE.
	 */
	public static final int SMALLEST_SAFE_PRIME_NOT_SHORT = 32843;

	/**
	 * The largest safe prime less than or equal to Integer.MAX_VALUE.
	 */
	public static final int LARGEST_SAFE_PRIME_INT = 2147483579;

	/**
	 * The smallest safe prime greater than Integer.MAX_VALUE.
	 */
	public static final long SMALLEST_SAFE_PRIME_NOT_INT = 2147483783L;

	/**
	 * The largest safe prime less than or equal to Long.MAX_VALUE.
	 */
	public static final long LARGEST_SAFE_PRIME_LONG = 9223372036854771239L;

	/**
	 * The smallest safe prime greater than Long.MAX_VALUE.
	 */
	public static final BigInteger SMALLEST_SAFE_PRIME_NOT_LONG = new BigInteger("9223372036854778487");

	/**
	 * The default value for the <code>base</code> argument of the <code>divisorPMinusOne</code> method.
	 */
	public static final byte P_MINUS_ONE_DEFAULT_BASE = 2;

	/**
	 * The default value for the <code>end</code> argument of the <code>divisorPMinusOne</code> method.
	 */
	public static final byte P_MINUS_ONE_DEFAULT_END = 100;

	/**
	 * Prevent instantiation.
	 */
	private NumUtil() {
		// Empty by design.
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
	 * Runtime is in <code>O(sqrt(n))</code>. <br>
	 * Precondition: <code>3 < n</code> <br>
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
		final long bound = ((long) Math.sqrt(n)) + 1L; // 3 <= bound
		final long maxI = bound + 1L; // 4 <= maxI
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
		if (n < 4L) {
			// The only primes less than 4 are 2 and 3.
			return ((n == 2L) || (n == 3L));
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
		return NumUtil.isPrimeSqrtFixedInput(n);
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
		if (n < 4) {
			// The only primes less than 4 are 2 and 3.
			return ((n == 2) || (n == 3));
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
		return NumUtil.isPrimeSqrtFixedInput(n);
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
		if (n < 4) {
			// The only primes less than 4 are 2 and 3.
			return ((n == 2) || (n == 3));
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
		return NumUtil.isPrimeSqrtFixedInput(n);
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
		if (n < 4) {
			// The only primes less than 4 are 2 and 3.
			return ((n == 2) || (n == 3));
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
		return NumUtil.isPrimeSqrtFixedInput(n);
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return The first prime greater than <code>n</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>NumUtil.LARGEST_PRIME_LONG <= n</code>
	 */
	public static long primeAfter(long n) throws IllegalArgumentException {
		if (NumUtil.LARGEST_PRIME_LONG <= n) {
			throw new IllegalArgumentException();
		}
		// n < NumUtil.LARGEST_PRIME_LONG

		if (n < 4L) { // i.e., (n < 2) || (n == 2) || (n == 3)
			return ((n < 2L) ? 2L : ((n == 2L) ? 3L : 5L));
		}
		// 4 <= n

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
			if (NumUtil.isPrimeSqrtFixedInput(p)) {
				return p;
			}
			// 24 <= n

			// Update n to be the first integer which is 5 (mod 6) and greater than the original value of n.
			n += 5L; // 29 <= n
		} else if (mod6 == 5L) { // i.e., n == 5, 11, 17, 23, ...
			// Since n is 5 (mod 6), check if n + 2 is a prime separately.
			final long p = n + 2L;
			if (NumUtil.isPrimeSqrtFixedInput(p)) {
				return p;
			}
			// 23 <= n

			// Update n to be the first integer which is 5 (mod 6) and greater than the original value of n.
			n += 6L; // 29 <= n
		} else { // i.e., (mod6 == 1) || (mod6 == 2) || (mod6 == 3) || (mod6 == 4)
			// Update n to be the first integer which is 5 (mod 6) and greater than the original value of n.
			n += 5L - mod6; // 5 <= n
		}
		// (n % 6 == 5) && (5 <= n)
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
			if (NumUtil.isPrimeSqrtFixedInput(n)) {
				break;
			} else if (NumUtil.isPrimeSqrtFixedInput(n += 2L)) {
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
	 *             If <code>NumUtil.LARGEST_PRIME_INT <= n</code>
	 */
	public static int primeAfter(int n) throws IllegalArgumentException {
		if (NumUtil.LARGEST_PRIME_INT <= n) {
			throw new IllegalArgumentException();
		}
		// n < NumUtil.LARGEST_PRIME_INT
		return ((int) NumUtil.primeAfter((long) n));
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return The first prime greater than <code>n</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>NumUtil.LARGEST_PRIME_SHORT <= n</code>
	 */
	public static short primeAfter(short n) throws IllegalArgumentException {
		if (NumUtil.LARGEST_PRIME_SHORT <= n) {
			throw new IllegalArgumentException();
		}
		// n < NumUtil.LARGEST_PRIME_SHORT
		return ((short) NumUtil.primeAfter((long) n));
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return The first prime greater than <code>n</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>NumUtil.LARGEST_PRIME_BYTE <= n</code>
	 */
	public static byte primeAfter(byte n) throws IllegalArgumentException {
		if (NumUtil.LARGEST_PRIME_BYTE <= n) {
			throw new IllegalArgumentException();
		}
		// n < NumUtil.LARGEST_PRIME_BYTE
		return ((byte) NumUtil.primeAfter((long) n));
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
			// 3 <= n
			// i.e., n == 3
			return 2L;
		}
		// 4 <= n

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
			if (NumUtil.isPrimeSqrtFixedInput(p)) {
				return p;
			}
			// 36 <= n

			// Update n to be the first integer which is 1 (mod 6) and smaller than the original value of n.
			n -= 5L; // 31 <= n
		} else if (mod6 == 1L) { // i.e., n == 7, 13, 19, 25, 31, 37, ...
			// Since n is 1 (mod 6), check if n - 2 is a prime separately.
			final long p = n - 2L;
			if (NumUtil.isPrimeSqrtFixedInput(p)) {
				return p;
			}
			// 37 <= n

			// Update n to be the first integer which is 1 (mod 6) and smaller than the original value of n.
			n -= 6L; // 31 <= n
		} else { // i.e., (mod6 == 2) || (mod6 == 3) || (mod6 == 4) || (mod6 == 5)
			if (n == mod6) { // i.e., (n == 4) || (n == 5)
				return 3L;
			}
			// 8 <= n

			// Update n to be the first integer which is 1 (mod 6) and smaller than the original value of n.
			n += 1L - mod6; // 7 <= n
		}
		// (n % 6 == 1) && (7 <= n)
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
			if (NumUtil.isPrimeSqrtFixedInput(n)) {
				break;
			} else if (NumUtil.isPrimeSqrtFixedInput(n -= 2L)) {
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
		return ((int) NumUtil.primeBefore((long) n));
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
		return ((short) NumUtil.primeBefore((long) n));
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
		return ((byte) NumUtil.primeBefore((long) n));
	}

	/**
	 * Runtime is in <code>O(sqrt(n))</code>. <br>
	 * Precondition: <code>7 < n</code> <br>
	 * Precondition: <code>n % 12 == 11</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>true</code> if and only if the given number is a safe prime.
	 */
	protected static boolean isSafePrimeSqrtFixedInput(long n) {
		// A safe prime is a prime that is one more than a multiple of 2.
		if (NumUtil.isPrimeSqrtFixedInput(n)) { // i.e., n is a prime greater than 7.
			final long N = (n - 1L) / 2L; // 11 <= n so 5 <= N
			if (((N & 1L) == 0L) || (N % 3L == 0L)) {
				// N is an integer greater than 4 and is divisible by 2 or 3 (or both).
				return false;
			}
			return NumUtil.isPrimeSqrtFixedInput(N);
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
		if (n < 8L) {
			// The only safe primes less than 8 are 5 and 7.
			return ((n == 5L) || (n == 7L));
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
		return NumUtil.isSafePrimeSqrtFixedInput(n);
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
		if (n < 8) {
			// The only safe primes less than 8 are 5 and 7.
			return ((n == 5) || (n == 7));
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
		return NumUtil.isSafePrimeSqrtFixedInput(n);
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
		if (n < 8) {
			// The only safe primes less than 8 are 5 and 7.
			return ((n == 5) || (n == 7));
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
		return NumUtil.isSafePrimeSqrtFixedInput(n);
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
		if (n < 8) {
			// The only safe primes less than 8 are 5 and 7.
			return ((n == 5) || (n == 7));
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
		return NumUtil.isSafePrimeSqrtFixedInput(n);
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return The first safe prime greater than <code>n</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>NumUtil.LARGEST_SAFE_PRIME_LONG <= n</code>
	 */
	public static long safePrimeAfter(long n) throws IllegalArgumentException {
		if (NumUtil.LARGEST_SAFE_PRIME_LONG <= n) {
			throw new IllegalArgumentException();
		}
		// n < NumUtil.LARGEST_SAFE_PRIME_LONG

		if (n < 8L) { // i.e., (n < 5) || (n == 5) || (n == 6) || (n == 7)
			return ((n < 5L) ? 5L : ((n < 7L) ? 7L : 11L));
		}
		// 8 <= n

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
			n += 12L; // 23 <= n
		} else { // i.e., (0 <= mod12) && (mod12 <= 10)
			// Update n to be the first integer which is 11 (mod 12) and greater than the original value of n.
			n += 11L - mod12; // 23 <= n
		}
		// (n % 12 == 11) && (23 <= n)
		for (/* Already initialized. */; true; n += 12L) {
			// Check if n (i.e., 11 (mod 12)) is a safe prime.
			if (NumUtil.isSafePrimeSqrtFixedInput(n)) {
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
	 *             If <code>NumUtil.LARGEST_SAFE_PRIME_INT <= n</code>
	 */
	public static int safePrimeAfter(int n) throws IllegalArgumentException {
		if (NumUtil.LARGEST_SAFE_PRIME_INT <= n) {
			throw new IllegalArgumentException();
		}
		// n < NumUtil.LARGEST_SAFE_PRIME_INT
		return ((int) NumUtil.safePrimeAfter((long) n));
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return The first safe prime greater than <code>n</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>NumUtil.LARGEST_SAFE_PRIME_SHORT <= n</code>
	 */
	public static short safePrimeAfter(short n) throws IllegalArgumentException {
		if (NumUtil.LARGEST_SAFE_PRIME_SHORT <= n) {
			throw new IllegalArgumentException();
		}
		// n < NumUtil.LARGEST_SAFE_PRIME_SHORT
		return ((short) NumUtil.safePrimeAfter((long) n));
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @return The first safe prime greater than <code>n</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>NumUtil.LARGEST_SAFE_PRIME_BYTE <= n</code>
	 */
	public static byte safePrimeAfter(byte n) throws IllegalArgumentException {
		if (NumUtil.LARGEST_SAFE_PRIME_BYTE <= n) {
			throw new IllegalArgumentException();
		}
		// n < NumUtil.LARGEST_SAFE_PRIME_BYTE
		return ((byte) NumUtil.safePrimeAfter((long) n));
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
			// 6 <= n
			// i.e., (n == 6) || (n == 7)
			return 5L;
		}
		// 8 <= n

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
			// 23 <= n

			// Update n to be the first integer which is 11 (mod 12) and smaller than the original value of n.
			n -= 12L; // 11 <= n
		} else { // i.e., (0 <= mod12) && (mod12 <= 10)
			if (n < 13L) { // i.e., ((8 <= n) && (n <= 10)) || (n == 12)
				return ((n == 12L) ? 11L : 7L);
			}
			// 13 <= n

			// Update n to be the first integer which is 11 (mod 12) and smaller than the original value of n.
			n -= 1L + mod12; // 11 <= n
		}
		// (n % 12 == 11) && (11 <= n)
		for (/* Already initialized. */; true; n -= 12L) {
			// Check if n (i.e., 11 (mod 12)) is a safe prime.
			if (NumUtil.isSafePrimeSqrtFixedInput(n)) {
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
		return ((int) NumUtil.safePrimeBefore((long) n));
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
		return ((short) NumUtil.safePrimeBefore((long) n));
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
		return ((byte) NumUtil.safePrimeBefore((long) n));
	}

	/**
	 * Precondition: <code>(result != null) && (1 <= result.size())</code>
	 * 
	 * @param result
	 *            the map produced by <code>NumUtil.factorSqrt(long, boolean, boolean)</code> to be
	 *            printed in the general case (i.e., absolute value of n was greater than 1)
	 * 
	 * @param hash
	 *            specifies whether the data structure used to store the factors, is a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code>
	 * 
	 * @return <code>result</code>.
	 */
	protected static Map<Long, Byte> printFactorsLong(Map<Long, Byte> result, boolean hash) {
		/*
		 * First remove the -1 factor if it's there, so that it's not printed twice but print a
		 * multiplication symbol.
		 */
		boolean negative = false;
		if (result.remove(-1L) != null) {
			negative = true;
			System.out.print(" * ");
		}
		// Even if a -1 factor was removed, we know that 1 <= result.size() at this point.

		/*
		 * There is no ordering in a HashMap so just iterate through it and print all of the factors.
		 * However, only print a multiplication symbol if there's at least one more factor in each iteration
		 * of the loop.
		 */
		if (hash) { // i.e., result instanceof HashMap
			final Iterator<Map.Entry<Long, Byte>> it = result.entrySet().iterator();
			Map.Entry<Long, Byte> entry = null;
			boolean notExit = true;
			do {
				entry = it.next();
				System.out.print("(" + entry.getKey() + ")^" + entry.getValue());
				// The following is meant to be an assignment of notExit.
				if (notExit = it.hasNext()) {
					System.out.print(" * ");
				}
			} while (notExit);
			System.out.println(); // Print a newline at the end.
			// Put all of then removed factors back into result before returning.
			if (negative) {
				result.put(-1L, (byte) 1);
			}
			return result;
		}
		// !hash
		// i.e., result instanceof TreeMap

		/*
		 * TreeMap is ordered by increasing keys and so we can print the factors in a nicer way. First
		 * remove the smallest prime factor and print it if there is more than one factor. Then print all of
		 * the other factors with a multiplication symbol before them.
		 */
		if (result.size() == 1) {
			final Map.Entry<Long, Byte> first = ((TreeMap<Long, Byte>) result).firstEntry();
			System.out.print("(" + first.getKey() + ")^" + first.getValue());
		} else { // result.size() != 1
			// i.e., 2 <= result.size()
			final Map.Entry<Long, Byte> first = ((TreeMap<Long, Byte>) result).pollFirstEntry();
			System.out.print("(" + first.getKey() + ")^" + first.getValue());
			// 1 <= result.size()
			final Iterator<Map.Entry<Long, Byte>> it = result.entrySet().iterator();
			for (Map.Entry<Long, Byte> entry = null; it.hasNext(); /* Update inside. */) {
				entry = it.next();
				System.out.print(" * (" + entry.getKey() + ")^" + entry.getValue());
			}
			// Put all of then removed factors back into result before returning.
			result.put(first.getKey(), first.getValue());
		}
		System.out.println(); // Print a newline at the end.
		// Put all of then removed factors back into result before returning.
		if (negative) {
			result.put(-1L, (byte) 1);
		}
		return result;
	}

	/**
	 * Runtime is in <code>O(sqrt(n) + time for Result.size() many put operations)</code>. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>((n == 0) || (n == 1)) if and only if Result.isEmpty()</code> <br>
	 * Postcondition: <code>(n == -1) implies (Result.size() == 1)</code> <br>
	 * Postcondition: <code>(n < 0) implies ((Result.get(-1L) != null) && (Result.get(-1L) == 1))</code>
	 * <br>
	 * Postcondition: <code>(0 <= n) implies (Result.get(-1L) == null)</code> <br>
	 * Postcondition:
	 * 
	 * <pre>
	 * <code>
	 * for (final Map.Entry&lt;Long, Byte&gt; entry : Result.entrySet()) {
	 * 	assert ((entry.getKey() != null) && ((entry.getKey() == -1L) || NumUtil.isPrimeSqrt(entry.getKey())));
	 * 	assert ((entry.getValue() != null) && (0 < entry.getValue()) && (entry.getValue() < 64));
	 * }
	 * </code>
	 * </pre>
	 * 
	 * Postcondition:
	 * 
	 * <pre>
	 * <code>
	 * if (n == Long.MIN_VALUE) {
	 * 	assert ((Result.size() == 2) && (Result.get(2L) == 63));
	 * } else if (!Result.isEmpty()) {
	 * 	long N = 1L;
	 * 	for (final Map.Entry&lt;Long, Byte&gt; entry : Result.entrySet()) {
	 * 		N = Math.multiplyExact(N, MathUtil.powExact(entry.getKey(), entry.getValue()));
	 * 	}
	 * 	assert (n == N);
	 * }
	 * </code>
	 * </pre>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param hash
	 *            specifies whether the data structure used to store the factors, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code>
	 * 
	 * @param print
	 *            specifies whether the result should be printed to the standard output stream
	 * 
	 * @return The resulting Map object.
	 */
	public static Map<Long, Byte> factorSqrt(long n, boolean hash, boolean print) {
		final Map<Long, Byte> result = hash ? new HashMap<Long, Byte>() : new TreeMap<Long, Byte>();

		// Factor -1 out of n if it's negative.
		if (n < 0L) {
			result.put(-1L, (byte) 1);
			// Only print if requested.
			if (print) {
				System.out.print(n + " = (-1)^1");
			}

			// Handle the degenerate case where n's absolute value is not representable as a non-negative long.
			if ((n *= -1L) == Long.MIN_VALUE) { // i.e., -n == n < 0
				result.put(2L, (byte) 63);
				// Only print if requested.
				if (print) {
					System.out.println(" * (2)^63");
				}
				return result;
			}
			// n != Long.MIN_VALUE
			// i.e., 0 < n
		} else {
			// Only print if requested.
			if (print) {
				System.out.print(n + " = ");
			}
		}
		// 0 <= n

		// Handle the simple special case.
		if (n < 2L) { // i.e., (n == 0) || (n == 1)
			// Only print if requested.
			if (print) {
				System.out.println(n);
			}
			return result;
		}
		// 2 <= n

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
		// Count the power of each prime which will fit in a byte since the largest power is at most 62.
		byte power = 0;
		/**
		 * Don't do <code>(n &= 1L) == 0L</code> since we need the value of <code>n</code> to remain
		 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
		 * will mutate <code>n</code>.
		 */
		// Check if 2 is a factor of n.
		if ((n & 1L) == 0L) { // i.e., n % 2 == 0
			// Update n and result.
			power = 0;
			do {
				n /= 2L;
				++power;
			} while ((n & 1L) == 0L); // i.e., n % 2 == 0
			result.put(2L, power);

			// Check if n has no more factors.
			if (n == 1L) {
				// Only print if requested.
				return (print ? NumUtil.printFactorsLong(result, hash) : result);
			}
		}
		// Check if 3 is a factor of n.
		if (n % 3L == 0L) {
			// Update n and result.
			power = 0;
			do {
				n /= 3L;
				++power;
			} while (n % 3L == 0L);
			result.put(3L, power);

			// Check if n has no more factors.
			if (n == 1L) {
				// Only print if requested.
				return (print ? NumUtil.printFactorsLong(result, hash) : result);
			}
		}
		/**
		 * Note that we are taking the square root of <code>n</code> after potentially having "pulled out"
		 * all of the <code>2</code> and the <code>3</code> factors. This means that the square root may not
		 * be applied to the original value of <code>n</code> but this is fine since all of the remaining
		 * prime divisors of the original value of <code>n</code> which are less than the square root, are
		 * still being considered.
		 */
		// Applying Math.floor before casting to long is unnecessary and it causes a large slow down.
		final long bound = ((long) Math.sqrt(n)) + 1L; // 2 <= bound
		final long maxI = bound + 1L; // 3 <= maxI
		for (long i = 5L; i < maxI; i += 4L) {
			// Check if i (i.e., -1 (mod 6)) is a factor of n.
			if (n % i == 0L) {
				// Update n and result.
				power = 0;
				do {
					n /= i;
					++power;
				} while (n % i == 0L);
				result.put(i, power);

				// Check if n has no more factors.
				if (n == 1L) {
					// Only print if requested.
					return (print ? NumUtil.printFactorsLong(result, hash) : result);
				}
			}

			/**
			 * It's fine to do <code>i += 2</code> instead of <code>i + 2</code> since we don't need the value
			 * of <code>i</code> to remain unchanged but quite the opposite. We have actually separated the
			 * incrementation of <code>i</code> by <code>6</code>, into an incrementation by <code>2</code> and
			 * an incrementation by <code>4</code> due to the need to add <code>2</code> to <code>i</code> at
			 * this point and the fact that <code>+=</code> is faster than <code>+</code> due to it not creating
			 * a temporary. Note that the difference is the <code>+=</code> instead of the <code>+</code> which
			 * will mutate <code>i</code>.
			 */
			// Check if i + 2 (i.e., 1 (mod 6)) is a factor of n.
			if (n % (i += 2L) == 0L) {
				// Update n and result.
				power = 0;
				do {
					n /= i;
					++power;
				} while (n % i == 0L);
				result.put(i, power);

				// Check if n has no more factors.
				if (n == 1L) {
					// Only print if requested.
					return (print ? NumUtil.printFactorsLong(result, hash) : result);
				}
			}
		}
		/**
		 * For all <code>n</code>, <code>n</code> can have at most one factor greater than
		 * <code>sqrt(n)</code>. <br>
		 * <br>
		 * 
		 * Therefore, due to the above loop, we know that <code>n</code> is a prime and a divisor of the
		 * original value of <code>n</code> at this point.
		 */
		result.put(n, (byte) 1);
		// Only print if requested.
		return (print ? NumUtil.printFactorsLong(result, hash) : result);
	}

	/**
	 * Runtime is in <code>O(sqrt(n) + time for Result.size() many put operations)</code>. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>((n == 0) || (n == 1)) if and only if Result.isEmpty()</code> <br>
	 * Postcondition: <code>(n == -1) implies (Result.size() == 1)</code> <br>
	 * Postcondition: <code>(n < 0) implies ((Result.get(-1L) != null) && (Result.get(-1L) == 1))</code>
	 * <br>
	 * Postcondition: <code>(0 <= n) implies (Result.get(-1L) == null)</code> <br>
	 * Postcondition:
	 * 
	 * <pre>
	 * <code>
	 * for (final Map.Entry&lt;Long, Byte&gt; entry : Result.entrySet()) {
	 * 	assert ((entry.getKey() != null) && ((entry.getKey() == -1L) || NumUtil.isPrimeSqrt(entry.getKey())));
	 * 	assert ((entry.getValue() != null) && (0 < entry.getValue()) && (entry.getValue() < 64));
	 * }
	 * </code>
	 * </pre>
	 * 
	 * Postcondition:
	 * 
	 * <pre>
	 * <code>
	 * if (n == Long.MIN_VALUE) {
	 * 	assert ((Result.size() == 2) && (Result.get(2L) == 63));
	 * } else if (!Result.isEmpty()) {
	 * 	long N = 1L;
	 * 	for (final Map.Entry&lt;Long, Byte&gt; entry : Result.entrySet()) {
	 * 		N = Math.multiplyExact(N, MathUtil.powExact(entry.getKey(), entry.getValue()));
	 * 	}
	 * 	assert (n == N);
	 * }
	 * </code>
	 * </pre>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param hash
	 *            specifies whether the data structure used to store the factors, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code>
	 * 
	 * @return <code>NumUtil.factorSqrt(n, hash, false)</code>.
	 */
	public static Map<Long, Byte> factorSqrt(long n, boolean hash) {
		return NumUtil.factorSqrt(n, hash, false);
	}

	/**
	 * Runtime is in <code>O(sqrt(n) + time for Result.size() many put operations)</code>. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>((n == 0) || (n == 1)) if and only if Result.isEmpty()</code> <br>
	 * Postcondition: <code>(n == -1) implies (Result.size() == 1)</code> <br>
	 * Postcondition: <code>(n < 0) implies ((Result.get(-1L) != null) && (Result.get(-1L) == 1))</code>
	 * <br>
	 * Postcondition: <code>(0 <= n) implies (Result.get(-1L) == null)</code> <br>
	 * Postcondition:
	 * 
	 * <pre>
	 * <code>
	 * for (final Map.Entry&lt;Long, Byte&gt; entry : Result.entrySet()) {
	 * 	assert ((entry.getKey() != null) && ((entry.getKey() == -1L) || NumUtil.isPrimeSqrt(entry.getKey())));
	 * 	assert ((entry.getValue() != null) && (0 < entry.getValue()) && (entry.getValue() < 64));
	 * }
	 * </code>
	 * </pre>
	 * 
	 * Postcondition:
	 * 
	 * <pre>
	 * <code>
	 * if (n == Long.MIN_VALUE) {
	 * 	assert ((Result.size() == 2) && (Result.get(2L) == 63));
	 * } else if (!Result.isEmpty()) {
	 * 	long N = 1L;
	 * 	for (final Map.Entry&lt;Long, Byte&gt; entry : Result.entrySet()) {
	 * 		N = Math.multiplyExact(N, MathUtil.powExact(entry.getKey(), entry.getValue()));
	 * 	}
	 * 	assert (n == N);
	 * }
	 * </code>
	 * </pre>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>NumUtil.factorSqrt(n, false)</code>.
	 */
	public static Map<Long, Byte> factorSqrt(long n) {
		return NumUtil.factorSqrt(n, false);
	}

	/**
	 * Precondition: <code>(result != null) && (1 <= result.size())</code>
	 * 
	 * @param result
	 *            the map produced by <code>NumUtil.factorSqrt(int, boolean, boolean)</code> to be
	 *            printed in the general case (i.e., absolute value of n was greater than 1)
	 * 
	 * @param hash
	 *            specifies whether the data structure used to store the factors, is a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code>
	 * 
	 * @return <code>result</code>.
	 */
	protected static Map<Integer, Byte> printFactorsInteger(Map<Integer, Byte> result, boolean hash) {
		/*
		 * First remove the -1 factor if it's there, so that it's not printed twice but print a
		 * multiplication symbol.
		 */
		boolean negative = false;
		if (result.remove(-1) != null) {
			negative = true;
			System.out.print(" * ");
		}
		// Even if a -1 factor was removed, we know that 1 <= result.size() at this point.

		/*
		 * There is no ordering in a HashMap so just iterate through it and print all of the factors.
		 * However, only print a multiplication symbol if there's at least one more factor in each iteration
		 * of the loop.
		 */
		if (hash) { // i.e., result instanceof HashMap
			final Iterator<Map.Entry<Integer, Byte>> it = result.entrySet().iterator();
			Map.Entry<Integer, Byte> entry = null;
			boolean notExit = true;
			do {
				entry = it.next();
				System.out.print("(" + entry.getKey() + ")^" + entry.getValue());
				// The following is meant to be an assignment of notExit.
				if (notExit = it.hasNext()) {
					System.out.print(" * ");
				}
			} while (notExit);
			System.out.println(); // Print a newline at the end.
			// Put all of then removed factors back into result before returning.
			if (negative) {
				result.put(-1, (byte) 1);
			}
			return result;
		}
		// !hash
		// i.e., result instanceof TreeMap

		/*
		 * TreeMap is ordered by increasing keys and so we can print the factors in a nicer way. First
		 * remove the smallest prime factor and print it if there is more than one factor. Then print all of
		 * the other factors with a multiplication symbol before them.
		 */
		if (result.size() == 1) {
			final Map.Entry<Integer, Byte> first = ((TreeMap<Integer, Byte>) result).firstEntry();
			System.out.print("(" + first.getKey() + ")^" + first.getValue());
		} else { // result.size() != 1
			// i.e., 2 <= result.size()
			final Map.Entry<Integer, Byte> first = ((TreeMap<Integer, Byte>) result).pollFirstEntry();
			System.out.print("(" + first.getKey() + ")^" + first.getValue());
			// 1 <= result.size()
			final Iterator<Map.Entry<Integer, Byte>> it = result.entrySet().iterator();
			for (Map.Entry<Integer, Byte> entry = null; it.hasNext(); /* Update inside. */) {
				entry = it.next();
				System.out.print(" * (" + entry.getKey() + ")^" + entry.getValue());
			}
			// Put all of then removed factors back into result before returning.
			result.put(first.getKey(), first.getValue());
		}
		System.out.println(); // Print a newline at the end.
		// Put all of then removed factors back into result before returning.
		if (negative) {
			result.put(-1, (byte) 1);
		}
		return result;
	}

	/**
	 * Runtime is in <code>O(sqrt(n) + time for Result.size() many put operations)</code>. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>((n == 0) || (n == 1)) if and only if Result.isEmpty()</code> <br>
	 * Postcondition: <code>(n == -1) implies (Result.size() == 1)</code> <br>
	 * Postcondition: <code>(n < 0) implies ((Result.get(-1) != null) && (Result.get(-1) == 1))</code>
	 * <br>
	 * Postcondition: <code>(0 <= n) implies (Result.get(-1) == null)</code> <br>
	 * Postcondition:
	 * 
	 * <pre>
	 * <code>
	 * for (final Map.Entry&lt;Integer, Byte&gt; entry : Result.entrySet()) {
	 * 	assert ((entry.getKey() != null) && ((entry.getKey() == -1) || NumUtil.isPrimeSqrt(entry.getKey())));
	 * 	assert ((entry.getValue() != null) && (0 < entry.getValue()) && (entry.getValue() < 32));
	 * }
	 * </code>
	 * </pre>
	 * 
	 * Postcondition:
	 * 
	 * <pre>
	 * <code>
	 * if (n == Integer.MIN_VALUE) {
	 * 	assert ((Result.size() == 2) && (Result.get(2) == 31));
	 * } else if (!Result.isEmpty()) {
	 * 	long N = 1L;
	 * 	for (final Map.Entry&lt;Integer, Byte&gt; entry : Result.entrySet()) {
	 * 		N = Math.multiplyExact(N, MathUtil.powExact(entry.getKey(), entry.getValue()));
	 * 	}
	 * 	assert (n == N);
	 * }
	 * </code>
	 * </pre>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param hash
	 *            specifies whether the data structure used to store the factors, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code>
	 * 
	 * @param print
	 *            specifies whether the result should be printed to the standard output stream
	 * 
	 * @return The resulting Map object.
	 */
	public static Map<Integer, Byte> factorSqrt(int n, boolean hash, boolean print) {
		final Map<Integer, Byte> result = hash ? new HashMap<Integer, Byte>() : new TreeMap<Integer, Byte>();

		// Factor -1 out of n if it's negative.
		if (n < 0) {
			result.put(-1, (byte) 1);
			// Only print if requested.
			if (print) {
				System.out.print(n + " = (-1)^1");
			}

			// Handle the degenerate case where n's absolute value is not representable as a non-negative int.
			if ((n *= -1) == Integer.MIN_VALUE) { // i.e., -n == n < 0
				result.put(2, (byte) 31);
				// Only print if requested.
				if (print) {
					System.out.println(" * (2)^31");
				}
				return result;
			}
			// n != Integer.MIN_VALUE
			// i.e., 0 < n
		} else {
			// Only print if requested.
			if (print) {
				System.out.print(n + " = ");
			}
		}
		// 0 <= n

		// Handle the simple special case.
		if (n < 2) { // i.e., (n == 0) || (n == 1)
			// Only print if requested.
			if (print) {
				System.out.println(n);
			}
			return result;
		}
		// 2 <= n

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
		// Count the power of each prime which will fit in a byte since the largest power is at most 30.
		byte power = 0;
		/**
		 * Don't do <code>(n &= 1) == 0</code> since we need the value of <code>n</code> to remain
		 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
		 * will mutate <code>n</code>.
		 */
		// Check if 2 is a factor of n.
		if ((n & 1) == 0) { // i.e., n % 2 == 0
			// Update n and result.
			power = 0;
			do {
				n /= 2;
				++power;
			} while ((n & 1) == 0); // i.e., n % 2 == 0
			result.put(2, power);

			// Check if n has no more factors.
			if (n == 1) {
				// Only print if requested.
				return (print ? NumUtil.printFactorsInteger(result, hash) : result);
			}
		}
		// Check if 3 is a factor of n.
		if (n % 3 == 0) {
			// Update n and result.
			power = 0;
			do {
				n /= 3;
				++power;
			} while (n % 3 == 0);
			result.put(3, power);

			// Check if n has no more factors.
			if (n == 1) {
				// Only print if requested.
				return (print ? NumUtil.printFactorsInteger(result, hash) : result);
			}
		}
		/**
		 * Note that we are taking the square root of <code>n</code> after potentially having "pulled out"
		 * all of the <code>2</code> and the <code>3</code> factors. This means that the square root may not
		 * be applied to the original value of <code>n</code> but this is fine since all of the remaining
		 * prime divisors of the original value of <code>n</code> which are less than the square root, are
		 * still being considered.
		 */
		// Applying Math.floor before casting to int is unnecessary and it causes a large slow down.
		final int bound = ((int) Math.sqrt(n)) + 1; // 2 <= bound
		final int maxI = bound + 1; // 3 <= maxI
		for (int i = 5; i < maxI; i += 4) {
			// Check if i (i.e., -1 (mod 6)) is a factor of n.
			if (n % i == 0) {
				// Update n and result.
				power = 0;
				do {
					n /= i;
					++power;
				} while (n % i == 0);
				result.put(i, power);

				// Check if n has no more factors.
				if (n == 1) {
					// Only print if requested.
					return (print ? NumUtil.printFactorsInteger(result, hash) : result);
				}
			}

			/**
			 * It's fine to do <code>i += 2</code> instead of <code>i + 2</code> since we don't need the value
			 * of <code>i</code> to remain unchanged but quite the opposite. We have actually separated the
			 * incrementation of <code>i</code> by <code>6</code>, into an incrementation by <code>2</code> and
			 * an incrementation by <code>4</code> due to the need to add <code>2</code> to <code>i</code> at
			 * this point and the fact that <code>+=</code> is faster than <code>+</code> due to it not creating
			 * a temporary. Note that the difference is the <code>+=</code> instead of the <code>+</code> which
			 * will mutate <code>i</code>.
			 */
			// Check if i + 2 (i.e., 1 (mod 6)) is a factor of n.
			if (n % (i += 2) == 0) {
				// Update n and result.
				power = 0;
				do {
					n /= i;
					++power;
				} while (n % i == 0);
				result.put(i, power);

				// Check if n has no more factors.
				if (n == 1) {
					// Only print if requested.
					return (print ? NumUtil.printFactorsInteger(result, hash) : result);
				}
			}
		}
		/**
		 * For all <code>n</code>, <code>n</code> can have at most one factor greater than
		 * <code>sqrt(n)</code>. <br>
		 * <br>
		 * 
		 * Therefore, due to the above loop, we know that <code>n</code> is a prime and a divisor of the
		 * original value of <code>n</code> at this point.
		 */
		result.put(n, (byte) 1);
		// Only print if requested.
		return (print ? NumUtil.printFactorsInteger(result, hash) : result);
	}

	/**
	 * Runtime is in <code>O(sqrt(n) + time for Result.size() many put operations)</code>. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>((n == 0) || (n == 1)) if and only if Result.isEmpty()</code> <br>
	 * Postcondition: <code>(n == -1) implies (Result.size() == 1)</code> <br>
	 * Postcondition: <code>(n < 0) implies ((Result.get(-1) != null) && (Result.get(-1) == 1))</code>
	 * <br>
	 * Postcondition: <code>(0 <= n) implies (Result.get(-1) == null)</code> <br>
	 * Postcondition:
	 * 
	 * <pre>
	 * <code>
	 * for (final Map.Entry&lt;Integer, Byte&gt; entry : Result.entrySet()) {
	 * 	assert ((entry.getKey() != null) && ((entry.getKey() == -1) || NumUtil.isPrimeSqrt(entry.getKey())));
	 * 	assert ((entry.getValue() != null) && (0 < entry.getValue()) && (entry.getValue() < 32));
	 * }
	 * </code>
	 * </pre>
	 * 
	 * Postcondition:
	 * 
	 * <pre>
	 * <code>
	 * if (n == Integer.MIN_VALUE) {
	 * 	assert ((Result.size() == 2) && (Result.get(2) == 31));
	 * } else if (!Result.isEmpty()) {
	 * 	long N = 1L;
	 * 	for (final Map.Entry&lt;Integer, Byte&gt; entry : Result.entrySet()) {
	 * 		N = Math.multiplyExact(N, MathUtil.powExact(entry.getKey(), entry.getValue()));
	 * 	}
	 * 	assert (n == N);
	 * }
	 * </code>
	 * </pre>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param hash
	 *            specifies whether the data structure used to store the factors, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code>
	 * 
	 * @return <code>NumUtil.factorSqrt(n, hash, false)</code>.
	 */
	public static Map<Integer, Byte> factorSqrt(int n, boolean hash) {
		return NumUtil.factorSqrt(n, hash, false);
	}

	/**
	 * Runtime is in <code>O(sqrt(n) + time for Result.size() many put operations)</code>. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>((n == 0) || (n == 1)) if and only if Result.isEmpty()</code> <br>
	 * Postcondition: <code>(n == -1) implies (Result.size() == 1)</code> <br>
	 * Postcondition: <code>(n < 0) implies ((Result.get(-1) != null) && (Result.get(-1) == 1))</code>
	 * <br>
	 * Postcondition: <code>(0 <= n) implies (Result.get(-1) == null)</code> <br>
	 * Postcondition:
	 * 
	 * <pre>
	 * <code>
	 * for (final Map.Entry&lt;Integer, Byte&gt; entry : Result.entrySet()) {
	 * 	assert ((entry.getKey() != null) && ((entry.getKey() == -1) || NumUtil.isPrimeSqrt(entry.getKey())));
	 * 	assert ((entry.getValue() != null) && (0 < entry.getValue()) && (entry.getValue() < 32));
	 * }
	 * </code>
	 * </pre>
	 * 
	 * Postcondition:
	 * 
	 * <pre>
	 * <code>
	 * if (n == Integer.MIN_VALUE) {
	 * 	assert ((Result.size() == 2) && (Result.get(2) == 31));
	 * } else if (!Result.isEmpty()) {
	 * 	long N = 1L;
	 * 	for (final Map.Entry&lt;Integer, Byte&gt; entry : Result.entrySet()) {
	 * 		N = Math.multiplyExact(N, MathUtil.powExact(entry.getKey(), entry.getValue()));
	 * 	}
	 * 	assert (n == N);
	 * }
	 * </code>
	 * </pre>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>NumUtil.factorSqrt(n, false)</code>.
	 */
	public static Map<Integer, Byte> factorSqrt(int n) {
		return NumUtil.factorSqrt(n, false);
	}

	/**
	 * Precondition: <code>(result != null) && (1 <= result.size())</code>
	 * 
	 * @param result
	 *            the map produced by <code>NumUtil.factorSqrt(short, boolean, boolean)</code> to be
	 *            printed in the general case (i.e., absolute value of n was greater than 1)
	 * 
	 * @param hash
	 *            specifies whether the data structure used to store the factors, is a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code>
	 * 
	 * @return <code>result</code>.
	 */
	protected static Map<Short, Byte> printFactorsShort(Map<Short, Byte> result, boolean hash) {
		/*
		 * First remove the -1 factor if it's there, so that it's not printed twice but print a
		 * multiplication symbol.
		 */
		boolean negative = false;
		if (result.remove((short) -1) != null) {
			negative = true;
			System.out.print(" * ");
		}
		// Even if a -1 factor was removed, we know that 1 <= result.size() at this point.

		/*
		 * There is no ordering in a HashMap so just iterate through it and print all of the factors.
		 * However, only print a multiplication symbol if there's at least one more factor in each iteration
		 * of the loop.
		 */
		if (hash) { // i.e., result instanceof HashMap
			final Iterator<Map.Entry<Short, Byte>> it = result.entrySet().iterator();
			Map.Entry<Short, Byte> entry = null;
			boolean notExit = true;
			do {
				entry = it.next();
				System.out.print("(" + entry.getKey() + ")^" + entry.getValue());
				// The following is meant to be an assignment of notExit.
				if (notExit = it.hasNext()) {
					System.out.print(" * ");
				}
			} while (notExit);
			System.out.println(); // Print a newline at the end.
			// Put all of then removed factors back into result before returning.
			if (negative) {
				result.put((short) -1, (byte) 1);
			}
			return result;
		}
		// !hash
		// i.e., result instanceof TreeMap

		/*
		 * TreeMap is ordered by increasing keys and so we can print the factors in a nicer way. First
		 * remove the smallest prime factor and print it if there is more than one factor. Then print all of
		 * the other factors with a multiplication symbol before them.
		 */
		if (result.size() == 1) {
			final Map.Entry<Short, Byte> first = ((TreeMap<Short, Byte>) result).firstEntry();
			System.out.print("(" + first.getKey() + ")^" + first.getValue());
		} else { // result.size() != 1
			// i.e., 2 <= result.size()
			final Map.Entry<Short, Byte> first = ((TreeMap<Short, Byte>) result).pollFirstEntry();
			System.out.print("(" + first.getKey() + ")^" + first.getValue());
			// 1 <= result.size()
			final Iterator<Map.Entry<Short, Byte>> it = result.entrySet().iterator();
			for (Map.Entry<Short, Byte> entry = null; it.hasNext(); /* Update inside. */) {
				entry = it.next();
				System.out.print(" * (" + entry.getKey() + ")^" + entry.getValue());
			}
			// Put all of then removed factors back into result before returning.
			result.put(first.getKey(), first.getValue());
		}
		System.out.println(); // Print a newline at the end.
		// Put all of then removed factors back into result before returning.
		if (negative) {
			result.put((short) -1, (byte) 1);
		}
		return result;
	}

	/**
	 * Runtime is in <code>O(sqrt(n) + time for Result.size() many put operations)</code>. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>((n == 0) || (n == 1)) if and only if Result.isEmpty()</code> <br>
	 * Postcondition: <code>(n == -1) implies (Result.size() == 1)</code> <br>
	 * Postcondition:
	 * <code>(n < 0) implies ((Result.get((short) -1) != null) && (Result.get((short) -1) == 1))</code>
	 * <br>
	 * Postcondition: <code>(0 <= n) implies (Result.get((short) -1) == null)</code> <br>
	 * Postcondition:
	 * 
	 * <pre>
	 * <code>
	 * for (final Map.Entry&lt;Short, Byte&gt; entry : Result.entrySet()) {
	 * 	assert ((entry.getKey() != null) && ((entry.getKey() == -1) || NumUtil.isPrimeSqrt(entry.getKey())));
	 * 	assert ((entry.getValue() != null) && (0 < entry.getValue()) && (entry.getValue() < 16));
	 * }
	 * </code>
	 * </pre>
	 * 
	 * Postcondition:
	 * 
	 * <pre>
	 * <code>
	 * if (n == Short.MIN_VALUE) {
	 * 	assert ((Result.size() == 2) && (Result.get((short) 2) == 15));
	 * } else if (!Result.isEmpty()) {
	 * 	long N = 1L;
	 * 	for (final Map.Entry&lt;Short, Byte&gt; entry : Result.entrySet()) {
	 * 		N = Math.multiplyExact(N, MathUtil.powExact(entry.getKey(), entry.getValue()));
	 * 	}
	 * 	assert (n == N);
	 * }
	 * </code>
	 * </pre>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param hash
	 *            specifies whether the data structure used to store the factors, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code>
	 * 
	 * @param print
	 *            specifies whether the result should be printed to the standard output stream
	 * 
	 * @return The resulting Map object.
	 */
	public static Map<Short, Byte> factorSqrt(short n, boolean hash, boolean print) {
		final Map<Short, Byte> result = hash ? new HashMap<Short, Byte>() : new TreeMap<Short, Byte>();

		// Factor -1 out of n if it's negative.
		if (n < 0) {
			result.put((short) -1, (byte) 1);
			// Only print if requested.
			if (print) {
				System.out.print(n + " = (-1)^1");
			}

			// Handle the degenerate case where n's absolute value is not representable as a non-negative short.
			if ((n *= -1) == Short.MIN_VALUE) { // i.e., -n == n < 0
				result.put((short) 2, (byte) 15);
				// Only print if requested.
				if (print) {
					System.out.println(" * (2)^15");
				}
				return result;
			}
			// n != Short.MIN_VALUE
			// i.e., 0 < n
		} else {
			// Only print if requested.
			if (print) {
				System.out.print(n + " = ");
			}
		}
		// 0 <= n

		// Handle the simple special case.
		if (n < 2) { // i.e., (n == 0) || (n == 1)
			// Only print if requested.
			if (print) {
				System.out.println(n);
			}
			return result;
		}
		// 2 <= n

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
		// Count the power of each prime which will fit in a byte since the largest power is at most 14.
		byte power = 0;
		/**
		 * Don't do <code>(n &= 1) == 0</code> since we need the value of <code>n</code> to remain
		 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
		 * will mutate <code>n</code>.
		 */
		// Check if 2 is a factor of n.
		if ((n & 1) == 0) { // i.e., n % 2 == 0
			// Update n and result.
			power = 0;
			do {
				n /= 2;
				++power;
			} while ((n & 1) == 0); // i.e., n % 2 == 0
			result.put((short) 2, power);

			// Check if n has no more factors.
			if (n == 1) {
				// Only print if requested.
				return (print ? NumUtil.printFactorsShort(result, hash) : result);
			}
		}
		// Check if 3 is a factor of n.
		if (n % 3 == 0) {
			// Update n and result.
			power = 0;
			do {
				n /= 3;
				++power;
			} while (n % 3 == 0);
			result.put((short) 3, power);

			// Check if n has no more factors.
			if (n == 1) {
				// Only print if requested.
				return (print ? NumUtil.printFactorsShort(result, hash) : result);
			}
		}
		/**
		 * Note that we are taking the square root of <code>n</code> after potentially having "pulled out"
		 * all of the <code>2</code> and the <code>3</code> factors. This means that the square root may not
		 * be applied to the original value of <code>n</code> but this is fine since all of the remaining
		 * prime divisors of the original value of <code>n</code> which are less than the square root, are
		 * still being considered.
		 */
		// Applying Math.floor before casting to short is unnecessary and it causes a large slow down.
		final short bound = (short) (((short) Math.sqrt(n)) + 1); // 2 <= bound
		final short maxI = (short) (bound + 1); // 3 <= maxI
		for (short i = 5; i < maxI; i += 4) {
			// Check if i (i.e., -1 (mod 6)) is a factor of n.
			if (n % i == 0) {
				// Update n and result.
				power = 0;
				do {
					n /= i;
					++power;
				} while (n % i == 0);
				result.put(i, power);

				// Check if n has no more factors.
				if (n == 1) {
					// Only print if requested.
					return (print ? NumUtil.printFactorsShort(result, hash) : result);
				}
			}

			/**
			 * It's fine to do <code>i += 2</code> instead of <code>i + 2</code> since we don't need the value
			 * of <code>i</code> to remain unchanged but quite the opposite. We have actually separated the
			 * incrementation of <code>i</code> by <code>6</code>, into an incrementation by <code>2</code> and
			 * an incrementation by <code>4</code> due to the need to add <code>2</code> to <code>i</code> at
			 * this point and the fact that <code>+=</code> is faster than <code>+</code> due to it not creating
			 * a temporary. Note that the difference is the <code>+=</code> instead of the <code>+</code> which
			 * will mutate <code>i</code>.
			 */
			// Check if i + 2 (i.e., 1 (mod 6)) is a factor of n.
			if (n % (i += 2) == 0) {
				// Update n and result.
				power = 0;
				do {
					n /= i;
					++power;
				} while (n % i == 0);
				result.put(i, power);

				// Check if n has no more factors.
				if (n == 1) {
					// Only print if requested.
					return (print ? NumUtil.printFactorsShort(result, hash) : result);
				}
			}
		}
		/**
		 * For all <code>n</code>, <code>n</code> can have at most one factor greater than
		 * <code>sqrt(n)</code>. <br>
		 * <br>
		 * 
		 * Therefore, due to the above loop, we know that <code>n</code> is a prime and a divisor of the
		 * original value of <code>n</code> at this point.
		 */
		result.put(n, (byte) 1);
		// Only print if requested.
		return (print ? NumUtil.printFactorsShort(result, hash) : result);
	}

	/**
	 * Runtime is in <code>O(sqrt(n) + time for Result.size() many put operations)</code>. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>((n == 0) || (n == 1)) if and only if Result.isEmpty()</code> <br>
	 * Postcondition: <code>(n == -1) implies (Result.size() == 1)</code> <br>
	 * Postcondition:
	 * <code>(n < 0) implies ((Result.get((short) -1) != null) && (Result.get((short) -1) == 1))</code>
	 * <br>
	 * Postcondition: <code>(0 <= n) implies (Result.get((short) -1) == null)</code> <br>
	 * Postcondition:
	 * 
	 * <pre>
	 * <code>
	 * for (final Map.Entry&lt;Short, Byte&gt; entry : Result.entrySet()) {
	 * 	assert ((entry.getKey() != null) && ((entry.getKey() == -1) || NumUtil.isPrimeSqrt(entry.getKey())));
	 * 	assert ((entry.getValue() != null) && (0 < entry.getValue()) && (entry.getValue() < 16));
	 * }
	 * </code>
	 * </pre>
	 * 
	 * Postcondition:
	 * 
	 * <pre>
	 * <code>
	 * if (n == Short.MIN_VALUE) {
	 * 	assert ((Result.size() == 2) && (Result.get((short) 2) == 15));
	 * } else if (!Result.isEmpty()) {
	 * 	long N = 1L;
	 * 	for (final Map.Entry&lt;Short, Byte&gt; entry : Result.entrySet()) {
	 * 		N = Math.multiplyExact(N, MathUtil.powExact(entry.getKey(), entry.getValue()));
	 * 	}
	 * 	assert (n == N);
	 * }
	 * </code>
	 * </pre>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param hash
	 *            specifies whether the data structure used to store the factors, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code>
	 * 
	 * @return <code>NumUtil.factorSqrt(n, hash, false)</code>.
	 */
	public static Map<Short, Byte> factorSqrt(short n, boolean hash) {
		return NumUtil.factorSqrt(n, hash, false);
	}

	/**
	 * Runtime is in <code>O(sqrt(n) + time for Result.size() many put operations)</code>. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>((n == 0) || (n == 1)) if and only if Result.isEmpty()</code> <br>
	 * Postcondition: <code>(n == -1) implies (Result.size() == 1)</code> <br>
	 * Postcondition:
	 * <code>(n < 0) implies ((Result.get((short) -1) != null) && (Result.get((short) -1) == 1))</code>
	 * <br>
	 * Postcondition: <code>(0 <= n) implies (Result.get((short) -1) == null)</code> <br>
	 * Postcondition:
	 * 
	 * <pre>
	 * <code>
	 * for (final Map.Entry&lt;Short, Byte&gt; entry : Result.entrySet()) {
	 * 	assert ((entry.getKey() != null) && ((entry.getKey() == -1) || NumUtil.isPrimeSqrt(entry.getKey())));
	 * 	assert ((entry.getValue() != null) && (0 < entry.getValue()) && (entry.getValue() < 16));
	 * }
	 * </code>
	 * </pre>
	 * 
	 * Postcondition:
	 * 
	 * <pre>
	 * <code>
	 * if (n == Short.MIN_VALUE) {
	 * 	assert ((Result.size() == 2) && (Result.get((short) 2) == 15));
	 * } else if (!Result.isEmpty()) {
	 * 	long N = 1L;
	 * 	for (final Map.Entry&lt;Short, Byte&gt; entry : Result.entrySet()) {
	 * 		N = Math.multiplyExact(N, MathUtil.powExact(entry.getKey(), entry.getValue()));
	 * 	}
	 * 	assert (n == N);
	 * }
	 * </code>
	 * </pre>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>NumUtil.factorSqrt(n, false)</code>.
	 */
	public static Map<Short, Byte> factorSqrt(short n) {
		return NumUtil.factorSqrt(n, false);
	}

	/**
	 * Precondition: <code>(result != null) && (1 <= result.size())</code>
	 * 
	 * @param result
	 *            the map produced by <code>NumUtil.factorSqrt(byte, boolean, boolean)</code> to be
	 *            printed in the general case (i.e., absolute value of n was greater than 1)
	 * 
	 * @param hash
	 *            specifies whether the data structure used to store the factors, is a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code>
	 * 
	 * @return <code>result</code>.
	 */
	protected static Map<Byte, Byte> printFactorsByte(Map<Byte, Byte> result, boolean hash) {
		/*
		 * First remove the -1 factor if it's there, so that it's not printed twice but print a
		 * multiplication symbol.
		 */
		boolean negative = false;
		if (result.remove((byte) -1) != null) {
			negative = true;
			System.out.print(" * ");
		}
		// Even if a -1 factor was removed, we know that 1 <= result.size() at this point.

		/*
		 * There is no ordering in a HashMap so just iterate through it and print all of the factors.
		 * However, only print a multiplication symbol if there's at least one more factor in each iteration
		 * of the loop.
		 */
		if (hash) { // i.e., result instanceof HashMap
			final Iterator<Map.Entry<Byte, Byte>> it = result.entrySet().iterator();
			Map.Entry<Byte, Byte> entry = null;
			boolean notExit = true;
			do {
				entry = it.next();
				System.out.print("(" + entry.getKey() + ")^" + entry.getValue());
				// The following is meant to be an assignment of notExit.
				if (notExit = it.hasNext()) {
					System.out.print(" * ");
				}
			} while (notExit);
			System.out.println(); // Print a newline at the end.
			// Put all of then removed factors back into result before returning.
			if (negative) {
				result.put((byte) -1, (byte) 1);
			}
			return result;
		}
		// !hash
		// i.e., result instanceof TreeMap

		/*
		 * TreeMap is ordered by increasing keys and so we can print the factors in a nicer way. First
		 * remove the smallest prime factor and print it if there is more than one factor. Then print all of
		 * the other factors with a multiplication symbol before them.
		 */
		if (result.size() == 1) {
			final Map.Entry<Byte, Byte> first = ((TreeMap<Byte, Byte>) result).firstEntry();
			System.out.print("(" + first.getKey() + ")^" + first.getValue());
		} else { // result.size() != 1
			// i.e., 2 <= result.size()
			final Map.Entry<Byte, Byte> first = ((TreeMap<Byte, Byte>) result).pollFirstEntry();
			System.out.print("(" + first.getKey() + ")^" + first.getValue());
			// 1 <= result.size()
			final Iterator<Map.Entry<Byte, Byte>> it = result.entrySet().iterator();
			for (Map.Entry<Byte, Byte> entry = null; it.hasNext(); /* Update inside. */) {
				entry = it.next();
				System.out.print(" * (" + entry.getKey() + ")^" + entry.getValue());
			}
			// Put all of then removed factors back into result before returning.
			result.put(first.getKey(), first.getValue());
		}
		System.out.println(); // Print a newline at the end.
		// Put all of then removed factors back into result before returning.
		if (negative) {
			result.put((byte) -1, (byte) 1);
		}
		return result;
	}

	/**
	 * Runtime is in <code>O(sqrt(n) + time for Result.size() many put operations)</code>. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>((n == 0) || (n == 1)) if and only if Result.isEmpty()</code> <br>
	 * Postcondition: <code>(n == -1) implies (Result.size() == 1)</code> <br>
	 * Postcondition:
	 * <code>(n < 0) implies ((Result.get((byte) -1) != null) && (Result.get((byte) -1) == 1))</code>
	 * <br>
	 * Postcondition: <code>(0 <= n) implies (Result.get((byte) -1) == null)</code> <br>
	 * Postcondition:
	 * 
	 * <pre>
	 * <code>
	 * for (final Map.Entry&lt;Byte, Byte&gt; entry : Result.entrySet()) {
	 * 	assert ((entry.getKey() != null) && ((entry.getKey() == -1) || NumUtil.isPrimeSqrt(entry.getKey())));
	 * 	assert ((entry.getValue() != null) && (0 < entry.getValue()) && (entry.getValue() < 8));
	 * }
	 * </code>
	 * </pre>
	 * 
	 * Postcondition:
	 * 
	 * <pre>
	 * <code>
	 * if (n == Byte.MIN_VALUE) {
	 * 	assert ((Result.size() == 2) && (Result.get((byte) 2) == 7));
	 * } else if (!Result.isEmpty()) {
	 * 	long N = 1L;
	 * 	for (final Map.Entry&lt;Byte, Byte&gt; entry : Result.entrySet()) {
	 * 		N = Math.multiplyExact(N, MathUtil.powExact(entry.getKey(), entry.getValue()));
	 * 	}
	 * 	assert (n == N);
	 * }
	 * </code>
	 * </pre>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param hash
	 *            specifies whether the data structure used to store the factors, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code>
	 * 
	 * @param print
	 *            specifies whether the result should be printed to the standard output stream
	 * 
	 * @return The resulting Map object.
	 */
	public static Map<Byte, Byte> factorSqrt(byte n, boolean hash, boolean print) {
		final Map<Byte, Byte> result = hash ? new HashMap<Byte, Byte>() : new TreeMap<Byte, Byte>();

		// Factor -1 out of n if it's negative.
		if (n < 0) {
			result.put((byte) -1, (byte) 1);
			// Only print if requested.
			if (print) {
				System.out.print(n + " = (-1)^1");
			}

			// Handle the degenerate case where n's absolute value is not representable as a non-negative short.
			if ((n *= -1) == Byte.MIN_VALUE) { // i.e., -n == n < 0
				result.put((byte) 2, (byte) 7);
				// Only print if requested.
				if (print) {
					System.out.println(" * (2)^7");
				}
				return result;
			}
			// n != Byte.MIN_VALUE
			// i.e., 0 < n
		} else {
			// Only print if requested.
			if (print) {
				System.out.print(n + " = ");
			}
		}
		// 0 <= n

		// Handle the simple special case.
		if (n < 2) { // i.e., (n == 0) || (n == 1)
			// Only print if requested.
			if (print) {
				System.out.println(n);
			}
			return result;
		}
		// 2 <= n

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
		// Count the power of each prime which will fit in a byte since the largest power is at most 6.
		byte power = 0;
		/**
		 * Don't do <code>(n &= 1) == 0</code> since we need the value of <code>n</code> to remain
		 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
		 * will mutate <code>n</code>.
		 */
		// Check if 2 is a factor of n.
		if ((n & 1) == 0) { // i.e., n % 2 == 0
			// Update n and result.
			power = 0;
			do {
				n /= 2;
				++power;
			} while ((n & 1) == 0); // i.e., n % 2 == 0
			result.put((byte) 2, power);

			// Check if n has no more factors.
			if (n == 1) {
				// Only print if requested.
				return (print ? NumUtil.printFactorsByte(result, hash) : result);
			}
		}
		// Check if 3 is a factor of n.
		if (n % 3 == 0) {
			// Update n and result.
			power = 0;
			do {
				n /= 3;
				++power;
			} while (n % 3 == 0);
			result.put((byte) 3, power);

			// Check if n has no more factors.
			if (n == 1) {
				// Only print if requested.
				return (print ? NumUtil.printFactorsByte(result, hash) : result);
			}
		}
		/**
		 * Note that we are taking the square root of <code>n</code> after potentially having "pulled out"
		 * all of the <code>2</code> and the <code>3</code> factors. This means that the square root may not
		 * be applied to the original value of <code>n</code> but this is fine since all of the remaining
		 * prime divisors of the original value of <code>n</code> which are less than the square root, are
		 * still being considered.
		 */
		// Applying Math.floor before casting to short is unnecessary and it causes a large slow down.
		final byte bound = (byte) (((byte) Math.sqrt(n)) + 1); // 2 <= bound
		final byte maxI = (byte) (bound + 1); // 3 <= maxI
		for (byte i = 5; i < maxI; i += 4) {
			// Check if i (i.e., -1 (mod 6)) is a factor of n.
			if (n % i == 0) {
				// Update n and result.
				power = 0;
				do {
					n /= i;
					++power;
				} while (n % i == 0);
				result.put(i, power);

				// Check if n has no more factors.
				if (n == 1) {
					// Only print if requested.
					return (print ? NumUtil.printFactorsByte(result, hash) : result);
				}
			}

			/**
			 * It's fine to do <code>i += 2</code> instead of <code>i + 2</code> since we don't need the value
			 * of <code>i</code> to remain unchanged but quite the opposite. We have actually separated the
			 * incrementation of <code>i</code> by <code>6</code>, into an incrementation by <code>2</code> and
			 * an incrementation by <code>4</code> due to the need to add <code>2</code> to <code>i</code> at
			 * this point and the fact that <code>+=</code> is faster than <code>+</code> due to it not creating
			 * a temporary. Note that the difference is the <code>+=</code> instead of the <code>+</code> which
			 * will mutate <code>i</code>.
			 */
			// Check if i + 2 (i.e., 1 (mod 6)) is a factor of n.
			if (n % (i += 2) == 0) {
				// Update n and result.
				power = 0;
				do {
					n /= i;
					++power;
				} while (n % i == 0);
				result.put(i, power);

				// Check if n has no more factors.
				if (n == 1) {
					// Only print if requested.
					return (print ? NumUtil.printFactorsByte(result, hash) : result);
				}
			}
		}
		/**
		 * For all <code>n</code>, <code>n</code> can have at most one factor greater than
		 * <code>sqrt(n)</code>. <br>
		 * <br>
		 * 
		 * Therefore, due to the above loop, we know that <code>n</code> is a prime and a divisor of the
		 * original value of <code>n</code> at this point.
		 */
		result.put(n, (byte) 1);
		// Only print if requested.
		return (print ? NumUtil.printFactorsByte(result, hash) : result);
	}

	/**
	 * Runtime is in <code>O(sqrt(n) + time for Result.size() many put operations)</code>. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>((n == 0) || (n == 1)) if and only if Result.isEmpty()</code> <br>
	 * Postcondition: <code>(n == -1) implies (Result.size() == 1)</code> <br>
	 * Postcondition:
	 * <code>(n < 0) implies ((Result.get((byte) -1) != null) && (Result.get((byte) -1) == 1))</code>
	 * <br>
	 * Postcondition: <code>(0 <= n) implies (Result.get((byte) -1) == null)</code> <br>
	 * Postcondition:
	 * 
	 * <pre>
	 * <code>
	 * for (final Map.Entry&lt;Byte, Byte&gt; entry : Result.entrySet()) {
	 * 	assert ((entry.getKey() != null) && ((entry.getKey() == -1) || NumUtil.isPrimeSqrt(entry.getKey())));
	 * 	assert ((entry.getValue() != null) && (0 < entry.getValue()) && (entry.getValue() < 8));
	 * }
	 * </code>
	 * </pre>
	 * 
	 * Postcondition:
	 * 
	 * <pre>
	 * <code>
	 * if (n == Byte.MIN_VALUE) {
	 * 	assert ((Result.size() == 2) && (Result.get((byte) 2) == 7));
	 * } else if (!Result.isEmpty()) {
	 * 	long N = 1L;
	 * 	for (final Map.Entry&lt;Byte, Byte&gt; entry : Result.entrySet()) {
	 * 		N = Math.multiplyExact(N, MathUtil.powExact(entry.getKey(), entry.getValue()));
	 * 	}
	 * 	assert (n == N);
	 * }
	 * </code>
	 * </pre>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param hash
	 *            specifies whether the data structure used to store the factors, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code>
	 * 
	 * @return <code>NumUtil.factorSqrt(n, hash, false)</code>.
	 */
	public static Map<Byte, Byte> factorSqrt(byte n, boolean hash) {
		return NumUtil.factorSqrt(n, hash, false);
	}

	/**
	 * Runtime is in <code>O(sqrt(n) + time for Result.size() many put operations)</code>. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>((n == 0) || (n == 1)) if and only if Result.isEmpty()</code> <br>
	 * Postcondition: <code>(n == -1) implies (Result.size() == 1)</code> <br>
	 * Postcondition:
	 * <code>(n < 0) implies ((Result.get((byte) -1) != null) && (Result.get((byte) -1) == 1))</code>
	 * <br>
	 * Postcondition: <code>(0 <= n) implies (Result.get((byte) -1) == null)</code> <br>
	 * Postcondition:
	 * 
	 * <pre>
	 * <code>
	 * for (final Map.Entry&lt;Byte, Byte&gt; entry : Result.entrySet()) {
	 * 	assert ((entry.getKey() != null) && ((entry.getKey() == -1) || NumUtil.isPrimeSqrt(entry.getKey())));
	 * 	assert ((entry.getValue() != null) && (0 < entry.getValue()) && (entry.getValue() < 8));
	 * }
	 * </code>
	 * </pre>
	 * 
	 * Postcondition:
	 * 
	 * <pre>
	 * <code>
	 * if (n == Byte.MIN_VALUE) {
	 * 	assert ((Result.size() == 2) && (Result.get((byte) 2) == 7));
	 * } else if (!Result.isEmpty()) {
	 * 	long N = 1L;
	 * 	for (final Map.Entry&lt;Byte, Byte&gt; entry : Result.entrySet()) {
	 * 		N = Math.multiplyExact(N, MathUtil.powExact(entry.getKey(), entry.getValue()));
	 * 	}
	 * 	assert (n == N);
	 * }
	 * </code>
	 * </pre>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>NumUtil.factorSqrt(n, false)</code>.
	 */
	public static Map<Byte, Byte> factorSqrt(byte n) {
		return NumUtil.factorSqrt(n, false);
	}

	/**
	 * Perform Pollard's <code>p - 1</code> Algorithm on all integers <code>k</code> in
	 * <code>[begin, end)</code> (i.e., check for a non-trivial divisor of <code>n</code> by checking
	 * <code>gcd(base<sup>k!</sup> - 1 (mod n), n)</code> for integer <code>k</code> in
	 * <code>[begin, end)</code>) in <code>O((end - begin) * lg(n)) time</code>. <br>
	 * Precondition: <code>4 < n</code> <br>
	 * Precondition: <code>(n % 2 != 0) && (n % 3 != 0)</code> <br>
	 * Precondition: <code>gcd(base, n) == 1</code> <br>
	 * Precondition: <code>(0 <= begin) && (begin < end)</code> <br>
	 * Precondition: <code>(0 < |base_to_begin_factorial|) && (|base_to_begin_factorial| < n)</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param begin
	 *            the given begin power
	 * 
	 * @param end
	 *            the given end power
	 * 
	 * @param base_to_begin_factorial
	 *            <code>B</code> where <code>B (mod n) == base<sup>begin!</sup> (mod n)</code>
	 * 
	 * @return A non-trivial divisor of <code>n</code> or <code>null</code> if no such divisor can be
	 *         found using Pollard's <code>p - 1</code> Algorithm.
	 */
	protected static Long divisorPMinusOneFixedInput(long n, long begin, long end, long base_to_begin_factorial) {
		/**
		 * No need to do <code>long d = (base_to_begin_factorial - 1L) % n;</code> since by the precondition
		 * on <code>base_to_begin_factorial</code>, we know that
		 * <code>(-n + 1 <= base_to_begin_factorial <= n - 1)</code>. Therefore,
		 * <code>(-n <= base_to_begin_factorial - 1 <= n - 2)</code> and so if <code>d < 0</code>, then
		 * <code>(-n <= base_to_begin_factorial - 1 <= -1)</code> which means that
		 * <code>(0 <= base_to_begin_factorial - 1 + n <= n - 1)</code> as required.
		 */
		long d = base_to_begin_factorial - 1L, gcd = 0L;
		// Fix d to be in [0, n - 1] \cap \doubleZ.
		if (d < 0L) {
			d += n;
		}
		/**
		 * <code>d</code> cannot be <code>n - 1</code> at this point since that can only happen if
		 * <code>base_to_begin_factorial == 0</code> which can never happen due to the preconditions on
		 * <code>base</code> and <code>base_to_begin_factorial</code> (i.e., <code>gcd(base, n) == 1</code>
		 * and <code>(0 < |base_to_begin_factorial|) && (|base_to_begin_factorial| < n)</code> which means
		 * that <code>base</code> is an element of <code>(\doubleZ / n \doubleZ)*</code> and so all of its
		 * powers are non-zero).
		 */
		// Check for a non-trivial divisor of n.
		if (1L < d) { // i.e., (d != 0) && (d != 1)
			// i.e., gcd(d, n) is non-trivial.
			if ((gcd = MathUtil.gcdFixedInput(d, n)) != 1L) {
				return gcd;
			}
		}

		// Iteratively compute base to the power of i! in mod n and check for a non-trivial divisor of n.
		for (long i = begin + 1L, base_to_i_factorial = MathUtil.modMinFixedInput(base_to_begin_factorial,
				n); i != end; ++i) {
			// Update base_to_i_factorial.
			if ((base_to_i_factorial = MathUtil.modPowFixedInput(base_to_i_factorial, i, n)) == 1L) {
				/**
				 * Note that <code>MathUtil.modMinFixedInput</code> (and as a result
				 * <code>MathUtil.modPowFixedInput</code>) return <code>1</code> instead of <code>1 - n</code> for
				 * all <code>1 < n</code> so the check is fine and we do not need to fix the result of
				 * <code>MathUtil.modPowFixedInput</code> to be in <code>[0, n - 1] \cap \doubleZ</code> by adding
				 * <code>n</code> to it if it's negative.
				 */
				break;
			}
			// base_to_i_factorial != 1
			/**
			 * We can also handle <code>base_to_i_factorial == -1L (i.e., -1 (mod n))</code> here, but it will
			 * automatically be handled by the above check after at most 2 iterations of the loop (since either
			 * <code>i + 1</code> or <code>i + 2</code> is even at which point <code>base_to_i_factorial</code>
			 * will become <code>1</code>). Therefore, not checking the <code>-1</code> case here, will cause
			 * slightly more work to be done while checking it will add an extra check to every iteration of the
			 * loop.
			 */

			// Fix d to be in [1, n - 1] \cap \doubleZ.
			if ((d = base_to_i_factorial - 1L) < 0L) {
				d += n;
			}
			/**
			 * <code>d</code> cannot be <code>n - 1</code> at this point since that can only happen if
			 * <code>base_to_i_factorial == 0</code> which can never happen due to the preconditions on
			 * <code>base</code> and <code>base_to_begin_factorial</code> (i.e., <code>gcd(base, n) == 1</code>
			 * and <code>(0 < |base_to_begin_factorial|) && (|base_to_begin_factorial| < n)</code> which means
			 * that <code>base</code> is an element of <code>(\doubleZ / n \doubleZ)*</code> and so all of its
			 * powers are non-zero).
			 */
			// Check for a non-trivial divisor of n.
			if (d != 1L) { // i.e., d != 1
				// i.e., gcd(d, n) is non-trivial.
				if ((gcd = MathUtil.gcdFixedInput(d, n)) != 1L) {
					return gcd;
				}
			}
		}
		/*
		 * No value of base_to_i_factorial for i in [begin, end) \cap \doubleZ resulted in a non-trivial
		 * divisor of n.
		 */
		return null;
	}

	/**
	 * Perform Pollard's <code>p - 1</code> Algorithm on all integers <code>k</code> in
	 * <code>[begin, end)</code> (i.e., check for a non-trivial divisor of <code>n</code> by checking
	 * <code>gcd(base<sup>k!</sup> - 1 (mod n), n)</code> for integer <code>k</code> in
	 * <code>[begin, end)</code>) in <code>O(end * lg(n)) time</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param base
	 *            the given base
	 * 
	 * @param begin
	 *            the given begin power
	 * 
	 * @param end
	 *            the given end power
	 * 
	 * @return A non-trivial divisor of <code>n</code> or <code>null</code> if no such divisor can be
	 *         found using Pollard's <code>p - 1</code> Algorithm.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>n <= 0</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(end < begin) || (begin < 0)
	 *             || (base == 0 (mod n)) || (base == 1 (mod n)) || (base == -1 (mod n))</code>
	 */
	public static Long divisorPMinusOne(long n, long base, long begin, long end)
			throws InvalidModulusException, IllegalArgumentException {
		if (n < 1L) {
			throw new InvalidModulusException();
		} else if ((end < begin) || (begin < 0L)) {
			throw new IllegalArgumentException();
		}
		// (1 <= n) && (begin <= end) && (0 <= begin)
		// i.e., (0 < n) && (0 <= begin) && (begin <= end)

		// Handle the <code>n == 1</code> case.
		if (n == 1L) {
			return 1L;
		}
		// n != 1
		// i.e., 2 <= n

		/**
		 * <pre>
		 * <code>
		 * Any integer n, is one of 0, 1, 2, 3, 4, or 5 (mod 6).
		 * However, if n is greater than 3, then it can only be a prime if it is 1 or 5 (mod 6) since otherwise
		 * it would be divisible by 2 or 3 (or both in the case of 0 (mod 6)).
		 * </code>
		 * </pre>
		 * 
		 * Don't do <code>(n &= 1L) == 0L</code> since we need the value of <code>n</code> to remain
		 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
		 * will mutate <code>n</code>.
		 */
		// Check if 2 is a factor of n.
		if ((n & 1L) == 0L) { // i.e., n % 2 == 0
			return 2L;
		} else if (n % 3L == 0L) {
			return 3L;
		}
		// (n % 2 != 0) && (n % 3 != 0)
		// i.e., (4 < n) && (n % 2 != 0) && (n % 3 != 0)

		// Fix base to be in [0, n - 1] \cap \doubleZ and handle the invalid-base special cases.
		if ((base %= n) < 0L) {
			base += n;
		}
		if ((base < 2L) || (base == n - 1L)) { // i.e., (base == 0) || (base == 1) || (base == -1 (mod n))
			throw new IllegalArgumentException();
		}
		// (2 <= base) && (base <= n - 2)
		// i.e., (1 < base) && (base < n - 1)

		// Check whether base and n have any common divisors by checking the gcd.
		if (3L < base) { // i.e., (base != 2) && (base != 3)
			// i.e., gcd(base, n) is non-trivial.
			final long gcd = MathUtil.gcdFixedInput(base, n);
			if (gcd != 1L) {
				return gcd;
			}
		}
		// gcd(base, n) == 1

		// Handle the <code>begin == end</code> case.
		if (begin == end) {
			return null;
		}
		// begin != end
		// i.e., begin < end

		// Compute <code>base<sup>begin!</sup> (mod n)</code>.
		long base_to_begin_factorial = base;
		if (1L < begin) { // i.e., 1 < begin!
			// Fix base_to_begin_factorial to be in [-n / 2, n / 2] \cap \doubleZ.
			base_to_begin_factorial = MathUtil.modMinFixedInput(base_to_begin_factorial, n);
			final long maxI = begin + 1L; // 3 <= maxI
			for (long i = 2L; i != maxI; ++i) {
				if ((base_to_begin_factorial = MathUtil.modPowFixedInput(base_to_begin_factorial, i, n)) == 1L) {
					/**
					 * Note that <code>MathUtil.modMinFixedInput</code> (and as a result
					 * <code>MathUtil.modPowFixedInput</code>) return <code>1</code> instead of <code>1 - n</code> for
					 * all <code>1 < n</code> so the check is fine and we do not need to fix the result of
					 * <code>MathUtil.modPowFixedInput</code> to be in <code>[0, n - 1] \cap \doubleZ</code> by adding
					 * <code>n</code> to it if it's negative.
					 */
					return null;
				}
				/**
				 * We can also handle <code>base_to_begin_factorial == -1L (i.e., -1 (mod n))</code> here, but it
				 * will automatically be handled by the above check after at most 2 iterations of the loop (since
				 * either <code>i + 1</code> or <code>i + 2</code> is even at which point
				 * <code>base_to_begin_factorial</code> will become <code>1</code>). Therefore, not checking the
				 * <code>-1</code> case here, will cause slightly more work to be done while checking it will add an
				 * extra check to every iteration of the loop.
				 */
			}
		}
		return NumUtil.divisorPMinusOneFixedInput(n, begin, end, base_to_begin_factorial);
	}

	/**
	 * Perform Pollard's <code>p - 1</code> Algorithm on all integers <code>k</code> in
	 * <code>[0, end)</code> (i.e., check for a non-trivial divisor of <code>n</code> by checking
	 * <code>gcd(base<sup>k!</sup> - 1 (mod n), n)</code> for integer <code>k</code> in
	 * <code>[0, end)</code>) in <code>O(end * lg(n)) time</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param base
	 *            the given base
	 * 
	 * @param end
	 *            the given end power
	 * 
	 * @return <code>NumUtil.divisorPMinusOne(n, base, 0L, end)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>n <= 0</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(end < 0)
	 *             || (base == 0 (mod n)) || (base == 1 (mod n)) || (base == -1 (mod n))</code>
	 */
	public static Long divisorPMinusOne(long n, long base, long end)
			throws InvalidModulusException, IllegalArgumentException {
		return NumUtil.divisorPMinusOne(n, base, 0L, end);
	}

	/**
	 * Perform Pollard's <code>p - 1</code> Algorithm on all integers <code>k</code> in
	 * <code>[0, end)</code> (i.e., check for a non-trivial divisor of <code>n</code> by checking
	 * <code>gcd(NumUtil.P_MINUS_ONE_DEFAULT_BASE<sup>k!</sup> - 1 (mod n), n)</code> for integer
	 * <code>k</code> in <code>[0, end)</code>) in <code>O(end * lg(n)) time</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param end
	 *            the given end power
	 * 
	 * @return <code>NumUtil.divisorPMinusOne(n, NumUtil.P_MINUS_ONE_DEFAULT_BASE, end)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>n <= 0</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>end < 0</code>
	 */
	public static Long divisorPMinusOne(long n, long end) throws InvalidModulusException, IllegalArgumentException {
		return NumUtil.divisorPMinusOne(n, NumUtil.P_MINUS_ONE_DEFAULT_BASE, end);
	}

	/**
	 * Perform Pollard's <code>p - 1</code> Algorithm on all integers <code>k</code> in
	 * <code>[0, NumUtil.P_MINUS_ONE_DEFAULT_END)</code> (i.e., check for a non-trivial divisor of
	 * <code>n</code> by checking
	 * <code>gcd(NumUtil.P_MINUS_ONE_DEFAULT_BASE<sup>k!</sup> - 1 (mod n), n)</code> for integer
	 * <code>k</code> in <code>[0, NumUtil.P_MINUS_ONE_DEFAULT_END)</code>) in
	 * <code>O(NumUtil.P_MINUS_ONE_DEFAULT_END * lg(n)) time</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>NumUtil.divisorPMinusOne(n, NumUtil.P_MINUS_ONE_DEFAULT_END)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>n <= 0</code>
	 */
	public static Long divisorPMinusOne(long n) throws InvalidModulusException {
		return NumUtil.divisorPMinusOne(n, NumUtil.P_MINUS_ONE_DEFAULT_END);
	}

	/**
	 * Perform Pollard's <code>p - 1</code> Algorithm on all integers <code>k</code> in
	 * <code>[begin, end)</code> (i.e., check for a non-trivial divisor of <code>n</code> by checking
	 * <code>gcd(base<sup>k!</sup> - 1 (mod n), n)</code> for integer <code>k</code> in
	 * <code>[begin, end)</code>) in <code>O(end * lg(n)) time</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param base
	 *            the given base
	 * 
	 * @param begin
	 *            the given begin power
	 * 
	 * @param end
	 *            the given end power
	 * 
	 * @return A non-trivial divisor of <code>n</code> or <code>null</code> if no such divisor can be
	 *         found using Pollard's <code>p - 1</code> Algorithm.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>n <= 0</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(end < begin) || (begin < 0)
	 *             || (base == 0 (mod n)) || (base == 1 (mod n)) || (base == -1 (mod n))</code>
	 */
	public static Integer divisorPMinusOne(int n, int base, int begin, int end)
			throws InvalidModulusException, IllegalArgumentException {
		final Long result = NumUtil.divisorPMinusOne((long) n, (long) base, (long) begin, (long) end);
		return ((result == null) ? null : result.intValue());
	}

	/**
	 * Perform Pollard's <code>p - 1</code> Algorithm on all integers <code>k</code> in
	 * <code>[0, end)</code> (i.e., check for a non-trivial divisor of <code>n</code> by checking
	 * <code>gcd(base<sup>k!</sup> - 1 (mod n), n)</code> for integer <code>k</code> in
	 * <code>[0, end)</code>) in <code>O(end * lg(n)) time</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param base
	 *            the given base
	 * 
	 * @param end
	 *            the given end power
	 * 
	 * @return <code>NumUtil.divisorPMinusOne(n, base, 0, end)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>n <= 0</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(end < 0)
	 *             || (base == 0 (mod n)) || (base == 1 (mod n)) || (base == -1 (mod n))</code>
	 */
	public static Integer divisorPMinusOne(int n, int base, int end)
			throws InvalidModulusException, IllegalArgumentException {
		return NumUtil.divisorPMinusOne(n, base, 0, end);
	}

	/**
	 * Perform Pollard's <code>p - 1</code> Algorithm on all integers <code>k</code> in
	 * <code>[0, end)</code> (i.e., check for a non-trivial divisor of <code>n</code> by checking
	 * <code>gcd(NumUtil.P_MINUS_ONE_DEFAULT_BASE<sup>k!</sup> - 1 (mod n), n)</code> for integer
	 * <code>k</code> in <code>[0, end)</code>) in <code>O(end * lg(n)) time</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param end
	 *            the given end power
	 * 
	 * @return <code>NumUtil.divisorPMinusOne(n, NumUtil.P_MINUS_ONE_DEFAULT_BASE, end)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>n <= 0</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>end < 0</code>
	 */
	public static Integer divisorPMinusOne(int n, int end) throws InvalidModulusException, IllegalArgumentException {
		return NumUtil.divisorPMinusOne(n, NumUtil.P_MINUS_ONE_DEFAULT_BASE, end);
	}

	/**
	 * Perform Pollard's <code>p - 1</code> Algorithm on all integers <code>k</code> in
	 * <code>[0, NumUtil.P_MINUS_ONE_DEFAULT_END)</code> (i.e., check for a non-trivial divisor of
	 * <code>n</code> by checking
	 * <code>gcd(NumUtil.P_MINUS_ONE_DEFAULT_BASE<sup>k!</sup> - 1 (mod n), n)</code> for integer
	 * <code>k</code> in <code>[0, NumUtil.P_MINUS_ONE_DEFAULT_END)</code>) in
	 * <code>O(NumUtil.P_MINUS_ONE_DEFAULT_END * lg(n)) time</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>NumUtil.divisorPMinusOne(n, NumUtil.P_MINUS_ONE_DEFAULT_END)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>n <= 0</code>
	 */
	public static Integer divisorPMinusOne(int n) throws InvalidModulusException {
		return NumUtil.divisorPMinusOne(n, NumUtil.P_MINUS_ONE_DEFAULT_END);
	}

	/**
	 * Perform Pollard's <code>p - 1</code> Algorithm on all integers <code>k</code> in
	 * <code>[begin, end)</code> (i.e., check for a non-trivial divisor of <code>n</code> by checking
	 * <code>gcd(base<sup>k!</sup> - 1 (mod n), n)</code> for integer <code>k</code> in
	 * <code>[begin, end)</code>) in <code>O(end * lg(n)) time</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param base
	 *            the given base
	 * 
	 * @param begin
	 *            the given begin power
	 * 
	 * @param end
	 *            the given end power
	 * 
	 * @return A non-trivial divisor of <code>n</code> or <code>null</code> if no such divisor can be
	 *         found using Pollard's <code>p - 1</code> Algorithm.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>n <= 0</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(end < begin) || (begin < 0)
	 *             || (base == 0 (mod n)) || (base == 1 (mod n)) || (base == -1 (mod n))</code>
	 */
	public static Short divisorPMinusOne(short n, short base, short begin, short end)
			throws InvalidModulusException, IllegalArgumentException {
		final Long result = NumUtil.divisorPMinusOne((long) n, (long) base, (long) begin, (long) end);
		return ((result == null) ? null : result.shortValue());
	}

	/**
	 * Perform Pollard's <code>p - 1</code> Algorithm on all integers <code>k</code> in
	 * <code>[0, end)</code> (i.e., check for a non-trivial divisor of <code>n</code> by checking
	 * <code>gcd(base<sup>k!</sup> - 1 (mod n), n)</code> for integer <code>k</code> in
	 * <code>[0, end)</code>) in <code>O(end * lg(n)) time</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param base
	 *            the given base
	 * 
	 * @param end
	 *            the given end power
	 * 
	 * @return <code>NumUtil.divisorPMinusOne(n, base, (short) 0, end)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>n <= 0</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(end < 0)
	 *             || (base == 0 (mod n)) || (base == 1 (mod n)) || (base == -1 (mod n))</code>
	 */
	public static Short divisorPMinusOne(short n, short base, short end)
			throws InvalidModulusException, IllegalArgumentException {
		return NumUtil.divisorPMinusOne(n, base, (short) 0, end);
	}

	/**
	 * Perform Pollard's <code>p - 1</code> Algorithm on all integers <code>k</code> in
	 * <code>[0, end)</code> (i.e., check for a non-trivial divisor of <code>n</code> by checking
	 * <code>gcd(NumUtil.P_MINUS_ONE_DEFAULT_BASE<sup>k!</sup> - 1 (mod n), n)</code> for integer
	 * <code>k</code> in <code>[0, end)</code>) in <code>O(end * lg(n)) time</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param end
	 *            the given end power
	 * 
	 * @return <code>NumUtil.divisorPMinusOne(n, NumUtil.P_MINUS_ONE_DEFAULT_BASE, end)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>n <= 0</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>end < 0</code>
	 */
	public static Short divisorPMinusOne(short n, short end) throws InvalidModulusException, IllegalArgumentException {
		return NumUtil.divisorPMinusOne(n, NumUtil.P_MINUS_ONE_DEFAULT_BASE, end);
	}

	/**
	 * Perform Pollard's <code>p - 1</code> Algorithm on all integers <code>k</code> in
	 * <code>[0, NumUtil.P_MINUS_ONE_DEFAULT_END)</code> (i.e., check for a non-trivial divisor of
	 * <code>n</code> by checking
	 * <code>gcd(NumUtil.P_MINUS_ONE_DEFAULT_BASE<sup>k!</sup> - 1 (mod n), n)</code> for integer
	 * <code>k</code> in <code>[0, NumUtil.P_MINUS_ONE_DEFAULT_END)</code>) in
	 * <code>O(NumUtil.P_MINUS_ONE_DEFAULT_END * lg(n)) time</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>NumUtil.divisorPMinusOne(n, NumUtil.P_MINUS_ONE_DEFAULT_END)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>n <= 0</code>
	 */
	public static Short divisorPMinusOne(short n) throws InvalidModulusException, IllegalArgumentException {
		return NumUtil.divisorPMinusOne(n, NumUtil.P_MINUS_ONE_DEFAULT_END);
	}

	/**
	 * Perform Pollard's <code>p - 1</code> Algorithm on all integers <code>k</code> in
	 * <code>[begin, end)</code> (i.e., check for a non-trivial divisor of <code>n</code> by checking
	 * <code>gcd(base<sup>k!</sup> - 1 (mod n), n)</code> for integer <code>k</code> in
	 * <code>[begin, end)</code>) in <code>O(end * lg(n)) time</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param base
	 *            the given base
	 * 
	 * @param begin
	 *            the given begin power
	 * 
	 * @param end
	 *            the given end power
	 * 
	 * @return A non-trivial divisor of <code>n</code> or <code>null</code> if no such divisor can be
	 *         found using Pollard's <code>p - 1</code> Algorithm.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>n <= 0</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(end < begin) || (begin < 0)
	 *             || (base == 0 (mod n)) || (base == 1 (mod n)) || (base == -1 (mod n))</code>
	 */
	public static Byte divisorPMinusOne(byte n, byte base, byte begin, byte end)
			throws InvalidModulusException, IllegalArgumentException {
		final Long result = NumUtil.divisorPMinusOne((long) n, (long) base, (long) begin, (long) end);
		return ((result == null) ? null : result.byteValue());
	}

	/**
	 * Perform Pollard's <code>p - 1</code> Algorithm on all integers <code>k</code> in
	 * <code>[0, end)</code> (i.e., check for a non-trivial divisor of <code>n</code> by checking
	 * <code>gcd(base<sup>k!</sup> - 1 (mod n), n)</code> for integer <code>k</code> in
	 * <code>[0, end)</code>) in <code>O(end * lg(n)) time</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param base
	 *            the given base
	 * 
	 * @param end
	 *            the given end power
	 * 
	 * @return <code>NumUtil.divisorPMinusOne(n, base, (byte) 0, end)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>n <= 0</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(end < 0)
	 *             || (base == 0 (mod n)) || (base == 1 (mod n)) || (base == -1 (mod n))</code>
	 */
	public static Byte divisorPMinusOne(byte n, byte base, byte end)
			throws InvalidModulusException, IllegalArgumentException {
		return NumUtil.divisorPMinusOne(n, base, (byte) 0, end);
	}

	/**
	 * Perform Pollard's <code>p - 1</code> Algorithm on all integers <code>k</code> in
	 * <code>[0, end)</code> (i.e., check for a non-trivial divisor of <code>n</code> by checking
	 * <code>gcd(NumUtil.P_MINUS_ONE_DEFAULT_BASE<sup>k!</sup> - 1 (mod n), n)</code> for integer
	 * <code>k</code> in <code>[0, end)</code>) in <code>O(end * lg(n)) time</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param end
	 *            the given end power
	 * 
	 * @return <code>NumUtil.divisorPMinusOne(n, NumUtil.P_MINUS_ONE_DEFAULT_BASE, end)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>n <= 0</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>end < 0</code>
	 */
	public static Byte divisorPMinusOne(byte n, byte end) throws InvalidModulusException, IllegalArgumentException {
		return NumUtil.divisorPMinusOne(n, NumUtil.P_MINUS_ONE_DEFAULT_BASE, end);
	}

	/**
	 * Perform Pollard's <code>p - 1</code> Algorithm on all integers <code>k</code> in
	 * <code>[0, NumUtil.P_MINUS_ONE_DEFAULT_END)</code> (i.e., check for a non-trivial divisor of
	 * <code>n</code> by checking
	 * <code>gcd(NumUtil.P_MINUS_ONE_DEFAULT_BASE<sup>k!</sup> - 1 (mod n), n)</code> for integer
	 * <code>k</code> in <code>[0, NumUtil.P_MINUS_ONE_DEFAULT_END)</code>) in
	 * <code>O(NumUtil.P_MINUS_ONE_DEFAULT_END * lg(n)) time</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>NumUtil.divisorPMinusOne(n, NumUtil.P_MINUS_ONE_DEFAULT_END)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>n <= 0</code>
	 */
	public static Byte divisorPMinusOne(byte n) throws InvalidModulusException {
		return NumUtil.divisorPMinusOne(n, NumUtil.P_MINUS_ONE_DEFAULT_END);
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
		if (n < 4L) {
			/**
			 * <code>phi(n) == 0</code> if <code>n < 1</code> (since <code>[1, n] == emptyset</code>) <br>
			 * <code>phi(n) == 1</code> if <code>n == 1</code> (since
			 * <code>([1, n] == { 1 }) && (gcd(1, 1) == 1)</code>) <br>
			 * <code>phi(n) == n - 1</code> if <code>(n == 2) || (n == 3)</code> (since <code>n</code> is prime)
			 * <br>
			 * <br>
			 * 
			 *
			 * It's fine to do <code>--n</code> instead of <code>n - 1</code> since we don't need the value of
			 * <code>n</code> to remain unchanged at that point.
			 */
			return ((n < 1L) ? 0L : ((n == 1L) ? 1L : (--n)));
		}
		// 4 <= n

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
		 * <br>
		 * <br>
		 * 
		 * Note that we are taking the square root of <code>n</code> after potentially having "pulled out"
		 * all of the <code>2</code> and the <code>3</code> factors. This means that the square root may not
		 * be applied to the original value of <code>n</code> but this is fine since all of the remaining
		 * prime divisors of the original value of <code>n</code> which are less than the square root, are
		 * still being considered.
		 */
		// Applying Math.floor before casting to long is unnecessary and it causes a large slow down.
		final long bound = ((long) Math.sqrt(n)) + 1L; // 3 <= bound
		final long maxI = bound + 1L; // 4 <= maxI
		for (long i = 5L; i < maxI; i += 4L) {
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
			 * It's fine to do <code>i += 2</code> instead of <code>i + 2</code> since we don't need the value
			 * of <code>i</code> to remain unchanged but quite the opposite. We have actually separated the
			 * incrementation of <code>i</code> by <code>6</code>, into an incrementation by <code>2</code> and
			 * an incrementation by <code>4</code> due to the need to add <code>2</code> to <code>i</code> at
			 * this point and the fact that <code>+=</code> is faster than <code>+</code> due to it not creating
			 * a temporary. Note that the difference is the <code>+=</code> instead of the <code>+</code> which
			 * will mutate <code>i</code>.
			 */
			// Check if i + 2 (i.e., 1 (mod 6)) is a factor of n.
			if (n % (i += 2L) == 0L) {
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
		}
		/**
		 * For all <code>n</code>, <code>n</code> can have at most one factor greater than
		 * <code>sqrt(n)</code>. <br>
		 * <br>
		 * 
		 * Therefore, due to the above loop, we know that <code>n</code> is a prime and a divisor of the
		 * original value of <code>n</code> at this point.
		 */
		return (result -= (result / n));
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
		return ((int) NumUtil.eulerTotientSqrt((long) n));
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
		return ((short) NumUtil.eulerTotientSqrt((long) n));
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
		return ((byte) NumUtil.eulerTotientSqrt((long) n));
	}
}
