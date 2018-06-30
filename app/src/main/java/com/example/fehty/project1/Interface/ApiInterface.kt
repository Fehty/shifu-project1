package com.example.fehty.project1.Interface

import com.example.fehty.project1.Poco.ApiModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("/rest/v2/all")
    fun listApiModel() : Call<MutableList<ApiModel>>
}