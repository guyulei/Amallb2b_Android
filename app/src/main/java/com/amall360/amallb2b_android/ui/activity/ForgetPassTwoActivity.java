package com.amall360.amallb2b_android.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amall360.amallb2b_android.R;
import com.amall360.amallb2b_android.base.BaseActivity;

public class ForgetPassTwoActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected int bindLayout() {
        return R.layout.activity_forget_pass_two;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        ImageView back = findViewById(R.id.title).findViewById(R.id.imageLeft);
        TextView title = findViewById(R.id.title).findViewById(R.id.textCenter);
        back.setOnClickListener(this);
        title.setText("忘记密码(2/2)");
        //

    }

    @Override
    protected void getDataNet() {

    }

    @Override
    protected void doBusiness(Context context) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageLeft:
                finish();
                break;
        }
    }
}
