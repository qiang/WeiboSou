package febsky.me.weibosou.module.presenter;

import java.util.ArrayList;
import java.util.List;

import febsky.me.weibosou.common.DataLoadType;
import febsky.me.weibosou.entity.WeiBoUserEntity;
import febsky.me.weibosou.module.interactor.GalleryListInteractor;
import febsky.me.weibosou.module.interactor.GalleryListInteractorImpl;
import febsky.me.weibosou.module.view.GalleryListView;
import febsky.me.weibosou.utils.Log;

/**
 * Author: liuqiang
 * Date: 2016-08-18
 * Time: 17:02
 * 这个地方因为抓的微博的包，不能直接封装成对应的Bean对象。
 * 返回String类型的json对象，然后再解析这个对象吧。
 */
public class GalleryListPresenterImpl extends BasePresenter<GalleryListView, String> implements GalleryListPresenter {

    private int currentPage = 0;
    private boolean mIsRefresh = false;

    private GalleryListInteractor interactor;

    private List<WeiBoUserEntity> userEntity = new ArrayList<>();

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

        Log.d("Q_M:", data);

        mView.updateGalleryList(userEntity,
                mIsRefresh ? DataLoadType.REFRESH_SUCCESS : DataLoadType.LOAD_MORE_SUCCESS);
        mView.hideProgress();
    }
}
