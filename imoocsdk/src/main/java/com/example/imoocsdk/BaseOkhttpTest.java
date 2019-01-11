package com.example.imoocsdk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.imoocsdk.okhttp.CommonOkHttpClient;
import com.example.imoocsdk.okhttp.listener.DisposeDataHandle;
import com.example.imoocsdk.okhttp.listener.DisposeDataListener;
import com.example.imoocsdk.okhttp.request.CommonRequest;
import com.example.imoocsdk.okhttp.response.CommonJsonCallback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BaseOkhttpTest extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    /**
     * 用http发送一个最基本的请求
     */

    private void sendRequest() {
        //创建一个okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .url("https://www.baidu.com/")
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            //请求失败
            @Override
            public void onFailure(Call call, IOException e) {

            }
            //请求成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

    }

    private void test(){
        CommonOkHttpClient.get(CommonRequest.createGetRequest("http://www.baidu.com",null),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {

            }

            @Override
            public void onFailure(Object responseObj) {

            }
        }));
    }
}
