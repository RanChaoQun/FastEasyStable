package com.fes.view;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import java.lang.reflect.Field;

public class ViewUtils {

    public static int getLayoutId(Object obj) {
        int id = -1;
        LayoutInject layoutInject = obj.getClass().getAnnotation(LayoutInject.class);
        if (layoutInject != null) {
            id = layoutInject.layout();
        }
        return id;
    }

    /**
     * @param activity
     */
    public static void initViews(Activity activity) {
        initViews(activity, activity.getWindow().getDecorView());
    }

    /**
     * 遍历当前属性，并和xml绑定
     *
     * @param injectedSource
     * @param sourceView
     */
    public static void initViews(Object injectedSource, View sourceView) {
        //获取所有属性
        Field[] fields = injectedSource.getClass().getDeclaredFields();
        //遍历
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    //已经绑定属性
                    if (field.get(injectedSource) != null) {
                        continue;
                    }

                    //绑定xml
                    ViewInject viewInject = field.getAnnotation(ViewInject.class);
                    if (viewInject != null) {
                        int viewId = viewInject.id();
                        if (viewId == -1) {
                            throw new RuntimeException("绑定控件失败," + field.getName() + "指定的ID" + viewId + "没有找到.");
                        }
                        //绑定控件
                        View view = sourceView.findViewById(viewId);
                        field.set(injectedSource, view);
                        //添加事件
                        if (viewInject.click()) {//
                            if (injectedSource instanceof View.OnClickListener) {
                                view.setOnClickListener((View.OnClickListener) injectedSource);
                            } else {
                                throw new RuntimeException("添加onClick事件失败,"
                                        + injectedSource.getClass().getName()
                                        + "不能转换为OnClickListener对象");
                            }
                        }
                        if (viewInject.longClick()) {
                            if (injectedSource instanceof View.OnLongClickListener) {
                                view.setOnLongClickListener((View.OnLongClickListener) injectedSource);
                            } else {
                                throw new RuntimeException("添加longClick事件失败,"
                                        + injectedSource.getClass().getName()
                                        + "不能转换为OnLongClickListener对象");
                            }
                        }
                        if (viewInject.itemClick()) {
                            if (injectedSource instanceof AdapterView.OnItemClickListener) {
                                if (view instanceof AdapterView) {
                                    ((AdapterView) view).setOnItemClickListener((AdapterView.OnItemClickListener) injectedSource);
                                } else {
                                    throw new RuntimeException("添加litemClick事件失败,"
                                            + field.getName()
                                            + "不是AdapterView");
                                }
                            } else {
                                throw new RuntimeException("添加litemClick事件失败,"
                                        + injectedSource.getClass().getName()
                                        + "不能转换为OnItemClickListener对象");
                            }

                        }
                        if (viewInject.itemLongClick()) {
                            if (injectedSource instanceof AdapterView.OnItemLongClickListener) {
                                if (view instanceof AdapterView) {
                                    ((AdapterView) view).setOnItemLongClickListener((AdapterView.OnItemLongClickListener) injectedSource);
                                } else {
                                    throw new RuntimeException("添加itemLongClick事件失败,"
                                            + field.getName()
                                            + "不是AdapterView");
                                }
                            } else {
                                throw new RuntimeException("添加litemClick事件失败,"
                                        + injectedSource.getClass().getName()
                                        + "不能转换为OnItemLongClickListener对象");
                            }
                        }
                        if (viewInject.checkChange()) {//checkChange
                            if (injectedSource instanceof CompoundButton.OnCheckedChangeListener) {
                                if (view instanceof CheckBox) {
                                    ((CheckBox) view).setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) injectedSource);
                                } else {
                                    throw new RuntimeException("添加checkChange事件失败,"
                                            + field.getName()
                                            + "CheckBox");
                                }
                            } else {
                                throw new RuntimeException("添加litemClick事件失败,"
                                        + injectedSource.getClass().getName()
                                        + "不能转换为CompoundButton.OnCheckedChangeListener对象");
                            }
                        }
                        if (viewInject.pageChange()) {//onPageChangeListener
                            if (injectedSource instanceof ViewPager.OnPageChangeListener) {
                                if (view instanceof ViewPager) {
//                                    if();
                                    ((ViewPager) view).addOnPageChangeListener((ViewPager.OnPageChangeListener) injectedSource);
                                } else {
                                    throw new RuntimeException("添加checkChange事件失败,"
                                            + field.getName()
                                            + "CheckBox");
                                }
                            } else {
                                throw new RuntimeException("添加litemClick事件失败,"
                                        + injectedSource.getClass().getName()
                                        + "不能转换为CompoundButton.OnCheckedChangeListener对象");
                            }
                        }
                        if (viewInject.selectChaneg()) {//selectChaneg
                            if (injectedSource instanceof RadioGroup.OnCheckedChangeListener) {
                                if (view instanceof RadioGroup) {
                                    ((RadioGroup) view).setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) injectedSource);
                                } else {
                                    throw new RuntimeException("添加checkChange事件失败,"
                                            + field.getName()
                                            + "CheckBox");
                                }
                            } else {
                                throw new RuntimeException("添加litemClick事件失败,"
                                        + injectedSource.getClass().getName()
                                        + "不能转换为CompoundButton.OnCheckedChangeListener对象");
                            }
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
