package com.veyo.ebook.utility

import android.content.Context
import com.google.gson.Gson

/**
 * Created by Veyo Nuon on 10/24/2019.
 */
class SharePrefUtils constructor(private val context: Context, private val gson: Gson) {

    companion object {
        const val ACCESS_TOKEN = "ACCESS_TOKEN"
    }

    fun setAccessToken(token: String?) {
        val sharedPrefs = context.getSharedPreferences(
            ACCESS_TOKEN, Context.MODE_PRIVATE
        )
        val editor = sharedPrefs.edit()
        editor.putString(ACCESS_TOKEN, token)
        editor.apply()
    }

    fun getAccessToken(): String? {
        val sharedPrefs = context.getSharedPreferences(
            ACCESS_TOKEN, Context.MODE_PRIVATE
        )
        return sharedPrefs.getString(ACCESS_TOKEN, null)
    }

}