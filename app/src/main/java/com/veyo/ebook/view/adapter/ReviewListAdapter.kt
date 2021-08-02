package com.veyo.ebook.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.veyo.ebook.R
import com.veyo.ebook.databinding.ListItemReviewBinding
import com.veyo.ebook.model.Review
import com.veyo.ebook.view.adapter.viewholder.BindingViewHolder

/**
 * Created by Veyo on 8/1/2021.
 */
class ReviewListAdapter(
    private val items: MutableList<Review>?
) : RecyclerView.Adapter<BindingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder =
        BindingViewHolder.Builder(parent, R.layout.list_item_review).build()

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val binding = holder.binding as ListItemReviewBinding
        val item = items?.get(position)
        binding.tvTitle.text = item?.body
    }

    override fun getItemCount(): Int = items?.size ?: 0
}