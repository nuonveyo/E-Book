package com.veyo.ebook.viewmodel

import com.veyo.ebook.R
import javax.inject.Inject

/**
 * Created by Veyo on 7/30/2021.
 */
class HomeViewModel @Inject constructor() : ToolbarViewModel() {

    override fun init() {
        //Set toolbar title
        title.set(myContext.get()?.getString(R.string.book_title))
    }
}