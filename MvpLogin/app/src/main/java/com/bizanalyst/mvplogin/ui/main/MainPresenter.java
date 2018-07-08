package com.bizanalyst.mvplogin.ui.main;

import com.bizanalyst.mvplogin.data.DataManager;
import com.bizanalyst.mvplogin.ui.base.BasePresenter;


public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {

    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public String getEmailId() {
        return getDataManager().getEmailId();
    }

    @Override
    public void setUserLoggedOut() {
        getDataManager().clear();
        getMvpView().openSplashActivity();
    }

}
