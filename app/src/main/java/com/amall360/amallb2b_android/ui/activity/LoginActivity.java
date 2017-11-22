package com.amall360.amallb2b_android.ui.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amall360.amallb2b_android.R;
import com.amall360.amallb2b_android.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.imageView)
    ImageView mTitleBack;
    @Bind(R.id.textright)
    TextView  mTitleRight;
    @Bind(R.id.textcenter)
    TextView  mTitleCenter;

    @Override
    protected int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        //设置定位图标
        mTitleCenter.setText("贝贝猫登录");
        Drawable drawable = getResources().getDrawable(R.mipmap.address);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mTitleRight.setCompoundDrawables(drawable, null, null, null);//设置TextView的drawableleft
        mTitleRight.setCompoundDrawablePadding(10);//设置图片和text之间的间距
        mTitleRight.setText("杭州");
    }

    @Override
    protected void getDataNet() {

    }

    @Override
    protected void doBusiness(Context context) {

    }

    @OnClick({R.id.imageView, R.id.textright})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView:
                finish();
                break;
            case R.id.textright:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
