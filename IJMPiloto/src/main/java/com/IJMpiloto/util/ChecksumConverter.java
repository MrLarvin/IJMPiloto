package com.IJMpiloto.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class ChecksumConverter {

	private static Formatter formatter;

	public static String getSHA(byte[] file) throws NoSuchAlgorithmException{
	    MessageDigest md = MessageDigest.getInstance("SHA-1"); 
	    return byteArray2Hex(md.digest(file));
	}

	private static String byteArray2Hex(final byte[] hash) {
	    formatter = new Formatter();
	    for (byte b : hash) {
	        formatter.format("%02x", b);
	    }
	    return formatter.toString();
	}
}
