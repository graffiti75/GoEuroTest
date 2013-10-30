package br.android.goeurotest.utils;

import android.annotation.SuppressLint;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * DateUtils class.
 * 
 * @author Rodrigo Cericatto
 * @since Oct 29, 2013
 */
public class DateUtils {
	
	//--------------------------------------------------
	// Methods
	//--------------------------------------------------

	/**
	 * Format a date.
	 * 
	 * @param date The date to be formatted.
	 * @param dayOrMonth Is true if the date is day or month (and false if is year).
	 * @return The formatted date.
	 */
	public static String formatDateTerm(Integer date, Boolean dayOrMonth) {
		String format = null;
		
		// Gets the right format.
		if (dayOrMonth) {
			format = "00";
		} else {
			format = "0000";
		}

		// Performs the formatting.
		DecimalFormat df = new DecimalFormat(format);
		return df.format(date);
	}
	
	/**
	 * Gets current date and time.
	 * 
	 * @return The current date and time, in formatted string.
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getCurrentDate() {
		Long currentTime = Calendar.getInstance().getTimeInMillis();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String date = formatter.format(currentTime);
        
        return date;
	}
}