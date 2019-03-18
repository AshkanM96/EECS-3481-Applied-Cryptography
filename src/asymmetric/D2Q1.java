package asymmetric;

import util.AlgebraUtil;
import util.MathUtil;

/**
 * Discrete logarithm and primitive root use case.
 * 
 * @author Ashkan Moatamed
 */
public class D2Q1 {
	/**
	 * Dependencies: <code>
	 * 		1. util.MathUtil
	 * 		2. util.AlgebraUtil
	 * </code>
	 */

	/**
	 * Prevent instantiation.
	 */
	private D2Q1() {
		// Empty by design.
	}

	public static void main(String[] args) {
		System.out.println(
				"Discrete logarithm of 3 to base 2 mod 11 is " + MathUtil.discreteLogLinearSearch(2, 3, 11) + ".\n");
		System.out.println(
				"Discrete logarithm of 3 to base 5 mod 11 is " + MathUtil.discreteLogLinearSearch(5, 3, 11) + ".\n");
		System.out.println("2 is" + (AlgebraUtil.isPrimitiveRoot(2, 11) ? "" : " not") + " a primitive root of 11.\n");
	}
}
