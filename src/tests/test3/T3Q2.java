package tests.test3;

import java.math.BigInteger;

public class T3Q2 {
	/**
	 * No dependencies.
	 */

	public static void main(String[] args) {
		final BigInteger phi = new BigInteger(
				"8584037913642434144111279062847405921823163865842701785008602377400681495147541519557274092429073976252689387304835782258785521935078205581766754116919200");
		final BigInteger q = new BigInteger(
				"87020952829623092932322362936864583897972618059974315662422560067745889600571");
		final BigInteger e = new BigInteger("65537");
		final BigInteger ct = new BigInteger(
				"5336019289981478689566707388017059861970003804192073118004850697169969021900148427418826906792632100179956987922885292941036461810045707376376634287718694");

		final BigInteger d = e.modInverse(phi);

		final BigInteger q_minus_1 = q.subtract(BigInteger.ONE);
		final BigInteger p_minus_1 = phi.divide(q_minus_1);
		final BigInteger p = p_minus_1.add(BigInteger.ONE);
		final BigInteger n = p.multiply(q);

		final byte[] pt = ct.modPow(d, n).toByteArray();
		System.out.println(new String(pt).trim());
	}
}
