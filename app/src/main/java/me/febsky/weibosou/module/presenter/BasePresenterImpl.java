package me.febsky.weibosou.module.presenter;

import me.febsky.weibosou.common.RequestCallback;
import me.febsky.weibosou.module.view.BaseView;

/**
 * Author: liuqiang
 * Date: 2016-08-31
 * Time: 17:29
 * 这里有这个类主要为了处理，请求回调方法的默认实现
 * 不用再每个Presenter类中都去实现一遍RequestCallback
 * 所以我让每个Presenter都有一个BaseView的泛型
 * 而Google给出的例子中他让BaseView包含一个Presenter的泛型，
 * 为的是让每个Presenter在初始化的时候就调用View的setPresenter方法，
 * 再通过泛型的方式实现Presenter在UI中的初始化，例如：
 * 如果一个Fragment实现了这个Presenter，而Presenter是在Fragment所对应的
 * Activity中初始化的，从而实现了在Activity中操作Fragment中的UI。？？这种方式有用？？
 * 也是哈，如果activity上有个按钮，点击后要刷新fragment，这样由于activity和fragment
 * 同事持有Presenter的引用，所以activity也能触发网络请求，从而间接实现页面刷新。
 * 而我却用了EventBus来实现这一效果。
 * <p/>
 * 最后其实Presenter是不需要任何不确定类型（泛型）的，因为他实现了RequestCallback
 * 这个东西，所以需要传入解析json数据格式的泛型参数
 */
public abstract class BasePresenterImpl<V extends BaseView, M>
        implements RequestCallback<M>, BasePresenter {

    protected V mView;

    public BasePresenterImpl(V view) {
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
