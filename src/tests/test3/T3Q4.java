package tests.test3;

import java.math.BigInteger;

import util.MillerRabin;

public class T3Q4 {
	/**
	 * Dependencies: <code>
	 * 		1. util.MillerRabin
	 * </code>
	 */

	public static void main(String[] args) {
		final BigInteger n = new BigInteger("1033931178476059651954862004553");
		final BigInteger b = BigInteger.valueOf(2);
		System.out.println("n == " + n + "\n");

		final MillerRabin m = new MillerRabin(n);
		m.test(b, true);
	}
}
