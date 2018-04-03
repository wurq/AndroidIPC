package com.arch.ipccenter.fore;

import android.content.Context;

import com.arch.application.AppProfile;
import com.arch.ipccenter.IIpcConnection;

/**
 * Created by wurongqiu on 2018/3/30.
 */

public class ForeEngine {
    final static String TAG = "ForeEngine";

    //---------------------------------------------------------------------------------
    IIpcConnection mBackEngine;

    /**
     * 后台进程AIDL通道是否已经连通。
     */
    public volatile boolean mIsBackConnected = false;

    boolean mIsStop = false;

    Context mContext;

    static ForeEngine mInstance = null;

    static ForeEngineHandler mForeMainHandler;

    static final int MAX_CONNECT_RETRY_COUNT = 10;

    volatile static int sConnectRetryCount;

//    public static boolean sIsExitSoftware = false;

//    static int sRebootViewId = -1;
//    static boolean sIsNeedReboot = false;

    public static volatile boolean sIsMeriReady = false;

    static boolean mNeedShowMainPage = false;

    // ------------------------------------------------------
    public static ForeEngine getInstance() {
        if(mInstance == null) {
            synchronized (ForeEngine.class) {
                if(mInstance == null) {
                    mInstance = new ForeEngine();
                }
            }
        }
        return mInstance;
    }

    ForeEngine() {
        mContext = AppProfile.getContext();
        mForeMainHandler = new ForeEngineHandler(this);
        sConnectRetryCount = 0;


        mForeMainHandler.post(new Runnable() {
            @Override
            public void run() {
                // 注册监听系统消息
            }
        });
    }

    public void connectBackEngineAsync() {

    }

}
