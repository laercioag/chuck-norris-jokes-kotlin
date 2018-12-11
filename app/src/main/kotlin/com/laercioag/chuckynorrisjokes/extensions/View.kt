package com.laercioag.chuckynorrisjokes.extensions

import android.view.View
import android.widget.ImageView
import androidx.annotation.RawRes
import com.bumptech.glide.Glide

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun ImageView.loadImage(string: String) {
    Glide.with(context).load(string).into(this)
}

fun ImageView.loadGif(@RawRes resourceId: Int) {
    Glide.with(context).asGif().load(resourceId).into(this)
}
