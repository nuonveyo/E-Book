package com.veyo.ebook.view.widget

import android.content.Context
import android.graphics.PointF
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler

/**
 * Created by Veyo on 8/1/2021.
 */
class LoaderMoreLayoutManager(context: Context?) : LinearLayoutManager(context) {
    private var isLoadingMore = false
    private var loadMoreListener: () -> Unit = {}
    private var linearSmoothScroller: LinearSmoothScroller? = null
    private var stopLoadMore = false

    var isStopLoadMore: Boolean
        get() = stopLoadMore
        set(value) {
            stopLoadMore = !value
        }

    override fun scrollVerticallyBy(
        dy: Int,
        recycler: Recycler,
        state: RecyclerView.State
    ): Int {
        if (!isStopLoadMore && !isLoadingMore && findLastVisibleItemPosition() >= itemCount - 1) {
            isLoadingMore = true
            loadMoreListener()
        }
        return super.scrollVerticallyBy(dy, recycler, state)
    }

    fun setOnLoadMoreListener(onLoadMoreListener: () -> Unit) {
        loadMoreListener = onLoadMoreListener
    }

    fun loadingFinished() {
        isLoadingMore = false
    }

    override fun smoothScrollToPosition(
        recyclerView: RecyclerView, state: RecyclerView.State,
        position: Int
    ) {
        if (linearSmoothScroller == null) {
            linearSmoothScroller = TopSnappedSmoothScroller(recyclerView.context)
        }
        linearSmoothScroller!!.targetPosition = position
        startSmoothScroll(linearSmoothScroller)
    }

    private inner class TopSnappedSmoothScroller(context: Context?) :
        LinearSmoothScroller(context) {
        override fun computeScrollVectorForPosition(targetPosition: Int): PointF? {
            return this@LoaderMoreLayoutManager
                .computeScrollVectorForPosition(targetPosition)
        }

        override fun getVerticalSnapPreference(): Int {
            return SNAP_TO_START
        }
    }
}