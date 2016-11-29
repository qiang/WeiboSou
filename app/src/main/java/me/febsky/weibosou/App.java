package me.febsky.weibosou;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

import me.febsky.weibosou.common.PublicReference;
import me.febsky.weibosou.utils.VolleyHelper;

/**
 * Author: liuqiang
 * Date: 2016-08-15
 * Time: 15:07
 */
public class App extends Application {

    public PublicReference reference;

    @Override
    public void onCreate() {
        super.onCreate();
        VolleyHelper.getInstance().init(this);
        reference = new PublicReference();

        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this, "lMPLwn22KKuxQbpbeah547Be-gzGzoHsz", "Wqo9CV4i8n5Mqs43puQRwrAv");
    }


}
