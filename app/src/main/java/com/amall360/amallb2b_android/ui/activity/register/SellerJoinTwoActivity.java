package com.amall360.amallb2b_android.ui.activity.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
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

public class SellerJoinTwoActivity extends BaseActivity implements TextWatcher {

    @Bind(R.id.back)
    ImageView mBack;
    @Bind(R.id.title)
    TextView  mTitle;
    @Bind(R.id.contacts)
    EditText  mContacts;
    @Bind(R.id.contactsClean)
    ImageView mContactsClean;
    @Bind(R.id.companyname)
    EditText  mCompanyname;
    @Bind(R.id.companynameClean)
    ImageView mCompanynameClean;
    @Bind(R.id.butten)
    Button    mButten;

    @Override
    protected int bindLayout() {
        return R.layout.activity_seller_join_two;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        mTitle.setText("商户注册(2/3)");
        mButten.setText("下一步");
        mContacts.addTextChangedListener(this);
        mCompanyname.addTextChangedListener(this);
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

    @OnClick({R.id.back, R.id.contactsClean, R.id.companynameClean, R.id.butten})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.contactsClean:
                setclean(mContacts);
                break;
            case R.id.companynameClean:
                setclean(mCompanyname);
                break;
            case R.id.butten:
                startActivity(new Intent(mActivity, SellerJoinThreeActivity.class));
                break;
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

    private void setclean(EditText editText) {
        editText.setText(null);
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        editText.setSelection(editText.getText().toString().length());
    }

    private void setloginbutten(Button button) {

        String contacts = mContacts.getText().toString();
        String companyname = mCompanyname.getText().toString();
        if (contacts.isEmpty()) {
            mContactsClean.setImageResource(R.mipmap.clean_nomal);
        } else {
            mContactsClean.setImageResource(R.mipmap.clean_press);
        }
        if (companyname.isEmpty()) {
            mCompanynameClean.setImageResource(R.mipmap.clean_nomal);
        } else {
            mCompanynameClean.setImageResource(R.mipmap.clean_press);
        }
        if (!contacts.isEmpty() && !companyname.isEmpty()) {
            button.setEnabled(true);
            button.setTextColor(getResources().getColor(R.color.colorffffff));
        } else {
            button.setEnabled(false);
            button.setTextColor(getResources().getColor(R.color.colorbbbbc5));
        }
    }
}
