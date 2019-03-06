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
	 * First prime which is also the only prime that is 2 (mod 6).
	 */
	public static final byte FIRST_PRIME = 2;

	/**
	 * Second prime which is also the only prime that is 3 (mod 6).
	 */
	public static final byte SECOND_PRIME = 3;

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
	 * First safe prime.
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
	 * Runtime is in <code>O(sqrt(n))</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>true</code> if and only if the given number is prime.
	 */
	public static boolean isPrimeSqrt(long n) {
		if (n < 2L) {
			// The first prime is 2.
			return false;
		} else if (n < 4L) { // i.e., (n == 2) || (n == 3)
			// The only primes that are not 1 or 5 (mod 6), are 2 and 3.
			return true;
		} else if (((n & 1L) == 0L) || (n % 3L == 0L)) {
			/**
			 * Don't do <code>(n &= 1L) == 0L</code> since we need the value of <code>n</code> to remain
			 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
			 * will mutate <code>n</code>.
			 */
			// n > 3 and is divisible by 2 or 3 (or both).
			return false;
		}
		// n is an odd integer greater than 3 and not divisible by 3.

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
		 * Therefore, due to the above loop, we know that <code>n</code> is prime at this point.
		 */
		return true;
	}

	/**
	 * Runtime is in <code>O(sqrt(n))</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>true</code> if and only if the given number is prime.
	 */
	public static boolean isPrimeSqrt(int n) {
		return PrimeUtil.isPrimeSqrt((long) n);
	}

	/**
	 * Runtime is in <code>O(sqrt(n))</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>true</code> if and only if the given number is prime.
	 */
	public static boolean isPrimeSqrt(short n) {
		return PrimeUtil.isPrimeSqrt((long) n);
	}

	/**
	 * Runtime is in <code>O(sqrt(n))</code>.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>true</code> if and only if the given number is prime.
	 */
	public static boolean isPrimeSqrt(byte n) {
		return PrimeUtil.isPrimeSqrt((long) n);
	}
}
