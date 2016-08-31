package me.febsky.weibosou.module.interactor;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import me.febsky.weibosou.common.Api;
import me.febsky.weibosou.common.RequestCallback;
import me.febsky.weibosou.utils.VolleyHelper;

/**
 * Author: liuqiang
 * Date: 2016-08-18
 * Time: 17:01
 * 首页微博用户头像加载
 */
public class GalleryListInteractorImpl implements GalleryListInteractor<String> {
    @Override
    public void requestGalleryList(final RequestCallback<String> callback, int page) {
        StringRequest stringRequest = new StringRequest(Api.requestUserList(page),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.requestSuccess(response);
                        callback.requestComplete();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.requestError(error.toString());
                callback.requestComplete();
            }
        });
        stringRequest.setShouldCache(true);

        VolleyHelper.getInstance().getRequestQueue().add(stringRequest);
    }
}
