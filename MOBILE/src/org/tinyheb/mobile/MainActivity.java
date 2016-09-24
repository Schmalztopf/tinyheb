package org.tinyheb.mobile;

import org.tinyheb.data.rest.WiFiReciever;
import org.tinyheb.data.rest.WiFiReciever.Listener;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {


	private Button btnSynchronisation;
	private WiFiReciever wifiStatusReciever;

	IntentFilter wifiFilter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnSynchronisation = (Button) findViewById(R.id.btnStartSync);
		
		wifiFilter = new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION);
		wifiStatusReciever = new WiFiReciever();
		wifiStatusReciever.setListener(new Listener() {
			
			@Override
			public void onAPIWifiConnected() {
				btnSynchronisation.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void onAPIWifiDisconnected() {
				btnSynchronisation.setVisibility(View.VISIBLE);
				
			}
		});
		registerReceiver(wifiStatusReciever, wifiFilter);

		btnSynchronisation.setVisibility(View.VISIBLE);
		
	}

	@Override
	protected void onDestroy() {
		if (wifiStatusReciever != null) {
			unregisterReceiver(wifiStatusReciever);
			wifiStatusReciever = null;
		}
		unregisterReceiver(wifiStatusReciever);
		super.onDestroy();
	}

	public void showPatrons(View view) {
		startActivity(new Intent(this, MasterDataPagerActivity.class));
	}

	public void insertPatrons(View view) {
		startActivity(new Intent(this, PatronInsertActivity.class));
	}

	public void startSync(View view){
		Intent intent = new Intent(this, APISyncService.class);
		startService(intent);
	}




}
