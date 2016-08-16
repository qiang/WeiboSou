package febsky.me.weibosou.module.ui.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import butterknife.Bind;
import febsky.me.weibosou.R;
import febsky.me.weibosou.annotation.InjectContentView;
import febsky.me.weibosou.module.presenter.MainTabPresenter;
import febsky.me.weibosou.module.presenter.MainTabPresenterImpl;
import febsky.me.weibosou.module.ui.BaseActivity;
import febsky.me.weibosou.module.view.MainTabView;

/**
 * Author: liuqiang
 * Date: 2016-08-16
 * Time: 11:36
 * Description: 主界面的tab页面切换
 */
@InjectContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements MainTabView {

    private MainTabPresenter mainTabPresenter;

    @Bind(R.id.layout_content)
    FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainTabPresenter = new MainTabPresenterImpl(this);
    }

    @Override
    public void showHomeFragment() {

    }

    @Override
    public void showCollectionFragment() {

    }

    @Override
    public void showUserFragment() {

    }
}
