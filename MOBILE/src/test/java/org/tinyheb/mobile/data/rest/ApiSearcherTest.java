
package org.tinyheb.mobile.data.rest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.annotation.Config;
import org.tinyheb.mobile.data.rest.ApiSearcher;
import org.tinyheb.mobile.data.rest.ApiSearcher.Listener;
import org.tinyheb.test.TinyhebTestRunner;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;

@Config(sdk = 23)
@RunWith(TinyhebTestRunner.class)
public class ApiSearcherTest {

	// Class under test
	private ApiSearcher searcher;
	
	
	@Mock
	private ApiRestClient mockApiClient;

	@Captor
	private ArgumentCaptor<Callback<Void>> callbackCaptor;
	
	private boolean serverfindExpected;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		searcher = new ApiSearcher(new Listener() {

			@Override
			public void onAPIServerFound() {
				assertTrue("Expected, not to find the server", serverfindExpected);
			}

			@Override
			public void onAPIServerMissing() {
				assertFalse("Expected, to find the server", serverfindExpected);
			}
			
		});
		
		searcher.setHttpClient(mockApiClient);
	}

	/**
	 * Test of checkUrlReachable method, of class TinyhebRestClient.
	 */
	@Test
	public void testCheckUrlReachable() {
		Response<Void> response;
		searcher.checkURL();
		
		
		Mockito.verify(mockApiClient).checkApiReachable(callbackCaptor.capture());
		serverfindExpected = false;
		callbackCaptor.getValue().onFailure(null, new Exception("test"));

		response = Response.error(404, ResponseBody.create(MediaType.parse("application/json") ,""));
		callbackCaptor.getValue().onResponse(null, response);
		
		serverfindExpected = true;
		response = Response.success(null);
		callbackCaptor.getValue().onResponse(null, response);
	}

}
