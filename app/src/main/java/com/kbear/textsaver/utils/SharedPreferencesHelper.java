package com.kbear.textsaver.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by allen on 8/9/14.
 */
public class SharedPreferencesHelper {
    private static final SharedPreferencesHelper sSharedPreferencesHelper = new SharedPreferencesHelper();

    private static SharedPreferences sSharedPreferences;
    private static SharedPreferences.Editor sEditor;

    private static final String SHARED_PREFERENCES = "TextSaverPreferences";

    public static void init(Context context) {
        sSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        sEditor = sSharedPreferences.edit();
    }

    public static SharedPreferencesHelper getInstance() {
        return sSharedPreferencesHelper;
    }

    public void setPreference(String key, String string) {
        sEditor.putString(key, string);
        sEditor.commit();
    }

    public void setPreference(String key, Set<String> strings) {
        sEditor.putStringSet(key, strings);
        sEditor.commit();
    }

    public void setPreference(String key, boolean flag) {
        sEditor.putBoolean(key, flag);
        sEditor.commit();
    }

    public String getString(String key) {
        return sSharedPreferences.getString(key, null);
    }

    public Set<String> getStringSet(String key) {
        return sSharedPreferences.getStringSet(key, null);
    }

    public boolean getBoolean(String key) {
        return sSharedPreferences.getBoolean(key, false);
    }

    public boolean contains(String key) {
        return sSharedPreferences.contains(key);
    }

    public void delete(String key) {
        sEditor.remove(key);
        sEditor.commit();
    }

    public void deleteAll() {
        sEditor.clear();
        sEditor.commit();
    }
}
