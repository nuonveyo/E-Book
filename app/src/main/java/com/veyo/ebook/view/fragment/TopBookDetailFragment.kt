package com.veyo.ebook.view.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.veyo.ebook.R
import com.veyo.ebook.databinding.FragmentTopBookDetailBinding
import com.veyo.ebook.di.utilities.Injectable
import com.veyo.ebook.di.injectViewModel
import com.veyo.ebook.utility.ID
import com.veyo.ebook.viewmodel.TopBookDetailViewModel
import javax.inject.Inject

/**
 * Created by Veyo on 8/1/2021.
 */
class TopBookDetailFragment :
    BindingFragmentViewModel<FragmentTopBookDetailBinding, TopBookDetailViewModel>(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun createViewModel(): TopBookDetailViewModel = injectViewModel(viewModelFactory)

    override fun getLayoutResource(): Int = R.layout.fragment_top_book_detail

    override fun onMyViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onMyViewCreated(view, savedInstanceState)
        val id = arguments?.getString(ID)
        viewModel.getTopBookById(id)
    }
}