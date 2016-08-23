package me.febsky.weibosou.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Author: liuqiang
 * Date: 2016-08-23
 * Time: 16:42
 */
public abstract class BaseRecyclerViewAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    protected OnItemClickListener mClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mClickListener = listener;
    }

    /**
     * Author: liuqiang
     * Date: 2016-08-23
     * Time: 16:32
     * RecyclerView 的Item点击事件
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
