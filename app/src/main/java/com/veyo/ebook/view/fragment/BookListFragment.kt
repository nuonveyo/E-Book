package com.veyo.ebook.view.fragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.veyo.ebook.R
import com.veyo.ebook.databinding.FragmentBookListBinding
import com.veyo.ebook.di.utilities.Injectable
import com.veyo.ebook.di.injectViewModel
import com.veyo.ebook.model.Book
import com.veyo.ebook.utility.ID
import com.veyo.ebook.view.adapter.BookListAdapter
import com.veyo.ebook.view.widget.LoaderMoreLayoutManager
import com.veyo.ebook.viewmodel.BookListViewModel
import javax.inject.Inject

/**
 * Created by Veyo on 8/1/2021.
 */
class BookListFragment : BindingFragmentViewModel<FragmentBookListBinding, BookListViewModel>(),
    Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var adapter: BookListAdapter? = null
    private var loaderMoreLayoutManager: LoaderMoreLayoutManager? = null

    override fun createViewModel(): BookListViewModel = injectViewModel(viewModelFactory)

    override fun getLayoutResource(): Int = R.layout.fragment_book_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.bookList.observe(viewLifecycleOwner, {
            bindAdapterItems(it)
        })
    }

    private fun bindAdapterItems(items: MutableList<Book>) {
        adapter = BookListAdapter(items) {
            view
                ?.findNavController()
                ?.navigate(
                    R.id.action_homeFragment_to_bookDetailFragment,
                    bundleOf(ID to it?.id())
                )
        }
        loaderMoreLayoutManager = LoaderMoreLayoutManager(context)
        dataBinding.rvItems.layoutManager = loaderMoreLayoutManager
        loaderMoreLayoutManager?.setOnLoadMoreListener {
            viewModel.loadMoreItem(
                {
                    adapter?.addLoadMoreData(it)
                    adapter?.loadingMore = viewModel.isHasMoreItems
                    loaderMoreLayoutManager?.isStopLoadMore = viewModel.isHasMoreItems
                    loaderMoreLayoutManager?.loadingFinished()
                }, {
                    adapter?.loadingMore = false
                    adapter?.notifyDataSetChanged()
                    loaderMoreLayoutManager?.loadingFinished()
                }
            )
        }
        dataBinding.rvItems.adapter = adapter
    }
}