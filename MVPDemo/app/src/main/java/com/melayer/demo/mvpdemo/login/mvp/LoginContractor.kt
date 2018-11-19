package com.melayer.demo.mvpdemo.login.mvp

import com.melayer.demo.mvpdemo.base.BasePresenter
import com.melayer.demo.mvpdemo.base.BaseView
import com.melayer.demo.mvpdemo.login.domain.Login

/**
 * Created by melayer on 19/10/18.
 */
interface LoginContractor {
    interface View : BaseView<Presenter> {
        fun doLogin(name:String)
        fun errorLogin()
    }

    interface Presenter : BasePresenter<View> {
        fun getLogin(login:Login)
    }
}