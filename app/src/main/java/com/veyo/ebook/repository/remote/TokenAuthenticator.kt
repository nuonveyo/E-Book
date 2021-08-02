package com.veyo.ebook.repository.remote


import android.content.Context
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

/**
 * Created by Veyo on 06-May-2019.
 * This class currently doesn't work because API there is not authentication yet
 */
class TokenAuthenticator(private val mContext: Context) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

//    @Inject
//    var apiService: ApiService? = null
//
//    @Throws(IOException::class)
//    override fun authenticate(route: Route?, response: Response): Request? {
//        val component = (mContext.applicationContext as MVVMCoreApplication)
//            .getApplicationComponent()
//            .tokenComponent(TokenModule(mContext))
//        component.inject(this)
//
//        Timber.e("request new access token")
//        val callback = apiService!!.getRefreshToken()
//        val res = callback.execute()
//        if (res.body() == null) {
//            Timber.e("error generate token, server return body null")
//            return null
//        }
//
//        Timber.e("access token receive")
//        val token = res.body()!!.response()!!.body()
//        //TODO: set access token here into utility
////        SharedPrefUtils.setAccessToken(token!!.getToken(), mContext)
//        if (responseCount(response) == 1) {
//            return response.request().newBuilder()
//                .header(CONTENT_TYPE, "application/json;charset=UTF-8")
//                .header(AUTHENTICATION, BEARER + "").build()
//
//        } else {
//            Timber.e("The amount of entering authenticate method for this request is more than 1 time.")
//        }
//
//        return null
//    }
//
//    private fun responseCount(response: Response?): Int {
//        var response = response
//        var result = 1
//        while ((response = response!!.priorResponse()) != null) {
//            result++
//        }
//        return result
//    }
//
//    companion object {
//        private val CONTENT_TYPE = "Content-Type"
//        private val AUTHENTICATION = "Authorization"
//        private val BEARER = "Bearer "
//    }

}
