package com.fes;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fes.view.ViewUtils;

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

    public View mRootView = null;

    protected Activity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=getActivity();
        /**获取布局xml文件*/
        int  mLayoutId = ViewUtils.getLayoutId(this);
        if (mLayoutId != -1) {
            if (mRootView == null) {
                mRootView = View.inflate(getContext(),ViewUtils.getLayoutId(this), null);
                ViewGroup parent = (ViewGroup) mRootView.getParent();
                if (null != parent) {
                    parent.removeView(mRootView);
                }
            }
            ViewUtils.initViews(this, mRootView);
        }
        fesOnCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return mRootView;
    }

    /**绑定完控件会回调*/
    protected abstract void fesOnCreate(Bundle savedInstanceState) ;


}
