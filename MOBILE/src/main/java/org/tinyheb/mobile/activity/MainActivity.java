package org.tinyheb.mobile.activity;

import org.tinyheb.mobile.R;
import org.tinyheb.mobile.WiFiReceiver;
import org.tinyheb.mobile.data.rest.ApiSearcher;
import org.tinyheb.mobile.service.APIGetAllDataService;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AbstractActivity implements ApiSearcher.Listener, WiFiReceiver.Listener {

	private Button btnSynchronisation;
	private WiFiReceiver wifiStatusReceiver;
	private IntentFilter wifiFilter;
	private ApiSearcher client;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnSynchronisation = (Button) findViewById(R.id.btnStartSync);
		wifiFilter = new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION);
		client = new ApiSearcher(this);
		addWifiReceiver();
	}

	@Override
	protected void onPause() {
		removeWifiReceiver();
		super.onPause();
	}

	@Override
	protected void onResume() {
		addWifiReceiver();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		removeWifiReceiver();
		super.onDestroy();
	}

	private void addWifiReceiver() {
		wifiStatusReceiver = new WiFiReceiver();
		wifiStatusReceiver.setListener(this);
		registerReceiver(wifiStatusReceiver, wifiFilter);
	}

	private void removeWifiReceiver() {
		if (wifiStatusReceiver != null) {
			unregisterReceiver(wifiStatusReceiver);
			wifiStatusReceiver = null;
		}
	}

	@Override
	public void onAPIServerFound() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				btnSynchronisation.setVisibility(View.VISIBLE);
			}
		});
	}

	@Override
	public void onAPIServerMissing() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				btnSynchronisation.setVisibility(View.GONE);
			}
		});
	}

	@Override
	public void onWifiConnected() {
		client.checkURL();
	}

	@Override
	public void onWifiDisconnected() {
		btnSynchronisation.setVisibility(View.GONE);
	}
}
