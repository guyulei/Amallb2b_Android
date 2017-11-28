package com.amall360.amallb2b_android.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import com.afollestad.materialdialogs.MaterialDialog;
import com.amall360.amallb2b_android.R;

public class DialogUtil {

    public static ProgressDialog makeDialog(Context context, String msg) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(msg);
        return progressDialog;
    }

    public static MaterialDialog materialDialog(Context context, String title, String content) {
        MaterialDialog materialDialog = new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .contentColor(context.getResources().getColor(R.color.black))
                .positiveText("确定")
                .cancelable(false)
                .show();
        return materialDialog;
    }

    public static MaterialDialog materialDialog(Context context, String content) {
        MaterialDialog materialDialog = new MaterialDialog.Builder(context)
                .content(content)
                .contentColor(context.getResources().getColor(R.color.black))
                .positiveText("确定")
                .cancelable(false)
                .show();
        return materialDialog;
    }

    public static MaterialDialog materialDialog(Context context, String content, MaterialDialog.SingleButtonCallback callback) {
        MaterialDialog materialDialog = new MaterialDialog.Builder(context)
                .content(content)
                .contentColor(context.getResources().getColor(R.color.black))
                .positiveText("确定")
                .onPositive(callback)
                .cancelable(false)
                .show();
        return materialDialog;
    }

    public static MaterialDialog materialDialog(Context context, String title, String content, MaterialDialog.SingleButtonCallback callback) {
        MaterialDialog materialDialog = new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .contentColor(context.getResources().getColor(R.color.black))
                .positiveText("确定")
                .onPositive(callback)
                .cancelable(false)
                .show();
        return materialDialog;
    }

    public static MaterialDialog materialDialog(Context context, String title, String content, MaterialDialog.SingleButtonCallback callback, MaterialDialog.SingleButtonCallback negativecallback) {
        MaterialDialog materialDialog = new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .contentColor(context.getResources().getColor(R.color.black))
                .positiveText("确定")
                .onPositive(callback)
                .negativeText("取消")
                .onNegative(negativecallback)
                .cancelable(false)
                .show();
        return materialDialog;
    }


    public static void showAlertDialog(final Context context, String str) {
        new AlertDialog.Builder(context)
                .setTitle("获取" + str + "权限被禁用")
                .setMessage("请在 设置-应用管理-" + context.getString(R.string.app_name) + "-权限管理 (将" + str + "权限打开)")
                .setNegativeButton("取消", null)
                .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package:" + context.getPackageName()));
                        context.startActivity(intent);
                    }
                }).show();
    }

}
