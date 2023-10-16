package com.example.myapplication

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class NewsViewHolder(view: View):ViewHolder(view) {

    val cbNews:CheckBox = view.findViewById(R.id.cbNews)
    val ivDelete: ImageView = view.findViewById(R.id.ivDelete)
}