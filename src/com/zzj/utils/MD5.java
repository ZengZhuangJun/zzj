package com.zzj.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 * @author JUM
 *
 */
public class MD5 
{
	public static String crypt(String str)
	{
		if (str == null || str.length() == 0) {
			throw new IllegalArgumentException("String to encript cannot be null or zero length");
		}
		StringBuffer hexString = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] hash = md.digest();
			
			for (int i = 0; i < hash.length; i++) {
				if ((0xff & hash[i]) < 0x10) {
					hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
				}				
				else {
					hexString.append(Integer.toHexString(0xFF & hash[i]));
				}				
			}
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hexString.toString();
	}
	
	public static void main(String args[]){
		String a=MD5.crypt("74ccf4da-d7a0-4a74-9bb4-ec4cd0bbcdbf"+"111111");
		System.out.println(a);
		String b=MD5.crypt("11ee0c09-4bd0-4b15-b41c-b418f561b298"+"111111");
		System.out.println(b);
		String c=MD5.crypt("9337dd3f-53ac-4ad4-99dc-2c7f904e1952"+"111111");
		System.out.println(c);
	}
}
