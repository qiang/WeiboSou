package me.febsky.weibosou.module.ui.fragment.bottom;

import android.content.Intent;

import butterknife.OnClick;
import me.febsky.weibosou.R;
import me.febsky.weibosou.annotation.InjectContentView;
import me.febsky.weibosou.module.ui.BaseFragment;
import me.febsky.weibosou.module.ui.activity.LoginActivity;

/**
 * Author: liuqiang
 * Date: 2016-08-16
 * Time: 14:55
 */
@InjectContentView(R.layout.fragment_user)
public class UserInfoFragment extends BaseFragment {


    @Override
    protected void initData() {

    }

    @OnClick(R.id.tv_login_btn)
    void openLoginPage() {
        startActivity(new Intent(mContext, LoginActivity.class));
    }
}
