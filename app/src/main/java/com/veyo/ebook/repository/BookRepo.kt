package com.veyo.ebook.repository

import com.veyo.ebook.di.utilities.PublicQualifier
import com.veyo.ebook.helper.observable.ResponseObserverHelper
import com.veyo.ebook.listener.OnCallbackListener
import com.veyo.ebook.model.Book
import com.veyo.ebook.model.ResultList
import com.veyo.ebook.repository.remote.ApiService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Veyo on 7/31/2021.
 */
@Singleton
class BookRepo @Inject constructor(
    @PublicQualifier
    private val apiService: ApiService
) {
    fun bookList(
        pageIndex: Int,
        onCallbackListener: OnCallbackListener<Response<ResultList<Book>>>
    ) {
        val respObserver = ResponseObserverHelper(apiService.getBookList(pageIndex))
        respObserver.execute(onCallbackListener)
    }

    fun topBookList(
        pageIndex: Int,
        onCallbackListener: OnCallbackListener<Response<ResultList<Book>>>
    ) {
        val respObserver = ResponseObserverHelper(apiService.getTopBookList(pageIndex))
        respObserver.execute(onCallbackListener)
    }

    fun getBookDetail(id: String?, onCallbackListener: OnCallbackListener<Response<Book>>) {
        val respObserver = ResponseObserverHelper(apiService.getBookDetail(id))
        respObserver.execute(onCallbackListener)
    }

    fun getTopBookDetail(
        id: String?,
        onCallbackListener: OnCallbackListener<Response<Book>>
    ) {
        val respObserver = ResponseObserverHelper(apiService.getTopBookDetail(id))
        respObserver.execute(onCallbackListener)
    }
}
