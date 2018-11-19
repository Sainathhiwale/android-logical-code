package com.melayer.demo.mvpdemo

import android.app.Application
import org.koin.android.ext.android.startKoin

/**
 * Created by melayer on 19/10/18.
 */
class MVPDemoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, AppModule)
    }
}