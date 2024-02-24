package com.example.mvp

import com.google.gson.annotations.SerializedName

data class User(

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("picture")
	val picture: String? = null
)
