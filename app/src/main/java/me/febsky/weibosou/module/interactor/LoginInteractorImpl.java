package me.febsky.weibosou.module.interactor;

import me.febsky.weibosou.common.Api;
import me.febsky.weibosou.common.RequestCallback;
import me.febsky.weibosou.module.presenter.LoginPresenter;
import me.febsky.weibosou.utils.VolleyHelper;

/**
 * Author: liuqiang
 * Date: 2016-11-24
 * Time: 11:40
 * Description: 用户登陆
 */
public class LoginInteractorImpl implements LoginInteractor<String> {


    @Override
    public void requestLogin(RequestCallback<String> callback, String userName, String pwd) {
//        VolleyHelper.requestJsonString(
//                Api.requestFid(uid, lcardid),
//                callback);
    }
}
