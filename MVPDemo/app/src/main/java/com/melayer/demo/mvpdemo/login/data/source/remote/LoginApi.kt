package com.melayer.demo.mvpdemo.login.data.source.remote

import com.melayer.demo.mvpdemo.login.domain.Login
import com.melayer.demo.mvpdemo.login.domain.LoginResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Created by melayer on 19/10/18.
 */
interface LoginApi {
    @Headers("Content-Type: application/json")
    /**
     * function of Login Api
     * @Header token
     * @body login
     */
    @POST("user/loginUser")
    fun userLogin(@Body login: Login): Call<LoginResponse>


    companion object {

        fun create(retrofit : Retrofit): LoginApi {
            return retrofit.create(LoginApi::class.java)
        }
    }
}