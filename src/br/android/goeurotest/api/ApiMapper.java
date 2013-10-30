package br.android.goeurotest.api;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import br.android.goeurotest.model.City;
import br.android.goeurotest.model.GeoPosition;

/**
 * ApiMapper class.
 * 
 * @author Rodrigo Cericatto
 * @since Oct 11, 2013
 */
public class ApiMapper {

	//----------------------------------------------
	// Statics
	//----------------------------------------------
	
	// The JSON object mapping parser.
	private static final ObjectMapper sObjectMapper = new ObjectMapper();
	
	//----------------------------------------------
	// JSON Parsing
	//----------------------------------------------

	/**
	 * Parse JSON string into a list.
	 * 
	 * @param data
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static List<?> parseList(String data) throws JsonParseException, JsonMappingException, IOException {
		return (List<?>)parseObject(data, List.class);
	}
	
	/**
	 * Parse JSON string into a map.
	 * 
	 * @param data
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static Map<?, ?> parseMap(String data) throws JsonParseException, JsonMappingException, IOException {
		return (Map<?, ?>)parseObject(data, Map.class);
	}
	
	/**
	 * Parse JSON string to the specified object.
	 * 
	 * @param data
	 * @param clazz
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static Object parseObject(String data, Class<?> clazz) throws JsonParseException, JsonMappingException, IOException {
		return sObjectMapper.readValue(data, clazz);
	}
	
	//----------------------------------------------
	// City
	//----------------------------------------------
	
	/**
	 * Gets the City from the given JSON data.
	 * 
	 * @param data The JSON data that contains the City.
	 * @return The city.
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static City getCityFromJson(Map<String, Object> data) throws Exception {
		City city = new City();
		
		if (data != null) {
			/*
			private String type;
			private GeoPosition geoPosition;
			*/
			city.set_type(getString(data.get(City.KEY_TYPE_ROOT)));
			city.set_id(getString(data.get(City.KEY_ID)));
			city.setName(getString(data.get(City.KEY_NAME)));
			city.setType(getString(data.get(City.KEY_TYPE)));
			city.setGeoPosition(getGeoPositionFromJson((Map<String, Object>) data.get(City.KEY_GEO_POSITION)));
		} else {
			city = null;
		}
		
		return city;
	}

	//----------------------------------------------
	// GeoPosition
	//----------------------------------------------
	
	/**
	 * Gets the GeoPosition from the given JSON data.
	 * 
	 * @param data The JSON data that contains the GeoPosition.
	 * @return The GeoPosition.
	 * @throws Exception 
	 */
	public static String getGeoPositionFromJson(Map<String, Object> data) throws Exception {
		GeoPosition geoPosition = new GeoPosition();
		
		if (data != null) {
			geoPosition.setLatitude(getDouble(data.get(GeoPosition.KEY_LATITUDE)));
			geoPosition.setLongitude(getDouble(data.get(GeoPosition.KEY_LONGITUDE)));
		} else {
			geoPosition = null;
		}
		String result = geoPosition.getLatitude().toString() + ";" + geoPosition.getLongitude().toString();
		
		return result;
	}
	
	//----------------------------------------------
	// Methods
	//----------------------------------------------
	
	/**
	 * Return the string value from an object.
	 * 
	 * @param object The checked object. 
	 * @return It's string value.
	 */
	private static String getString(Object object) {
		String value = "";
		if (object != null) {
			try {
				value = (String)object;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	
    /**
     * Returns primitive double from object with a default value of 0.0 for non double.
     * 
     * @param object
     * @param defaultValue
     * @return
     */
	private static double getDouble(Object object) {
    	if (object == null) return 0;
    	try {
    		return Double.parseDouble(object.toString());
    	} catch(NumberFormatException e) {
    		return 0.0;
    	}
    }
}