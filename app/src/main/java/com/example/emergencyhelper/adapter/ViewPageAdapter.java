package com.example.emergencyhelper.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * author ： yxm521
 * time    ： 2022/4/3
 */
public class ViewPageAdapter extends PagerAdapter {
    private List<View> views;

    public ViewPageAdapter(List<View> views) {
        this.views = views;
    }

    @Override
    public int getCount() {
        return views != null ? views.size() : 0;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
