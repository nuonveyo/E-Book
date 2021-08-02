package com.veyo.ebook.di.component

import android.app.Application
import com.veyo.ebook.App
import com.veyo.ebook.di.module.ActivityModule
import com.veyo.ebook.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Veyo Nuon on 9/20/2019.
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}