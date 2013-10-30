package br.android.goeurotest.utils;

import br.android.goeurotest.gps.GPSTracker;
import android.content.Context;

/**
 * MapUtils class.
 * 
 * @author Rodrigo Cericatto
 * @since Oct 27, 2013
 */
public class MapUtils {

	/**
	 * FORMULA = 6371 *
	 *  	acos (cos(PI() * (90 - lat2) / 180) *
	 *  	cos((90 - lat1) * pi() / 180) +
	 * 		sin((90 - lat2) * pi() / 180) *
	 * 		sin((90 - lat1) * pi() / 180) *
	 * 		cos((lon1 - lon2) * pi() / 180));
	 * @param lat1
	 * @param lon1
	 * @param lat2
	 * @param lon2
	 * @return
	 */
	public static Double geodesicDistance(
			Double lat1, Double lon1, Double lat2, Double lon2) {
		Double term1 = Math.cos(Math.PI * (90 - lat2) / 180);
        Double term2 = Math.cos((90 - lat1) * Math.PI / 180);
        Double term3 = Math.sin((90 - lat2) * Math.PI / 180);
        Double term4 = Math.sin((90 - lat1) * Math.PI / 180);
        Double term5 = Math.cos((lon1 - lon2) * Math.PI / 180); 
        Double finalTerm = term1 * term2 + term3 * term4 * term5;
        Double distance = Math.acos(finalTerm);
        return 6371 * distance;
	}
	
    /**
     * Get the latitude and longitude from the GeoPosition.
     * 
     * @param geoPosition The GeoPosition.
     * @return The latitude and longitude from the GeoPosition.
     */
    public static Double[] getCoordenatesFromString(String geoPosition) {
    	String[] coordenates = geoPosition.split(";");
    	Double latitude = Double.valueOf(coordenates[0]);
    	Double longitude = Double.valueOf(coordenates[1]);
    	
    	Double[] latLong = new Double[2];
    	latLong[0] = latitude;
    	latLong[1] = longitude;
    	
    	return latLong;
    }
    
    /**
     * Gets the current latitude and longitude (if the GPS is enabled).
     * If isn't, shows a dialog for the user.
     * 
     * @param context The context of the application.
     * @return Gets the current latitude and longitude.
     */
    public static Double[] getLatLong(Context context) {
    	GPSTracker gpsTracker = new GPSTracker(context);
    	Double[] latLong = new Double[2];
		// Check if GPS enabled.
        if(gpsTracker.canGetLocation()){
        	Double latitude = gpsTracker.getLatitude();
        	Double longitude = gpsTracker.getLongitude();
        	latLong[0] = latitude;
        	latLong[1] = longitude;
        } else {
        	// Can't get location because GPS or Network is not enabled.
        	// Ask user to enable GPS/network in settings.
        	gpsTracker.showSettingsAlert();
        	latLong = null;
        }
        return latLong;
    }
}