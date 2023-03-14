package com.nativemobilebits.globe.utils

import androidx.recyclerview.widget.RecyclerView

/**
 * General adapter with common methods for RecyclerView
 */
abstract class BaseRecyclerViewAdapter : RecyclerView.Adapter<BaseViewHolder>() {

  protected var listener: RecyclerItemClickListener? = null

  interface RecyclerItemClickListener {
    fun onItemClick(item: Any?, position: Int)
  }

  abstract fun showItems(items: List<Any>?)
}