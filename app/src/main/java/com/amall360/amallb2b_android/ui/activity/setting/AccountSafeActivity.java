package com.amall360.amallb2b_android.ui.activity.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amall360.amallb2b_android.R;
import com.amall360.amallb2b_android.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountSafeActivity extends BaseActivity {

    @Bind(R.id.back)
    ImageView mBack;
    @Bind(R.id.title)
    TextView  mTitle;
    @Bind(R.id.managementmodel)
    TextView  mManagementmodel;

    @Override
    protected int bindLayout() {
        return R.layout.activity_account_safe;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        mTitle.setText("账户安全");
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

    @OnClick({R.id.back, R.id.login_safe, R.id.pay_safe, R.id.bankcard_safe})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.login_safe:
                startActivity(new Intent(mActivity, LoginSafeActivity.class));
                break;
            case R.id.pay_safe:
                break;
            case R.id.bankcard_safe:
                break;
        }
    }
}
