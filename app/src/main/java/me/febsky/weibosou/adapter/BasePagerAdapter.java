package me.febsky.weibosou.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Author: liuqiang
 * Date: 2016-09-09
 * Time: 11:16
 * 根据数据List构建Viewpager
 */
public abstract class BasePagerAdapter<T> extends PagerAdapter {

    protected Context mContext;
    protected List<T> mData;

    public BasePagerAdapter(Context context, List<T> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = initItemView(position);
        container.addView(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        return view;
    }

    protected abstract View initItemView(int position);


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


}
