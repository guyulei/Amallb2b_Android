package com.amall360.amallb2b_android.bean;

import android.support.annotation.LayoutRes;

/**
 * Created by A on 2017/11/20.
 */

public class PageModel {
    @LayoutRes
    public int LayoutRes;

    public PageModel(@LayoutRes int layoutRes) {
        this.LayoutRes = layoutRes;
    }
}
