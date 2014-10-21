package com.thoughtfocus.leave.test;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

public class GenerateEncryptedPassword {
	
	private static final String ALGO = "AES";
	 private static final byte[] keyValue = 
	        new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't','S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String password="Gen111Mansi";
		try {
			System.out.println(encrypt(password));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	/**
	 * Encrypt the password before saving	
	 * @param Data
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }
	/**
	 * Key Generator for encryption and decryption
	 * @return
	 * @throws Exception
	 */
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
}

}
