package me.febsky.weibosou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.febsky.weibosou.R;
import me.febsky.weibosou.entity.WeiBoUserEntity;

/**
 * Author: liuqiang
 * Date: 2016-09-11
 * Time: 17:15  搜索用户结果列表
 */
public class UserSearchResultAdapter extends BaseRecyclerViewAdapter<UserSearchResultAdapter.ViewHolder> {

    private final LayoutInflater mInflater;
    private List<WeiBoUserEntity> userEntities;
    private Context mContext;
    private Random mRandom = new Random();

    public UserSearchResultAdapter(List<WeiBoUserEntity> userEntities, Context context) {
        this.userEntities = userEntities;
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ViewHolder holder = new ViewHolder(mInflater.inflate(R.layout.item_user_search_result, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //在线加载图片
        Glide.with(mContext)
                .load(userEntities.get(position).getAvatar_large())
                .asBitmap()
                .placeholder(R.drawable.icon_avatar_placeholder)    //头像占位符
                .error(R.drawable.icon_avatar_placeholder)
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.photoSummary);

        holder.screenName.setText(userEntities.get(position).getScreen_name());
        holder.desc1.setText(userEntities.get(position).getDesc1());
        holder.desc2.setText(userEntities.get(position).getDesc2());

    }

    @Override
    public int getItemCount() {
        return userEntities == null ? 0 : userEntities.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_user_avatar)
        ImageView photoSummary;
        @BindView(R.id.tv_screen_name)
        TextView screenName;
        @BindView(R.id.tv_desc1)
        TextView desc1;
        @BindView(R.id.tv_desc2)
        TextView desc2;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
