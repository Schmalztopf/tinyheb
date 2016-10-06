package org.tinyheb.mobile;

import org.tinyheb.core.Patron;
import org.tinyheb.data.rest.TinyhebAPIClient;
import org.tinyheb.data.rest.WiFiReceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AbstractActivity implements TinyhebAPIClient.Listener, WiFiReceiver.Listener {

	private Button btnSynchronisation;
	private WiFiReceiver wifiStatusReceiver;
	private IntentFilter wifiFilter;
	private TinyhebAPIClient client;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnSynchronisation = (Button) findViewById(R.id.btnStartSync);
		wifiFilter = new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION);
		addWifiReceiver();
		client = new TinyhebAPIClient();
	}

	public WiFiReceiver getWifiStatusReceiver() {
		return wifiStatusReceiver;
	}

	public void setWifiStatusReceiver(WiFiReceiver wifiStatusReceiver) {
		this.wifiStatusReceiver = wifiStatusReceiver;
	}

	public TinyhebAPIClient getClient() {
		return client;
	}

	public void setClient(TinyhebAPIClient client) {
		this.client = client;
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


	public void showPatrons(View view) {
		Intent MasterdataIntent = new Intent(this, MasterDataPagerActivity.class);

		startActivity(MasterdataIntent);
	}

	public void insertPatrons(View view) {
		startActivity(new Intent(this, PatronInsertActivity.class));
	}

	public void startSync(View view) {
		Intent intent = new Intent(this, APISyncService.class);
		startService(intent);
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
	public void onAPIWifiConnected() {
		client.setListener(this);
		client.checkURL();
	}

	@Override
	public void onAPIWifiDisconnected() {
		btnSynchronisation.setVisibility(View.VISIBLE);
	}
}
