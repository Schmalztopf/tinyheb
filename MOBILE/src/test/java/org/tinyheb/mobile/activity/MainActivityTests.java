package org.tinyheb.mobile.activity;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.tinyheb.mobile.R;
import org.tinyheb.mobile.activity.MainActivity;
import org.tinyheb.mobile.activity.MasterDataPagerActivity;
import org.tinyheb.mobile.activity.PatronInsertActivity;
import org.tinyheb.mobile.service.APIGetAllDataService;
import org.tinyheb.test.TinyhebTestRunner;

import android.content.Intent;
import android.view.View;

@Config(sdk = 23)
@RunWith(TinyhebTestRunner.class)
public class MainActivityTests {

	static MainActivity activity;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		
		if (activity == null) {
			activity = Robolectric.buildActivity(MainActivity.class).create().visible().get();
		}
	}

	@Test
	public void testStartPatronInsertButton() {
		//activity.findViewById(R.id.btnInsertPatrons).performClick();

		Intent expectedIntent = new Intent(activity, PatronInsertActivity.class);
		Intent resultIntent = Shadows.shadowOf(activity).getNextStartedActivity();
		assertEquals("Patron insert activity should have been startet.", resultIntent.toString(), expectedIntent.toString());
	}

	@Test
	public void testStartPatronShowButton() {
		//activity.findViewById(R.id.btnShowPatrons).performClick();

		Intent expectedIntent = new Intent(activity, MasterDataPagerActivity.class);
		Intent resultIntent = Shadows.shadowOf(activity).getNextStartedActivity();
		assertEquals("Master data activity should have been startet.", resultIntent.toString(), expectedIntent.toString());
	}

	@Test
	public void testStartSynchronizeButton() {
		activity.findViewById(R.id.btnStartSync).performClick();

		Intent expectedIntent = new Intent(activity, APIGetAllDataService.class);
		Intent resultIntent = Shadows.shadowOf(activity).getNextStartedService();
		assertEquals("API Synchronisation Service should have been startet.", resultIntent.toString(), expectedIntent.toString());
	}


	@Test
	public void testAPIServerSearchResultHandling() {
		activity.onAPIServerFound();
		assertEquals("Button for Synchronisation should be gone - Visibility = " + activity.findViewById(R.id.btnStartSync).getVisibility(), activity.findViewById(R.id.btnStartSync).getVisibility() , View.VISIBLE);

		activity.onAPIServerMissing();
		assertEquals("Button for Synchronisation should be gone - Visibility = " + activity.findViewById(R.id.btnStartSync).getVisibility(), activity.findViewById(R.id.btnStartSync).getVisibility() , View.GONE);
	}
	
	@Test
	public void testWifiConnectionChangeEventHandling() {
		activity.onWifiDisconnected();
		assertEquals("Button for Synchronisation should be gone - Visibility = " + activity.findViewById(R.id.btnStartSync).getVisibility(), activity.findViewById(R.id.btnStartSync).getVisibility() , View.GONE);
	}
	

}
