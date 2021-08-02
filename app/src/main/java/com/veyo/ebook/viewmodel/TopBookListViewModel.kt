package com.veyo.ebook.viewmodel

import androidx.lifecycle.MutableLiveData
import com.veyo.ebook.listener.OnCallbackListener
import com.veyo.ebook.model.Book
import com.veyo.ebook.model.ResultList
import com.veyo.ebook.repository.BookRepo
import retrofit2.Response
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * Created by Veyo on 8/1/2021.
 */
class TopBookListViewModel @Inject constructor(
    private val repo: BookRepo
) : PaginationViewModel<Book>() {
    val bookList = MutableLiveData<MutableList<Book>>()

    override fun init() {
        isLoading.set(true)
        getBookList()
    }

    private fun getBookList() {
        repo.topBookList(pageIndex, object : OnCallbackListener<Response<ResultList<Book>>> {
            override fun onFail(throwable: Throwable) {
                isHasMoreItems = false
                if (throwable is ConnectException || throwable is UnknownHostException) {
                    showError("Network connection error!")
                } else {
                    showError("Unknown error!")
                }
            }

            override fun onComplete(result: Response<ResultList<Book>>) {
                clearAllLoading()
                val items = result.body()?.items
                //Check whenever has more item to load
                checkHasMoreItems(items)
                if (!items.isNullOrEmpty()) {
                    bookList.value = items
                } else {
                    showError("Data is empty!")
                }
            }
        })
    }

    fun loadMoreItem(onSuccess: (MutableList<Book>) -> Unit, onFail: () -> Unit) {
        repo.topBookList(pageIndex, object : OnCallbackListener<Response<ResultList<Book>>> {
            override fun onFail(throwable: Throwable) {
                isHasMoreItems = false
                onFail()
            }

            override fun onComplete(result: Response<ResultList<Book>>) {
                val items = result.body()?.items
                //Check whenever has more item to load
                checkHasMoreItems(items)
                if (items.isNullOrEmpty()) {
                    onFail()
                } else {
                    onSuccess(items)
                }
            }
        })
    }

    override fun onRefresh() {
        super.onRefresh()
        pageIndex = 1
        getBookList()
    }

    override fun pageLimitSize(): Int = 10
}