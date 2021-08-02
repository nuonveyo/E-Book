package com.veyo.ebook.repository.remote

import com.veyo.ebook.model.Book
import com.veyo.ebook.model.ResultList
import com.veyo.ebook.model.Review
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Veyo Nuon on 9/17/2019.
 */
interface ApiService {
    @GET("books")
    fun getBookList(@Query("page") pageIndex: Int): Observable<Response<ResultList<Book>>>

    @GET("top_books")
    fun getTopBookList(@Query("page") pageIndex: Int): Observable<Response<ResultList<Book>>>

    @GET("books/{id}")
    fun getBookDetail(@Path("id") id: String? = null): Observable<Response<Book>>

    @GET("books/{id}/reviews")
    fun getBookReviewList(@Path("id") id: String? = null): Observable<Response<ResultList<Review>>>

    @GET("top_books/{id}")
    fun getTopBookDetail(@Path("id") id: String? = null): Observable<Response<Book>>
}