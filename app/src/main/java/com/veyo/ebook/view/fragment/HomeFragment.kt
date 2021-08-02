package com.veyo.ebook.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.veyo.ebook.R
import com.veyo.ebook.databinding.FragmentHomeBinding
import com.veyo.ebook.di.utilities.Injectable
import com.veyo.ebook.di.injectViewModel
import com.veyo.ebook.view.adapter.HomeTabAdapter
import com.veyo.ebook.viewmodel.HomeViewModel
import javax.inject.Inject

/**
 * Created by Veyo on 7/30/2021.
 */
class HomeFragment : BindingFragmentViewModel<FragmentHomeBinding, HomeViewModel>(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun createViewModel(): HomeViewModel = injectViewModel(viewModelFactory)

    override fun getLayoutResource(): Int = R.layout.fragment_home

    override fun onMyViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onMyViewCreated(view, savedInstanceState)
        activity?.let {
            val fragments =
                mutableListOf<Fragment>(
                    BookListFragment(),
                    TopBookListFragment()
                )
            dataBinding.viewPager.adapter = HomeTabAdapter(fragments, it)
            TabLayoutMediator(dataBinding.tabLayout, dataBinding.viewPager) { tab, position ->
                tab.text =
                    if (position == 0) getString(R.string.book_list) else getString(R.string.top_book)
            }.attach()
        }
    }
}