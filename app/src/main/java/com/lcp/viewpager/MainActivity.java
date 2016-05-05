package com.lcp.viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ImageView> imageViews;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        initView();
        initData();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setPageMargin(50);
        mViewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                float v = Math.abs(position);
                float v1 = (float) (0.2 * (v * v));
                page.setScaleY(1 - v1);
            }
        });
    }

    protected void initData() {
        imageViews = new ArrayList<>();
        int[] ids = {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.a, R.mipmap.b};
        for (int i = 0; i < 5; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(ids[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setTag(i);
            imageViews.add(imageView);
        }
        mViewPager.setAdapter(new MyViewPagerAdapter(imageViews));
        mViewPager.setCurrentItem(1);
    }

    class MyViewPagerAdapter extends PagerAdapter {
        private List<ImageView> mList;

        MyViewPagerAdapter(List<ImageView> mList) {
            this.mList = mList;
        }

        @Override
        public int getCount() {
            if (mList == null) {
                return 0;
            }
            return mList.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mList.get(position));
            return mList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position));
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }
    }

    public void toMainAc1(View v){
        startActivity(new Intent(this,MainActivity1.class));
    }
}
