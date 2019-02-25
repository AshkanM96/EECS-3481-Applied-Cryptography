package util;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Utility math methods in addition to MathUtil but mainly focused on statistical purposes.
 * 
 * @author Ashkan Moatamed
 */
public class StatUtil {
	/**
	 * No dependencies.
	 */

	/**
	 * <code>BigDecimal.valueOf(100)</code>.
	 */
	public static final BigDecimal HUNDRED_ZERO_SCALE = BigDecimal.valueOf(100L);

	/**
	 * Default scale to be used for BigDecimal operations.
	 */
	public static final int DEFAULT_SCALE = 20;

	/**
	 * Prevent instantiation.
	 */
	private StatUtil() {
		// Empty by design.
	}

	/**
	 * Linearly compute <code>n factorial</code> (i.e., <code>n!</code>). <br>
	 * Precondition: <code>(n != null) && (n >= 0)</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>n factorial</code> (i.e., <code>n!</code>).
	 */
	protected static BigInteger factorialLinearFixedInput(BigInteger n) {
		if (n.signum() == 0) { // i.e., n == 0
			return BigInteger.ONE;
		}
		// n != 0
		// i.e., n >= 1

		// Compute n! in the loop and then return it.
		BigInteger result = BigInteger.ONE;
		for (BigInteger i = BigInteger.ONE; !i.equals(n); /* Update inside. */) {
			// i! = i * (i - 1)!
			i = i.add(BigInteger.ONE);
			result = result.multiply(i);
		}
		return result;
	}

	/**
	 * Linearly compute <code>n factorial</code> (i.e., <code>n!</code>).
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>n factorial</code> (i.e., <code>n!</code>).
	 * 
	 * @throws NullPointerException
	 *             If <code>n == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n < 0</code>
	 */
	public static BigInteger factorialLinear(BigInteger n) throws NullPointerException, IllegalArgumentException {
		if (n.signum() == -1) { // i.e., n < 0
			throw new IllegalArgumentException();
		}
		// n >= 0
		return StatUtil.factorialLinearFixedInput(n);
	}

	/**
	 * Linearly compute <code>n factorial</code> (i.e., <code>n!</code>). <br>
	 * Precondition: <code>n >= 0</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>n factorial</code> (i.e., <code>n!</code>).
	 */
	protected static BigInteger factorialLinearFixedInput(long n) {
		if (n == 0L) {
			return BigInteger.ONE;
		}
		// n != 0
		// i.e., n >= 1

		// Compute n! in the loop and then return it.
		BigInteger result = BigInteger.ONE;
		for (long i = 1L; i != n; /* Update inside. */) {
			// i! = i * (i - 1)!
			/**
			 * Note that <code>BigInteger.valueOf(long)</code> calls are much cheaper than performing arithmetic
			 * operations on a <code>BigInteger</code> since those are fully general operations for potentially
			 * big integers. Furthermore, due to <code>BigInteger</code> being immutable, its arithmetic
			 * operations always create a new object and so using <code>BigInteger.valueOf(long)</code> is
			 * neither adding nor removing an object creation but it is cheaper because of the faster primitive
			 * arithmetic operations and the simplicity of the implementation of
			 * <code>BigInteger.valueOf(long)</code>.
			 */
			result = result.multiply(BigInteger.valueOf(++i));
		}
		return result;
	}

	/**
	 * Linearly compute <code>n factorial</code> (i.e., <code>n!</code>).
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>n factorial</code> (i.e., <code>n!</code>).
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n < 0</code>
	 */
	public static BigInteger factorialLinear(long n) throws IllegalArgumentException {
		if (n < 0L) {
			throw new IllegalArgumentException();
		}
		// n >= 0
		return StatUtil.factorialLinearFixedInput(n);
	}

	/**
	 * Linearly compute <code>n subfactorial</code> (i.e., <code>!n</code>). <br>
	 * Precondition: <code>(n != null) && (n >= 0)</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>n subfactorial</code> (i.e., <code>!n</code>).
	 */
	protected static BigInteger subfactorialLinearFixedInput(BigInteger n) {
		if (n.signum() == 0) { // i.e., n == 0
			return BigInteger.ONE;
		}
		// n != 0
		// i.e., n >= 1

		// Compute !n in the loop and then return it.
		BigInteger result = BigInteger.ZERO;
		for (BigInteger i = BigInteger.ONE; !i.equals(n); /* Update inside. */) {
			// !i = i * !(i - 1) + (-1)^i
			result = result.multiply(i = i.add(BigInteger.ONE));
			result = i.testBit(0) ? result.subtract(BigInteger.ONE) : result.add(BigInteger.ONE);
		}
		return result;
	}

	/**
	 * Linearly compute <code>n subfactorial</code> (i.e., <code>!n</code>).
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>n subfactorial</code> (i.e., <code>!n</code>).
	 * 
	 * @throws NullPointerException
	 *             If <code>n == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n < 0</code>
	 */
	public static BigInteger subfactorialLinear(BigInteger n) throws NullPointerException, IllegalArgumentException {
		if (n.signum() == -1) { // i.e., n < 0
			throw new IllegalArgumentException();
		}
		// n >= 0
		return StatUtil.subfactorialLinearFixedInput(n);
	}

	/**
	 * Linearly compute <code>n subfactorial</code> (i.e., <code>!n</code>). <br>
	 * Precondition: <code>n >= 0</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>n subfactorial</code> (i.e., <code>!n</code>).
	 */
	protected static BigInteger subfactorialLinearFixedInput(long n) {
		if (n == 0L) {
			return BigInteger.ONE;
		}
		// n != 0
		// i.e., n >= 1

		// Compute !n in the loop and then return it.
		BigInteger result = BigInteger.ZERO;
		for (long i = 1L; i != n; /* Update inside. */) {
			// !i = i * !(i - 1) + (-1)^i
			/**
			 * Note that <code>BigInteger.valueOf(long)</code> calls are much cheaper than performing arithmetic
			 * operations on a <code>BigInteger</code> since those are fully general operations for potentially
			 * big integers. Furthermore, due to <code>BigInteger</code> being immutable, its arithmetic
			 * operations always create a new object and so using <code>BigInteger.valueOf(long)</code> is
			 * neither adding nor removing an object creation but it is cheaper because of the faster primitive
			 * arithmetic operations and the simplicity of the implementation of
			 * <code>BigInteger.valueOf(long)</code>.
			 */
			result = result.multiply(BigInteger.valueOf(++i));
			/**
			 * Don't do <code>(i &= 1L) != 0L</code> since we need the value of <code>i</code> to remain
			 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
			 * will mutate <code>i</code>.
			 */
			result = ((i & 1L) != 0L) ? result.subtract(BigInteger.ONE) : result.add(BigInteger.ONE);
		}
		return result;
	}

	/**
	 * Linearly compute <code>n subfactorial</code> (i.e., <code>!n</code>).
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>n subfactorial</code> (i.e., <code>!n</code>).
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n < 0</code>
	 */
	public static BigInteger subfactorialLinear(long n) throws IllegalArgumentException {
		if (n < 0L) {
			throw new IllegalArgumentException();
		}
		// n >= 0
		return StatUtil.subfactorialLinearFixedInput(n);
	}

	/**
	 * Linearly compute <code>nPr</code>. <br>
	 * Precondition: <code>(n != null) && (n >= 0)</code> <br>
	 * Precondition: <code>(r != null) && (0 <= r) && (r <= n)</code>
	 * 
	 * @param n
	 *            the given number of objects
	 * 
	 * @param r
	 *            the given sample size
	 * 
	 * @return <code>nPr</code>.
	 */
	protected static BigInteger nPrLinearFixedInput(BigInteger n, BigInteger r) {
		BigInteger result = BigInteger.ONE;
		final BigInteger endI = n.subtract(r);
		for (BigInteger i = n; !i.equals(endI); i = i.subtract(BigInteger.ONE)) {
			result = result.multiply(i);
		}
		return result;
	}

	/**
	 * Linearly compute <code>nPr</code>.
	 * 
	 * @param n
	 *            the given number of objects
	 * 
	 * @param r
	 *            the given sample size
	 * 
	 * @return <code>nPr</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(n == null) || (r == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(n < 0) || (r < 0) || (n < r)</code>
	 */
	public static BigInteger nPrLinear(BigInteger n, BigInteger r)
			throws NullPointerException, IllegalArgumentException {
		if ((n.signum() == -1) || (r.signum() == -1)) { // i.e., (n < 0) || (r < 0)
			throw new IllegalArgumentException();
		}
		// (n >= 0) && (r >= 0)

		final int n_cmp_r = n.compareTo(r);
		if (n_cmp_r < 0) { // i.e., n < r
			throw new IllegalArgumentException();
		}
		// n_cmp_r >= 0
		// i.e., n >= r

		if (r.signum() == 0) { // i.e., r == 0
			/*
			 * This case is only an optimization since nP0 == 1 and so the loop will never execute but extra
			 * unnecessary work will be done to arrive at the same result.
			 */
			return BigInteger.ONE;
		}
		// r != 0
		// i.e., r >= 1
		return StatUtil.nPrLinearFixedInput(n, r);
	}

	/**
	 * Linearly compute <code>nPr</code>. <br>
	 * Precondition: <code>n >= 0</code> <br>
	 * Precondition: <code>(0 <= r) && (r <= n)</code>
	 * 
	 * @param n
	 *            the given number of objects
	 * 
	 * @param r
	 *            the given sample size
	 * 
	 * @return <code>nPr</code>.
	 */
	protected static BigInteger nPrLinearFixedInput(long n, long r) {
		/**
		 * Don't delegate to <code>StatUtil.nPrLinearFixedInput(BigInteger, BigInteger)</code> since
		 * arithmetic operations on a <code>primitive type (i.e., long)</code>, are much faster than
		 * arithmetic operations on a <code>BigInteger</code>.
		 */

		BigInteger result = BigInteger.ONE;
		final long endI = n - r;
		for (long i = n; i != endI; --i) {
			/**
			 * Note that <code>BigInteger.valueOf(long)</code> calls are much cheaper than performing arithmetic
			 * operations on a <code>BigInteger</code> since those are fully general operations for potentially
			 * big integers. Furthermore, due to <code>BigInteger</code> being immutable, its arithmetic
			 * operations always create a new object and so using <code>BigInteger.valueOf(long)</code> is
			 * neither adding nor removing an object creation but it is cheaper because of the faster primitive
			 * arithmetic operations and the simplicity of the implementation of
			 * <code>BigInteger.valueOf(long)</code>.
			 */
			result = result.multiply(BigInteger.valueOf(i));
		}
		return result;
	}

	/**
	 * Linearly compute <code>nPr</code>.
	 * 
	 * @param n
	 *            the given number of objects
	 * 
	 * @param r
	 *            the given sample size
	 * 
	 * @return <code>nPr</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(n < 0) || (r < 0) || (n < r)</code>
	 */
	public static BigInteger nPrLinear(long n, long r) throws IllegalArgumentException {
		if ((n < 0L) || (r < 0L)) {
			throw new IllegalArgumentException();
		}
		// (n >= 0) && (r >= 0)

		if (n < r) {
			throw new IllegalArgumentException();
		}
		// n >= r

		if (r == 0L) {
			/*
			 * This case is only an optimization since nP0 == 1 and so the loop will never execute but extra
			 * unnecessary work will be done to arrive at the same result.
			 */
			return BigInteger.ONE;
		}
		// r != 0
		// i.e., r >= 1
		return StatUtil.nPrLinearFixedInput(n, r);
	}

	/**
	 * Linearly compute <code>nCr</code>. <br>
	 * Precondition: <code>(n != null) && (n >= 0)</code> <br>
	 * Precondition: <code>(r != null) && (1 <= r) && (r <= n / 2)</code>
	 * 
	 * @param n
	 *            the given number of objects
	 * 
	 * @param r
	 *            the given sample size
	 * 
	 * @return <code>nCr</code>.
	 */
	protected static BigInteger nCrLinearFixedInput(BigInteger n, BigInteger r) {
		BigInteger numerator = n, denominator = r;
		for (BigInteger i = BigInteger.ONE, n_i = n.subtract(BigInteger.ONE); !i.equals(r); i = i
				.add(BigInteger.ONE), n_i = n_i.subtract(BigInteger.ONE)) {
			numerator = numerator.multiply(n_i);
			denominator = denominator.multiply(i);
		}
		return numerator.divide(denominator);
	}

	/**
	 * Linearly compute <code>nCr</code>.
	 * 
	 * @param n
	 *            the given number of objects
	 * 
	 * @param r
	 *            the given sample size
	 * 
	 * @return <code>nCr</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(n == null) || (r == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(n < 0) || (r < 0) || (n < r)</code>
	 */
	public static BigInteger nCrLinear(BigInteger n, BigInteger r)
			throws NullPointerException, IllegalArgumentException {
		if ((n.signum() == -1) || (r.signum() == -1)) { // i.e., (n < 0) || (r < 0)
			throw new IllegalArgumentException();
		}
		// (n >= 0) && (r >= 0)

		final int n_cmp_r = n.compareTo(r);
		if (n_cmp_r < 0) { // i.e., n < r
			throw new IllegalArgumentException();
		} else if (n_cmp_r == 0) { // i.e., n == r
			/**
			 * This case is only an optimization since nCn == 1. The following code will handle it after setting
			 * <code>r == n_r == n - n == 0</code> but we might as well handle it earlier and save some
			 * computations.
			 */
			return BigInteger.ONE;
		}
		// n_cmp_r > 0
		// i.e., n > r

		// nCr == nC(n - r)
		final BigInteger n_r = n.subtract(r);
		if (n_r.compareTo(r) < 0) { // i.e., n_r < r
			r = n_r;
		}
		// r == min(original r, n - original r)
		// i.e., r <= n / 2

		if (r.signum() == 0) { // i.e., r == 0
			// This case is needed since the loop will run forever for r == 0 even though nC0 == 1.
			return BigInteger.ONE;
		} else if (r.equals(BigInteger.ONE)) { // i.e., r == 1
			/*
			 * This case is only an optimization since nC1 == n and so the loop will never execute but extra
			 * unnecessary work will be done to arrive at the same result.
			 */
			return n;
		}
		// (r != 0) && (r != 1)
		// i.e., r >= 2
		return StatUtil.nCrLinearFixedInput(n, r);
	}

	/**
	 * Linearly compute <code>nCr</code>. <br>
	 * Precondition: <code>n >= 0</code> <br>
	 * Precondition: <code>(1 <= r) && (r <= n / 2)</code>
	 * 
	 * @param n
	 *            the given number of objects
	 * 
	 * @param r
	 *            the given sample size
	 * 
	 * @return <code>nCr</code>.
	 */
	protected static BigInteger nCrLinearFixedInput(long n, long r) {
		/**
		 * Don't delegate to <code>StatUtil.nCrLinearFixedInput(BigInteger, BigInteger)</code> since
		 * arithmetic operations on a <code>primitive type (i.e., long)</code>, are much faster than
		 * arithmetic operations on a <code>BigInteger</code>.
		 */

		BigInteger numerator = BigInteger.valueOf(n), denominator = BigInteger.valueOf(r);
		for (long i = 1L; i != r; ++i) {
			/**
			 * Note that <code>BigInteger.valueOf(long)</code> calls are much cheaper than performing arithmetic
			 * operations on a <code>BigInteger</code> since those are fully general operations for potentially
			 * big integers. Furthermore, due to <code>BigInteger</code> being immutable, its arithmetic
			 * operations always create a new object and so using <code>BigInteger.valueOf(long)</code> is
			 * neither adding nor removing an object creation but it is cheaper because of the faster primitive
			 * arithmetic operations and the simplicity of the implementation of
			 * <code>BigInteger.valueOf(long)</code>.
			 */
			numerator = numerator.multiply(BigInteger.valueOf(n - i));
			denominator = denominator.multiply(BigInteger.valueOf(i));
		}
		return numerator.divide(denominator);
	}

	/**
	 * Linearly compute <code>nCr</code>.
	 * 
	 * @param n
	 *            the given number of objects
	 * 
	 * @param r
	 *            the given sample size
	 * 
	 * @return <code>nCr</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(n < 0) || (r < 0) || (n < r)</code>
	 */
	public static BigInteger nCrLinear(long n, long r) throws IllegalArgumentException {
		if ((n < 0L) || (r < 0L)) {
			throw new IllegalArgumentException();
		}
		// (n >= 0) && (r >= 0)

		if (n < r) {
			throw new IllegalArgumentException();
		} else if (n == r) {
			/**
			 * This case is only an optimization since nCn == 1. The following code will handle it after setting
			 * <code>r == n_r == n - n == 0</code> but we might as well handle it earlier and save some
			 * computations.
			 */
			return BigInteger.ONE;
		}
		// n > r

		// nCr == nC(n - r)
		final long n_r = n - r;
		if (n_r < r) {
			r = n_r;
		}
		// r == Math.min(original r, n - original r)
		// i.e., r <= n / 2

		if (r == 0L) {
			// This case is needed since the loop will run forever for r == 0 even though nC0 == 1.
			return BigInteger.ONE;
		} else if (r == 1L) {
			/*
			 * This case is only an optimization since nC1 == n and so the loop will never execute but extra
			 * unnecessary work will be done to arrive at the same result.
			 */
			return BigInteger.valueOf(n);
		}
		// (r != 0) && (r != 1)
		// i.e., r >= 2
		return StatUtil.nCrLinearFixedInput(n, r);
	}

	/**
	 * Linearly compute <code>S2(n, r)</code>. <br>
	 * Precondition: <code>n >= 1</code> <br>
	 * Precondition: <code>(2 <= r) && (r <= n - 1)</code>
	 * 
	 * @param n
	 *            the given number of objects
	 * 
	 * @param r
	 *            the given sample size
	 * 
	 * @return <code>S2(n, r)</code>.
	 */
	protected static BigInteger stirling2LinearFixedInput(int n, int r) {
		// S2(n, r) = (sum (-1)^i rCi (r - i)^n from i = 0 to i = r) / r!
		BigInteger numerator = BigInteger.ZERO, denominator = BigInteger.ONE;
		BigInteger tmp = null;
		for (int i = 0; i <= r; ++i) {
			/**
			 * Note that <code>BigInteger.valueOf(long)</code> calls are much cheaper than performing arithmetic
			 * operations on a <code>BigInteger</code> since those are fully general operations for potentially
			 * big integers. Furthermore, due to <code>BigInteger</code> being immutable, its arithmetic
			 * operations always create a new object and so using <code>BigInteger.valueOf(long)</code> is
			 * neither adding nor removing an object creation but it is cheaper because of the faster primitive
			 * arithmetic operations and the simplicity of the implementation of
			 * <code>BigInteger.valueOf(long)</code>.
			 */
			tmp = StatUtil.nCrLinear(r, i).multiply(BigInteger.valueOf(r - i).pow(n));
			/**
			 * Don't do <code>(i &= 1) != 0</code> since we need the value of <code>i</code> to remain
			 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
			 * will mutate <code>i</code>.
			 */
			numerator = ((i & 1) != 0) ? numerator.subtract(tmp) : numerator.add(tmp);
			if (i != 0) {
				denominator = denominator.multiply(BigInteger.valueOf(i));
			}
		}
		return numerator.divide(denominator);
	}

	/**
	 * Linearly compute <code>S2(n, r)</code>.
	 * 
	 * @param n
	 *            the given number of objects
	 * 
	 * @param r
	 *            the given sample size
	 * 
	 * @return <code>S2(n, r)</code>.
	 */
	public static BigInteger stirling2Linear(int n, int r) throws IllegalArgumentException {
		if ((n < 0) || (r < 0)) {
			throw new IllegalArgumentException();
		}
		// (n >= 0) && (r >= 0)

		if (n < r) {
			throw new IllegalArgumentException();
		} else if ((n == 0) || (r == 1) || (n == r)) {
			// S2(0, 0) == S2(n, 1) == S2(n, n) == 1
			return BigInteger.ONE;
		} else if (r == 0) {
			// S2(n, 0) for n != 0 is 0
			return BigInteger.ZERO;
		}
		// (n > r) && (n != 0) && (r != 0) && (r != 1)
		// i.e., (n - 1 >= r) && (n >= 1) && (r >= 2)
		return StatUtil.stirling2LinearFixedInput(n, r);
	}

	/**
	 * Precondition: <code>d >= 2</code> <br>
	 * Precondition: <code>(0 <= n) && (n <= d - 1)</code> <br>
	 * Precondition: <code>scale >= 0</code>
	 * 
	 * @param d
	 *            the given number of days
	 * 
	 * @param n
	 *            the given number of people
	 * 
	 * @param scale
	 *            the given scale which will only be used when the probability is not an exact integer
	 * 
	 * @param outOf100
	 *            specifies whether the result should be out of 100
	 * 
	 * @return The probability of at least one shared birthday between the two people.
	 */
	protected static BigDecimal birthday1LinearFixedInput(int d, int n, int scale, boolean outOf100) {
		// Probability of no collision is: <code>dPn / (d^n)</code>
		final BigInteger numerator = StatUtil.nPrLinearFixedInput(d, n);
		final BigDecimal denominator = BigDecimal.valueOf(d).pow(n);
		// Probability of >= 1 collision is: <code>1 - probability of no collision</code>
		final BigDecimal result = BigDecimal.ONE
				.subtract(new BigDecimal(numerator).divide(denominator, scale, BigDecimal.ROUND_HALF_UP));
		return (outOf100 ? result.multiply(StatUtil.HUNDRED_ZERO_SCALE) : result);
	}

	/**
	 * @param d
	 *            the given number of days
	 * 
	 * @param n
	 *            the given number of people
	 * 
	 * @param scale
	 *            the given scale which will only be used when the probability is not an exact integer
	 * 
	 * @param outOf100
	 *            specifies whether the result should be out of 100
	 * 
	 * @return The probability of at least one shared birthday between the two people.
	 */
	public static BigDecimal birthday1Linear(int d, int n, int scale, boolean outOf100)
			throws IllegalArgumentException {
		if ((d < 1) || (n < 0) || (scale < 0)) {
			throw new IllegalArgumentException();
		}
		// (d >= 1) && (n >= 0) && (scale >= 0)

		// Handle the simple special cases.
		if (n < 2) {
			// Probability of >= 1 collision with <= 1 people is: <code>0</code>
			return BigDecimal.ZERO;
		} else if (n >= d) {
			/**
			 * Probability of >= 1 collision with d or more people is:
			 * <code>1 by the Pigeon Hole Principle</code>
			 */
			return (outOf100 ? StatUtil.HUNDRED_ZERO_SCALE : BigDecimal.ONE);
		}
		// (1 < n) && (n < d)
		// i.e., (1 < n) && (n < d) && (d >= 2)
		return StatUtil.birthday1LinearFixedInput(d, n, scale, outOf100);
	}

	/**
	 * @param d
	 *            the given number of days
	 * 
	 * @param n
	 *            the given number of people
	 * 
	 * @param scale
	 *            the given scale which will only be used when the probability is not an exact integer
	 * 
	 * @return The probability of at least one shared birthday between the two people.
	 */
	public static BigDecimal birthday1Linear(int d, int n, int scale) throws IllegalArgumentException {
		return StatUtil.birthday1Linear(d, n, scale, true);
	}

	/**
	 * @param d
	 *            the given number of days
	 * 
	 * @param n
	 *            the given number of people
	 * 
	 * @return The probability of at least one shared birthday between the two people.
	 */
	public static BigDecimal birthday1Linear(int d, int n) throws IllegalArgumentException {
		return StatUtil.birthday1Linear(d, n, StatUtil.DEFAULT_SCALE);
	}

	/**
	 * Precondition: <code>d >= 2</code> <br>
	 * Precondition: <code>(n >= 1) && (m >= 1)</code> <br>
	 * Precondition: <code>m + n <= d</code> <br>
	 * Precondition: <code>scale >= 0</code>
	 * 
	 * @param d
	 *            the given number of days
	 * 
	 * @param m
	 *            the given number of people of type 1
	 * 
	 * @param n
	 *            the given number of people of type 2
	 * 
	 * @param scale
	 *            the given scale
	 * 
	 * @param outOf100
	 *            specifies whether the result should be out of 100
	 * 
	 * @return The probability of at least one shared birthday between the two different types of
	 *         people. Note that shared birthdays between the same type of people doesn't count.
	 */
	protected static BigDecimal birthday2LinearFixedInput(int d, int m, int n, int scale, boolean outOf100) {
		/**
		 * Probability of no collision is as follows:
		 * 
		 * <pre>
		 * <code>
		 *	(sum from i = 1 to i = m
		 *		sum from j = 1 to j = n
		 *			S2(m, i) * S2(n, j) * (product d - k from k = 0 to k = i + j - 1)
		 *	) / (d^(n + m))
		 * </code>
		 * </pre>
		 */
		final BigDecimal denominator = BigDecimal.valueOf(d).pow(m + n);
		BigInteger numerator = BigInteger.ZERO;
		BigInteger tmp = null;
		for (int i = 1; i <= m; ++i) {
			for (int j = 1; j <= n; ++j) {
				tmp = StatUtil.stirling2Linear(m, i).multiply(StatUtil.stirling2Linear(n, j));
				for (long k = 0L, maxK = ((long) i) + j; k != maxK; ++k) {
					/**
					 * Note that <code>BigInteger.valueOf(long)</code> calls are much cheaper than performing arithmetic
					 * operations on a <code>BigInteger</code> since those are fully general operations for potentially
					 * big integers. Furthermore, due to <code>BigInteger</code> being immutable, its arithmetic
					 * operations always create a new object and so using <code>BigInteger.valueOf(long)</code> is
					 * neither adding nor removing an object creation but it is cheaper because of the faster primitive
					 * arithmetic operations and the simplicity of the implementation of
					 * <code>BigInteger.valueOf(long)</code>.
					 */
					tmp = tmp.multiply(BigInteger.valueOf(d - k));
				}
				numerator = numerator.add(tmp);
			}
		}
		// Probability of >= 1 collision is: <code>1 - probability of no collision</code>
		final BigDecimal result = BigDecimal.ONE
				.subtract(new BigDecimal(numerator).divide(denominator, scale, BigDecimal.ROUND_HALF_UP));
		return (outOf100 ? result.multiply(StatUtil.HUNDRED_ZERO_SCALE) : result);
	}

	/**
	 * @param d
	 *            the given number of days
	 * 
	 * @param m
	 *            the given number of people of type 1
	 * 
	 * @param n
	 *            the given number of people of type 2
	 * 
	 * @param scale
	 *            the given scale which will only be used when the probability is not an exact integer
	 * 
	 * @param outOf100
	 *            specifies whether the result should be out of 100
	 * 
	 * @return The probability of at least one shared birthday between the two different types of
	 *         people. Note that shared birthdays between the same type of people doesn't count.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(d < 1) || (m < 0) || (n < 0) || (scale < 0)
	 *             || ((m < d) && (n < d) && (m + n > d))</code>
	 */
	public static BigDecimal birthday2Linear(int d, int m, int n, int scale, boolean outOf100)
			throws IllegalArgumentException {
		if ((d < 1) || (m < 0) || (n < 0) || (scale < 0)) {
			throw new IllegalArgumentException();
		}
		// (d >= 1) && (m >= 0) && (n >= 0) && (scale >= 0)

		// Handle the simple special cases.
		if ((m == 0) || (n == 0)) {
			// Probability of >= 1 collision with 0 people of at least one of the types is: <code>0</code>
			return BigDecimal.ZERO;
		} else if ((m >= d) && (n >= d)) {
			/**
			 * Probability of >= 1 collision with d or more people in each type, is:
			 * <code>1 by the Pigeon Hole Principle</code>
			 */
			return (outOf100 ? StatUtil.HUNDRED_ZERO_SCALE : BigDecimal.ONE);
		}
		// (m != 0) && (n != 0) && (m < d) && (n < d)
		// i.e., (1 <= m) && (1 <= n) && (m < d) && (n < d)

		if (((long) m) + n > d) {
			throw new IllegalArgumentException();
		}
		// m + n <= d
		// i.e., (m + n <= d) && (d >= 2)
		return StatUtil.birthday2LinearFixedInput(d, m, n, scale, outOf100);
	}

	/**
	 * @param d
	 *            the given number of days
	 * 
	 * @param m
	 *            the given number of people of type 1
	 * 
	 * @param n
	 *            the given number of people of type 2
	 * 
	 * @param scale
	 *            the given scale which will only be used when the probability is not an exact integer
	 * 
	 * @return The probability of at least one shared birthday between the two different types of
	 *         people. Note that shared birthdays between the same type of people doesn't count.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(d < 1) || (m < 0) || (n < 0) || (scale < 0)
	 *             || ((m < d) && (n < d) && (m + n > d))</code>
	 */
	public static BigDecimal birthday2Linear(int d, int m, int n, int scale) throws IllegalArgumentException {
		return StatUtil.birthday2Linear(d, m, n, scale, true);
	}

	/**
	 * @param d
	 *            the given number of days
	 * 
	 * @param m
	 *            the given number of people of type 1
	 * 
	 * @param n
	 *            the given number of people of type 2
	 * 
	 * @return The probability of at least one shared birthday between the two different types of
	 *         people. Note that shared birthdays between the same type of people doesn't count.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(d < 1) || (m < 0) || (n < 0)
	 *             || ((m < d) && (n < d) && (m + n > d))</code>
	 */
	public static BigDecimal birthday2Linear(int d, int m, int n) throws IllegalArgumentException {
		return StatUtil.birthday2Linear(d, m, n, StatUtil.DEFAULT_SCALE);
	}

	/**
	 * Linearly compute the <code>n<sup>th</sup></code> fibonacci number. <br>
	 * Precondition: <code>(n != null) && (n >= 0)</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return The <code>n<sup>th</sup></code> fibonacci number.
	 */
	protected static BigInteger fibonacciLinearFixedInput(BigInteger n) {
		if (n.signum() == 0) { // i.e., n == 0
			return BigInteger.ZERO;
		}
		// n != 0
		// i.e., n >= 1

		// Compute Fn in the loop and then return it.
		BigInteger Fi_2 = BigInteger.ZERO, Fi_1 = BigInteger.ONE, Fi = BigInteger.ONE;
		for (BigInteger i = BigInteger.ONE; !i.equals(n); i = i.add(BigInteger.ONE)) {
			// Fi = Fi_1 + Fi_2
			Fi = Fi_1.add(Fi_2);
			Fi_2 = Fi_1;
			Fi_1 = Fi;
		}
		return Fi;
	}

	/**
	 * Linearly compute the <code>n<sup>th</sup></code> fibonacci number.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return The <code>n<sup>th</sup></code> fibonacci number.
	 */
	public static BigInteger fibonacciLinear(BigInteger n) {
		if (n.signum() == -1) { // i.e., n < 0
			throw new IllegalArgumentException();
		}
		// n >= 0
		return StatUtil.fibonacciLinearFixedInput(n);
	}

	/**
	 * Linearly compute the <code>n<sup>th</sup></code> fibonacci number. <br>
	 * Precondition: <code>n >= 0</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return The <code>n<sup>th</sup></code> fibonacci number.
	 */
	protected static BigInteger fibonacciLinearFixedInput(long n) {
		if (n == 0L) {
			return BigInteger.ZERO;
		}
		// n != 0
		// i.e., n >= 1

		/**
		 * Don't delegate to <code>StatUtil.fibonacciLinearFixedInput(BigInteger)</code> since arithmetic
		 * operations on a <code>primitive type (i.e., long)</code>, are much faster than arithmetic
		 * operations on a <code>BigInteger</code>.
		 */

		// Compute Fn in the loop and then return it.
		BigInteger Fi_2 = BigInteger.ZERO, Fi_1 = BigInteger.ONE, Fi = BigInteger.ONE;
		for (long i = 1L; i != n; ++i) {
			// Fi = Fi_1 + Fi_2
			Fi = Fi_1.add(Fi_2);
			Fi_2 = Fi_1;
			Fi_1 = Fi;
		}
		return Fi;
	}

	/**
	 * Linearly compute the <code>n<sup>th</sup></code> fibonacci number.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return The <code>n<sup>th</sup></code> fibonacci number.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n < 0</code>
	 */
	public static BigInteger fibonacciLinear(long n) throws IllegalArgumentException {
		if (n < 0L) {
			throw new IllegalArgumentException();
		}
		// n >= 0
		return StatUtil.fibonacciLinearFixedInput(n);
	}
}
