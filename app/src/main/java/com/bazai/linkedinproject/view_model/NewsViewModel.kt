package com.bazai.linkedinproject.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bazai.linkedinproject.data_model.ResponseNews
import com.bazai.linkedinproject.repository.NewsRepositoryInterface
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class NewsViewModel(private val newsRepositoryInterface: NewsRepositoryInterface):ViewModel() {
    val getNewsLiveData = MutableLiveData<ResponseNews>()
    private val compositeDisposable = CompositeDisposable()

    fun getNews(query: String, fromDate: String, sortBy: String, apiKey: String){
        newsRepositoryInterface.getNews(query, fromDate, sortBy, apiKey).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ResponseNews>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG_GET_NEWS_ERROR", "onError:${e.message} ")
                }

                override fun onSuccess(t: ResponseNews) {
                    getNewsLiveData.value = t
                    Log.d("TAG_GET_NEWS_RESPONSE", "onError:${t.articles} ")
                }

            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}