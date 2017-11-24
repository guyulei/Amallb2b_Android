package com.amall360.amallb2b_android.ui.activity.register;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amall360.amallb2b_android.R;
import com.amall360.amallb2b_android.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MemberJoinActivity extends BaseActivity {

    @Bind(R.id.back)
    ImageView mBack;
    @Bind(R.id.title)
    TextView  mTitle;
    @Bind(R.id.username)
    TextView  mUsername;
    @Bind(R.id.userNameEdit)
    EditText  mUserNameEdit;
    @Bind(R.id.userNameClean)
    ImageView mUserNameClean;

    @Override
    protected int bindLayout() {
        return R.layout.activity_member_join;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        mTitle.setText("会员注册(1/3)");
        mUsername.setText("用户名");
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


    @OnClick({R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

        }
    }
}
