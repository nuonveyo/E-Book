package com.veyo.ebook

import android.app.Application
import com.veyo.ebook.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Veyo on 7/29/2021.
 */
class App : Application(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)

        if (BuildConfig.DEBUG) {
            //To available log only in development mode
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun androidInjector() = dispatchingAndroidInjector
}