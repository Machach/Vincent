package com.example.machachong.youdu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.imoocsdk.adutil.Utils;
import com.example.machachong.youdu.R;
import com.example.machachong.youdu.activity.base.BaseActivity;
import com.example.machachong.youdu.adapter.PhotoPagerAdapter;

import java.util.ArrayList;

public class PhotoViewActivity extends BaseActivity implements View.OnClickListener {

    public ViewPager mViewPager;
    public TextView mIndictorView;
    public ImageView mShareView;
    public PhotoPagerAdapter mAdapter;

    /**
     * Data
     */
    private ArrayList<String> mPhotoLists;
    public int mLength;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view_layout);
        initData();
        initView();
    }

    public static final String PHOTO_LIST = "photo_list";
    private void initData(){
        mPhotoLists = getIntent().getStringArrayListExtra(PHOTO_LIST);
        mLength = mPhotoLists.size();
    }

    private void initView(){
        mIndictorView = (TextView)findViewById(R.id.indictor_view);
        mShareView = (ImageView)findViewById(R.id.share_view);
        mShareView.setOnClickListener(this);
        mViewPager = (ViewPager)findViewById(R.id.photo_pager);
        mViewPager.setPageMargin(Utils.dip2px(this, 30));

        mAdapter = new PhotoPagerAdapter(this, mPhotoLists, false);
        mViewPager.setAdapter(mAdapter);

    }

    @Override
    public void onClick(View v) {

    }
}
