package com.veyo.ebook.view.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.veyo.ebook.R
import com.veyo.ebook.databinding.FragmentBookDetailBinding
import com.veyo.ebook.di.utilities.Injectable
import com.veyo.ebook.di.injectViewModel
import com.veyo.ebook.model.Review
import com.veyo.ebook.utility.ID
import com.veyo.ebook.view.adapter.ReviewListAdapter
import com.veyo.ebook.viewmodel.BookDetailViewModel
import javax.inject.Inject

/**
 * Created by Veyo on 8/1/2021.
 */
class BookDetailFragment :
    BindingFragmentViewModel<FragmentBookDetailBinding, BookDetailViewModel>(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun createViewModel(): BookDetailViewModel = injectViewModel(viewModelFactory)

    override fun getLayoutResource(): Int = R.layout.fragment_book_detail

    override fun onMyViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onMyViewCreated(view, savedInstanceState)
        val id = arguments?.getString(ID)
        viewModel.getBookById(id)
        viewModel.book.observe(viewLifecycleOwner, Observer {
            bindAdapterItem(it.reviews)
        })
    }

    private fun bindAdapterItem(items: MutableList<Review>?) {
        dataBinding.rvReviews.layoutManager = LinearLayoutManager(context)
        dataBinding.rvReviews.adapter = ReviewListAdapter(items)
    }
}