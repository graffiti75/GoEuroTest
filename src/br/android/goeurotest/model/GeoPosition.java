package br.android.goeurotest.model;

/**
 * GeoPosition class.
 * 
 * @author Rodrigo Cericatto
 * @since Oct 11, 2013
 */
public class GeoPosition {
	
	//----------------------------------------------
	// JSON Keys
	//----------------------------------------------
	
	public static final String KEY_ROOT = "geo_position";
	public static final String KEY_LATITUDE = "latitude";
	public static final String KEY_LONGITUDE = "longitude";

	//--------------------------------------------------
	// Attributes
	//--------------------------------------------------
	
	private Double latitude;
	
	private Double longitude;
	
	//--------------------------------------------------
	// Constructor
	//--------------------------------------------------
	
	public GeoPosition() {}

	public GeoPosition(Double latitude, Double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	//--------------------------------------------------
	// To String
	//--------------------------------------------------
	
	@Override
	public String toString() {
		return "GeoPosition [latitude: " + latitude + ", longitude: " + longitude + "]";
	}

	//--------------------------------------------------
	// Getters and Setters
	//--------------------------------------------------
	
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
}