package com.example.machachong.youdu.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.imoocsdk.okhttp.listener.DisposeDataListener;
import com.example.machachong.youdu.R;
import com.example.machachong.youdu.activity.base.BaseActivity;
import com.example.machachong.youdu.adapter.CourseCommentAdapter;
import com.example.machachong.youdu.module.course.BaseCourseModel;
import com.example.machachong.youdu.module.course.CourseCommentValue;
import com.example.machachong.youdu.module.course.CourseHeaderValue;
import com.example.machachong.youdu.network.http.RequestCenter;
import com.example.machachong.youdu.view.course.CourseDetailHeaderView;

public class CourseDetailActivity extends BaseActivity implements View.OnClickListener {

    private static final String COURSE_ID = "courseID";
    /**
     * UI
     */
    private ImageView mBackView;
    private ListView mListView;
    private ImageView mLoadingView;

    /**
     * DATA
     */

    private String mCourseID;
    private BaseCourseModel mData;
    private CourseCommentAdapter mAdapter;
    private CourseDetailHeaderView headerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail_layout);
        initData();
        initView();
        requestDetail();
    }

    private void initData(){
        mCourseID = getIntent().getStringExtra(COURSE_ID);
    }

    private void initView(){
        mBackView = (ImageView) findViewById(R.id.back_view);
        mBackView.setOnClickListener(this);
        mListView = (ListView) findViewById(R.id.comment_list_view);
//        mListView.setOnItemClickListener(this);
        mListView.setVisibility(View.GONE);
        mLoadingView = (ImageView) findViewById(R.id.loading_view);
        mLoadingView.setVisibility(View.VISIBLE);
        AnimationDrawable anim = (AnimationDrawable) mLoadingView.getDrawable();
        anim.start();

    }

    private void requestDetail(){
        RequestCenter.requestCourseDetail(mCourseID, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                mData = (BaseCourseModel) responseObj;
                updateUI();
            }

            @Override
            public void onFailure(Object responseObj) {

            }
        });
    }

    private void updateUI(){
        mLoadingView.setVisibility(View.GONE);
        mListView.setVisibility(View.VISIBLE);

        mAdapter = new CourseCommentAdapter(this, mData.data.body);
        mListView.setAdapter(mAdapter);

        if(headerView!= null){
            mListView.removeHeaderView(headerView);
        }
        headerView = new CourseDetailHeaderView(this, mData.data.head);
        mListView.addHeaderView(headerView);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_view:
                finish();
                break;
        }

    }
}
