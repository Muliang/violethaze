package violethaze.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * signature utility class
 * @author violethaze
 *
 */
public class SignatureUtil {
	// token
	private static final String TOKEN = "tftwechat";

	/**
	 * check validation of signature
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return true if the signature is valid, else false
	 */
	public static boolean checkSignature(String signature, String timestamp,
			String nonce) {
		String[] tempArray = { TOKEN, timestamp, nonce };
		Arrays.sort(tempArray);

		// catenate token, timestamp, nonce
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < tempArray.length; i++) {
			content.append(tempArray[i]);
		}

		MessageDigest md = null;
		String encryptedStr = null;

		try {
			// Encrypt
			md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(content.toString().getBytes());

			encryptedStr = toHexString(digest); // encrypted string
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return encryptedStr != null ? encryptedStr.equals(signature) : false;
	}

	/**
	 * convert a byte array to a hex string
	 * 
	 * @param array
	 *            : a byte array
	 * @return hex string
	 */
	private static String toHexString(byte[] array) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(
					1, 3));
		}
		return sb.toString();
	}
}
