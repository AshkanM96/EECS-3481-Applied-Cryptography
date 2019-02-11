package tests.test4;

import java.security.MessageDigest;

public class York232 {
	private final byte[] msg;
	private final byte[] sha;

	public York232(byte[] msg) throws Exception {
		if ((this.msg = msg) == null) {
			throw new NullPointerException();
		}
		this.sha = MessageDigest.getInstance("Sha-224").digest(this.msg);
	}

	public byte[] getHash() {
		final byte[] result = new byte[29];
		if (this.msg.length == 28) {
			// Map 28 byte messages to 0 || msg.
			result[0] = 0;
			System.arraycopy(this.msg, 0, result, 1, 28);
		} else {
			// Map non-28 byte messages to 1 || SHA-224(msg).
			result[0] = 1;
			System.arraycopy(this.sha, 0, result, 1, 28);
		}
		return result;
	}
}
