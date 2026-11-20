package com.androidbook.recyclerviewexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val item: ArrayList<MyItem>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val itemImage: ImageView = itemView.findViewById(R.id.imageView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(view)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item : MyItem = item.get(position)
        holder.textView.text = item.name
        holder.itemImage.setImageResource(item.image)

        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context,holder.textView.text, Toast.LENGTH_SHORT).show()
        }
    }
    override fun getItemCount(): Int {
        return item.size
    }
}
