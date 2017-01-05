package me.febsky.weibosou.module.presenter;

import me.febsky.weibosou.module.interactor.LoginInteractor;
import me.febsky.weibosou.module.interactor.LoginInteractorImpl;
import me.febsky.weibosou.module.view.LoginView;

/**
 * Author: liuqiang
 * Time: 2016/11/23 18:50
 * Description:
 */
public class LoginPresenterImpl extends BasePresenterImpl<LoginView, String> implements LoginPresenter {

    private LoginInteractor<String> mInteractor;

    public LoginPresenterImpl(LoginView view) {
        super(view);
        mInteractor = new LoginInteractorImpl();
    }

    @Override
    public void requestSuccess(String data) {
        mView.onLoginSuccess();
    }


    @Override
    public void login(String userName, String pwd) {
        mInteractor.requestLogin(this, userName, pwd);
    }

    @Override
    public void requestError(Exception e) {
        super.requestError(e);
        mView.onLoginFailed();
    }

}
