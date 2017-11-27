package com.amall360.amallb2b_android.ui.activity.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amall360.amallb2b_android.R;
import com.amall360.amallb2b_android.base.BaseActivity;
import com.amall360.amallb2b_android.utils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MemberJoinActivity extends BaseActivity implements TextWatcher {

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
    @Bind(R.id.setpassword)
    EditText  mSetpassword;
    @Bind(R.id.setpasswordagain)
    EditText  mSetpasswordagain;
    @Bind(R.id.butten)
    Button    mButten;
    @Bind(R.id.agreement)
    TextView  mAgreement;

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
        mButten.setText("下一步");
        mAgreement.setText(Html.fromHtml("<u>" + "《贝贝猫用户注册协议》" + "</u>"));
        //
        //用户名
        mUserNameEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    mUserNameEdit.setSelection(mUserNameEdit.getText().length());
                    setusernameclean();
                }
            }
        });
        //
        mUserNameEdit.addTextChangedListener(this);
        mSetpassword.addTextChangedListener(this);
        mSetpasswordagain.addTextChangedListener(this);
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


    @OnClick({R.id.back, R.id.butten,R.id.userNameClean})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.butten:
                // TODO: 2017/11/27
                String password = mSetpassword.getText().toString();
                String passwordagain = mSetpasswordagain.getText().toString();
                if (!password.equals(passwordagain)) {
                    ToastUtil.showToast("您输入的两次密码不一致！");
                    return;
                } else {
                    startActivity(new Intent(mActivity, MemberJoinTwoActivity.class));
                }
                break;
            case R.id.userNameClean:
                mUserNameEdit.setText(null);
                needFocusable(mUserNameEdit);
                break;

        }
    }

    private void setusernameclean() {
        String s = mUserNameEdit.getText().toString();
        if (s.isEmpty()) {
            mUserNameClean.setImageResource(R.mipmap.clean_nomal);
        } else {
            mUserNameClean.setImageResource(R.mipmap.clean_press);
        }
    }

    private void needFocusable(EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        editText.setSelection(editText.getText().toString().length());
    }

    private void setloginbutten(Button button) {
        String username = mUserNameEdit.getText().toString();
        String password = mSetpassword.getText().toString();
        String passwordagain = mSetpasswordagain.getText().toString();
        if (username.isEmpty()) {
            mUserNameClean.setImageResource(R.mipmap.clean_nomal);
        } else {
            mUserNameClean.setImageResource(R.mipmap.clean_press);
        }
        if (!password.isEmpty() && !passwordagain.isEmpty() && !username.isEmpty()) {
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
