/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tinyheb.data.rest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TinyhebAPICollaborator {

    public static int ERROR_CODE = -1;

    public TinyhebAPICollaborator() {
    }

    public void checkIfURLReachable(final TinyhebAPIClient callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                try {
                    conn = (HttpURLConnection) new URL("http://192.168.0.102:8080/").openConnection();
                    conn.setRequestMethod("HEAD");
                    callback.onResponse(conn.getResponseCode());
                } catch (MalformedURLException ex) {
                    Logger.getLogger(TinyhebAPICollaborator.class.getName()).log(Level.SEVERE, null, ex);
                    callback.onResponse(ERROR_CODE);
                } catch (IOException ex) {
                    Logger.getLogger(TinyhebAPICollaborator.class.getName()).log(Level.SEVERE, null, ex);
                    callback.onResponse(ERROR_CODE);
                }
            }
        }).start();
    }

}
