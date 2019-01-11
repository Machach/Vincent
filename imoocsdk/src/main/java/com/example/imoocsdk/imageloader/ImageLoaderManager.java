package com.example.imoocsdk.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.content.Context;

import com.example.imoocsdk.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * 初始化UniversalImageLoader，并用来加载网络图片
 */
public class ImageLoaderManager {

    /**
     * 默认参数值
     */
    private static final int THREAD_COUNT = 4;
    private static final int PROPRITY = 2;
    private static final int DISK_CACHE_SIZE = 50*1024*1024;
    private static final int CONECTION_TIMEOUT = 5*1000;
    private static final int READ_TIME_OUT = 30*1000;

    private static ImageLoader mImageLoader = null;
    private static ImageLoaderManager mInstance = null;

    public static ImageLoaderManager getInstance(Context context){
        if(mInstance == null){
            synchronized (ImageLoaderManager.class){
                if (mInstance == null){
                    mInstance = new ImageLoaderManager(context);
                }
            }
        }
        return mInstance;
    }

    private ImageLoaderManager(Context context){
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration
                .Builder(context)
                .threadPoolSize(THREAD_COUNT)
                .threadPriority(Thread.NORM_PRIORITY-PROPRITY)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new WeakMemoryCache())
                .diskCacheSize(DISK_CACHE_SIZE)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .defaultDisplayImageOptions(getDefaultOptions())
                .imageDownloader(new BaseImageDownloader(context,CONECTION_TIMEOUT,READ_TIME_OUT))
                .writeDebugLogs()
                .build();

        ImageLoader.getInstance().init(configuration);
        mImageLoader = ImageLoader.getInstance();

    }

    /**
     * 实现默认的option
     * @return
     */
    private DisplayImageOptions getDefaultOptions(){

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.xadsdk_img_error)//图片地址为空时返回图
                .cacheInMemory(true)//设置图片可以缓存在内存
                .cacheOnDisk(true)//缓存在磁盘
                .bitmapConfig(Bitmap.Config.RGB_565)//使用的图片解码类型
                .decodingOptions(new BitmapFactory.Options())//图片解码配置
                .build();
        return options;
    }


    /**
     * 图片加载API
     * @param imageView
     * @param url
     * @param options
     * @param listener
     */
    public void disPlayImage(ImageView imageView, String url,
                             DisplayImageOptions options,
                             ImageLoadingListener listener){
        if (mImageLoader != null){
            mImageLoader.displayImage(url, imageView, options,listener);
        }
    }

    public void disPlayImage(ImageView imageView, String url,
                             ImageLoadingListener listener){
        disPlayImage(imageView,url,null,listener);
    }

    public void disPlayImage(ImageView imageView, String url){
        disPlayImage(imageView,url,null);
    }

}
