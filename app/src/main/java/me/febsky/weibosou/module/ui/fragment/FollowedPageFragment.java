package me.febsky.weibosou.module.ui.fragment;

import me.febsky.weibosou.R;
import me.febsky.weibosou.annotation.InjectContentView;
import me.febsky.weibosou.module.ui.LazyBaseFragment;
import me.febsky.weibosou.utils.Log;

/**
 * Author: liuqiang
 * Date: 2016-08-16
 * Time: 16:19
 * Des: 关注的人
 */
@InjectContentView(R.layout.fragment_page_followed)
public class FollowedPageFragment extends LazyBaseFragment {

    @Override
    public void lazyInitData() {
        Log.i("Q_M:","FollowedPageFragment 加载数据");
    }

    @Override
    public String toString() {
        return "关注";
    }
}
