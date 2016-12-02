package me.febsky.weibosou.module.interactor;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;

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


//        AVUser user = new AVUser();// 新建 AVUser 对象实例
//        user.setUsername(userName);// 设置用户名
//        user.setPassword(pwd);// 设置密码
//        user.setMobilePhoneNumber(userName);
////        user.setEmail(email);//设置邮箱
//        user.put("pwd", pwd);
//        user.signUpInBackground(new SignUpCallback() {
//            @Override
//            public void done(AVException e) {
//
//            }
//        });
        AVUser user = AVUser.getCurrentUser();
        user.setEmail("niyingxunzong@163.com");
        user.saveInBackground();

    }
}
