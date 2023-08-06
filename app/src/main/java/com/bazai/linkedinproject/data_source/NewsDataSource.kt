package com.bazai.linkedinproject.data_source

import com.bazai.linkedinproject.data_model.ResponseNews
import com.bazai.linkedinproject.network.NewsApiService
import io.reactivex.Single

class NewsDataSource(private val newsApiService: NewsApiService):NewsDataSourceInterface {
    override fun getNews(
        query: String,
        fromDate: String,
        sortBy: String,
        apiKey: String
    ): Single<ResponseNews> {
        return newsApiService.getNews(query, fromDate, sortBy, apiKey)
    }
}