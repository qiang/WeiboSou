package me.febsky.weibosou.module.interactor;

import me.febsky.weibosou.common.Api;
import me.febsky.weibosou.common.RequestCallback;
import me.febsky.weibosou.utils.VolleyHelper;

/**
 * Author: liuqiang
 * Date: 2016-08-30
 * Time: 11:40
 * Description: 微博用户缩略图列表
 */
public class UserPhotoListInteractorImpl implements UserPhotoListInteractor<String> {

    @Override
    public void refreshData(final RequestCallback<String> callback, String uid, String fid, String lcardid) {
        VolleyHelper.requestJsonString(
                Api.requestUserPhotoFistPage(uid, fid, lcardid),
                callback);
    }

    @Override
    public void loadMoreData(final RequestCallback<String> callback, String uid, String fid, String since_id, String lcardid) {
        VolleyHelper.requestJsonString(Api.requestUserPhotoMorePage(uid, fid, since_id, lcardid), callback);
    }

    @Override
    public void requestFid(final RequestCallback<String> callback, String uid, String lcardid) {   //Api.requestFid(uid)
        VolleyHelper.requestJsonString(
                Api.requestFid(uid, lcardid),
                callback);
    }
}
