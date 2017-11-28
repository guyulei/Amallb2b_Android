package com.amall360.amallb2b_android.ui.activity.setting;

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

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateLoginTeleActivity extends BaseActivity implements TextWatcher {

    @Bind(R.id.title)
    TextView  mTitle;
    @Bind(R.id.codeEditText)
    EditText  mCodeEditText;
    @Bind(R.id.codeImageView)
    ImageView mCodeImageView;
    @Bind(R.id.codeTextView)
    TextView  mCodeTextView;
    @Bind(R.id.butten)
    Button    mButten;
    private String codetelephone;//上个页面传来的code
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
        return R.layout.activity_update_login_tele;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        codetelephone = "123456";
        mTitle.setText("修改登录手机");
        mButten.setText("完成");
        mCodeEditText.addTextChangedListener(this);

    }

    @Override
    protected void getDataNet() {

    }

    @Override
    protected void doBusiness(Context context) {
        startTimer();
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
        String code = mCodeEditText.getText().toString();

        if (code.equals(codetelephone)) {
            mCodeImageView.setImageResource(R.mipmap.code_true);
        } else {
            mCodeImageView.setImageResource(R.mipmap.clean_press);
        }
        if (!code.isEmpty()  && code.equalsIgnoreCase(codetelephone)) {
            mButten.setEnabled(true);
            mButten.setTextColor(getResources().getColor(R.color.colorffffff));
        } else {
            mButten.setEnabled(false);
            mButten.setTextColor(getResources().getColor(R.color.colorbbbbc5));
        }

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @OnClick({R.id.back, R.id.codeTextView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.codeTextView:
                //重发验证码
                startTimer();
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
}
