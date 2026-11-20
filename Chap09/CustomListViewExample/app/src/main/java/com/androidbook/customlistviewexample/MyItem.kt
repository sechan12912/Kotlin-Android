package com.androidbook.customlistviewexample

import android.app.LauncherActivity.ListItem
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class MyItem {
    var name: String
    var image: Int

    constructor(name: String, image: Int) {
        this.name = name
        this.image = image
    }
}
