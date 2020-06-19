package com.mapmyindia.intouchsdkdemo.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.mapmyindia.sdk.tracking.Config;


public class PreferenceHelper {

    private static final PreferenceHelper ourInstance = new PreferenceHelper();
    private final String KEY_IS_INIT = "is_init";
    private final String KEY_CONFIG_DATA = "key_config_data";
    private final String KEY_DEVICE_NAME = "key_device_name";

    private PreferenceHelper() {
    }

    public static PreferenceHelper getInstance() {
        return ourInstance;
    }


    public boolean isInitialized(Context context) {
        if (context == null)
            throw new IllegalArgumentException("Please pass valid context");

        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY_IS_INIT, false);
    }

    @SuppressLint("ApplySharedPref")
    public void setInitializeSuccess(Context context, boolean isInitialized) {
        if (context == null)
            throw new IllegalArgumentException("Please pass valid context");
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(KEY_IS_INIT, isInitialized).commit();
    }

    public boolean setConfiguration(Context context, Config obj) {
        if (obj != null) {
            try {

                PreferenceManager.getDefaultSharedPreferences(context).edit()
                        .putString(KEY_CONFIG_DATA, new Gson().toJson(obj, Config.class))
                        .apply();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

        }
        return false;
    }


    public Config getBeaconConfiguration(Context context) {
        Config configuration = null;
        try {
            String str = PreferenceManager.getDefaultSharedPreferences(context).getString(KEY_CONFIG_DATA, null);
            if (str != null)
                configuration = new Gson().fromJson(str, Config.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return configuration;
    }

    public boolean setDeviceName(Context context, String name) {
        try {

            PreferenceManager.getDefaultSharedPreferences(context).edit()
                    .putString(KEY_DEVICE_NAME, name)
                    .apply();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    public String getDeviceName(Context context) {
        String name = null;
        try {
            name = PreferenceManager.getDefaultSharedPreferences(context).getString(KEY_DEVICE_NAME, null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return name;
    }


}
