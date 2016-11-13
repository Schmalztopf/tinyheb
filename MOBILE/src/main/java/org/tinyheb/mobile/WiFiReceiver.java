package org.tinyheb.mobile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class WiFiReceiver extends BroadcastReceiver {

	Listener listener;

	public interface Listener {

		public void onWifiConnected();

		public void onWifiDisconnected();
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = conMan.getActiveNetworkInfo();
		if (netInfo != null && netInfo.getType() == ConnectivityManager.TYPE_WIFI)
			if (netInfo.isConnectedOrConnecting()) {
				listener.onWifiConnected();
				return;
			} 


		listener.onWifiDisconnected();
	}

};
