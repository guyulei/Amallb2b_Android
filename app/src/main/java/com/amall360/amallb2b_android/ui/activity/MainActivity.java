package com.amall360.amallb2b_android.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;

import com.amall360.amallb2b_android.R;
import com.amall360.amallb2b_android.adapter.MyFragmentPagerAdapter;
import com.amall360.amallb2b_android.base.BaseActivity;
import com.amall360.amallb2b_android.bean.PageModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    List<PageModel> pageModels = new ArrayList<>();
    @Bind(R.id.radioGroup)
    RadioGroup mRadioGroup;

    {
        pageModels.add(new PageModel(R.layout.pager_home));
        pageModels.add(new PageModel(R.layout.pager_classify));
        pageModels.add(new PageModel(R.layout.pager_warmcircle));
        pageModels.add(new PageModel(R.layout.pager_shoppingcart));
        pageModels.add(new PageModel(R.layout.pager_my));
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        mViewpager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), pageModels));
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

        /*String username = "gu";
        String mobile = "15958121433";
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("username", username);
        map1.put("mobile", mobile);
        String encrypt = AesEncryptionUtil.encrypt(JSONObject.toJSONString(map1));
        LogUtils.e("encrypt:" + encrypt);
        getNetData(mBBMApiStores.setuserCheck(encrypt), new ApiCallback<UserCheckBean>() {
            @Override
            public void onSuccess(UserCheckBean model) {
                Toast.makeText(MainActivity.this, model.getStatus_code(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });*/
    }

    @Override
    protected void doBusiness(Context context) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
