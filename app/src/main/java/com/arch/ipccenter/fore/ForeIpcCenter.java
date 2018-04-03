package com.arch.ipccenter.fore;

import android.os.Bundle;

import com.arch.ipccenter.base.IpcCenter;

/**
 * Created by wurongqiu on 2018/3/30.
 */

public class ForeIpcCenter extends IpcCenter{


    @Override
    public int ipcCall(int ipcMsg, Bundle inBundle, Bundle outBundle) {
        return 0;
    }

    @Override
    public int asyncIpcCall(int ipcMsg, Bundle inBundle) {
        return 0;
    }
}
