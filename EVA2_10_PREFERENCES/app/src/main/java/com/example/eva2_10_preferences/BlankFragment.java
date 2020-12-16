package com.example.eva2_10_preferences;


import android.os.Bundle;


import androidx.preference.PreferenceFragmentCompat;




public class BlankFragment extends PreferenceFragmentCompat {


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

    }




}
