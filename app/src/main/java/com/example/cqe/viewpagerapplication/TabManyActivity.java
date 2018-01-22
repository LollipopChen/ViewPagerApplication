package com.example.cqe.viewpagerapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by zq on 2017/1/12.
 */

public class TabManyActivity extends AppCompatActivity {
    private TabLayout tabLayout = null;
    private ViewPager vp_pager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tab_layout);
        tabLayout = findViewById(R.id.tablayout);
        vp_pager = findViewById(R.id.tab_viewpager);
        initView();
    }
    private void initView() {
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        vp_pager.setAdapter(new MorePagerAdapter());
        tabLayout.setupWithViewPager(vp_pager);
    }

    final class MorePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            LinearLayout linearLayout = new LinearLayout(TabManyActivity.this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            //设置LinearLayout属性(宽和高)
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            //设置边距
            layoutParams.setMargins(10, 0, 10, 0);
            //将以上的属性赋给LinearLayout
            linearLayout.setLayoutParams(layoutParams);

            TextView tv = new TextView(TabManyActivity.this);
            //设置宽高以及权重
            LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //设置textview垂直居中
            tvParams.gravity = Gravity.CENTER_VERTICAL;
            tv.setLayoutParams(tvParams);
            tv.setTextSize(20.0f);
            tv.setTextColor(getResources().getColor(R.color.blue_light));
            tv.setText("布局"+ position);

            //列表
            View view = LayoutInflater.from(TabManyActivity.this).inflate(R.layout.layout_recycler_view,linearLayout,false);
            RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

            recyclerView.setLayoutManager(new LinearLayoutManager(TabManyActivity.this));
            RecyclerAdapter adapter = new RecyclerAdapter();
            recyclerView.setAdapter(adapter);
            
            linearLayout.addView(tv);
            linearLayout.addView(view);
            (container).addView(linearLayout);
            return linearLayout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            (container).removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Tab" + position;
        }
    }
}