package com.veyo.ebook.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Veyo on 8/1/2021.
 */
data class Review(
    @SerializedName("@id")
    val id: String? = null,
    @SerializedName("body")
    val body: String? = null
)