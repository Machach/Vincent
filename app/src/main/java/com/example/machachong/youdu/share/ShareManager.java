package com.example.machachong.youdu.share;

import android.content.Context;

import com.mob.MobSDK;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.system.text.ShortMessage;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import cn.sharesdk.youdao.YouDao;

public class ShareManager {

    private Platform mCurrentPlatform;

    private static ShareManager mShareManager = null;

    public static ShareManager getInstance(){
        if (mShareManager == null){
            synchronized (ShareManager.class){
                if (mShareManager == null){
                    mShareManager = new ShareManager();
                }
            }
        }
        return mShareManager;
    }

    private ShareManager(){
    }

    public static void init(Context context){
        MobSDK.init(context);
    }



    //分享数据入口
    public void shareData(ShareData data, PlatformActionListener listener){

        switch (data.type){

            case QQ:
                mCurrentPlatform = ShareSDK.getPlatform(QQ.NAME);
                break;
            case Wechat:
                mCurrentPlatform = ShareSDK.getPlatform(Wechat.NAME);
                break;
            case YouDao:
                mCurrentPlatform = ShareSDK.getPlatform(YouDao.NAME);
                break;
            case SinaWeibo:
                mCurrentPlatform = ShareSDK.getPlatform(SinaWeibo.NAME);
                break;
            case ShortMessage:
                mCurrentPlatform = ShareSDK.getPlatform(ShortMessage.NAME);
                break;
            case WechatMoments:
                mCurrentPlatform = ShareSDK.getPlatform(WechatMoments.NAME);
                break;

        }
        mCurrentPlatform.setPlatformActionListener(listener);
        mCurrentPlatform.share(data.params);

    }

    //当前所支持的 平台类型
    public enum PlatformType{
        QQ, Wechat, YouDao, SinaWeibo, ShortMessage, WechatMoments;
    }

}
