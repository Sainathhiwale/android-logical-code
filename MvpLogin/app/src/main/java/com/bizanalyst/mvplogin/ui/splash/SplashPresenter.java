package com.bizanalyst.mvplogin.ui.splash;

import com.bizanalyst.mvplogin.data.DataManager;
import com.bizanalyst.mvplogin.ui.base.BasePresenter;
public class SplashPresenter<V extends SplashMvpView> extends BasePresenter<V> implements SplashMvpPresenter<V> {

    public SplashPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void decideNextActivity() {
        if (getDataManager().getLoggedInMode()) {
            getMvpView().openMainActivity();
        } else {
            getMvpView().openLoginActivity();
        }
    }
}
