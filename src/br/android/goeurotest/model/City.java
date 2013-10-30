package br.android.goeurotest.model;

/**
 * City class.
 * 
 * @author Rodrigo Cericatto
 * @since Oct 11 2013
 */
public class City {

	//----------------------------------------------
	// JSON Keys
	//----------------------------------------------
	
	public static final String KEY_ROOT = "results";
	public static final String KEY_TYPE_ROOT = "_type";
	public static final String KEY_ID = "_id";
	public static final String KEY_NAME = "name";
	public static final String KEY_TYPE = "type";
	public static final String KEY_GEO_POSITION = "geo_position";

	//--------------------------------------------------
	// Attributes
	//--------------------------------------------------
	
	private String _type;
	
	private String _id;
	
	private String name;
	
	private String type;
	
	private String geoPosition;
	
	//--------------------------------------------------
	// Constructor
	//--------------------------------------------------
	
	public City() {}

	public City(String _type, String _id, String name, String type, String geoPosition) {
		this._type = _type;
		this._id = _id;
		this.name = name;
		this.type = type;
		this.geoPosition = geoPosition;
	}

	//--------------------------------------------------
	// To String
	//--------------------------------------------------
	
	@Override
	public String toString() {
		return "City [_type=" + _type + ", _id=" + _id + ", name=" + name
			+ ", type=" + type + ", geoPosition=" + geoPosition + "]";
	}	
	
	//--------------------------------------------------
	// Getters and Setters
	//--------------------------------------------------
	
	public String get_type() {
		return _type;
	}
	public void set_type(String _type) {
		this._type = _type;
	}

	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getGeoPosition() {
		return geoPosition;
	}
	public void setGeoPosition(String geoPosition) {
		this.geoPosition = geoPosition;
	}
}