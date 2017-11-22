package com.amall360.amallb2b_android.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RadioGroup;

import com.amall360.amallb2b_android.R;
import com.amall360.amallb2b_android.adapter.MyFragmentPagerAdapter;
import com.amall360.amallb2b_android.base.BaseActivity;
import com.amall360.amallb2b_android.net.ConnManager;
import com.amall360.amallb2b_android.ui.fragment.FragmentClassify;
import com.amall360.amallb2b_android.ui.fragment.FragmentHome;
import com.amall360.amallb2b_android.ui.fragment.FragmentMy;
import com.amall360.amallb2b_android.ui.fragment.FragmentShoppingcart;
import com.amall360.amallb2b_android.ui.fragment.FragmentWarmcircle;
import com.amall360.amallb2b_android.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.viewpager)
    NoScrollViewPager mViewpager;
    @Bind(R.id.radioGroup)
    RadioGroup        mRadioGroup;
    List<Fragment> mFragments = new ArrayList<>();


    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        //
        ConnManager connManager = new ConnManager(this);
        connManager.connect();

        mFragments.clear();
        mFragments.add(FragmentHome.newInstance(R.layout.pager_home));
        mFragments.add(FragmentClassify.newInstance(R.layout.pager_classify));
        mFragments.add(FragmentWarmcircle.newInstance(R.layout.pager_warmcircle));
        mFragments.add(FragmentShoppingcart.newInstance(R.layout.pager_shoppingcart));
        mFragments.add(FragmentMy.newInstance(R.layout.pager_my));
        mViewpager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments));
        mViewpager.setCurrentItem(0);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.radio_home:
                        mViewpager.setCurrentItem(0, false);
                        break;
                    case R.id.radio_classify:
                        mViewpager.setCurrentItem(1, false);
                        break;
                    case R.id.radio_warmcircle:
                        mViewpager.setCurrentItem(2, false);
                        break;
                    case R.id.radio_shoppingcart:
                        mViewpager.setCurrentItem(3, false);
                        break;
                    case R.id.radio_my:
                        mViewpager.setCurrentItem(4, false);
                        break;
                }
            }
        });
    }

    @Override
    protected void getDataNet() {
    }

    @Override
    protected void doBusiness(Context context) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {
        //按返回键返回桌面 手机返回桌面后，点击app图标，app重启
        moveTaskToBack(true);
    }
}
