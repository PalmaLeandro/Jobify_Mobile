package com.example.root.jobify.Utilities;

/**
 * Created by root on 06/09/16.
 */
public class BasePresenter<T> {

    private T mViewInstance;

    public BasePresenter(T viewInstance) {
        this.mViewInstance = viewInstance;
    }

    public BasePresenter() { }

    public void setView(T viewInstance) {
        this.mViewInstance = viewInstance;
    }

    protected T getView() {
        return mViewInstance;
    }

    public boolean isViewAttached() {
        return mViewInstance != null;
    }

    public void detachView() {
        mViewInstance = null;
    }

}