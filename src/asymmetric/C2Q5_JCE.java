package asymmetric;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import util.CryptoTools;
import util.RSACipherEng;

/**
 * RSA decryption use case with JCE.
 * 
 * @author Ashkan Moatamed
 */
public class C2Q5_JCE {
	/**
	 * Dependencies: <code>
	 * 		1. util.RSACipherEng
	 * 		2. util.CryptoTools
	 * </code>
	 */

	/**
	 * Known modulus.
	 */
	private static final BigInteger N = new BigInteger(
			"94587468335128982981605019776781234618384857805657005686084562260910788622013722070926491690843853690071248130134427832324966728582532832363221542231787068203763027067400082835394459857525017707284768411819006776211493735326500782954621660256501187035611332577696332459049538105669711385995976912007767106063");

	/**
	 * Known public key.
	 */
	private static final BigInteger E = new BigInteger("74327");

	/**
	 * Known private key.
	 */
	private static final BigInteger D = new BigInteger(
			"7289370196881601766768920490284861650464951706793000236386405648425161747775298344104658393385359209126267833888223695609366844098655240542152017354442883676634193191857568369042999854440242050353181703706753485749165295123694487676952198090537385200990850805837963871485320168470788328336240930212290450023");

	/**
	 * Known ciphertext.
	 */
	private static final BigInteger CIPHERTEXT = new BigInteger(
			"87014856975716299121085087309577038316883175412853820115551293556230488405826385706604303724175236985573832006395540199066061101502996745421485579743246846982636317440505885092956723199407403632041108913018671613508572002898008615700858579079601105011909417884801902333329415712320494308682279897714456370814");

	/**
	 * Prevent instantiation.
	 */
	private C2Q5_JCE() {
		// Empty by design.
	}

	@Override
	protected Object clone() throws CloneNotSupportedException { // semi-copy
		throw new CloneNotSupportedException();
	}

	public static void main(String[] args)
			throws InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		// Create the cipher engine with the appropriate attributes.
		final RSACipherEng engine = RSACipherEng.knownKeys(C2Q5_JCE.N, C2Q5_JCE.E, C2Q5_JCE.D);

		// Decrypt the ciphertext using the engine to get the plaintext.
		final byte[] ciphertext = C2Q5_JCE.CIPHERTEXT.toByteArray();
		final byte[] plaintext = engine.decrypt(ciphertext);
		System.out.println("The plaintext is:\n" + CryptoTools.toString(plaintext).trim() + "\n");
	}
}
