package me.febsky.weibosou;

import android.app.Application;

import me.febsky.weibosou.utils.VolleyHelper;

/**
 * Author: liuqiang
 * Date: 2016-08-15
 * Time: 15:07
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        VolleyHelper.getInstance().init(this);
    }
}