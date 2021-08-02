package com.veyo.ebook.view.fragment

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.veyo.ebook.BR
import com.veyo.ebook.viewmodel.BaseViewModel

/**
 * Created by Veyo Nuon on 10/11/2019.
 */
abstract class BindingFragmentViewModel<T : ViewDataBinding, V : BaseViewModel> :
    BindingFragment<T>() {

    lateinit var viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel()
        viewModel.myContext.set(context)
        viewModel.init()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.view.set(view)
        setVariable(BR.viewModel, viewModel)
    }

    abstract fun createViewModel(): V

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onViewDestroy()
    }

}