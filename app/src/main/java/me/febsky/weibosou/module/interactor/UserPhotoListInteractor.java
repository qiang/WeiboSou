package me.febsky.weibosou.module.interactor;

import me.febsky.weibosou.common.RequestCallback;

/**
 * Author: liuqiang
 * Date: 2016-08-30
 * Time: 11:40
 * Description: 微博用户缩略图列表
 */
public interface UserPhotoListInteractor<T> {

    void refreshData(RequestCallback<T> callback, String uid, String fid, String lcardid);

    void loadMoreData(RequestCallback<T> callback, String uid, String fid, String since_id, String lcardid);

    void requestFid(RequestCallback<T> callback, String uid, String lcardid);
}
