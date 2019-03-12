package tests.test4;

import java.math.BigInteger;

public class T4Q4 {
	/**
	 * No dependencies.
	 */

	public static void main(String[] args) {
		final BigInteger p = new BigInteger("565209496520876299");
		final BigInteger g = BigInteger.valueOf(101L);
		final BigInteger f = BigInteger.valueOf(13L);

		final BigInteger p_minus_1 = p.subtract(BigInteger.ONE);
		final BigInteger[] qr = p_minus_1.divideAndRemainder(f);
		assert (qr[1].signum() == 0);
		final BigInteger quotient = qr[0];
		System.out.println("(p - 1) / " + f + " == " + quotient);

		final BigInteger A = new BigInteger("10938180900183");
		final BigInteger expect = A.modPow(quotient, p);
		System.out.println("expect == " + expect);

		final BigInteger base = g.modPow(quotient, p);
		System.out.println("base == g^quotient (mod p) == " + base + "\n");
		for (BigInteger a_0 = BigInteger.ZERO, tmp = null; !a_0.equals(f); a_0 = a_0.add(BigInteger.ONE)) {
			tmp = base.modPow(a_0, p);
			if (tmp.equals(expect)) {
				System.out.printf("a_0 == %2s: base^a_0 (mod p) == expect\n", a_0.toString());
			}
		}
	}
}
