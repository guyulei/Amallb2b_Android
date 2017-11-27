package com.amall360.amallb2b_android.ui.activity.forgetpass;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.amall360.amallb2b_android.utils.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ForgetPassTwoActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    @Bind(R.id.codeEditText)
    EditText  mCodeEditText;
    @Bind(R.id.codeImageView)
    ImageView mCodeImageView;
    @Bind(R.id.codeTextView)
    TextView  mCodeTextView;
    @Bind(R.id.telTextView)
    TextView  mTelTextView;//显示 手机号
    @Bind(R.id.back)
    ImageView mBack;
    @Bind(R.id.title)
    TextView  mTitle;
    @Bind(R.id.option)
    TextView  mOption;
    private Boolean showPassword = true;//密码是否明文显示
    private String   codetelephone;//上个页面传来的code
    private EditText mNewPassText;
    private Button   mFinishbutten;
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

    @Override
    protected int bindLayout() {
        return R.layout.activity_forget_pass_two;
    }

    @Override
    protected void initData(Bundle bundle) {
        // TODO: 2017/11/24 验证正确时

    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        codetelephone = "123456";

        mBack.setOnClickListener(this);
        mTitle.setText("忘记密码(2/2)");
        //
        mCodeEditText.addTextChangedListener(this);
        mCodeTextView.setOnClickListener(this);//重发验证码
        //
        TextView newpass = findViewById(R.id.newpassword).findViewById(R.id.textLeft);
        newpass.setText("新密码");
        mNewPassText = findViewById(R.id.newpassword).findViewById(R.id.editText);
        mNewPassText.setHint("6-20位数字、字母组合");
        mNewPassText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        mNewPassText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    mNewPassText.setSelection(mNewPassText.getText().length());
                }
            }
        });

        mNewPassText.addTextChangedListener(this);
        final ImageView newPassshow = findViewById(R.id.newpassword).findViewById(R.id.imageRight);
        newPassshow.setImageResource(R.mipmap.password_nomal);
        newPassshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showPassword) {// 显示密码
                    newPassshow.setImageResource(R.mipmap.password_press);
                    mNewPassText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showPassword = !showPassword;
                } else {// 隐藏密码
                    newPassshow.setImageResource(R.mipmap.password_nomal);
                    mNewPassText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showPassword = !showPassword;
                }
                needFocusable(mNewPassText);
            }
        });
        //
        mFinishbutten = findViewById(R.id.finishbutten).findViewById(R.id.butten);
        mFinishbutten.setText("完成");
        mFinishbutten.setOnClickListener(this);
    }

    @Override
    protected void getDataNet() {


    }

    @Override
    protected void doBusiness(Context context) {
        startTimer();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.codeTextView:
                // TODO: 2017/11/24 重发验证码
                startTimer();
                break;
            case R.id.butten:
                // TODO: 2017/11/24
                ToastUtil.showToast("完成");
                break;

        }
    }

    private void needFocusable(EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        editText.setSelection(editText.getText().toString().length());
    }

    private void setloginbutten(EditText editText, EditText editText1, Button button) {
        String s = editText.getText().toString();
        String s1 = editText1.getText().toString();

        if (s.equalsIgnoreCase(codetelephone)) {
            mCodeImageView.setImageResource(R.mipmap.code_true);
        } else {
            mCodeImageView.setImageResource(R.mipmap.clean_press);
        }

        if (!s.isEmpty() && !s1.isEmpty() && s.equalsIgnoreCase(codetelephone)) {
            button.setEnabled(true);
            button.setTextColor(getResources().getColor(R.color.colorffffff));
        } else {
            button.setEnabled(false);
            button.setTextColor(getResources().getColor(R.color.colorbbbbc5));
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        setloginbutten(mCodeEditText, mNewPassText, mFinishbutten);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
