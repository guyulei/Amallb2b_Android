package com.amall360.amallb2b_android.bean.EventBusBean;

/**
 * Created by A on 2017/6/20.
 */

public class EventBusPublicBean {
    private String keyword;
    private int    id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public EventBusPublicBean(String str) {
        this.keyword = str;
    }

    public void setkeyword(String str) {
        this.keyword = str;
    }

    public String getkeyword() {
        return keyword;
    }

}
