package com.melayer.demo.mvpdemo.login.domain

import com.google.gson.annotations.SerializedName

data class Login(

	@field:SerializedName("password")
	var password: String? = null,

	@field:SerializedName("emailId")
	var emailId: String? = null
)