package org.tinyheb.mobile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.Robolectric;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.view.View;
import org.tinyheb.test.TinyhebTestRunner;
import org.robolectric.shadows.ShadowConnectivityManager;

@RunWith(TinyhebTestRunner.class)
public class MainActivityTests {

    MainActivity activity;

    @Before
    public void setUp() throws Exception {
        if (activity == null) {
            activity = Robolectric.buildActivity(MainActivity.class).create().visible().get();
        }

    }

    @Test
    public void testStartPatronInsertButton() {
        activity.findViewById(R.id.btnInsertPatrons).performClick();

        Intent expectedIntent = new Intent(activity, PatronInsertActivity.class);
        Intent resultIntent = Shadows.shadowOf(activity).getNextStartedActivity();
        assertEquals("Patron insert activity should have been startet.", resultIntent, expectedIntent);
    }

    @Test
    public void testStartPatronShowButton() {
        activity.findViewById(R.id.btnShowPatrons).performClick();

        Intent expectedIntent = new Intent(activity, MasterDataPagerActivity.class);
        Intent resultIntent = Shadows.shadowOf(activity).getNextStartedActivity();
        assertEquals("Master data activity should have been startet.", resultIntent, expectedIntent);
    }

    @Test
    public void testStartSynchronizeButton() {
        activity.findViewById(R.id.btnStartSync).performClick();

        Intent expectedIntent = new Intent(activity, APISyncService.class);
        Intent resultIntent = Shadows.shadowOf(activity).getNextStartedService();
        assertEquals("API Synchronisation Service should have been startet.", resultIntent, expectedIntent);
    }

    private ConnectivityManager connectivityManager;
    private ShadowConnectivityManager shadowConnectivityManager;


    @Test
    public void testWifiReceiverHandling() {
        Intent intent = new Intent("org.tinyheb.data.rest.WifiReceiver");
        RuntimeEnvironment.application.sendBroadcast(intent);
        
        
        connectivityManager = getConnectivityManager();
        assertTrue("Wifi should be connected", connectivityManager.getActiveNetworkInfo().isConnected());

        assertTrue("Button for Synchronisation should be gone - Visibility = " + activity.findViewById(R.id.btnStartSync).getVisibility(), activity.findViewById(R.id.btnStartSync).getVisibility() == View.GONE);

    }

    private ConnectivityManager getConnectivityManager() {
        return (ConnectivityManager) RuntimeEnvironment.application.getSystemService(Context.CONNECTIVITY_SERVICE);
    }
}
