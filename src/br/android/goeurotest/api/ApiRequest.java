package br.android.goeurotest.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import android.util.Log;
import br.android.goeurotest.AppConfiguration;
import br.android.goeurotest.api.OperationResult.ResultType;
import br.android.goeurotest.manager.HttpManager;
import br.android.goeurotest.model.City;
import br.android.goeurotest.utils.StringUtils;

/**
 * ApiRequest class.
 * 
 * @author Rodrigo Cericatto
 * @since Oct 11, 2013
 */
public class ApiRequest {
	
	//----------------------------------------------
	// Get Web Services
	//----------------------------------------------
	
	/**
	 * Gets the City list.
	 * 
	 * @return The City list.
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static OperationResult getCityList(String cityName) throws Exception {
		// Gets the url of this service.
		String url = AppConfiguration.SERVER_IP + cityName;
		
		// Holds the operation result.
		OperationResult result = null;
		
		// Getting data from server.
		try {
			result = HttpManager.getInstance().executeHttpGetWithRetry(url);
		} catch (Exception e) {
		}
		
		// Checking for errors.
		if (!ResultType.SUCCESS.equals(result.getResultType())) {
			return result;
		}
		
		// Getting JSON string.
		String jsonString = result.getResponseString();
		if (StringUtils.isEmpty(jsonString)) {
			// Set the result as parsing error.
			result.setResultType(ResultType.PARSING_ERROR);
			return result;
		}
		
		// Parsing the data.
		try {
			// Mapping JSON data.
			Map<String, Object> jsonData = (Map<String, Object>)ApiMapper.parseMap(jsonString);
			// Getting the alderman list.
			List<City> list = new ArrayList<City>();
			// Parsing JSON.
			if (jsonData.containsKey(City.KEY_ROOT)) {
				for (Map<String, Object> item : (List<Map<String, Object>>)jsonData.get(City.KEY_ROOT)) {
					City city = ApiMapper.getCityFromJson(item);
					list.add(city);
				}
			}
			// Setting the result list.
			result.setEntityList(list);
		} catch (JsonParseException e) {
			Log.e(AppConfiguration.COMMON_LOGGING_TAG, "Parse exception.");
			result.setResultType(ResultType.PARSING_ERROR);
			return result;
		} catch (JsonMappingException e) {
			Log.e(AppConfiguration.COMMON_LOGGING_TAG, "Mapping exception.");
			result.setResultType(ResultType.UNKNOWN);
			return result;
		} catch (IOException e) {
			Log.e(AppConfiguration.COMMON_LOGGING_TAG, "IOException.", e);
		}
		return result;
	}
}