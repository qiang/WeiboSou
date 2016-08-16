package febsky.me.weibosou.module.presenter;

import febsky.me.weibosou.R;
import febsky.me.weibosou.module.view.MainTabView;

/**
 * Author: liuqiang
 * Date: 2016-08-16
 * Time: 11:25
 * Description: 主界面的tab页面切换
 */
public class MainTabPresenterImpl implements MainTabPresenter {

    private MainTabView mMainView;

    public MainTabPresenterImpl(MainTabView mMainView) {
        this.mMainView = mMainView;
    }

    @Override
    public void switchNavigation(int id) {

        switch (id) {
            case R.id.rb_home:
                mMainView.showHomeFragment();
                break;
            case R.id.rb_collection:
                mMainView.showCollectionFragment();
                break;
            case R.id.rb_user:
                mMainView.showUserFragment();
                break;
            default:
                mMainView.showHomeFragment();
                break;
        }
    }
}
