package me.febsky.weibosou.module.presenter;

/**
 * Author: liuqiang
 * Date: 2016-08-30
 * Time: 12:53
 * Description:
 */
public interface UserPhotoListPresenter {

    void refreshData(String uid);

    void loadMoreData();
}
