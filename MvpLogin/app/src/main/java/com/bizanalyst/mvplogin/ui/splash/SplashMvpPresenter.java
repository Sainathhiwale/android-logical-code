package com.bizanalyst.mvplogin.ui.splash;

import com.bizanalyst.mvplogin.ui.base.MvpPresenter;

public interface SplashMvpPresenter<V extends SplashMvpView> extends MvpPresenter<V> {

    void decideNextActivity();

}
