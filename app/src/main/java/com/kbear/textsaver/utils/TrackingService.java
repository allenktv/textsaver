package com.kbear.textsaver.utils;

import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.kbear.textsaver.R;

/**
 * Created by allen on 8/9/14.
 */
public class TrackingService {
    private Tracker mTracker;

    private static final TrackingService sTrackingService = new TrackingService();

    public static void init(Context c) {
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(c);
        sTrackingService.mTracker = analytics.newTracker(R.xml.app_tracker);
    }

    public static TrackingService getInstance() {
        return sTrackingService;
    }

    public void sendEvent(String event) {
        mTracker.send(new HitBuilders.EventBuilder()
            .setAction(event)
            .build());
    }
}
