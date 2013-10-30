package br.android.goeurotest.utils;

/**
 * A group of utility methods.
 * 
 * @author Rodrigo Cericatto
 * @since Oct 11, 2013
 */
public class Utils {

	//--------------------------------------------------
	// Attributes
	//--------------------------------------------------
	
	private static Float mDensity;

	//--------------------------------------------------
	// Methods
	//--------------------------------------------------	
	
	/**
	 * Returns the class name without package, if any.
	 * 
	 * @param clazz
	 * @return
	 */
	
	/**
	 * Returns the package name of the given class.
	 * 
	 * @param clazz
	 * @return The package name.
	 */
	public static String getPackageName(Class<?> clazz) {
		return clazz.getPackage().getName();
	}
	
	/**
	 * Saves density of the screen.
	 * 
	 * @param density Screen density
	 */
	public static void setScreenDensity(Float density) {
		mDensity = density;
	}
}