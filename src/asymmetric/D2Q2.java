package asymmetric;

import java.util.HashSet;

/**
 * Primitive root use case.
 * 
 * @author Ashkan Moatamed
 */
public class D2Q2 {
	/**
	 * No dependencies.
	 */

	/**
	 * Prevent instantiation.
	 */
	private D2Q2() {
		// Empty by design.
	}

	public static void main(String[] args) {
		final int p = 31;

		// The order of the subgroup generated by a primitive root.
		final int primitiveOrder = p - 1;
		final HashSet<Integer> subgroup = new HashSet<Integer>(primitiveOrder);

		// For prime p, all primitive roots are in [2, p - 2].
		System.out.println("Primitive roots of " + p + " are:");
		final int maxAlpha = p - 2;
		for (int alpha = 2; alpha <= maxAlpha; ++alpha) {
			// Fill the subgroup from the current alpha.
			for (int i = 0, power = 1; i != primitiveOrder; ++i) {
				power = (power * alpha) % p;
				subgroup.add(power);
			}

			// Check if the subgroup has the primitive order.
			if (subgroup.size() == primitiveOrder) {
				System.out.println(alpha);
			}

			// Clear the subgroup from the previous alpha.
			subgroup.clear();
		}
		System.out.println();
	}
}