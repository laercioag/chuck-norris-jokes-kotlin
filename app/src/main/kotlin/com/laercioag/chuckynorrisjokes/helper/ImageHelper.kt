package com.laercioag.chuckynorrisjokes.helper

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object ImageHelper {

    fun loadImage(context: Context, view: ImageView, string: String) {
        Glide.with(context).load(string).into(view)
    }

    fun loadGif(context: Context, view: ImageView, resourceId: Int) {
        Glide.with(context).asGif().load(resourceId).into(view)
    }
}
