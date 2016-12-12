package org.tinyheb.mobile.data.rest;


import org.tinyheb.core.Patron;
import org.tinyheb.core.TinyhebDataContainer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class ApiRestClient {

	private static final String BASE_URL = "http://api-server-address:8080/";
	private IApiService apiService;
	private ObjectMapper mapper;
	private Retrofit retrofit;

	public ApiRestClient() {
		mapper = new ObjectMapper();
		retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(JacksonConverterFactory.create(mapper))
				.build();
		apiService = retrofit
				.create(IApiService.class);
	}

	public void get(Callback<TinyhebDataContainer> callback) {
		Call<TinyhebDataContainer> call = apiService.getAllData();
		call.enqueue(callback);
	}

	public void writePatron(Patron patron, Callback<Patron> callback) throws JsonProcessingException {
		Call<Patron> call = apiService.createPatron(patron);
		call.enqueue(callback);
	}

	public void checkApiReachable(Callback<Void> callback) {
		Call<Void> call = apiService.checkUrlReachable();
		call.enqueue(callback);
	}
	
}
