package com.bazai.linkedinproject.repository

import com.bazai.linkedinproject.data_model.ResponseNews
import com.bazai.linkedinproject.data_source.NewsDataSourceInterface
import io.reactivex.Single

class NewsRepositoryImpl(private val newsDataSourceInterface: NewsDataSourceInterface):NewsRepositoryInterface {
    override fun getNews(
        query: String,
        fromDate: String,
        sortBy: String,
        apiKey: String
    ): Single<ResponseNews> {
        return newsDataSourceInterface.getNews(query, fromDate, sortBy, apiKey)
    }
}