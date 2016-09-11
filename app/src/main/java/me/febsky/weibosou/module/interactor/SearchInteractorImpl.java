package me.febsky.weibosou.module.interactor;

import me.febsky.weibosou.common.Api;
import me.febsky.weibosou.common.RequestCallback;
import me.febsky.weibosou.utils.VolleyHelper;

/**
 * Author: liuqiang
 * Date: 2016-08-30
 * Time: 11:40
 * Description: 微博用户搜索列表
 */
public class SearchInteractorImpl implements SearchInteractor<String> {

    @Override
    public void loadMoreData(final RequestCallback<String> callback, String keyword, int page) {
        VolleyHelper.requestJsonString(Api.requestSearchPage(keyword, page), callback);
    }

}
