package com.zhiyuan.demoarch.data.user;

import com.zhiyuan.demoarch.constants.UserType;
import com.zhiyuan.demoarch.vo.main.UserInfoBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhiyuan
 * @date 2018/9/6
 */

public class UserDataSourceImpl implements IUserDataSource {

    /**
     * 假数据源
     */
    public static int fakeUserType = UserType.ADMIN;

    @Override
    public UserInfoBean loadUserInfo() {
        UserInfoBean bean = new UserInfoBean();
        bean.name = "二狗子";
        bean.currentUserType = fakeUserType;
        bean.userTypes = new ArrayList<>();
        bean.userTypes.add(UserType.ADMIN);
        bean.userTypes.add(UserType.ORG);
        return bean;
    }
}
