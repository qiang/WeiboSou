package me.febsky.weibosou.module.view;

/**
 * Author: liuqiang
 * Time: 2016/11/23 18:46
 * Description: 登陆的View
 */
public interface LoginView extends BaseView {


    /**
     * @param loginCode 登陆的返回结果码 成功失败或者其他
     */
    void updateView(int loginCode);
}
