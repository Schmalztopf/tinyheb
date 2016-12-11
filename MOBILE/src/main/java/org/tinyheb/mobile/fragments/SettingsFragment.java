package org.tinyheb.mobile.fragments;

import org.tinyheb.mobile.R;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class SettingsFragment  extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }
}
