package com.laercioag.chuckynorrisjokes.presentation.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.laercioag.chuckynorrisjokes.R
import com.laercioag.chuckynorrisjokes.domain.entity.Category
import com.laercioag.chuckynorrisjokes.extensions.capitalizeWords
import com.laercioag.chuckynorrisjokes.extensions.loadGif

private const val LIST_ITEM_VIEW = 1
private const val FOOTER_ITEM_VIEW = 2

class CategoriesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: MutableList<Category> = mutableListOf()
    var itemListener: (Category) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == LIST_ITEM_VIEW) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_categories_item, parent, false)
            ItemViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_categories_footer, parent, false)
            FooterViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return if (items.size > 0) {
            items.size + 1
        } else {
            0
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.title.text = items[position].description.capitalizeWords()
            holder.item.setOnClickListener { itemListener(items[position]) }
        } else if (holder is FooterViewHolder) {
            holder.image.loadGif(R.raw.chuck_norris_dancing)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < items.size) {
            LIST_ITEM_VIEW
        } else {
            FOOTER_ITEM_VIEW
        }
    }

    fun setItems(items: List<Category>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item: ConstraintLayout = itemView.findViewById(R.id.item)
        val title: AppCompatTextView = itemView.findViewById(R.id.title)
    }

    class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: AppCompatImageView = itemView.findViewById(R.id.footerImage)
    }
}
