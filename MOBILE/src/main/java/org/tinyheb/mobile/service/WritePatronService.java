package org.tinyheb.mobile.service;

import org.json.JSONObject;
import org.tinyheb.core.Patron;
import org.tinyheb.mobile.TinyhebApp;
import org.tinyheb.mobile.data.rest.TinyhebRestClient;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;
import cz.msebera.android.httpclient.Header;
import retrofit2.Response;

public class WritePatronService extends Service {

	private Looper mServiceLooper;
	private ServiceHandler mServiceHandler;
	private RuntimeExceptionDao<Patron, Long> patronDao;
	private Patron writePatron;
	private static Context context;

	@Override
	public void onCreate() {
		HandlerThread thread = new HandlerThread("ServiceStartArguments");
		thread.start();

		context = getApplicationContext();
		// Get the HandlerThread's Looper and use it for our Handler
		mServiceLooper = thread.getLooper();
		mServiceHandler = new ServiceHandler(mServiceLooper);

		patronDao = ((TinyhebApp) getApplication()).getHelper().getRuntimeExceptionDao(Patron.class);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

		long patid = intent.getIntExtra("patron", 0);
		writePatron = patronDao.queryForEq("_id", patid).get(0);

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
			try {
				if (writePatron.getId() < 0 ) {
					new TinyhebRestClient(this).writePatron(writePatron);
				}
			} catch (Exception e) {
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

		@Override
		public void onsuccess(Response<Patron> response) {
			patronDao.update(response.body());
			patronDao.delete(writePatron);
			Toast.makeText(getBaseContext(), "success", Toast.LENGTH_SHORT).show();
			sendMessageToActivity();
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
	
	private static void sendMessageToActivity() {
	    Intent intent = new Intent("TinyhebPatronUpdate");
	    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
	}
}
