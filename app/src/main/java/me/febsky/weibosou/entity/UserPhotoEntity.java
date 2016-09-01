package me.febsky.weibosou.entity;

/**
 * Author: liuqiang
 * Date: 2016-08-18
 * Time: 16:01
 * 微博用户缩略图信息
 */
public class UserPhotoEntity extends BaseEntity {


    private String pic_id;
    private String pic_small;
    private String pic_big;
    private String pic_middle;

    public String getPic_id() {
        return pic_id;
    }

    public void setPic_id(String pic_id) {
        this.pic_id = pic_id;
    }

    public String getPic_small() {
        return pic_small;
    }

    public void setPic_small(String pic_small) {
        this.pic_small = pic_small;
    }

    public String getPic_big() {
        return pic_big;
    }

    public void setPic_big(String pic_big) {
        this.pic_big = pic_big;
    }

    public String getPic_middle() {
        return pic_middle;
    }

    public void setPic_middle(String pic_middle) {
        this.pic_middle = pic_middle;
    }
}
