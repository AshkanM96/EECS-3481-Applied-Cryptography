package tests.test3;

import java.math.BigInteger;

import util.BigIntUtil;

public class T3Q3 {
	/**
	 * Dependencies: <code>
	 * 		1. util.BigIntUtil
	 * </code>
	 */

	public static void main(String[] args) {
		final BigInteger m1 = new BigInteger("1055827021987");
		final BigInteger m2 = new BigInteger("973491987203");
		final BigInteger n1 = new BigInteger("247775631513");
		final BigInteger n2 = new BigInteger("43368118785");
		System.out.println(BigIntUtil.crt(n1, m1, n2, m2, true, false)[0]);
	}
}
