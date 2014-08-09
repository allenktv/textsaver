package com.kbear.textsaver.activities;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.kbear.textsaver.R;
import com.kbear.textsaver.utils.SharedPreferencesHelper;

/**
 * Created by allen on 8/9/14.
 */
public class TextSaverApplication extends Application{
    private Tracker mTracker;

    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    private void init() {
        SharedPreferencesHelper.init(this);
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
        mTracker = analytics.newTracker(R.xml.app_tracker);
    }

    public synchronized Tracker getTracker() {
        return mTracker;
    }
}
