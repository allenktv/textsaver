package com.kbear.textsaver.activities;

import android.app.Activity;

import com.google.android.gms.analytics.GoogleAnalytics;

/**
 * Created by allen on 8/9/14.
 */
public class BaseActivity extends Activity {

    @Override
    protected void onStart() {
        super.onStart();
        GoogleAnalytics.getInstance(this).reportActivityStop(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        GoogleAnalytics.getInstance(this).reportActivityStop(this);
    }
}
