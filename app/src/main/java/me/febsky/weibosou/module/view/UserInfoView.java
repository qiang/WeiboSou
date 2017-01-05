package me.febsky.weibosou.module.view;

import com.avos.avoscloud.AVUser;

/**
 * Author: liuqiang
 * Date: 2017-01-03
 * Time: 19:20
 * Description: 对应UserInfoFragment
 */
public interface UserInfoView extends BaseView {

    void openLoginPage();

    void updateViews(AVUser user);
}
