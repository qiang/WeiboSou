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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.febsky.weibosou.module.view.BaseView;


/**
 * Author:  liuqiang
 * Date:    2016/5/2
 * Description: ViewPager 延迟加载
 * viewpager监听切换tab事件，tab切换一次，执行一次setUserVisibleHint()方法
 * fragment 的显示和隐藏都会调用这个方法，但是这个方法会在生命周期之前被调用
 */
public abstract class LazyBaseFragment extends BaseFragment implements BaseView {

    /**
     * 控件是否初始化完成
     */
    private boolean isViewCreated;
    /**
     * 数据是否已加载过一次
     */
    private boolean isLoadDataCompleted;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isViewCreated = true;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * Author: liuqiang
     * Time: 2017-01-11 16:10
     * Description: 这个方法真正的含义应该是Fragment的View即将创建
     * 而且这个方法是主动设置给Fragment，fragment本身并不会管理这个方法
     * setUserVisibleHint 这个是 ViewPager 的行为，它始终都是先行与 Fragment 的生命周期调用的。
     * 我们之所以能用懒加载这种办法，主要是因为预加载的 Fragment 已经创建完成一路调用了 onAttach --> onPause
     * 启动activity打印日志如下：
     * 08-11 11:33:36.156    7162-7162/me.febsky.test V/Fragment1﹕ setUserVisibleHint false
     * 08-11 11:33:36.156    7162-7162/me.febsky.test V/Fragment2﹕ setUserVisibleHint false
     * 08-11 11:33:36.157    7162-7162/me.febsky.test V/Fragment1﹕ setUserVisibleHint true
     * 08-11 11:33:36.158    7162-7162/me.febsky.test V/Fragment1﹕ onAttach
     * 08-11 11:33:36.158    7162-7162/me.febsky.test V/Fragment1﹕ onCreate
     * 08-11 11:33:36.159    7162-7162/me.febsky.test V/Fragment1﹕ onCreateView
     * 08-11 11:33:36.160    7162-7162/me.febsky.test V/Fragment1﹕ onActivityCreated
     * 08-11 11:33:36.160    7162-7162/me.febsky.test V/Fragment1﹕ onResume()
     * 08-11 11:33:36.160    7162-7162/me.febsky.test V/Fragment2﹕ onAttach
     * 08-11 11:33:36.160    7162-7162/me.febsky.test V/Fragment2﹕ onCreate
     * 08-11 11:33:36.160    7162-7162/me.febsky.test V/Fragment2﹕ onCreateView
     * 08-11 11:33:36.161    7162-7162/me.febsky.test V/Fragment2﹕ onActivityCreated
     * 08-11 11:33:36.161    7162-7162/me.febsky.test V/Fragment2﹕ onResume()
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isViewCreated && !isLoadDataCompleted) {
            isLoadDataCompleted = true;
            lazyInitData();
        }
    }

    /**
     * Author: liuqiang
     * Time: 2017-01-11 16:07
     * Description: 这个地方是为了应对ViewPager第一页  FragmentPagerAdapter
     * setUserVisibleHint 会在onCreateView 之前调用，这就导致了isViewCreated为false
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getUserVisibleHint()) {
            isLoadDataCompleted = true;
            lazyInitData();
        }
    }


    /**
     * 子类实现加载数据的方法
     */
    public abstract void lazyInitData();

    @Override
    protected void initData() {

    }
}
