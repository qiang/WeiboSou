package me.febsky.weibosou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.febsky.weibosou.R;
import me.febsky.weibosou.entity.WeiBoUserEntity;

/**
 * Author: liuqiang
 * Date: 2016-08-18
 * Time: 18:28
 * 首页显示用户列表
 */
public class GalleryListAdapter extends RecyclerView.Adapter<GalleryListAdapter.ViewHolder> {

    private final LayoutInflater mInflater;
    private List<WeiBoUserEntity> userEntities;
    private Context mContext;

    public GalleryListAdapter(List<WeiBoUserEntity> userEntities, Context context) {
        this.userEntities = userEntities;
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ViewHolder holder = new ViewHolder(mInflater.inflate(R.layout.item_photo_gallery, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(mContext).load(userEntities.get(position).getAvatar_large())
                .asBitmap()
                .placeholder(R.drawable.icon_placeholder)
                .error(R.drawable.icon_placeholder)
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.photoSummary);
    }

    @Override
    public int getItemCount() {
        return userEntities == null ? 0 : userEntities.size();
    }

    public void addMore(List<WeiBoUserEntity> moreEntities) {
        if (userEntities == null) {
            userEntities = moreEntities;
        } else {
            userEntities.addAll(moreEntities);
        }
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_photo_summary)
        ImageView photoSummary;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
