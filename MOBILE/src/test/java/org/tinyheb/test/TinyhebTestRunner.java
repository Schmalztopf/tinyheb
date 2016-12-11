/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tinyheb.test;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;
import org.robolectric.res.Fs;

/**
 *
 * @author haubi
 */
public class TinyhebTestRunner extends RobolectricTestRunner {
    private static final int MAX_SDK_SUPPORTED_BY_ROBOLECTRIC = 18;
    
    public TinyhebTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected AndroidManifest getAppManifest(Config config) {
        String myAppPath = TinyhebTestRunner.class.getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath();
        String myAppPathRoot = myAppPath.substring(0, myAppPath.indexOf("/target"));
        String manifestPath = myAppPathRoot + "/src/main/AndroidManifest.xml";
        String resPath = myAppPathRoot + "/res";
        String assetPath = myAppPathRoot + "/assets";
        

        System.setProperty("android.package", "org.tinyheb.mobile");
        System.setProperty("android.manifest", manifestPath);
        System.setProperty("android.resources", resPath);
        System.setProperty("android.assets", assetPath);

        return new AndroidManifest(Fs.fileFromPath(manifestPath), Fs.fileFromPath(resPath), Fs.fileFromPath(assetPath)) {
        	@Override
        	public int getTargetSdkVersion() {
        		return MAX_SDK_SUPPORTED_BY_ROBOLECTRIC;
        	}
        };
    
    }
    
    
}
