// IIpcCallback.aidl
package com.arch.ipccenter;

// Declare any non-default types here with import statements

interface IIpcCallback {
   	int onIpcCallback(in int callbackId, in Bundle inBundle, inout Bundle inoutBundle);
}

