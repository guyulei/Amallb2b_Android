package com.amall360.amallb2b_android.ui.activity.citymanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.fastjson.JSONObject;
import com.amall360.amallb2b_android.R;
import com.amall360.amallb2b_android.adapter.DomainListAdapter;
import com.amall360.amallb2b_android.base.BaseActivity;
import com.amall360.amallb2b_android.bean.DomainListBean;
import com.amall360.amallb2b_android.constant.Constant;
import com.amall360.amallb2b_android.net.ApiCallback;
import com.amall360.amallb2b_android.ui.activity.MainActivity;
import com.amall360.amallb2b_android.utils.AesEncryptionUtil;
import com.amall360.amallb2b_android.utils.DialogUtil;
import com.amall360.amallb2b_android.utils.LogUtils;
import com.amall360.amallb2b_android.utils.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CityManagerActivity extends BaseActivity {

    @Bind(R.id.back)
    ImageView    mBack;
    @Bind(R.id.title)
    TextView     mTitle;
    @Bind(R.id.location_city)
    TextView     mLocationCity;
    @Bind(R.id.province)
    TextView     mProvince;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private DomainListBean.DataBean                mData;
    private List<DomainListBean.DataBean.ListBean> mList;
    private GridLayoutManager                      mManagerLayout;
    private DomainListAdapter                      mDomainListAdapter;
    private HashMap<String, String>                mMap;
    int num = 0;
    private String cityname = "";


    @Override
    protected int bindLayout() {
        return R.layout.activity_city_manager;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        mTitle.setText("选择分站");
        mList = new ArrayList<>();
        mManagerLayout = new GridLayoutManager(mActivity, 4);
        mRecyclerView.setLayoutManager(mManagerLayout);
        mDomainListAdapter = new DomainListAdapter(R.layout.adapter_domainlist_item, mList);
        mDomainListAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mDomainListAdapter.isFirstOnly(false);
        mRecyclerView.setAdapter(mDomainListAdapter);
        //
        mDomainListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DomainListBean.DataBean.ListBean item = (DomainListBean.DataBean.ListBean) adapter.getItem(position);
                num++;
                if (num == 1) {//点击了省
                    String id = item.getId();//第一次点击 省的id
                    cityname = item.getName();
                    mProvince.setText(cityname);//省的名字
                    mMap.put("type", "1");
                    mMap.put("provinceid", id);
                    getDataNet();//获取市
                } else if (num == 2) {//点击了市
                    String id = item.getId();//第一次点击 省的id
                    showtoast("num=" + num + "---id:" + id);
                    cityname = cityname + "-" + item.getName();
                    mProvince.setText(cityname);//省的名字
                    //缓存 分站id
                    SPUtils.getInstance().put(Constant.city_id, id);
                    SPUtils.getInstance().put(Constant.city_name, item.getName());
                    // TODO: 2017/11/28
                    startActivity(new Intent(mActivity, MainActivity.class));
                    finish();
                }
            }
        });
        //接口参数
        mMap = new HashMap<>();
        mMap.put("type", "0");
        mMap.put("provinceid", "0");
    }

    @Override
    protected void getDataNet() {
        //获取分站信息
        /**type : 0 ;               -int/string （0-获取省份  1-获取城市）
         provinceid : 11;    -int/string （省份id，type=1时需传参）*/

        showDialog(null);
        String key = AesEncryptionUtil.encrypt(JSONObject.toJSONString(mMap));
        LogUtils.e("getdomainList-key:" + key);
        getNetData(mBBMApiStores.getdomainList(key), new ApiCallback<DomainListBean>() {
            @Override
            public void onSuccess(DomainListBean model) {
                mData = model.getData();
                if (mData != null) {
                    DomainListBean.DataBean.SiteBean site = mData.getSite();
                    mLocationCity.setText("定位城市:" + site.getProvince() + "-" + site.getCity());
                    mList.clear();
                    mList.addAll(mData.getList());
                    mDomainListAdapter.notifyDataSetChanged();
                    disDialog();
                }
            }

            @Override
            public void onFailure(String msg) {
                showtoast(msg);
                disDialog();
            }
        });
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

    @OnClick({R.id.back, R.id.location_city})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                if (num < 2) {
                    MaterialDialog.SingleButtonCallback callback = new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            //确定
                            dialog.dismiss();
                        }
                    };
                    MaterialDialog.SingleButtonCallback negativecallback = new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            //取消
                            dialog.dismiss();
                            finish();
                        }
                    };
                    DialogUtil.materialDialog(mActivity, "提示", "请选择城市分站", callback, negativecallback);
                }
                break;
            case R.id.location_city:
                if (mData != null) {
                    DomainListBean.DataBean.SiteBean site = mData.getSite();
                    String id = site.getId();
                    String city = site.getCity();
                    //缓存 分站id
                    SPUtils.getInstance().put(Constant.city_id, id);
                    SPUtils.getInstance().put(Constant.city_name, city);
                    // TODO: 2017/11/28
                    startActivity(new Intent(mActivity, MainActivity.class));
                    finish();
                }else {
                    showtoast("111");
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mData != null) {
            mData = null;
        }
        if (mList != null) {
            mList = null;
        }
        if (mDomainListAdapter != null) {
            mDomainListAdapter = null;
        }
        if (mMap != null) {
            mMap.clear();
        }


    }
}
