package com.melayer.demo.mvpdemo.login.data.source

import com.melayer.demo.mvpdemo.login.domain.Login
import com.melayer.demo.mvpdemo.login.domain.LoginResponse
import retrofit2.Call

/**
 * Created by melayer on 19/10/18.
 */
interface LoginDataSource {
    fun getLogin(login:Login):Call<LoginResponse>
}