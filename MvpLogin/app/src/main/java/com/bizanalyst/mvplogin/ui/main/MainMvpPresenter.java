package com.bizanalyst.mvplogin.ui.main;

import com.bizanalyst.mvplogin.ui.base.MvpPresenter;

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    String getEmailId();

    void setUserLoggedOut();

}