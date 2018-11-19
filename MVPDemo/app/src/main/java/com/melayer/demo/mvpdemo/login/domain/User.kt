package com.melayer.demo.mvpdemo.login.domain

import com.google.gson.annotations.SerializedName

data class User(

	@field:SerializedName("offer")
	val offer: Any? = null,

	@field:SerializedName("firstName")
	val firstName: String? = null,

	@field:SerializedName("lastName")
	val lastName: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("phoneNumber")
	val phoneNumber: String? = null,

	@field:SerializedName("removeUserFlag")
	val removeUserFlag: Boolean? = null,

	@field:SerializedName("coupenDiscount")
	val coupenDiscount: Int? = null,

	@field:SerializedName("addressLine1")
	val addressLine1: String? = null,

	@field:SerializedName("emailId")
	val emailId: String? = null,

	@field:SerializedName("addressLine2")
	val addressLine2: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("codeStatus")
	val codeStatus: Any? = null
)