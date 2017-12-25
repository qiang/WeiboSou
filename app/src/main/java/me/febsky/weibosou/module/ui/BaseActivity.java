package me.febsky.weibosou.module.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


import java.util.List;

import butterknife.ButterKnife;
import me.febsky.weibosou.App;
import me.febsky.weibosou.annotation.InjectContentView;
import me.febsky.weibosou.module.view.BaseView;
import me.febsky.weibosou.widget.LoadingDialog;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    /**
     * Log tag
     */
    protected static String LOG_TAG = null;


    public static int RESULT_OK = 1;
    public static int RESULT_CANCEL = 0;
    public static int RESULT_ERROR = -1;

    public Context mContext;
    public App mApplication;
    /**
     * 布局的id
     */
    protected int mContentViewId;

    /**
     * 当前activity是否可见
     */
    protected boolean isVisible = false;

    protected LoadingDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LOG_TAG = this.getClass().getSimpleName();

        mContext = getApplicationContext();
        mApplication = (App) mContext;
        mDialog = new LoadingDialog(this);

        if (getClass().isAnnotationPresent(InjectContentView.class)) {
            InjectContentView annotation = getClass()
                    .getAnnotation(InjectContentView.class);
            mContentViewId = annotation.value();
            setContentView(mContentViewId);
        } else {
            throw new RuntimeException(
                    "Class must add annotations of ActivityFragmentInitParams.class");
        }

        if (isBindButterKnife()) {
            ButterKnife.bind(this);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        isVisible = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isVisible = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBindButterKnife()) {
//            ButterKnife.unbind(this);
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        List<Fragment> fragmentList = fragmentManager.getFragments();
//        if (fragmentList != null) {
//            for (Fragment fragment : fragmentList) {
//                fragment.onActivityResult(requestCode, resultCode, data);
//            }
//        }
//    }

    @Override
    public void finish() {
        onActivityFinish();
        super.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            finish();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    /**
     * 在activity finish之前做一些操作
     */
    protected void onActivityFinish() {

    }

    @Override
    public void showProgressDialog(String msg) {
    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void showError(String msg) {

    }

    /**
     * 向子类暴漏是不是加载ButterKnife库
     *
     * @return
     */
    protected boolean isBindButterKnife() {
        return true;
    }

    /**
     * 向子类暴漏是不是加载EventBus库
     *
     * @return
     */
    protected boolean isBindEventBus() {
        return false;
    }


    protected void hideSoftInput(EditText view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
    }
}
