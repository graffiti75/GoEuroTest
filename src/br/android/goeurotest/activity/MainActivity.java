package br.android.goeurotest.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;
import br.android.goeurotest.R;
import br.android.goeurotest.adapter.StableArrayAdapter;
import br.android.goeurotest.api.OperationResult;
import br.android.goeurotest.api.OperationResult.ResultType;
import br.android.goeurotest.dialog.DatePickerListener;
import br.android.goeurotest.dialog.DialogHelper;
import br.android.goeurotest.manager.ContentManager;
import br.android.goeurotest.model.City;
import br.android.goeurotest.model.CityDistance;
import br.android.goeurotest.model.CityDistanceComparator;
import br.android.goeurotest.tasks.Notifiable;
import br.android.goeurotest.utils.DateUtils;
import br.android.goeurotest.utils.MapUtils;

/**
 * MainActivity class.
 * 
 * @author Rodrigo Cericatto
 * @since Oct 21, 2013
 */
public class MainActivity extends Activity implements Notifiable {

	//--------------------------------------------------
	// Attributes
	//--------------------------------------------------

	private Context mContext;
	
    private AutoCompleteTextView mCityOriginAutoComplete;
    private AutoCompleteTextView mCityDestinyAutoComplete;
    private TextView mDatePicker;

    private Boolean mIsOrigin = true;
    
	//--------------------------------------------------
	// Activity Life Cycle Methods
	//--------------------------------------------------
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mContext = MainActivity.this;
        setLayoutComponents();
        initializeCityOriginSearch();
        initializeDatePicker();
    }
    
	//--------------------------------------------------
	// Methods
	//--------------------------------------------------    
    
    public void setLayoutComponents() {
    	mCityOriginAutoComplete = (AutoCompleteTextView)findViewById(R.id.id_origin_city_search);
    	mCityDestinyAutoComplete = (AutoCompleteTextView)findViewById(R.id.id_destiny_city_search);
    	mDatePicker = (TextView)findViewById(R.id.id_date_picker);
    }
    
    /**
     * Initializes the AutoCompleteTextView's of the cities.
     */
    public void initializeCityOriginSearch() {
		mCityOriginAutoComplete.setText("");
		mCityDestinyAutoComplete.setText("");
		mCityOriginAutoComplete.requestFocus();

		mCityOriginAutoComplete.addTextChangedListener(new TextWatcher() { 
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				mIsOrigin = true;
				ContentManager.getInstance().getCityList(MainActivity.this, mCityOriginAutoComplete.getText().toString());
			}
			
			public void afterTextChanged(Editable editable) {} 
			public void beforeTextChanged(CharSequence cs, int i, int j, int k) {} 
		});
		
		mCityDestinyAutoComplete.addTextChangedListener(new TextWatcher() { 
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				mIsOrigin = false;
				ContentManager.getInstance().getCityList(MainActivity.this, mCityDestinyAutoComplete.getText().toString());
			}
			
			public void afterTextChanged(Editable editable) {} 
			public void beforeTextChanged(CharSequence cs, int i, int j, int k) {} 
		});
    }
    
    /**
     * Initializes the DatePicker.
     */
    public void initializeDatePicker() {
    	CharSequence sequence = DateUtils.getCurrentDate();
    	mDatePicker.setText(sequence);
    	mDatePicker.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DatePickerDialog datePickerDialog = null;
				datePickerDialog = DialogHelper.showDatePickerDialog(mContext, new DatePickerListener(mDatePicker), 2013, 11, 1);
				datePickerDialog = null;
			}
		});
    }
    
    /**
     * Sets the adapter.
     */
    public void setAdapter() {
   		List<City> list = ContentManager.getInstance().getCachedCityList();
    	Double[] myLatLong = MapUtils.getLatLong(this);
    	List<CityDistance> orderedList = orderCityByLatLong(myLatLong, list);
    	List<String> listCityNames = getCityNames(orderedList);
    	
    	if (mIsOrigin) {
    		StableArrayAdapter originAdapter = new StableArrayAdapter(mContext,android.R.layout.simple_list_item_1, listCityNames);
    		mCityOriginAutoComplete.setAdapter(originAdapter);
    	} else {
    		StableArrayAdapter destinyAdapter = new StableArrayAdapter(mContext,android.R.layout.simple_list_item_1, listCityNames);
    		mCityDestinyAutoComplete.setAdapter(destinyAdapter);
    	}
    }

    /**
     * Order the cities according to their distances to my location.
     * 
     * @param myLatLong My latitude and longitude.
     * @param list The City list. 
     * @return The CityDistance list. 
     */
    public List<CityDistance> orderCityByLatLong(Double[] myLatLong, List<City> list) {
    	List<CityDistance> listCityDistance = new ArrayList<CityDistance>();
    	
    	// Gets the CityDistance list.
    	for (City c : list) {
    		Double[] coord = MapUtils.getCoordenatesFromString(c.getGeoPosition());
    		Double latitude = coord[0];
    		Double longitude = coord[1];
    		Double distance = MapUtils.geodesicDistance(myLatLong[0], myLatLong[1], latitude, longitude);
    		listCityDistance.add(new CityDistance(c, distance));
    	}
    	
    	// Orders the list.
		CityDistanceComparator comparator = new CityDistanceComparator();
		Collections.sort(listCityDistance, comparator);
		
    	return listCityDistance;
    }
    
    /**
     * Get the City names of a list of City. 
     * 
     * @param list The City list.
     * @return The City list in strings.
     */
    public List<String> getCityNames(List<CityDistance> list) {
    	List<String> listString = new ArrayList<String>();
    	for (CityDistance c : list) {
    		String name = c.getCity().getName();
    		listString.add(name);
    	}
    	return listString;
    }

	//--------------------------------------------------
	// View Methods
	//--------------------------------------------------
    
    public void notImplemented(View v) {
    	Toast.makeText(mContext, getString(R.string.not_implemented), Toast.LENGTH_LONG).show();
    }
    
    //----------------------------------------------
	// Tasks
	//----------------------------------------------

	@SuppressWarnings("unchecked")
	@Override
	public void taskFinished(int type, OperationResult result) {
		// Validating the error result to show the proper dialog message.
		DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {}
		};
		OperationResult.validateResult(mContext, result, listener);
		List<City> list = new ArrayList<City>();
		 
		if (type == ContentManager.FETCH_TASK.CITY) {
			if (ResultType.SUCCESS.equals(result.getResultType())) {
				list = (List<City>)result.getEntityList();
				if (list.size() > 0 && list != null) {
					ContentManager.getInstance().setCachedCityList(list);
					setAdapter();
				}
			}
		}
	}
}