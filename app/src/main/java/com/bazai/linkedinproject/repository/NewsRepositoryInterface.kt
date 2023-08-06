package com.bazai.linkedinproject.repository

import com.bazai.linkedinproject.data_model.ResponseNews
import io.reactivex.Single

interface NewsRepositoryInterface {

    fun getNews(
        query: String,
        fromDate: String,
        sortBy: String,
        apiKey: String
    ): Single<ResponseNews>

}