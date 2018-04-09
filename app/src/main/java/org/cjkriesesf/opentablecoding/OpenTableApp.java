package org.cjkriesesf.opentablecoding;

import android.app.Application;

import timber.log.Timber;

public class OpenTableApp extends Application{

    @Override public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
