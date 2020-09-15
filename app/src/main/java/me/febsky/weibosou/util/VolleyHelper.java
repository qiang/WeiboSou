package me.febsky.weibosou.util;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.reflect.TypeToken;

import me.febsky.weibosou.common.ApiResponse;
import me.febsky.weibosou.common.GsonRequest;
import me.febsky.weibosou.common.RequestCallback;

/**
 * Author:  liuqiang
 * Date:    2015/3/20.
 * Description: 简化volley操作
 * 本来感觉volley是比较小巧的一个库，后来感觉不太好用
 * 还不如我之前用自己封装的okhttp呢，
 * 2020 //
 */
public class VolleyHelper {

    private RequestQueue requestQueue = null;

    private static volatile VolleyHelper instance = null;

    private VolleyHelper() {
    }

    public static VolleyHelper getInstance() {
        if (null == instance) {
            synchronized (VolleyHelper.class) {
                if (null == instance) {
                    instance = new VolleyHelper();
                }
            }
        }
        return instance;
    }

    /**
     * init volley helper
     *   https://developer.android.google.cn/training/volley/requestqueue#network
     * @param context
     */
    public void init(Context context) {
        //传入context 主要为了处理cache  的文件目录
        //主线程和子线程切换用的是 Looper.mainLooper();
        requestQueue = Volley.newRequestQueue(context);
    }

    /**
     * get request queue
     *
     * @return
     */
    public RequestQueue getRequestQueue() {
        if (null != requestQueue) {
            return requestQueue;
        } else {
            throw new IllegalArgumentException("RequestQueue is not initialized.");
        }
    }

    /**
     * 用于本项目，因为本项目数据是抓（偷）的别人的不能封装太过
     *
     * @param url
     * @param callback
     */
    public static void requestJsonString(String url, final RequestCallback<String> callback) {
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.requestSuccess(response);
                        callback.requestComplete();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.requestError(error);
                        callback.requestComplete();
                    }
                });
        stringRequest.setShouldCache(true);

        VolleyHelper.getInstance().getRequestQueue().add(stringRequest);
    }

    /**
     * 我的设计的理想化的volley请求，未测试能不能用
     *
     * @param tClass
     * @param url
     * @param callback
     * @param <T>
     */
    public static <T> void requestJsonBean(Class<T> tClass, String url, final RequestCallback<T> callback) {

        GsonRequest<ApiResponse<T>> gsonRequest = new GsonRequest<>(
                url, null,
                new TypeToken<ApiResponse<T>>() {
                }.getType(),
                new Response.Listener<ApiResponse<T>>() {
                    @Override
                    public void onResponse(ApiResponse<T> response) {
                        callback.requestSuccess(response.getData());
                        callback.requestComplete();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.requestError(error);
                        callback.requestComplete();
                    }
                });
        gsonRequest.setShouldCache(true);

        VolleyHelper.getInstance().getRequestQueue().add(gsonRequest);
    }

}
