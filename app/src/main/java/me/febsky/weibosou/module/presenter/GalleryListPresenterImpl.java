package me.febsky.weibosou.module.presenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.febsky.weibosou.common.DataLoadType;
import me.febsky.weibosou.entity.WeiBoUserEntity;
import me.febsky.weibosou.module.interactor.GalleryListInteractor;
import me.febsky.weibosou.module.interactor.GalleryListInteractorImpl;
import me.febsky.weibosou.module.view.GalleryListView;
import me.febsky.weibosou.utils.Log;

/**
 * Author: liuqiang
 * Date: 2016-08-18
 * Time: 17:02
 * 这个地方因为抓的微博的包，不能直接封装成对应的Bean对象。
 * 返回String类型的json对象，然后再解析这个对象吧。
 */
public class GalleryListPresenterImpl extends BasePresenterImpl<GalleryListView, String>
        implements GalleryListPresenter {

    private int currentPage = 0;
    private boolean mIsRefresh = false;

    private GalleryListInteractor<String> interactor;

    private List<WeiBoUserEntity> userEntityList = new ArrayList<>();

    public GalleryListPresenterImpl(GalleryListView view) {
        super(view);
        this.interactor = new GalleryListInteractorImpl();
    }

    public boolean ismIsRefresh() {
        return mIsRefresh;
    }

    public void setmIsRefresh(boolean mIsRefresh) {
        this.mIsRefresh = mIsRefresh;
    }

    @Override
    public void refreshData() {
        currentPage = 1;
        mIsRefresh = true;
        interactor.requestGalleryList(this, currentPage++);
    }

    @Override
    public void loadMoreData() {
        mIsRefresh = false;
        interactor.requestGalleryList(this, currentPage++);
    }

    @Override
    public void requestSuccess(String data) {
        super.requestComplete();
        userEntityList.clear();

        Log.d("Q_M:", data);

        WeiBoUserEntity userEntity;

        try {
            JSONObject jsonObject = new JSONObject(data);

            JSONArray card_group = jsonObject.getJSONArray("cards").getJSONObject(0).getJSONArray("card_group");
            for (int i = 0; i < card_group.length(); i++) {
                JSONObject user = card_group.getJSONObject(i).getJSONObject("user");
                userEntity = new WeiBoUserEntity();
                userEntity.setId(user.getLong("id"));
                userEntity.setScreen_name(user.getString("screen_name"));
                userEntity.setAvatar_large(user.getString("avatar_large"));
                userEntity.setProfile_image_url(user.getString("profile_image_url"));
                userEntity.setFollowers_count(user.getInt("followers_count"));
                userEntity.setDesc1(card_group.getJSONObject(i).getString("desc1"));
                userEntity.setDesc2(card_group.getJSONObject(i).getString("desc2"));
                userEntity.setLcardid(card_group.getJSONObject(i).getString("itemid"));
                userEntityList.add(userEntity);
            }
            mView.updateGalleryList(userEntityList,
                    mIsRefresh ? DataLoadType.REFRESH_SUCCESS : DataLoadType.LOAD_MORE_SUCCESS);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
