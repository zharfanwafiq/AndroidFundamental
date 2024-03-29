package com.zharfan.androidfundamental.data.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.zharfan.androidfundamental.BuildConfig
import com.zharfan.androidfundamental.data.api.news.ApiService
import com.zharfan.androidfundamental.data.local.entity.NewsEntity
import com.zharfan.androidfundamental.data.local.room.NewsDao
import com.zharfan.androidfundamental.data.response.news.NewsResponse
import com.zharfan.androidfundamental.utils.news.AppExecutors
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsRepository private constructor(
    private val apiService: ApiService,
    private val newsDao: NewsDao,
    private val appExecutors: AppExecutors
) {
    private val result = MediatorLiveData<Result<List<NewsEntity>>>()
    private val apiKey = BuildConfig.API_KEY

    val getBookmarkedNews: LiveData<List<NewsEntity>> = newsDao.getBookmarkedNews()

    fun getHeadlineNews(): LiveData<Result<List<NewsEntity>>> {
        result.value = Result.Loading
        val client = apiService.getNews(apiKey)
        client.enqueue(object : Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    val articles = response.body()?.articles
                    val newsList = ArrayList<NewsEntity>()
                    appExecutors.diskID.execute {
                        articles?.forEach { articlesItem ->
                            val isBookmarked = newsDao.isNewsBookmarked(articlesItem.title)
                            val news = NewsEntity(
                                articlesItem.title,
                                articlesItem.publishedAt,
                                articlesItem.urlToImage,
                                articlesItem.url,
                                isBookmarked
                            )
                            newsList.add(news)
                        }
                        newsDao.deleteAll()
                        newsDao.insertNews(newsList)
                    }
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                result.value = Result.Error(t.message.toString())
            }

        })

        val localData = newsDao.getNews()
        result.addSource(localData) {
            result.value = Result.Success(it)
        }
        return result
    }

    fun setBookmarkedNews(newsEntity: NewsEntity, bookmarkState: Boolean) {
        appExecutors.diskID.execute {
            newsEntity.isBookmarked = bookmarkState
            newsDao.updateNews((newsEntity))
        }
    }

    companion object {
        @Volatile
        private var instance: NewsRepository? = null
        fun getInstance(
            apiService: ApiService,
            newsDao: NewsDao,
            appExecutors: AppExecutors
        ): NewsRepository =
            instance ?: synchronized(this) {
                instance ?: NewsRepository(apiService, newsDao, appExecutors)
            }.also { instance = it }
    }
}