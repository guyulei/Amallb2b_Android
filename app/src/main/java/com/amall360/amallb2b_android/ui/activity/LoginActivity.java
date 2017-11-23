package com.amall360.amallb2b_android.ui.activity;

import android.content.Context;
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

import com.amall360.amallb2b_android.R;
import com.amall360.amallb2b_android.base.BaseActivity;

import butterknife.ButterKnife;

import static com.amall360.amallb2b_android.R.id.editText;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mBack;
    private TextView  mLocation;//定位
    private EditText  mUsername;
    private ImageView mUsernameclean;
    private EditText  mPassword;
    private ImageView mPasswordshow;
    private Boolean showPassword = true;//密码是否明文显示
    private Button mLoginbutten;

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageLeft:
                finish();
                break;
        }
    }

    private void initview() {
        //标题
        mBack = findViewById(R.id.title).findViewById(R.id.imageLeft);
        mBack.setOnClickListener(this);
        TextView title = findViewById(R.id.title).findViewById(R.id.textCenter);
        title.setText("贝贝猫登录");
        mLocation = findViewById(R.id.title).findViewById(R.id.textRight);
        Drawable drawable = getResources().getDrawable(R.mipmap.address);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mLocation.setCompoundDrawables(drawable, null, null, null);//设置TextView的drawableleft
        mLocation.setCompoundDrawablePadding(10);//设置图片和text之间的间距
        mLocation.setText("杭州");
        //用户名
        TextView user = findViewById(R.id.username).findViewById(R.id.textLeft);
        user.setText("账号");
        mUsername = findViewById(R.id.username).findViewById(editText);
        mUsername.setHint("用户名/邮箱/手机号");
        mUsernameclean = findViewById(R.id.username).findViewById(R.id.imageRight);
        mUsernameclean.setImageResource(R.mipmap.clean_nomal);
        mUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    mUsername.setSelection(mUsername.getText().length());
                    setusernameclean();
                }
            }
        });
        mUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setusernameclean();
                setloginbutten(mUsername, mPassword);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mUsernameclean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUsername.setText(null);
                needFocusable(mUsername);
            }
        });
        //密码
        TextView pass = findViewById(R.id.password).findViewById(R.id.textLeft);
        mPassword = findViewById(R.id.password).findViewById(editText);
        mPasswordshow = findViewById(R.id.password).findViewById(R.id.imageRight);
        pass.setText("密码");
        mPassword.setHint("请输入密码");
        mPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        mPasswordshow.setImageResource(R.mipmap.password_nomal);
        mPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    mPassword.setSelection(mPassword.getText().length());
                }
            }
        });
        mPasswordshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showPassword) {// 显示密码
                    mPasswordshow.setImageResource(R.mipmap.password_press);
                    mPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showPassword = !showPassword;
                } else {// 隐藏密码
                    mPasswordshow.setImageResource(R.mipmap.password_nomal);
                    mPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showPassword = !showPassword;
                }
                needFocusable(mPassword);
            }
        });
        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setloginbutten(mUsername, mPassword);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //登录按钮
        mLoginbutten = findViewById(R.id.loginbutten).findViewById(R.id.butten);
        setloginbutten(mUsername, mPassword);

    }

    private void setloginbutten(EditText editText, EditText editText1) {
        String s = editText.getText().toString();
        String s1 = editText1.getText().toString();
        if (!s.isEmpty() && !s1.isEmpty()) {
            mLoginbutten.setEnabled(true);
            mLoginbutten.setTextColor(getResources().getColor(R.color.colorffffff));
        } else {
            mLoginbutten.setEnabled(false);
            mLoginbutten.setTextColor(getResources().getColor(R.color.colorbbbbc5));
        }
    }

    private void needFocusable(EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        editText.setSelection(editText.getText().toString().length());
    }

    private void setusernameclean() {
        String s = mUsername.getText().toString();
        if (s.isEmpty()) {
            mUsernameclean.setImageResource(R.mipmap.clean_nomal);
        } else {
            mUsernameclean.setImageResource(R.mipmap.clean_press);
        }
    }
}
