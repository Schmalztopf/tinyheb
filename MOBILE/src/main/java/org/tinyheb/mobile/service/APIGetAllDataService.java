package org.tinyheb.mobile.service;

import java.util.List;

import org.tinyheb.core.HealthInsurance;
import org.tinyheb.core.Patron;
import org.tinyheb.core.TinyhebDataContainer;
import org.tinyheb.mobile.TinyhebApp;
import org.tinyheb.mobile.data.rest.ApiRestClient;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIGetAllDataService extends Service {

	public APIGetAllDataService() {
	}
	private Looper mServiceLooper;
	private ServiceHandler mServiceHandler;
    private Callback<TinyhebDataContainer> callback = new Callback<TinyhebDataContainer>() {
		@Override
		public void onResponse(Call<TinyhebDataContainer> call, Response<TinyhebDataContainer> response) {
			TinyhebDataContainer alldata = (TinyhebDataContainer) response.body();
			writeClient(alldata.patrons, Patron.class);
			writeClient(alldata.insurances, HealthInsurance.class);
		}
		
		@Override
		public void onFailure(Call<TinyhebDataContainer> call, Throwable t) {
			Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
		}
	};

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
			new ApiRestClient()
			.get(callback);
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

	private <T, T_ID> void writeClient(List<T> newClients, Class<T> classtype) {
		RuntimeExceptionDao<T, T_ID> simpleDao = ((TinyhebApp) this.getApplication()).getHelper().getRuntimeExceptionDao(classtype);

		for (T newEntry : newClients) {
			simpleDao.createOrUpdate(newEntry);
		}
		Toast.makeText(this, newClients.size() + " Einträge geladen", Toast.LENGTH_SHORT).show();

	}
}
