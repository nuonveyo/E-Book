package com.veyo.ebook.di.module

import com.google.gson.Gson
import com.veyo.ebook.di.utilities.PublicQualifier
import com.veyo.ebook.repository.remote.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Veyo on 06-May-2019.
 */
@Module(includes = [NetworkModule::class])
class PublicModule {

    @Singleton
    @Provides
    @PublicQualifier
    fun provideApiService(@PublicQualifier retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    @PublicQualifier
    fun provideRetrofit(
        @PublicQualifier builder: Retrofit.Builder,
        url: String
    ): Retrofit =
        builder.baseUrl(url).build()

    @Singleton
    @Provides
    @PublicQualifier
    fun provideRetrofitBuilder(
        @PublicQualifier okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    @PublicQualifier
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .build()
    }
}
