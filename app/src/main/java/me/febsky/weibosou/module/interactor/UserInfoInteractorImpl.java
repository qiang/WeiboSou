package me.febsky.weibosou.module.interactor;

import com.avos.avoscloud.AVUser;

/**
 * Author: liuqiang
 * Date: 2017-01-05
 * Time: 11:03
 * Description:
 */
public class UserInfoInteractorImpl implements UserInfoInteractor {

    @Override
    public boolean isLogin() {
        AVUser user = AVUser.getCurrentUser();
        return user != null;
    }

    public AVUser getUser() {
        return AVUser.getCurrentUser();
    }
}
