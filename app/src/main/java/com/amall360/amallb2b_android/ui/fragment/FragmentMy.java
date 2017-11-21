package com.amall360.amallb2b_android.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.amall360.amallb2b_android.base.BaseFragment;

public class FragmentMy extends BaseFragment {
    @LayoutRes int LayoutRes;

    public static FragmentMy newInstance(@LayoutRes int layoutRes) {
        FragmentMy fragment = new FragmentMy();
        Bundle args = new Bundle();
        args.putInt("fragmentmy", layoutRes);
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
            LayoutRes = args.getInt("fragmentmy");
        }
    }
}
