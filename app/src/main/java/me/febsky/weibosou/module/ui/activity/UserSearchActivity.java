package me.febsky.weibosou.module.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import me.febsky.weibosou.R;
import me.febsky.weibosou.adapter.UserSearchResultAdapter;
import me.febsky.weibosou.annotation.InjectContentView;
import me.febsky.weibosou.common.Const;
import me.febsky.weibosou.common.DataLoadType;
import me.febsky.weibosou.entity.WeiBoUserEntity;
import me.febsky.weibosou.module.presenter.SearchPresenter;
import me.febsky.weibosou.module.presenter.SearchPresenterImpl;
import me.febsky.weibosou.module.ui.BaseActivity;
import me.febsky.weibosou.module.view.SearchView;
import me.febsky.weibosou.utils.MeasureUtil;
import me.febsky.weibosou.widget.LoadMoreRecyclerView;
import me.febsky.weibosou.widget.SpacesItemDecoration;

@InjectContentView(R.layout.activity_user_search)
public class UserSearchActivity extends BaseActivity
        implements SearchView,
        LoadMoreRecyclerView.OnLoadMoreListener,
        LoadMoreRecyclerView.OnItemClickListener {


    @Bind(R.id.tv_search_input)
    EditText inputText;
    @Bind(R.id.load_more_recycler_view)
    LoadMoreRecyclerView recyclerView;

    private SearchPresenter mPresenter;
    private List<WeiBoUserEntity> data = new ArrayList<>();
    private UserSearchResultAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new SearchPresenterImpl(this);

        mAdapter = new UserSearchResultAdapter(data, mContext);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SpacesItemDecoration(MeasureUtil.dip2px(this, 4)));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setOnLoadMoreListener(this);
        recyclerView.setOnItemClickListener(this);
    }

    @OnClick(R.id.iv_back_btn)
    void backout() {
        finish();
    }

    @OnClick(R.id.iv_search_btn)
    void search() {
        if (TextUtils.isEmpty(inputText.getText())) {
            Toast.makeText(UserSearchActivity.this, "搜索数据不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        mDialog.show();
        mPresenter.loadMoreData(inputText.getText() + "");
        hideSoftInput(inputText);
    }

    @Override
    public void showNoneData() {
    }

    @Override
    public void updateUserList(List<WeiBoUserEntity> userEntities, int loadType) {
        if (recyclerView != null) {
            recyclerView.loadMoreComplete();
        }
        //这时候就知道该关闭谁了
        if (loadType == DataLoadType.REFRESH_SUCCESS) {
            data.clear();
        }
        data.addAll(userEntities);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void loadMore() {
        mPresenter.loadMoreData(inputText.getText() + "");
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, UserPhotoListActivity.class);
        intent.putExtra(Const.USER_ENTITY, data.get(position));
        startActivity(intent);
    }

    @Override
    public void hideProgressDialog() {
        super.hideProgressDialog();
        //如果下拉开着，关了，如果下拉开着关了
        mDialog.dismiss();
    }
}
