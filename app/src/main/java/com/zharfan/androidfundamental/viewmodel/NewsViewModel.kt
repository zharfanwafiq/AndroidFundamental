package com.zharfan.androidfundamental.viewmodel

import androidx.lifecycle.ViewModel
import com.zharfan.androidfundamental.data.local.entity.NewsEntity
import com.zharfan.androidfundamental.data.news.NewsRepository

class NewsViewModel(private val newsRepository: NewsRepository): ViewModel() {
    val getHeadlineNews = newsRepository.getHeadlineNews()
    val getBookmarkedNews = newsRepository.getBookmarkedNews

    fun saveNews(newsEntity: NewsEntity){
        newsRepository.setBookmarkedNews(newsEntity,true)
    }

    fun deleteNews(newsEntity: NewsEntity){
        newsRepository.setBookmarkedNews(newsEntity,false)
    }
}