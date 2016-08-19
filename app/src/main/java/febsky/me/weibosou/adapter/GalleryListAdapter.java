package febsky.me.weibosou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import febsky.me.weibosou.R;
import febsky.me.weibosou.entity.WeiBoUserEntity;

/**
 * Author: liuqiang
 * Date: 2016-08-18
 * Time: 18:28
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

    }

    @Override
    public int getItemCount() {
        return 5;
//        return userEntities == null ? 0 : userEntities.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View view) {
            super(view);

        }
    }
}
