package br.android.goeurotest;

import android.app.Application;
import br.android.goeurotest.manager.ContentManager;
import br.android.goeurotest.manager.HttpManager;
import br.android.goeurotest.utils.Utils;

import com.j256.ormlite.android.apptools.OpenHelperManager;

/**
 * A custom application class to manage the global application state.
 * 
 * @author Rodrigo Cericatto
 * @since Oct 11, 2013
 */
public class CustomApplication extends Application {
	
	//--------------------------------------------------
	// Statics
	//--------------------------------------------------
	
	// The root package name.
	public static final String ROOT_PACKAGE_NAME;
	static {
		ROOT_PACKAGE_NAME = Utils.getPackageName(CustomApplication.class);
	}
	
	//--------------------------------------------------
	// Application Life Cycle Methods
	//--------------------------------------------------
	
	@Override
	public void onCreate() {
		super.onCreate();
		// Initializing the http manager.
		HttpManager.getInstance().setContext(this);
		// Initializing the content manager.
		ContentManager.getInstance().setContext(this);
		// Setting device density.
		Utils.setScreenDensity(getResources().getDisplayMetrics().density);
	}
	
	@Override
	public void onLowMemory() {
		super.onLowMemory();
		// Shut down HTTP connections
		HttpManager.getInstance().shutdownHttpClient();
        // Cleaning cached content.
      	ContentManager.getInstance().clean();
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();
		// Releasing the helper.
		OpenHelperManager.releaseHelper();
		// Shut down HTTP connections
		HttpManager.getInstance().shutdownHttpClient();
        // Cleaning cached content.
      	ContentManager.getInstance().clean();
	}
}