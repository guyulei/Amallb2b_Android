package com.amall360.amallb2b_android.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.amall360.amallb2b_android.base.BaseFragment;

public class FragmentWarmcircle extends BaseFragment {
    @LayoutRes int LayoutRes;

    public static FragmentWarmcircle newInstance(@LayoutRes int layoutRes) {
        FragmentWarmcircle fragment = new FragmentWarmcircle();
        Bundle args = new Bundle();
        args.putInt("fragmentwarmcircle", layoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return LayoutRes;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {

    }

    @Override
    public void doBusiness(Context context) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            LayoutRes = args.getInt("fragmentwarmcircle");
        }
    }
}
