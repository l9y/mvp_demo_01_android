package com.zhiyuan.demoarch.data.user;

import android.support.annotation.WorkerThread;

import com.zhiyuan.demoarch.vo.main.UserInfoBean;

/**
 * @author zhiyuan
 * @date 2018/9/6
 */

public interface IUserDataSource {

    @WorkerThread
    UserInfoBean loadUserInfo();
}
