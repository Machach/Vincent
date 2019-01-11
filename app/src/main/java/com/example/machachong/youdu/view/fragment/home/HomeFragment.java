package com.example.machachong.youdu.view.fragment.home;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.imoocsdk.okhttp.listener.DisposeDataListener;
import com.example.machachong.youdu.adapter.CourseAdapter;
import com.example.machachong.youdu.view.fragment.BaseFragment;
import com.example.machachong.youdu.R;
import com.example.machachong.youdu.module.recommand.BaseRecommandModule;
import com.example.machachong.youdu.network.http.RequestCenter;
import com.example.machachong.youdu.view.home.HomeHeaderLayout;
//import com.example.machachong.youdu.view.home.HomeHeaderLayout;

import static android.content.ContentValues.TAG;

public class HomeFragment extends BaseFragment implements View.OnClickListener,AdapterView.OnItemClickListener{
    /**
     * UI
     * @param savedInstanceState
     */

    private View mContentView;
    private ListView mListView;
    private TextView mCategoryView;
    private TextView mSearchView;
    private ImageView mLoadingView;


    /**
     * data
     * @param savedInstanceState
     */
    private CourseAdapter mAdapter;
    private BaseRecommandModule mRecommandData;

    public HomeFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RequestRecommandData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        mContext = getActivity();
        mContentView = inflater.inflate(R.layout.fragment_home_layout,container,false);
        initView();
        return mContentView;
    }
    private void initView(){
        mCategoryView = mContentView.findViewById(R.id.category_view);
        mCategoryView.setOnClickListener(this);
        mSearchView = mContentView.findViewById(R.id.search_view);
        mSearchView.setOnClickListener(this);
        mListView = mContentView.findViewById(R.id.list_view);
//        mListView.setOnClickListener(this);
        mLoadingView = mContentView.findViewById(R.id.loading_view);
        //启动动画
        AnimationDrawable anim = (AnimationDrawable) mLoadingView.getDrawable();
        anim.start();
    }

    /**
     * 发送首页列表数据请求
     */
    private void RequestRecommandData(){
        RequestCenter.requestRecommandData(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Log.e(TAG,"onSuccess:aaa"+responseObj.toString());

                /**
                 * 獲取數據后更新ui
                 */

                mRecommandData = (BaseRecommandModule) responseObj;



                showSuccessView();
            }

            @Override
            public void onFailure(Object responseObj) {
                Log.e(TAG,"onFailure:aa"+responseObj.toString());

                showErrorView();

            }
        });


    }

    private void showSuccessView(){
        if (mRecommandData.data.list != null && mRecommandData.data.list.size() != 0){

            mLoadingView.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
            //为ListView添加列表头
            mListView.addHeaderView(new HomeHeaderLayout(mContext,mRecommandData.data.head));

            //创建adapter
            mAdapter = new CourseAdapter(mContext,mRecommandData.data.list);
            mListView.setAdapter(mAdapter);


        }else {
            showErrorView();
        }
    }

    private void showErrorView(){}

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
