package com.example.machachong.youdu.network.http;

import com.example.imoocsdk.okhttp.CommonOkHttpClient;
import com.example.imoocsdk.okhttp.HttpConstant;
import com.example.imoocsdk.okhttp.listener.DisposeDataHandle;
import com.example.imoocsdk.okhttp.listener.DisposeDataListener;
import com.example.imoocsdk.okhttp.request.CommonRequest;
import com.example.imoocsdk.okhttp.request.RequestParams;
import com.example.machachong.youdu.module.course.BaseCourseModel;
import com.example.machachong.youdu.module.recommand.BaseRecommandModule;
import com.example.machachong.youdu.module.update.UpdateModel;
import com.example.machachong.youdu.module.user.User;

/**
 *
 */

public class RequestCenter {
    public static void PostRequest(String url, RequestParams params,
                                    DisposeDataListener listener, Class<?>claz){
        CommonOkHttpClient.get(CommonRequest.createGetRequest(url, params), new DisposeDataHandle(listener, claz));
    }

    public static void requestRecommandData(DisposeDataListener listener){
        RequestCenter.PostRequest(HttpConstants.HOME_RECOMMAND,null,listener,BaseRecommandModule.class);
    }

    public static void checkVersion(DisposeDataListener listener){
        RequestCenter.PostRequest(HttpConstants.CHECK_UPDATE, null, listener, UpdateModel.class);
    }

    public static void login(String userName, String passwd, DisposeDataListener listener){
        RequestParams params = new RequestParams();
        params.put("mb", userName);
        params.put("pwd", passwd);
        RequestCenter.PostRequest(HttpConstants.LOGIN, params, listener, User.class);
    }

    public static void requestCourseDetail(String courseID, DisposeDataListener listener){
        RequestParams params = new RequestParams();
        params.put("courseID", courseID);
        RequestCenter.PostRequest(HttpConstants.COURSE_DETAIL, params, listener,BaseCourseModel.class);
    }






}
