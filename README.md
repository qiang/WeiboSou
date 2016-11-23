在Google给出的应用实例中，他把View和Presenter放到一块了，也行

## 用到的比较重要的库

    1. 网络请求Volley

       因为考虑请求的是抓取的微博数据，可能还抓取别的数据，不算格式化的返回数据，所以没有用Retrofit，而选择了Volley

    2. 网络协议栈 OkHttp

    3. 通信EventBus

    4. ButterKnife

    5. 下拉加载 ultra-ptr

    6. 加载网络图片glide

    7. 大图预览 PhotoView

    8. Log日志 [logger](https://github.com/orhanobut/logger)

    9. ORM sugar

    10. 参考XRecyclerView的源码


## 架构 MVP

    Google推荐的MVP是把View和Presenter的接口放到了一个类（Contract）下来统一管理，只是一种管理代码的方式而已

    [Google MVP范例](https://github.com/googlesamples/android-architecture)

## 目的

   主要为了练习使用MVP架构模式和自己封装BaseActivity作为以后项目的基本框架