package org.tinyheb.data.rest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class WiFiReciever extends BroadcastReceiver {

	Listener listener;
	APIExistanceCheckTask APICheckTask;

	public interface Listener {
		public void onAPIWifiConnected();
		public void onAPIWifiDisconnected();
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	@Override
	public void onReceive(Context context, Intent intent) {     
		ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE); 
		NetworkInfo netInfo = conMan.getActiveNetworkInfo();
		if (netInfo != null && netInfo.getType() == ConnectivityManager.TYPE_WIFI) 
			createAPIReachableTask();
		else
			listener.onAPIWifiDisconnected();
	}

	private void createAPIReachableTask()
	{
		APICheckTask = new APIExistanceCheckTask();
		APICheckTask.setListener(new APIExistanceCheckTask.Listener() {
			@Override
			public void onAPIServerFound() {
				listener.onAPIWifiConnected();
			}

			@Override
			public void onAPIServerMissing() {
				listener.onAPIWifiDisconnected();				
			}
		});
		APICheckTask.execute();
	}
};