package me.febsky.weibosou.module.ui.activity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

/**
 * Author: liuqiang
 * Date: 2016-09-29
 * Time: 10:32
 * Description: 大图浏览
 */
@InjectContentView(R.layout.activity_photo_detail)
public class PhotoDetailActivity extends BaseActivity
        implements UserPhotoDetailView, ViewPager.OnPageChangeListener {

    @Bind(R.id.loading_view)
    ThreePointLoadingView mLoadingView;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;
    @Bind(R.id.tv_title)
    TextView titleView;

    private int currentPosition = 0;

    private UserPhotoDetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentPosition = getIntent().getIntExtra(Const.VIEWPAGER_CURRENT_POSITION, 0);
        mPresenter = new UserPhotoDetailPresenterImpl(this);
        titleView.setText("" + (currentPosition + 1) + "/" + mApplication.reference.USER_PHOTO_ENTITY.size());
    }

    @Override
    public void initViewPager() {
        PhotoDetailAdapter photoDetailAdapter = new PhotoDetailAdapter(this, mApplication.reference.USER_PHOTO_ENTITY);
        mViewPager.setAdapter(photoDetailAdapter);
        mViewPager.setCurrentItem(currentPosition);
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    protected void onActivityFinish() {
        super.onActivityFinish();
        mApplication.reference.USER_PHOTO_ENTITY = null;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        titleView.setText("" + (position + 1) + "/" + mApplication.reference.USER_PHOTO_ENTITY.size());

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
