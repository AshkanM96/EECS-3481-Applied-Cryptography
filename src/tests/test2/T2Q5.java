package tests.test2;

import util.Binary;
import util.CryptoTools;
import util.Hex;

public class T2Q5 {
	/**
	 * Dependencies: <code>
	 * 		1. util.Binary
	 * 		2. util.Hex
	 * 		3. util.CryptoTools
	 * </code>
	 */

	public static void main(String[] args) {
		/**
		 * We know the following: <br>
		 * 1. <code>ct1</code> is <code>key xor pt1</code>. <br>
		 * 2. <code>ct2</code> is <code>key xor pt2</code>. <br>
		 * 
		 * Therefore, we can conclude the following: <br>
		 * 1. <code>ct1 xor ct2</code> is <code>pt1 xor pt2</code>.
		 */
		final byte[] ct1 = Hex.toBytes("3D48044D421349564A1541054204131C");
		final byte[] ct2 = Hex.toBytes("3D54024D531442454C0941175404150A");
		final byte[] xored = Binary.xor(ct1, ct2);

		final byte[] pt1_sub = "bridge".getBytes();
		final int crib_len = pt1_sub.length, maxI = xored.length - crib_len + 1;
		final byte[] pt2_sub = new byte[crib_len];
		for (int i = 0; i != maxI; ++i) {
			System.arraycopy(xored, i, pt2_sub, 0, crib_len);
			Binary.xorEquals(pt2_sub, pt1_sub);

			System.out.println(CryptoTools.toString(pt2_sub));
		}
	}
}
