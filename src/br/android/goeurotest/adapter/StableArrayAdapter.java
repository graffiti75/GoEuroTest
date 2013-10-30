package br.android.goeurotest.adapter;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * StableArrayAdapter class.
 * 
 * @author Rodrigo Cericatto
 * @since Oct 29, 2013
 */
public class StableArrayAdapter extends ArrayAdapter<String> {

	//--------------------------------------------------
	// Attributes
	//--------------------------------------------------
	
	private HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

	//--------------------------------------------------
	// Constructor
	//--------------------------------------------------
	
	public StableArrayAdapter(Context context, int textViewResourceId,
			List<String> objects) {
		super(context, textViewResourceId, objects);
		for (int i = 0; i < objects.size(); ++i) {
			mIdMap.put(objects.get(i), i);
		}
	}

	//--------------------------------------------------
	// Adapter Methods
	//--------------------------------------------------
	
	@Override
	public long getItemId(int position) {
		String item = getItem(position);
		return mIdMap.get(item);
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}
}