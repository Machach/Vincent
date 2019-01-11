package com.example.imoocsdk.okhttp.listener;

public class DisposeDataHandle {
    public DisposeDataListener mListener = null;
    public Class<?> mClass = null;
    public String mSource = null;

    public DisposeDataHandle(DisposeDataListener listener){
        this.mListener = listener;
    }

    public DisposeDataHandle(DisposeDataListener listener,Class<?> claz){
        this.mListener = listener;
        this.mClass = claz;
    }

    public DisposeDataHandle(DisposeDataListener listener,String source){
        this.mListener = listener;
        this.mSource = source;
    }
}
