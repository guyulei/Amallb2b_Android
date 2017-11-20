package com.amall360.amallb2b_android.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amall360.amallb2b_android.net.AppClient;
import com.amall360.amallb2b_android.net.BBMApiStores;

import org.simple.eventbus.EventBus;

import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseFragment extends Fragment {

    private static final String TAG = "BaseFragment";

    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    /**
     * 当前Activity渲染的视图View
     */
    protected View         contentView;
    protected BaseActivity mActivity;
    private boolean isVisible = false;
    private boolean isInitView = false;
    private boolean isFirstLoad = true;

    protected BBMApiStores          mBBMApiStores;
    private   CompositeSubscription mCompositeSubscription;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
        Log.d(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        contentView = inflater.inflate(bindLayout(), null);
        Log.d(TAG, "onCreateView: ");
        ButterKnife.bind(this,contentView);
        // 注册对象
        EventBus.getDefault().register(this);
        mBBMApiStores = AppClient.getWorkerRetrofit().create(BBMApiStores.class);
        return contentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        initData(bundle);
        initView(savedInstanceState, contentView);
        isInitView=true;
        Log.d(TAG, "onViewCreated: ");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
        lazyLoad();

        Log.d(TAG, "onActivityCreated: ");
    }

    private void lazyLoad() {

        if (!isFirstLoad || !isVisible || !isInitView) {
            //不加载数据
            return;
        }
        //加载数据
        doBusiness(mActivity);

        isFirstLoad = false;

    }


    /**
     * 初始化数据
     *
     * @param bundle 传递过来的bundle
     */
    public abstract void initData(Bundle bundle);

    /**
     * 绑定布局
     *
     * @return 布局Id
     */
    public abstract int bindLayout();

    /**
     * 初始化view
     */
    public abstract void initView(Bundle savedInstanceState, final View view);

    /**
     * 业务操作
     *
     * @param context 上下文
     */
    public abstract void doBusiness(Context context);


    @Override
    public void onDestroyView() {
        if (contentView != null) {
            ((ViewGroup) contentView.getParent()).removeView(contentView);
        }
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        // 注销
        EventBus.getDefault().unregister(this);
        //RXjava取消注册，以避免内存泄露
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    public void getNetDataSub(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

}
