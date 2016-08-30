package me.febsky.weibosou.module.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.febsky.weibosou.R;
import me.febsky.weibosou.annotation.InjectContentView;
import me.febsky.weibosou.module.ui.BaseActivity;

@InjectContentView(R.layout.activity_user_photo_list)
public class UserPhotoListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
