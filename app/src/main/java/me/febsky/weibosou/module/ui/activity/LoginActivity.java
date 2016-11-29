package me.febsky.weibosou.module.ui.activity;

import android.os.Bundle;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.OnClick;
import me.febsky.weibosou.R;
import me.febsky.weibosou.annotation.InjectContentView;
import me.febsky.weibosou.module.presenter.LoginPresenter;
import me.febsky.weibosou.module.presenter.LoginPresenterImpl;
import me.febsky.weibosou.module.ui.BaseActivity;
import me.febsky.weibosou.module.view.LoginView;

@InjectContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements LoginView {

    private LoginPresenter mPresenter;

    @Bind(R.id.edit_text_username)
    EditText userNameEt;
    @Bind(R.id.edit_text_password)
    EditText pwdEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new LoginPresenterImpl(this);
    }

    @OnClick(R.id.tv_close)
    void goBack() {
        finish();
    }

    @OnClick(R.id.login_button)
    void onLogin() {
        mPresenter.login(userNameEt.getText().toString(), pwdEt.getText().toString());
    }

    @Override
    public void updateView(int loginCode) {

    }
}
