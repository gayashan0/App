package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.NewsViewHolder
import com.example.myapplication.R
import com.example.myapplication.database.News
import com.example.myapplication.database.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsAdapter(items:List<News>, repository: NewsRepository,
    viewModel:MainActivityData): RecyclerView.Adapter<NewsViewHolder>() {

    var context: Context? = null
    val items = items
    val repository = repository
    val viewModel = viewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_news,parent,false)
        context = parent.context

        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.cbNews.text = items.get(position).item
        holder.ivDelete.setOnClickListener {
            val isChecked = holder.cbNews.isChecked

            if(isChecked){
                CoroutineScope(Dispatchers.IO).launch {
                    repository.delete(items.get(position))
                    val data = repository.getAllNews()
                    withContext(Dispatchers.Main){
                        viewModel.setData(data)
                    }
                }
                Toast.makeText(context,"Item Deleted",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context,"Select the item to be deleted", Toast.LENGTH_LONG).show()
            }
            //Toast.makeText(context,"Hello from Button $position", Toast.LENGTH_LONG).show()
        }
    }
}