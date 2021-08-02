package com.veyo.ebook.viewmodel

import android.content.Context
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel

/**
 * Created by Veyo Nuon on 10/10/2019.
 */
abstract class BaseViewModel : ViewModel(), LifecycleObserver {
    val myContext = ObservableField<Context?>()
    val view = ObservableField<View>()

    val errorMsg = ObservableField<String>()
    val isError = ObservableBoolean()
    val isLoading = ObservableBoolean()
    val isRefresh = ObservableField<Boolean>()

    /**
     * Sub class override to init value after object have been created
     */
    open fun init() {

    }

    /**
     * Sub class override to implement on user swipe to refresh data
     */
    open fun onRefresh() {
        isRefresh.set(true)
    }

    open fun showError(msg: String?) {
        errorMsg.set(msg)
        isLoading.set(false)
        isError.set(true)
    }

    open fun hideError() {
        errorMsg.set(null)
        isLoading.set(false)
        isError.set(false)
    }

    fun clearAllLoading() {
        isLoading.set(false)
        isRefresh.set(false)
        isError.set(false)
    }

    /**
     * Sub class override to handle some task when fragment view destroyed
     */
    open fun onViewDestroy() {
        //Stub
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        onViewDestroy()
    }
}