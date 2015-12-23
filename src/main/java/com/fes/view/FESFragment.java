package com.fes.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 项目名称：OpenSource
 * 类描述：
 * 创建人：colorful
 * 创建时间：15/12/23 17:11
 * 修改人：colorful
 * 修改时间：15/12/23 17:11
 * 修改备注：
 */
public abstract class FESFragment extends Fragment {
    protected View mRootView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (null != parent) {
                parent.removeView(mRootView);
            }
        }
        ViewUtils.initViews(this, mRootView);
        bingViewFinish();
        return mRootView;
    }

    /**
     * 获取布局文件ID
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 处理业务
     */
    public abstract void bingViewFinish();
}
