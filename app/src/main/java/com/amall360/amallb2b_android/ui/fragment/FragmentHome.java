package com.amall360.amallb2b_android.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amall360.amallb2b_android.R;
import com.amall360.amallb2b_android.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.amall360.amallb2b_android.R.id.refreshLayout;

public class FragmentHome extends BaseFragment {
    @LayoutRes
    int                LayoutRes;
    @Bind(refreshLayout)
    SmartRefreshLayout mRefreshLayout;


    public static FragmentHome newInstance(@LayoutRes int layoutRes) {
        FragmentHome fragment = new FragmentHome();
        Bundle args = new Bundle();
        args.putInt("fragmenthome", layoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.pager_home;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        //设置 Header 为 Material样式
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000);
            }
        });
    }

    @Override
    public void doBusiness(final Context context) {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            LayoutRes = args.getInt("fragmenthome");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
