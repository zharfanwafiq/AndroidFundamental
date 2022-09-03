package com.zharfan.androidfundamental.data.api.news

import com.zharfan.androidfundamental.data.response.news.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines?country=id&category=science")
    fun getNews(@Query("apiKey") apiKey:String):Call<NewsResponse>
}