package me.febsky.weibosou.entity;

/**
 * Author: liuqiang
 * Date: 2016-08-18
 * Time: 16:01
 */
public class WeiBoUserEntity extends BaseEntity {

    private long id;   //uid
    private String screen_name;   //貌似是昵称
    private String avatar_large;    //头像
    private String profile_image_url;  //小图
    private int followers_count;
    private String desc1;
    private String desc2;

    private String lcardid;


    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    public String getDesc2() {
        return desc2;
    }

    public void setDesc2(String desc2) {
        this.desc2 = desc2;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }

    public String getLcardid() {
        return lcardid;
    }

    public void setLcardid(String lcardid) {
        this.lcardid = lcardid;
    }

    @Override
    public String toString() {
        return "WeiBoUserEntity{" +
                "id=" + id +
                ", screen_name='" + screen_name + '\'' +
                ", avatar_large='" + avatar_large + '\'' +
                ", profile_image_url='" + profile_image_url + '\'' +
                ", followers_count=" + followers_count +
                ", desc1='" + desc1 + '\'' +
                ", desc2='" + desc2 + '\'' +
                ", lcardid='" + lcardid + '\'' +
                '}';
    }
}
