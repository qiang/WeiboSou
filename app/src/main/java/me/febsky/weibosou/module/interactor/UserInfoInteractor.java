package me.febsky.weibosou.module.interactor;

import com.avos.avoscloud.AVUser;

/**
 * Author: liuqiang
 * Date: 2017-01-05
 * Time: 11:01
 * Description:
 */
public interface UserInfoInteractor {
    boolean isLogin();

    AVUser getUser();
}
