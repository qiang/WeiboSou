package febsky.me.weibosou.module.ui.fragment;

import febsky.me.weibosou.R;
import febsky.me.weibosou.annotation.InjectContentView;
import febsky.me.weibosou.module.ui.LazyBaseFragment;
import febsky.me.weibosou.utils.Log;

/**
 * Author: liuqiang
 * Date: 2016-08-16
 * Time: 16:18
 * Des: 首页显示的图片，恩，无限加载模式
 * 这里面都是图集，每个图集是一个人的
 */
@InjectContentView(R.layout.fragment_page_gallery)
public class GalleryPageFragment extends LazyBaseFragment {
    @Override
    public void lazyInitData() {
        Log.i("Q_M:", "GalleryPageFragment 加载数据");
    }

    @Override
    public String toString() {
        return "推荐";
    }
}
