package br.android.goeurotest.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import br.android.goeurotest.api.OperationResult;
import br.android.goeurotest.api.OperationResult.ResultType;
import br.android.goeurotest.model.City;
import br.android.goeurotest.tasks.CityAsyncTask;
import br.android.goeurotest.tasks.Notifiable;

/**
 * ContentManager class.
 * 
 * @author Rodrigo Cericatto
 * @since Oct 11, 2013
 */
public class ContentManager {

	//----------------------------------------------
	// Constants
	//----------------------------------------------
	
	// Fetch task types.
	public static final class FETCH_TASK {
		public static final int CITY = 1;
	}
	
	//----------------------------------------------
	// Statics
	//----------------------------------------------
	
	// The singleton instance.
	private static ContentManager sInstance = null;
	
	//----------------------------------------------
	// Attributes
	//----------------------------------------------

	// The application context.
	private Context mContext;
	
	// Cached values.
	private List<City> mCityList = null;
	
	// Notifiables map.
	private Map<Object, Notifiable> mTaskNotifiables = new HashMap<Object, Notifiable>();
	
	//----------------------------------------------
	// Constructor
	//----------------------------------------------
	
	/**
	 * Private constructor.
	 */
	private ContentManager() {}
	
	/**
	 * @return The singleton instance of ContentManager.
	 */
	public static ContentManager getInstance() {
		if (sInstance == null) {
			sInstance = new ContentManager();
		}
		return sInstance;
	}

	//----------------------------------------------
	// Global Methods
	//----------------------------------------------
	
	/**
	 * Sets the application context.
	 * 
	 * @param context The context to be used.
	 */
	public void setContext(Context context) {
		mContext = context;
	}
	
	/**
	 * Cleans all cached content.
	 */
	public void clean() {
		// Cleaning all cached contents.
		mCityList = null;
	}
	
	//----------------------------------------------
	// City
	//----------------------------------------------

	/**
	 * Sets the City list from cache.
	 */
	public void setCachedCityList(List<City> list) {
		mCityList = list;
	}
	
	/**
	 * Gets the City list from cache.
	 * 
	 * @return
	 */
	public List<City> getCachedCityList() {
		return mCityList;
	}
	
	/**
	 * Gets the City list.
	 * 
	 * @param notifiable The notifiable object to be called.
	 */
	public void getCityList(Notifiable notifiable, String cityName) {
		CityAsyncTask task = new CityAsyncTask(mContext, cityName);
		if (notifiable != null) {
			mTaskNotifiables.put(task, notifiable);
		}
		task.execute();
	}
	
	//----------------------------------------------
	// Tasks Handling
	//----------------------------------------------
	
	@SuppressWarnings("unchecked")
	public void taskFinished(Object task, OperationResult result) {
		int taskType = getTaskType(task);
		
		// Gets current task result.
		if (FETCH_TASK.CITY == taskType) {
			if (ResultType.SUCCESS.equals(result.getResultType())) {
				// Puts the City list in the cache.
				mCityList = (List<City>)result.getEntityList();
			}
		}
		
		// Removes performed task.
		Notifiable notifiable = mTaskNotifiables.get(task);
		if (notifiable != null) {
			notifiable.taskFinished(taskType, result);
			mTaskNotifiables.remove(task);
		}
	}
	
	/**
	 * Returns the task type.
	 * 
	 * @param task
	 * @return The task type.
	 */
	private int getTaskType(Object task) {
		if (task instanceof CityAsyncTask) {
			return FETCH_TASK.CITY;
		}
		return -1;
	}
}