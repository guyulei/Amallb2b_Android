package com.amall360.amallb2b_android.ui.activity.register;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amall360.amallb2b_android.R;
import com.amall360.amallb2b_android.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MemberJoinTwoActivity extends BaseActivity implements TextWatcher {

    @Bind(R.id.back)
    ImageView mBack;
    @Bind(R.id.title)
    TextView  mTitle;
    @Bind(R.id.butten)
    Button    mButten;
    @Bind(R.id.member_type)
    TextView  mMemberType;
    @Bind(R.id.management_model)
    TextView  mManagementModel;
    @Bind(R.id.address)
    TextView  mAddress;

    @Override
    protected int bindLayout() {
        return R.layout.activity_member_join_two;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        mTitle.setText("会员注册(2/3)");
        mButten.setText("下一步");
        mMemberType.addTextChangedListener(this);
        mManagementModel.addTextChangedListener(this);
        mAddress.addTextChangedListener(this);
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

    @OnClick({R.id.back, R.id.butten, R.id.member_type, R.id.management_model, R.id.address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

            case R.id.member_type:
                // TODO: 2017/11/27
                break;
            case R.id.management_model:
                // TODO: 2017/11/27
                break;
            case R.id.address:
                // TODO: 2017/11/27
                break;

            case R.id.butten:
                break;
        }
    }

    private void setloginbutten(Button button) {
        String memberType = mMemberType.getText().toString();
        String managementModel = mManagementModel.getText().toString();
        String address = mAddress.getText().toString();
        if (!memberType.isEmpty() && !managementModel.isEmpty() && !address.isEmpty()) {
            button.setEnabled(true);
            button.setTextColor(getResources().getColor(R.color.colorffffff));
        } else {
            button.setEnabled(false);
            button.setTextColor(getResources().getColor(R.color.colorbbbbc5));
        }
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        setloginbutten(mButten);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
