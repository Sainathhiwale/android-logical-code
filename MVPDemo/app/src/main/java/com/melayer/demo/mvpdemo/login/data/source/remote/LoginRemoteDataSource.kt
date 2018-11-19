package com.melayer.demo.mvpdemo.login.data.source.remote

import android.util.Log
import com.melayer.demo.mvpdemo.login.data.source.LoginDataSource
import com.melayer.demo.mvpdemo.login.domain.Login
import com.melayer.demo.mvpdemo.login.domain.LoginResponse
import retrofit2.Call
import retrofit2.Retrofit

/**
 * Created by melayer on 19/10/18.
 */
class LoginRemoteDataSource(
        private val retrofit: Retrofit
):LoginDataSource   {
    override fun getLogin(login: Login): Call<LoginResponse> {
        val apiService = LoginApi.create(retrofit)
        val call=apiService.userLogin(login)
        Log.i("@rajan","Login url =====> ${call.request().url()}")
        return call
    }
}