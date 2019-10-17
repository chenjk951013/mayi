package com.cl.mayi.myapplication.base;


import com.cl.mayi.myapplication.base.interfaces.IPresenter;
import com.cl.mayi.myapplication.base.interfaces.IView;

public class BasePresenter<V extends IView> implements IPresenter<V> {
    protected V mView;

    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public boolean isViewAttached() {
        return mView != null;
    }
}
