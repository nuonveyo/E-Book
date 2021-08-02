package com.veyo.ebook.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created by Veyo Nuon on 9/17/2019.
 */

abstract class BindingFragment<T : ViewDataBinding> : BaseFragment() {

    lateinit var dataBinding: T
    private var myView: View? = null
    private var isDataLoaded = false

    @LayoutRes
    protected abstract fun getLayoutResource(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (myView == null) {
            dataBinding = DataBindingUtil.inflate(inflater, getLayoutResource(), container, false)
            dataBinding.lifecycleOwner = this
            myView = dataBinding.root
        }

        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isDataLoaded) {
            isDataLoaded = true
            onMyViewCreated(view, savedInstanceState)
        }
    }

    fun setVariable(id: Int, value: Any) {
        dataBinding.setVariable(id, value)
    }

    /**
     * Sub-Class override this method to operate binding data into view(mxl) to avoid duplicated view from back state
     */
    open fun onMyViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}