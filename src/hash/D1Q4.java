package hash;

import util.StatUtil;

/**
 * Generalized birthday problem use case.
 * 
 * @author Ashkan Moatamed
 */
public class D1Q4 {
	/**
	 * Dependencies: <code>
	 * 		1. util.StatUtil
	 * </code>
	 */

	/**
	 * Prevent instantiation.
	 */
	private D1Q4() {
		// Empty by design.
	}

	@Override
	protected Object clone() throws CloneNotSupportedException { // semi-copy
		throw new CloneNotSupportedException();
	}

	public static void main(String[] args) {
		/**
		 * Every York student has an ID made up of 9 digits. We will assume that these ID's are uniformly
		 * distributed and we will define the following hash function on them: h(id) = id (mod 10,000). In a
		 * room of 100 students, what is the exact probability of at least one collision in this hash
		 * function. <br>
		 * 
		 * Since, the number of 9 digit numbers (i.e., 999,999,999 - 000,000,000 + 1 = 1,000,000,000) is
		 * divisible by the modulus (i.e., 10,000) then the hash values are also uniformly distributed since
		 * the original values (i.e., IDs) were uniformly distributed.
		 */
		System.out.println(StatUtil.birthday1Linear(10000, 100));
	}
}
