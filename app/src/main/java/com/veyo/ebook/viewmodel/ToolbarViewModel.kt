package com.veyo.ebook.viewmodel

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField


/**
 * Created by Veyo on 6/16/2021.
 */
abstract class ToolbarViewModel : BaseViewModel() {
    //Toolbar title
    val title = ObservableField<String>()

    /**
     * Handle toolbar navigation click to back to previous state
     */
    open fun setNavigationOnClickListener(): View.OnClickListener {
        return View.OnClickListener {
            if (myContext.get() is AppCompatActivity) {
                (myContext.get() as AppCompatActivity).onBackPressed()
            }
        }
    }
}