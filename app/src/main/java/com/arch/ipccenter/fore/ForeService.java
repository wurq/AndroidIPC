package com.arch.ipccenter.fore;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class ForeService extends Service {
    static final String TAG = "ForeService";

    public static final String ACTION_NAME = "com.arch.ipccenter.fore.ForeService";


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
