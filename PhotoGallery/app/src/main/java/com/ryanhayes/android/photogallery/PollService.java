package com.ryanhayes.android.photogallery;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

/**
 * Created by Ryan Hayes on 7/9/2017.
 */

public class PollService extends IntentService {

    private static final String TAG = "PollService";
    public static final String ACTION_SHOW_NOTIFICATION = "com.ryanhayes.android.photogallery.SHOW_NOTIFICATION";

    public PollService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressWarnings("deprecation")
        boolean isNetworkAvailable = cm.getBackgroundDataSetting() && cm.getActiveNetworkInfo() != null;
        if(!isNetworkAvailable)return;

        sendBroadcast(new Intent(ACTION_SHOW_NOTIFICATION));
    }

}
