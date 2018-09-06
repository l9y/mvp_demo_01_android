package com.zhiyuan.demoarch.domain.user;

import android.support.annotation.NonNull;

import com.zhiyuan.demoarch.base.ICallback;
import com.zhiyuan.demoarch.constants.UserType;
import com.zhiyuan.demoarch.vo.main.UserInfoBean;

/**
 * @author zhiyuan
 * @date 2018/9/6
 */

public interface IUserModel {


    void changeUserType(@UserType int newType, @NonNull ICallback<Void> callback);

    void loadUserData(@NonNull ICallback<UserInfoBean> callback);
}
