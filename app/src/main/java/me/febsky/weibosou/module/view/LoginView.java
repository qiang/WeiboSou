package me.febsky.weibosou.module.view;

/**
 * Author: liuqiang
 * Time: 2016/11/23 18:46
 * Description: 登陆的View
 */
public interface LoginView extends BaseView {

    void onLoginSuccess();

    void onLoginFailed();
}
