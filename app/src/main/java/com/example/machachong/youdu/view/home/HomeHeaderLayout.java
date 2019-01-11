package com.example.machachong.youdu.view.home;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.imoocsdk.imageloader.ImageLoaderManager;
import com.example.machachong.youdu.R;
import com.example.machachong.youdu.adapter.PhotoPagerAdapter;
import com.example.machachong.youdu.module.recommand.RecommandHeadValue;
import com.example.machachong.youdu.view.viewpagerindictor.CirclePageIndicator;




import me.angeldevil.autoscrollviewpager.AutoScrollViewPager;

public class HomeHeaderLayout extends RelativeLayout {
    private Context mContext;
    /**
     * UI
     */
    private RelativeLayout mRootView;
    private AutoScrollViewPager mViewPager;
    private CirclePageIndicator mPagerIndictor;
    private TextView mHotView;
    private PhotoPagerAdapter mAdapter;
    private ImageView[] mImageViews = new ImageView[4];
    private LinearLayout mFootLayout;

    /**
     * Data
     */
    private RecommandHeadValue mHeaderValue;
    private ImageLoaderManager mImageLoader;

    public HomeHeaderLayout(Context context,RecommandHeadValue headValue){
        this(context,null,headValue);
    }
    public HomeHeaderLayout(Context context, AttributeSet attrs, RecommandHeadValue headValue){
        super(context, attrs);
        mContext = context;
        mHeaderValue = headValue;
        mImageLoader = ImageLoaderManager.getInstance(mContext);
        initView();

    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        mRootView = (RelativeLayout) inflater.inflate(R.layout.listview_home_head_layout, this);
        mViewPager = (AutoScrollViewPager) mRootView.findViewById(R.id.pager);
        mPagerIndictor = (CirclePageIndicator) mRootView.findViewById(R.id.pager_indictor_view);
        mHotView = (TextView) mRootView.findViewById(R.id.zuixing_view);
        mImageViews[0] = (ImageView) mRootView.findViewById(R.id.head_image_one);
        mImageViews[1] = (ImageView) mRootView.findViewById(R.id.head_image_two);
        mImageViews[2] = (ImageView) mRootView.findViewById(R.id.head_image_three);
        mImageViews[3] = (ImageView) mRootView.findViewById(R.id.head_image_four);
        mFootLayout = (LinearLayout) mRootView.findViewById(R.id.content_layout);

        mAdapter = new PhotoPagerAdapter(mContext, mHeaderValue.ads, true);
        mViewPager.setAdapter(mAdapter);
        mViewPager.startAutoScroll(3000);
        mPagerIndictor.setViewPager(mViewPager);

        for (int i = 0; i < mImageViews.length; i++) {
            mImageLoader.disPlayImage(mImageViews[i], mHeaderValue.middle.get(i));
        }

//        for (RecommandFooterValue value : mHeaderValue.footer) {
//            mFootLayout.addView(createItem(value));
//        }
        mHotView.setText(mContext.getString(R.string.today_zuixing));
    }

//    private HomeBottomItem createItem(RecommandFooterValue value) {
//        HomeBottomItem item = new HomeBottomItem(mContext, value);
//        return item;
//    }

}
