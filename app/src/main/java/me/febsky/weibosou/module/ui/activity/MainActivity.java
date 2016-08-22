package me.febsky.weibosou.module.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
    private FragmentManager fragmentManager;

    @Bind(R.id.main_radio_group)
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
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
        if (fragmentMap.get("HomeFragment") == null) {
            fragmentMap.put("HomeFragment", new HomeFragment());
        }
        switchToFragment(fragmentMap.get("HomeFragment"));
    }

    @Override
    public void showCollectionFragment() {
        if (fragmentMap.get("CollectionFragment") == null) {
            fragmentMap.put("CollectionFragment", new CollectionFragment());
        }
        switchToFragment(fragmentMap.get("CollectionFragment"));
    }

    @Override
    public void showUserFragment() {
        if (fragmentMap.get("UserInfoFragment") == null) {
            fragmentMap.put("UserInfoFragment", new UserInfoFragment());
        }
        switchToFragment(fragmentMap.get("UserInfoFragment"));
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        mainTabPresenter.switchNavigation(checkedId);
    }

    /**
     * 辅助完成Fragment的切换
     */
    private void switchToFragment(BaseFragment targetFragment) {
        FragmentTransaction transaction = fragmentManager
                .beginTransaction();
        if (currentFragment != null) {
            transaction
                    .hide(currentFragment);
        }
        if (!targetFragment.isAdded()) {
            transaction
                    .add(R.id.layout_content, targetFragment, "" + targetFragment.getClass().getSimpleName())
                    .commit();
        } else {
            transaction
                    .show(targetFragment)
                    .commit();
        }
        currentFragment = targetFragment;
    }
}
