package me.febsky.weibosou.module.interactor;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import me.febsky.weibosou.common.Api;
import me.febsky.weibosou.common.RequestCallback;
import me.febsky.weibosou.utils.Log;
import me.febsky.weibosou.utils.VolleyHelper;

/**
 * Author: liuqiang
 * Date: 2016-08-30
 * Time: 11:40
 * Description: 微博用户缩略图列表
 */
public class UserPhotoListInteractorImpl implements UserPhotoListInteractor<String> {

    @Override
    public void refreshData(final RequestCallback<String> callback, String uid, String fid) {
        StringRequest stringRequest = new StringRequest(Api.requestUserPhotoFistPage(uid, fid),
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

    @Override
    public void loadMoreData(RequestCallback<String> callback, String fid, String uid, String since_id) {

    }

    @Override
    public void requestFid(final RequestCallback<String> callback, String uid) {
        StringRequest stringRequest = new StringRequest(Api.requestFid(uid),
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
