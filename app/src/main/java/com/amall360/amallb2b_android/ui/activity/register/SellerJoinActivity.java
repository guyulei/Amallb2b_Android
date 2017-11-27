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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SellerJoinActivity extends BaseActivity implements TextWatcher {

    @Bind(R.id.back)
    ImageView mBack;
    @Bind(R.id.title)
    TextView  mTitle;
    @Bind(R.id.butten)
    Button    mButten;
    @Bind(R.id.username)
    TextView  mUsername;
    @Bind(R.id.agreement)
    TextView  mAgreement;
    @Bind(R.id.userNameEdit)
    EditText  mUserNameEdit;
    @Bind(R.id.userNameClean)
    ImageView mUserNameClean;
    @Bind(R.id.seller_address)
    TextView  mSellerAddress;
    @Bind(R.id.seller_class)
    TextView  mSellerClass;

    @Override
    protected int bindLayout() {
        return R.layout.activity_seller_join;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        mTitle.setText("商户注册(1/3)");
        mButten.setText("下一步");
        mUsername.setText("用户名");
        mAgreement.setText(Html.fromHtml("<u>" + "《贝贝猫用户注册协议》" + "</u>"));
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
        mSellerAddress.addTextChangedListener(this);
        mSellerClass.addTextChangedListener(this);
    }

    @Override
    protected void getDataNet() {

    }

    @Override
    protected void doBusiness(Context context) {

    }

    @OnClick({R.id.back, R.id.butten, R.id.userNameClean})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.butten:
                // TODO: 2017/11/27
                startActivity(new Intent(mActivity, SellerJoinTwoActivity.class));
                break;
            case R.id.userNameClean:
                mUserNameEdit.setText(null);
                needFocusable(mUserNameEdit);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
        String sellerAddress = mSellerAddress.getText().toString();
        String sellerClass = mSellerClass.getText().toString();
        if (username.isEmpty()) {
            mUserNameClean.setImageResource(R.mipmap.clean_nomal);
        } else {
            mUserNameClean.setImageResource(R.mipmap.clean_press);
        }
        if (!sellerAddress.isEmpty() && !sellerClass.isEmpty() && !username.isEmpty()) {
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
