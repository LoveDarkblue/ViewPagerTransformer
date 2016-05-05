package com.lcp.viewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity1 extends Activity {

    private ArrayList<ImageView> imageViews;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main1);
        initView();
        initData();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager1);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                if (position > 0 && position <= 1) {
                    page.setPivotX(0);
                    page.setScaleX(1 - position);
                } else if (position <= 0 && position >= -1) {
                    page.setPivotX(page.getWidth());
                    page.setScaleX(1 + position);
                }
            }
        });
    }

    protected void initData() {
        imageViews = new ArrayList<>();
        int[] ids = {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.a, R.mipmap.b, R.mipmap.c};
        for (int i = 0; i < 6; i++) {
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
}
