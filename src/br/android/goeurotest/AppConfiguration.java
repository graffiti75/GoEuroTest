package br.android.goeurotest;

import android.annotation.SuppressLint;

/**
 * Stores the application configuration params.
 * 
 * @author Rodrigo Cericatto
 * @since Oct 11, 2013
 */
@SuppressLint("SdCardPath")
public class AppConfiguration {
	
	//----------------------------------------------
	// General Constants
	//----------------------------------------------	
	
	// Application version.
	public static final String APP_VERSION = "1.0.0";
	
	//----------------------------------------------
	// Database Settings
	//----------------------------------------------

	// Any time you make changes to the database objects, you have to increase the database version.
	public static final int DATABASE_VERSION = 1;
	
	// Path to the database files.
	public static final String DATABASE_PATH = "/data/data/" + CustomApplication.ROOT_PACKAGE_NAME + "/databases/";

	// The name of the database file.
	public static final String DATABASE_NAME = "goeurotest.db";
	
	// Flag to get data from server or from database.
	public static Boolean sDatabaseNeedsUpdate = false;
	
	//----------------------------------------------
	// File Settings
	//----------------------------------------------
	
	// Separators.
	public static final String SEPARATOR = ";";
	public static final String NEW_LINE = "\n";

	//----------------------------------------------
	// Logging
	//----------------------------------------------
	
	// Enable logging.
	public static final boolean LOGGING_ENABLED = true;
	
	// Tag for common log output.
	public static final String COMMON_LOGGING_TAG = "goeuro";

	//----------------------------------------------
	// Network Configuration
	//----------------------------------------------
	
	// Default socket buffer size (4KB).
	public static final int SOCKET_BUFFER_SIZE = 4096;

	// Default connection timeout (5 seconds).
	public static final int CONNECTION_TIMEOUT = 5000;

	// Default socket timeout for downloading content (30 seconds).
	public static final int SOCKET_TIMEOUT = 30000;

	// Max retry count for HTTP timeouts.
	public static final int MAX_HTTP_RETRY_COUNT = 3;

	// Wait to retry the server on a cache generation error.
	public static final int HTTP_CACHE_RETRY_WAIT = 4000;

	// Wait to retry connecting to the server on a connection timeout.
	public static final int HTTP_SERVER_RETRY_WAIT = 2000;
	
	//----------------------------------------------
	// Server Settings
	//----------------------------------------------

	// Server IP.
	public static final String SERVER_IP = "http://pre.dev.goeuro.de:12345/api/v1/suggest/position/en/name/";
	
	// HTTP UserAgent.
	public static final String USER_AGENT = "ContactManager/" + APP_VERSION;
}