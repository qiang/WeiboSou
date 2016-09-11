package me.febsky.weibosou.module.interactor;

import me.febsky.weibosou.common.RequestCallback;

/**
 * Author: liuqiang
 * Date: 2016-09-11
 * Time: 11:40
 * Description: 微博用户搜索
 */
public interface SearchInteractor<T> {

    void loadMoreData(RequestCallback<T> callback, String keyword, int page);
}
