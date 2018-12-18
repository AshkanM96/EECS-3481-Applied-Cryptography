package tests.test4;

import java.security.MessageDigest;

public class York232 {
	private byte[] msg;
	private byte[] sha;

	public York232(byte[] msg) throws Exception {
		this.msg = msg;
		this.sha = MessageDigest.getInstance("Sha-224").digest(this.msg);
	}

	public byte[] getHash() {
		byte[] result = new byte[29];
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
