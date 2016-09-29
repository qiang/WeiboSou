package me.febsky.weibosou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.febsky.weibosou.R;
import me.febsky.weibosou.entity.UserPhotoEntity;
import me.febsky.weibosou.utils.Log;

/**
 * Author: liuqiang
 * Date: 2016-08-31
 * Time: 13:28
 * 显示微博用户的所有照片
 */
public class UserPhotoListAdapter extends BaseRecyclerViewAdapter<UserPhotoListAdapter.ViewHolder> {

    private final LayoutInflater mInflater;
    private List<UserPhotoEntity> userPhotoEntities;
    private Context mContext;

    public UserPhotoListAdapter(List<UserPhotoEntity> userPhotoEntities, Context context) {
        this.userPhotoEntities = userPhotoEntities;
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ViewHolder holder = new ViewHolder(mInflater.inflate(R.layout.item_user_photos, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //在线加载图片
        Glide.with(mContext)
                .load(userPhotoEntities.get(position).getPic_middle())
                .asBitmap()
                .placeholder(R.drawable.gray_placeholder)    //占位符
//                .error(R.drawable.icon_avatar_placeholder)
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(holder.photo);
    }

    @Override
    public int getItemCount() {
        return userPhotoEntities == null ? 0 : userPhotoEntities.size();
    }

    private int lastPosition = -1;

    protected void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils
                    .loadAnimation(viewToAnimate.getContext(), R.anim.item_bottom_in);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_photo)
        ImageView photo;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
