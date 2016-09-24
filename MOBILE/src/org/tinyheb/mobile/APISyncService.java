package org.tinyheb.mobile;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.tinyheb.core.HealthInsurance;
import org.tinyheb.core.Patron;
import org.tinyheb.data.rest.TinyhebRestClient;
import org.tinyheb.data.sqlite.SQLiteDBHelper;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.loopj.android.http.JsonHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class APISyncService extends Service {
	public APISyncService() {
	}
	private Looper mServiceLooper;
	private ServiceHandler mServiceHandler;
	private SQLiteDBHelper dbHelper = null;


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

	private final class ServiceHandler extends Handler {
		public ServiceHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(final Message msg) {

			TinyhebRestClient.get(new JsonHttpResponseHandler() {
				@Override
				public void onFailure(int statusCode, Header[] headers,
						String responseString, Throwable throwable) {
					super.onFailure(statusCode, headers, responseString, throwable);
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,
						JSONObject response) {

					super.onSuccess(statusCode, headers, response);

					Gson gson = new GsonBuilder().create();

					List<Patron> patrons = null;
					List<HealthInsurance> insurances = null;
					try {
						patrons = gson.fromJson(response.getString("patrons"), new TypeToken<List<Patron>>(){}.getType());
						insurances = gson.fromJson(response.getString("insurances"), new TypeToken<List<HealthInsurance>>(){}.getType());

					} catch (JsonSyntaxException e) {
						e.printStackTrace();
					} catch (JSONException e) {
						e.printStackTrace();
					} 	
					try {
						writeClient(patrons, Patron.class);
						writeClient(insurances, HealthInsurance.class);
						
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						stopSelf(msg.arg1);
					}

				}
			});
		}

	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {	    
		if (dbHelper != null) {
			OpenHelperManager.releaseHelper();
			dbHelper = null;
		}
		Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
		super.onDestroy();
	}

	public <T, T_ID> void writeClient(List<T> newClients, Class<T> classtype) {
		RuntimeExceptionDao<T, T_ID> simpleDao =  getHelper().getRuntimeExceptionDao(classtype);

		for (T newEntry : newClients) {
			simpleDao.createIfNotExists(newEntry);
		}
		Toast.makeText(this, newClients.size() + " Einträge geladen", Toast.LENGTH_SHORT).show();

	}

	private SQLiteDBHelper getHelper() {
		if (dbHelper == null) {
			dbHelper = (SQLiteDBHelper)OpenHelperManager.getHelper(this, SQLiteDBHelper.class);
		}
		return dbHelper;
	}
}
