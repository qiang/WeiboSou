package me.febsky.weibosou.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import me.febsky.weibosou.util.MeasureUtil;


/**
 * Author: liuqiang
 * Date: 2016-08-17
 * Time: 15:07
 * Description: 添加了滑动到底部自动加载的RecyclerView
 */
public class LoadMoreRecyclerView extends RecyclerView {

    private int[] mVisiblePositions;

    private boolean isLoadingData = false;    //当前是不是正在加载数据
    private boolean isNoMore = false;    //没有更多了
    private boolean loadingMoreEnabled = true;    //是否可以加载更多
    public static final int TYPE_FOOTER = 10002;
    public static final int TYPE_HEADER = 10001;

    private ViewGroup mFootView;
    private ViewGroup mHeadView;
    private WrapAdapter mWrapAdapter;
    private LayoutInflater mInflater;

    private boolean mUseAnimation = true;   //Item的加载动画，不是原生的RecyclerView 带的是自定义的

    private final AdapterDataObserver mDataObserver = new DataObserver();

    public LoadMoreRecyclerView(Context context) {
        this(context, null);
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        mInflater = LayoutInflater.from(context);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LayoutParams rl = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(rl);
        linearLayout.setGravity(Gravity.CENTER);
        mFootView = linearLayout;
        mHeadView = new FrameLayout(context);

        super.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!isLoadingData
                        && RecyclerView.SCROLL_STATE_IDLE == newState
                        && findLastCompletelyVisibleItemPositions() == getAdapter().getItemCount() - 1
                        && mLoadMoreListener != null
                        && loadingMoreEnabled
                        && !isNoMore
                        ) {
                    mFootView.setVisibility(VISIBLE);
                    Log.d("TAG:", "加载更多数据");
                    mLoadMoreListener.loadMore();
                    // 之前的状态为非正在加载状态
                    isLoadingData = true;
                }

            }
        });
    }

    /**
     * 计算RecyclerView当前最后一个完全可视位置
     */
    private int findLastCompletelyVisibleItemPositions() {
        // 判断LayoutManager类型获取第一个完全可视位置
        if (getLayoutManager() instanceof StaggeredGridLayoutManager) {
            if (mVisiblePositions == null) {
                mVisiblePositions = new int[((StaggeredGridLayoutManager) getLayoutManager())
                        .getSpanCount()];
            }
            ((StaggeredGridLayoutManager) getLayoutManager())
                    .findLastCompletelyVisibleItemPositions(mVisiblePositions);
            int max = -1;
            for (int pos : mVisiblePositions) {
                max = Math.max(max, pos);
            }
            return max;
            // return mVisiblePositions[0];
        } else if (getLayoutManager() instanceof GridLayoutManager) {
            return ((GridLayoutManager) getLayoutManager()).findLastCompletelyVisibleItemPosition();
        } else {
            return ((LinearLayoutManager) getLayoutManager())
                    .findLastCompletelyVisibleItemPosition();
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        mWrapAdapter = new WrapAdapter(adapter);
        super.setAdapter(mWrapAdapter);
        adapter.registerAdapterDataObserver(mDataObserver);
        mDataObserver.onChanged();
    }


    private class DataObserver extends AdapterDataObserver {
        @Override
        public void onChanged() {
            if (mWrapAdapter != null) {
                mWrapAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            mWrapAdapter.notifyItemRangeInserted(positionStart, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            mWrapAdapter.notifyItemRangeChanged(positionStart, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            mWrapAdapter.notifyItemRangeChanged(positionStart, itemCount, payload);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            mWrapAdapter.notifyItemRangeRemoved(positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            mWrapAdapter.notifyItemMoved(fromPosition, toPosition);
        }
    }

    /**
     * Author: liuqiang
     * Date: 2016-08-23
     * Time: 16:34
     * Description: 为上拉加载更多准备的
     */
    public class WrapAdapter extends Adapter<ViewHolder> {

        private Adapter<ViewHolder> adapter;

        public WrapAdapter(Adapter adapter) {
            this.adapter = adapter;
        }

        public boolean isFooter(int position) {
            if (loadingMoreEnabled) {
                return position != 0 && position == getItemCount() - 1;
            } else {
                return false;
            }
        }

        public boolean isHeader(int position) {
            return position == 0;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_FOOTER) {
                if (mFootView.getChildCount() == 0) {
                    ((LinearLayout) mFootView).setOrientation(LinearLayout.HORIZONTAL);
                    ProgressBar pb = new ProgressBar(parent.getContext(), null, android.R.attr.progressBarStyleSmall);
                    LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    ll.gravity = Gravity.CENTER;
                    pb.setLayoutParams(ll);
                    mFootView.addView(pb);

                    LinearLayout.LayoutParams ll2 = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    ll2.setMargins(MeasureUtil.dip2px(parent.getContext(), 10.0f), 0, 0, 0);
                    TextView textView = new TextView(parent.getContext());
                    textView.setLayoutParams(ll2);
                    textView.setText("正在加载...");
                    textView.setTextSize(16);    //这个设置默认是sp
                    textView.setTextColor(Color.parseColor("#727272"));
                    mFootView.addView(textView);

                    mFootView.setPadding(0,
                            MeasureUtil.dip2px(parent.getContext(), 10.0f),
                            0,
                            MeasureUtil.dip2px(parent.getContext(), 10.0f)
                    );

                    mFootView.setVisibility(VISIBLE);

                    //如果FootView距离屏幕顶部的位置

                    //
//                    if (findLastCompletelyVisibleItemPositions() == adapter.getItemCount() - 1) {
//                        loadingMoreEnabled = false;
//                        mFootView.setVisibility(GONE);
//                    }
                }
                return new SimpleViewHolder(mFootView);
            } else if (viewType == TYPE_HEADER) {
                return new SimpleViewHolder(mHeadView);
            }

            //一种点击事件的实现方式，有个头所以要减一
            final ViewHolder viewHolder = adapter.onCreateViewHolder(parent, viewType);
            if (mClickListener != null) {
                viewHolder.itemView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mClickListener.onItemClick(v, viewHolder.getLayoutPosition() - 1);
                    }
                });
            }
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            if (isFooter(position)) {
                mFootView.setVisibility(VISIBLE);
                if (!isLoadingData) mFootView.setVisibility(GONE);
            } else if (isHeader(position)) {

            } else {
                adapter.onBindViewHolder(holder, position - 1);
            }
        }

        @Override
        public int getItemCount() {
            if (loadingMoreEnabled) {
                return adapter.getItemCount() == 0 ? 1 : adapter.getItemCount() + 2;
            } else {
                return adapter.getItemCount() + 1;
            }
        }

        @Override
        public int getItemViewType(int position) {

            if (adapter.getItemViewType(position) == TYPE_FOOTER) {
                throw new IllegalStateException(" this itemViewType is used by TYPE_FOOTER !");
            }
            if (isFooter(position)) {
                return TYPE_FOOTER;
            } else if (isHeader(position)) {
                return TYPE_HEADER;
            }

            return adapter.getItemViewType(position);
        }

        @Override
        public long getItemId(int position) {
            if (adapter != null && position >= +1) {
                return adapter.getItemId(position - 1);
            }
            return -1;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
            LayoutManager manager = recyclerView.getLayoutManager();
            if (manager instanceof GridLayoutManager) {
                final GridLayoutManager gridManager = ((GridLayoutManager) manager);
                gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return (isHeader(position) || isFooter(position)) ? gridManager.getSpanCount() : 1;
                    }
                });
            }
            adapter.onAttachedToRecyclerView(recyclerView);
        }


        @Override
        public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
            adapter.onDetachedFromRecyclerView(recyclerView);
        }

        @Override
        public void onViewAttachedToWindow(ViewHolder holder) {
            super.onViewAttachedToWindow(holder);
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp != null
                    && lp instanceof StaggeredGridLayoutManager.LayoutParams
                    && (isHeader(holder.getLayoutPosition()) || isFooter(holder.getLayoutPosition()))) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
            adapter.onViewAttachedToWindow(holder);
        }


        @Override
        public void onViewDetachedFromWindow(ViewHolder holder) {
            adapter.onViewDetachedFromWindow(holder);
            if (mUseAnimation && holder.itemView.getAnimation() != null && holder.itemView
                    .getAnimation().hasStarted()) {
                holder.itemView.clearAnimation();
            }
        }

        @Override
        public void onViewRecycled(ViewHolder holder) {
            adapter.onViewRecycled(holder);
        }

        @Override
        public boolean onFailedToRecycleView(ViewHolder holder) {
            return adapter.onFailedToRecycleView(holder);
        }

        @Override
        public void unregisterAdapterDataObserver(AdapterDataObserver observer) {
            adapter.unregisterAdapterDataObserver(observer);
        }

        @Override
        public void registerAdapterDataObserver(AdapterDataObserver observer) {
            adapter.registerAdapterDataObserver(observer);
        }


        private class SimpleViewHolder extends ViewHolder {
            public SimpleViewHolder(View itemView) {
                super(itemView);
            }
        }
    }


    public void setFootView(final View view) {
        mFootView.removeAllViews();
        mFootView.addView(view);
    }

    public void loadMoreComplete() {
        isLoadingData = false;
    }

    public void setLoadingMoreEnabled(boolean loadingMoreEnabled) {
        this.loadingMoreEnabled = loadingMoreEnabled;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        mLoadMoreListener = loadMoreListener;
    }

    private OnLoadMoreListener mLoadMoreListener;

    public interface OnLoadMoreListener {
        void loadMore();
    }


    public void addHeader(View header) {
        this.mHeadView.addView(header);
    }


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