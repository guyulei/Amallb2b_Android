package com.amall360.amallb2b_android.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.amall360.amallb2b_android.R;
import com.amall360.amallb2b_android.base.BaseActivity;
import com.amall360.amallb2b_android.bean.LoginBean;
import com.amall360.amallb2b_android.net.ApiCallback;
import com.amall360.amallb2b_android.ui.activity.citymanager.CityManagerActivity;
import com.amall360.amallb2b_android.ui.activity.forgetpass.ForgetPassOneActivity;
import com.amall360.amallb2b_android.ui.activity.register.MemberJoinActivity;
import com.amall360.amallb2b_android.ui.activity.register.SellerJoinActivity;
import com.amall360.amallb2b_android.utils.AesEncryptionUtil;
import com.amall360.amallb2b_android.utils.LogUtils;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements TextWatcher {

    @Bind(R.id.back)
    ImageView mBack;
    @Bind(R.id.title)
    TextView  mTitle;
    @Bind(R.id.option)
    TextView  mLocation;//定位
    @Bind(R.id.forgetpass)
    TextView  mForgetpass;
    @Bind(R.id.memberjoin)
    TextView  mMemberjoin;
    @Bind(R.id.sellerjoin)
    TextView  mSellerjoin;
    @Bind(R.id.userNameEdit)
    EditText  mUserNameEdit;
    @Bind(R.id.userNameClean)
    ImageView mUserNameClean;
    @Bind(R.id.passwordEdit)
    EditText  mPasswordEdit;
    @Bind(R.id.passwordClean)
    ImageView mPasswordClean;
    @Bind(R.id.butten)
    Button    mButten;

    private Boolean showPassword = true;//密码是否明文显示

    @Override
    protected int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {

        initview();//各种初始化

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

    @OnClick({R.id.back, R.id.forgetpass, R.id.memberjoin, R.id.sellerjoin, R.id.option})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.forgetpass:
                startActivity(new Intent(mActivity, ForgetPassOneActivity.class));
                break;
            case R.id.memberjoin:
                startActivity(new Intent(mActivity, MemberJoinActivity.class));
                break;
            case R.id.sellerjoin:
                startActivity(new Intent(mActivity, SellerJoinActivity.class));
                break;
            case R.id.option:
                startActivity(new Intent(mActivity, CityManagerActivity.class));
                break;
        }
    }

    private void initview() {
        //标题
        mBack.setImageResource(R.mipmap.close);
        mTitle.setText("贝贝猫登录");
        //
        Drawable drawable = getResources().getDrawable(R.mipmap.address);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mLocation.setCompoundDrawables(drawable, null, null, null);//设置TextView的drawableleft
        mLocation.setCompoundDrawablePadding(10);//设置图片和text之间的间距
        mLocation.setText("城市分站");
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
        mUserNameEdit.addTextChangedListener(this);
        mUserNameClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserNameEdit.setText(null);
                needFocusable(mUserNameEdit);
            }
        });
        //密码
        mPasswordClean.setImageResource(R.mipmap.password_nomal);
        mPasswordEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    mPasswordEdit.setSelection(mPasswordEdit.getText().length());
                }
            }
        });
        mPasswordClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPasswordIsClean();
            }
        });
        mPasswordEdit.addTextChangedListener(this);
        //登录按钮
        setloginbutten(mUserNameEdit, mPasswordEdit, mButten);

    }

    private void setPasswordIsClean() {
        if (showPassword) {// 显示密码
            mPasswordClean.setImageResource(R.mipmap.password_press);
            mPasswordEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            showPassword = !showPassword;
        } else {// 隐藏密码
            mPasswordClean.setImageResource(R.mipmap.password_nomal);
            mPasswordEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
            showPassword = !showPassword;
        }
        needFocusable(mPasswordEdit);
    }

    private void setloginbutten(EditText editText, EditText editText1, Button button) {
        String s = editText.getText().toString();
        String s1 = editText1.getText().toString();
        if (!s.isEmpty() && !s1.isEmpty()) {
            button.setEnabled(true);
            button.setTextColor(getResources().getColor(R.color.colorffffff));
        } else {
            button.setEnabled(false);
            button.setTextColor(getResources().getColor(R.color.colorbbbbc5));
        }
    }

    private void needFocusable(EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        editText.setSelection(editText.getText().toString().length());
    }

    private void setusernameclean() {
        String s = mUserNameEdit.getText().toString();
        if (s.isEmpty()) {
            mUserNameClean.setImageResource(R.mipmap.clean_nomal);
        } else {
            mUserNameClean.setImageResource(R.mipmap.clean_press);
        }
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        setusernameclean();
        setloginbutten(mUserNameEdit, mPasswordEdit, mButten);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @OnClick(R.id.butten)
    public void onViewClicked() {
               /**username : 用户名
                *password：密码
                *domain_id ：登录站点id*/
        showDialog(null);
        /*String username = "gu";
        String mobile = "15958121433";*/
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("username", mUserNameEdit.getText().toString());
        map1.put("password", mPasswordEdit.getText().toString());
        map1.put("domain_id", "1");
        String key = AesEncryptionUtil.encrypt(JSONObject.toJSONString(map1));
        LogUtils.e("encrypt:" + key);
        getNetData(mBBMApiStores.getLogin(key), new ApiCallback<LoginBean>() {

            @Override
            public void onSuccess(LoginBean model) {
                disDialog();
                String message = model.getMessage();
                showtoast(message);

            }

            @Override
            public void onFailure(String msg) {
                disDialog();
                showtoast(msg);

            }
        });
    }
}
