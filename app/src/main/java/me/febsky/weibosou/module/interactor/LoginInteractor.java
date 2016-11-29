package me.febsky.weibosou.module.interactor;

import me.febsky.weibosou.common.RequestCallback;

/**
 * Author: liuqiang
 * Date: 2016-11-24
 * Time: 11:40
 * Description: 用户登陆
 */
public interface LoginInteractor<T> {

    void requestLogin(RequestCallback<String> callback, String userName, String pwd);
}
