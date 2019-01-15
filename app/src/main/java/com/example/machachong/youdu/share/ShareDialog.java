package com.example.machachong.youdu.share;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.machachong.youdu.R;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.tencent.qq.QQ;

public class ShareDialog extends Dialog implements View.OnClickListener {

    private Context mContext;
    private DisplayMetrics dm;

    /**
     * UI
     */
    private RelativeLayout mWeixinLayout;
    private RelativeLayout mWeixinMomentLayout;
    private RelativeLayout mQQLayout;
    private RelativeLayout mYouDaoLayout;
    private RelativeLayout mDownloadLayout;
    private TextView mCancelView;

    public void setmShareType(int mShareType) {
        this.mShareType = mShareType;
    }

    public void setmShareTitle(String mShareTitle) {
        this.mShareTitle = mShareTitle;
    }

    public void setmShareText(String mShareText) {
        this.mShareText = mShareText;
    }

    public void setmSharePhoto(String mSharePhoto) {
        this.mSharePhoto = mSharePhoto;
    }

    public void setmShareTileUrl(String mShareTileUrl) {
        this.mShareTileUrl = mShareTileUrl;
    }

    public void setmShareSiteUrl(String mShareSiteUrl) {
        this.mShareSiteUrl = mShareSiteUrl;
    }

    public void setmShareSite(String mShareSite) {
        this.mShareSite = mShareSite;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public void setmResourceUrl(String mResourceUrl) {
        this.mResourceUrl = mResourceUrl;
    }

    /**
     * share relative
     */
    private int mShareType; //指定分享类型
    private String mShareTitle; //指定分享内容标题
    private String mShareText; //指定分享内容文本
    private String mSharePhoto; //指定分享本地图片
    private String mShareTileUrl;
    private String mShareSiteUrl;
    private String mShareSite;
    private String mUrl;
    private String mResourceUrl;

    private boolean isShowDownload;

    public ShareDialog(Context context, boolean isShowDownload){
        super(context, R.style.SheetDialogStyle);
        mContext = context;
        dm = mContext.getResources().getDisplayMetrics();
        this.isShowDownload = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_share_layout);
        initView();
    }

    private void initView(){
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = dm.widthPixels; //宽度
        dialogWindow.setAttributes(lp);

        mWeixinLayout = (RelativeLayout) findViewById(R.id.weixin_layout);
        mWeixinLayout.setOnClickListener(this);
        mWeixinMomentLayout = (RelativeLayout) findViewById(R.id.moment_layout);
        mWeixinMomentLayout.setOnClickListener(this);
        mQQLayout = (RelativeLayout) findViewById(R.id.qq_layout);
        mQQLayout.setOnClickListener(this);
        mYouDaoLayout = (RelativeLayout) findViewById(R.id.youdao_layout);
        mYouDaoLayout.setOnClickListener(this);
        mDownloadLayout = (RelativeLayout) findViewById(R.id.download_layout);
        mDownloadLayout.setOnClickListener(this);
        if (isShowDownload) {
            mDownloadLayout.setVisibility(View.VISIBLE);
        }
        mCancelView = (TextView) findViewById(R.id.cancel_view);
        mCancelView.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.weixin_layout:
                share(ShareManager.PlatformType.Wechat);
                break;
            case R.id.moment_layout:
                share(ShareManager.PlatformType.WechatMoments);
                break;
                case R.id.qq_layout:
                    share(ShareManager.PlatformType.QQ);
                    break;
            case R.id.youdao_layout:
                share(ShareManager.PlatformType.YouDao);
                break;
            case R.id.cancel_view:
                dismiss();
                break;


        }

    }



    private void share(ShareManager.PlatformType type){
        ShareData data = new ShareData();
        Platform.ShareParams params = new Platform.ShareParams();

        params.setShareType(mShareType);
        params.setTitle(mShareTitle);
        params.setTitleUrl(mShareTileUrl);
        params.setText(mShareText);
        params.setImagePath(mSharePhoto);
        params.setSite(mShareSite);
        params.setSiteUrl(mShareSiteUrl);
        params.setUrl(mUrl);

        data.params = params;
        data.type = type;

        ShareManager.getInstance().shareData(data, listener);

    }

    /**
     * 分享结果事件监听
     */

    private PlatformActionListener listener = new PlatformActionListener() {
        @Override
        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

        }

        @Override
        public void onError(Platform platform, int i, Throwable throwable) {
            Toast.makeText(mContext, "分享出错", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel(Platform platform, int i) {
            Toast.makeText(mContext, "分享取消", Toast.LENGTH_SHORT).show();


        }
    };
}
