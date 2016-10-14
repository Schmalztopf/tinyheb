package org.tinyheb.mobile.data.rest;

import org.tinyheb.core.Patron;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IPatronService {
	@POST("API/tinyheb/writepatron")
	Call<Patron> createPatron(@Body Patron patron);
}
