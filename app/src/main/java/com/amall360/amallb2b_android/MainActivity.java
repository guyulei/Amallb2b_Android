package com.amall360.amallb2b_android;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.amall360.amallb2b_android.base.BaseActivity;
import com.amall360.amallb2b_android.bean.ceshi.UserCheckBean;
import com.amall360.amallb2b_android.net.ApiCallback;
import com.amall360.amallb2b_android.utils.AesEncryptionUtil;
import com.amall360.amallb2b_android.utils.LogUtils;

import java.util.HashMap;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.textview)
    TextView mTextview;

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {

    }

    @Override
    protected void getDataNet() {



    }

    @Override
    protected void doBusiness(Context context) {
        String username = "gu";
        String mobile = "15958121433";

        /*JSONObject jsonObject=JSONObject.fromObject(map);
        jsonObject.toString();*/
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("username",username);
        map1.put("mobile",mobile);


        String s1 = JSONObject.toJSONString(map1);


        HashMap<String, String> map = new HashMap<>();

        String encrypt = AesEncryptionUtil.encrypt(s1, "625202f9149e061d", "5efd3f6060e20330");

        LogUtils.e("encrypt:"+encrypt);
        map.put("key",encrypt);
        getNetData(mWorkerApiStores.setuserCheck(map), new ApiCallback<UserCheckBean>() {


            @Override
            public void onSuccess(UserCheckBean model) {
                Toast.makeText(MainActivity.this,model.getStatus_code(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_LONG).show();
            }
        });
    }
}
