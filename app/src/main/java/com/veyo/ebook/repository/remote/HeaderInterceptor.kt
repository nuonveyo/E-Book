package com.veyo.ebook.repository.remote

import android.content.Context
import android.text.TextUtils
import com.veyo.ebook.utility.SharePrefUtils
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import java.io.IOException

/**
 * Created by Veyo on 06-May-2019.
 */
class HeaderInterceptor(private val mContext: Context, private val sharePrefUtils: SharePrefUtils) :
    Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain
            .request()
            .newBuilder()

        val token = sharePrefUtils.getAccessToken()
        Timber.e("Header token: %s", token)
        if (!TextUtils.isEmpty(token)) {
            builder.addHeader(CONTENT_TYPE, "application/json;charset=UTF-8")
            builder.addHeader(AUTHENTICATION, BEARER + token)
        }

        val request = builder.build()
        return chain.proceed(request)
    }

    companion object {
        private const val CONTENT_TYPE = "Content-Type"
        private const val AUTHENTICATION = "Authorization"
        private const val BEARER = "Bearer "
    }
}
