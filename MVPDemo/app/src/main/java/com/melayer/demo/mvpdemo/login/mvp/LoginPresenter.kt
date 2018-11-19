package com.melayer.demo.mvpdemo.login.mvp

import com.melayer.demo.mvpdemo.login.data.LoginRepository
import com.melayer.demo.mvpdemo.login.domain.Login
import com.melayer.demo.mvpdemo.login.domain.LoginResponse
import org.koin.standalone.KoinComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by melayer on 19/10/18.
 */
class LoginPresenter(
        private val loginRepository: LoginRepository
):LoginContractor.Presenter,KoinComponent {
    override fun start() {

    }

    override lateinit var view: LoginContractor.View

    override fun getLogin(login: Login) {
        loginRepository.getLogin(login)
                .enqueue(object :Callback<LoginResponse>{
                    override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
                        view.errorLogin()
                    }

                    override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {
                        if(response?.code()==200)
                        {
                         view.doLogin(response.body()?.user?.firstName.toString())
                        }
                    }
                })
    }
}