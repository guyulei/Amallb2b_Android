package com.amall360.amallb2b_android.ui.activity.register;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amall360.amallb2b_android.R;
import com.amall360.amallb2b_android.base.BaseActivity;
import com.amall360.amallb2b_android.utils.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SellerJoinThreeActivity extends BaseActivity implements TextWatcher {

    @Bind(R.id.back)
    ImageView mBack;
    @Bind(R.id.title)
    TextView  mTitle;
    @Bind(R.id.butten)
    Button    mButten;
    @Bind(R.id.setpassword)
    EditText  mSetpassword;
    @Bind(R.id.setpasswordagain)
    EditText  mSetpasswordagain;
    @Bind(R.id.telephone)
    EditText  mTelephone;
    @Bind(R.id.codeTextView)
    TextView  mCodeTextView;
    @Bind(R.id.code)
    EditText  mCode;
    @Bind(R.id.codetest)
    ImageView mCodetest;

    private Timer   mTimer    = null;
    private int     countdown = 60;
    private Handler handler   = new Handler() {
        public void handleMessage(Message msg) {
            mCodeTextView.setText(countdown + "S后重发");
            mCodeTextView.setTextColor(getResources().getColor(R.color.colorbbbbc5));
            mCodeTextView.setAlpha(0.5f);
            if (0 == countdown) {
                //停止倒计时显示页面
                resume();
                stopTimer();
                return;
            }
            countdown--;
        }
    };
    private String  CodeNet   = "";

    @Override
    protected int bindLayout() {
        return R.layout.activity_seller_join_three;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        CodeNet = "123456";
        mTitle.setText("商户注册(3/3");
        mButten.setText("完成");
        mCode.addTextChangedListener(this);
        mTelephone.addTextChangedListener(this);
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

    @OnClick({R.id.back, R.id.butten, R.id.codeTextView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.codeTextView:
                startTimer();
                break;
            case R.id.butten:
                //判断密码一致
                String password = mSetpassword.getText().toString();
                String passwordagain = mSetpasswordagain.getText().toString();
                if (password.equals(passwordagain)) {

                    // TODO: 2017/11/27

                } else {
                    ToastUtil.showToast("您输入的密码不一致！");
                }
                break;
        }
    }

    //开始倒计时
    private void startTimer() {
        //点击获取验证码后改变按钮的状态,让按钮不可点击
        mCodeTextView.setClickable(false);
        if (mTimer == null) {
            mTimer = new Timer(true);
        }
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        }, 0, 1000);
    }

    private void resume() {
        stopTimer();
        countdown = 60;
        //倒计时结束后,让按钮重新可点击
        mCodeTextView.setClickable(true);
        mCodeTextView.setText("重新获取");
        mCodeTextView.setTextColor(getResources().getColor(R.color.colorf23030));
        mCodeTextView.setAlpha(0.9f);
    }

    private void stopTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimer();
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

    private void setloginbutten(Button button) {
        String password = mSetpassword.getText().toString();
        String passwordagain = mSetpasswordagain.getText().toString();
        String tele = mTelephone.getText().toString();
        String code = mCode.getText().toString();
        if (code.equalsIgnoreCase(CodeNet)) {
            mCodetest.setImageResource(R.mipmap.code_true);
        } else {
            mCodetest.setImageResource(R.mipmap.clean_press);
        }
        if (!password.isEmpty() && !passwordagain.isEmpty() && !code.isEmpty() && !tele.isEmpty() && code.equalsIgnoreCase(CodeNet)) {
            button.setEnabled(true);
            button.setTextColor(getResources().getColor(R.color.colorffffff));
        } else {
            button.setEnabled(false);
            button.setTextColor(getResources().getColor(R.color.colorbbbbc5));
        }
    }
}
