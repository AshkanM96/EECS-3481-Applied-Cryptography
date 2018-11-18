package tests.test3;

import java.math.BigInteger;

public class T3Q3 {
	/**
	 * No dependencies.
	 */

	public static void main(String[] args) {
		final BigInteger n1 = new BigInteger("1055827021987");
		final BigInteger n2 = new BigInteger("973491987203");
		assert n1.gcd(n2).equals(BigInteger.ONE);

		final BigInteger y1 = new BigInteger("247775631513");
		final BigInteger y2 = new BigInteger("43368118785");

		final BigInteger n1Inv = n1.modInverse(n2);
		final BigInteger n2Inv = n2.modInverse(n1);

		final BigInteger n1n2 = n1.multiply(n2);

		final BigInteger lhs = y1.multiply(n2).multiply(n2Inv);
		final BigInteger rhs = y2.multiply(n1).multiply(n1Inv);

		final BigInteger x = (lhs.add(rhs)).mod(n1n2);
		System.out.println(x);
	}
}
