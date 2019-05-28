package helper;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encoding {

    static public String encode(String input) throws UnsupportedEncodingException {
        return new String(input.getBytes("iso-8859-1"), "UTF-8");
    }

    static public String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    static public String generateToken(String str) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            return "Error";
        }
        return bytesToHex(digest.digest(str.getBytes(StandardCharsets.UTF_8)));
    }
}
