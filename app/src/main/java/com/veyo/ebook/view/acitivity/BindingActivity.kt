package com.veyo.ebook.view.acitivity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created by Veyo Nuon on 9/17/2019.
 */
abstract class BindingActivity<T : ViewDataBinding> : AppCompatActivity() {

    lateinit var dataBinding: T

    @LayoutRes
    protected abstract fun getLayoutResource(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, getLayoutResource())
        dataBinding.lifecycleOwner = this
    }

    fun setVariable(id: Int, value: Any) {
        dataBinding.setVariable(id, value)
    }

}