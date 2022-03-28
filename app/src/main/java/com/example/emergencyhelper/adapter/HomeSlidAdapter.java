package com.example.emergencyhelper.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class HomeSlidAdapter extends FragmentPagerAdapter {
    private String mTitles[];

    private ArrayList<Fragment>fragments=new ArrayList<>();
    public  HomeSlidAdapter(FragmentManager fm,String mTitles[], ArrayList<Fragment> fragments)
    {
        super(fm);
        this.fragments=fragments;
        this.mTitles=mTitles;
    }
    public int getCount()
    {
        return fragments.size();
    }
    public CharSequence getPageTitle(int position)
    {
        return mTitles[position];
    }
    public Fragment getItem(int position)
    {
        return fragments.get(position);
    }
}