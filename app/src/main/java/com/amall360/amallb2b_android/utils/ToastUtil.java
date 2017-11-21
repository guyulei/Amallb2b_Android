package com.amall360.amallb2b_android.utils;

import android.widget.Toast;

/**
 * Created by Administrator on 2016/6/30 0030.
 */
public class ToastUtil {

    private static Toast toast;

    public static void showToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(Utils.getContext(), "", Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }
   /* public static void showToast(Context context,String msg) {
        if (toast == null) {
            toast = Toast.makeText(Utils.getContext(), "", Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }*/
}
