package com.amall360.amallb2b_android.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.amall360.amallb2b_android.R;
import com.amall360.amallb2b_android.net.AppClient;
import com.amall360.amallb2b_android.net.WorkerApiStores;
import com.gyf.barlibrary.ImmersionBar;

import org.simple.eventbus.EventBus;

import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by A on 2017/6/15.
 */

public abstract class BaseActivity extends AppCompatActivity {


    protected BaseActivity mActivity;
    /**
     * 当前Activity渲染的视图View
     */
    protected View         contentView;
    /**
     * 是否全屏
     */
    private boolean isFullScreen     = false;
    /**
     * 是否沉浸状态栏
     */
    private boolean isSteepStatusBar = true;
    protected WorkerApiStores       mWorkerApiStores;
    private   CompositeSubscription mCompositeSubscription;
    private int mcolor = R.color.colorAccent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mWorkerApiStores = AppClient.getWorkerRetrofit().create(WorkerApiStores.class);
            mActivity = this;
            // 添加Activity到堆栈
            AppManager.getAppManager().addActivity(this);
            Bundle bundle = getIntent().getExtras();
            initData(bundle);
            contentView = LayoutInflater.from(this).inflate(bindLayout(), null);
            if (isFullScreen) {
                this.getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
                requestWindowFeature(Window.FEATURE_NO_TITLE);
            }
            if (isSteepStatusBar) {
                setstatusBar(mcolor);
            }
            setContentView(contentView);
            ButterKnife.bind(this);
            initView(savedInstanceState, contentView);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//屏幕竖屏
            //
            // 注册对象
            EventBus.getDefault().register(this);

        } catch (Exception e) {
            e.printStackTrace();
        }

        getDataNet();
    }

    public void setstatusBar(int color) {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //当前设备支持状态栏字体变色，会设置状态栏字体为黑色，不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .statusBarColor(color)
                .fitsSystemWindows(true)  //使用该属性必须指定状态栏的颜色，不然状态栏透明
                .init();
    }


    protected abstract int bindLayout();

    //从上个Activity传递过来的bundle
    protected abstract void initData(Bundle bundle);

    public abstract void initView(Bundle savedInstanceState, final View view);

    protected abstract void getDataNet();

    protected abstract void doBusiness(Context context);

    @Override
    protected void onStart() {
        super.onStart();

        //获取网络数据

        doBusiness(this);
    }


    public void showtoast(String str) {
        Toast.makeText(mActivity, str, Toast.LENGTH_SHORT).show();
    }

    public ProgressDialog progressDialog;

    public ProgressDialog showPro(String mess) {
        progressDialog = new ProgressDialog(this);
        if (null == mess) {
            progressDialog.setMessage("加载中");
        } else {
            progressDialog.setMessage(mess);
        }
        progressDialog.show();
        return progressDialog;
    }

    public void dismissPro() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }


    /**
     * 设置是否全屏
     *
     * @param isFullScreen 是否全屏
     */
    public void setFullScreen(boolean isFullScreen) {
        this.isFullScreen = isFullScreen;
    }

    /**
     * 设置是否沉浸状态栏
     *
     * @param isSteepStatusBar 是否沉浸状态栏
     */
    public void setSteepStatusBar(boolean isSteepStatusBar) {
        this.isSteepStatusBar = isSteepStatusBar;
    }

    @Override
    protected void onDestroy() {
        // 结束Activity&从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
        // 注销
        EventBus.getDefault().unregister(this);
        super.onDestroy();
        ButterKnife.unbind(this);
        ImmersionBar.with(this).destroy();//状态栏
        //RXjava取消注册，以避免内存泄露
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public void getNetData(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
}
