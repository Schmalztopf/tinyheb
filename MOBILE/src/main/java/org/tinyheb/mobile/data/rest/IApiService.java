package org.tinyheb.mobile.data.rest;

import org.tinyheb.core.Patron;
import org.tinyheb.core.TinyhebDataContainer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.POST;

public interface IApiService {
	@POST("API/tinyheb/writepatron")
	Call<Patron> createPatron(@Body Patron patron);
	
	
	@GET("API/tinyheb/getall")
	Call<TinyhebDataContainer> getAllData();
	
	@HEAD("/")
	Call<Void> checkUrlReachable();
}
