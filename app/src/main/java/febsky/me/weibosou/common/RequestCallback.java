package febsky.me.weibosou.common;

/**
 * Author: liuqiang
 * Date: 2016-08-18
 * Time: 17:07
 * Description:
 */
public interface RequestCallback<T> {


    /**
     * 请求之前调用
     */
    void beforeRequest();

    /**
     * 请求错误调用
     *
     * @param msg 错误信息
     */
    void requestError(String msg);

    /**
     * 请求完成调用
     */
    void requestComplete();

    /**
     * 请求成功调用
     *
     * @param data 数据
     */
    void requestSuccess(T data);
}
