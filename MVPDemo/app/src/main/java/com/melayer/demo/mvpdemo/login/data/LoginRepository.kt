package com.melayer.demo.mvpdemo.login.data

import com.melayer.demo.mvpdemo.login.data.source.LoginDataSource
import com.melayer.demo.mvpdemo.login.data.source.remote.LoginRemoteDataSource
import com.melayer.demo.mvpdemo.login.domain.Login
import com.melayer.demo.mvpdemo.login.domain.LoginResponse
import retrofit2.Call

/**
 * Created by melayer on 19/10/18.
 */
class LoginRepository(
        private val loginRemoteDataSource: LoginRemoteDataSource
):LoginDataSource {
    override fun getLogin(login: Login): Call<LoginResponse> =
        loginRemoteDataSource.getLogin(login)

}