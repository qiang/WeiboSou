package me.febsky.weibosou.module.interactor;

import me.febsky.weibosou.common.RequestCallback;

/**
 * Author: liuqiang
 * Date: 2016-08-18
 * Time: 17:01
 * 首页图片加载
 */
public interface GalleryListInteractor<T> {
    void requestGalleryList(RequestCallback<T> callback, int page);
}
