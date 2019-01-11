package com.example.imoocsdk.okhttp.request;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;

/**
 * 接受请求参数，为我们生成Request对象
 */
public class CommonRequest {
    public static Request createPostRequest(String url,RequestParams params){
        FormBody.Builder mFormBodyBuild = new FormBody.Builder();
        if(params != null){

            for (Map.Entry<String ,String>entry:params.urlParams.entrySet()){
                //将请求参数加入到我们的请求构建类中
                mFormBodyBuild.add(entry.getKey(),entry.getValue());
            }
        }
        //通过请求构建类的build方法生成真正的请求体对象
        FormBody mFormBody = mFormBodyBuild.build();

        return new Request.Builder().url(url).post(mFormBody).build();
    }

    public static Request createGetRequest(String url,RequestParams params){
        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if(params != null){
            for (Map.Entry<String,String> entry:params.urlParams.entrySet()){

                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }

        return new Request.Builder()
                .url(urlBuilder.substring(0,urlBuilder.length()-1)).get().build();
    }

    /**
     * @param url
     * @param params
     * @return
     */
    public static Request createMonitorRequest(String url, RequestParams params) {
        StringBuilder urlBuilder = new StringBuilder(url).append("&");
        if (params != null && params.hasParams()) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        return new Request.Builder().url(urlBuilder.substring(0, urlBuilder.length() - 1)).get().build();
    }




}
