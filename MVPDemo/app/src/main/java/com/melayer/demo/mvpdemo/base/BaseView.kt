package com.melayer.demo.mvpdemo.base

/**
 * Created by melayer on 19/10/18.
 */
interface BaseView <out T : BasePresenter<*>> {

    val presenter: T
}