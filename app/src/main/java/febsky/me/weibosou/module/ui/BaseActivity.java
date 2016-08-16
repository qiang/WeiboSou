package febsky.me.weibosou.module.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import butterknife.ButterKnife;
import febsky.me.weibosou.App;
import febsky.me.weibosou.annotation.InjectContentView;
import febsky.me.weibosou.module.view.BaseView;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    /**
     * Log tag
     */
    protected static String LOG_TAG = null;

    public Context mContext;
    public App mApplication;
    /**
     * 布局的id
     */
    protected int mContentViewId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LOG_TAG = this.getClass().getSimpleName();

        mContext = getApplicationContext();
        mApplication = (App) mContext;

        if (getClass().isAnnotationPresent(InjectContentView.class)) {
            InjectContentView annotation = getClass()
                    .getAnnotation(InjectContentView.class);
            mContentViewId = annotation.value();
            setContentView(mContentViewId);
        } else {
            throw new RuntimeException(
                    "Class must add annotations of ActivityFragmentInitParams.class");
        }

        if (isBindEventBus()) {
            ButterKnife.bind(this);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBindEventBus()) {
            ButterKnife.unbind(this);
        }
    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    /**
     * 向子类暴漏是不是加载EventBus库
     *
     * @return
     */
    protected boolean isBindEventBus() {
        return true;
    }
}
