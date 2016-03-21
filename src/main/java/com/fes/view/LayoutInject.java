package com.fes.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 项目名称：OpenSource
 * 类描述：使用注解绑定layout文件
 * 创建人：colorful-chao
 * 创建时间：16/1/18 18:27
 * 修改人：colorful
 * 修改时间：16/1/18 18:27
 * 修改备注：
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LayoutInject {
    int layout() default -1;
}
