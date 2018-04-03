package com.arch.ipccenter.back;

import android.os.Bundle;

import com.arch.ipccenter.base.IpcCenter;

/**
 * Created by wurongqiu on 2018/3/30.
 */

public class BackIpcCenter extends IpcCenter{
    private static final String TAG = "BackIpcCenter";

    static BackIpcCenter sInstance = null;


    @Override
    public int ipcCall(int ipcMsg, Bundle inBundle, Bundle outBundle) {
        return 0;
    }

    @Override
    public int asyncIpcCall(int ipcMsg, Bundle inBundle) {
        return 0;
    }
}
