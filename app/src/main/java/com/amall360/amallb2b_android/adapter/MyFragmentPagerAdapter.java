package com.amall360.amallb2b_android.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.amall360.amallb2b_android.bean.PageModel;
import com.amall360.amallb2b_android.ui.fragment.PageFragment;

import java.util.List;

/**
 * Created by A on 2017/11/20.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    public List<PageModel> mPageModels;

    public MyFragmentPagerAdapter(FragmentManager fm, List<PageModel> pageModels) {
        super(fm);
        this.mPageModels = pageModels;
    }

    @Override
    public Fragment getItem(int position) {
        PageModel pageModel = mPageModels.get(position);
        return PageFragment.newInstance(pageModel.LayoutRes);
    }

    @Override
    public int getCount() {
        return mPageModels.size();
    }
}
