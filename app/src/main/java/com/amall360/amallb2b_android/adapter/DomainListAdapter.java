package com.amall360.amallb2b_android.adapter;

import android.support.annotation.Nullable;

import com.amall360.amallb2b_android.R;
import com.amall360.amallb2b_android.bean.DomainListBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by A on 2017/11/27.
 */

public class DomainListAdapter extends BaseQuickAdapter<DomainListBean.DataBean.ListBean, BaseViewHolder> {


    public DomainListAdapter(int layoutResId, @Nullable List<DomainListBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DomainListBean.DataBean.ListBean item) {
        helper.setText(R.id.text, item.getName());
    }
}
