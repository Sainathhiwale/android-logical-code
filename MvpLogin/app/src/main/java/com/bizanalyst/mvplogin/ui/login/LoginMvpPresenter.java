package com.bizanalyst.mvplogin.ui.login;

import com.bizanalyst.mvplogin.ui.base.MvpPresenter;


public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {

    void startLogin(String emailId);

}
