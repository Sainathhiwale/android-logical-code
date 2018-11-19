package com.melayer.demo.mvpdemo.login.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.melayer.demo.mvpdemo.R
import com.melayer.demo.mvpdemo.login.domain.Login
import com.melayer.demo.mvpdemo.login.mvp.LoginContractor
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(),LoginContractor.View {
    override val presenter: LoginContractor.Presenter by inject()

    override fun doLogin(name: String) {
        text.text="Welcome "+name
    }

    override fun errorLogin() {
        Toast.makeText(applicationContext,"Try again",Toast.LENGTH_LONG)
                .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.getLogin(getData())
    }
    override fun onResume() {
        presenter.view = this
        super.onResume()
    }
    private fun getData(): Login {
        val login=Login()
        login.emailId="saindane.rajan@gmail.com"
        login.password="rajan123"
        return login
    }
}
