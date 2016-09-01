package me.febsky.weibosou.module;

import android.os.Bundle;
import android.os.PersistableBundle;

import me.febsky.weibosou.R;
import me.febsky.weibosou.annotation.InjectContentView;
import me.febsky.weibosou.module.ui.BaseActivity;

/**
 * Author: liuqiang
 * Date: 2016-08-15
 * Time: 16:22
 * 留一个空白的测试Activity
 */
@InjectContentView(R.layout.activity_test)
public class TestActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }
}
