package com.veyo.ebook.view.adapter

import android.view.ViewGroup
import com.veyo.ebook.R
import com.veyo.ebook.databinding.ListItemBookBinding
import com.veyo.ebook.model.Book
import com.veyo.ebook.view.adapter.viewholder.BindingViewHolder

/**
 * Created by Veyo on 8/1/2021.
 */
class BookListAdapter(
    private val items: MutableList<Book>,
    private val callback: (Book?) -> Unit = {}
) : RefreshableAdapter<Book, BindingViewHolder>(items) {

    override fun onCreateView(parent: ViewGroup?, viewType: Int): BindingViewHolder =
        BindingViewHolder.Builder(parent, R.layout.list_item_book).build()

    override fun onBindData(holder: BindingViewHolder, position: Int) {
        val binding = holder.binding as ListItemBookBinding
        val item = items[position]
        binding.tvTitle.text = holder.context.getString(R.string.title, item.title)
        binding.tvAuthor.text = holder.context.getString(R.string.author, item.author)
        binding.tvDate.text =
            holder.context.getString(R.string.publish_date, item.getPublishDate())

        binding.container.setOnClickListener {
            callback(item)
        }
    }
}