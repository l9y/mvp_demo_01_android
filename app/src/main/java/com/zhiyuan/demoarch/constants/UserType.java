package com.zhiyuan.demoarch.constants;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhiyuan
 * @date 2018/9/6
 */

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.CONSTRUCTOR, ElementType.LOCAL_VARIABLE, ElementType.PARAMETER,
        ElementType.FIELD, ElementType.METHOD})
@IntDef({UserType.ORG, UserType.ADMIN, UserType.NORMAL})
public @interface UserType {

    /**
     * 管理员
     */
    int ADMIN = 0;

    /**
     * 机构用户
     */
    int ORG = 1;

    /**
     * 普通用户
     */
    int NORMAL = 2;
}
