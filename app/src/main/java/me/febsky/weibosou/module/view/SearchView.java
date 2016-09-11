package me.febsky.weibosou.module.view;

import java.util.List;

import me.febsky.weibosou.entity.UserPhotoEntity;
import me.febsky.weibosou.entity.WeiBoUserEntity;

/**
 * Author: liuqiang
 * Date: 2016-09-11
 * Time: 15:45
 * Des: 搜索
 */
public interface SearchView extends BaseView {

    /**
     * @param userEntities 数据集合
     * @param loadType      //加载的状态，是刷新还是加载更多，成功没有
     */
    void updateUserList(List<WeiBoUserEntity> userEntities, int loadType);

    /**
     * 没有更多数据了
     */
    void showNoneData();
}
