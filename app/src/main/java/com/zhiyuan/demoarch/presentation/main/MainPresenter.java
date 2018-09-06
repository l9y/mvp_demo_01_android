package com.zhiyuan.demoarch.presentation.main;

import com.zhiyuan.demoarch.base.ICallback;
import com.zhiyuan.demoarch.domain.setting.SettingModelImpl;
import com.zhiyuan.demoarch.domain.user.UserModelImpl;
import com.zhiyuan.demoarch.utils.AppUtils;
import com.zhiyuan.demoarch.utils.LogUtils;
import com.zhiyuan.demoarch.vo.main.UserInfoBean;
import com.zhiyuan.demoarch.vo.main.UserSettingInfo;

/**
 * @author zhiyuan
 * @date 2018/9/6
 */

public class MainPresenter implements MainContract.Presenter {
    public static final String TAG = "MainPresenter";

    private MainContract.View mView;

    private boolean mUserDataLoading, mUserSettingLoading;

    public MainPresenter(MainContract.View view) {
        mView = view;
        view.setPresenter(this);
    }

    @Override
    public void loadUserData() {
        if (mUserDataLoading) {
            LogUtils.i(TAG, "正在加载用户数据，忽略此次加载");
            return;
        }
        mUserDataLoading = true;
        updateViewLoading();
        UserModelImpl.getInstance().loadUserData(new ICallback<UserInfoBean>() {
            @Override
            public void onSucceed(UserInfoBean data) {
                if (AppUtils.isViewFinishing(mView)) {
                    return;
                }
                mUserDataLoading = false;
                mView.setUserData(data);
                updateViewLoading();
            }

            @Override
            public void onError(String msg) {
                if (AppUtils.isViewFinishing(mView)) {
                    return;
                }
                mUserDataLoading = false;
                mView.showToast(msg);
                updateViewLoading();
            }
        });
    }

    @Override
    public void loadSettingData() {
        if (mUserSettingLoading) {
            LogUtils.i(TAG, "正在加载设置数据，忽略此次加载");
            return;
        }
        mUserSettingLoading = true;
        updateViewLoading();
        new SettingModelImpl().loadSettingData(new ICallback<UserSettingInfo>() {
            @Override
            public void onSucceed(UserSettingInfo data) {
                if (AppUtils.isViewFinishing(mView)) {
                    return;
                }
                mUserSettingLoading = false;
                mView.setSettingData(data);
                updateViewLoading();
            }

            @Override
            public void onError(String msg) {
                if (AppUtils.isViewFinishing(mView)) {
                    return;
                }
                mUserSettingLoading = false;
                mView.showToast(msg);
                updateViewLoading();
            }
        });
    }

    @Override
    public void onUserClickChangeUserType(int newType) {
        mView.showLoading(true);
        UserModelImpl.getInstance().changeUserType(newType, new ICallback<Void>() {
            @Override
            public void onSucceed(Void data) {
                if (AppUtils.isViewFinishing(mView)) {
                    return;
                }
                //更改用户成功，需要刷新用户数据
                loadUserData();
                loadSettingData();
            }

            @Override
            public void onError(String msg) {
                if (AppUtils.isViewFinishing(mView)) {
                    return;
                }
                mView.showLoading(false);
            }
        });
    }

    private void updateViewLoading() {
        mView.showLoading(mUserSettingLoading || mUserDataLoading);
    }
}
