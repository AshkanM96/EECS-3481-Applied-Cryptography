package symmetric;

import util.Binary;
import util.CryptoTools;
import util.Hex;

/**
 * OTP 3-pass decryption use case.
 * 
 * @author Ashkan Moatamed
 */
public class B1Q4 {
	/**
	 * Dependencies: <code>
	 * 		1. util.Binary
	 * 		2. util.Hex
	 * 		3. util.CryptoTools
	 * </code>
	 */

	/**
	 * The first transmission.
	 */
	private static final byte[] TRANSMISSION_1 = Hex.toBytes("0A4F0C08003503492F247442105B5757");

	/**
	 * The second transmission.
	 */
	private static final byte[] TRANSMISSION_2 = Hex.toBytes("5E2769286B507A69494B066252343579");

	/**
	 * The third transmission.
	 */
	private static final byte[] TRANSMISSION_3 = Hex.toBytes("170708454B1116002A2E2111725F5000");

	/**
	 * Prevent instantiation.
	 */
	private B1Q4() {
		// Empty by design.
	}

	@Override
	protected Object clone() throws CloneNotSupportedException { // semi-copy
		throw new CloneNotSupportedException();
	}

	public static void main(String[] args) {
		/**
		 * We know the following: <br>
		 * 1. <code>B1Q4.TRANSMISSION_1</code> is <code>plaintext xor key_alice</code> <br>
		 * 2. <code>B1Q4.TRANSMISSION_2</code> is <code>B1Q4.TRANSMISSION_1 xor key_bob</code> <br>
		 * 3. <code>B1Q4.TRANSMISSION_3</code> is <code>B1Q4.TRANSMISSION_2 xor key_alice</code> <br>
		 * 
		 * Therefore, we can conclude the following: <br>
		 * 1. <code>B1Q4.TRANSMISSION_1</code> is <code>plaintext xor key_alice</code> <br>
		 * 2. <code>B1Q4.TRANSMISSION_2</code> is <code>plaintext xor key_alice xor key_bob</code> <br>
		 * 3. <code>B1Q4.TRANSMISSION_3</code> is <code>plaintext xor key_bob</code> <br>
		 * 
		 * Thus, Eve can just xor the first and second transmission to find <code>key_bob</code> and then
		 * xor that with the third transmission to find the <code>plaintext</code>.
		 */
		final byte[] key_bob = Binary.xor(B1Q4.TRANSMISSION_1, B1Q4.TRANSMISSION_2);
		final byte[] plaintext = Binary.xor(B1Q4.TRANSMISSION_3, key_bob);
		System.out.println("The plaintext is:\n" + CryptoTools.toString(plaintext) + "\n");
	}
}
