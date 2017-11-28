package com.amall360.amallb2b_android.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;

import com.amall360.amallb2b_android.R;
import com.maning.mndialoglibrary.MProgressDialog;
import com.maning.mndialoglibrary.MStatusDialog;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by A on 2017/11/22.
 */

public class DialogUtils {

    //mMProgressDialog.showNoText();
    //mMProgressDialog.showWithProgress();
    private static MProgressDialog         mMProgressDialog;
    private static DialogUtils             dialogUtils;
    private static Context                 mContext;
    private static MProgressDialog.Builder sBuilder;
    private        MStatusDialog           mMStatusDialog;

    private Timer     timer;
    private TimerTask task;
    private Handler mHandler        = new Handler();
    private float   currentProgress = 0.0f;

    public static DialogUtils init(Context context) {
        mContext = context;
        if (dialogUtils == null) {
            dialogUtils = new DialogUtils();
        }
        if (mMProgressDialog == null) {
            sBuilder = new MProgressDialog.Builder(mContext);
        }
        return dialogUtils;
    }

    //自定义
    public void showProgressDialog() {
        //新建一个Dialog
        mMProgressDialog = sBuilder
                //点击外部是否可以取消
                .isCanceledOnTouchOutside(true)
                //全屏背景窗体的颜色
                .setBackgroundWindowColor(mContext.getResources().getColor(R.color.colorDialogWindowBg))
                //View背景的颜色
                .setBackgroundViewColor(mContext.getResources().getColor(R.color.colorDialogViewBg))
                //View背景的圆角
                .setCornerRadius(20)
                //View 边框的颜色
                .setStrokeColor(mContext.getResources().getColor(R.color.colorAccent))
                //View 边框的宽度
                .setStrokeWidth(2)
                //Progress 颜色
                .setProgressColor(mContext.getResources().getColor(R.color.colorDialogProgressBarColor))
                //Progress 宽度
                .setProgressWidth(3)
                //Progress 内圈颜色
                .setProgressRimColor(Color.YELLOW)
                //Progress 内圈宽度
                .setProgressRimWidth(4)
                //文字的颜色
                .setTextColor(mContext.getResources().getColor(R.color.colorDialogTextColor))
                .build();
        if (mMProgressDialog != null) {
            mMProgressDialog.show();

        }
    }

    //带信息
    public void showDialog(String msg) {
        mMProgressDialog = sBuilder.build();
        if (mMProgressDialog != null) {
            mMProgressDialog.show(msg);
        }
    }

    //默认
    public void showDialog() {
        mMProgressDialog = sBuilder.build();
        if (mMProgressDialog != null) {
            mMProgressDialog.show();
        }
    }

    public void dissDialog() {
        if (mMProgressDialog != null) {
            mMProgressDialog.dismiss();
        }
    }

    //下载
    /*mMProgressDialog.showWithProgress();
    initTimer();*/
    private void initTimer() {
        destroyTimer();
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (currentProgress < 1.0f) {
                            int pro = (int) (currentProgress * 100);
                            mMProgressDialog.setDialogProgress(currentProgress, "视频下载进度: " + pro + "%");

                            currentProgress += 0.1;
                        } else {
                            destroyTimer();
                            currentProgress = 0.0f;
                            mMProgressDialog.setDialogProgress(1.0f, "完成");
                            //关闭
                            mHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mMProgressDialog.dismiss();

                                }
                            }, 500);
                        }
                    }
                });
            }
        };
        timer.schedule(task, 0, 1000); //延时1000ms后执行，1000ms执行一次
    }

    private void destroyTimer() {
        if (timer != null && task != null) {
            timer.cancel();
            task.cancel();
            timer = null;
            task = null;
        }
    }

    public void showStatusDialog() {
        mMStatusDialog = new MStatusDialog(mContext);
        mMStatusDialog.show("保存成功", mContext.getResources().getDrawable(R.mipmap.mn_icon_dialog_ok));
    }
}
