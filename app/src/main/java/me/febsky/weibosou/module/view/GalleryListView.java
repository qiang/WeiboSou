package me.febsky.weibosou.module.view;

import java.util.List;

import me.febsky.weibosou.entity.WeiBoUserEntity;

/**
 * Author: liuqiang
 * Date: 2016-08-18
 * Time: 15:23
 * Des: 首页的列表 该显示啥呢。头像 还是图片
 * 最终决定还是搞微博的24小时热门的人吧
 */
public interface GalleryListView extends BaseView {

    /**
     * @param userEntityList 数据集合
     * @param loadType       //加载的状态，是刷新还是加载更多，成功没有
     */
    void updateGalleryList(List<WeiBoUserEntity> userEntityList, int loadType);
}
