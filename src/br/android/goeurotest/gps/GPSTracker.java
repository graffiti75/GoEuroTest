package br.android.goeurotest.gps;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

public class GPSTracker extends Service implements LocationListener {
	
	//--------------------------------------------------
	// Attributes
	//--------------------------------------------------

	private final Context mContext;

	// Flags for GPS status.
	private boolean mIsGPSEnabled = false;
	private boolean mCanGetLocation = false;
	
	// Flag for network status.
	private boolean mIsNetworkEnabled = false;

	private Location mLocation;
	private double mLatitude;
	private double mLongitude;

	//--------------------------------------------------
	// Statics
	//--------------------------------------------------
	
	// The minimum distance to change Updates in meters (10 meters).
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;

	// The minimum time between updates in milliseconds (1 minute).
	private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;

	// Declaring a Location Manager.
	protected LocationManager locationManager;

	//--------------------------------------------------
	// Constructor
	//--------------------------------------------------
	
	public GPSTracker(Context context) {
		this.mContext = context;
		getLocation();
	}

	//--------------------------------------------------
	// Methods
	//--------------------------------------------------
	
	public Location getLocation() {
		try {
			locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
			// Getting GPS status.
			mIsGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			// Getting network status.
			mIsNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

			if (!mIsGPSEnabled && !mIsNetworkEnabled) {
				// No network provider is enabled.
			} else {
				this.mCanGetLocation = true;
				if (mIsNetworkEnabled) {
					locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
					Log.d("Network", "Network");
					if (locationManager != null) {
						mLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
						if (mLocation != null) {
							mLatitude = mLocation.getLatitude();
							mLongitude = mLocation.getLongitude();
						}
					}
				}
				// If GPS Enabled get lat/long using GPS Services.
				if (mIsGPSEnabled) {
					if (mLocation == null) {
						locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
						Log.d("GPS Enabled", "GPS Enabled");
						if (locationManager != null) {
							mLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
							if (mLocation != null) {
								mLatitude = mLocation.getLatitude();
								mLongitude = mLocation.getLongitude();
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mLocation;
	}
	
	/**
	 * Stop using GPS listener.
	 * Calling this function will stop using GPS in your app.
	 */
	public void stopUsingGPS() {
		if (locationManager != null) {
			locationManager.removeUpdates(GPSTracker.this);
		}
	}
	
	/**
	 * Function to get mLatitude.
	 */
	public double getLatitude() {
		if (mLocation != null) {
			mLatitude = mLocation.getLatitude();
		}
		return mLatitude;
	}
	
	/**
	 * Function to get mLongitude.
	 */
	public double getLongitude() {
		if (mLocation != null) {
			mLongitude = mLocation.getLongitude();
		}
		return mLongitude;
	}
	
	/**
	 * Function to check GPS/wifi enabled.
	 * 
	 * @return boolean
	 */
	public boolean canGetLocation() {
		return this.mCanGetLocation;
	}
	
	/**
	 * Function to show settings alert dialog.
	 * On pressing Settings button will launch Settings Options.
	 */
	public void showSettingsAlert(){
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
   	 
        // Setting Dialog Title
        alertDialog.setTitle("GPS is settings");
        // Setting dialog message.
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");
        // On pressing settings button.
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
            	Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            	mContext.startActivity(intent);
            }
        });
 
        // On pressing cancel button.
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int which) {
        		dialog.cancel();
            }
        });
        // Showing alert message.
        alertDialog.show();
	}

	@Override
	public void onLocationChanged(Location location) {}

	@Override
	public void onProviderDisabled(String provider) {}

	@Override
	public void onProviderEnabled(String provider) {}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
}