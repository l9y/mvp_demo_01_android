package com.zhiyuan.demoarch.presentation.main;

import android.support.annotation.NonNull;

import com.zhiyuan.demoarch.base.BaseView;
import com.zhiyuan.demoarch.constants.UserType;
import com.zhiyuan.demoarch.vo.main.UserInfoBean;
import com.zhiyuan.demoarch.vo.main.UserSettingInfo;

/**
 * @author zhiyuan
 * @date 2018/9/6
 */

public interface MainContract {


    interface View extends BaseView<Presenter> {

        /**
         * 显示loading
         * @param show true 显示， false 隐藏
         */
        void showLoading(boolean show);

        /**
         * 设置用户数据
         * @param userData 用户数据
         */
        void setUserData(@NonNull UserInfoBean userData);

        /**
         * 设置设置光管数据
         * @param settingInfo 设置数据
         */
        void setSettingData(@NonNull UserSettingInfo settingInfo);

        void showToast(@NonNull String msg);
    }

    interface Presenter {

        /**
         * 加载用户数据
         */
        void loadUserData();

        /**
         * 加载用户设置数据
         */
        void loadSettingData();

        void onUserClickChangeUserType(@UserType int newType);
    }
}
