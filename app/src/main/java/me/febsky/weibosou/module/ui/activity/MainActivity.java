package me.febsky.weibosou.module.ui.activity;

import android.os.Bundle;
import android.widget.RadioGroup;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import me.febsky.weibosou.R;
import me.febsky.weibosou.annotation.InjectContentView;
import me.febsky.weibosou.module.presenter.MainTabPresenter;
import me.febsky.weibosou.module.presenter.MainTabPresenterImpl;
import me.febsky.weibosou.module.ui.BaseActivity;
import me.febsky.weibosou.module.ui.BaseFragment;
import me.febsky.weibosou.module.ui.fragment.CollectionFragment;
import me.febsky.weibosou.module.ui.fragment.HomeFragment;
import me.febsky.weibosou.module.ui.fragment.UserInfoFragment;
import me.febsky.weibosou.module.view.MainTabView;

/**
 * Author: liuqiang
 * Date: 2016-08-16
 * Time: 11:36
 * Description: 主界面的tab页面切换
 */
@InjectContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements MainTabView, RadioGroup.OnCheckedChangeListener {

    private MainTabPresenter mainTabPresenter;
    private Map<String, BaseFragment> fragmentMap;
    private BaseFragment currentFragment;

    @Bind(R.id.main_radio_group)
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentMap = new HashMap<>();
        mainTabPresenter = new MainTabPresenterImpl(this);
        showHomeFragment();
        initEvent();
    }

    private void initEvent() {
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void showHomeFragment() {
        currentFragment = fragmentMap.get("HomeFragment");
        if (currentFragment == null) {
            currentFragment = new HomeFragment();
            fragmentMap.put("HomeFragment", currentFragment);
        }
        beginTransaction();
    }

    @Override
    public void showCollectionFragment() {
        currentFragment = fragmentMap.get("CollectionFragment");
        if (currentFragment == null) {
            currentFragment = new CollectionFragment();
            fragmentMap.put("CollectionFragment", currentFragment);
        }
        beginTransaction();
    }

    @Override
    public void showUserFragment() {
        currentFragment = fragmentMap.get("UserInfoFragment");
        if (currentFragment == null) {
            currentFragment = new UserInfoFragment();
            fragmentMap.put("UserInfoFragment", currentFragment);
        }
        beginTransaction();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        mainTabPresenter.switchNavigation(checkedId);
    }

    /**
     * 辅助完成Fragment的切换
     */
    private void beginTransaction() {
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_content, currentFragment).commit();
    }
}
