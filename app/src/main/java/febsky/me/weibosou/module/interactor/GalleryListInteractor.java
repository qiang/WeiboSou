package febsky.me.weibosou.module.interactor;

import febsky.me.weibosou.common.RequestCallback;

/**
 * Author: liuqiang
 * Date: 2016-08-18
 * Time: 17:01
 * 首页图片加载
 */
public interface GalleryListInteractor<T> {
    void requestGalleryList(RequestCallback<T> callback, int page);
}
