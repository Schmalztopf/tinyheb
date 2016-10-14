/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tinyheb.mobile;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowConnectivityManager;
import org.robolectric.shadows.ShadowNetworkInfo;
import org.mockito.exceptions.base.MockitoAssertionError;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.robolectric.Shadows;
import org.tinyheb.mobile.WiFiReceiver;
import org.tinyheb.test.TinyhebTestRunner;


@RunWith(TinyhebTestRunner.class)
public class WiFiReceiverTest {

    @Before
    public void setUp() {
        connectivityManager = getConnectivityManager();
        shadowConnectivityManager = Shadows.shadowOf((ConnectivityManager) connectivityManager);

        context = mock(Context.class);
        mockConnectivityManager = mock(ConnectivityManager.class);

        listener = mock(WiFiReceiver.Listener.class);
        wifiReceiverInstance = new WiFiReceiver();
        wifiReceiverInstance.setListener(listener);
    }

    @After
    public void tearDown() {
        mockConnectivityManager = null;
    }

    private ConnectivityManager connectivityManager;
    private ShadowConnectivityManager shadowConnectivityManager;
    private ConnectivityManager mockConnectivityManager;
    private Context context;
    private WiFiReceiver wifiReceiverInstance;
    private WiFiReceiver.Listener listener;

    @Test
    public void testSetListener() {
        assertNotNull(wifiReceiverInstance.listener);
        assertEquals("Listener should have been set", listener, wifiReceiverInstance.listener);
    }

    @Test
    public void testOnReceiveDisconnectedWifiStateOnWifiDisabled() {
        setMockContext(NetworkInfo.DetailedState.DISCONNECTED, ConnectivityManager.TYPE_WIFI, false, false);

        verifyDisconnected();
    }

    @Test
    public void testOnReceiveDisconnectedWifiStateOnWifiDisConnected() {
        setMockContext(NetworkInfo.DetailedState.DISCONNECTED, ConnectivityManager.TYPE_WIFI, true, false);
        verifyDisconnected();
    }

    @Test
    public void testOnReceiveDisconnectedOtherNetworkState() {
        setMockContext(NetworkInfo.DetailedState.CONNECTED, ConnectivityManager.TYPE_MOBILE, true, true);
        verifyDisconnected();
    }

    @Test
    public void testOnReceiveConnectedWifiStateOnWifiConnected() {
        setMockContext(NetworkInfo.DetailedState.CONNECTED, ConnectivityManager.TYPE_WIFI, true, true);
        verifyConnected();
    }

    private void setMockContext(NetworkInfo.DetailedState connectedState, int networktype, boolean networkavailable, boolean networkconnected) {
        shadowConnectivityManager.setActiveNetworkInfo(
                ShadowNetworkInfo.newInstance(connectedState, networktype, 0, networkavailable, networkconnected));
        when(mockConnectivityManager.getActiveNetworkInfo()).thenReturn(shadowConnectivityManager.getActiveNetworkInfo());
        when(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(mockConnectivityManager);
        wifiReceiverInstance.onReceive(context, null);
    }

    private ConnectivityManager getConnectivityManager() {
        return (ConnectivityManager) RuntimeEnvironment.application.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    private void verifyDisconnected() {
        try {
            verify(wifiReceiverInstance.listener).onAPIWifiDisconnected();
        } catch (MockitoAssertionError e) {
            throw new MockitoAssertionError("Was expecting a call to onAPIWifiDisconnected() but got " + e.getMessage());
        }
    }

    private void verifyConnected() {
        try {
            verify(wifiReceiverInstance.listener).onAPIWifiConnected();
        } catch (MockitoAssertionError e) {
            throw new MockitoAssertionError("Was expecting a call to onAPIWifiConnected() but got " + e.getMessage());
        }
    }
}
