package me.febsky.weibosou.module.view;

import java.util.List;

import me.febsky.weibosou.entity.UserPhotoEntity;

/**
 * Author: liuqiang
 * Date: 2016-08-18
 * Time: 15:23
 * Des: 用户的所有图片缩略图显示
 */
public interface UserPhotoListView extends BaseView {

    /**
     * @param photoEntities 数据集合
     * @param loadType      //加载的状态，是刷新还是加载更多，成功没有
     */
    void updatePhotoList(List<UserPhotoEntity> photoEntities, int loadType);

    /**
     * 没有更多数据了
     */
    void showNoneData();
}
