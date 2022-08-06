package com.whatsscan.ktorexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.whatsscan.ktorexample.R
import com.whatsscan.ktorexample.models.ResponseModel

class DataAdapter(var listReceived: List<ResponseModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflatedView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_layout, parent, false)
        return InItDataClass(inflatedView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataHolder = holder as InItDataClass
        // URL
        dataHolder.imgReceived.load(listReceived[position].image)
        dataHolder.title.text = listReceived[position].title
        dataHolder.description.text = listReceived[position].description
    }

    override fun getItemCount(): Int {
        return listReceived.size
    }

    class InItDataClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgReceived: ImageView = itemView.findViewById(R.id.imgReceived)
        val title: TextView = itemView.findViewById(R.id.txtTitle)
        val description: TextView = itemView.findViewById(R.id.txtDescription)
    }
}