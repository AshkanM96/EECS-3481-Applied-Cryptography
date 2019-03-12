package tests.test3;

import java.math.BigInteger;

public class T3Q1 {
	/**
	 * No dependencies.
	 */

	public static void main(String[] args) {
		final BigInteger n = new BigInteger("40057384521392344387295509139");
		// final BigInteger e = BigInteger.valueOf(101L);
		final BigInteger d = new BigInteger("21416819447080840026842414141");
		final BigInteger ct = new BigInteger("159911625443136560226876180");

		final byte[] pt = ct.modPow(d, n).toByteArray();
		System.out.println(new String(pt).trim());
	}
}
