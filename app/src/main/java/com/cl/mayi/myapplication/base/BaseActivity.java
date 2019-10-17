package com.cl.mayi.myapplication.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cl.mayi.myapplication.base.interfaces.IPresenter;
import com.cl.mayi.myapplication.base.interfaces.IView;
import com.cl.mayi.myapplication.dialog.LoadingDialog;
import com.cl.mayi.myapplication.user.UserUtil;
import com.cl.mayi.myapplication.utils.ActivityManagement;
import com.cl.mayi.myapplication.utils.Statusbar.StatusBarUtil;


public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IView {
    protected P mPresenter;

    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//禁止横屏
        UserUtil.registerUserUtil(this);
        ActivityManagement.getInstance().addActivity(this);


        //沉浸式代码配置
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this, 0x55000000);
        }
        initPresenter();

    }

    public void startActivity(Class<? extends Activity> target) {
        Intent intent = new Intent(this, target);
        startActivity(intent);

    }

    private void initPresenter() {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    protected abstract P createPresenter();


    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }

        super.onDestroy();
    }

    public void showLoadingMessage() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);
        }
        loadingDialog.show();
    }


    public void closeLoadingMessage() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

}
