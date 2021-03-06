package com.kbear.textsaver.activities;

import android.app.Application;

import com.kbear.textsaver.utils.SharedPreferencesHelper;
import com.kbear.textsaver.utils.TrackingService;

/**
 * Created by allen on 8/9/14.
 */
public class TextSaverApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    private void init() {
        SharedPreferencesHelper.init(this);
        TrackingService.init(this);
    }
}
