package me.febsky.weibosou.module.ui.activity;

import android.os.Bundle;

import butterknife.Bind;
import butterknife.OnClick;
import me.febsky.weibosou.R;
import me.febsky.weibosou.annotation.InjectContentView;
import me.febsky.weibosou.module.ui.BaseActivity;

@InjectContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.tv_close)
    void goBack() {
        finish();
    }
}
