package org.tinyheb.mobile.service;

import org.json.JSONObject;
import org.tinyheb.core.Patron;
import org.tinyheb.mobile.data.rest.TinyhebRestClient;

import com.fasterxml.jackson.core.JsonProcessingException;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;
import cz.msebera.android.httpclient.Header;

public class WritePatronService extends Service {

	private Looper mServiceLooper;
	private ServiceHandler mServiceHandler;

	@Override
	public void onCreate() {
		HandlerThread thread = new HandlerThread("ServiceStartArguments");
		thread.start();

		// Get the HandlerThread's Looper and use it for our Handler
		mServiceLooper = thread.getLooper();
		mServiceHandler = new ServiceHandler(mServiceLooper);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

		Message msg = mServiceHandler.obtainMessage();
		msg.arg1 = startId;
		mServiceHandler.sendMessage(msg);

		return START_STICKY;
	}

	private final class ServiceHandler extends Handler implements TinyhebRestClient.HttpListener {

		public ServiceHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(final Message msg) {
			Patron patron = new Patron();
			patron.setFirstname("test");
			patron.setLastname("test");
			try {
				new TinyhebRestClient(this).writePatron(patron);
			} catch (JsonProcessingException e) {
				stopSelf();
			}
		}

		@Override
		public void onFailure(String errorMessage) {
			Toast.makeText(getBaseContext(), errorMessage, Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onSuccess(int statusCode, Header[] headers, JSONObject response) throws Exception {

		}

	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
		super.onDestroy();
	}
}
