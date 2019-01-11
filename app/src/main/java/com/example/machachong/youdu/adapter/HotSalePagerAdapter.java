package com.example.machachong.youdu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.imoocsdk.imageloader.ImageLoaderManager;
import com.example.machachong.youdu.R;
import com.example.machachong.youdu.module.recommand.RecommandBodyValue;

import java.util.ArrayList;

public class HotSalePagerAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<RecommandBodyValue> mData;
    private LayoutInflater mInflater;
    private ImageLoaderManager mImageLoader;

    public HotSalePagerAdapter(Context context,ArrayList<RecommandBodyValue> list){
        mContext = context;
        mData = list;
        mInflater = LayoutInflater.from(mContext);
        mImageLoader = ImageLoaderManager.getInstance(mContext);
    }

    @Override
    public int getCount() { return Integer.MAX_VALUE; }

    @Override
    public boolean isViewFromObject(@NonNull View arg0, @NonNull Object arg1) {
        return arg0==arg1;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final RecommandBodyValue value = mData.get(position %mData.size());
        /**
         * 初始化控件
         */
        View rootView = mInflater.inflate(R.layout.item_hot_product_pager_layout, null);
        TextView titleView = (TextView) rootView.findViewById(R.id.title_view);
        TextView infoView = (TextView) rootView.findViewById(R.id.info_view);
        TextView gonggaoView = (TextView) rootView.findViewById(R.id.gonggao_view);
        TextView saleView = (TextView) rootView.findViewById(R.id.sale_num_view);
        ImageView[] imageViews = new ImageView[3];
        imageViews[0] = (ImageView) rootView.findViewById(R.id.image_one);
        imageViews[1] = (ImageView) rootView.findViewById(R.id.image_two);
        imageViews[2] = (ImageView) rootView.findViewById(R.id.image_three);

        /**
         * 绑定数据到view
         */
        titleView.setText(value.title);
        infoView.setText(value.price);
        gonggaoView.setText(value.info);
        saleView.setText(value.text);
        for (int i = 0;i<imageViews.length;i++){
            mImageLoader.disPlayImage(imageViews[i],value.url.get(i));
        }

        container.addView(rootView,0);
        return rootView;
    }
}
