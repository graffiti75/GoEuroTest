package br.android.goeurotest.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.TextView;
import br.android.goeurotest.R;
import br.android.goeurotest.model.City;

/**
 * CityAdapter class.
 * 
 * @author Rodrigo Cericatto
 * @since Aug 26, 2013
 */
public class CityAdapter extends BaseAdapter implements ListAdapter, Filterable {
	
	//--------------------------------------------------
	// Attributes
	//--------------------------------------------------
	
	// Attributes of the list adapter.
	private Context mContext;
	private List<City> mList;
	
	// -------------------------------------------------
	// Constructor
	//--------------------------------------------------
	
	public CityAdapter(Context context, List<City> list) {
		this.mContext = context;
		this.mList = list;
	}
	
	// -------------------------------------------------
	// Array Methods
	//--------------------------------------------------
	
	public int getCount() {
		if (mList != null && mList.size() > 0) {
			return mList.size();
		}
		return 0;
	}
	
	public Object getItem(int position) {
		if (position > -1 && mList != null && mList.size() > 0)
			return mList.get(position);
		return null;
	}
	
	public long getItemId(int position) {
		if (position > -1)
			return position;
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// First, let's create a new convertView if needed.
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.auto_complete_listview, null);
		}
		
		// Refresh text value of new list unity name.
		City data = mList.get(position);
		TextView textName = (TextView)convertView.findViewById(R.id.city_name_auto_complete);
		textName.setText(data.getName());
		
		return convertView;
	}

	@Override
	public Filter getFilter() {
		return null;
	}
}