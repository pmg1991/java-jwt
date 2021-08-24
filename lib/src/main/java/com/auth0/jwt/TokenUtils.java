package com.auth0.jwt;

import com.auth0.jwt.exceptions.JWTDecodeException;

abstract class TokenUtils {

    /**
     * Splits the given token on the "." chars into a String array with 3 parts.
     *
     * @param token the string to split.
     * @return the array representing the 3 parts of the token.
     * @throws JWTDecodeException if the Token doesn't have 3 parts.
     */
    static String[] splitToken(String token) throws JWTDecodeException {
        String[] parts = fastSplitDot(token);
        if (parts.length == 2 && token.endsWith(".")) {
            //Tokens with alg='none' have empty String as Signature.
            parts = new String[]{parts[0], parts[1], ""};
        }
        if (parts.length != 3) {
            throw new JWTDecodeException(String.format("The token was expected to have 3 parts, but got %s.", parts.length));
        }
        return parts;
    }
    public static String[] fastSplitDot(final String text) {
  	  
	    final ArrayList<String> result = new ArrayList<String>();
	    final int len = text.length();
	    int tokenStart = 0;	   
	    char[] chars = text.toCharArray();
	    for (int pos = 0; pos < len; ++pos) {
	        char c = chars[pos];
	        if ( c == '.') {	           
	            result.add(text.substring(tokenStart, pos));	               
	            tokenStart = pos + 1;
	        }
	    }
	    if (tokenStart < len) {
	        result.add(text.substring(tokenStart));
	    }	    
	    return result.toArray(new String[result.size()]);
	}
}
