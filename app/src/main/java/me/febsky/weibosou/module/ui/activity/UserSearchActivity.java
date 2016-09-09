package me.febsky.weibosou.module.ui.activity;

import android.os.Bundle;

import butterknife.OnClick;
import me.febsky.weibosou.R;
import me.febsky.weibosou.annotation.InjectContentView;
import me.febsky.weibosou.module.ui.BaseActivity;

@InjectContentView(R.layout.activity_user_search)
public class UserSearchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @OnClick(R.id.iv_back_btn)
    void backout() {
        finish();
    }
}
