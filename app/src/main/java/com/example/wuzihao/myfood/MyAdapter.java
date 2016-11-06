package com.example.wuzihao.myfood;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by wuzihao on 2016/11/3.
 */

public class MyAdapter extends PagerAdapter {
    private List<View> list;
    private List<String> titlelist;


    public MyAdapter(List<String> titlelist, List<View> list) {
        this.titlelist = titlelist;
        this.list = list;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(list.get(position));
        return list.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(list.get(position));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }
}
