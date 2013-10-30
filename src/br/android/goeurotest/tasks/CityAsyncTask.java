package br.android.goeurotest.tasks;

import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import br.android.goeurotest.api.ApiRequest;
import br.android.goeurotest.api.OperationResult;
import br.android.goeurotest.api.OperationResult.ResultType;
import br.android.goeurotest.manager.ContentManager;
import br.android.goeurotest.model.City;

/**
 * CityAsyncTask class.
 * 
 * @author Rodrigo Cericatto
 * @since Oct 23, 2013
 */
public class CityAsyncTask extends AsyncTask<Void, Integer, OperationResult> {
	
	//--------------------------------------------------
	// Attributes
	//--------------------------------------------------
	
	// Application context reference.
	private final Context mContext;
	
	// The city name from the city spinner.
	private String mCityName;
	
	//--------------------------------------------------
	// Constructor
	//--------------------------------------------------
	
	/**
	 * Creates a new CityAsyncTask instance.
	 */
	public CityAsyncTask(Context context, String cityName) {
		mContext = context;
		mCityName = cityName;
	}

	//----------------------------------------------
	// Async Task
	//----------------------------------------------
	
	@Override
	protected OperationResult doInBackground(Void... params) {
		// Updating City list.
		try {
			return update(mContext, mCityName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(OperationResult result) {
		ContentManager.getInstance().taskFinished(this, result);
	}

	//----------------------------------------------
	// Static Methods
	//----------------------------------------------
	
	/**
	 * Updates the City if needed or fetch it from the database.
	 * 
	 * @return
	 * @throws Exception 
	 */
	public static OperationResult update(Context context, String cityName) throws Exception {
		// If we need to update from the server.
		OperationResult serverResult = getCityFromServer(context, cityName);
		return serverResult;
	}
	
	/**
	 * Fetches the City from the server.
	 * 
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	private static OperationResult getCityFromServer(final Context context, String cityName) throws Exception {
		// Getting the data from the online server.
		OperationResult result = ApiRequest.getCityList(cityName);
		// If successful, persist the data into the database.
		if (ResultType.SUCCESS.equals(result.getResultType())) {
			// Creating a final reference to the entity list.
			final List<City> list = (List<City>)result.getEntityList();
			// Replacing saved results in the list.
			result.setEntityList(list);
		}
		return result;
	}
}