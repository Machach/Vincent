package com.example.machachong.youdu.db;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.machachong.youdu.application.ImoocApplication;

public class SharedPreferenceManager {
    private static SharedPreferenceManager mInstance;
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    public static final String SHARE_PREFERENCE_NAME = "imooc.pre"; //文件名
    public static final String VIDEO_PLAY_SETTING = "video_play_setting";


    public static SharedPreferenceManager getInstance(){
        if(mInstance == null){
            mInstance = new SharedPreferenceManager();
        }
        return mInstance;
    }

    private SharedPreferenceManager(){

        sp = ImoocApplication.getInstance().getSharedPreferences(SHARE_PREFERENCE_NAME,
                Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void putInt(String key, int value){
        editor.putInt(key, value);
        editor.commit();
    }

    public int getInt(String key, int defalultValue){

        return sp.getInt(key,defalultValue);
    }


}
