/*
 * Copyright (c) 2015 [1076559197@qq.com | tchen0707@gmail.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.febsky.weibosou.module.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import me.febsky.weibosou.annotation.InjectContentView;
import me.febsky.weibosou.module.view.BaseView;


/**
 * Author:  liuqiang
 * Date:    2016/5/2
 * Description:
 */
public abstract class BaseFragment extends Fragment implements BaseView {

    /**
     * Log tag
     */
    protected static String LOG_TAG = null;

    protected Context mContext;
    //protected Activity mActivity;
    protected View rootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LOG_TAG = this.getClass().getSimpleName();
        //this.mActivity = getActivity();
        this.mContext = getContext();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == rootView) {
            if (getClass().isAnnotationPresent(InjectContentView.class)) {
                InjectContentView annotation = getClass()
                        .getAnnotation(InjectContentView.class);
                rootView = inflater.inflate(annotation.value(), container, false);
            } else {
                throw new RuntimeException(
                        "Class must add annotations of ActivityFragmentInitParams.class");
            }
        }
        if (isBindButterKnife()) {
            ButterKnife.bind(this, rootView);
        }

        if (isBindEventBus()) {
            EventBus.getDefault().register(this);
        }

        return rootView;
    }

    /**
     * fragment 初次加载数据
     */
    protected abstract void initData();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isBindButterKnife()) {
            ButterKnife.unbind(this);
        }
        if (isBindEventBus()){
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void showError(String msg) {
    }

    @Override
    public void showProgressDialog(String msg) {
    }

    @Override
    public void hideProgressDialog() {

    }


    /**
     * 向子类暴漏是不是加载ButterKnife库
     *
     * @return
     */
    protected boolean isBindButterKnife() {
        return true;
    }

    /**
     * 向子类暴漏是不是加载EventBus库
     *
     * @return
     */
    protected boolean isBindEventBus() {
        return false;
    }
}
