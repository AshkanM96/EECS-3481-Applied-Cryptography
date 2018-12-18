package tests.test4;

import java.math.BigInteger;

public class T4Q4 {
	public static void main(String[] args) {
		final BigInteger p = new BigInteger("565209496520876299");
		final BigInteger p_minus_1 = p.subtract(BigInteger.ONE);
		final BigInteger f = BigInteger.valueOf(13);
		final BigInteger[] quot_rem = p_minus_1.divideAndRemainder(f);
		assert (quot_rem[1].signum() == 0);

		final BigInteger quotient = quot_rem[0];
		System.out.println("(p - 1) / " + f + " == " + quotient);

		final BigInteger g = BigInteger.valueOf(101);

		final BigInteger A = new BigInteger("10938180900183");
		final BigInteger expect = A.modPow(quot_rem[0], p);
		System.out.println("Method 2: " + expect);

		final BigInteger base = g.modPow(quotient, p);
		for (BigInteger a_0 = BigInteger.ZERO, tmp = null; !a_0.equals(f); a_0 = a_0.add(BigInteger.ONE)) {
			tmp = base.modPow(a_0, p);
			if (tmp.equals(expect)) {
				System.out.println("a_0 == " + a_0);
			}
		}
	}
}