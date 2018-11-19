package com.melayer.demo.mvpdemo

import android.util.Log
import com.google.gson.GsonBuilder
import com.melayer.demo.mvpdemo.login.loginModule
import com.melayer.demo.mvpdemo.util.Appconstants.KEY_BASE_URL
import okhttp3.OkHttpClient
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by melayer on 19/10/18.
 */

val retrofitModule = applicationContext {
    bean {

        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request()
                    val response = chain.proceed(request)
                    if (response != null) {
                        Log.i("@iddriver","code : " + response.code())

                        if(response.code()==401) {

                        }

                    }
                    response!!
                }.build()


        Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(KEY_BASE_URL)
                .client(okHttpClient)
                .build()
    }
}

val AppModule = listOf(
        retrofitModule,
        loginModule
)
