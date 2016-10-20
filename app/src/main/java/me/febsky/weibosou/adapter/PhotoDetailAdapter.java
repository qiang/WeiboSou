package me.febsky.weibosou.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import me.febsky.weibosou.R;
import me.febsky.weibosou.entity.UserPhotoEntity;
import me.febsky.weibosou.module.ui.BaseActivity;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Author: liuqiang
 * Date: 2016-09-09
 * Time: 11:10
 */
public class PhotoDetailAdapter extends BasePagerAdapter<UserPhotoEntity> implements PhotoViewAttacher.OnViewTapListener, View.OnLongClickListener {


    public PhotoDetailAdapter(Context context, List<UserPhotoEntity> data) {
        super(context, data);
    }

    @Override
    protected View initItemView(int position) {

        final PhotoView photoView = new PhotoView(mContext);


        String smaillUrl = mData.get(position).getPic_middle() == null ? mData.get(position).getPic_small() : mData.get(position).getPic_middle();
        //用其它图片作为缩略图
        DrawableRequestBuilder<String> thumbnailRequest = Glide
                .with(mContext)
                .load(smaillUrl);

        Glide.with(mContext)
                .load(mData.get(position).getPic_big())
                .asBitmap()
//                .placeholder(R.drawable.gray_placeholder)    //占位符
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.1f)
                .into(photoView);
//                .into();



        PhotoViewAttacher mAttacher = new PhotoViewAttacher(photoView);
        mAttacher.setOnViewTapListener(this);
        mAttacher.setOnLongClickListener(this);
        return photoView;
    }

    @Override
    public void onViewTap(View view, float x, float y) {
        ((BaseActivity) mContext).finish();
        ((BaseActivity) mContext).overridePendingTransition(0, R.anim.zoom_out);
    }


    @Override
    public boolean onLongClick(View v) {
        Toast.makeText(mContext, "LongClick", Toast.LENGTH_SHORT).show();
        return false;
    }
}
