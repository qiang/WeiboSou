package me.febsky.weibosou.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.febsky.weibosou.R;
import me.febsky.weibosou.entity.WeiBoUserEntity;
import me.febsky.weibosou.utils.MeasureUtil;

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
    private Random mRandom = new Random();

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
        //调整ImageView大小
        int picWidth, picHeight;
        final ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
        picWidth = MeasureUtil.getScreenSize(mContext).x / 2 - MeasureUtil
                .dip2px(mContext, 4) * 2 - MeasureUtil.dip2px(mContext, 2);
        picHeight = (int) (picWidth * (mRandom.nextFloat() / 4 + 1));
        params.width = picWidth;
        params.height = picHeight;
        holder.itemView.setLayoutParams(params);

        //在线加载图片
        Glide.with(mContext)
                .load(userEntities.get(position).getAvatar_large())
                .asBitmap()
                .placeholder(R.drawable.icon_placeholder)
                .error(R.drawable.icon_placeholder)
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.photoSummary);

        holder.screenName.setText(userEntities.get(position).getScreen_name());
        holder.desc.setText(userEntities.get(position).getDesc2());

        //item 出场动画
        // 注意这个地方一旦启用动画，必须在onViewDetachedFromWindow 销毁掉否则引起难以琢磨的错误
        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return userEntities == null ? 0 : userEntities.size();
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
        @Bind(R.id.iv_photo_summary)
        ImageView photoSummary;
        @Bind(R.id.tv_screen_name)
        TextView screenName;
        @Bind(R.id.tv_desc)
        TextView desc;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
