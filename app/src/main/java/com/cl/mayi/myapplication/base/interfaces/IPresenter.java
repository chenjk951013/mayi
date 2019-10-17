package com.cl.mayi.myapplication.base.interfaces;

public interface IPresenter<V extends IView> {
    /**
     * 建立联系
     */
    void attachView(V view);

    /**
     * 取消联系
     */
    void detachView();

    /**
     * 判断View是否已经销毁
     */
    boolean isViewAttached();


}
