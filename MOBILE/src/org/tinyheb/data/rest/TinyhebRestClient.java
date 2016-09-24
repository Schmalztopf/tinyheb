package org.tinyheb.data.rest;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class TinyhebRestClient {
	private static final String BASE_URL = "http://192.168.0.102:8080/API/tinyheb/getall";

	private static AsyncHttpClient restclient = new AsyncHttpClient();

	public static void get(AsyncHttpResponseHandler responseHandler) {
		restclient.get(BASE_URL, responseHandler);
	}
}
