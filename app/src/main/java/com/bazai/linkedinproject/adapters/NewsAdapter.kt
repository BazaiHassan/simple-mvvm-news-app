package com.bazai.linkedinproject.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bazai.linkedinproject.R
import com.bazai.linkedinproject.data_model.ArticlesItem
import com.bazai.linkedinproject.data_model.ResponseNews
import com.bumptech.glide.Glide

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<ArticlesItem>() {
        override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
            return oldItem.url === newItem.url
        }

        override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_news,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = differ.currentList[position]
        val title: TextView? = holder.itemView.findViewById(R.id.titleTextView)
        val describe: TextView? = holder.itemView.findViewById(R.id.descriptionTextView)
        val image: ImageView? = holder.itemView.findViewById(R.id.newsImageView)
        val date: TextView? = holder.itemView.findViewById(R.id.dateTextView)
        Log.d("TAG_Adapter", "onBindViewHolder:${news.title} ")
        holder.itemView.apply {
            title?.text = news.title
            describe?.text = news.description
            date?.text = news.publishedAt
            if (image != null) {
                Glide
                    .with(holder.itemView.context)
                    .load(news.urlToImage)
                    .centerCrop()
                    .placeholder(R.drawable.news)
                    .into(image)
            }

            setOnNewsClickListener {
                onNewsClickListener?.let {
                    it(news)
                }
            }
        }
    }

    private var onNewsClickListener: ((ArticlesItem) -> Unit)? = null

    private fun setOnNewsClickListener(listener: (ArticlesItem) -> Unit) {
        onNewsClickListener = listener
    }
}