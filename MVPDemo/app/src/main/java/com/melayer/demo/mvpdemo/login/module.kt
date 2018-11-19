package com.melayer.demo.mvpdemo.login

import com.melayer.demo.mvpdemo.login.data.LoginRepository
import com.melayer.demo.mvpdemo.login.data.source.remote.LoginRemoteDataSource
import com.melayer.demo.mvpdemo.login.mvp.LoginContractor
import com.melayer.demo.mvpdemo.login.mvp.LoginPresenter
import com.melayer.demo.mvpdemo.login.ui.MainActivity
import org.koin.dsl.module.applicationContext

/**
 * Created by melayer on 19/10/18.
 */
val loginModule = applicationContext {

    factory { MainActivity() }
    factory { LoginPresenter(get()) as LoginContractor.Presenter }
    bean("remoteLogin") { LoginRemoteDataSource(get()) }
    bean {
        LoginRepository(
                get("remoteLogin")
        )
    }
}