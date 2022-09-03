package com.zharfan.androidfundamental.di

import android.content.Context
import com.zharfan.androidfundamental.data.api.news.ApiConfig
import com.zharfan.androidfundamental.data.local.room.NewsDatabase
import com.zharfan.androidfundamental.data.news.NewsRepository
import com.zharfan.androidfundamental.utils.news.AppExecutors

object Injection {
    fun provideRepository(context: Context): NewsRepository {
        val apiService = ApiConfig.getApiService()
        val database = NewsDatabase.getInstance(context)
        val dao = database.newsDao()
        val appExecutors = AppExecutors()
        return NewsRepository.getInstance(apiService, dao, appExecutors)
    }
}