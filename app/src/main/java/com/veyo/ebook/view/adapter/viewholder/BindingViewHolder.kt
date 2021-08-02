package com.veyo.ebook.view.adapter.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Veyo on 06-May-2019.
 * Binding view holder from xml layout to adapter class
 */
open class BindingViewHolder(itemView: ViewDataBinding) : RecyclerView.ViewHolder(itemView.root) {
    var binding: ViewDataBinding protected set

    val context: Context
        get() = binding.root.context

    init {
        binding = itemView
    }

    class Builder(
        private val parent: ViewGroup?, @param:LayoutRes @field:LayoutRes
        private val layoutRes: Int
    ) {

        fun build(): BindingViewHolder {
            val inflater = LayoutInflater.from(parent?.context)
            val binding = DataBindingUtil.inflate<ViewDataBinding>(
                inflater,
                layoutRes,
                parent,
                false
            )
            return BindingViewHolder(binding)
        }
    }


}
