package com.veyo.ebook.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Veyo on 7/31/2021.
 */
data class ResultList<T>(
    @SerializedName("@id")
    val id: String? = null,
    @SerializedName("hydra:member")
    val items: MutableList<T>? = null
)