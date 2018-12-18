package hash;

/**
 * Generalized birthday problem use case.
 * 
 * @author Ashkan Moatamed
 */
public class D1Q4 {
	/**
	 * No dependencies.
	 */

	/**
	 * Prevent instantiation.
	 */
	private D1Q4() {
		// Empty by design.
	}

	/**
	 * @param d
	 *            the number of days in a year
	 * 
	 * @param n
	 *            the number of people
	 * 
	 * @return The probability of at least two people having the same birthday.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(d <= 0) || (n < 0)</code>
	 */
	public static double atLeastOneCollision(int d, int n) throws IllegalArgumentException {
		if ((d <= 0) || (n < 0)) {
			throw new IllegalArgumentException();
		}

		// Handle the simple special cases.
		if (n <= 1) {
			// Probability of at least one collision with 0 or 1 people is just 0.
			return 0;
		} else if (n >= d) {
			/*
			 * Probability of at least one collision with d or more people is just 1 by the Pigeon Hole
			 * Principle.
			 */
			return 1;
		}

		// First compute the probability of no collisions and then subtract that from 100%.
		double probNoCollisions = 1;
		for (int i = 0; i != n; ++i) {
			// probNoCollisions *= ((double) (d - i)) / d;
			probNoCollisions *= 1 - ((double) i) / d;
		}
		return ((1 - probNoCollisions) * 100);
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
		System.out.println(D1Q4.atLeastOneCollision(10000, 100));
	}
}
