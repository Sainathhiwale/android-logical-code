package com.bizanalyst.mvplogin;

import android.app.Application;

import com.bizanalyst.mvplogin.data.DataManager;
import com.bizanalyst.mvplogin.data.SharedPrefsHelper;


public class MvpApp extends Application {

    DataManager dataManager;

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPrefsHelper sharedPrefsHelper = new SharedPrefsHelper(getApplicationContext());
        dataManager = new DataManager(sharedPrefsHelper);

    }

    public DataManager getDataManager() {
        return dataManager;
    }

}
