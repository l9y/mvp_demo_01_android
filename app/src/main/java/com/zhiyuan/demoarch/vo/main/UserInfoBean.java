package com.zhiyuan.demoarch.vo.main;

import com.zhiyuan.demoarch.constants.UserType;

import java.util.List;
/**
 * 服务器返回的用户信息
 * @author zhiyuan
 * @date 2018/9/6
 */

public class UserInfoBean {

    public String name;

    @UserType
    public int currentUserType;

    @UserType
    public List<Integer> userTypes;

}
