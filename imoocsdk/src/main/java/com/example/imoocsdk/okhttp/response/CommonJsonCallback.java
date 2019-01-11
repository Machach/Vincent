package com.example.imoocsdk.okhttp.response;

import android.os.Handler;
import android.os.Looper;


import com.example.imoocsdk.okhttp.exception.OkHttpException;
import com.example.imoocsdk.okhttp.listener.DisposeDataHandle;
import com.example.imoocsdk.okhttp.listener.DisposeDataListener;
import com.example.imoocsdk.okhttp.listener.DisposeHandleCookieListener;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;
import org.json.JSONObject;

/**
 * @author vision
 * @function 专门处理JSON的回调
 */
public class CommonJsonCallback implements Callback {

    /**
     * the logic layer exception, may alter in different app
     */
    protected final String RESULT_CODE = "ecode"; // 有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";
    protected final String COOKIE_STORE = "Set-Cookie"; // decide the server it
    // can has the value of
    // set-cookie2

    /**
     * the java layer exception, do not same to the logic error
     */
    protected final int NETWORK_ERROR = -1; // the network relative error
    protected final int JSON_ERROR = -2; // the JSON relative error
    protected final int OTHER_ERROR = -3; // the unknow error

    /**
     * 将其它线程的数据转发到UI线程
     */
    private Handler mDeliveryHandler;//进行消息的转发
    private DisposeDataListener mListener;
    private Class<?> mClass;

    public CommonJsonCallback(DisposeDataHandle handle) {
        this.mListener = handle.mListener;
        this.mClass = handle.mClass;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(final Call call, final IOException ioexception) {
        /**
         * 此时还在非UI线程，因此要转发
         */
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OkHttpException(NETWORK_ERROR, ioexception));
            }
        });
    }

    //真正的响应处理函数
    @Override
    public void onResponse(final Call call, final Response response) throws IOException {
        final String result = response.body().string();
        final ArrayList<String> cookieLists = handleCookie(response.headers());
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
                /**
                 * handle the cookie
                 */
                if (mListener instanceof DisposeHandleCookieListener) {
                    ((DisposeHandleCookieListener) mListener).onCookie(cookieLists);
                }
            }
        });
    }

    private ArrayList<String> handleCookie(Headers headers) {
        ArrayList<String> tempList = new ArrayList<String>();
        for (int i = 0; i < headers.size(); i++) {
            if (headers.name(i).equalsIgnoreCase(COOKIE_STORE)) {
                tempList.add(headers.value(i));
            }
        }
        return tempList;
    }

    /**
     * 处理服务器返回的数据
     * @param responseObj
     */
    private void handleResponse(Object responseObj) {
        //保证代码健壮性
        if (responseObj == null || responseObj.toString().trim().equals("")) {
            mListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }

        try {
            /**
             * 协议确定后看这里如何修改
             */
            JSONObject result = new JSONObject(responseObj.toString());

            if(result.has(RESULT_CODE)){
                //從json對象中取出響應碼，若為0，則是正確的響應。
                if(result.getInt(RESULT_CODE) == RESULT_CODE_VALUE){
                    if (mClass == null){
                        mListener.onSuccess(responseObj);
                    }else {

                        //需要將json對象轉換為實體對象
                        Object obj = ResponseEntityToModule.parseJsonObjectToModule(result,mClass);
                        //正確轉為了實體對象
                        if (obj != null){
                            mListener.onSuccess(obj);
                        }else {
                            //返回的不是合法的json
                            mListener.onFailure(new OkHttpException(JSON_ERROR,EMPTY_MSG));
                        }
                    }
                }else {
                    mListener.onFailure(new OkHttpException(OTHER_ERROR,result.get(RESULT_CODE)));
                }
            }
        } catch (Exception e) {
            mListener.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()));
            e.printStackTrace();
        }
    }
}