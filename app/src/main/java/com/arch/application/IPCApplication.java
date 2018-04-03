package com.arch.application;

import android.content.Context;
import android.os.Handler;

import com.arch.base.application.BaseApplication;
import com.arch.ipccenter.fore.ForeEngine;

/**
 * Created by wurongqiu on 2018/3/30.
 */

public class IPCApplication extends BaseApplication {

    static final String TAG = "IPCApplication";

    /** 后台是否准备好了 */
    public static boolean sIsBackEngineReady = false;

    static Context mContext;
    static Handler sMainThreadHandler;

    @Override
    public void onCreate() {
        super.onCreate();

        Context context = getApplicationContext();
        AppProfile.setContext(context);
        AppProfile.setStartTime(System.currentTimeMillis() /*+ DataConst.RuntimeStatus.CURRENT_TIME*/);
        sMainThreadHandler = new Handler();


//        ForeService.tryStartForeService();
        ForeEngine.getInstance().connectBackEngineAsync();
    }
}
