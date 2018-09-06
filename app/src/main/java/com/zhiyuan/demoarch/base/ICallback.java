package com.zhiyuan.demoarch.base;

/**
 * @author zhiyuan
 * @date 2018/9/6
 */

public interface ICallback<T> {

    void onSucceed(T data);

    void onError(String msg);
}
