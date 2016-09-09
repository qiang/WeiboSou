package me.febsky.weibosou.module.presenter;

import me.febsky.weibosou.module.view.UserPhotoDetailView;

/**
 * Author: liuqiang
 * Date: 2016-08-30
 * Time: 12:53
 * Description:
 */
public class UserPhotoDetailPresenterImpl extends BasePresenterImpl<UserPhotoDetailView, String> implements UserPhotoDetailPresenter {

    public UserPhotoDetailPresenterImpl(UserPhotoDetailView view) {
        super(view);
        initViewPager();
    }

    @Override
    public void requestSuccess(String data) {

    }

    @Override
    public void initViewPager() {
        mView.initViewPager();
    }
}
