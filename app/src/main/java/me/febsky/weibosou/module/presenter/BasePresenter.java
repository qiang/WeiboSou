package me.febsky.weibosou.module.presenter;

import me.febsky.weibosou.common.RequestCallback;
import me.febsky.weibosou.module.view.BaseView;

/**
 * Author: liuqiang
 * Date: 2016-08-18
 * Time: 17:29
 */
public abstract class BasePresenter<V extends BaseView, M> implements RequestCallback<M> {

    protected V mView;

    public BasePresenter(V view) {
        mView = view;
    }

    @Override
    public void beforeRequest() {
        mView.showProgress("");
    }


    @Override
    public void requestError(String msg) {
        mView.showError(msg);
        mView.hideProgress();
    }

    @Override
    public void requestComplete() {
        mView.hideProgress();
    }

    //搞成抽象类？ 请求成功方法必须实现？也行

}
