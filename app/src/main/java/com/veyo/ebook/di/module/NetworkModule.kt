package com.veyo.ebook.di.module

import android.app.Application
import com.google.gson.Gson
import com.veyo.ebook.BuildConfig
import com.veyo.ebook.repository.remote.ApiService
import com.veyo.ebook.repository.remote.HeaderInterceptor
import com.veyo.ebook.repository.remote.TokenAuthenticator
import com.veyo.ebook.utility.SharePrefUtils
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Veyo Nuon on 9/17/2019.
 */
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideUrl(): String = BuildConfig.HOST_WS

    @Singleton
    @Provides
    fun provideGSon(): Gson = Gson()

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor { message -> Timber.i(message) }
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }

    @Singleton
    @Provides
    fun provideAuthenticator(context: Application): TokenAuthenticator =
        TokenAuthenticator(context.applicationContext)

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient, gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideServerRetrofit(builder: Retrofit.Builder, url: String): Retrofit =
        builder.baseUrl(url).build()

    @Singleton
    @Provides
    fun provideOkHttpClient(
        authenticator: TokenAuthenticator,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: HeaderInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .authenticator(authenticator)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(headerInterceptor)
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    @Singleton
    @Provides
    fun provideHeaderInterceptor(
        context: Application,
        sharePrefUtils: SharePrefUtils
    ): HeaderInterceptor =
        HeaderInterceptor(
            context.applicationContext,
            sharePrefUtils
        )


}