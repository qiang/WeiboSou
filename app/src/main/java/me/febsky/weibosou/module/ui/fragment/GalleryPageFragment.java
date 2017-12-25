package me.febsky.weibosou.module.ui.fragment;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;


import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;
import me.febsky.weibosou.R;
import me.febsky.weibosou.adapter.GalleryListAdapter;
import me.febsky.weibosou.annotation.InjectContentView;
import me.febsky.weibosou.common.Const;
import me.febsky.weibosou.common.DataLoadType;
import me.febsky.weibosou.entity.WeiBoUserEntity;
import me.febsky.weibosou.event.RefreshEvent;
import me.febsky.weibosou.module.presenter.GalleryListPresenter;
import me.febsky.weibosou.module.presenter.GalleryListPresenterImpl;
import me.febsky.weibosou.module.ui.LazyBaseFragment;
import me.febsky.weibosou.module.ui.activity.UserPhotoListActivity;
import me.febsky.weibosou.module.view.GalleryListView;
import me.febsky.weibosou.util.MeasureUtil;
import me.febsky.weibosou.widget.LoadMoreRecyclerView;
import me.febsky.weibosou.widget.SpacesItemDecoration;

/**
 * Author: liuqiang
 * Date: 2016-08-16
 * Time: 16:18
 * Des: 首页显示的图片，恩，无限加载模式
 * 这里面都是图集，每个图集是一个人的
 */
@InjectContentView(R.layout.fragment_page_gallery)
public class GalleryPageFragment extends LazyBaseFragment
        implements GalleryListView, LoadMoreRecyclerView.OnLoadMoreListener, PtrHandler, LoadMoreRecyclerView.OnItemClickListener {

    @BindView(R.id.refresh_layout)
    PtrFrameLayout refreshLayout;
    @BindView(R.id.recycler_view)
    LoadMoreRecyclerView recyclerView;

    private GalleryListPresenter mPresenter;
    private GalleryListAdapter mAdapter;
    private List<WeiBoUserEntity> data = new ArrayList<>();

    @Override
    public void lazyInitData() {
        mPresenter = new GalleryListPresenterImpl(this);

        mAdapter = new GalleryListAdapter(data, mContext);

        final StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(MeasureUtil.dip2px(getActivity(), 4)));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setOnLoadMoreListener(this);
        recyclerView.setOnItemClickListener(this);

        final MaterialHeader header = new MaterialHeader(getContext());
        int[] colors = getResources().getIntArray(R.array.google_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, MeasureUtil.dip2px(mContext, 15), 0, MeasureUtil.dip2px(mContext, 10));
        header.setPtrFrameLayout(refreshLayout);

        refreshLayout.setLoadingMinTime(1000);
        refreshLayout.setDurationToCloseHeader(1500);
        refreshLayout.disableWhenHorizontalMove(true);
        refreshLayout.setHeaderView(header);
        refreshLayout.addPtrUIHandler(header);
        refreshLayout.setPtrHandler(this);
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.autoRefresh(false);
            }
        }, 150);
    }

    @Override
    public String toString() {
        return "首页";
    }

    //presenter  对view赋值  表示此时数据加载完成
    @Override
    public void updateGalleryList(List<WeiBoUserEntity> userEntityList, int loadType) {
        //这时候就知道该关闭谁了
        if (loadType == DataLoadType.REFRESH_SUCCESS) {
            data.clear();
        }
        data.addAll(userEntityList);
        mAdapter.notifyDataSetChanged();
    }

    //上拉加载更多
    @Override
    public void loadMore() {
        mPresenter.loadMoreData();
    }

    // 下拉刷新库
    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        // 默认实现，根据实际情况做改动
        return PtrDefaultHandler.checkContentCanBePulledDown(frame, recyclerView, header);
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        mPresenter.refreshData();
    }

    @Override
    public void hideProgressDialog() {
        super.hideProgressDialog();

        //如果下拉开着，关了，如果下拉开着关了
        if (refreshLayout != null) {
            refreshLayout.refreshComplete();
        }
        if (recyclerView != null) {
            recyclerView.loadMoreComplete();
        }
    }

    @Override
    protected boolean isBindEventBus() {
        return true;
    }

    /**
     * 点击刷新效果
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshEvent(RefreshEvent event) {
        if (refreshLayout.isRefreshing()) return;
        recyclerView.smoothScrollToPosition(0);
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.autoRefresh(false);
            }
        }, 1);

    }

    //RecyclerView的Item点击事件
    @Override
    public void onItemClick(View view, int position) {
//        startActivity(startIntent);
        Intent intent = new Intent(getActivity(), UserPhotoListActivity.class);
        intent.putExtra(Const.USER_ENTITY, data.get(position));
        //让新的Activity从一个小的范围扩大到全屏
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
    }
}
