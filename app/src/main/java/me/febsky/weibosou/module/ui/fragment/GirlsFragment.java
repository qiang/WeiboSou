package me.febsky.weibosou.module.ui.fragment;

import me.febsky.weibosou.R;
import me.febsky.weibosou.annotation.InjectContentView;
import me.febsky.weibosou.module.ui.LazyBaseFragment;
import me.febsky.weibosou.util.Log;

/**
 * Author: liuqiang
 * Date: 2016-09-29
 * Time: 16:19
 * Des: 美女
 */
@InjectContentView(R.layout.fragment_page_girls)
public class GirlsFragment extends LazyBaseFragment {

    @Override
    public void lazyInitData() {
        Log.i("Q_M:", "GirlsFragment 加载数据");
    }

    @Override
    public String toString() {
        return "美女";
    }
}
