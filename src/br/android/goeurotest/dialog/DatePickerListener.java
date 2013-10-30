package br.android.goeurotest.dialog;

import br.android.goeurotest.utils.DateUtils;
import android.app.DatePickerDialog.OnDateSetListener;
import android.widget.DatePicker;
import android.widget.TextView;

/**
 * Updates the text view after the DatePickerDialog be used.
 * 
 * @author Rodrigo Cericatto
 * @since Oct 29, 2013
 */
public class DatePickerListener implements OnDateSetListener {
	
	//--------------------------------------------------
	// Attributes
	//--------------------------------------------------

	private TextView mTextView;

	//--------------------------------------------------
	// Constructor
	//--------------------------------------------------
	
	public DatePickerListener(TextView textView) {
		mTextView = textView;
	}
	
	//--------------------------------------------------
	// Listener
	//--------------------------------------------------
	
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		String text = DateUtils.formatDateTerm(dayOfMonth, true) + "/" + DateUtils.formatDateTerm(monthOfYear + 1, true) + "/" + DateUtils.formatDateTerm(year, false);
		mTextView.setText(text);
	}
}