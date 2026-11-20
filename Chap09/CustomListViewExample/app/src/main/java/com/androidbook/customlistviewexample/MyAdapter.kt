package com.androidbook.customlistviewexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MyAdapter(val itemList : ArrayList<MyItem>) : BaseAdapter() {
    override fun getCount(): Int {
        return itemList.size
    }
    override fun getItem(p0: Int): Any {
        return itemList[p0]
    }
    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val item = this.itemList[p0]
        var convertView = p1
        convertView = LayoutInflater.from(p2?.context).inflate(R.layout.listview_item,
                                            p2, false)
        val itemText: TextView = convertView.findViewById(R.id.textView)
        val itemImage: ImageView = convertView.findViewById(R.id.imageView)
        itemText.text = item.name
        itemImage.setImageResource(item.image)
        return convertView
    }
}
