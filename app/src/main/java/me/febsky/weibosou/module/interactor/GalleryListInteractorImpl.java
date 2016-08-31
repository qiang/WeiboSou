package me.febsky.weibosou.module.interactor;

import me.febsky.weibosou.common.Api;
import me.febsky.weibosou.common.RequestCallback;
import me.febsky.weibosou.utils.VolleyHelper;

/**
 * Author: liuqiang
 * Date: 2016-08-18
 * Time: 17:01
 * 首页微博用户头像加载
 */
public class GalleryListInteractorImpl implements GalleryListInteractor<String> {
    @Override
    public void requestGalleryList(final RequestCallback<String> callback, int page) {
        VolleyHelper.requestJsonString(
                Api.requestUserList(page),
                callback);
    }
}
