package com.example.machachong.youdu.activity;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.machachong.youdu.R;
import com.example.machachong.youdu.activity.base.BaseActivity;
import com.example.machachong.youdu.view.fragment.home.HomeFragment;
import com.example.machachong.youdu.view.fragment.home.MessageFragment;
import com.example.machachong.youdu.view.fragment.home.MineFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity  {


    //    private FragmentManager fm;
//    private HomeFragment mHomeFragment;
//    private MessageFragment mMessageFragment;
//    private MineFragment mMineFragment;
//
//    private RelativeLayout mHomeLayout;
//    private RelativeLayout mMessageLayout;
//    private RelativeLayout mMinerLayout;
//
//    private TextView mHomeView;
//    private TextView mMessageView;
//    private TextView mMinerView;
    //https://segmentfault.com/a/1190000013153365

    public MyAdapter mAdapter;
    private TabLayout tabLayout;
    public ViewPager viewPager;
    private List<Fragment> list;
    private String[] titles = {"主页","消息","我的"};
    private int images[] ={R.drawable.comui_tab_home_selected,
            R.drawable.comui_tab_message_selected,
            R.drawable.comui_tab_person_selected};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new MessageFragment());
        list.add(new MineFragment());

        mAdapter = new MyAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(mAdapter);//使用适配器将viewPager和fragment绑定

        tabLayout.setupWithViewPager(viewPager);//将tabLayout和viewPager绑定

        for (int i = 0; i<tabLayout.getTabCount();i++){
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(mAdapter.getTabView(i));
        }

    }

    class MyAdapter extends FragmentPagerAdapter{
        private Context context;
        private MyAdapter(FragmentManager fm,Context context){
            super(fm);
            this.context = context;

        }


        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        private View getTabView(int position){
            View v = LayoutInflater.from(context).inflate(R.layout.tab_custom,null);
            TextView textView = v.findViewById(R.id.tv_title);
            ImageView imageView = v.findViewById(R.id.iv_icon);
            textView.setText(titles[position]);
            imageView.setImageResource(images[position]);

            textView.setTextColor(tabLayout.getTabTextColors());

            return v;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home_layout);
//
//        initView();
//
//        mHomeFragment = new HomeFragment();
//        fm = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fm.beginTransaction();
//        fragmentTransaction.replace(R.id.content_layout,mHomeFragment);
//        fragmentTransaction.commit();
//
//
//    }
//
//    /**
//     *
//     */
//    private void initView(){
//        mHomeLayout = findViewById(R.id.home_layout_view);
//        mHomeLayout.setOnClickListener(this);
//        mMessageLayout = findViewById(R.id.message_layout_view);
//        mMessageLayout.setOnClickListener(this);
//        mMinerLayout = findViewById(R.id.mine_layout_view);
//        mMinerLayout.setOnClickListener(this);
//
//        mHomeView = findViewById(R.id.home_image_view);
//        mMessageView = findViewById(R.id.message_image_view);
//        mMinerView = findViewById(R.id.mine_image_view);
//
//    }
//
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//    }
//
//
//
//    private void hideFragment(Fragment fragment,FragmentTransaction fragmentTransaction){
//        if(fragment != null){
//            fragmentTransaction.hide(fragment);
//        }
//    }
//
//
//    @Override
//    public void onClick(View v) {
//        FragmentTransaction fragmentTransaction = fm.beginTransaction();
//        switch (v.getId()){
//            case R.id.home_layout_view:
//
//                //ui
//
//                //
//                hideFragment(mMessageFragment,fragmentTransaction);
//                hideFragment(mMineFragment,fragmentTransaction);
//                if(mHomeFragment == null){
//                    mHomeFragment = new HomeFragment();
//                    fragmentTransaction.add(R.id.content_layout,mHomeFragment);
//                }else {
//                    fragmentTransaction.show(mHomeFragment);
//                }
//                break;
//            case R.id.message_layout_view:
//
//                hideFragment(mHomeFragment,fragmentTransaction);
//                hideFragment(mMineFragment,fragmentTransaction);
//                if(mMessageFragment == null){
//                    mMessageFragment= new MessageFragment();
//                    fragmentTransaction.add(R.id.content_layout,mMessageFragment);
//                }else {
//                    fragmentTransaction.show(mMessageFragment);
//                }
//                break;
//            case R.id.mine_layout_view:
//                hideFragment(mHomeFragment,fragmentTransaction);
//                hideFragment(mMessageFragment,fragmentTransaction);
//                if(mMineFragment == null){
//                    mMineFragment= new MineFragment();
//                    fragmentTransaction.add(R.id.content_layout,mMineFragment);
//                }else {
//                    fragmentTransaction.show(mMineFragment);
//                }
//                break;
//
//
//        }
//        fragmentTransaction.commit();
//
//    }
//
//



}
