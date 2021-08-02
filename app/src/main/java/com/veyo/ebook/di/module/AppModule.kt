package com.veyo.ebook.di.module

import dagger.Module

/**
 * Created by Veyo Nuon on 9/20/2019.
 */
@Module(includes = [ViewModelModule::class, PublicModule::class])
class AppModule {
//    @Singleton
//    @Provides
//    fun provideSharePref(application: Application, gson: Gson): SharePrefUtils {
//        return SharePrefUtils(application, gson)
//    }
}