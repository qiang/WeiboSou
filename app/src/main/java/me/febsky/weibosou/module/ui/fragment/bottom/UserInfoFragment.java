package me.febsky.weibosou.module.ui.fragment.bottom;

import android.content.Intent;

import butterknife.OnClick;
import me.febsky.weibosou.R;
import me.febsky.weibosou.annotation.InjectContentView;
import me.febsky.weibosou.module.presenter.GalleryListPresenter;
import me.febsky.weibosou.module.presenter.UserInfoPresenter;
import me.febsky.weibosou.module.presenter.UserInfoPresenterImpl;
import me.febsky.weibosou.module.ui.BaseFragment;
import me.febsky.weibosou.module.ui.activity.LoginActivity;
import me.febsky.weibosou.module.view.UserInfoView;

/**
 * Author: liuqiang
 * Date: 2016-08-16
 * Time: 14:55
 */
@InjectContentView(R.layout.fragment_user)
public class UserInfoFragment extends BaseFragment implements UserInfoView {

    private UserInfoPresenter mPresenter;

    @Override
    protected void initData() {
        mPresenter = new UserInfoPresenterImpl(this);
    }

    @Override
    public void openLoginPage() {
        startActivity(new Intent(mContext, LoginActivity.class));
    }


    @OnClick(R.id.iv_photo_summary)
    void clickLoginBtn() {
        if (mPresenter.isLogin()) {

        } else {
            mPresenter.openLoginPage();
        }
    }
}
