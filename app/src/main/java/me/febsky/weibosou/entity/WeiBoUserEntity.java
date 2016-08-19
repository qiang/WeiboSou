package me.febsky.weibosou.entity;

/**
 * Author: liuqiang
 * Date: 2016-08-18
 * Time: 16:01
 */
public class WeiBoUserEntity extends BaseEntity {

    private int id;   //uid
    private String screen_name;   //貌似是昵称
    private String avatar_large;    //头像
    private int followers_count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public String getAvatar_large() {
        return avatar_large;
    }

    public void setAvatar_large(String avatar_large) {
        this.avatar_large = avatar_large;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }
}
