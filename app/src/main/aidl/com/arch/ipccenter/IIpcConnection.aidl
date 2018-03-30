// IIpcConnection.aidl
package com.arch.ipccenter;

// Declare any non-default types here with import statements
import com.mistong.ipccenter.IIpcCallback;

interface IIpcConnection {
    int ipcCall(in int ipcMsg, in Bundle inBundle, inout Bundle inoutBundle);
	void regCallback(IIpcCallback callback);
    void unregCallback(IIpcCallback callback);
}
