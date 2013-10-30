package br.android.goeurotest.utils;

/**
 * StringUtils class.
 * 
 * @author Rodrigo Cericatto
 * @since Oct 11, 2013
 */
public class StringUtils {
	
	/**
	 * Return true if string is null or has a length equal 0.
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isEmpty(CharSequence string) {
		return string == null || string.length() == 0;
	}
}