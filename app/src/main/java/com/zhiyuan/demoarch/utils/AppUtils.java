package com.zhiyuan.demoarch.utils;

import android.app.Activity;

import com.zhiyuan.demoarch.constants.UserType;

/**
 * @author zhiyuan
 * @date 2018/9/6
 */

public class AppUtils {

    public static String getUserTypeName(@UserType int userType) {
        switch (userType) {
            case UserType.ADMIN:
                return "管理员";
            case UserType.NORMAL:
                return "普通用户";
            case UserType.ORG:
                return "机构用户";
            default:
                throw new IllegalArgumentException("未知用户类型：" + userType);
        }
    }


    public static boolean isViewFinishing(Object obj) {
        if (obj instanceof Activity) {
            return ((Activity) obj).isFinishing();
        }
        throw new IllegalArgumentException("未知类型：" + obj);
    }
}
