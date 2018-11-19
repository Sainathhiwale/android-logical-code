package com.melayer.demo.mvpdemo.base

/**
 * Created by melayer on 19/10/18.
 */
interface BasePresenter<T> {


    fun start()

    var view: T
}