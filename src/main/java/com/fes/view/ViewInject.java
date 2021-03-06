package com.fes.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 项目名称：OpenSource
 * 类描述：
 * 创建人：colorful-chao
 * 创建时间：16/1/18 18:24
 * 修改人：colorful
 * 修改时间：16/1/18 18:24
 * 修改备注：
 */
@Target(ElementType.FIELD)//
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewInject {
    int id() default -1;//属性对应xml的ID

    boolean click() default false;//点击

    boolean longClick() default false;//长按

    boolean itemClick() default false;//item点击

    boolean itemLongClick() default false;//item长按

    boolean pageChange() default false;//viewpagerpage change

    boolean checkChange() default false; //checkbox check change

    boolean selectChaneg() default false; //radiogrup selec change
}
