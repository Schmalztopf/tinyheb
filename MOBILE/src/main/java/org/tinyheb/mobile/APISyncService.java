package org.tinyheb.mobile;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.tinyheb.core.HealthInsurance;
import org.tinyheb.core.Patron;
import org.tinyheb.core.TinyhebDataContainer;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
                public void onProgress(long bytesWritten, long totalSize) {
                	Toast.makeText(getBaseContext(), String.format("%02.0d % uploaded", bytesWritten/totalSize * 100), Toast.LENGTH_SHORT);
                }
                
                @Override
                public void onSuccess(int statusCode, Header[] headers,
                        JSONObject response) {

                    super.onSuccess(statusCode, headers, response);
                    
                    TinyhebDataContainer alldata = new TinyhebDataContainer();

                    ObjectMapper mapper = new ObjectMapper();
                    try {
						alldata = mapper.readValue(response.toString(), TinyhebDataContainer.class);
					} catch (JsonParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (JsonMappingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    
                    try {
                        writeClient(alldata.patrons, Patron.class);
                        writeClient(alldata.insurances, HealthInsurance.class);

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

    private <T, T_ID> void writeClient(List<T> newClients, Class<T> classtype) {
        RuntimeExceptionDao<T, T_ID> simpleDao = getHelper().getRuntimeExceptionDao(classtype);

        for (T newEntry : newClients) {
            simpleDao.createOrUpdate(newEntry);
        }
        Toast.makeText(this, newClients.size() + " Einträge geladen", Toast.LENGTH_SHORT).show();

    }

    private SQLiteDBHelper getHelper() {
        if (dbHelper == null) {
            dbHelper = (SQLiteDBHelper) OpenHelperManager.getHelper(this, SQLiteDBHelper.class);
        }
        return dbHelper;
    }
}
