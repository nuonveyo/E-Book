package com.veyo.ebook.model

import com.google.gson.annotations.SerializedName
import com.veyo.ebook.utility.DateTimeUtil

/**
 * Created by Veyo on 7/31/2021.
 */
data class Book(
    @SerializedName("@id")
    val id: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("publicationDate")
    val publicationDate: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("part")
    val part: String? = null,
    @SerializedName("place")
    val place: String? = null,
    @SerializedName("borrowCount")
    val borrowCount: Int? = null,
    @SerializedName("reviews")
    var reviews: MutableList<Review>? = null
) {
    fun getPublishDate(): String? {
        val convertDate = DateTimeUtil.covertTimeFromServerToLocalDate(
            publicationDate,
            DateTimeUtil.FORMAT_DATE_SERVER
        )
        return DateTimeUtil.formatDate(convertDate, DateTimeUtil.FORMAT_DATE_1)
    }

    fun id(): String? {
        return id?.replace("/books/", "")
    }

    fun topBookId(): String? {
        return id?.replace("/top_books/", "")
    }
}