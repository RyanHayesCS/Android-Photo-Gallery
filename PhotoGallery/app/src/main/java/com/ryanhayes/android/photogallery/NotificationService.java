package com.ryanhayes.android.photogallery;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

/**
 * Created by Ryan Hayes on 7/9/2017.
 * This class is used in the background as a means of handing
 * network connectivity messages
 * it is capable of being used to send notifications
 */

public class NotificationService extends IntentService {

    private static final String TAG = "NotificationService";
    public static final String ACTION_SHOW_NOTIFICATION = "com.ryanhayes.android.photogallery.SHOW_NOTIFICATION";

    public NotificationService() {
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
