package com.kbear.textsaver.services;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by allen on 8/9/14.
 */
public class TextService {
    static Context context;

    public static void saveText(String key, String s) {
        SharedPreferencesHelper.getInstance().setPreference(key, s);
    }

    public static void getAllText(String key) {
        SharedPreferencesHelper.getInstance().getStringSet(key);
    }
}
