package me.febsky.weibosou.module.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;
import me.febsky.weibosou.R;
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
        implements UserPhotoListView, PtrHandler, LoadMoreRecyclerView.OnLoadMoreListener, LoadMoreRecyclerView.OnItemClickListener {

    @Bind(R.id.refresh_layout)
    PtrFrameLayout refreshLayout;
    @Bind(R.id.recycler_view)
    LoadMoreRecyclerView recyclerView;
    @Bind(R.id.tv_title)
    TextView titleView;


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
            titleView.setText(userEntity.getScreen_name() + "的美图");
        }

        mPresenter = new UserPhotoListPresenterImpl(this);
//        mPresenter.refreshData(uid);

        mAdapter = new UserPhotoListAdapter(data, mContext);


        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.addItemDecoration(new SpacesItemDecoration(MeasureUtil.dip2px(this, 4)));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setOnItemClickListener(this);


        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.icon_avatar_placeholder);

        recyclerView.addHeader(imageView);
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
        recyclerView.setLoadingMoreEnabled(true);
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
    public void showNoneData() {
        if (recyclerView != null) {
            recyclerView.loadMoreComplete();
            //设置不可上拉
            recyclerView.setLoadingMoreEnabled(false);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, PhotoDetailActivity.class);
        mApplication.reference.USER_PHOTO_ENTITY = data;
        intent.putExtra(Const.VIEWPAGER_CURRENT_POSITION, position);
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
        ActivityCompat.startActivity(this, intent, options.toBundle());
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
