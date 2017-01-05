package me.febsky.weibosou.module.interactor;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
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
    public void requestLogin(final RequestCallback<String> callback, String userName, String pwd) {

        //注册
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

        //修改
//        AVUser user = AVUser.getCurrentUser();
//        user.setEmail("niyingxunzong@163.com");
//        user.saveInBackground();

        //登陆
        AVUser.logInInBackground(userName, pwd, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if (e == null) {
                    callback.requestSuccess("");
                } else {
                    callback.requestError(e);
                }
            }
        });

    }
}
