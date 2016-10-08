package org.tinyheb.mobile.data.rest;

import org.json.JSONObject;
import org.tinyheb.core.Patron;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class TinyhebRestClient {

    private static final String BASE_URL = "http://localhost:8080/";

    private static AsyncHttpClient restclient = new AsyncHttpClient();
    private AsyncHttpResponseHandler responseHandler;
    
    public interface HttpListener {
    	public void onFailure(String errorMessage);
    	public void onSuccess(int statusCode, Header[] headers,
                JSONObject response) throws Exception;
    }
    
    public TinyhebRestClient(final HttpListener listener) {
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
		ObjectMapper mapper = new ObjectMapper();
    	RequestParams params = new RequestParams();
    	params.add("patron", mapper.writeValueAsString(patron));
        restclient.post(BASE_URL + "API/tinyheb/writepatron", params, this.responseHandler);
    }

    public static void checkUrlReachable(AsyncHttpResponseHandler responseHandler) {
        restclient.head(BASE_URL, responseHandler);
    }
}
