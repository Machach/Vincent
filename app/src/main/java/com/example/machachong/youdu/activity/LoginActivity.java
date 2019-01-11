package com.example.machachong.youdu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imoocsdk.okhttp.listener.DisposeDataListener;
import com.example.machachong.youdu.R;
import com.example.machachong.youdu.activity.base.BaseActivity;
import com.example.machachong.youdu.module.PushMessage;
import com.example.machachong.youdu.module.user.User;
import com.example.machachong.youdu.network.http.RequestCenter;
import com.example.machachong.youdu.manager.UserManager;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    public static final String LOGIN_ACTION = "com.imooc.action.LOGIN_ACTION";
    /**
     *UI
     */
    private EditText mUserNameView;
    private EditText mPasswordView;
    private TextView mLoginView;
    private ImageView mQQLoginView;

    /**
     *data
     */
    private PushMessage mPushMessage;
    private boolean fromPush;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);

        initData();
        initView();
    }

    private void initData(){
        Intent intent = getIntent();
        if (intent.hasExtra("PushMessage")){
            mPushMessage = (PushMessage)intent.getSerializableExtra("PushMessage");
        }
        fromPush = intent.getBooleanExtra("fromPush",false);
    }

    private void initView(){
        mUserNameView = (EditText)findViewById(R.id.associate_email_input);
        mPasswordView = (EditText)findViewById(R.id.login_input_password);
        mLoginView = (TextView)findViewById(R.id.login_button);
        mLoginView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.login_button:
                login();
                break;
        }

    }

    private void login(){
        String userName = mUserNameView.getText().toString().trim();
        String pwd = mPasswordView.getText().toString().trim();

        //發送請求
        RequestCenter.login(userName, pwd, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                User user = (User)responseObj;
                UserManager.getInstance().setUser(user);//通過UserManager管理用戶信息

                sendLoginBrodcast();
                finish();
            }

            @Override
            public void onFailure(Object responseObj) {
                Toast.makeText(getApplicationContext(), "密码错误", Toast.LENGTH_SHORT);

            }
        });

    }

    private void sendLoginBrodcast(){
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(LOGIN_ACTION));
    }
}
