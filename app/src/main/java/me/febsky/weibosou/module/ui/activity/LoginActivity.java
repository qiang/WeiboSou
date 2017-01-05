package me.febsky.weibosou.module.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnTextChanged;
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
    @Bind(R.id.login_button)
    Button loginButton;

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

    @OnTextChanged(value = R.id.edit_text_password, callback = OnTextChanged.Callback.TEXT_CHANGED)
    void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!TextUtils.isEmpty(userNameEt.getText().toString()) && !TextUtils.isEmpty(s.toString())) {
            loginButton.setEnabled(true);
        } else {
            loginButton.setEnabled(false);
        }
    }

    @Override
    public void onLoginSuccess() {
        setResult(RESULT_OK, null);
        finish();
    }

    @Override
    public void onLoginFailed() {
        Toast.makeText(mContext, "用户名或密码错误", Toast.LENGTH_SHORT).show();
    }
}
