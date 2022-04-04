package com.example.emergencyhelper.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * author ： yxm521
 * time    ： 2022/4/4
 */
public class ExpertFragmentAdapter extends FragmentPagerAdapter {
    private String titles[];
    private Context context;
    private List<Fragment> fragments;

    public ExpertFragmentAdapter(@NonNull FragmentManager fm, String[] titles, Context context, List<Fragment> fragments) {
        super(fm);
        this.titles = titles;
        this.context = context;
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
