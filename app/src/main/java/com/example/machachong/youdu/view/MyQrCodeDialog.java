package com.example.machachong.youdu.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.imoocsdk.adutil.Utils;
import com.example.machachong.youdu.R;
import com.example.machachong.youdu.manager.UserManager;
import com.example.machachong.youdu.util.Util;

public class MyQrCodeDialog extends Dialog {
    private Context mContext;

    private ImageView mQrCodeView;
    private TextView mTickView;
    private TextView mCloseView;

    public MyQrCodeDialog(Context context){
        super(context, 0);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_mycode_layout);
        initView();
    }

    private void initView(){
        mQrCodeView = (ImageView) findViewById(R.id.qrcode_view);
        mTickView = findViewById(R.id.tick_view);
        mCloseView = findViewById(R.id.close_view);
        mCloseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        String name = UserManager.getInstance().getmUser().data.name;


        mQrCodeView.setImageBitmap(Util.createQRCode(
                Utils.dip2px(mContext, 200),
                Utils.dip2px(mContext, 200),
                name));
    }


}
