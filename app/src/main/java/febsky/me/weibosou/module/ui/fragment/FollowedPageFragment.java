package febsky.me.weibosou.module.ui.fragment;

import febsky.me.weibosou.R;
import febsky.me.weibosou.annotation.InjectContentView;
import febsky.me.weibosou.module.ui.LazyBaseFragment;

/**
 * Author: liuqiang
 * Date: 2016-08-16
 * Time: 16:19
 * Des: 关注的人
 */
@InjectContentView(R.layout.fragment_page_followed)
public class FollowedPageFragment extends LazyBaseFragment {

    @Override
    public void lazyLoadData() {

    }

    @Override
    public String toString() {
        return "关注";
    }
}
