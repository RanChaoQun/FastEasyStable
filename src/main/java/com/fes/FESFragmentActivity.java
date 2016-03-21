package com.fes;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.fes.view.ViewUtils;

/**
 * 项目名称：OpenSource
 * 类描述：
 * 创建人：colorful
 * 创建时间：15/12/23 17:08
 * 修改人：colorful
 * 修改时间：15/12/23 17:08
 * 修改备注：
 */
public abstract class FESFragmentActivity extends FragmentActivity{
    protected Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=this;
        int  mLayoutId=ViewUtils.getLayoutId(this);
        if(mLayoutId!=-1) {
            setContentView(mLayoutId);
        }
        fesOnCreate(savedInstanceState);
        setTranslucentStatus(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ViewUtils.initViews(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ViewUtils.initViews(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ViewUtils.initViews(this);
    }

    /**绑定完控件会回调*/
    protected abstract void fesOnCreate(Bundle savedInstanceState) ;

    /**
     * 设置沉侵栏
     *
     * @param on
     */
    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}
