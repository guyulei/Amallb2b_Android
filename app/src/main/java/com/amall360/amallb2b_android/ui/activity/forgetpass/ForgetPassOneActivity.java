package com.amall360.amallb2b_android.ui.activity.forgetpass;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.amall360.amallb2b_android.R;
import com.amall360.amallb2b_android.base.BaseActivity;

import butterknife.Bind;

public class ForgetPassOneActivity extends BaseActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, TextWatcher {

    @Bind(R.id.mySeekBar)
    SeekBar   mMySeekBar;
    @Bind(R.id.hinttext)
    TextView  mHinttext;
    @Bind(R.id.customservice)
    TextView  mCustomservice;
    @Bind(R.id.back)
    ImageView mBack;
    @Bind(R.id.title)
    TextView  mTitle;
    private EditText  mTelephone;
    private ImageView mTelephoneclean;

    @Override
    protected int bindLayout() {
        return R.layout.activity_forget_pass_one;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {

        mBack.setOnClickListener(this);
        mTitle.setText("忘记密码(1/2)");
        //
        TextView tel = findViewById(R.id.telephone).findViewById(R.id.textLeft);
        tel.setText("手机号");
        mTelephone = findViewById(R.id.telephone).findViewById(R.id.editText);
        mTelephone.setInputType(InputType.TYPE_CLASS_NUMBER);
        mTelephone.setHint("请输入注册时的手机号");
        mTelephoneclean = findViewById(R.id.telephone).findViewById(R.id.imageRight);
        mTelephoneclean.setImageResource(R.mipmap.clean_nomal);
        mTelephone.addTextChangedListener(this);
        mTelephoneclean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTelephone.setText(null);
                needFocusable(mTelephone);
            }
        });
        //
        mMySeekBar.setOnSeekBarChangeListener(this);
        mCustomservice.setOnClickListener(this);
        mCustomservice.setText(Html.fromHtml("<u>" + "联系客服" + "</u>"));
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
            case R.id.back:
                finish();
                break;
            case R.id.customservice:
                // TODO: 2017/11/23 联系客服
                showtoast("联系客服");
                break;
        }
    }

    private void needFocusable(EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        editText.setSelection(editText.getText().toString().length());
    }

    private void settelephoneclean() {
        String s = mTelephone.getText().toString();
        if (s.isEmpty()) {
            mTelephoneclean.setImageResource(R.mipmap.clean_nomal);
        } else {
            mTelephoneclean.setImageResource(R.mipmap.clean_press);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (seekBar.getProgress() != 0) {
            mHinttext.setText(null);
        } else {
            mHinttext.setTextColor(getResources().getColor(R.color.color22222c));
            mHinttext.setText("向右滑动验证");
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        seekBar.setProgressDrawable(getResources().getDrawable(R.drawable.bg_forgotpassword_seekbar));
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar.getProgress() != seekBar.getMax()) {
            seekBar.setProgress(0);
            seekBar.setThumb(getResources().getDrawable(R.mipmap.seekbar_thumb));
            seekBar.setProgressDrawable(getResources().getDrawable(R.mipmap.seekbar_progress));
        } else {
            String s = mTelephone.getText().toString();
            if (s.isEmpty()) {
                mHinttext.setTextColor(getResources().getColor(R.color.colorffffff));
                mHinttext.setText("验证不通过");
                seekBar.setProgressDrawable(getResources().getDrawable(R.mipmap.seekbar_progress_false));
                seekBar.setThumb(getResources().getDrawable(R.mipmap.seekbar_thumb_false));
            } else {
                mHinttext.setTextColor(getResources().getColor(R.color.colorffffff));
                mHinttext.setText("验证通过");
                seekBar.setProgressDrawable(getResources().getDrawable(R.mipmap.seekbar_progress_true));
                seekBar.setThumb(getResources().getDrawable(R.mipmap.seekbar_thumb_true));
                //
                startActivity(new Intent(mActivity, ForgetPassTwoActivity.class));
            }
        }
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        settelephoneclean();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
