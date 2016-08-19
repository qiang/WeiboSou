package me.febsky.weibosou.module.view;

/**
 * Author: liuqiang
 * Date: 2016-08-16
 * Time: 11:22
 * Description:  主界面的tab页面切换主界面的tab页面切换
 */
public interface MainTabView extends BaseView {

    void showHomeFragment();

    void showCollectionFragment();

    void showUserFragment();
}
