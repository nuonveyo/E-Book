package com.veyo.ebook.viewmodel.databinding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.appbar.MaterialToolbar

/**
 * Created by Veyo on 8/1/2021.
 */

@BindingAdapter("visibleView")
fun visibleView(v: View, isShow: Boolean) {
    v.visibility = if (isShow) View.VISIBLE else View.GONE
}

@BindingAdapter("setRefreshing")
fun setRefreshing(layout: SwipeRefreshLayout, isRefreshing: Boolean) {
    layout.isRefreshing = isRefreshing
}

@BindingAdapter("setOnRefreshListener")
fun setOnRefreshListener(
    layout: SwipeRefreshLayout,
    listener: SwipeRefreshLayout.OnRefreshListener
) {
    layout.setOnRefreshListener(listener)

}

@BindingAdapter(
    "onToolbarNavigationClickListener"
)
fun onToolbarNavigationClickListener(
    topAppBarLayout: MaterialToolbar,
    onClickListener: View.OnClickListener?,
) {
    topAppBarLayout.setNavigationOnClickListener(onClickListener)
}