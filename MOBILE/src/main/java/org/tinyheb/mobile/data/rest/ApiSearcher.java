/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tinyheb.mobile.data.rest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * @author haubi
 */
public class ApiSearcher {

	private Listener listener;
    private int resultCode;
    private ApiRestClient httpClient;

	public ApiSearcher(Listener apiListner) {
    	this.listener = apiListner;
    	this.httpClient = new ApiRestClient();
    }
    
    Callback<Void> callback = new Callback<Void>() {
		@Override
		public void onResponse(Call<Void> call, Response<Void> response) {
	        resultCode = response.code();
	        if (isAvailable())
	        	listener.onAPIServerFound();
	        else
	        	listener.onAPIServerMissing();
			
		}
		
		@Override
		public void onFailure(Call<Void> call, Throwable t) {
		   	listener.onAPIServerMissing();	
		}
	};

	public interface Listener {
		public void onAPIServerFound();
		public void onAPIServerMissing();
	}
	
	public void checkURL() {
		httpClient.checkApiReachable(callback);
	}
 
	public boolean isAvailable() {
		return this.resultCode == 200;
	}
	
    
    public void setHttpClient(ApiRestClient httpClient) {
		this.httpClient = httpClient;
	}

}
