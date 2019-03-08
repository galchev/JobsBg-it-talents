package com.example.demo.interfaces;

import java.math.BigInteger;
import java.security.MessageDigest;

public interface IStringToSha1 {
	static public String stringToSha1(String string) {		
		
		String sha1 = "";
		
		try {
			
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			
	        digest.reset();
	        
	        digest.update(string.getBytes("utf8"));
	        
	        sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
	        
		} catch (Exception e){
			
			return "invalid String to sha1";
			
		}
		return sha1;
	}
}
