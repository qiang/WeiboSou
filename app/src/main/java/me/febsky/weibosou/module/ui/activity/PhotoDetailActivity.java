package me.febsky.weibosou.module.ui.activity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import butterknife.Bind;
import me.febsky.weibosou.R;
import me.febsky.weibosou.adapter.PhotoDetailAdapter;
import me.febsky.weibosou.annotation.InjectContentView;
import me.febsky.weibosou.common.Const;
import me.febsky.weibosou.module.presenter.UserPhotoDetailPresenter;
import me.febsky.weibosou.module.presenter.UserPhotoDetailPresenterImpl;
import me.febsky.weibosou.module.ui.BaseActivity;
import me.febsky.weibosou.module.view.UserPhotoDetailView;
import me.febsky.weibosou.widget.ThreePointLoadingView;


@InjectContentView(R.layout.activity_photo_detail)
public class PhotoDetailActivity extends BaseActivity
        implements UserPhotoDetailView {

    @Bind(R.id.loading_view)
    ThreePointLoadingView mLoadingView;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    private int currentPositon = 0;

    private UserPhotoDetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentPositon = getIntent().getIntExtra(Const.VIEWPAGER_CURRENT_POSITION, 0);
        mPresenter = new UserPhotoDetailPresenterImpl(this);
    }

    @Override
    public void initViewPager() {
        PhotoDetailAdapter photoDetailAdapter = new PhotoDetailAdapter(this, mApplication.reference.USER_PHOTO_ENTITY);
        mViewPager.setAdapter(photoDetailAdapter);
        mViewPager.setCurrentItem(currentPositon);
    }

    @Override
    public void finish() {
        super.finish();
        mApplication.reference.USER_PHOTO_ENTITY = null;
    }
}