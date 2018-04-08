package com.arch.ipccenter.fore;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.arch.application.AppProfile;
import com.arch.commonconst.HostAction;
import com.arch.ipccenter.IIpcConnection;
import com.arch.ipccenter.base.IpcCenter;

import static com.arch.ipccenter.fore.ForeEngineHandler.MSG_TRY_CONNECT_BACK_ENGINE;

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

    /**
     * 后台进程的service connection。
     */
    ServiceConnection mBackEngineConn = new ServiceConnection() {
        /**
         * binService成功（后台到前台的通道此时已打通）后会被调用。 将前台回调注册到后台，打通后台到前台的通道。
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "back engine is connected");
            onBackServiceConnected(name, service);
        }

        /**
         * unbinService成功后会被调用。 移除先前注册的回调。
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "back engine is disconnected");
            onBackServiceDisconnected(name);
        }
    };



    private void onBackServiceConnected(ComponentName name, IBinder service) {
    }

    private void onBackServiceDisconnected(ComponentName name) {
    }

    /*
     *
     */
    public boolean bindBackEngine() {
        Log.i(TAG, "connect back engine");
        // 启动并连接后台
        Intent intent = new Intent();
        // 在部分手机上需要设置componentName ref：https://www.jianshu.com/p/97de61256e5b
        ComponentName componentName= new ComponentName( HostAction.APP_PACKAGE_NAME, HostAction.BACK_ENGINE);
        intent.setComponent(componentName);

        intent.setAction(HostAction.BACK_ENGINE);
        intent.putExtra(IpcCenter.IPC_MSG_ID, IpcCenter.IpcMsg.F2B_ASYNC_JUST_START);
        boolean ret = false;
        try {
            ret = mContext.bindService(intent, mBackEngineConn, Service.BIND_AUTO_CREATE);
        } catch (Exception e) {
            // do nothing
        }
        Log.d(TAG, "bind BackEngine Service return =  " + ret);
        return ret;
    }

    /*
     * 通过AIDL异步启动
     */
    public void connectBackEngineAsync() {
        Log.i(TAG, "connect back engine async");

        mIsStop = false;
        sConnectRetryCount = 0;

        if (!mIsBackConnected) {
            Message msg = Message.obtain();
            msg.what = MSG_TRY_CONNECT_BACK_ENGINE;
            mForeMainHandler.sendMessageAtFrontOfQueue(msg);
        }

        // 启动前台fore的service
        if (!ForeService.sIsServiceOn) {
            mForeMainHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
//                    Intent fsIntent = new Intent(ForeService.ACTION_NAME);
                    Intent fsIntent = new Intent(AppProfile.getContext(),ForeService.class);
                    AppProfile.getContext().startService(fsIntent);
                }
            }, 1000);
        }
    }



}
