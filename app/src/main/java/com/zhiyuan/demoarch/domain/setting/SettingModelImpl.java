package com.zhiyuan.demoarch.domain.setting;

import com.zhiyuan.demoarch.base.ICallback;
import com.zhiyuan.demoarch.constants.UserType;
import com.zhiyuan.demoarch.domain.user.UserModelImpl;
import com.zhiyuan.demoarch.vo.main.UserInfoBean;
import com.zhiyuan.demoarch.vo.main.UserSettingInfo;

/**
 * @author zhiyuan
 * @date 2018/9/6
 */

public class SettingModelImpl implements ISettingModel {
    @Override
    public void loadSettingData(final ICallback<UserSettingInfo> callback) {
        UserModelImpl.getInstance().loadUserData(new ICallback<UserInfoBean>() {
            @Override
            public void onSucceed(UserInfoBean data) {
                if (data.currentUserType == UserType.ADMIN) {
                    callback.onSucceed(loadAdminSettingInfo());
                } else if (data.currentUserType == UserType.ORG) {
                    callback.onSucceed(loadOrgSettingInfo());
                } else if (data.currentUserType == UserType.NORMAL) {
                    callback.onSucceed(loadNormalSettingInfo());
                }
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }

    /**
     * 加载管理员配置
     * @return {@link UserSettingInfo}
     */
    private UserSettingInfo loadAdminSettingInfo() {
        UserSettingInfo info = new UserSettingInfo();
        info.titles = new String[]{"管理员条目1", "管理员条目2"};
        info.badges = new int[] {0, 1};
        return info;
    }

    /**
     * 加载机构用户配置
     * @return {@link UserInfoBean}
     */
    private UserSettingInfo loadOrgSettingInfo() {
        UserSettingInfo info = new UserSettingInfo();
        info.titles = new String[]{"机构条目1", "机构条目2", "机构条目3"};
        info.badges = new int[] {0, 1, 0};
        return info;
    }

    /**
     * 加载普通用户配置
     * @return {@link UserInfoBean}
     */
    private UserSettingInfo loadNormalSettingInfo() {
        UserSettingInfo info = new UserSettingInfo();
        info.titles = new String[]{"普通条目1", "普通条目2", "普通条目3"};
        info.badges = new int[] {0, 0, 1};
        return info;
    }
}
