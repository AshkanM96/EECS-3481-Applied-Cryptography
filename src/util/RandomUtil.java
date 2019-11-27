package util;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility random methods in addition to Java's Random class.
 * 
 * @author Ashkan Moatamed
 */
public class RandomUtil {
	/**
	 * No dependencies.
	 */

	/**
	 * <code>BigInteger.valueOf(Long.MIN_VALUE)</code>.
	 */
	public static final BigInteger LONG_MIN_VALUE = BigInteger.valueOf(Long.MIN_VALUE); // -2^63

	/**
	 * <code>BigInteger.valueOf(Long.MAX_VALUE)</code>.
	 */
	public static final BigInteger LONG_MAX_VALUE = BigInteger.valueOf(Long.MAX_VALUE); // 2^63 - 1

	/**
	 * <code>RandomUtil.LONG_MAX_VALUE.add(BigInteger.ONE)</code>.
	 */
	public static final BigInteger LONG_MAX_VALUE_PLUS_1 = RandomUtil.LONG_MIN_VALUE.negate(); // 2^63

	/**
	 * Prevent instantiation.
	 */
	private RandomUtil() {
		// Empty by design.
	}

	/**
	 * @param bound
	 *            the given upper bound
	 * 
	 * @param prng
	 *            source of random bits used to compute the new BigInteger
	 * 
	 * @return A pseudorandom <code>BigInteger</code> value uniformly distributed in
	 *         <code>[0, bound)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>bound == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>bound <= 0</code>
	 */
	public static BigInteger nextBigInt(BigInteger bound, Random prng)
			throws NullPointerException, IllegalArgumentException {
		if (bound.signum() != 1) { // i.e., bound <= 0
			throw new IllegalArgumentException();
		}
		// 0 < bound
		if (prng == null) {
			prng = ThreadLocalRandom.current();
		}

		/*
		 * Generate a random integer in [0, bound - 1] uniformly at random by randomly generating integers
		 * uniformly distributed in [0, 2^bitLength - 1] and then rejecting the ones that are greater than
		 * bound - 1 (i.e., greater than or equal to bound).
		 */
		final int bitLength = bound.bitLength();
		BigInteger result = null;
		do {
			result = new BigInteger(bitLength, prng);
		} while (bound.compareTo(result) <= 0);
		return result;
	}

	/**
	 * @param bound
	 *            the given upper bound
	 * 
	 * @return <code>RandomUtil.nextBigInt(bound, ThreadLocalRandom.current())</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>bound == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>bound <= 0</code>
	 */
	public static BigInteger nextBigInt(BigInteger bound) throws NullPointerException, IllegalArgumentException {
		// Cannot pass null as the second argument since it would be ambiguous.
		return RandomUtil.nextBigInt(bound, ThreadLocalRandom.current());
	}

	/**
	 * @param begin
	 *            the given lower bound
	 * 
	 * @param end
	 *            the given upper bound
	 * 
	 * @param prng
	 *            source of random bits used to compute the new BigInteger
	 * 
	 * @return A pseudorandom <code>BigInteger</code> value uniformly distributed in
	 *         <code>[begin, end)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(begin == null) || (end == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>end <= begin</code>
	 */
	public static BigInteger nextBigInt(BigInteger begin, BigInteger end, Random prng)
			throws NullPointerException, IllegalArgumentException {
		if (0 <= begin.compareTo(end)) { // i.e., end <= begin
			throw new IllegalArgumentException();
		}
		// begin < end
		if (prng == null) {
			prng = ThreadLocalRandom.current();
		}

		/*
		 * Generate a random integer in [0, bound - 1] uniformly at random by randomly generating integers
		 * uniformly distributed in [0, 2^bitLength - 1] and then rejecting the ones that are greater than
		 * bound - 1 (i.e., greater than or equal to bound).
		 */
		final BigInteger bound = end.subtract(begin); // 0 < bound == end - begin
		final int bitLength = bound.bitLength();
		BigInteger result = null;
		do {
			result = new BigInteger(bitLength, prng);
		} while (bound.compareTo(result) <= 0);
		// Finally, add begin to result to get a random integer in [begin, end - 1].
		return result.add(begin);
	}

	/**
	 * @param begin
	 *            the given lower bound
	 * 
	 * @param end
	 *            the given upper bound
	 * 
	 * @return <code>RandomUtil.nextBigInt(begin, end, null)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(begin == null) || (end == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>end <= begin</code>
	 */
	public static BigInteger nextBigInt(BigInteger begin, BigInteger end)
			throws NullPointerException, IllegalArgumentException {
		return RandomUtil.nextBigInt(begin, end, null);
	}

	/**
	 * @param begin
	 *            the given lower bound
	 * 
	 * @param end
	 *            the given upper bound
	 * 
	 * @param prng
	 *            source of random bits used to compute the new int
	 * 
	 * @return A pseudorandom <code>int</code> value uniformly distributed in <code>[begin, end)</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>end <= begin</code>
	 */
	public static int nextInt(int begin, int end, Random prng) throws IllegalArgumentException {
		if (end <= begin) {
			throw new IllegalArgumentException();
		}
		// begin < end
		if (prng == null) {
			prng = ThreadLocalRandom.current();
		}

		// Generate a random integer in [0, bound - 1] uniformly at random.
		final long bound = ((long) end) - begin; // 0 < bound
		long result = (bound <= Integer.MAX_VALUE) ? prng.nextInt((int) bound)
				: RandomUtil.nextBigInt(BigInteger.valueOf(bound), prng).longValue();
		// Finally, add begin to result to get a random integer in [begin, end - 1].
		/**
		 * It's fine to do <code>result += begin</code> instead of <code>result + begin</code> since we
		 * don't need the value of <code>result</code> to remain unchanged. Furthermore, note that
		 * <code>result + begin</code> is guaranteed to fit in an int since it is an element of
		 * <code>[begin, end - 1] \cap \doubleZ</code>.
		 */
		return ((int) (result += begin));
	}

	/**
	 * @param begin
	 *            the given lower bound
	 * 
	 * @param end
	 *            the given upper bound
	 * 
	 * @return <code>RandomUtil.nextInt(begin, end, null)</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>end <= begin</code>
	 */
	public static int nextInt(int begin, int end) throws IllegalArgumentException {
		return RandomUtil.nextInt(begin, end, null);
	}

	/**
	 * @param bound
	 *            the given upper bound
	 * 
	 * @param prng
	 *            source of random bits used to compute the new long
	 * 
	 * @return A pseudorandom <code>long</code> value uniformly distributed in <code>[0, bound)</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>bound <= 0</code>
	 */
	public static long nextLong(long bound, Random prng) throws IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a BigInteger construction.
		if (bound <= 0) {
			throw new IllegalArgumentException();
		}
		// 0 < bound
		if (prng == null) {
			prng = ThreadLocalRandom.current();
		}

		// Generate a random integer in [0, bound - 1] uniformly at random.
		return ((bound <= Integer.MAX_VALUE) ? prng.nextInt((int) bound)
				: RandomUtil.nextBigInt(BigInteger.valueOf(bound), prng).longValue());
	}

	/**
	 * @param bound
	 *            the given upper bound
	 * 
	 * @param prng
	 *            source of random bits used to compute the new long
	 * 
	 * @return <code>RandomUtil.nextLong(bound, null)</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>bound <= 0</code>
	 */
	public static long nextLong(long bound) throws IllegalArgumentException {
		return RandomUtil.nextLong(bound, null);
	}

	/**
	 * @param begin
	 *            the given lower bound
	 * 
	 * @param end
	 *            the given upper bound
	 * 
	 * @param prng
	 *            source of random bits used to compute the new long
	 * 
	 * @return A pseudorandom <code>long</code> value uniformly distributed in
	 *         <code>[begin, end)</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>end <= begin</code>
	 */
	public static long nextLong(long begin, long end, Random prng) throws IllegalArgumentException {
		// Even though the following is a repeated check, it'll save two BigInteger constructions.
		if (end <= begin) {
			throw new IllegalArgumentException();
		}
		// begin < end

		// Generate a random integer in [begin, end - 1] uniformly at random.
		return RandomUtil.nextBigInt(BigInteger.valueOf(begin), BigInteger.valueOf(end), prng).longValue();
	}

	/**
	 * @param begin
	 *            the given lower bound
	 * 
	 * @param end
	 *            the given upper bound
	 * 
	 * @return <code>RandomUtil.nextLong(begin, end, null)</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>end <= begin</code>
	 */
	public static long nextLong(long begin, long end) throws IllegalArgumentException {
		return RandomUtil.nextLong(begin, end, null);
	}

	/**
	 * @param prng
	 *            source of random bits used to compute the new long
	 * 
	 * @return A pseudorandom <code>long</code> value uniformly distributed in
	 *         <code>[Long.MIN_VALUE, Long.MAX_VALUE]</code>.
	 */
	public static long nextLong(Random prng) {
		return RandomUtil.nextBigInt(RandomUtil.LONG_MIN_VALUE, RandomUtil.LONG_MAX_VALUE_PLUS_1, prng).longValue();
	}

	/**
	 * @return <code>RandomUtil.nextLong(null)</code>.
	 */
	public static long nextLong() {
		return RandomUtil.nextLong(null);
	}
}
