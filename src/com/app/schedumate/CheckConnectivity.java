package com.app.schedumate;

/*
 * CLASS TO CHECK THE CONNECTIVITY OF THE DEVICE
 * AUTHOR :- ARITRA SAMANTA
 */

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.content.Context;
import android.util.Log;

public class CheckConnectivity {
	
	private static final String TAG = "CHECKCONNECTIVITY";
	
	private ConnectivityManager connection_manager;
	private NetworkInfo active_network;
	private Context context;
	private boolean isConnected;
	
	private boolean WIFI;
	private boolean MOBILE_DATA;
	private boolean WIMAX;
	
	public CheckConnectivity(Context context) {
		connection_manager = null;
		active_network = null;
		this.context = context;
		
		WIFI = MOBILE_DATA = WIMAX = false;
	}
	
	//	FUNCTION TO INITIALIZE CONNECTIVITY MANAGER.
	private void init_cm() {
		try {
			connection_manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		} catch (Exception e) {
			Log.d(TAG, "Here" + e.getMessage());
		}
	}
	
	//	FUNCTION TO CHECK THE INTERNET CONNECTION.
	private void checkConnection() {
		init_cm();
		
		active_network = connection_manager.getActiveNetworkInfo();
		isConnected = (active_network != null && active_network.isConnectedOrConnecting());
	}

	//	FUNCTION TO CHECK CONNECTIVITY STATUS.
	public boolean returnConnectivityStatus() {
		this.checkConnection();
		return this.isConnected;
	}
	
	//	FUNCTION TO DETECT CONNECTION TYPE.
	private void connectionType() {
		WIFI = (active_network.getType() == ConnectivityManager.TYPE_WIFI);
		MOBILE_DATA = (active_network.getType() == ConnectivityManager.TYPE_MOBILE);
		WIMAX = (active_network.getType() == ConnectivityManager.TYPE_WIMAX);
	}
	
	//	FUNCTION TO RETURN THE CONNECTION TYPE.
	public int returnConnectionType() {
		if( returnConnectivityStatus() ) {
			this.connectionType();
			if( WIFI )
				return 1;
			else if( MOBILE_DATA )
				return 2;
			else if( WIMAX )
				return 3;
		}
		return 0;
	}
}