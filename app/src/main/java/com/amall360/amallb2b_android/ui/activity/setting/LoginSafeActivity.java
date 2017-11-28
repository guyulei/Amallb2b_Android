package com.amall360.amallb2b_android.ui.activity.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.amall360.amallb2b_android.R;
import com.amall360.amallb2b_android.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginSafeActivity extends BaseActivity {

    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.loginTelephone)
    TextView mLoginTelephone;

    @Override
    protected int bindLayout() {
        return R.layout.activity_login_safe;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        mTitle.setText("登录安全");
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

    @OnClick({R.id.back, R.id.loginTelephone, R.id.loginPassword})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.loginTelephone:
                startActivity(new Intent(mActivity, UpdateLoginTeleActivity.class));
                break;
            case R.id.loginPassword:
                startActivity(new Intent(mActivity, UpdateLoginPassActivity.class));
                break;
        }
    }
}
