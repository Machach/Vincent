package com.example.machachong.youdu.view.fragment.home;


import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imoocsdk.okhttp.listener.DisposeDataListener;
import com.example.machachong.youdu.R;
import com.example.machachong.youdu.activity.LoginActivity;
import com.example.machachong.youdu.activity.SettingActivity;
import com.example.machachong.youdu.module.update.UpdateInfo;
import com.example.machachong.youdu.module.update.UpdateModel;
import com.example.machachong.youdu.network.http.RequestCenter;
import com.example.machachong.youdu.service.update.UpdateService;
import com.example.machachong.youdu.share.ShareDialog;
import com.example.machachong.youdu.util.Util;
import com.example.machachong.youdu.view.CommonDialog;
import com.example.machachong.youdu.view.MyQrCodeDialog;
import com.example.machachong.youdu.view.fragment.BaseFragment;
import com.example.machachong.youdu.manager.UserManager;

import cn.sharesdk.framework.Platform;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 *
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {
    /**
     * UI
     */
    private View mContentView;
    private RelativeLayout mLoginLayout;
    private CircleImageView mPhotoView;
    private TextView mLoginInfoView;
    private TextView mLoginView;
    private RelativeLayout mLoginedLayout;
    private TextView mUserNameView;
    private TextView mTickView;
    private TextView mVideoPlayerView;
    private TextView mShareView;
    private TextView mQrCodeView;
    private TextView mUpdateView;

    //自定义了一个广播接收器
    private LoginBroadcastReceiver mReceiver = new LoginBroadcastReceiver();

    public MineFragment(){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        registerLoginBroadcast();
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.fragment_mine_layout,null,false);
        initView();
        return mContentView;

    }

    public void initView(){
        mLoginLayout = (RelativeLayout) mContentView.findViewById(R.id.login_layout);
        mLoginLayout.setOnClickListener(this);
        mLoginedLayout = (RelativeLayout) mContentView.findViewById(R.id.logined_layout);
        mLoginedLayout.setOnClickListener(this);

        mPhotoView = (CircleImageView) mContentView.findViewById(R.id.photo_view);
        mPhotoView.setOnClickListener(this);
        mLoginView = (TextView) mContentView.findViewById(R.id.login_button);
        mLoginView.setOnClickListener(this);
        mVideoPlayerView = (TextView) mContentView.findViewById(R.id.video_setting_view);
        mVideoPlayerView.setOnClickListener(this);
        mShareView = (TextView) mContentView.findViewById(R.id.share_imooc_view);
        mShareView.setOnClickListener(this);
        mQrCodeView = (TextView) mContentView.findViewById(R.id.my_qrcode_view);
        mQrCodeView.setOnClickListener(this);
        mLoginInfoView = (TextView) mContentView.findViewById(R.id.login_info_view);
        mUserNameView = (TextView) mContentView.findViewById(R.id.username_view);
        mTickView = (TextView) mContentView.findViewById(R.id.tick_view);

        mUpdateView = (TextView) mContentView.findViewById(R.id.update_view);
        mUpdateView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.video_setting_view:
                mContext.startActivity(new Intent(mContext,SettingActivity.class));
                break;
            case R.id.update_view:
                checkVersion();
                break;
            case R.id.login_button:
                mContext.startActivity(new Intent(mContext,LoginActivity.class));
                break;
            case R.id.my_qrcode_view:
                if (!UserManager.getInstance().hasLoigin()){
                    toLogin();
                }else {
                    MyQrCodeDialog dialog = new MyQrCodeDialog(mContext);
                    dialog.show();
                }
                break;
            case R.id.share_imooc_view:
                shareImooc();
                break;
        }
    }

    private void shareImooc(){
        ShareDialog dialog = new ShareDialog(mContext ,false);
        dialog.setmShareType(Platform.SHARE_TEXT);
        dialog.setmShareTitle("慕课网");
        dialog.setmShareTileUrl("http://www.imooc.com");
        dialog.setmShareSite("imooc");
        dialog.setmShareText("慕课网");
        dialog.setmShareSiteUrl("http://www.imooc.com");
        dialog.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterLoginBroadcast();
    }

    private void checkVersion(){
        RequestCenter.checkVersion(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                final UpdateModel updateModel = (UpdateModel)responseObj;

                if (Util.getVersionCode(mContext) < updateModel.data.currentVersion){
                    CommonDialog dialog = new CommonDialog(mContext, getString(
                            R.string.update_new_version),getString(R.string.update_title),
                            getString(R.string.update_install), getString(R.string.cancel),
                            new CommonDialog.DialogClickListener(){
                                @Override
                                public void onDialogClick() {
                                    Intent intent = new Intent(mContext, UpdateService.class);
                                    mContext.startActivity(intent);

                                }
                            });
                    dialog.show();
                }else {
                    Toast.makeText(getContext(), "已是最新版本", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Object responseObj) {
                Toast.makeText(getContext(),"檢查版本失敗", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 去登陆页面
     */
    private void toLogin() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
    }

    private void registerLoginBroadcast(){
        IntentFilter filter = new IntentFilter(LoginActivity.LOGIN_ACTION);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(mReceiver,filter);
    }

    private void unregisterLoginBroadcast(){
        LocalBroadcastManager.getInstance(mContext).unregisterReceiver(mReceiver);
    }


    private class LoginBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            //更新UI
            mLoginLayout.setVisibility(View.GONE);
            mLoginedLayout.setVisibility(View.VISIBLE);
            mUserNameView.setText(UserManager.getInstance().getmUser().data.name);
            mTickView.setText(UserManager.getInstance().getmUser().data.tick);
        }
    }

}

