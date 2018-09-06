package com.zhiyuan.demoarch.domain.user;

import android.support.annotation.NonNull;

import com.zhiyuan.demoarch.base.ICallback;
import com.zhiyuan.demoarch.data.user.UserDataSourceImpl;
import com.zhiyuan.demoarch.utils.ThreadUtils;
import com.zhiyuan.demoarch.vo.main.UserInfoBean;

/**
 * @author zhiyuan
 * @date 2018/9/6
 */

public class UserModelImpl implements IUserModel {

    private static UserModelImpl sInstance;

    private UserModelImpl() {}

    public static IUserModel getInstance() {
        if (sInstance == null) {
            synchronized (UserModelImpl.class) {
                if (sInstance == null) {
                    sInstance = new UserModelImpl();
                }
            }
        }
        return sInstance;
    }


    @Override
    public void changeUserType(final int newType, @NonNull final ICallback<Void> callback) {
        //假装提交数据
        ThreadUtils.exec(new Runnable() {
            @Override
            public void run() {
                try {
                    //假装请求接口
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                UserDataSourceImpl.fakeUserType = newType;
                ThreadUtils.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSucceed(null);
                    }
                });
            }
        });
    }

    @Override
    public void loadUserData(@NonNull final ICallback<UserInfoBean> callback) {
        // 真实项目需要添加缓存来实现，这里只模拟从网络实现了

        ThreadUtils.exec(new Runnable() {
            @Override
            public void run() {
                try {
                    //假装加载数据
                    Thread.sleep(2000);
                    final UserInfoBean bean = new UserDataSourceImpl().loadUserInfo();
                    ThreadUtils.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSucceed(bean);
                        }
                    });
                } catch (final Exception e) {
                    e.printStackTrace();
                    ThreadUtils.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onError(e.getMessage());
                        }
                    });
                }
            }
        });
    }
}
