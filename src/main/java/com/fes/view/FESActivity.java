package com.fes.view;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

/**
 * 项目名称：OpenSource
 * 类描述：
 * 创建人：colorful
 * 创建时间：15/12/23 17:08
 * 修改人：colorful
 * 修改时间：15/12/23 17:08
 * 修改备注：
 */
public abstract class FESActivity extends Activity{

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ViewUtils.initViews(this);
        bindViewFisish();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ViewUtils.initViews(this);
        bindViewFisish();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ViewUtils.initViews(this);
        bindViewFisish();
    }

    /**绑定完控件会回调*/
    protected abstract void bindViewFisish() ;
}
