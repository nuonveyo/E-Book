package com.veyo.ebook.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.veyo.ebook.R
import com.veyo.ebook.view.adapter.viewholder.BindingViewHolder

/**
 * Created by Veyo on 8/1/2021.
 */
@Suppress("UNCHECKED_CAST")
abstract class RefreshableAdapter<D, T : RecyclerView.ViewHolder?>(private var items: MutableList<D>) :
    RecyclerView.Adapter<T>() {

    private var haseMoreItems = true
    private var preventBindLoadMoreItem = true

    companion object {
        private const val VIEW_ITEM = 0X002
        private const val VIEW_PROGRESS = 0X003
    }

    /**
     * Items with a generic model class type to set into adapter
     */
    var data: MutableList<D>
        get() = items
        set(value) {
            items = value
        }

    var loadingMore: Boolean
        get() = haseMoreItems
        set(value) {
            haseMoreItems = value
        }

    var preventLoadMoreItem: Boolean
        get() = preventBindLoadMoreItem
        set(value) {
            preventBindLoadMoreItem = value
        }

    /**
     * Add one more last item for loading layout whenever has a paginatin to load
     */
    override fun getItemCount(): Int {
        return if (haseMoreItems) {
            items.size + 1
        } else {
            items.size
        }
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        if (preventBindLoadMoreItem) {
            if (position < items.size) {
                onBindData(holder, position)
            }
        } else {
            onBindData(holder, position)
        }
    }

    /**
     * Add more items to adapter
     */
    fun addLoadMoreData(items: List<D>) {
        val previousItemCount = itemCount
        this.items.addAll(items)
        notifyItemRangeInserted(previousItemCount + 1, items.size)
    }

    fun clear() {
        items.clear()
        loadingMore = false
        notifyDataSetChanged()
    }

    /**
     * Binding a loading layout whenever getItemViewType equal the last one, otherwise bind a general layout
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        return if (viewType == VIEW_PROGRESS) {
            val binding = DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                R.layout.layout_loading_more_item, parent, false
            )
            BindingViewHolder(binding) as T
        } else {
            onCreateView(parent, viewType)
        }
    }

    abstract fun onCreateView(parent: ViewGroup?, viewType: Int): T

    abstract fun onBindData(holder: T, position: Int)

    /**
     * Return a general item_view_type whenever scroll down items doesn't yet arrive the last item
     */
    override fun getItemViewType(position: Int): Int {
        return if (position < items.size) {
            VIEW_ITEM
        } else {
            VIEW_PROGRESS
        }
    }
}