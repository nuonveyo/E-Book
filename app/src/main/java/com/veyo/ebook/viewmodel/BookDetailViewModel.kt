package com.veyo.ebook.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.veyo.ebook.listener.OnCallbackListener
import com.veyo.ebook.model.Book
import com.veyo.ebook.repository.BookRepo
import retrofit2.Response
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * Created by Veyo on 8/1/2021.
 */
class BookDetailViewModel @Inject constructor(
    private val repo: BookRepo
) : ToolbarViewModel() {

    private val _book = MutableLiveData<Book>()
    val book: LiveData<Book> = _book

    override fun init() {
        //Set toolbar title
        title.set("Book Detail")
    }

    fun getBookById(id: String?) {
        isLoading.set(true)
        repo.getBookDetail(id, object : OnCallbackListener<Response<Book>> {
            override fun onFail(throwable: Throwable) {
                if (throwable is ConnectException || throwable is UnknownHostException) {
                    showError("Network connection error!")
                } else {
                    showError("Unknown error!")
                }
            }

            override fun onComplete(result: Response<Book>) {
                if (result.body() != null) {
                    isLoading.set(false)
                    _book.value = result.body()
                } else {
                    showError("Data is empty!")
                }
            }
        })
    }

}