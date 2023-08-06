package com.bazai.linkedinproject.di

import android.app.Application
import com.bazai.linkedinproject.adapters.NewsAdapter
import com.bazai.linkedinproject.data_source.NewsDataSource
import com.bazai.linkedinproject.network.NewsApiService
import com.bazai.linkedinproject.network.newsApi
import com.bazai.linkedinproject.repository.NewsRepositoryImpl
import com.bazai.linkedinproject.repository.NewsRepositoryInterface
import com.bazai.linkedinproject.view_model.NewsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App:Application() {
    override fun onCreate() {
        super.onCreate()

        val myModule = module {
            single<NewsApiService> { newsApi() }

            // Instance for news source and repository
            factory<NewsRepositoryInterface> { NewsRepositoryImpl(NewsDataSource(get())) }
            viewModel { NewsViewModel(get()) }
            factory { NewsAdapter() }
        }

        startKoin {
            androidContext(this@App)
            modules(myModule)
        }
    }
}