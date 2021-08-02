package com.veyo.ebook.listener

/**
 * Created by nuonveyo on 1/4/18.
 */
interface OnCallbackListener<T> : OnCompleteListener<T> {
    fun onFail(throwable: Throwable)
}