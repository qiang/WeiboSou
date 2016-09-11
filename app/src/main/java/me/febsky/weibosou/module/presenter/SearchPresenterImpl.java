package me.febsky.weibosou.module.presenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.febsky.weibosou.common.DataLoadType;
import me.febsky.weibosou.entity.WeiBoUserEntity;
import me.febsky.weibosou.module.interactor.SearchInteractor;
import me.febsky.weibosou.module.interactor.SearchInteractorImpl;
import me.febsky.weibosou.module.view.SearchView;
import me.febsky.weibosou.utils.Log;

/**
 * Author: liuqiang
 * Date: 2016-08-30
 * Time: 12:53
 * Description:
 */
public class SearchPresenterImpl extends BasePresenterImpl<SearchView, String>
        implements SearchPresenter {

    private boolean mIsRefresh = false;
    private int page = 1;

    private SearchInteractor<String> mInteractor;

    private List<WeiBoUserEntity> userEntities = new ArrayList<>();

    public SearchPresenterImpl(SearchView view) {
        super(view);
        mInteractor = new SearchInteractorImpl();
    }

    @Override
    public void loadMoreData(String keyword) {
        Log.d("Q_M:", "loadMoreData");
        mIsRefresh = false;
        mInteractor.loadMoreData(this, keyword, page++);
    }

    @Override
    public void requestSuccess(String data) {
        super.requestComplete();
        //所有的照片数据返回解析
        userEntities.clear();
        //解析data重新构建

//        Log.d("Q_M:", "" + data);

        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray cardsArr = jsonObject.getJSONArray("cards");
            JSONObject cardObj;
            for (int i = 0; i < cardsArr.length(); i++) {
                cardObj = cardsArr.getJSONObject(i);

                switch (cardObj.getInt("card_type")) {
                    case 11:
                        parseCardGroup(cardObj.getJSONArray("card_group"));
                        break;
                    default:
                        break;
                }
            }

            mView.updateUserList(userEntities,
                    mIsRefresh ? DataLoadType.REFRESH_SUCCESS : DataLoadType.LOAD_MORE_SUCCESS);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void parseCardGroup(JSONArray card_group) throws JSONException {
        WeiBoUserEntity userEntity;

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
            userEntities.add(userEntity);
        }
    }

}
