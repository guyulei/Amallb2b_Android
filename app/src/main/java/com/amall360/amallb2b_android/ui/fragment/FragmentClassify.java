package com.amall360.amallb2b_android.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.amall360.amallb2b_android.base.BaseFragment;
import com.amall360.amallb2b_android.bean.ceshi.UserCheckBean;
import com.amall360.amallb2b_android.net.ApiCallback;
import com.amall360.amallb2b_android.utils.AesEncryptionUtil;
import com.amall360.amallb2b_android.utils.LogUtils;

import java.util.HashMap;

public class FragmentClassify extends BaseFragment {
    @LayoutRes
    int LayoutRes;

    public static FragmentClassify newInstance(@LayoutRes int layoutRes) {
        FragmentClassify fragment = new FragmentClassify();
        Bundle args = new Bundle();
        args.putInt("fragmentclassify", layoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return LayoutRes;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {

    }

    @Override
    public void doBusiness(final Context context) {

        showDialog(null);
        String username = "gu";
        String mobile = "15958121433";
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("username", username);
        map1.put("mobile", mobile);
        String encrypt = AesEncryptionUtil.encrypt(JSONObject.toJSONString(map1));
        LogUtils.e("encrypt:" + encrypt);
        getNetData(mBBMApiStores.setuserCheck(encrypt), new ApiCallback<UserCheckBean>() {
            @Override
            public void onSuccess(UserCheckBean model) {
                Toast.makeText(context, model.getStatus_code(), Toast.LENGTH_LONG).show();
                disDialog();
            }

            @Override
            public void onFailure(String msg) {
                disDialog();
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            LayoutRes = args.getInt("fragmentclassify");
        }
    }
}
