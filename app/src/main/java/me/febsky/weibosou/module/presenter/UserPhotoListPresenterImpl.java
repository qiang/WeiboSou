package me.febsky.weibosou.module.presenter;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.febsky.weibosou.common.DataLoadType;
import me.febsky.weibosou.common.RequestCallback;
import me.febsky.weibosou.entity.UserPhotoEntity;
import me.febsky.weibosou.module.interactor.UserPhotoListInteractor;
import me.febsky.weibosou.module.interactor.UserPhotoListInteractorImpl;
import me.febsky.weibosou.module.view.UserPhotoListView;
import me.febsky.weibosou.utils.Log;

/**
 * Author: liuqiang
 * Date: 2016-08-30
 * Time: 12:53
 * Description:
 */
public class UserPhotoListPresenterImpl extends BasePresenter<UserPhotoListView, String> implements UserPhotoListPresenter {

    private String uid;
    private String fid;
    private String since_id;

    private boolean mIsRefresh = false;

    private UserPhotoListInteractor<String> mInteractor;

    private List<UserPhotoEntity> userPhotoEntities = new ArrayList<>();

    public UserPhotoListPresenterImpl(UserPhotoListView view) {
        super(view);
        mInteractor = new UserPhotoListInteractorImpl();
    }

    @Override
    public void refreshData(final String uid) {
        if (TextUtils.isEmpty(uid)) {
            mView.showError("请求参数错误");
            return;
        }
        this.uid = uid;
        mIsRefresh = true;
        if (TextUtils.isEmpty(fid)) {
            mInteractor.requestFid(new RequestCallback<String>() {
                @Override
                public void beforeRequest() {

                }

                @Override
                public void requestError(String msg) {

                }

                @Override
                public void requestComplete() {

                }

                @Override
                public void requestSuccess(String data) {
                    //解析数据拿到fid,递归调用这个函数

                    try {
                        JSONObject jsonObject = new JSONObject(data);

                        JSONObject albumJsonObj = jsonObject.getJSONObject("tabsInfo").getJSONArray("tabs").getJSONObject(2);

                        fid = albumJsonObj.getString("containerid");

                        refreshData(uid);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, uid);
        } else {
            mInteractor.refreshData(this, uid, fid);
        }
    }

    @Override
    public void loadMoreData() {
        mIsRefresh = false;
        if (TextUtils.isEmpty(since_id)) {
            mView.showError("参数错误");
            return;
        }
        mInteractor.loadMoreData(this, uid, fid, since_id);
    }

    @Override
    public void requestSuccess(String data) {
        super.requestComplete();
        //所有的照片数据返回解析
        Log.d("Q_M:", data);
        //同时要解析出 since_id 为了加载更多

        UserPhotoEntity entity;
        userPhotoEntities.clear();
        try {
            JSONObject jsonObject = new JSONObject(data);
            //把json解析成 UserPhotoEntity 对象

            mView.updatePhotoList(userPhotoEntities,
                    mIsRefresh ? DataLoadType.REFRESH_SUCCESS : DataLoadType.LOAD_MORE_SUCCESS);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
