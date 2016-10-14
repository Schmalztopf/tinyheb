package org.tinyheb.mobile.data.rest;

import org.json.JSONObject;
import org.tinyheb.core.Patron;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class TinyhebRestClient {

	private static final String BASE_URL = "http://192.168.0.102:8080/";

	private static AsyncHttpClient restclient = new AsyncHttpClient();
	private AsyncHttpResponseHandler responseHandler;
	private final HttpListener listener;

	public interface HttpListener {
		public void onFailure(String errorMessage);
		public void onSuccess(int statusCode, Header[] headers,
				JSONObject response) throws Exception;
		public void onsuccess(Response<Patron> response);
	}

	public TinyhebRestClient(final HttpListener listener) {
		this.listener = listener;
		this.responseHandler = new JsonHttpResponseHandler() {
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				super.onFailure(statusCode, headers, responseString, throwable);
				listener.onFailure("Error getting data");
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				super.onSuccess(statusCode, headers, response);
				try {
					listener.onSuccess(statusCode, headers, response);
				} catch (Exception e) {
					listener.onFailure("Error processing data.");
				}

			}
		};
	}

	public void get() {
		restclient.get(BASE_URL + "API/tinyheb/getall", this.responseHandler);
	}

	public void writePatron(Patron patron) throws JsonProcessingException {
		try {
		
		ObjectMapper mapper = new ObjectMapper();
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(JacksonConverterFactory.create(mapper))
				.build();

		IPatronService apiService = retrofit.create(IPatronService.class);

		Call<Patron> call = apiService.createPatron(patron);
		call.enqueue(new Callback<Patron>() {
			@Override
			public void onResponse(Call<Patron> call, Response<Patron> response) {
				listener.onsuccess(response);
			}

			@Override
			public void onFailure(Call<Patron> call, Throwable t) {
				listener.onFailure(t.toString());
			}
		});
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void checkUrlReachable(AsyncHttpResponseHandler responseHandler) {
		restclient.head(BASE_URL, responseHandler);
	}
}
