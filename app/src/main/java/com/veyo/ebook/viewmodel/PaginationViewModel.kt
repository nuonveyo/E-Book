package com.veyo.ebook.viewmodel

import timber.log.Timber

/**
 * Created by Veyo on 8/2/2021.
 */
abstract class PaginationViewModel<T> : ToolbarViewModel() {
    var pageIndex = 1
    var isHasMoreItems = true

    /**
     * Subclass override to init pagination size of the request API list in current screen
     */
    abstract fun pageLimitSize(): Int

    fun checkHasMoreItems(items: MutableList<T>?) {
        isHasMoreItems = if (items.isNullOrEmpty() || items.size < pageLimitSize()) {
            false
        } else {
            pageIndex++
            true
        }
    }
}