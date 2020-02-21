package util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;

/**
 * Utility math methods in addition to MathUtil but mainly focused on statistics.
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
	 * The default scale to be used for BigDecimal operations.
	 */
	public static final int DEFAULT_SCALE = 20;

	/**
	 * The maximum recursion depth of the functions in this class.
	 */
	public static final int MAX_RECUR_DEPTH = 5000;

	/**
	 * Fibonacci numbers map (n -> Fn).
	 */
	protected static HashMap<Integer, BigInteger> fibonaccis = null;

	/**
	 * Factorials map (n -> n!).
	 */
	protected static HashMap<Integer, BigInteger> factorials = null;

	/**
	 * Subfactorials map (n -> !n).
	 */
	protected static HashMap<Integer, BigInteger> subfactorials = null;

	/**
	 * nPr values map (n -> r -> nPr).
	 */
	protected static HashMap<Integer, HashMap<Integer, BigInteger>> nPrs = null;

	/**
	 * nCr values map (n -> r -> nCr).
	 */
	protected static HashMap<Integer, HashMap<Integer, BigInteger>> nCrs = null;

	/**
	 * S2(n, r) values map (n -> r -> S2(n, r)).
	 */
	protected static HashMap<Integer, HashMap<Integer, BigInteger>> nS2rs = null;

	/**
	 * S1(n, r) values map (n -> r -> (signed S1(n, r))).
	 */
	protected static HashMap<Integer, HashMap<Integer, BigInteger>> nS1rs = null;

	/**
	 * Prevent instantiation.
	 */
	private StatUtil() {
		// Empty by design.
	}

	@Override
	protected Object clone() throws CloneNotSupportedException { // semi-copy
		throw new CloneNotSupportedException();
	}

	/**
	 * Clear <code>StatUtil.fibonaccis</code>.
	 */
	public static void clear_fibonaccis() {
		if (StatUtil.fibonaccis != null) {
			StatUtil.fibonaccis.clear();
			// See implementation of fibonacciMap(int) for the reasoning about the following.
			StatUtil.fibonaccis.put(0, BigInteger.ZERO); // Require: F0 == 0
			StatUtil.fibonaccis.put(1, BigInteger.ONE); // Require: F1 == 1
		}
	}

	/**
	 * Clear <code>StatUtil.factorials</code>.
	 */
	public static void clear_factorials() {
		if (StatUtil.factorials != null) {
			StatUtil.factorials.clear();
			// See implementation of factorialMap(int) for the reasoning about the following.
			StatUtil.factorials.put(0, BigInteger.ONE); // Require: 0! == 1
			StatUtil.factorials.put(1, BigInteger.ONE); // Optimization: 1! == 1
		}
	}

	/**
	 * Clear <code>StatUtil.subfactorials</code>.
	 */
	public static void clear_subfactorials() {
		if (StatUtil.subfactorials != null) {
			StatUtil.subfactorials.clear();
			// See implementation of subfactorialMap(int) for the reasoning about the following.
			StatUtil.subfactorials.put(0, BigInteger.ONE); // Require: !0 == 1
			StatUtil.subfactorials.put(1, BigInteger.ZERO); // Optimization: !1 == 0
		}
	}

	/**
	 * Clear <code>StatUtil.nPrs</code>.
	 */
	public static void clear_nPrs() {
		if (StatUtil.nPrs != null) {
			StatUtil.nPrs.clear();
		}
	}

	/**
	 * Clear <code>StatUtil.nCrs</code>.
	 */
	public static void clear_nCrs() {
		if (StatUtil.nCrs != null) {
			StatUtil.nCrs.clear();
		}
	}

	/**
	 * Clear <code>StatUtil.nS2rs</code>.
	 */
	public static void clear_nS2rs() {
		if (StatUtil.nS2rs != null) {
			StatUtil.nS2rs.clear();
		}
	}

	/**
	 * Clear <code>StatUtil.nS1rs</code>.
	 */
	public static void clear_nS1rs() {
		if (StatUtil.nS1rs != null) {
			StatUtil.nS1rs.clear();
		}
	}

	/**
	 * Linearly compute the <code>n<sup>th</sup></code> fibonacci number. <br>
	 * Precondition: <code>(n != null) && (0 <= n)</code>
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
		// i.e., 1 <= n

		// Compute Fn in the loop and then return it.
		BigInteger Fi_minus_1 = BigInteger.ZERO, Fi = BigInteger.ONE, Fi_plus_1 = BigInteger.ONE;
		for (BigInteger i = BigInteger.ONE; !i.equals(n); i = i.add(BigInteger.ONE)) {
			// Fi_plus_1 = Fi + Fi_minus_1 for all 1 <= i
			Fi_plus_1 = Fi.add(Fi_minus_1);
			Fi_minus_1 = Fi;
			Fi = Fi_plus_1;
		}
		return Fi_plus_1;
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
	public static BigInteger fibonacciLinear(BigInteger n) throws IllegalArgumentException {
		if (n.signum() == -1) { // i.e., n < 0
			throw new IllegalArgumentException();
		}
		// 0 <= n
		return StatUtil.fibonacciLinearFixedInput(n);
	}

	/**
	 * Linearly compute the <code>n<sup>th</sup></code> fibonacci number. <br>
	 * Precondition: <code>0 <= n</code>
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
		// i.e., 1 <= n

		/**
		 * Don't delegate to <code>StatUtil.fibonacciLinearFixedInput(BigInteger)</code> since arithmetic
		 * operations on a <code>primitive type (i.e., long)</code>, are much faster than arithmetic
		 * operations on a <code>BigInteger</code>.
		 */

		// Compute Fn in the loop and then return it.
		BigInteger Fi_minus_1 = BigInteger.ZERO, Fi = BigInteger.ONE, Fi_plus_1 = BigInteger.ONE;
		for (long i = 1L; i != n; ++i) {
			// Fi_plus_1 = Fi + Fi_minus_1 for all 1 <= i
			Fi_plus_1 = Fi.add(Fi_minus_1);
			Fi_minus_1 = Fi;
			Fi = Fi_plus_1;
		}
		return Fi_plus_1;
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
		// 0 <= n
		return StatUtil.fibonacciLinearFixedInput(n);
	}

	/**
	 * Recursively retrieve/compute_and_store the <code>n<sup>th</sup></code> fibonacci number. <br>
	 * Precondition: <code>StatUtil.fibonaccis != null</code> <br>
	 * Precondition: <code>StatUtil.fibonaccis.get(0) == BigInteger.ZERO</code> <br>
	 * Precondition: <code>StatUtil.fibonaccis.get(1) == BigInteger.ONE</code> <br>
	 * Precondition: <code>0 <= n</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return The <code>n<sup>th</sup></code> fibonacci number.
	 */
	protected static BigInteger fibonacciMapFixedInput(int n) {
		BigInteger result = StatUtil.fibonaccis.get(n);
		if (result == null) { // i.e., (n != 0) && (n != 1)
			// i.e., 2 <= n

			// Recursively retrieve/compute_and_store Fn_minus_1 then compute Fn from it.
			// Note that by recursing on n - 1, we also recursively compute (and store) Fn_minus_2 if needed.
			// Fn = Fn_minus_1 + Fn_minus_2 for all 2 <= n
			result = StatUtil.fibonacciMapFixedInput(n - 1).add(StatUtil.fibonaccis.get(n - 2));

			// Store Fn so that it is not recomputed later.
			StatUtil.fibonaccis.put(n, result);
		}
		return result;
	}

	/**
	 * Recursively retrieve/compute_and_store the <code>n<sup>th</sup></code> fibonacci number.
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return The <code>n<sup>th</sup></code> fibonacci number.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n < 0</code>
	 */
	public static BigInteger fibonacciMap(int n) throws IllegalArgumentException {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		// 0 <= n

		// Handle the simple special cases.
		if (n < 2) { // i.e., (n == 0) || (n == 1)
			// (F0 == 0) && (F1 == 1)
			// The following valueOf call just retrieves BigInteger.ZERO or BigInteger.ONE.
			return BigInteger.valueOf(n);
		}
		// 2 <= n

		// Initialize StatUtil.fibonaccis if needed. Executed at most once.
		if (StatUtil.fibonaccis == null) {
			StatUtil.fibonaccis = new HashMap<Integer, BigInteger>();
			StatUtil.fibonaccis.put(0, BigInteger.ZERO); // Require: F0 == 0
			StatUtil.fibonaccis.put(1, BigInteger.ONE); // Require: F1 == 1
		}

		/*
		 * Break the recursive call into smaller pieces of size StatUtil.MAX_RECUR_DEPTH each, so that the
		 * entire function call does not unnecessarily get a stack overflow.
		 */
		for (int i = StatUtil.MAX_RECUR_DEPTH; i < n; i += StatUtil.MAX_RECUR_DEPTH) {
			StatUtil.fibonacciMapFixedInput(i);
		}
		// Recursively retrieve/compute_and_store Fn then return it.
		return StatUtil.fibonacciMapFixedInput(n);
	}

	/**
	 * Linearly compute <code>n factorial</code> (i.e., <code>n!</code>). <br>
	 * Precondition: <code>(n != null) && (0 <= n)</code>
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
		// i.e., 1 <= n

		// Compute n! in the loop and then return it.
		BigInteger result = BigInteger.ONE;
		for (BigInteger i = BigInteger.ONE; !i.equals(n); /* Update inside. */) {
			// i! = i * (i - 1)! for all 1 <= i
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
		// 0 <= n
		return StatUtil.factorialLinearFixedInput(n);
	}

	/**
	 * Linearly compute <code>n factorial</code> (i.e., <code>n!</code>). <br>
	 * Precondition: <code>0 <= n</code>
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
		// i.e., 1 <= n

		// Compute n! in the loop and then return it.
		BigInteger result = BigInteger.ONE;
		for (long i = 1L; i != n; /* Update inside. */) {
			// i! = i * (i - 1)! for all 1 <= i
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
		// 0 <= n
		return StatUtil.factorialLinearFixedInput(n);
	}

	/**
	 * Recursively retrieve/compute_and_store <code>n factorial</code> (i.e., <code>n!</code>). <br>
	 * Precondition: <code>StatUtil.factorials != null</code> <br>
	 * Precondition: <code>StatUtil.factorials.get(0) == BigInteger.ONE</code> <br>
	 * Precondition: <code>0 <= n</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>n factorial</code> (i.e., <code>n!</code>).
	 */
	protected static BigInteger factorialMapFixedInput(int n) {
		BigInteger result = StatUtil.factorials.get(n);
		if (result == null) { // i.e., n != 0
			// i.e., 1 <= n

			// Recursively retrieve/compute_and_store (n - 1)! then compute n! from it.
			// n! = n * (n - 1)! for all 1 <= n
			// Recurse first then construct BigInteger object for optimization.
			result = StatUtil.factorialMapFixedInput(n - 1).multiply(BigInteger.valueOf(n));

			// Store n! so that it is not recomputed later.
			StatUtil.factorials.put(n, result);
		}
		return result;
	}

	/**
	 * Recursively retrieve/compute_and_store <code>n factorial</code> (i.e., <code>n!</code>).
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>n factorial</code> (i.e., <code>n!</code>).
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n < 0</code>
	 */
	public static BigInteger factorialMap(int n) throws IllegalArgumentException {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		// 0 <= n

		// Handle the simple special cases.
		if (n < 2) { // i.e., (n == 0) || (n == 1)
			// (0! == 1) && (1! == 1)
			return BigInteger.ONE;
		}
		// 2 <= n

		// Initialize StatUtil.factorials if needed. Executed at most once.
		if (StatUtil.factorials == null) {
			StatUtil.factorials = new HashMap<Integer, BigInteger>();
			StatUtil.factorials.put(0, BigInteger.ONE); // Require: 0! == 1
			StatUtil.factorials.put(1, BigInteger.ONE); // Optimization: 1! == 1
		}

		/*
		 * Break the recursive call into smaller pieces of size StatUtil.MAX_RECUR_DEPTH each, so that the
		 * entire function call does not unnecessarily get a stack overflow.
		 */
		for (int i = StatUtil.MAX_RECUR_DEPTH; i < n; i += StatUtil.MAX_RECUR_DEPTH) {
			StatUtil.factorialMapFixedInput(i);
		}
		// Recursively retrieve/compute_and_store n! then return it.
		return StatUtil.factorialMapFixedInput(n);
	}

	/**
	 * Linearly compute <code>n subfactorial</code> (i.e., <code>!n</code>). <br>
	 * Precondition: <code>(n != null) && (0 <= n)</code>
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
		// i.e., 1 <= n

		// Compute !n in the loop and then return it.
		BigInteger result = BigInteger.ZERO;
		for (BigInteger i = BigInteger.ONE; !i.equals(n); /* Update inside. */) {
			// !i = i * !(i - 1) + (-1)^i for all 1 <= i
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
		// 0 <= n
		return StatUtil.subfactorialLinearFixedInput(n);
	}

	/**
	 * Linearly compute <code>n subfactorial</code> (i.e., <code>!n</code>). <br>
	 * Precondition: <code>0 <= n</code>
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
		// i.e., 1 <= n

		// Compute !n in the loop and then return it.
		BigInteger result = BigInteger.ZERO;
		for (long i = 1L; i != n; /* Update inside. */) {
			// !i = i * !(i - 1) + (-1)^i for all 1 <= i
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
		// 0 <= n
		return StatUtil.subfactorialLinearFixedInput(n);
	}

	/**
	 * Recursively retrieve/compute_and_store <code>n subfactorial</code> (i.e., <code>!n</code>). <br>
	 * Precondition: <code>StatUtil.subfactorials != null</code> <br>
	 * Precondition: <code>StatUtil.subfactorials.get(0) == BigInteger.ONE</code> <br>
	 * Precondition: <code>0 <= n</code>
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>n subfactorial</code> (i.e., <code>!n</code>).
	 */
	protected static BigInteger subfactorialMapFixedInput(int n) {
		BigInteger result = StatUtil.subfactorials.get(n);
		if (result == null) { // i.e., n != 0
			// i.e., 1 <= n

			// Recursively retrieve/compute_and_store !(n - 1) then compute !n from it.
			// !n = n * !(n - 1) + (-1)^n for all 1 <= n
			// Recurse first then construct BigInteger object for optimization.
			result = StatUtil.subfactorialMapFixedInput(n - 1).multiply(BigInteger.valueOf(n));
			/**
			 * Don't do <code>(n &= 1) != 0</code> since we need the value of <code>n</code> to remain
			 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
			 * will mutate <code>n</code>.
			 */
			result = ((n & 1) != 0) ? result.subtract(BigInteger.ONE) : result.add(BigInteger.ONE);

			// Store !n so that it is not recomputed later.
			StatUtil.subfactorials.put(n, result);
		}
		return result;
	}

	/**
	 * Recursively retrieve/compute_and_store <code>n subfactorial</code> (i.e., <code>!n</code>).
	 * 
	 * @param n
	 *            the given number
	 * 
	 * @return <code>n subfactorial</code> (i.e., <code>!n</code>).
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>n < 0</code>
	 */
	public static BigInteger subfactorialMap(int n) throws IllegalArgumentException {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		// 0 <= n

		// Handle the simple special cases.
		if (n < 2) { // i.e., (n == 0) || (n == 1)
			// (!0 == 1) && (!1 == 0)
			// The following valueOf call just retrieves BigInteger.ONE or BigInteger.ZERO.
			return BigInteger.valueOf(1L - n);
		}
		// 2 <= n

		// Initialize StatUtil.subfactorials if needed. Executed at most once.
		if (StatUtil.subfactorials == null) {
			StatUtil.subfactorials = new HashMap<Integer, BigInteger>();
			StatUtil.subfactorials.put(0, BigInteger.ONE); // Require: !0 == 1
			StatUtil.subfactorials.put(1, BigInteger.ZERO); // Optimization: !1 == 0
		}

		/*
		 * Break the recursive call into smaller pieces of size StatUtil.MAX_RECUR_DEPTH each, so that the
		 * entire function call does not unnecessarily get a stack overflow.
		 */
		for (int i = StatUtil.MAX_RECUR_DEPTH; i < n; i += StatUtil.MAX_RECUR_DEPTH) {
			StatUtil.subfactorialMapFixedInput(i);
		}
		// Recursively retrieve/compute_and_store !n then return it.
		return StatUtil.subfactorialMapFixedInput(n);
	}

	/**
	 * Linearly compute <code>nPr</code>. <br>
	 * Precondition: <code>(n != null) && (0 <= n)</code> <br>
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
		// (0 <= n) && (0 <= r)
		final int n_cmp_r = n.compareTo(r);
		if (n_cmp_r < 0) { // i.e., n < r
			throw new IllegalArgumentException();
		}
		// r <= n

		if (r.signum() == 0) { // i.e., r == 0
			/*
			 * This case is only an optimization since nP0 == 1 and so the loop will never execute but extra
			 * unnecessary work will be done to arrive at the same result.
			 */
			return BigInteger.ONE;
		}
		// r != 0
		// i.e., 1 <= r
		return StatUtil.nPrLinearFixedInput(n, r);
	}

	/**
	 * Linearly compute <code>nPr</code>. <br>
	 * Precondition: <code>0 <= n</code> <br>
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
		// (0 <= n) && (0 <= r)
		if (n < r) {
			throw new IllegalArgumentException();
		}
		// r <= n

		if (r == 0L) {
			/*
			 * This case is only an optimization since nP0 == 1 and so the loop will never execute but extra
			 * unnecessary work will be done to arrive at the same result.
			 */
			return BigInteger.ONE;
		}
		// r != 0
		// i.e., 1 <= r
		return StatUtil.nPrLinearFixedInput(n, r);
	}

	/**
	 * Linearly compute <code>nPr</code> but save previously computed values.
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
	public static BigInteger nPrMap(int n, int r) throws IllegalArgumentException {
		// Initialize StatUtil.nPrs if needed. Executed at most once.
		if (StatUtil.nPrs == null) {
			StatUtil.nPrs = new HashMap<Integer, HashMap<Integer, BigInteger>>();
		}

		// Retrieve/compute_and_store nPr then return it.
		HashMap<Integer, BigInteger> rMap = StatUtil.nPrs.get(n);
		BigInteger result = (rMap == null) ? null : rMap.get(r);
		if (result == null) {
			// Linearly compute nPr and then store it so that it is not recomputed later.
			result = StatUtil.nPrLinear(n, r);
			if (rMap == null) {
				rMap = new HashMap<Integer, BigInteger>();
				rMap.put(r, result);
				StatUtil.nPrs.put(n, rMap);
			} else {
				rMap.put(r, result);
			}
		}
		return result;
	}

	/**
	 * Linearly compute <code>nCr</code>. <br>
	 * Precondition: <code>(n != null) && (0 <= n)</code> <br>
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
		for (BigInteger i = BigInteger.ONE, n_minus_i = n.subtract(BigInteger.ONE); !i.equals(r); i = i
				.add(BigInteger.ONE), n_minus_i = n_minus_i.subtract(BigInteger.ONE)) {
			numerator = numerator.multiply(n_minus_i);
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
		// (0 <= n) && (0 <= r)
		final int n_cmp_r = n.compareTo(r);
		if (n_cmp_r < 0) { // i.e., n < r
			throw new IllegalArgumentException();
		}
		// r <= n

		// Handle the simple special case.
		if (n_cmp_r == 0) { // i.e., n == r
			/**
			 * This case is only an optimization since nCn == 1. The following code will handle it after setting
			 * <code>r == n_minus_r == n - n == 0</code> but we might as well handle it earlier and save some
			 * computations.
			 */
			return BigInteger.ONE;
		}
		// r < n

		// nCr == nC(n - r)
		final BigInteger n_minus_r = n.subtract(r);
		if (n_minus_r.compareTo(r) < 0) { // i.e., n - r < r
			r = n_minus_r;
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
		// i.e., 2 <= r
		return StatUtil.nCrLinearFixedInput(n, r);
	}

	/**
	 * Linearly compute <code>nCr</code>. <br>
	 * Precondition: <code>0 <= n</code> <br>
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
		// (0 <= n) && (0 <= r)
		if (n < r) {
			throw new IllegalArgumentException();
		}
		// r <= n

		// Handle the simple special case.
		if (n == r) {
			/**
			 * This case is only an optimization since nCn == 1. The following code will handle it after setting
			 * <code>r == n_minus_r == n - n == 0</code> but we might as well handle it earlier and save some
			 * computations.
			 */
			return BigInteger.ONE;
		}
		// r < n

		// nCr == nC(n - r)
		final long n_minus_r = n - r;
		if (n_minus_r < r) { // i.e., n - r < r
			r = n_minus_r;
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
		// i.e., 2 <= r
		return StatUtil.nCrLinearFixedInput(n, r);
	}

	/**
	 * Linearly compute <code>nCr</code> but save previously computed values.
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
	public static BigInteger nCrMap(int n, int r) throws IllegalArgumentException {
		// Initialize StatUtil.nCrs if needed. Executed at most once.
		if (StatUtil.nCrs == null) {
			StatUtil.nCrs = new HashMap<Integer, HashMap<Integer, BigInteger>>();
		}

		// Retrieve/compute_and_store nCr then return it.
		HashMap<Integer, BigInteger> rMap = StatUtil.nCrs.get(n);
		BigInteger result = (rMap == null) ? null : rMap.get(r);
		if (result == null) {
			// Linearly compute nCr and then store it so that it is not recomputed later.
			result = StatUtil.nCrLinear(n, r);
			if (rMap == null) {
				rMap = new HashMap<Integer, BigInteger>();
				rMap.put(r, result);
				StatUtil.nCrs.put(n, rMap);
			} else {
				rMap.put(r, result);
			}
		}
		return result;
	}

	/**
	 * Linearly compute <code>S2(n, r)</code>. <br>
	 * Precondition: <code>1 <= n</code> <br>
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
		BigInteger numerator = BigInteger.valueOf(r).pow(n), denominator = BigInteger.ONE, tmp = null;
		for (int i = 1; i <= r; ++i) {
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
			denominator = denominator.multiply(BigInteger.valueOf(i));
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
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(n < 0) || (r < 0) || (n < r)</code>
	 */
	public static BigInteger stirling2Linear(int n, int r) throws IllegalArgumentException {
		if ((n < 0) || (r < 0)) {
			throw new IllegalArgumentException();
		}
		// (0 <= n) && (0 <= r)
		if (n < r) {
			throw new IllegalArgumentException();
		}
		// r <= n

		// Handle the simple special cases.
		if ((n == 0) || (r == 1) || (n == r)) {
			// S2(0, 0) == S2(n, 1) == S2(n, n) == 1
			return BigInteger.ONE;
		} else if (r == 0) {
			// S2(n, 0) for n != 0 is 0
			return BigInteger.ZERO;
		}
		// (n != 0) && (r != 1) && (n != r) && (r != 0)
		// i.e., (1 <= n) && (2 <= r) && (r <= n - 1)
		return StatUtil.stirling2LinearFixedInput(n, r);
	}

	/**
	 * Linearly compute <code>S2(n, r)</code> but save previously computed values.
	 * 
	 * @param n
	 *            the given number of objects
	 * 
	 * @param r
	 *            the given sample size
	 * 
	 * @return <code>S2(n, r)</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(n < 0) || (r < 0) || (n < r)</code>
	 */
	public static BigInteger stirling2Map(int n, int r) throws IllegalArgumentException {
		if ((n < 0) || (r < 0)) {
			throw new IllegalArgumentException();
		}
		// (0 <= n) && (0 <= r)
		if (n < r) {
			throw new IllegalArgumentException();
		}
		// r <= n

		// Handle the simple special cases.
		if ((n == 0) || (r == 1) || (n == r)) {
			// S2(0, 0) == S2(n, 1) == S2(n, n) == 1
			return BigInteger.ONE;
		} else if (r == 0) {
			// S2(n, 0) for n != 0 is 0
			return BigInteger.ZERO;
		}
		// (n != 0) && (r != 1) && (n != r) && (r != 0)
		// i.e., (1 <= n) && (2 <= r) && (r <= n - 1)

		// Initialize StatUtil.nS2rs if needed. Executed at most once.
		if (StatUtil.nS2rs == null) {
			StatUtil.nS2rs = new HashMap<Integer, HashMap<Integer, BigInteger>>();
		}

		// Retrieve/compute_and_store S2(n, r) then return it.
		HashMap<Integer, BigInteger> rMap = StatUtil.nS2rs.get(n);
		BigInteger result = (rMap == null) ? null : rMap.get(r);
		if (result == null) {
			/**
			 * Linearly compute S2(n, r) and then store it so that it is not recomputed later. Note that we
			 * cannot set <code>result</code> to <code>StatUtil.stirling2LinearFixedInput(n, r)</code> here,
			 * since we want to use the map version of <code>nCr</code>.
			 */
			BigInteger numerator = BigInteger.valueOf(r).pow(n), denominator = BigInteger.ONE, tmp = null;
			for (int i = 1; i <= r; ++i) {
				/**
				 * Note that <code>BigInteger.valueOf(long)</code> calls are much cheaper than performing arithmetic
				 * operations on a <code>BigInteger</code> since those are fully general operations for potentially
				 * big integers. Furthermore, due to <code>BigInteger</code> being immutable, its arithmetic
				 * operations always create a new object and so using <code>BigInteger.valueOf(long)</code> is
				 * neither adding nor removing an object creation but it is cheaper because of the faster primitive
				 * arithmetic operations and the simplicity of the implementation of
				 * <code>BigInteger.valueOf(long)</code>.
				 */
				tmp = StatUtil.nCrMap(r, i).multiply(BigInteger.valueOf(r - i).pow(n));
				/**
				 * Don't do <code>(i &= 1) != 0</code> since we need the value of <code>i</code> to remain
				 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
				 * will mutate <code>i</code>.
				 */
				numerator = ((i & 1) != 0) ? numerator.subtract(tmp) : numerator.add(tmp);
				denominator = denominator.multiply(BigInteger.valueOf(i));
			}
			// S2(n, r) = (sum (-1)^i rCi (r - i)^n from i = 0 to i = r) / r!
			result = numerator.divide(denominator);
			if (rMap == null) {
				rMap = new HashMap<Integer, BigInteger>();
				rMap.put(r, result);
				StatUtil.nS2rs.put(n, rMap);
			} else {
				rMap.put(r, result);
			}
		}
		return result;
	}

	/**
	 * Linearly compute <code>S1(n, r)</code>. <br>
	 * Precondition: <code>1 <= n</code> <br>
	 * Precondition: <code>(1 <= r) && (r <= n - 1)</code>
	 * 
	 * @param n
	 *            the given number of objects
	 * 
	 * @param r
	 *            the given sample size
	 * 
	 * @param signed
	 *            specifies whether the signed value of <code>S1(n, r)</code> should be returned
	 * 
	 * @return <code>S1(n, r)</code>.
	 */
	protected static BigInteger stirling1LinearFixedInput(int n, int r, boolean signed) {
		/**
		 * <pre>
		 * <code>
		 * (signed S1(n, r)) = sum (-1)^i (n - 1 + i)C(n - r + i) (2n - r)C(n - r - i) S2(n - r + i, i) from i = 0 to i = n - r
		 * </code>
		 * </pre>
		 */
		final int n_minus_1 = n - 1, n_minus_r = n - r, two_n_minus_r = n + n_minus_r;
		BigInteger result = BigInteger.ZERO, tmp = null;
		for (int i = 0; i <= n_minus_r; ++i) {
			tmp = StatUtil.nCrLinear(n_minus_1 + i, n_minus_r + i)
					.multiply(StatUtil.nCrLinear(two_n_minus_r, n_minus_r - i))
					.multiply(StatUtil.stirling2Linear(n_minus_r + i, i));
			/**
			 * Don't do <code>(i &= 1) != 0</code> since we need the value of <code>i</code> to remain
			 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
			 * will mutate <code>i</code>.
			 */
			result = ((i & 1) != 0) ? result.subtract(tmp) : result.add(tmp);
		}
		// (signed S1(n, r)) = (-1)^(n - r) (unsigned S1(n, r))
		return ((signed || // i.e., signed || NumUtil.isEven(n - r)
				(((((long) n) - r) & 1L) == 0L)) ? result : result.negate());
	}

	/**
	 * Linearly compute <code>S1(n, r)</code>.
	 * 
	 * @param n
	 *            the given number of objects
	 * 
	 * @param r
	 *            the given sample size
	 * 
	 * @param signed
	 *            specifies whether the signed value of <code>S1(n, r)</code> should be returned
	 * 
	 * @return <code>S1(n, r)</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(n < 0) || (r < 0) || (n < r)</code>
	 */
	public static BigInteger stirling1Linear(int n, int r, boolean signed) throws IllegalArgumentException {
		if ((n < 0) || (r < 0)) {
			throw new IllegalArgumentException();
		}
		// (0 <= n) && (0 <= r)
		if (n < r) {
			throw new IllegalArgumentException();
		}
		// r <= n

		// Handle the simple special cases.
		if ((n == 0) || (n == r)) {
			// S1(0, 0) == S1(n, n) == 1
			return BigInteger.ONE;
		} else if (r == 0) {
			// S1(n, 0) for n != 0 is 0
			return BigInteger.ZERO;
		}
		// (n != 0) && (n != r) && (r != 0)
		// i.e., (1 <= n) && (1 <= r) && (r <= n - 1)
		return StatUtil.stirling1LinearFixedInput(n, r, signed);
	}

	/**
	 * Linearly compute <code>S1(n, r)</code>.
	 * 
	 * @param n
	 *            the given number of objects
	 * 
	 * @param r
	 *            the given sample size
	 * 
	 * @return <code>StatUtil.stirling1Linear(n, r, true)</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(n < 0) || (r < 0) || (n < r)</code>
	 */
	public static BigInteger stirling1Linear(int n, int r) throws IllegalArgumentException {
		return StatUtil.stirling1Linear(n, r, true);
	}

	/**
	 * Linearly compute <code>S1(n, r)</code> but save previously computed values.
	 * 
	 * @param n
	 *            the given number of objects
	 * 
	 * @param r
	 *            the given sample size
	 * 
	 * @param signed
	 *            specifies whether the signed value of <code>S1(n, r)</code> should be returned
	 * 
	 * @return <code>S1(n, r)</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(n < 0) || (r < 0) || (n < r)</code>
	 */
	public static BigInteger stirling1Map(int n, int r, boolean signed) throws IllegalArgumentException {
		if ((n < 0) || (r < 0)) {
			throw new IllegalArgumentException();
		}
		// (0 <= n) && (0 <= r)
		if (n < r) {
			throw new IllegalArgumentException();
		}
		// r <= n

		// Handle the simple special cases.
		if ((n == 0) || (n == r)) {
			// S1(0, 0) == S1(n, n) == 1
			return BigInteger.ONE;
		} else if (r == 0) {
			// S1(n, 0) for n != 0 is 0
			return BigInteger.ZERO;
		}
		// (n != 0) && (n != r) && (r != 0)
		// i.e., (1 <= n) && (1 <= r) && (r <= n - 1)

		// Initialize StatUtil.nS1rs if needed. Executed at most once.
		if (StatUtil.nS1rs == null) {
			StatUtil.nS1rs = new HashMap<Integer, HashMap<Integer, BigInteger>>();
		}

		// Retrieve/compute_and_store S1(n, r) then return it.
		HashMap<Integer, BigInteger> rMap = StatUtil.nS1rs.get(n);
		BigInteger result = (rMap == null) ? null : rMap.get(r);
		if (result == null) {
			/**
			 * Linearly compute S1(n, r) and then store it so that it is not recomputed later. Note that we
			 * cannot set <code>result</code> to <code>StatUtil.stirling1LinearFixedInput(n, r, true)</code>
			 * here, since we want to use the map versions of <code>nCr</code> and <code>S2(n, r)</code>.
			 */
			final int n_minus_1 = n - 1, n_minus_r = n - r, two_n_minus_r = n + n_minus_r;
			result = BigInteger.ZERO;
			BigInteger tmp = null;
			for (int i = 0; i <= n_minus_r; ++i) {
				tmp = StatUtil.nCrMap(n_minus_1 + i, n_minus_r + i)
						.multiply(StatUtil.nCrMap(two_n_minus_r, n_minus_r - i))
						.multiply(StatUtil.stirling2Map(n_minus_r + i, i));
				/**
				 * Don't do <code>(i &= 1) != 0</code> since we need the value of <code>i</code> to remain
				 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
				 * will mutate <code>i</code>.
				 */
				result = ((i & 1) != 0) ? result.subtract(tmp) : result.add(tmp);
			}
			/**
			 * <pre>
			 * <code>
			 * (signed S1(n, r)) = sum (-1)^i (n - 1 + i)C(n - r + i) (2n - r)C(n - r - i) S2(n - r + i, i) from i = 0 to i = n - r
			 * </code>
			 * </pre>
			 */
			if (rMap == null) {
				rMap = new HashMap<Integer, BigInteger>();
				rMap.put(r, result);
				StatUtil.nS1rs.put(n, rMap);
			} else {
				rMap.put(r, result);
			}
		}
		// (signed S1(n, r)) = (-1)^(n - r) (unsigned S1(n, r))
		return ((signed || // i.e., signed || NumUtil.isEven(n - r)
				(((((long) n) - r) & 1L) == 0L)) ? result : result.negate());
	}

	/**
	 * Linearly compute <code>S1(n, r)</code> but save previously computed values.
	 * 
	 * @param n
	 *            the given number of objects
	 * 
	 * @param r
	 *            the given sample size
	 * 
	 * @return <code>StatUtil.stirling1Map(n, r, true)</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(n < 0) || (r < 0) || (n < r)</code>
	 */
	public static BigInteger stirling1Map(int n, int r) throws IllegalArgumentException {
		return StatUtil.stirling1Map(n, r, true);
	}

	/**
	 * Precondition: <code>2 <= d</code> <br>
	 * Precondition: <code>(0 <= n) && (n <= d - 1)</code> <br>
	 * Precondition: <code>0 <= scale</code>
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
		/**
		 * Probability of no collision is as follows:
		 * 
		 * <pre>
		 * <code>
		 * dPn / (d^n)
		 * </code>
		 * </pre>
		 */
		final BigInteger numerator = StatUtil.nPrLinearFixedInput(d, n);
		final BigDecimal denominator = BigDecimal.valueOf(d).pow(n);
		// Probability of at least one collision is 1 - probability of no collision.
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
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(d < 1) || (n < 0) || (scale < 0)</code>
	 */
	public static BigDecimal birthday1Linear(int d, int n, int scale, boolean outOf100)
			throws IllegalArgumentException {
		if ((d < 1) || (n < 0) || (scale < 0)) {
			throw new IllegalArgumentException();
		}
		// (1 <= d) && (0 <= n) && (0 <= scale)

		// Handle the simple special cases.
		if (n < 2) {
			// Probability of at least one collision with <= 1 people is 0.
			return BigDecimal.ZERO;
		} else if (d <= n) {
			// Probability of at least one collision with d or more people is 1 by the Pigeon Hole Principle.
			return (outOf100 ? StatUtil.HUNDRED_ZERO_SCALE : BigDecimal.ONE);
		}
		// (1 < n) && (n < d)
		// i.e., (1 < n) && (n < d) && (2 <= d)
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
	 * @return <code>StatUtil.birthday1Linear(d, n, scale, true)</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(d < 1) || (n < 0) || (scale < 0)</code>
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
	 * @return <code>StatUtil.birthday1Linear(d, n, StatUtil.DEFAULT_SCALE)</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(d < 1) || (n < 0) || (scale < 0)</code>
	 */
	public static BigDecimal birthday1Linear(int d, int n) throws IllegalArgumentException {
		return StatUtil.birthday1Linear(d, n, StatUtil.DEFAULT_SCALE);
	}

	/**
	 * Precondition: <code>2 <= d</code> <br>
	 * Precondition: <code>(0 <= n) && (n <= d - 1)</code> <br>
	 * Precondition: <code>0 <= scale</code>
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
	protected static BigDecimal birthday1MapFixedInput(int d, int n, int scale, boolean outOf100) {
		/**
		 * Probability of no collision is as follows:
		 * 
		 * <pre>
		 * <code>
		 * dPn / (d^n)
		 * </code>
		 * </pre>
		 */
		final BigInteger numerator = StatUtil.nPrMap(d, n);
		final BigDecimal denominator = BigDecimal.valueOf(d).pow(n);
		// Probability of at least one collision is 1 - probability of no collision.
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
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(d < 1) || (n < 0) || (scale < 0)</code>
	 */
	public static BigDecimal birthday1Map(int d, int n, int scale, boolean outOf100) throws IllegalArgumentException {
		if ((d < 1) || (n < 0) || (scale < 0)) {
			throw new IllegalArgumentException();
		}
		// (1 <= d) && (0 <= n) && (0 <= scale)

		// Handle the simple special cases.
		if (n < 2) {
			// Probability of at least one collision with <= 1 people is 0.
			return BigDecimal.ZERO;
		} else if (d <= n) {
			// Probability of at least one collision with d or more people is 1 by the Pigeon Hole Principle.
			return (outOf100 ? StatUtil.HUNDRED_ZERO_SCALE : BigDecimal.ONE);
		}
		// (1 < n) && (n < d)
		// i.e., (1 < n) && (n < d) && (2 <= d)
		return StatUtil.birthday1MapFixedInput(d, n, scale, outOf100);
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
	 * @return <code>StatUtil.birthday1Map(d, n, scale, true)</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(d < 1) || (n < 0) || (scale < 0)</code>
	 */
	public static BigDecimal birthday1Map(int d, int n, int scale) throws IllegalArgumentException {
		return StatUtil.birthday1Map(d, n, scale, true);
	}

	/**
	 * @param d
	 *            the given number of days
	 * 
	 * @param n
	 *            the given number of people
	 * 
	 * @return <code>StatUtil.birthday1Map(d, n, StatUtil.DEFAULT_SCALE)</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(d < 1) || (n < 0) || (scale < 0)</code>
	 */
	public static BigDecimal birthday1Map(int d, int n) throws IllegalArgumentException {
		return StatUtil.birthday1Map(d, n, StatUtil.DEFAULT_SCALE);
	}

	/**
	 * Precondition: <code>2 <= d</code> <br>
	 * Precondition: <code>(1 <= n) && (1 <= m)</code> <br>
	 * Precondition: <code>Math.addExact(m, n) <= d</code> <br>
	 * Precondition: <code>0 <= scale</code>
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
		 * (sum sum S2(m, i) * S2(n, j) * (product d - k from k = 0 to k = i + j - 1) from j = 1 to j = n from i = 1 to i = m) / (d^(n + m))
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
		// Probability of at least one collision is 1 - probability of no collision.
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
	 *             || ((m < d) && (n < d) && (d < (((long) m) + n)))</code>
	 */
	public static BigDecimal birthday2Linear(int d, int m, int n, int scale, boolean outOf100)
			throws IllegalArgumentException {
		if ((d < 1) || (m < 0) || (n < 0) || (scale < 0)) {
			throw new IllegalArgumentException();
		}
		// (1 <= d) && (0 <= m) && (0 <= n) && (0 <= scale)

		// Handle the simple special cases.
		if ((m == 0) || (n == 0)) {
			// Probability of at least one collision with 0 people in at least one of the types is 0.
			return BigDecimal.ZERO;
		} else if ((d <= m) && (d <= n)) {
			/*
			 * Probability of at least one collision with d or more people in each type is 1 by the Pigeon Hole
			 * Principle.
			 */
			return (outOf100 ? StatUtil.HUNDRED_ZERO_SCALE : BigDecimal.ONE);
		}
		// (m != 0) && (n != 0) && (m < d) && (n < d)
		// i.e., (1 <= m) && (1 <= n) && (max(m, n) < d)

		if (d < (((long) m) + n)) {
			throw new IllegalArgumentException();
		}
		// m + n <= d
		// i.e., (m + n <= d) && (2 <= d)
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
	 * @return <code>StatUtil.birthday2Linear(d, m, n, scale, true)</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(d < 1) || (m < 0) || (n < 0) || (scale < 0)
	 *             || ((m < d) && (n < d) && (d < (((long) m) + n)))</code>
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
	 * @return <code>StatUtil.birthday2Linear(d, m, n, StatUtil.DEFAULT_SCALE)</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(d < 1) || (m < 0) || (n < 0)
	 *             || ((m < d) && (n < d) && (d < (((long) m) + n)))</code>
	 */
	public static BigDecimal birthday2Linear(int d, int m, int n) throws IllegalArgumentException {
		return StatUtil.birthday2Linear(d, m, n, StatUtil.DEFAULT_SCALE);
	}

	/**
	 * Precondition: <code>2 <= d</code> <br>
	 * Precondition: <code>(1 <= n) && (1 <= m)</code> <br>
	 * Precondition: <code>Math.addExact(m, n) <= d</code> <br>
	 * Precondition: <code>0 <= scale</code>
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
	protected static BigDecimal birthday2MapFixedInput(int d, int m, int n, int scale, boolean outOf100) {
		/**
		 * Probability of no collision is as follows:
		 * 
		 * <pre>
		 * <code>
		 * (sum sum S2(m, i) * S2(n, j) * (product d - k from k = 0 to k = i + j - 1) from j = 1 to j = n from i = 1 to i = m) / (d^(n + m))
		 * </code>
		 * </pre>
		 */
		final BigDecimal denominator = BigDecimal.valueOf(d).pow(m + n);
		BigInteger numerator = BigInteger.ZERO;
		BigInteger tmp = null;
		for (int i = 1; i <= m; ++i) {
			for (int j = 1; j <= n; ++j) {
				tmp = StatUtil.stirling2Map(m, i).multiply(StatUtil.stirling2Map(n, j));
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
		// Probability of at least one collision is 1 - probability of no collision.
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
	 *             || ((m < d) && (n < d) && (d < (((long) m) + n)))</code>
	 */
	public static BigDecimal birthday2Map(int d, int m, int n, int scale, boolean outOf100)
			throws IllegalArgumentException {
		if ((d < 1) || (m < 0) || (n < 0) || (scale < 0)) {
			throw new IllegalArgumentException();
		}
		// (1 <= d) && (0 <= m) && (0 <= n) && (0 <= scale)

		// Handle the simple special cases.
		if ((m == 0) || (n == 0)) {
			// Probability of at least one collision with 0 people in at least one of the types is 0.
			return BigDecimal.ZERO;
		} else if ((d <= m) && (d <= n)) {
			/*
			 * Probability of at least one collision with d or more people in each type is 1 by the Pigeon Hole
			 * Principle.
			 */
			return (outOf100 ? StatUtil.HUNDRED_ZERO_SCALE : BigDecimal.ONE);
		}
		// (m != 0) && (n != 0) && (m < d) && (n < d)
		// i.e., (1 <= m) && (1 <= n) && (max(m, n) < d)

		if (d < (((long) m) + n)) {
			throw new IllegalArgumentException();
		}
		// m + n <= d
		// i.e., (m + n <= d) && (2 <= d)
		return StatUtil.birthday2MapFixedInput(d, m, n, scale, outOf100);
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
	 * @return <code>StatUtil.birthday2Map(d, m, n, scale, true)</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(d < 1) || (m < 0) || (n < 0) || (scale < 0)
	 *             || ((m < d) && (n < d) && (d < (((long) m) + n)))</code>
	 */
	public static BigDecimal birthday2Map(int d, int m, int n, int scale) throws IllegalArgumentException {
		return StatUtil.birthday2Map(d, m, n, scale, true);
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
	 * @return <code>StatUtil.birthday2Map(d, m, n, StatUtil.DEFAULT_SCALE)</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(d < 1) || (m < 0) || (n < 0)
	 *             || ((m < d) && (n < d) && (d < (((long) m) + n)))</code>
	 */
	public static BigDecimal birthday2Map(int d, int m, int n) throws IllegalArgumentException {
		return StatUtil.birthday2Map(d, m, n, StatUtil.DEFAULT_SCALE);
	}
}
