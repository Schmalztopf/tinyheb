
package org.tinyheb.data.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.tinyheb.data.rest.TinyhebAPIClient.Listener;
import org.tinyheb.test.TinyhebTestRunner;

@RunWith(TinyhebTestRunner.class)
public class TinyhebAPIClientTest {

	// Class under test
	private TinyhebAPIClient apiClient;

	private boolean serverfindExpected;

	@Mock
	private TinyhebAPICollaborator mockAPICollaborator;

	@Captor
	private ArgumentCaptor<TinyhebAPICallback> callbackCaptor;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		apiClient = new TinyhebAPIClient(mockAPICollaborator);
	}

	/**
	 * Test of checkUrlReachable method, of class TinyhebRestClient.
	 */
	@Test
	public void testCheckUrlReachable() {
		System.out.println("checkUrlReachable");

		// Let's call the method under test
		apiClient.checkURL();
		apiClient.setListener(new Listener() {
			
			@Override
			public void onAPIServerFound() {
				assertEquals("Did expect to find API server", serverfindExpected, true);
			}
			
			@Override
			public void onAPIServerMissing() {
				assertEquals("Did not expect to find API server", serverfindExpected, false);
			}
		});

		int result;

		// Let's call the callback. ArgumentCaptor.capture() works like a matcher.
		verify(mockAPICollaborator, times(1)).checkIfURLReachable((TinyhebAPIClient) callbackCaptor.capture());
		assertEquals("Initially Server shoulld not be available", serverfindExpected, apiClient.isAvailable());

		result = 404;
		serverfindExpected = false;
		callbackCaptor.getValue().onResponse(result);
		assertEquals("On status 404, server should not be available", serverfindExpected, apiClient.isAvailable());

		result = 200;
		serverfindExpected = true;
		callbackCaptor.getValue().onResponse(result);
		assertEquals(serverfindExpected, apiClient.isAvailable());
	}

}
