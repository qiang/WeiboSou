package me.febsky.weibosou.module.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import me.febsky.weibosou.R;
import me.febsky.weibosou.annotation.InjectContentView;
import me.febsky.weibosou.entity.WeiBoUserEntity;
import me.febsky.weibosou.module.presenter.SearchPresenter;
import me.febsky.weibosou.module.presenter.SearchPresenterImpl;
import me.febsky.weibosou.module.ui.BaseActivity;
import me.febsky.weibosou.module.view.SearchView;
import me.febsky.weibosou.utils.Log;

@InjectContentView(R.layout.activity_user_search)
public class UserSearchActivity extends BaseActivity implements SearchView {

    private SearchPresenter mPresenter;

    @Bind(R.id.tv_search_input)
    EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new SearchPresenterImpl(this);
    }

    @OnClick(R.id.iv_back_btn)
    void backout() {
        finish();
    }

    @OnClick(R.id.iv_search_btn)
    void search() {
        if (TextUtils.isEmpty(inputText.getText()))
            return;
        mPresenter.loadMoreData(inputText.getText() + "");
    }

    @Override
    public void updateUserList(List<WeiBoUserEntity> userEntities, int loadType) {
        Log.d("Q_M:", userEntities.toString() + "------->" + userEntities.size());
    }

    @Override
    public void showNoneData() {

    }
}
