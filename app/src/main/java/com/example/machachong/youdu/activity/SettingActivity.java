package com.example.machachong.youdu.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.imoocsdk.constant.SDKConstant;
import com.example.imoocsdk.core.AdParameters;
import com.example.machachong.youdu.R;
import com.example.machachong.youdu.activity.base.BaseActivity;
import com.example.machachong.youdu.db.SharedPreferenceManager;

import static com.example.machachong.youdu.db.SharedPreferenceManager.SHARE_PREFERENCE_NAME;

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    /**
     * UI
     */
    private RelativeLayout mWifiLayout;
    private RelativeLayout mAlwayLayout;
    private RelativeLayout mNeverLayout;
    private CheckBox mWifBox, mAlwayBox, mNeverBox;
    private ImageView mBackView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_layout);

        initView();
    }

    private void initView(){
        mBackView = (ImageView) findViewById(R.id.back_view);
        mAlwayBox = (CheckBox) findViewById(R.id.alway_check_box);
        mWifBox = (CheckBox)findViewById(R.id.wifi_check_box);
        mNeverBox = (CheckBox)findViewById(R.id.close_check_box);
        mAlwayLayout = (RelativeLayout) findViewById(R.id.alway_layout);
        mWifiLayout = (RelativeLayout) findViewById(R.id.wifi_layout);
        mNeverLayout = (RelativeLayout) findViewById(R.id.close_layout);

        mBackView.setOnClickListener(this);
        mWifiLayout.setOnClickListener(this);
        mAlwayLayout.setOnClickListener(this);
        mNeverLayout.setOnClickListener(this);

        int currentSetting = SharedPreferenceManager.getInstance().getInt(
                SharedPreferenceManager.VIDEO_PLAY_SETTING, 1
        );
        switch (currentSetting){
            case 0:
                mAlwayBox.setBackgroundResource(R.drawable.setting_selected);
                mWifBox.setBackgroundResource(0);
                mNeverBox.setBackgroundResource(0);
                break;
            case 1:
                mAlwayBox.setBackgroundResource(0);
                mWifBox.setBackgroundResource(R.drawable.setting_selected);
                mNeverBox.setBackgroundResource(0);
                break;
            case 2:
                mAlwayBox.setBackgroundResource(0);
                mWifBox.setBackgroundResource(0);
                mNeverBox.setBackgroundResource(R.drawable.setting_selected);
                break;

        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.alway_layout:
                SharedPreferenceManager.getInstance().putInt(
                        SharedPreferenceManager.VIDEO_PLAY_SETTING, 0);
                AdParameters.setCurrentSetting(SDKConstant.AutoPlaySetting.AUTO_PLAY_3G_4G_WIFI);
                mAlwayBox.setBackgroundResource(R.drawable.setting_selected);
                mWifBox.setBackgroundResource(0);
                mNeverBox.setBackgroundResource(0);
                break;
            case R.id.wifi_layout:
                SharedPreferenceManager.getInstance().putInt(
                        SharedPreferenceManager.VIDEO_PLAY_SETTING, 1);
                AdParameters.setCurrentSetting(SDKConstant.AutoPlaySetting.AUTO_PLAY_ONLY_WIFI);
                mAlwayBox.setBackgroundResource(0);
                mWifBox.setBackgroundResource(R.drawable.setting_selected);
                mNeverBox.setBackgroundResource(0);
                break;
            case R.id.close_layout:
                SharedPreferenceManager.getInstance().putInt(
                        SharedPreferenceManager.VIDEO_PLAY_SETTING, 2);
                AdParameters.setCurrentSetting(SDKConstant.AutoPlaySetting.AUTO_PLAY_NEVER);
                mAlwayBox.setBackgroundResource(0);
                mWifBox.setBackgroundResource(0);
                mNeverBox.setBackgroundResource(R.drawable.setting_selected);
                break;
            case R.id.back_view:
                finish();
                break;
        }
    }
}
