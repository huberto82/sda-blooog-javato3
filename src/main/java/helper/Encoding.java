package helper;

import java.io.UnsupportedEncodingException;

public class Encoding {
    static public String encode(String input) throws UnsupportedEncodingException {
        return new String(input.getBytes("iso-8859-1"), "UTF-8");
    }
}
