package com.zhiyuan.demoarch.domain.setting;

import com.zhiyuan.demoarch.base.ICallback;
import com.zhiyuan.demoarch.vo.main.UserInfoBean;
import com.zhiyuan.demoarch.vo.main.UserSettingInfo;

/**
 * @author zhiyuan
 * @date 2018/9/6
 */

public interface ISettingModel {

    void loadSettingData(ICallback<UserSettingInfo> callback);

}
