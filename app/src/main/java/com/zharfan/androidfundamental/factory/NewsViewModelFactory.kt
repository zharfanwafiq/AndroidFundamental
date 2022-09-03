package com.zharfan.androidfundamental.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zharfan.androidfundamental.data.news.NewsRepository
import com.zharfan.androidfundamental.di.Injection
import com.zharfan.androidfundamental.viewmodel.NewsViewModel

class NewsViewModelFactory private constructor(
    private val newsRepository: NewsRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(newsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)

    }

    companion object {
        @Volatile
        private var instance: NewsViewModelFactory? = null
        fun getInstance(context: Context): NewsViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: NewsViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }

}