package me.febsky.weibosou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.febsky.weibosou.R;
import me.febsky.weibosou.entity.UserPhotoEntity;
import me.febsky.weibosou.utils.MeasureUtil;

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
    private Random mRandom = new Random();

    public UserPhotoListAdapter(List<UserPhotoEntity> userPhotoEntities, Context context) {
        this.userPhotoEntities = userPhotoEntities;
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ViewHolder holder = new ViewHolder(mInflater.inflate(R.layout.item_user_photos, parent, false));
        if (mClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(v, holder.getLayoutPosition());
                }
            });
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //调整ImageView大小
        int picWidth, picHeight;
        final ViewGroup.LayoutParams params = holder.photo.getLayoutParams();
        picWidth = MeasureUtil.getScreenSize(mContext).x / 2 - MeasureUtil
                .dip2px(mContext, 4) * 2 - MeasureUtil.dip2px(mContext, 2);
        picHeight = (int) (picWidth * (mRandom.nextFloat() / 2 + 1));
        params.width = picWidth;
        params.height = picHeight;
        holder.photo.setLayoutParams(params);

        //item 出场动画
        // 注意这个地方一旦启用动画，必须在onViewDetachedFromWindow 销毁掉否则引起难以琢磨的错误
        setAnimation(holder.itemView, position);
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
