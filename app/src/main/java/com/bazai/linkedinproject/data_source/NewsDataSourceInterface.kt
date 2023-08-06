package com.bazai.linkedinproject.data_source

import com.bazai.linkedinproject.data_model.ResponseNews
import io.reactivex.Single
import retrofit2.http.Query

interface NewsDataSourceInterface {
    fun getNews(
        query: String,
        fromDate: String,
        sortBy: String,
        apiKey: String
    ): Single<ResponseNews>
}