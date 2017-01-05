package me.febsky.weibosou.module.presenter;

import me.febsky.weibosou.module.interactor.UserInfoInteractor;
import me.febsky.weibosou.module.interactor.UserInfoInteractorImpl;
import me.febsky.weibosou.module.view.UserInfoView;
import me.febsky.weibosou.module.view.UserPhotoDetailView;

/**
 * Author: liuqiang
 * Date: 2017-01-03
 * Time: 19:22
 * Description:
 */
public class UserInfoPresenterImpl extends BasePresenterImpl<UserInfoView, String> implements UserInfoPresenter {

    private UserInfoInteractor mInteractor;

    public UserInfoPresenterImpl(UserInfoView view) {
        super(view);
        mInteractor = new UserInfoInteractorImpl();
    }

    @Override
    public void openLoginPage() {
        mView.openLoginPage();
    }

    @Override
    public boolean isLogin() {
        return mInteractor.isLogin();
    }

    @Override
    public void requestSuccess(String data) {

    }
}
