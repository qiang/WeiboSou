package me.febsky.weibosou.module.presenter;

import android.text.TextUtils;

import org.json.JSONArray;
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
public class UserPhotoListPresenterImpl extends BasePresenterImpl<UserPhotoListView, String> implements UserPhotoListPresenter {

    private String uid;
    private String fid;
    private String since_id;
    private String lcardid;

    private boolean mIsRefresh = false;

    private UserPhotoListInteractor<String> mInteractor;

    private List<UserPhotoEntity> userPhotoEntities = new ArrayList<>();

    public UserPhotoListPresenterImpl(UserPhotoListView view) {
        super(view);
        mInteractor = new UserPhotoListInteractorImpl();
    }

    @Override
    public void refreshData(final String uid, final String lcardid) {
        if (TextUtils.isEmpty(uid)) {
            mView.showError("请求参数错误");
            return;
        }
        this.uid = uid;
        this.lcardid = lcardid;
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

                        refreshData(uid, lcardid);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, uid, lcardid);
        } else {
            mInteractor.refreshData(this, uid, fid, lcardid);
        }
    }

    @Override
    public void loadMoreData() {
        Log.d("Q_M:", "loadMoreData");
        mIsRefresh = false;
        if (TextUtils.isEmpty(since_id)) {
            mView.showError("参数错误");
            return;
        } else if ("0".equals(since_id)) {    //如果为0  表示没有后续数据了
            mView.showNoneData();
            return;
        }
        mInteractor.loadMoreData(this, uid, fid, since_id, lcardid);
    }

    @Override
    public void requestSuccess(String data) {
        super.requestComplete();
        //所有的照片数据返回解析
        userPhotoEntities.clear();
        try {
            JSONObject jsonObject = new JSONObject(data);
            //把json解析成 UserPhotoEntity 对象
            //同时要解析出 since_id 为了加载更多
            since_id = jsonObject.getJSONObject("cardlistInfo").getString("since_id");

            JSONArray cards = jsonObject.getJSONArray("cards");

            parseCards(cards);

            mView.updatePhotoList(userPhotoEntities,
                    mIsRefresh ? DataLoadType.REFRESH_SUCCESS : DataLoadType.LOAD_MORE_SUCCESS);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 由于照片的首页也更多页的数据格式不同，导致要分开解析
     *
     * @param data
     * @throws JSONException
     */
    private void parseFirstPageData(String data) throws JSONException {
        UserPhotoEntity entity;
        JSONObject jsonObject = new JSONObject(data);
        //把json解析成 UserPhotoEntity 对象
        //同时要解析出 since_id 为了加载更多
        since_id = jsonObject.getJSONObject("cardlistInfo").getString("since_id");

        JSONArray cards = jsonObject.getJSONArray("cards");
        Log.d("Q_M:", "cards.length==" + cards.length());
        for (int m = 1; cards.length() > 1 && m < cards.length(); m++) {
            JSONArray card_group = cards.getJSONObject(m).getJSONArray("card_group");
            Log.d("Q_M:", "card_group.length==" + card_group.length());
            for (int i = 0; i < card_group.length(); i++) {
                JSONObject groupObj = card_group.getJSONObject(i);
                JSONArray picsArr = groupObj.getJSONArray("pics");    //每个pics中有三个pic
                if (picsArr != null) {
                    for (int j = 0; j < picsArr.length(); j++) {
                        entity = new UserPhotoEntity();

                        JSONObject picObj = picsArr.getJSONObject(j);
                        String pic_small = picObj.getString("pic_small");
                        String pic_big = picObj.getString("pic_big");
                        String pic_middle = picObj.getString("pic_middle");

                        if (picObj.has("pic_id")) {    //这个东西有可能没有
                            String pic_id = picObj.getString("pic_id");
                            entity.setPic_id(pic_id);
                        }

                        entity.setPic_big(pic_big);
                        entity.setPic_small(pic_small);
                        entity.setPic_middle(pic_middle);
                        userPhotoEntities.add(entity);
                    }
                }
            }
        }

    }

    private void parseMorePageData(String data) throws JSONException {
        UserPhotoEntity entity;
        JSONObject jsonObject = new JSONObject(data);
        //把json解析成 UserPhotoEntity 对象
        //同时要解析出 since_id 为了加载更多
        since_id = jsonObject.getJSONObject("cardlistInfo").getString("since_id");

        JSONArray cards = jsonObject.getJSONArray("cards");
        for (int i = 1; i < cards.length(); i++) {
            JSONObject groupObj = cards.getJSONObject(i);
            JSONArray picsArr = groupObj.getJSONArray("pics");    //每个pics中有三个pic
            if (picsArr != null) {
                for (int j = 0; j < picsArr.length(); j++) {
                    entity = new UserPhotoEntity();

                    JSONObject picObj = picsArr.getJSONObject(j);
                    String pic_small = picObj.getString("pic_small");
                    String pic_big = picObj.getString("pic_big");
                    String pic_middle = picObj.getString("pic_middle");

                    if (picObj.has("pic_id")) {    //这个东西有可能没有
                        String pic_id = picObj.getString("pic_id");
                        entity.setPic_id(pic_id);
                    }

                    entity.setPic_big(pic_big);
                    entity.setPic_small(pic_small);
                    entity.setPic_middle(pic_middle);
                    userPhotoEntities.add(entity);
                }
            }
        }
    }


    /**
     * 主要做的是解析的分发
     *
     * @param cards
     * @throws JSONException
     */
    private void parseCards(JSONArray cards) throws JSONException {

        JSONObject currentJsonObj;
        for (int i = 0; i < cards.length(); i++) {
            currentJsonObj = cards.getJSONObject(i);
            switch (currentJsonObj.getInt("card_type")) {
                case 3:    //这种类型的解析有点麻烦，图片没有大中小只有一张图片
                    //parsePicsObj(currentJsonObj);
                    break;
                case 11:
                    parseCards(currentJsonObj.getJSONArray("card_group"));
                    break;
                case 47:
                    parsePicsObj(currentJsonObj);
                    break;
                case 35:
                    parsePicsObj(currentJsonObj);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * {card_type:47,pics[]}中的pics数组
     *
     * @param picsParent
     */
    private void parsePicsObj(JSONObject picsParent) throws JSONException {
        UserPhotoEntity entity;
        JSONArray picsArr = picsParent.getJSONArray("pics");    //每个pics中有三个pic
        if (picsArr != null) {
            for (int j = 0; j < picsArr.length(); j++) {
                entity = new UserPhotoEntity();

                JSONObject picObj = picsArr.getJSONObject(j);
                String pic_small = picObj.getString("pic_small");
                String pic_big = picObj.getString("pic_big");

                if (picObj.has("pic_id")) {    //这个东西有可能没有
                    String pic_id = picObj.getString("pic_id");
                    entity.setPic_id(pic_id);
                }
                if (picObj.has("pic_middle")) {
                    String pic_middle = picObj.getString("pic_middle");
                    entity.setPic_middle(pic_middle);
                }

                entity.setPic_big(pic_big);
                entity.setPic_small(pic_small);
                userPhotoEntities.add(entity);
            }
        }
    }

}
