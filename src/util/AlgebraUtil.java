package util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Utility math methods in addition to MathUtil but mainly focused on algebra.
 * 
 * @author Ashkan Moatamed
 */
public class AlgebraUtil {
	/**
	 * Dependencies: <code>
	 * 		1. util.NumUtil
	 * 		2. util.MathUtil
	 * 		3. util.InvalidModulusException
	 * </code>
	 */

	/**
	 * Prevent instantiation.
	 */
	private AlgebraUtil() {
		// Empty by design.
	}

	/**
	 * Precondition: <code>m >= 7</code> <br>
	 * Precondition: <code>(m % 2 != 0) || ((m / 2) % 2 != 0)</code> <br>
	 * Precondition: <code>NumUtil.factorSqrt((m % 2 == 0) ? (m / 2) : m).size() == 1</code> <br>
	 * Precondition: <code>(1 < n) && (n < m - 1)</code> <br>
	 * Precondition: <code>gcd(n, m) == 1</code> <br>
	 * Precondition: <code>(phiMFactorsKeySet != null) && (!phiMFactorsKeySet.isEmpty())</code> <br>
	 * Precondition:
	 * 
	 * <pre>
	 * <code>
	 * final Map&lt;Long, Byte&gt; factors = NumUtil.factorSqrt(phiM);
	 * assert (phiMFactorsKeySet.size() == factors.size());
	 * for (final Long q : phiMFactorsKeySet) {
	 * 	assert ((q != null) && factors.containsKey(q));
	 * }
	 * </code>
	 * </pre>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param phiM
	 *            <code>phi(m)</code>
	 * 
	 * @param phiMFactors
	 *            the given map containing the (distinct) prime divisors of <code>phiM</code> as keys
	 *            and their associated powers as values
	 * 
	 * @return <code>true</code> if and only if <code>n</code> is a primitive root modulo
	 *         <code>m</code>.
	 */
	protected static boolean isPrimitiveRootFixedInput(long n, long m, long phiM, Set<Long> phiMFactorsKeySet) {
		/**
		 * We know that <code>!phiMFactorsKeySet.isEmpty()</code> and so we can optimize the following loop
		 * by removing an extra call to <code>it.hashNext()</code> by writing it as a do-while loop instead
		 * of a for loop or a while loop.
		 */
		Iterator<Long> it = phiMFactorsKeySet.iterator();
		do {
			/**
			 * We know that for <code>n</code> in <code>[2, m - 2] \cap \doubleZ</code> coprime with
			 * <code>m</code>, <code>n</code> is a primitive root modulo <code>m</code> if and only if
			 * <code>n<sup>(<sup>phi(m)</sup>&frasl;<sub>q</sub>)</sup> (mod m) != 1</code> for all
			 * <code>q</code> where <code>q</code> is a prime divisor of <code>phi(m)</code>.
			 */
			if (MathUtil.modPowFixedInput(n, phiM / it.next(), m) == 1L) {
				/**
				 * Note that <code>MathUtil.modMinFixedInput</code> (and as a result
				 * <code>MathUtil.modPowFixedInput</code>) return <code>1</code> instead of <code>m - 1</code> for
				 * all <code>m</code> (including <code>m == 2</code>) so the following check is fine and we do not
				 * need to fix the result of <code>MathUtil.modPowFixedInput</code> to be in
				 * <code>[0, m - 1] \cap \doubleZ</code> by adding <code>m</code> to it if it's negative.
				 */
				return false;
			}
		} while (it.hasNext());
		return true;
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param hash
	 *            specifies whether the data structure used to store the factors, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code> when factoring <code>m</code>
	 *            and <code>phi(m)</code>
	 * 
	 * @param print
	 *            specifies whether the factoring of <code>m</code> and <code>phi(m)</code> should be
	 *            printed to the standard output stream
	 * 
	 * @return <code>true</code> if and only if <code>n</code> is a primitive root modulo
	 *         <code>m</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static boolean isPrimitiveRoot(long n, long m, boolean hash, boolean print) throws InvalidModulusException {
		if (m < 7L) { // i.e., (m < 1) || (m == 1) || (m == 2) || (m == 3) || (m == 4) || (m == 5) || (m == 6)
			if (m < 1L) {
				throw new InvalidModulusException();
			} else if (m == 1L) { // i.e., n == 0 (mod m)
				return false;
			}
			// m > 1
			// i.e., (m == 2) || (m == 3) || (m == 4) || (m == 5) || (m == 6)

			// Fix n to be in [0, m - 1] \cap \doubleZ.
			if ((n %= m) < 0L) {
				n += m;
			}
			if (n == 0L) {
				return false;
			}
			// n != 0
			// i.e., (1 <= n) && (n <= m - 1)
			if (m != 5L) { // i.e., (m == 2) || (m == 3) || (m == 4) || (m == 6)
				/**
				 * It's fine to do <code>++n</code> instead of <code>n + 1</code> since we don't need the value of
				 * <code>n</code> to remain unchanged.
				 */
				return (++n == m); // Only primitive root mod m, is m - 1 for m in { 2, 3, 4, 6 }.
			}
			// m == 5
			return ((n == 2L) || (n == 3L)); // Only primitive roots mod 5, are 2 and 3.
		}
		// m >= 7

		/**
		 * There is a primitive root mod <code>m</code>, if and only if <code>m</code> factors into
		 * <code>p<sup>e</sup></code> or <code>2 * p<sup>e</sup></code> where <code>p</code> is an odd prime
		 * number and <code>e</code> is a natural number. Therefore, make sure that <code>2</code> divides
		 * <code>m</code> at most once.
		 */
		long mOddFactor = m;
		if ((m & 1L) == 0L) { // i.e., MathUtil.isEven(m)
			/**
			 * Don't do <code>(m &= 1L) == 0L</code> since we need the value of <code>m</code> to remain
			 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
			 * will mutate <code>m</code>.
			 */
			// The following is meant to be an assignment of mOddFactor.
			if (((mOddFactor /= 2L) & 1L) == 0L) {
				return false;
			}
		}
		// ((m % 2 != 0) && (mOddFactor == m)) || (((m / 2) % 2 != 0) && (mOddFactor == m / 2))

		// Fix n to be in [0, m - 1] \cap \doubleZ.
		if ((n %= m) < 0L) {
			n += m;
		}

		/**
		 * For all <code>m >= 7</code>, we know that <code>0</code>, <code>1</code>, and <code>m - 1</code>
		 * can never be a primitive root modulo <code>m</code> since <code>phi(m) >= 3</code> but
		 * <code>order(0) DNE</code>, <code>order(1) == 1</code>, and <code>order(m - 1) == 2</code>.
		 */
		if ((n < 2L) || (n == m - 1L)) { // i.e., (n == 0) || (n == 1) || (n == -1 (mod m))
			return false;
		}
		// (2 <= n) && (n <= m - 2)
		// i.e., (1 < n) && (n < m - 1)

		/**
		 * We know that a primitive root modulo <code>m</code>, must be coprime with <code>m</code>.
		 */
		if (MathUtil.gcdFixedInput(n, m) != 1L) {
			return false;
		}
		// gcd(n, m) == 1

		/**
		 * Factor the largest odd divisor of <code>m</code> and check whether it is a prime power.
		 */
		final Map<Long, Byte> mOddFactors = NumUtil.factorSqrt(mOddFactor, hash, false);
		// Only print if requested.
		if (print) {
			if (m == mOddFactor) {
				System.out.print("m = " + m + " = ");
			} else { // m != mOddFactor
				// i.e., m == 2 * mOddFactor
				System.out.print("m = " + m + " = 2 * ");
			}
			NumUtil.printFactorsLong(mOddFactors, hash);
		}
		if (mOddFactors.size() != 1) {
			return false;
		}

		/**
		 * Compute <code>phi(m)</code> and then factor it. <br>
		 * <br>
		 * 
		 * At this point, we know that one of the following is true: <br>
		 * Case I: <code>m == mOddFactor</code> <br>
		 * Therefore, <code>phi(m) == phi(mOddFactor)</code> <br>
		 * Case II: <code>m == 2 * mOddFactor</code> <br>
		 * Therefore, <code>phi(m) == phi(2) * phi(mOddFactor) == 1 * phi(mOddFactor)</code> <br>
		 * <br>
		 * 
		 * However, we also know that <code>mOddFactor == p<sup>e</sup></code> and so we can conclude that
		 * <code>phi(m) == phi(mOddFactor) == mOddFactor * (1 - <sup>1</sup>&frasl;<sub>p</sub>)</code>.
		 */
		final long p = mOddFactors.entrySet().iterator().next().getKey();
		final long phiM = mOddFactor - (mOddFactor / p);
		final Map<Long, Byte> phiMFactors = NumUtil.factorSqrt(phiM, hash, false);
		// Only print if requested.
		if (print) {
			System.out.print("phi(m) = " + phiM + " = ");
			NumUtil.printFactorsLong(phiMFactors, hash);
		}
		final Set<Long> phiMFactorsKeySet = phiMFactors.keySet();
		return AlgebraUtil.isPrimitiveRootFixedInput(n, m, phiM, phiMFactorsKeySet);
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param hash
	 *            specifies whether the data structure used to store the factors, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code> when factoring <code>m</code>
	 *            and <code>phi(m)</code>
	 * 
	 * @return <code>AlgebraUtil.isPrimitiveRoot(n, m, hash, false)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static boolean isPrimitiveRoot(long n, long m, boolean hash) throws InvalidModulusException {
		return AlgebraUtil.isPrimitiveRoot(n, m, hash, false);
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>AlgebraUtil.isPrimitiveRoot(n, m, false)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static boolean isPrimitiveRoot(long n, long m) throws InvalidModulusException {
		return AlgebraUtil.isPrimitiveRoot(n, m, false);
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param hash
	 *            specifies whether the data structure used to store the factors, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code> when factoring <code>m</code>
	 *            and <code>phi(m)</code>
	 * 
	 * @param print
	 *            specifies whether the factoring of <code>m</code> and <code>phi(m)</code> should be
	 *            printed to the standard output stream
	 * 
	 * @return <code>true</code> if and only if <code>n</code> is a primitive root modulo
	 *         <code>m</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static boolean isPrimitiveRoot(int n, int m, boolean hash, boolean print) throws InvalidModulusException {
		return AlgebraUtil.isPrimitiveRoot((long) n, (long) m, hash, print);
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param hash
	 *            specifies whether the data structure used to store the factors, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code> when factoring <code>m</code>
	 *            and <code>phi(m)</code>
	 * 
	 * @return <code>AlgebraUtil.isPrimitiveRoot(n, m, hash, false)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static boolean isPrimitiveRoot(int n, int m, boolean hash) throws InvalidModulusException {
		return AlgebraUtil.isPrimitiveRoot(n, m, hash, false);
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>AlgebraUtil.isPrimitiveRoot(n, m, false)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static boolean isPrimitiveRoot(int n, int m) throws InvalidModulusException {
		return AlgebraUtil.isPrimitiveRoot(n, m, false);
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param hash
	 *            specifies whether the data structure used to store the factors, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code> when factoring <code>m</code>
	 *            and <code>phi(m)</code>
	 * 
	 * @param print
	 *            specifies whether the factoring of <code>m</code> and <code>phi(m)</code> should be
	 *            printed to the standard output stream
	 * 
	 * @return <code>true</code> if and only if <code>n</code> is a primitive root modulo
	 *         <code>m</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static boolean isPrimitiveRoot(short n, short m, boolean hash, boolean print)
			throws InvalidModulusException {
		return AlgebraUtil.isPrimitiveRoot((long) n, (long) m, hash, print);
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param hash
	 *            specifies whether the data structure used to store the factors, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code> when factoring <code>m</code>
	 *            and <code>phi(m)</code>
	 * 
	 * @return <code>AlgebraUtil.isPrimitiveRoot(n, m, hash, false)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static boolean isPrimitiveRoot(short n, short m, boolean hash) throws InvalidModulusException {
		return AlgebraUtil.isPrimitiveRoot(n, m, hash, false);
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>AlgebraUtil.isPrimitiveRoot(n, m, false)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static boolean isPrimitiveRoot(short n, short m) throws InvalidModulusException {
		return AlgebraUtil.isPrimitiveRoot(n, m, false);
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param hash
	 *            specifies whether the data structure used to store the factors, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code> when factoring <code>m</code>
	 *            and <code>phi(m)</code>
	 * 
	 * @param print
	 *            specifies whether the factoring of <code>m</code> and <code>phi(m)</code> should be
	 *            printed to the standard output stream
	 * 
	 * @return <code>true</code> if and only if <code>n</code> is a primitive root modulo
	 *         <code>m</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static boolean isPrimitiveRoot(byte n, byte m, boolean hash, boolean print) throws InvalidModulusException {
		return AlgebraUtil.isPrimitiveRoot((long) n, (long) m, hash, print);
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @param hash
	 *            specifies whether the data structure used to store the factors, should be a
	 *            <code>HashMap</code> instead of a <code>TreeMap</code> when factoring <code>m</code>
	 *            and <code>phi(m)</code>
	 * 
	 * @return <code>AlgebraUtil.isPrimitiveRoot(n, m, hash, false)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static boolean isPrimitiveRoot(byte n, byte m, boolean hash) throws InvalidModulusException {
		return AlgebraUtil.isPrimitiveRoot(n, m, hash, false);
	}

	/**
	 * @param n
	 *            the given number
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>AlgebraUtil.isPrimitiveRoot(n, m, false)</code>.
	 * 
	 * @throws InvalidModulusException
	 *             If <code>m < 1</code>
	 */
	public static boolean isPrimitiveRoot(byte n, byte m) throws InvalidModulusException {
		return AlgebraUtil.isPrimitiveRoot(n, m, false);
	}
}
