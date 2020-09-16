package me.febsky.weibosou;

/**
 * Author: Crazy Elephant
 * Date: 2020-09-16
 * Time: 11:35
 * Description:
 */
class TestBean {

    /**
     * is_main_land : 1
     * mfw_hotel_id : 0
     * hotel_name : name,name,name
     * ota_hotel_id : 530342
     * check_in : 20190818
     * check_out : 20190819
     * room_num : 1
     * person_num : 1
     * xc_mdd_range : 1
     * user_name : 18813012472
     * uid : M3353090635
     */

    private int is_main_land;
    private String mfw_hotel_id;
    private String hotel_name;
    private String ota_hotel_id;
    private String check_in;
    private String check_out;
    private int room_num;
    private int person_num;
    private int xc_mdd_range;
    private long user_name;
    private String uid;

    public int getIs_main_land() {
        return is_main_land;
    }

    public void setIs_main_land(int is_main_land) {
        this.is_main_land = is_main_land;
    }

    public String getMfw_hotel_id() {
        return mfw_hotel_id;
    }

    public void setMfw_hotel_id(String mfw_hotel_id) {
        this.mfw_hotel_id = mfw_hotel_id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getOta_hotel_id() {
        return ota_hotel_id;
    }

    public void setOta_hotel_id(String ota_hotel_id) {
        this.ota_hotel_id = ota_hotel_id;
    }

    public String getCheck_in() {
        return check_in;
    }

    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }

    public String getCheck_out() {
        return check_out;
    }

    public void setCheck_out(String check_out) {
        this.check_out = check_out;
    }

    public int getRoom_num() {
        return room_num;
    }

    public void setRoom_num(int room_num) {
        this.room_num = room_num;
    }

    public int getPerson_num() {
        return person_num;
    }

    public void setPerson_num(int person_num) {
        this.person_num = person_num;
    }

    public int getXc_mdd_range() {
        return xc_mdd_range;
    }

    public void setXc_mdd_range(int xc_mdd_range) {
        this.xc_mdd_range = xc_mdd_range;
    }

    public long getUser_name() {
        return user_name;
    }

    public void setUser_name(long user_name) {
        this.user_name = user_name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "is_main_land=" + is_main_land +
                ", mfw_hotel_id='" + mfw_hotel_id + '\'' +
                ", hotel_name='" + hotel_name + '\'' +
                ", ota_hotel_id='" + ota_hotel_id + '\'' +
                ", check_in='" + check_in + '\'' +
                ", check_out='" + check_out + '\'' +
                ", room_num=" + room_num +
                ", person_num=" + person_num +
                ", xc_mdd_range=" + xc_mdd_range +
                ", user_name=" + user_name +
                ", uid='" + uid + '\'' +
                '}';
    }
}
