package com.amall360.amallb2b_android.ui.activity.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amall360.amallb2b_android.R;
import com.amall360.amallb2b_android.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @Bind(R.id.back)
    ImageView mBack;
    @Bind(R.id.title)
    TextView  mTitle;
    @Bind(R.id.heardimage)
    ImageView mHeardimage;
    @Bind(R.id.managementmodel)
    TextView  mManagementmodel;
    @Bind(R.id.real_name)
    TextView  mRealName;
    @Bind(R.id.address_manager)
    ImageView mAddressManager;
    @Bind(R.id.safe)
    ImageView mSafe;
    @Bind(R.id.update)
    TextView  mUpdate;
    @Bind(R.id.exit)
    Button    mExit;

    @Override
    protected int bindLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        mTitle.setText("设置");
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
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back, R.id.heardimage, R.id.real_name, R.id.address_manager, R.id.safe, R.id.update, R.id.exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.heardimage:
                // TODO: 2017/11/28 换头像
                break;
            case R.id.real_name:
                break;
            case R.id.address_manager:
                break;
            case R.id.safe:
                startActivity(new Intent(mActivity, AccountSafeActivity.class));
                break;
            case R.id.update:
                break;
            case R.id.exit:
                break;
        }
    }

}