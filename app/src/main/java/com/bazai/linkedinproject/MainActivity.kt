package com.bazai.linkedinproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bazai.linkedinproject.adapters.NewsAdapter
import com.bazai.linkedinproject.utils.Constants.Companion.API_NEWS_KEY
import com.bazai.linkedinproject.view_model.NewsViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val newsViewModel: NewsViewModel by viewModel()
    private val newsAdapter: NewsAdapter by inject()
    private lateinit var searchText: EditText
    private lateinit var rvNews: RecyclerView
    private lateinit var results:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()


        searchText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                newsViewModel.getNews(
                    p0.toString(),
                    "2023-07-04",
                    "publishedAt",
                    API_NEWS_KEY
                )
            }

        })

        newsViewModel.getNewsLiveData.observe(this) { news ->
            if (news != null) {
                results.text = "There are ${ news.totalResults } articles"
                newsAdapter.differ.submitList(news.articles)
                rvNews.apply {
                    layoutManager =
                        LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    adapter = newsAdapter
                }
            }
        }


    }

    private fun initializeViews() {
        searchText = findViewById(R.id.search_field)
        rvNews = findViewById(R.id.rv_news)
        results = findViewById(R.id.results_text)
    }
}