package org.tinyheb.data.rest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.UnknownHostException;

import android.os.AsyncTask;

public class APIExistanceCheckTask extends AsyncTask<Void, Void, Boolean> {

	Listener listener;

	public interface Listener {
		public void onAPIServerFound();
		public void onAPIServerMissing();
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	@Override
	protected Boolean doInBackground(Void... params) {
		HttpURLConnection httpUrlConnection;
		try {
			httpUrlConnection = (HttpURLConnection) new URL("http://192.168.0.102:8080/").openConnection();
			httpUrlConnection.setRequestMethod("HEAD");
			int responseCode = httpUrlConnection.getResponseCode();
			return responseCode == HttpURLConnection.HTTP_OK;
		} catch (ProtocolException e) {
			return false;
		} catch (MalformedURLException e) {
			return false;
		} catch (UnknownHostException noInternetConnection) {
			return false;
		} catch (IOException e) {
			return false;
		}
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		if (result) listener.onAPIServerFound();
		else listener.onAPIServerMissing();
	}
}
