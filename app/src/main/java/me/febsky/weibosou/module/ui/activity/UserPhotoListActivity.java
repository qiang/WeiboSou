package me.febsky.weibosou.module.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;
import me.febsky.weibosou.R;
import me.febsky.weibosou.adapter.BaseRecyclerViewAdapter;
import me.febsky.weibosou.adapter.UserPhotoListAdapter;
import me.febsky.weibosou.annotation.InjectContentView;
import me.febsky.weibosou.common.Const;
import me.febsky.weibosou.common.DataLoadType;
import me.febsky.weibosou.entity.UserPhotoEntity;
import me.febsky.weibosou.entity.WeiBoUserEntity;
import me.febsky.weibosou.module.presenter.UserPhotoListPresenter;
import me.febsky.weibosou.module.presenter.UserPhotoListPresenterImpl;
import me.febsky.weibosou.module.ui.BaseActivity;
import me.febsky.weibosou.module.view.UserPhotoListView;
import me.febsky.weibosou.utils.Log;
import me.febsky.weibosou.utils.MeasureUtil;
import me.febsky.weibosou.widget.GradationScrollView;
import me.febsky.weibosou.widget.LoadMoreRecyclerView;
import me.febsky.weibosou.widget.SpacesItemDecoration;

/**
 * Author: liuqiang
 * Date: 2016-08-30
 * Time: 11:22
 * Description: 某个微博用户的照片列表
 */
@InjectContentView(R.layout.activity_user_photo_list)
public class UserPhotoListActivity extends BaseActivity
        implements UserPhotoListView, PtrHandler, LoadMoreRecyclerView.OnLoadMoreListener, BaseRecyclerViewAdapter.OnItemClickListener {

    @Bind(R.id.refresh_layout)
    PtrFrameLayout refreshLayout;
    @Bind(R.id.recycler_view)
    LoadMoreRecyclerView recyclerView;


    private List<UserPhotoEntity> data = new ArrayList<>();
    private UserPhotoListAdapter mAdapter;
    private UserPhotoListPresenter mPresenter;
    private WeiBoUserEntity userEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent requestIntent = getIntent();
        if (requestIntent != null) {
            userEntity = (WeiBoUserEntity) requestIntent.getSerializableExtra(Const.USER_ENTITY);
            Log.d("Q_M:", "userEntity " + userEntity.toString());
        }

        mPresenter = new UserPhotoListPresenterImpl(this);
//        mPresenter.refreshData(uid);

        mAdapter = new UserPhotoListAdapter(data, mContext);
        mAdapter.setOnItemClickListener(this);


        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.addItemDecoration(new SpacesItemDecoration(MeasureUtil.dip2px(this, 4)));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setOnLoadMoreListener(this);

        initPrtFrameLayout();
    }

    /**
     * 初始化下拉刷新库
     */
    private void initPrtFrameLayout() {
        final MaterialHeader header = new MaterialHeader(this);
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

    //下拉加载库
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
        mPresenter.refreshData(userEntity.getId() + "", userEntity.getLcardid());
    }

    @Override
    public void updatePhotoList(List<UserPhotoEntity> photoEntities, int loadType) {
        if (loadType == DataLoadType.REFRESH_SUCCESS) {
            data.clear();
        }
        data.addAll(photoEntities);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void hideProgress() {
        super.hideProgress();

        //如果下拉开着，关了，如果下拉开着关了
        if (refreshLayout != null) {
            refreshLayout.refreshComplete();
        }
        if (recyclerView != null) {
            recyclerView.loadMoreComplete();
        }
    }

    @OnClick(R.id.iv_back_btn)
    void backout() {
        finish();
    }


}
